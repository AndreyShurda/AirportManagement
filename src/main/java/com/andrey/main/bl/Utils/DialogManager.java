package com.andrey.main.bl.Utils;

import javafx.scene.control.Alert;

import static javafx.scene.control.Alert.AlertType.ERROR;
import static javafx.scene.control.Alert.AlertType.INFORMATION;

public class DialogManager {

    public  static void showInfoDialog(String title, String text){
        showMsg(title, text, INFORMATION);
    }

    public  static void showErrorDialog(String title, String text){
        showMsg(title, text, ERROR);
    }

    private static void showMsg(String title, String text, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setContentText(text);
        alert.setHeaderText("");
        alert.showAndWait();
    }
}
