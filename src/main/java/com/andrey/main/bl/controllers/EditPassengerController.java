package com.andrey.main.bl.controllers;

import com.andrey.main.bl.Utils.FXUtil;
import com.andrey.main.dl.dao.FlightDAO;
import com.andrey.main.dl.data.ClassType;
import com.andrey.main.dl.data.Gender;
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
    private TextField txtIdFlight;
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
    private FlightDAO flightDAO = FlightDAO.getInstance();

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.location = location;
        this.resources = resources;

        initIdFlightNumber();
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

    private List<String> initFlightNumber() {
        return flightDAO.getAll().stream()
                .map(c -> c.getNumber())
                .collect(Collectors.toList());
    }

    private void initIdFlightNumber() {
        TextFields.bindAutoCompletion(txtIdFlight, initFlightNumber());
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public void setPassenger(Passenger passenger) {
        initIdFlightNumber();
        this.passenger = passenger;

        if (passenger.getId() == 0) {
            txtIdFlight.clear();
            txtFirstName.clear();
            txtLastName.clear();
            txtNationality.clear();
            txtPassport.clear();
            txtBirthday.setValue(null);
            cbGender.getSelectionModel().select(null);
            cbClassType.getSelectionModel().select(null);
        } else {
            txtIdFlight.setText(passenger.getFlightNumber());
            txtFirstName.setText(passenger.getFirstName());
            txtLastName.setText(passenger.getLastName());
            txtNationality.setText(passenger.getNationality());
            txtPassport.setText(passenger.getPassport());
            txtBirthday.setValue(passenger.getBirthday());
            cbGender.getSelectionModel().select(passenger.getGender().ordinal());
            cbClassType.getSelectionModel().select(passenger.getClassType().ordinal());
        }
    }

    public void saveFlight(ActionEvent actionEvent) {
        if (!isValidPassenger()) {
            return;
        }

        String idFlight = txtIdFlight.getText();
        String firstName = txtFirstName.getText();
        String lastName = txtLastName.getText();
        String nationality = txtNationality.getText();
        String passport = txtPassport.getText();
        LocalDate birthday = txtBirthday.getValue();
        Gender gender = Gender.valueOf(String.valueOf(cbGender.getSelectionModel().getSelectedItem()));
        ClassType classType = ClassType.valueOf(String.valueOf(cbClassType.getSelectionModel().getSelectedItem()));

        Passenger editPassenger;
        if (getPassenger() != null) {
            editPassenger = new Passenger(getPassenger().getId(), idFlight, firstName, lastName, nationality, passport, birthday, gender, classType);
        } else {
            editPassenger = new Passenger(idFlight, firstName, lastName, nationality, passport, birthday, gender, classType);
        }
        setPassenger(editPassenger);

        actionClose(actionEvent);
    }

    private boolean isValidPassenger() {
        if (txtIdFlight.getText().trim().length() == 0 || txtFirstName.getText().trim().length() == 0 ||
                txtLastName.getText().trim().length() == 0 || txtNationality.getText().trim().length() == 0 ||
                txtPassport.getText().trim().length() == 0 || txtBirthday.getValue() == null ||
                cbGender.getSelectionModel().getSelectedItem() == null ||
                cbClassType.getSelectionModel().getSelectedItem() == null
                ) {
            System.out.println("not valid passenger");
            return false;
        }
        return true;
    }


    public void actionClose(ActionEvent actionEvent) {
        FXUtil.actionClose(actionEvent);
    }
}
