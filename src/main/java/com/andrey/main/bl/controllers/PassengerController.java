package com.andrey.main.bl.controllers;

import com.andrey.main.bl.Utils.DialogManager;
import com.andrey.main.bl.Utils.FXUtil;
import com.andrey.main.bl.access.AccessHandler;
import com.andrey.main.bl.access.MyPermission;
import com.andrey.main.bl.access.PermissionAction;
import com.andrey.main.bl.operations.ProxyOperations;
import com.andrey.main.bl.services.PassengerService;
import com.andrey.main.dl.data.Gender;
import com.andrey.main.dl.data.ClassType;
import com.andrey.main.dl.models.Flight;
import com.andrey.main.dl.models.Passenger;
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
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.ResourceBundle;

import static com.andrey.main.dl.dao.utils.InitialData.LOCALE_VALUE;
import static com.andrey.main.dl.dao.utils.InitialData.PATH_BUNDLES_LOCALE;
import static javafx.scene.control.ButtonType.CANCEL;
import static javafx.scene.control.ButtonType.NO;
import static javafx.scene.control.ButtonType.YES;


public class PassengerController implements Initializable, ProxyOperations {
    @FXML
    private RadioButton rbByFirstName;
    @FXML
    private RadioButton rbByLastName;
    @FXML
    private RadioButton rbByPassport;
    private ToggleGroup group = new ToggleGroup();

    @FXML
    private TextField txtSearch;
    @FXML
    public TableView tablePassengers;
    @FXML
    private Label labelCount;
    @FXML
    private TableColumn<Flight, String> columnFlightNumber;
    @FXML
    private TableColumn<Passenger, String> columnFirstName;
    @FXML
    private TableColumn<Passenger, String> columnLastName;
    @FXML
    private TableColumn<Passenger, String> columnNationality;
    @FXML
    private TableColumn<Passenger, String> columnPassport;
    @FXML
    private TableColumn<Passenger, LocalDate> columnBirthday;
    @FXML
    private TableColumn<Passenger, Gender> columnGender;
    @FXML
    private TableColumn<Passenger, ClassType> columnClassType;

    private ObservableList<Passenger> passengers = FXCollections.observableArrayList();
    private PassengerService passengerService = new PassengerService();

    private Stage mainStage;
    private URL location;
    private ResourceBundle resources;

    private Parent fxmlEdit;
    private FXMLLoader fxmlLoader = new FXMLLoader();
    private EditPassengerController editPassengerController;
    private Stage editDialogStage;
    private ProxyOperations proxyController;

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.location = location;
        this.resources = resources;

        initSearchPanel();
        initTable();
        initListner();
        initEditDialog();
        updateCountLable();

        proxyController = (ProxyOperations) AccessHandler.newInstance(this);
    }

    private void initSearchPanel() {
        rbByFirstName.setToggleGroup(group);
        rbByFirstName.setSelected(true);
        rbByLastName.setToggleGroup(group);
        rbByPassport.setToggleGroup(group);
    }

    private void initEditDialog() {
        try {
            URL resource = getClass().getResource("/fxml/editPassenger.fxml");
//        System.out.println("resource: " + resource);
            fxmlLoader.setLocation(resource);
            fxmlLoader.setResources(ResourceBundle.getBundle(PATH_BUNDLES_LOCALE, LOCALE_VALUE));

            fxmlEdit = fxmlLoader.load();
            editPassengerController = fxmlLoader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void initTable() {
        columnFlightNumber.setCellValueFactory(new PropertyValueFactory<Flight, String>("flight"));
        columnFirstName.setCellValueFactory(new PropertyValueFactory<Passenger, String>("firstName"));
        columnLastName.setCellValueFactory(new PropertyValueFactory<Passenger, String>("lastName"));
        columnNationality.setCellValueFactory(new PropertyValueFactory<Passenger, String>("nationality"));
        columnPassport.setCellValueFactory(new PropertyValueFactory<Passenger, String>("passport"));
        columnBirthday.setCellValueFactory(new PropertyValueFactory<Passenger, LocalDate>("birthday"));
        columnGender.setCellValueFactory(new PropertyValueFactory<Passenger, Gender>("gender"));
        columnClassType.setCellValueFactory(new PropertyValueFactory<Passenger, ClassType>("classType"));

        passengers.addAll(passengerService.getAll());

        tablePassengers.setItems(passengers);
    }

    private void initListner() {
        passengers.addListener(new ListChangeListener<Passenger>() {
            @Override
            public void onChanged(Change<? extends Passenger> c) {
                updateCountLable();
            }
        });

        tablePassengers.setOnMouseClicked((MouseEvent event) -> {
            if (event.getClickCount() == 2) {
                editRow();
            }
        });
        tablePassengers.setOnKeyPressed(event -> {
            if (event.getCode().equals(KeyCode.ENTER)) {
                editRow();
            }
            if (event.getCode().equals(KeyCode.DELETE)) {
                deleteRow();
            }
        });

    }

    private void updateCountLable() {
        labelCount.setText(FXUtil.updateCountLable(passengers));
    }

    private void deleteRow() {
        proxyController.delete();
    }

    private void editRow() {
        proxyController.edit();
    }

    private void editPassenger() {
        Passenger selectedPassenger = (Passenger) tablePassengers.getSelectionModel().getSelectedItem();
        int selectedIndex = tablePassengers.getSelectionModel().getSelectedIndex();
        editPassengerController.setPassenger(selectedPassenger);
        showDialog();
        Passenger passengerEdit = editPassengerController.getPassenger();

        passengerService.update(passengerEdit);
        passengers.set(selectedIndex, passengerEdit);
    }

    private void showDialog() {
        editDialogStage = FXUtil.showDialog(fxmlEdit, editDialogStage);
        fxmlLoader.setResources(ResourceBundle.getBundle(PATH_BUNDLES_LOCALE, LOCALE_VALUE));
    }

    public void createNew() {
        createPassenger();
    }

    private void createPassenger() {
        editPassengerController.setPassenger(new Passenger());
        showDialog();
        Passenger newPassenger = editPassengerController.getPassenger();
        if (newPassenger.hashCode() != 0) {
            passengerService.add(newPassenger);
            updateTable();
        }
    }

    public void updateTable() {
        passengers.clear();
        passengers.addAll(passengerService.getAll());
    }

    @Override
    @MyPermission(value = {PermissionAction.STAFF, PermissionAction.ADMIN})
    public void add() {
        createNew();
    }

    @Override
    @MyPermission(value = {PermissionAction.STAFF, PermissionAction.ADMIN})
    public void edit() {
        editPassenger();
    }

    @Override
    @MyPermission(PermissionAction.ADMIN)
    public void delete() {
        boolean yes = DialogManager.showInfoDialog(resources.getString("dm.info"), resources.getString("main.deleteRow"),
                new Alert(Alert.AlertType.INFORMATION, "", YES, NO, CANCEL));
        if (yes) {
            passengerService.delete((Passenger) tablePassengers.getSelectionModel().getSelectedItem());
            passengers.remove(tablePassengers.getSelectionModel().getSelectedIndex());
        }
    }

    @FXML
    public void search() {
        String text = txtSearch.getText();
        List<Passenger> list = null;
        if (rbByFirstName.isSelected()) {
            list = passengerService.searchByFirstName(text);
        }
        if (rbByLastName.isSelected()) {
            list = passengerService.searchByLastName(text);
        }
        if (rbByPassport.isSelected()) {
            list = passengerService.searchByPassport(text);
        }
        passengers.clear();
        passengers.addAll(list);
    }
}
