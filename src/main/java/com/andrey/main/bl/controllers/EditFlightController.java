package com.andrey.main.bl.controllers;

import com.andrey.main.bl.Utils.FXUtil;
import com.andrey.main.dl.data.FlightStatus;
import com.andrey.main.dl.models.Flight;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import jfxtras.scene.control.LocalDateTimeTextField;

import java.net.URL;
import java.time.LocalDateTime;
import java.util.ResourceBundle;

public class EditFlightController implements Initializable {

    @FXML
    private TextField txtNumFlight;
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


    private Flight flight;
    private ResourceBundle resourceBundle;
//    private FlightController flightController = FlightController.getInstance();

//    private List<Airport> airportList = ParserAirPort.convertFileToAirport("D:\\airport\\airports.csv");


    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        System.out.println("setFlight:" + flight);
//        if (flight.getId() == 0) {
//            System.out.println("setFlight null");
//        }else {
//            System.out.println("setFlight not null");
//        }
        this.flight = flight;
        if (flight.getId() == 0) {
            txtNumFlight.clear();
            txtDate.setLocalDateTime(LocalDateTime.now());
//            txtDate.setPromptText("Date");
            txtCity.clear();
            cbTerminal.getSelectionModel().select(null);
            cbStatus.getSelectionModel().select(null);
            txtGate.clear();
            return;
        }

        System.out.println("sets fields");
//        this.flight = flight;

        txtNumFlight.setText(flight.getNumber());
        txtDate.setLocalDateTime(flight.getDate());
        txtCity.setText(flight.getCity());
//        txtTo.setText(flight.getTo());
        cbTerminal.setValue(flight.getTerminal());
        cbStatus.getSelectionModel().select(flight.getStatus().ordinal());
        txtGate.setText(flight.getGate());


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.resourceBundle = resources;
        initTerminals();

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

    private void initTerminals() {
        ObservableList<Character> alphabet = FXCollections.observableArrayList();

        for (int i = 'A'; i < 'Z'; i++) {
            alphabet.add((char) i);
        }

        cbTerminal.setItems(alphabet);
    }


    public void saveFlight(ActionEvent actionEvent) {
        if (!isValidFlight()) {
            return;
        }

        String number = txtNumFlight.getText();
        LocalDateTime date = txtDate.getLocalDateTime();
        String city = txtCity.getText();
//        String to = txtTo.getText();
        char terminal = (char) cbTerminal.getSelectionModel().getSelectedItem();
        FlightStatus status = FlightStatus.valueOf(String.valueOf(cbStatus.getSelectionModel().getSelectedItem()));
        String gate = txtGate.getText();
//        System.out.println("1:"+cbTerminal.getSelectionModel().getSelectedItem());
//        System.out.println(number);
//        System.out.println(date);
//        System.out.println(city);
////        System.out.println(to);
//        System.out.println(terminal);
//        System.out.println(status);
//        System.out.println(gate);

        Flight flightEdit;
        if (this.flight != null) {
//        flight.setNumber(number);
//        flight.setDate(date);
//        flight.setCity(city);
//        flight.setTerminal(terminal);
//        flight.setStatus(status);
//        flight.setGate(gate);
            flightEdit = new Flight(getFlight().getId(), number, date, city, terminal, status, gate);

//            flightController.update(flightEdit);
        } else {
            System.out.println("is null in save");
            flightEdit = new Flight(number, date, city, terminal, status, gate);
//            flightController.add(newFlight);
        }
//        setFlight(flightEdit);
        this.flight = flightEdit;
        System.out.println("saveFlight" + getFlight());
//        System.out.println(flightEdit);
//
//        if (flight == null || !this.flight.equals(flightEdit)) {
//            setFlight(flightEdit);
////            System.out.println("add " + this.flight.equals(flightEdit));
//
//            flightController.add(flight);
//        }
        actionClose(actionEvent);

    }

    private boolean isValidFlight() {
        if (txtNumFlight.getText().trim().length() == 0 || txtDate.getLocalDateTime() == null ||
                txtCity.getText().trim().length() == 0 ||
                cbTerminal.getSelectionModel().getSelectedItem() == null ||
                cbStatus.getSelectionModel().getSelectedItem() == null ||
                txtGate.getText().trim().length() == 0
                ) {
            System.out.println("not valid flight");
            return false;
        }
        return true;
    }

    public void actionClose(ActionEvent actionEvent) {
        FXUtil.actionClose(actionEvent);
    }
}
