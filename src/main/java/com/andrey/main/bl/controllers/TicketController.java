package com.andrey.main.bl.controllers;

import com.andrey.main.bl.Utils.FXUtil;
import com.andrey.main.bl.access.AccessHandler;
import com.andrey.main.bl.access.MyPermission;
import com.andrey.main.bl.access.PermissionAction;
import com.andrey.main.bl.operations.MenuItemsEdit;
import com.andrey.main.dl.dao.FlightDAO;
import com.andrey.main.dl.dao.TicketDAO;
import com.andrey.main.dl.data.ClassType;
import com.andrey.main.dl.models.Flight;
import com.andrey.main.dl.models.Ticket;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.andrey.main.dl.dao.InitialData.LOCALE_VALUE;
import static com.andrey.main.dl.dao.InitialData.PATH_BUNDLES_LOCALE;


public class TicketController implements Initializable, MenuItemsEdit {
    @FXML
    private Label labelCount;
    @FXML
    private TextField txtFrom;
    @FXML
    private TextField txtTo;

    //    @FXML
    public TableView tableTickets;
    @FXML
    private TableColumn<Ticket, String> columnFlightNumber;
    @FXML
    private TableColumn<Ticket, Double> columnPrice;
    @FXML
    private TableColumn<Ticket, ClassType> columnClassType;


    private URL location;
    private ResourceBundle resources;
    private ObservableList<Ticket> tickets = FXCollections.observableArrayList();
    private TicketDAO ticketDAO = TicketDAO.getInstance();

    private final String editTicketFile = "/fxml/editTicket.fxml";
    private Parent fxmlEdit;
    private EditTicketController editTicketController;
    private Stage editDialogStage;
    private MenuItemsEdit proxyController;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.location = location;
        this.resources = resources;

        initTable();
        initEditDialog();
        initListner();
        updateCountLable();

        proxyController = (MenuItemsEdit) AccessHandler.newInstance(this);
    }

    private void initTable() {
        columnFlightNumber.setCellValueFactory(new PropertyValueFactory<Ticket, String>("IdFlight"));
        columnPrice.setCellValueFactory(new PropertyValueFactory<Ticket, Double>("price"));
        columnClassType.setCellValueFactory(new PropertyValueFactory<Ticket, ClassType>("classType"));

        tickets.addAll(ticketDAO.getAll());

        tableTickets.setItems(tickets);
    }

    private void initEditDialog() {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(editTicketFile), ResourceBundle.getBundle(PATH_BUNDLES_LOCALE, LOCALE_VALUE));
        try {
            fxmlEdit = loader.load();
            editTicketController = loader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void initListner() {
        tickets.addListener(new ListChangeListener<Ticket>() {
            @Override
            public void onChanged(Change<? extends Ticket> c) {
                updateCountLable();
            }
        });

        tableTickets.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                editRow();
//                System.out.println("cliked 2");
            }
        });

        tableTickets.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                editRow();
            }
            if (event.getCode().equals(KeyCode.DELETE)) {
                deleteRow();
            }
        });

        txtFrom.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (!newValue.matches("\\d*")) {
                    txtFrom.setText(newValue.replaceAll("[^\\d]", ""));
                }
            }
        });

    }

    private void updateCountLable() {
        labelCount.setText(FXUtil.updateCountLable(tickets));
    }

    private void showDialog() {
        editDialogStage = FXUtil.showDialog(fxmlEdit, editDialogStage);
//        fxmlLoader.setResources(ResourceBundle.getBundle(PATH_BUNDLES_LOCALE, LOCALE_VALUE));
    }


    private void updateTable() {
        tickets.clear();
        tickets.addAll(ticketDAO.getAll());
    }

    private void createRow() {
        editTicketController.setTicket(new Ticket());
        showDialog();
        Ticket newTicket = editTicketController.getTicket();
        if (newTicket.hashCode() != 0) {
//        System.out.println("newFlight = " + newFlight);
            for (Flight flight : FlightDAO.getInstance().getAll()) {
                if (flight.getNumber().equals(newTicket.getIdFlight())) {
                    newTicket.setIdFlight(String.valueOf(flight.getId()));
                    break;
                }
            }
            ticketDAO.add(newTicket);
            updateTable();
        }
    }


    private void editRow() {
        proxyController.edit();
    }

    private void editTicket() {
        Ticket selectedTicked = (Ticket) tableTickets.getSelectionModel().getSelectedItem();
        int selectedIndex = tableTickets.getSelectionModel().getSelectedIndex();
        editTicketController.setTicket(selectedTicked);
        showDialog();
        Ticket ticketEdit = editTicketController.getTicket();
//        System.out.println("editRow" + ticketEdit);
        if (!selectedTicked.equals(ticketEdit)) {
            tickets.set(selectedIndex, ticketEdit);
            for (Flight flight : FlightDAO.getInstance().getAll()) {
                if (flight.getNumber().equals(ticketEdit.getIdFlight())) {

                    Ticket ticketUpdate = new Ticket(ticketEdit.getIdTicket(), String.valueOf(flight.getId()), ticketEdit.getPrice(), ticketEdit.getClassType());
//                    ticketUpdate.setIdFlight(String.valueOf(flight.getId()));
//                    System.out.println("123:ww " + ticketUpdate);
                    ticketDAO.update(ticketUpdate);
                }
            }
        }
    }

    private void deleteRow() {
        proxyController.delete();
    }


    @Override
    @MyPermission(value = {PermissionAction.STAFF, PermissionAction.ADMIN})
    public void add() {
        createRow();
    }

    @Override
    @MyPermission(value = {PermissionAction.STAFF, PermissionAction.ADMIN})
    public void edit() {
        editTicket();
    }

    @Override
    @MyPermission(value = {PermissionAction.ADMIN})
    public void delete() {
        ticketDAO.delete((Ticket) tableTickets.getSelectionModel().getSelectedItem());
        tickets.remove(tableTickets.getSelectionModel().getSelectedIndex());
    }

    @FXML
    public void search() {
        double from = getNumberFromField(txtFrom);
        double to = getNumberFromField(txtTo);
        tickets.clear();
//        tickets.addAll(searchService.searchByPrice(c -> (c.getPrice() >= from && c.getPrice() <= to)));
        tickets.addAll(ticketDAO.searchByPrice(from, to));
    }

    private double getNumberFromField(TextField textField) {
        if (textField.getText().isEmpty())
            return 0.0;
        else
            return Double.valueOf(textField.getText());
    }
}
