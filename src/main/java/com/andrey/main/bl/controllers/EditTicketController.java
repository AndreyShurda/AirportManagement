package com.andrey.main.bl.controllers;


import com.andrey.main.bl.Utils.AutoCompleteComboBoxListener;
import com.andrey.main.bl.Utils.FXUtil;
import com.andrey.main.dl.dao.FlightDAO;
import com.andrey.main.dl.data.ClassType;
import com.andrey.main.dl.models.Ticket;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import java.util.stream.Collectors;

public class EditTicketController implements Initializable {
    @FXML
    private ComboBox txtIdFlight;
    @FXML
    private TextField txtPrice;
    @FXML
    private ComboBox cbClassType;

    private Ticket ticket;
    private URL location;
    private ResourceBundle resources;

    private FlightDAO instanceFlight = FlightDAO.getInstance();

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        System.out.println(ticket);
        this.ticket = ticket;

        if (ticket.getIdTicket() == 0) {
            txtIdFlight.getSelectionModel().select(null);
            txtPrice.clear();
            cbClassType.getSelectionModel().select(null);
        } else {
            txtIdFlight.setValue(ticket.getIdFlight());
            txtPrice.setText(String.valueOf(ticket.getPrice()));
            cbClassType.getSelectionModel().select(ticket.getClassType().ordinal());
        }
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.location = location;
        this.resources = resources;

//        TextFields.bindAutoCompletion(txtIdFlight, initFlightNumber());
        txtIdFlight.setItems(FXCollections.observableArrayList(initFlightNumber()));
        new AutoCompleteComboBoxListener(txtIdFlight);
        cbClassType.setItems(FXCollections.observableArrayList(ClassType.values()));
    }

    private List<String> initFlightNumber() {

        return instanceFlight.getAll().stream()
                .map(c -> c.getNumber())
                .collect(Collectors.toList());
    }

    public void save(ActionEvent actionEvent) {
        if (!isValidTicket()) {
            return;
        }

        String idFlight = (String) txtIdFlight.getSelectionModel().getSelectedItem();
        Double price = Double.valueOf(txtPrice.getText());
        ClassType classType = ClassType.valueOf(String.valueOf(cbClassType.getSelectionModel().getSelectedItem()));

        Ticket editTicket;
        if (getTicket() != null) {
            editTicket = new Ticket(getTicket().getIdTicket(), idFlight, price, classType);
        } else {
            editTicket = new Ticket(idFlight, price, classType);
        }
        setTicket(editTicket);

        actionClose(actionEvent);
    }

    private boolean isValidTicket() {
        if (txtIdFlight.getSelectionModel().getSelectedItem() == null || txtPrice.getText().trim().length() == 0 ||
                cbClassType.getSelectionModel().getSelectedItem() == null
                ) {
            System.out.println("not valid ticket");
            return false;
        }
        return true;
    }

    public void actionClose(ActionEvent actionEvent) {
        FXUtil.actionClose(actionEvent);
    }
}
