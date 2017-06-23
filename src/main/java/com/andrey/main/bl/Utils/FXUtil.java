package com.andrey.main.bl.Utils;


import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class FXUtil {

    public static Stage showDialog(Parent fxmlEdit, Stage editDialogStage) {
        if (editDialogStage == null) {
            editDialogStage = new Stage();
//            editDialogStage.setTitle(resourceBundle.getString("edit"));
            editDialogStage.setResizable(false);
            editDialogStage.setScene(new Scene(fxmlEdit));
            editDialogStage.initModality(Modality.WINDOW_MODAL);
            editDialogStage.initOwner(null);
        }
        editDialogStage.showAndWait();

        return editDialogStage;
    }

    public static void actionClose(ActionEvent actionEvent) {
        Node source = (Node) actionEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        stage.hide();
    }

    public static String updateCountLable(ObservableList list) {
        return "Count of row: " + list.size();
    }

}
