package com.andrey.main.ui;

import com.andrey.main.bl.controllers.MainController;
import com.andrey.main.bl.controllers.PassengerController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class FXMainFlights extends Application {

    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        URL resource = getClass().getResource("/fxml/main.fxml");
//        URL resource = getClass().getResource("../ui/fxml/editFlying.fxml");
//        System.out.println(resource);
        fxmlLoader.setLocation(resource);
        fxmlLoader.setResources(ResourceBundle.getBundle("bundles.Locale", new Locale("ru")));

        Parent fxmlMain = fxmlLoader.load();

        MainController mainController = fxmlLoader.getController();
        mainController.setMainStage(primaryStage);

//        scene.getStylesheets().add(0, "test.styles/my.css");
//        primaryStage.setTitle(fxmlLoader.getResources().getString("address_book"));



        primaryStage.setTitle("flights list");
//        primaryStage.initModality(Modality.WINDOW_MODAL);
//        primaryStage.setScene(new Scene(fxmlMain, 800, 500));
        Scene scene = new Scene(fxmlMain);

        primaryStage.setScene(scene);
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception {
        System.exit(0);
    }
}
