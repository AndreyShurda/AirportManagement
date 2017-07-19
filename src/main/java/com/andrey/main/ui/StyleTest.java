package com.andrey.main.ui;

///**
// * Created by admin on 15.07.2017.
// */
//public class StyleTest {
//}


import javafx.application.Application;
        import javafx.beans.value.ChangeListener;
        import javafx.beans.value.ObservableValue;
        import javafx.collections.ObservableList;
        import javafx.scene.Scene;
        import javafx.scene.control.Label;
        import javafx.scene.control.TextField;
        import javafx.scene.layout.GridPane;
        import javafx.stage.Stage;

import java.util.Collections;

class ValidatingTextFieldExample extends Application {

    @Override
    public void start(Stage primaryStage) {
        GridPane root = new GridPane();
        TextField nameTF = new TextField();
        TextField emailTF = new TextField();

        root.add(new Label("Name:"), 0, 0);
        root.add(nameTF, 1, 0);
        root.add(new Label("Email:"), 0, 1);
        root.add(emailTF, 1, 1);

        setUpValidation(nameTF);
        setUpValidation(emailTF);

        Scene scene = new Scene(root, 250, 150);
        scene.getStylesheets().add(getClass().getResource("text-field-red-border.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void setUpValidation(final TextField tf) {
        tf.textProperty().addListener(new ChangeListener<String>() {

            @Override
            public void changed(ObservableValue<? extends String> observable,
                                String oldValue, String newValue) {
                validate(tf);
            }

        });

        validate(tf);
    }

    private void validate(TextField tf) {
        ObservableList<String> styleClass = tf.getStyleClass();
        if (tf.getText().trim().length()==0) {
            if (! styleClass.contains("error")) {
                styleClass.add("error");
            }
        } else {
            // remove all occurrences:
            styleClass.removeAll(Collections.singleton("error"));
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}