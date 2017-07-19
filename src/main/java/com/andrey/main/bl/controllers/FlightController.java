package com.andrey.main.bl.controllers;

import com.andrey.main.bl.Utils.DialogManager;
import com.andrey.main.bl.Utils.FXUtil;
import com.andrey.main.bl.access.AccessHandler;
import com.andrey.main.bl.access.MyPermission;
import com.andrey.main.bl.access.PermissionAction;
import com.andrey.main.bl.operations.ProxyOperations;
import com.andrey.main.bl.services.DestinationService;
import com.andrey.main.dl.dao.DestinationDAO;
import com.andrey.main.dl.dao.InitialData;
import com.andrey.main.dl.data.FlightStatus;
import com.andrey.main.dl.models.Arrivals;
import com.andrey.main.dl.models.Departures;
import com.andrey.main.dl.models.Destination;
import com.andrey.main.dl.models.Flight;
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
import java.time.LocalDateTime;
import java.util.List;
import java.util.ResourceBundle;

import static com.andrey.main.dl.dao.InitialData.LOCALE_VALUE;
import static com.andrey.main.dl.dao.InitialData.PATH_BUNDLES_LOCALE;
import static javafx.scene.control.ButtonType.*;

public class FlightController implements Initializable, ProxyOperations {
    @FXML
    private ToggleButton btnArrivals;
    @FXML
    private ToggleButton btnDepartures;
    @FXML
    private TextField txtSearch;
    @FXML
    private RadioButton rbByNumber;
    @FXML
    private RadioButton rbByCity;
    private ToggleGroup groupRB = new ToggleGroup();
    //    @FXML
//    private ToggleGroup groupButton = new ToggleGroup();
    //    @FXML
    public TableView<Destination> tableFlight;
    @FXML
    private MenuItem conMenuNew;
    //    @FXML
//    private TableColumn<Destination, Integer> columnId;
    @FXML
    private TableColumn<Flight, String> columnNumber;
    @FXML
    private TableColumn<Destination, LocalDateTime> columnDateTime;
    @FXML
    private TableColumn<Destination, String> city;
    @FXML
    private TableColumn<Destination, Character> columnTerminal;
    @FXML
    private TableColumn<Destination, FlightStatus> columnStatus;
    @FXML
    private TableColumn<Destination, String> columnGate;

    @FXML
    private Label labelCount;

    private ObservableList<Destination> flights = FXCollections.observableArrayList();
    //    private FlightDAO flightDAO = FlightDAO.getInstance();
    private DestinationService destinationService = new DestinationService();
    private ResourceBundle resources;
    private URL location;

    private Parent fxmlEdit;
    private FXMLLoader fxmlLoader = new FXMLLoader();
    private EditFlightController editFlightController;
    private Stage editDialogStage;
    private ProxyOperations proxyController;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.resources = resources;
        this.location = location;
//        System.out.println("url: " + location);
        rbByNumber.setToggleGroup(groupRB);
        rbByNumber.setSelected(true);
        rbByCity.setToggleGroup(groupRB);


        initTable();
        initEditDialog();
//        updateCountLable();
        initListner();

        proxyController = (ProxyOperations) AccessHandler.newInstance(this);

    }


    private void initEditDialog() {

        try {
            URL resource = getClass().getResource("/fxml/editFlying.fxml");
            fxmlLoader.setLocation(resource);
            fxmlLoader.setResources(ResourceBundle.getBundle(PATH_BUNDLES_LOCALE, LOCALE_VALUE));

            fxmlEdit = fxmlLoader.load();
            editFlightController = fxmlLoader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initTable() {
        columnNumber.setCellValueFactory(new PropertyValueFactory<Flight, String>("flight"));
        columnDateTime.setCellValueFactory(new PropertyValueFactory<Destination, LocalDateTime>("date"));
        city.setCellValueFactory(new PropertyValueFactory<Destination, String>("city"));
        columnTerminal.setCellValueFactory(new PropertyValueFactory<Destination, Character>("terminal"));
        columnStatus.setCellValueFactory(new PropertyValueFactory<Destination, FlightStatus>("status"));
        columnGate.setCellValueFactory(new PropertyValueFactory<Destination, String>("gate"));

        flights.addAll(destinationService.getAll());

        tableFlight.setItems(flights);
        updateCountLable();
    }

    private void initListner() {
        flights.addListener(new ListChangeListener<Destination>() {
            @Override
            public void onChanged(Change<? extends Destination> c) {
                updateCountLable();
            }
        });

        tableFlight.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                editRow();
            }
        });
        tableFlight.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                editRow();
            }
            if (event.getCode().equals(KeyCode.DELETE)) {
                deleteRow();
            }

        });

    }

    @FXML
    private void deleteRow() {
        proxyController.delete();
    }

    @FXML
    private void editRow() {
        proxyController.edit();
    }

    private void editFlight() {
        Destination selectedFlight = (Destination) tableFlight.getSelectionModel().getSelectedItem();
        editFlightController.setDestination(selectedFlight);
        showDialog();
        Destination flightEdit = editFlightController.getDestination();
        System.out.println("editRow" + flightEdit);
        destinationService.update(flightEdit);
        flights.set(tableFlight.getSelectionModel().getSelectedIndex(), flightEdit);
    }

    @FXML
    private void createRow() {
        proxyController.add();
    }

    private void createFlight() {
        if (btnArrivals.isSelected()) {
            editFlightController.setDestination(new Arrivals());
        }
        if (btnDepartures.isSelected()) {
            editFlightController.setDestination(new Departures());
        }

        showDialog();
        Destination destination = editFlightController.getDestination();

        if (destination.hashCode() != 0) {
            destinationService.add(destination);
            updateTable();
        }
    }

    private void updateTable() {
        flights.clear();
        flights.addAll(destinationService.getAll());
    }

    private void updateCountLable() {
        labelCount.setText(FXUtil.updateCountLable(flights));
    }


    private void showDialog() {
        fxmlLoader.setResources(ResourceBundle.getBundle(PATH_BUNDLES_LOCALE, LOCALE_VALUE));
        editDialogStage = FXUtil.showDialog(fxmlEdit, editDialogStage);
    }

    @Override
    @MyPermission(value = {PermissionAction.STAFF, PermissionAction.ADMIN})
    public void add() {
        createFlight();
    }

    @Override
    @MyPermission(value = {PermissionAction.STAFF, PermissionAction.ADMIN})
    public void edit() {
        editFlight();
    }

    @Override
    @MyPermission(PermissionAction.ADMIN)
    public void delete() {
//        DialogManager.showInfoDialog(resources.getString("dm.info"), resources.getString("main.not_select_row"));
        boolean yes = DialogManager.showInfoDialog(resources.getString("dm.info"), resources.getString("main.deleteRow"),
                new Alert(Alert.AlertType.INFORMATION, "", YES, NO, CANCEL));
        if (yes) {
            destinationService.delete((Destination) tableFlight.getSelectionModel().getSelectedItem());
            flights.remove(tableFlight.getSelectionModel().getSelectedIndex());
        }
    }

    @FXML
    private void search() {
        String text = txtSearch.getText();
        List<Destination> list = null;
        if (rbByNumber.isSelected()) {
            list = destinationService.searchByNumber(text);
        }
        if (rbByCity.isSelected()) {
            list = destinationService.searchByCity(text);
        }
        flights.clear();
        flights.addAll(list);
    }

    @FXML
    private void showArrivals() {
        DestinationDAO.setTABLE(InitialData.TABLE_ARRIVALS);
        updateTable();
    }

    @FXML
    private void showDepartures() {
        DestinationDAO.setTABLE(InitialData.TABLE_DEPARTURES);
        updateTable();
    }
}
