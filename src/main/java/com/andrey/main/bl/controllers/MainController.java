package com.andrey.main.bl.controllers;

import com.andrey.main.bl.Utils.DialogManager;
import com.andrey.main.bl.access.AccessHandler;
import com.andrey.main.bl.access.MyPermission;
import com.andrey.main.bl.access.PermissionUtils;
import com.andrey.main.bl.operations.ProxyOperations;
import com.andrey.main.ui.FXMain;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static com.andrey.main.bl.access.PermissionAction.*;
import static com.andrey.main.dl.dao.utils.ApplicationProperties.*;
import static com.andrey.main.dl.dao.utils.InitialData.*;



public class MainController implements Initializable {

    @FXML
    private Menu menuEdit;

    @FXML
    private CheckMenuItem checkItemFlights;
    @FXML
    private CheckMenuItem checkItemPassengers;
    @FXML
    private CheckMenuItem checkItemTickets;

    @FXML
    private Label lbUser;
    @FXML
    private Label lbRole;

    @FXML
    private TabPane tabPane;
    @FXML
    private Tab tabFlights;
    @FXML
    private Tab tabPassengers;
    @FXML
    private Tab tabTickets;


    private Stage mainStage;
    private ResourceBundle resourceBundle;
    private URL location;

    @FXML
    private FlightController flightController;
    @FXML
    private PassengerController passengerController;
    @FXML
    private TicketController ticketController;


    private ProxyOperations proxyFlightController;
    private ProxyOperations proxyPassengerController;
    private ProxyOperations proxyTicketController;


    public void setFlightController(FlightController flightController) {
        this.flightController = flightController;
    }

    public void setPassengerController(PassengerController passengerController) {
        this.passengerController = passengerController;
    }

    public void setTicketController(TicketController ticketController) {
        this.ticketController = ticketController;
    }


    public void setMainStage(Stage mainStage) {
        this.mainStage = mainStage;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        this.resourceBundle = resources;
        this.location = location;

        initListner();
        initControllers();

        proxyFlightController = (ProxyOperations) AccessHandler.newInstance(flightController);
        proxyPassengerController = (ProxyOperations) AccessHandler.newInstance(passengerController);
        proxyTicketController = (ProxyOperations) AccessHandler.newInstance(ticketController);

    }

    public void changeUser() {
        lbUser.setText(CURRENT_USER.getName());
        String roles = CURRENT_USER.getPermissionAction().toString();
//        lbRole.setText(roles.substring(1, roles.length() - 1));
        lbRole.setText(roles);
        accessToMenu();
    }

    @MyPermission(value = {STAFF, ADMIN})
    public void accessToMenu() {
        if (!PermissionUtils.processPermission(this.getClass(), "accessToMenu")) {
//            checkItemFlights.setDisable(true);
            checkItemPassengers.setDisable(true);
            menuEdit.setDisable(true);
        } else {
//            checkItemFlights.setDisable(false);
            checkItemPassengers.setDisable(false);
            menuEdit.setDisable(false);
        }
    }

    private void initControllers() {

        try {
            FXMLLoader loader = getLoader("/fxml/flights.fxml");
            tabFlights = getTab("flights", loader);
            setFlightController(loader.getController());


            loader = getLoader("/fxml/passengers.fxml");
            tabPassengers = getTab("passengers", loader);
            setPassengerController(loader.getController());

            loader = getLoader("/fxml/tickets.fxml");
            tabTickets = getTab("tickets", loader);
            setTicketController(loader.getController());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }


    private void initListner() {

    }


    private FXMLLoader getLoader(String pathToFXML) {
        FXMLLoader loader = new FXMLLoader();
        URL resource = getClass().getResource(pathToFXML);
        loader.setLocation(resource);
        return loader;
    }

    private Tab getTab(String tabName, FXMLLoader loader) throws IOException {
        loader.setResources(ResourceBundle.getBundle(PATH_BUNDLES_LOCALE, LOCALE_VALUE));

        AnchorPane anchorPane = (AnchorPane) loader.load();
        Tab tab = new Tab();

        tab.setText(loader.getResources().getString(tabName));
        tab.setContent(anchorPane);
        return tab;
    }

    @FXML
    public void add() {
        doOperation(tabFlights, (s) -> s.add(), proxyFlightController);
        doOperation(tabPassengers, (s) -> s.add(), proxyPassengerController);
        doOperation(tabTickets, (s) -> s.add(), proxyTicketController);
    }

    @FXML
    public void edit() {
        doOperation(tabFlights, flightController.tableFlight, (s) -> s.edit(), proxyFlightController);
        doOperation(tabPassengers, passengerController.tablePassengers, (s) -> s.edit(), proxyPassengerController);
        doOperation(tabTickets, ticketController.tableTickets, (s) -> s.edit(), proxyTicketController);
    }

    @FXML
    public void delete() {
        doOperation(tabFlights, flightController.tableFlight, (s) -> s.delete(), proxyFlightController);
        doOperation(tabPassengers, passengerController.tablePassengers, (s) -> s.delete(), proxyPassengerController);
        passengerController.updateTable();
        doOperation(tabTickets, ticketController.tableTickets, (s) -> s.delete(), proxyTicketController);
        ticketController.updateTable();
    }

    @FXML
    private void setEnglishLocale() {
        setLanguage("en");
    }

    @FXML
    private void setRussianLocale() {
        setLanguage("ru");
    }

    private void setLanguage(String language) {
        writeProperty("language", language);
        savePropertiesToFile();
        DialogManager.showInfoDialog(resourceBundle.getString("dm.info"), resourceBundle.getString("main.language.change"));
    }

    @FXML
    public void closeApp() {
        System.exit(0);
    }

    @FXML
    private void login() {
        mainStage.close();
        FXMain.primaryStage.show();
    }

    @FunctionalInterface
    public interface Command {

        void execute(ProxyOperations ProxyOperations);
    }
    private void doOperation(Tab tab, Command command, ProxyOperations ProxyOperations) {
        if (tab.isSelected()) {
            command.execute(ProxyOperations);
        }
    }

    private void doOperation(Tab tab, TableView tableView, Command command, ProxyOperations ProxyOperations) {
        if (tableView.getSelectionModel().getSelectedIndex() != -1) {
            doOperation(tab, command, ProxyOperations);
        } else if (tab.isSelected()) {
            DialogManager.showInfoDialog(resourceBundle.getString("dm.info"), resourceBundle.getString("main.not_select_row"));
        }
    }

    private void showTab(TabPane tabPane, Tab tab, CheckMenuItem checkMenuItem) {
        if (checkMenuItem.isSelected()) {
            if (!tabPane.getTabs().contains(tab)) {
                tabPane.getTabs().add(tab);
            }

            tabPane.getSelectionModel().select(tab);
        } else {
            if (tabPane.getTabs().contains(tab)) {
                tabPane.getTabs().remove(tab);
            }
        }
    }

    @FXML
    private void showFlights() {
        showTab(tabPane, tabFlights, checkItemFlights);
    }

    @FXML
    private void showPassengers() {
        showTab(tabPane, tabPassengers, checkItemPassengers);
    }

    @FXML
    private void showTickets() {
        showTab(tabPane, tabTickets, checkItemTickets);
    }

    @FXML
    private void aboutApp() {
        initCreateForm();
    }

    public void initCreateForm() {
        Stage primaryStage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader();
        URL resource = getClass().getResource("/fxml/about.fxml");
        fxmlLoader.setLocation(resource);
        fxmlLoader.setResources(ResourceBundle.getBundle(PATH_BUNDLES_LOCALE, LOCALE_VALUE));

        Parent fxmlMain = null;
        try {
            fxmlMain = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        AboutController mainController = fxmlLoader.getController();
        mainController.setMainStage(primaryStage);

        primaryStage.setTitle(fxmlLoader.getResources().getString("about"));
        Scene scene = new Scene(fxmlMain);

        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();

        primaryStage.focusedProperty().addListener((ov, t, t1) -> {
            primaryStage.close();
        });

    }
}
