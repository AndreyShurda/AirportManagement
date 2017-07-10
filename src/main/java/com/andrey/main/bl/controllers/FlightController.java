package com.andrey.main.bl.controllers;

import com.andrey.main.bl.Utils.DialogManager;
import com.andrey.main.bl.Utils.FXUtil;
import com.andrey.main.bl.access.AccessHandler;
import com.andrey.main.bl.access.MyPermission;
import com.andrey.main.bl.access.PermissionAction;
import com.andrey.main.bl.operations.ProxyOperations;
import com.andrey.main.bl.services.FlightService;
import com.andrey.main.dl.dao.FlightDAO;
import com.andrey.main.dl.dao.InitialData;
import com.andrey.main.dl.data.FlightStatus;
import com.andrey.main.dl.models.Flight;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
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
    private TextField txtSearch;
    @FXML
    private RadioButton rbByNumber;
    @FXML
    private RadioButton rbByCity;
    private ToggleGroup groupRB = new ToggleGroup();
    //    @FXML
//    private ToggleGroup groupButton = new ToggleGroup();
    //    @FXML
    public TableView<Flight> tableFlight;
    @FXML
    private MenuItem conMenuNew;
    //    @FXML
//    private TableColumn<Flight, Integer> columnId;
    @FXML
    private TableColumn<Flight, String> columnNumber;
    @FXML
    private TableColumn<Flight, LocalDateTime> columnDateTime;
    @FXML
    private TableColumn<Flight, String> city;
    @FXML
    private TableColumn<Flight, Character> columnTerminal;
    @FXML
    private TableColumn<Flight, FlightStatus> columnStatus;
    @FXML
    private TableColumn<Flight, String> columnGate;

    @FXML
    private Label labelCount;

    private ObservableList<Flight> flights = FXCollections.observableArrayList();
    //    private FlightDAO flightDAO = FlightDAO.getInstance();
    private FlightService flightService
            = new FlightService();
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

//        System.out.println("FlightControllerFX initEditDialog()");
        try {
            URL resource = getClass().getResource("/fxml/editFlying.fxml");
//        System.out.println("resources: " + resources);
            fxmlLoader.setLocation(resource);
            fxmlLoader.setResources(ResourceBundle.getBundle(PATH_BUNDLES_LOCALE, LOCALE_VALUE));
//            fxmlLoader.setResources(ResourceBundle.getBundle("test.bundles.Locale", new Locale("ru")));

            fxmlEdit = fxmlLoader.load();
            editFlightController = fxmlLoader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initTable() {
//        columnId.setCellValueFactory(new PropertyValueFactory<Flight, Integer>("id"));
        columnNumber.setCellValueFactory(new PropertyValueFactory<Flight, String>("number"));
        columnDateTime.setCellValueFactory(new PropertyValueFactory<Flight, LocalDateTime>("date"));
        city.setCellValueFactory(new PropertyValueFactory<Flight, String>("city"));
//        columnTo.setCellValueFactory(new PropertyValueFactory<Flight, String>("to"));
        columnTerminal.setCellValueFactory(new PropertyValueFactory<Flight, Character>("terminal"));
        columnStatus.setCellValueFactory(new PropertyValueFactory<Flight, FlightStatus>("status"));
        columnGate.setCellValueFactory(new PropertyValueFactory<Flight, String>("gate"));

        flights.addAll(flightService.getAll());

        tableFlight.setItems(flights);
        updateCountLable();
    }

    private void initListner() {
        flights.addListener(new ListChangeListener<Flight>() {
            @Override
            public void onChanged(Change<? extends Flight> c) {
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
        Flight selectedFlight = (Flight) tableFlight.getSelectionModel().getSelectedItem();
        editFlightController.setFlight(selectedFlight);
        showDialog();
        Flight flightEdit = editFlightController.getFlight();
        System.out.println("editRow" + flightEdit);
        if (!selectedFlight.equals(flightEdit)) {
            flightService.update(flightEdit);
            flights.set(tableFlight.getSelectionModel().getSelectedIndex(), flightEdit);
        }
    }

    @FXML
    private void createRow() {
        proxyController.add();
    }

    private void createFlight() {
        editFlightController.setFlight(new Flight());
        showDialog();
        Flight newFlight = editFlightController.getFlight();
        if (newFlight.hashCode() != 0) {
//        System.out.println("newFlight = " + newFlight);
            flightService.add(newFlight);
            updateTable();
        }
    }

    private void updateTable() {
        flights.clear();
        flights.addAll(flightService.getAll());
    }

    private void updateCountLable() {
        labelCount.setText(FXUtil.updateCountLable(flights));
    }


    private void showDialog() {
        fxmlLoader.setResources(ResourceBundle.getBundle(PATH_BUNDLES_LOCALE, LOCALE_VALUE));
        editDialogStage = FXUtil.showDialog(fxmlEdit, editDialogStage);
//        editDialogStage = FXUtil.showDialog(new ActionEvent(), fxmlEdit, editDialogStage);
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
            flightService.delete((Flight) tableFlight.getSelectionModel().getSelectedItem());
            flights.remove(tableFlight.getSelectionModel().getSelectedIndex());
        }
    }

    @FXML
    private void search() {
        String text = txtSearch.getText();
        List<Flight> list = null;
        if (rbByNumber.isSelected()) {
            list = flightService.searchByNumber(text);
        }
        if (rbByCity.isSelected()) {
            list = flightService.searchByCity(text);
        }
        flights.clear();
        flights.addAll(list);
    }

    @FXML
    private void showArrivals() {
        FlightDAO.setTABLE(InitialData.TABLE_ARRIVALS);
        updateTable();
    }

    @FXML
    private void showDepartures() {
        FlightDAO.setTABLE(InitialData.TABLE_DEPARTURES);
        updateTable();
    }
}
