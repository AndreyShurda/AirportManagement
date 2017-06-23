package com.andrey.main.ui;

import com.andrey.main.bl.controllers.MainController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.andrey.main.dl.dao.InitialData.*;
import static com.andrey.main.dl.dao.InitialData.LOCALE_VALUE;
import static com.andrey.main.dl.dao.InitialData.PATH_BUNDLES_LOCALE;

public class FXMain extends Application {
    private Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;
        InitRootLayout();
    }

    private void InitRootLayout() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        URL resource = getClass().getResource("/fxml/main.fxml");

        fxmlLoader.setLocation(resource);
        fxmlLoader.setResources(ResourceBundle.getBundle(PATH_BUNDLES_LOCALE, LOCALE_VALUE));

        VBox rootLayout = (VBox) fxmlLoader.load();
        MainController mainController = fxmlLoader.getController();

//        CURRENT_USER = GUEST;
//        CURRENT_USER = USER_ADMIN;

//        mainController.accessToMenu();
        mainController.changeToGuest();
        mainController.setMainStage(primaryStage);


        primaryStage.setTitle(fxmlLoader.getResources().getString("airport_management"));

        Scene scene = new Scene(rootLayout);
        scene.getStylesheets().add(getClass().getResource("/css/AppStyle.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.setMinWidth(880);
        primaryStage.setMinHeight(520);
        primaryStage.show();
    }


}