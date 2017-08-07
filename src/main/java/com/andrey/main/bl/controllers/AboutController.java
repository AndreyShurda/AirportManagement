package com.andrey.main.bl.controllers;

import com.sun.deploy.uitoolkit.impl.fx.HostServicesFactory;
import com.sun.javafx.application.HostServicesDelegate;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ResourceBundle;

public class AboutController implements Initializable {
    private Stage mainStage;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

    public void actionClose() {
        mainStage.close();
    }

    @FXML
    private void goToFacebook() {
        openURL("https://www.facebook.com/andrey.shurda");
    }

    @FXML
    private void goToLinkedIn() {
        openURL("https://www.linkedin.com/in/andrii-shurda-a45080143/");
    }

    @FXML
    private void goToGitHub() {
        openURL("https://github.com/AndreyShurda");
    }

    @FXML
    private void goToEmail() {
        openURL("adriano225@mail.ru");
    }

    private void openURL(String str) {
        try {
            Desktop.getDesktop().browse(new URI(str));
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
