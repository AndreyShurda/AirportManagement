package com.andrey.main.ui;

import com.andrey.main.bl.controllers.AuthorizationController;
import com.andrey.main.bl.controllers.PassengerController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class FXMainLogin extends Application {

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws IOException {
//        loadLogin(primaryStage);
        Pane root = new Pane();
        Button btn = new Button("new window");
        btn.setOnAction(event -> newWindow("modal window"));

        root.getChildren().add(btn);
        Scene scene = new Scene(root, 300, 300);
        primaryStage.setScene(scene);
        primaryStage.setTitle("First window");
        primaryStage.show();

    }

    private void newWindow(String title){
        Stage window = new Stage();
        window.initModality(Modality.WINDOW_MODAL);

        Pane pane = new Pane();

        Scene scene = new Scene(pane, 300, 300);
        window.setScene(scene);
        window.setTitle(title);
//        window.setAlwaysOnTop(true);
        window.show();
    }

//    private void loadLogin(Stage primaryStage) throws IOException {
//        FXMLLoader fxmlLoader = new FXMLLoader();
//        URL resource = getClass().getResource("/fxml/authorization.fxml");
//        fxmlLoader.setLocation(resource);
//        fxmlLoader.setResources(ResourceBundle.getBundle("bundles.Locale", new Locale("ru")));
//
//        Parent fxmlMain = fxmlLoader.load();
//
//        AuthorizationController mainController = fxmlLoader.getController();
//        mainController.setMainStage(primaryStage);
//
//        primaryStage.setTitle("authorization");
//        Scene scene = new Scene(fxmlMain);
//
//        primaryStage.setScene(scene);
//        primaryStage.setResizable(false);
//        primaryStage.show();
//    }
}
