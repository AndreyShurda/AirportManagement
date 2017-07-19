package com.andrey.main.bl.controllers;

import com.andrey.main.bl.Utils.DialogManager;
import com.andrey.main.bl.Utils.FXUtil;
import com.andrey.main.bl.access.AccessHandler;
import com.andrey.main.bl.access.MyPermission;
import com.andrey.main.bl.access.PermissionAction;
import com.andrey.main.bl.operations.ProxyOperations;
import com.andrey.main.bl.services.TicketService;
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

import static com.andrey.main.dl.dao.utils.InitialData.LOCALE_VALUE;
import static com.andrey.main.dl.dao.utils.InitialData.PATH_BUNDLES_LOCALE;
import static javafx.scene.control.ButtonType.CANCEL;
import static javafx.scene.control.ButtonType.NO;
import static javafx.scene.control.ButtonType.YES;


public class TicketController implements Initializable, ProxyOperations {
    @FXML
    private Label labelCount;
    @FXML
    private TextField txtFrom;
    @FXML
    private TextField txtTo;

    @FXML
    public TableView tableTickets;
    @FXML
    private TableColumn<Flight, String> columnFlightNumber;
    @FXML
    private TableColumn<Ticket, Double> columnPrice;
    @FXML
    private TableColumn<Ticket, ClassType> columnClassType;


    private URL location;
    private ResourceBundle resources;
    private ObservableList<Ticket> tickets = FXCollections.observableArrayList();
    private TicketService ticketService = new TicketService();

    private final String editTicketFile = "/fxml/editTicket.fxml";
    private Parent fxmlEdit;
    private EditTicketController editTicketController;
    private Stage editDialogStage;
    private ProxyOperations proxyController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.location = location;
        this.resources = resources;

        initTable();
        initEditDialog();
        initListner();
        updateCountLable();

        proxyController = (ProxyOperations) AccessHandler.newInstance(this);
    }

    private void initTable() {
        columnFlightNumber.setCellValueFactory(new PropertyValueFactory<Flight, String>("flight"));
        columnPrice.setCellValueFactory(new PropertyValueFactory<Ticket, Double>("price"));
        columnClassType.setCellValueFactory(new PropertyValueFactory<Ticket, ClassType>("classType"));

        tickets.addAll(ticketService.getAll());

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
            }
        });

        tableTickets.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                editRow();
            }
//            if (event.getCode().equals(KeyCode.DELETE)) {
//                deleteRow();
//            }
        });

        txtFrom.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
//                if (!newValue.matches("[0-9]*\\\\.?[0-9]+")) {
                if (!newValue.matches("\\d+\\.\\d+")) {
//                    txtFrom.setText(newValue.replaceAll("[^\\d]", ""));
                    int length = newValue.length();
//                    if (length > 0) {
//                        txtFrom.setText(newValue.substring(0, length - 1));
//                    }
                    System.out.println(newValue.substring(0, length - 1));
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


    public void updateTable() {
        tickets.clear();
        tickets.addAll(ticketService.getAll());
    }

    private void createRow() {
        editTicketController.setTicket(new Ticket());
        showDialog();
        Ticket newTicket = editTicketController.getTicket();
        if (newTicket.hashCode() != 0) {
            ticketService.add(newTicket);
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
        ticketService.update(ticketEdit);
        tickets.set(selectedIndex, ticketEdit);
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
        boolean yes = DialogManager.showInfoDialog(resources.getString("dm.info"), resources.getString("main.deleteRow"),
                new Alert(Alert.AlertType.INFORMATION, "", YES, NO, CANCEL));
        if (yes) {
            ticketService.delete((Ticket) tableTickets.getSelectionModel().getSelectedItem());
            tickets.remove(tableTickets.getSelectionModel().getSelectedIndex());
        }
    }

    @FXML
    public void search() {
        double from = getNumberFromField(txtFrom);
        double to = getNumberFromField(txtTo);
        tickets.clear();
        if (from == 0 && to == 0) {
            tickets.addAll(ticketService.getAll());
        }
//        tickets.addAll(searchService.searchByPrice(c -> (c.getPrice() >= from && c.getPrice() <= to)));
        tickets.addAll(ticketService.searchByPrice(from, to));
    }

    private double getNumberFromField(TextField textField) {
        if (textField.getText().isEmpty())
            return 0.0;
        else
            return Double.valueOf(textField.getText());
    }
}
