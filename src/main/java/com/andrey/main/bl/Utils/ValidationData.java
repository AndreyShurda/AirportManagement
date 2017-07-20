package com.andrey.main.bl.Utils;

import com.andrey.main.dl.models.Flight;
import javafx.scene.control.ComboBox;
import org.apache.poi.ss.formula.functions.T;

import java.util.ResourceBundle;

import static com.andrey.main.dl.dao.utils.InitialData.LOCALE_VALUE;
import static com.andrey.main.dl.dao.utils.InitialData.PATH_BUNDLES_LOCALE;


public class ValidationData {

    public static boolean isFlightNumber(String s){
        return s.matches("(^[a-zA-Z]{2}).*\\d");
    }

    public static Flight getFlightComboBox(ComboBox<Flight> txtNumFlight){
        if (txtNumFlight.getSelectionModel().getSelectedIndex() != -1) {
            return txtNumFlight.getItems().get(txtNumFlight.getSelectionModel().getSelectedIndex());
        } else {
            DialogManager.showErrorDialog(ResourceBundle.getBundle(PATH_BUNDLES_LOCALE, LOCALE_VALUE).getString("dm.error"),
                    ResourceBundle.getBundle(PATH_BUNDLES_LOCALE, LOCALE_VALUE).getString("flight.cb.valid"));
            return null;
        }
    }
}
