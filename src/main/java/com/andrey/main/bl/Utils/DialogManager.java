package com.andrey.main.bl.Utils;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

import static javafx.scene.control.Alert.AlertType.ERROR;
import static javafx.scene.control.Alert.AlertType.INFORMATION;
import static javafx.scene.control.Alert.AlertType.WARNING;
import static javafx.scene.control.ButtonType.*;

public class DialogManager {

    public static void showInfoDialog(String title, String text) {
        showMsg(title, text, new Alert(INFORMATION));
    }

    public static boolean showInfoDialog(String title, String text, Alert alert) {
        if (showMsgResult(title, text, alert) == 4) {//Yes
            return true;
        } else {
            return false;
        }
    }

    public static void showErrorDialog(String title, String text) {
        showMsg(title, text, new Alert(ERROR));
    }

    public static void showWarningDialog(String title, String text) {
        showMsg(title, text, new Alert(WARNING));
    }


    private static void showMsg(String title, String text, Alert alert) {
//        Alert alert = new Alert(alertType,"", YES, NO, CANCEL);
        alert.setTitle(title);
        alert.setContentText(text);
        alert.setHeaderText("");
        alert.showAndWait();
//        return alert.getResult().getButtonData().ordinal();
    }


    private static int showMsgResult(String title, String text, Alert alert) {
        showMsg(title, text, alert);
        return alert.getResult().getButtonData().ordinal();
    }
}
