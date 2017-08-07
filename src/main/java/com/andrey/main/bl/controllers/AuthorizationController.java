package com.andrey.main.bl.controllers;


import com.andrey.main.bl.Utils.CryptoUtils;
import com.andrey.main.bl.services.UserService;
import com.andrey.main.dl.dao.utils.InitialData;
import com.andrey.main.dl.models.User;
import com.andrey.main.ui.FXMain;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import static com.andrey.main.dl.dao.utils.InitialData.LOCALE_VALUE;
import static com.andrey.main.dl.dao.utils.InitialData.PATH_BUNDLES_LOCALE;

public class AuthorizationController implements Initializable {
    @FXML
    private TextField txtUser;
    @FXML
    private TextField txtPassword;
    @FXML
    private Label lbInfo;
    private URL location;
    private ResourceBundle resources;

    private UserService userService = new UserService();

    private Stage mainStage;
    private Stage primaryStage;

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.location = location;
        this.resources = resources;
    }

    @FXML
    private void login() {
        if (!isValidAuthorization()) {
            return;
        }

        String userName = txtUser.getText();
        String password = txtPassword.getText();
        if (isValidUser(userName, password)) {

            try {
                actionClose();
                primaryStage = initRootLayout();
                txtUser.clear();
                txtPassword.clear();
                lbInfo.setVisible(false);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private boolean isValidUser(String userName, String password) {
        List<User> users = userService.getAll();
        if (users.size() == 0){
            lbInfo.setText(resources.getString("authorization.wrongUser"));
            lbInfo.setVisible(true);
        }else {
            for (User user : users) {
                if (user.getName().equals(userName) && CryptoUtils.decode(user.getPassword()).equals(password)) {
                    InitialData.CURRENT_USER = user;
                    return true;
                } else {
                    lbInfo.setText(resources.getString("authorization.wrongUser"));
                    lbInfo.setVisible(true);
                }
            }
        }
        return false;
    }

    public void initCreateForm() {
        Stage primaryStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader();
        URL resource = getClass().getResource("/fxml/createUser.fxml");
        fxmlLoader.setLocation(resource);
        fxmlLoader.setResources(ResourceBundle.getBundle(PATH_BUNDLES_LOCALE, LOCALE_VALUE));

        Parent fxmlMain = null;
        try {
            fxmlMain = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        CreateUserController mainController = fxmlLoader.getController();
        mainController.setMainStage(primaryStage);

        primaryStage.setTitle(fxmlLoader.getResources().getString("authorization.create"));
        Scene scene = new Scene(fxmlMain);

        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

        primaryStage.setOnCloseRequest(we -> FXMain.primaryStage.show());
    }

    private Stage initRootLayout() throws IOException {
        Stage primaryStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader();
        URL resource = getClass().getResource("/fxml/main.fxml");

        fxmlLoader.setLocation(resource);
        fxmlLoader.setResources(ResourceBundle.getBundle(PATH_BUNDLES_LOCALE, LOCALE_VALUE));

        VBox rootLayout = (VBox) fxmlLoader.load();
        MainController mainController = fxmlLoader.getController();

        mainController.changeUser();
        mainController.setMainStage(primaryStage);


        primaryStage.getIcons().add(new Image("/images/terminal.png"));
        primaryStage.setTitle(fxmlLoader.getResources().getString("airport_management"));

        Scene scene = new Scene(rootLayout);
        scene.getStylesheets().add(getClass().getResource("/css/AppStyle.css").toExternalForm());

        primaryStage.setScene(scene);
        primaryStage.setMinWidth(880);
        primaryStage.setMinHeight(530);
        primaryStage.show();

        return primaryStage;
    }

    private boolean isValidAuthorization() {
        return isValidField(txtUser, resources.getString("authorization.fillLogin")) &&
                isValidField(txtPassword, resources.getString("authorization.fillPassword"));
    }

    private boolean isValidField(TextField textField, String text) {
        if (textField.getText().trim().length() == 0) {
            lbInfo.setText(text);
            lbInfo.setVisible(true);
            return false;
        }
        return true;
    }


    @FXML
    private void actionClose() {
        mainStage.close();
    }

    @FXML
    private void register() {
        initCreateForm();
        FXMain.primaryStage.hide();
    }
}
