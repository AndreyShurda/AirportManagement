package com.andrey.main.bl.controllers;


import com.andrey.main.bl.Utils.DialogManager;
import com.andrey.main.bl.Utils.CryptoUtils;
import com.andrey.main.bl.access.PermissionAction;
import com.andrey.main.bl.services.UserEntityService;
import com.andrey.main.dl.models.UserEntity;
import com.andrey.main.ui.FXMain;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class CreateUserController implements Initializable {
    @FXML
    private TextField txtUser;
    @FXML
    private TextField txtPassword;
    @FXML
    private ComboBox<PermissionAction> cbRole;

    private URL location;
    private ResourceBundle resources;
    private Stage mainStage;

    private UserEntityService userEntityService = new UserEntityService();

    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.location = location;
        this.resources = resources;

        cbRole.setItems(FXCollections.observableArrayList(PermissionAction.values()));

    }

    @FXML
    private void save() {
        if (!isValidUser()) {
            return;
        } else if (!isValidUserName(txtUser.getText())) {
            DialogManager.showErrorDialog(resources.getString("dm.info"), resources.getString("createUser.valid.user.name"));
            return;
        }else if (!isValidUserPassword(txtPassword.getText())){
            DialogManager.showErrorDialog(resources.getString("dm.info"), resources.getString("createUser.valid.user.password"));
            return;
        }

        String userName = txtUser.getText();
        String password = txtPassword.getText();
        PermissionAction role = cbRole.getSelectionModel().getSelectedItem();
        UserEntity user = new UserEntity();
        user.setName(userName);
        user.setPassword(CryptoUtils.encode(password));
        user.setPermissionAction(role);
        System.out.println(user);
        if (!userEntityService.getAll().contains(user)) {
            System.out.println("new user");
            userEntityService.add(user);
            actionClose();
        } else {
            System.out.println("This user already exists");
        }
    }

    private boolean isValidUser() {
        if (txtUser.getText().trim().length() == 0 ||
                txtUser.getText().trim().length() == 0 ||
                cbRole.getSelectionModel().getSelectedItem() == null) {
            DialogManager.showErrorDialog(resources.getString("dm.error"), resources.getString("createUser.validation"));
            return false;
        }
        return true;
    }

    private boolean isValidUserName(String userName) {
        return userName.matches("(^[a-zA-Z]{1})([a-zA-Z_]*)([a-zA-Z]$)");
    }

    private boolean isValidUserPassword(String password) {
        return password.matches("([a-zA-Z0-9])*");
    }

    @FXML
    private void actionClose() {
        FXMain.primaryStage.show();
        mainStage.close();
    }


}
