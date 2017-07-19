package com.andrey.main.bl.controllers;

import com.andrey.main.bl.Utils.DialogManager;
import com.andrey.main.bl.Utils.FXUtil;
import com.andrey.main.bl.services.DestinationService;
import com.andrey.main.bl.services.FlightService;
import com.andrey.main.dl.data.ClassType;
import com.andrey.main.dl.data.Gender;
import com.andrey.main.dl.models.Flight;
import com.andrey.main.dl.models.Passenger;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import org.controlsfx.control.textfield.TextFields;

import java.net.URL;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class EditPassengerController implements Initializable {
    @FXML
    private ComboBox<Flight> txtIdFlight;
    @FXML
    private TextField txtFirstName;
    @FXML
    private TextField txtLastName;
    @FXML
    private TextField txtNationality;
    @FXML
    private TextField txtPassport;
    @FXML
    private DatePicker txtBirthday;
    @FXML
    private ComboBox cbGender;
    @FXML
    private ComboBox cbClassType;
    @FXML

    private Passenger passenger;

    private URL location;
    private ResourceBundle resources;
    private FlightService flightService = new FlightService();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.location = location;
        this.resources = resources;

        initFlightNumber();
        TextFields.bindAutoCompletion(txtNationality, getListOfCountries());
        cbGender.setItems(FXCollections.observableArrayList(Gender.values()));
        cbClassType.setItems(FXCollections.observableArrayList(ClassType.values()));
    }

    private List<String> getListOfCountries() {

        String[] locales = Locale.getISOCountries();

        List<String> countries = Arrays.stream(locales)
                .map(l -> new Locale("", l))
                .map(c -> c.getDisplayCountry(Locale.ENGLISH))
                .collect(Collectors.toList());
        return countries;
    }

    private void initFlightNumber() {
        txtIdFlight.setItems(FXCollections.observableArrayList(flightService.getAll()));
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        initFlightNumber();
//        List<Flight> all = flightService.getAll();
//        txtIdFlight.setItems(FXCollections.observableArrayList(all));
        this.passenger = passenger;

        if (passenger.getId() == 0) {
            txtIdFlight.getSelectionModel().select(null);
            txtFirstName.clear();
            txtLastName.clear();
            txtNationality.clear();
            txtPassport.clear();
            txtBirthday.setValue(null);
            cbGender.getSelectionModel().select(null);
            cbClassType.getSelectionModel().select(null);
        } else {
            txtIdFlight.setValue(passenger.getFlight());
            txtFirstName.setText(passenger.getFirstName());
            txtLastName.setText(passenger.getLastName());
            txtNationality.setText(passenger.getNationality());
            txtPassport.setText(passenger.getPassport());
            txtBirthday.setValue(passenger.getBirthday());
            cbGender.getSelectionModel().select(passenger.getGender().ordinal());
            cbClassType.getSelectionModel().select(passenger.getClassType().ordinal());
        }
    }

    @FXML
    public void saveFlight(ActionEvent actionEvent) {
        if (!isValidPassenger()) {
            return;
        }

        Flight flight = txtIdFlight.getItems().get(txtIdFlight.getSelectionModel().getSelectedIndex());
        String firstName = txtFirstName.getText();
        String lastName = txtLastName.getText();
        String nationality = txtNationality.getText();
        String passport = txtPassport.getText();
        LocalDate birthday = txtBirthday.getValue();
        Gender gender = Gender.valueOf(String.valueOf(cbGender.getSelectionModel().getSelectedItem()));
        ClassType classType = ClassType.valueOf(String.valueOf(cbClassType.getSelectionModel().getSelectedItem()));

//        Passenger newPassenger = new Passenger();
        if (getPassenger() != null) {
            passenger.setId(getPassenger().getId());
        }
        passenger.setFirstName(firstName);
        passenger.setLastName(lastName);
        passenger.setNationality(nationality);
        passenger.setPassport(passport);
        passenger.setBirthday(birthday);
        passenger.setGender(gender);
        passenger.setClassType(classType);
        passenger.setFlight(flight);

//        setPassenger(passenger);
        actionClose(actionEvent);
    }

    private boolean isValidPassenger() {
        if (txtIdFlight.getSelectionModel().getSelectedItem() == null || txtFirstName.getText().trim().length() == 0 ||
                txtLastName.getText().trim().length() == 0 || txtNationality.getText().trim().length() == 0 ||
                txtPassport.getText().trim().length() == 0 || txtBirthday.getValue() == null ||
                cbGender.getSelectionModel().getSelectedItem() == null ||
                cbClassType.getSelectionModel().getSelectedItem() == null
                ) {
            DialogManager.showErrorDialog(resources.getString("dm.error"), resources.getString("main.validData"));
            System.out.println("not valid passenger");
            return false;
        }
        return true;
    }

    @FXML
    private void actionClose(ActionEvent actionEvent) {
        FXUtil.actionClose(actionEvent);
    }
}
