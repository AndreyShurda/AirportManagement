package com.andrey.main.bl.controllers;


import com.andrey.main.bl.Utils.AutoCompleteComboBoxListener;
import com.andrey.main.bl.Utils.DialogManager;
import com.andrey.main.bl.Utils.FXUtil;
import com.andrey.main.bl.services.FlightService;
import com.andrey.main.dl.data.ClassType;
import com.andrey.main.dl.models.Flight;
import com.andrey.main.dl.models.Ticket;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class EditTicketController implements Initializable {
    @FXML
    private ComboBox<Flight> txtIdFlight;
    @FXML
    private TextField txtPrice;
    @FXML
    private ComboBox cbClassType;

    private Ticket ticket;
    private URL location;
    private ResourceBundle resources;

    private FlightService flightService = new FlightService();

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        System.out.println(ticket);
        initFlightNumber();
        this.ticket = ticket;

        if (ticket.getIdTicket() == 0) {
            txtIdFlight.getSelectionModel().select(null);
            txtPrice.clear();
            cbClassType.getSelectionModel().select(null);
        } else {
            txtIdFlight.setValue(ticket.getFlight());
            txtPrice.setText(String.valueOf(ticket.getPrice()));
            cbClassType.getSelectionModel().select(ticket.getClassType().ordinal());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.location = location;
        this.resources = resources;

        initFlightNumber();
        new AutoCompleteComboBoxListener(txtIdFlight);
        cbClassType.setItems(FXCollections.observableArrayList(ClassType.values()));

//        txtPrice.textProperty().addListener(new ChangeListener<String>() {
//            @Override
//            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
////                if (!newValue.matches("\\d*")) {
////                    txtPrice.setText(newValue.replaceAll("[^\\d]", ""));
////                }
//                if (!newValue.matches("[0-9]*\\\\.?[0-9]+")) {
////                    System.out.println("asd");
////                    txtPrice.setText(newValue.replaceAll("[^\\d]", ""));
//                    txtPrice.setText(newValue.substring(0, newValue.length() - 1));
//                }
//            }
//        });
    }

    private void initFlightNumber() {
        txtIdFlight.setItems(FXCollections.observableArrayList(flightService.getAll()));
    }

    @FXML
    public void save(ActionEvent actionEvent) {
        if (!isValidTicket()) {
            return;
        }
        Flight flight = txtIdFlight.getItems().get(txtIdFlight.getSelectionModel().getSelectedIndex());
        Double price = Double.valueOf(txtPrice.getText());
        ClassType classType = ClassType.valueOf(String.valueOf(cbClassType.getSelectionModel().getSelectedItem()));

        if (getTicket() != null) {
            ticket.setIdTicket(getTicket().getIdTicket());
        }

        ticket.setPrice(price);
        ticket.setClassType(classType);

        ticket.setFlight(flight);

        actionClose(actionEvent);
    }

    private boolean isValidTicket() {
        if (txtIdFlight.getSelectionModel().getSelectedItem() == null || txtPrice.getText().trim().length() == 0 ||
                cbClassType.getSelectionModel().getSelectedItem() == null
                ) {
            DialogManager.showErrorDialog(resources.getString("dm.error"), resources.getString("main.validData"));
            return false;
        }
        return true;
    }

    @FXML
    private void actionClose(ActionEvent actionEvent) {
        FXUtil.actionClose(actionEvent);
    }
}
