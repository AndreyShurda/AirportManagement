package com.andrey.main.ui;

import com.andrey.main.bl.controllers.AuthorizationController;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.andrey.main.dl.dao.utils.InitialData.LOCALE_VALUE;
import static com.andrey.main.dl.dao.utils.InitialData.PATH_BUNDLES_LOCALE;

public class FXMain extends Application {
    public static Stage primaryStage;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        this.primaryStage = primaryStage;

        loadLogin();
    }

    private void loadLogin() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader();
        URL resource = getClass().getResource("/fxml/authorization.fxml");
        fxmlLoader.setLocation(resource);
        fxmlLoader.setResources(ResourceBundle.getBundle(PATH_BUNDLES_LOCALE, LOCALE_VALUE));
        Parent fxmlMain = fxmlLoader.load();

        AuthorizationController mainController = fxmlLoader.getController();
        mainController.setMainStage(primaryStage);

        primaryStage.setTitle(fxmlLoader.getResources().getString("authorization"));
        Scene scene = new Scene(fxmlMain);
//        scene.getStylesheets().add(getClass().getResource("/css/menu.css").toExternalForm());

        primaryStage.getIcons().add(new Image("/images/terminal.png"));
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setAlwaysOnTop(true);
        primaryStage.show();

        scene.setOnKeyPressed(event -> {
            if (event.isAltDown() && event.isControlDown() && event.isShiftDown()) {
                mainController.initCreateForm();
                primaryStage.hide();
            }
        });
    }

    @Override
    public void stop() throws Exception {
        System.exit(0);
    }
}