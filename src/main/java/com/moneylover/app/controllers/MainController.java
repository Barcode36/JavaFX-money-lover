package com.moneylover.app.controllers;

import com.moneylover.app.controllers.Pages.ReportController;
import javafx.beans.property.BooleanProperty;
import javafx.collections.ObservableList;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import com.moneylover.app.controllers.Contracts.LoaderInterface;
import com.moneylover.app.controllers.Pages.TransactionController;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    private BooleanProperty changeScene = new SimpleBooleanProperty(false);

    private VBox mainView;

    private LoaderInterface controller;

    public BooleanProperty getChangeScene() {
        return changeScene;
    }

    public void setChangeScene(boolean changeScene) {
        this.changeScene.setValue(changeScene);
    }

    public VBox getMainView() {
        return this.mainView;
    }

    public LoaderInterface getController() {
        return this.controller;
    }

    @FXML
    public void pressTransaction(Event e) throws IOException {
        Node button = (Node) e.getSource();
        this.controller = new TransactionController();
        boolean result = this.handleSidebarButtonClass(button);

        if (result) {
            this.mainView = this.controller.loadView();
            this.setChangeScene(true);
        }
    }

    @FXML
    public void pressReport(Event e) throws IOException {
        Node button = (Node) e.getSource();
        this.controller = new ReportController();
        boolean result = this.handleSidebarButtonClass(button);

        if (result) {
            this.mainView = this.controller.loadView();
            this.setChangeScene(true);
        }
    }

    @FXML
    public void pressBudget(Event e) {
        Node button = (Node) e.getSource();
        boolean result = this.handleSidebarButtonClass(button);
    }

    private boolean handleSidebarButtonClass(Node button) {
        ObservableList<Node> nodes = button.getParent().getChildrenUnmodifiable();
        boolean newScene = false;

        for (Node node: nodes) {
            ObservableList<String> classes = node.getStyleClass();

            if (node == button) {
                if (!classes.toString().contains("active")) {
                    classes.add("active");
                    newScene = true;
                }
            } else {
                classes.remove("active");
            }
        }

        return newScene;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try {
            this.controller = new TransactionController();
            this.mainView = this.controller.loadView();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
