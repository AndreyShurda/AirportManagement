package com.andrey.main.bl.controllers;

import com.andrey.main.bl.Utils.DialogManager;
import com.andrey.main.bl.Utils.FXUtil;
import com.andrey.main.bl.services.FlightService;
import com.andrey.main.dl.data.FlightStatus;
import com.andrey.main.dl.models.Destination;
import com.andrey.main.dl.models.Flight;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import jfxtras.scene.control.LocalDateTimeTextField;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class EditFlightController implements Initializable {

    @FXML
//    private TextField txtNumFlight;
    private ComboBox<Flight> txtNumFlight;
    @FXML
    private LocalDateTimeTextField txtDate;
    @FXML
    private TextField txtCity;
    //    @FXML
//    private TextField txtTo;
    @FXML
    private ComboBox cbTerminal;
    @FXML
    private ChoiceBox cbStatus;
    @FXML
    private TextField txtGate;

    @FXML
    private Button btnSave;
    @FXML
    private Button btnClose;


    private Destination destination;
    private ResourceBundle resources;

    private FlightService flightService = new FlightService();
//    private FlightController flightController = FlightController.getInstance();

//    private List<Airport> airportList = ParserAirPort.convertFileToAirport("D:\\airport\\airports.csv");


    public Destination getDestination() {
        return destination;
    }

    public void setDestination(Destination destination) {
//        System.out.println("setDestination:" + destination);

        this.destination = destination;
        if (destination.getId() == 0) {
            txtNumFlight.getSelectionModel().select(null);
            txtDate.setLocalDateTime(LocalDateTime.now());
//            txtDate.setPromptText("Date");
            txtCity.clear();
            cbTerminal.getSelectionModel().select(null);
            cbStatus.getSelectionModel().select(null);
            txtGate.clear();
            return;
        }

        System.out.println("sets fields");
//        this.destination = destination;

//        txtNumFlight.setText(destination.getFlight().getNumber());
        txtNumFlight.setValue(destination.getFlight());
        txtDate.setLocalDateTime(destination.getDate());
        txtCity.setText(destination.getCity());
//        txtTo.setText(destination.getTo());
        cbTerminal.setValue(destination.getTerminal());
        cbStatus.getSelectionModel().select(destination.getStatus().ordinal());
        txtGate.setText(destination.getGate());


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.resources = resources;
        initTerminals();
        initFlightNumber();
        cbStatus.setItems(FXCollections.observableArrayList(FlightStatus.values()));


//        Set<String> listCities = airportList.stream()
//                .map(c -> c.getCity())
//                .collect(Collectors.toSet());


//        for (String listCity : listCities) {
//            System.out.println(listCity);
//        }

//        TextFields.bindAutoCompletion(txtCity, listCities);
//        TextFields.bindAutoCompletion(txtTo, listCities);
    }

    private void initFlightNumber() {
        txtNumFlight.setItems(FXCollections.observableArrayList(flightService.getAll()));
    }

    private void initTerminals() {
        ObservableList<Character> alphabet = FXCollections.observableArrayList();

        for (int i = 'A'; i < 'Z'; i++) {
            alphabet.add((char) i);
        }

        cbTerminal.setItems(alphabet);
    }

    @FXML
    public void saveFlight(ActionEvent actionEvent) {
        if (!isValidFlight()) {
            return;
        }

//        String number = txtNumFlight.getText();
        Flight flight = txtNumFlight.getSelectionModel().getSelectedItem();
        LocalDateTime date = txtDate.getLocalDateTime();
        String city = txtCity.getText();
//        String to = txtTo.getText();
        char terminal = (char) cbTerminal.getSelectionModel().getSelectedItem();
        FlightStatus status = FlightStatus.valueOf(String.valueOf(cbStatus.getSelectionModel().getSelectedItem()));
        String gate = txtGate.getText();


//        Flight flightEdit = null;
        if (getDestination() != null) {
            destination.setId(getDestination().getId());
        }
//        destination.setNumber(number);

//        flightService.getById()
//
//        Flight flight = new Flight();
//        flight.setNumber(number);

        destination.setFlight(flight);
        destination.setDate(date);
        destination.setCity(city);
        destination.setTerminal(terminal);
        destination.setStatus(status);
        destination.setGate(gate);

//        this.destination = flightEdit;
        System.out.println("saveFlight" + getDestination());

        actionClose(actionEvent);

    }

    private boolean isValidFlight() {
        if (txtNumFlight.getSelectionModel().getSelectedItem() == null || txtDate.getLocalDateTime() == null ||
                txtCity.getText().trim().length() == 0 ||
                cbTerminal.getSelectionModel().getSelectedItem() == null ||
                cbStatus.getSelectionModel().getSelectedItem() == null ||
                txtGate.getText().trim().length() == 0
                ) {
            DialogManager.showErrorDialog(resources.getString("dm.error"), resources.getString("main.validData"));
            System.out.println("not valid destination");
            return false;
        }
        return true;
    }

    @FXML
    private void actionClose(ActionEvent actionEvent) {
        FXUtil.actionClose(actionEvent);
    }
}
