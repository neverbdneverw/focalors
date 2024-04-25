/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.neverbdneverw.focalors;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author HUAWEI-Pc
 */
public class SettingsController implements Initializable {

    @FXML
    private Button settingReturn;
    @FXML
    private ToggleButton appearanceButton;
    @FXML
    private ToggleButton behaviorButton;
    @FXML
    private ToggleButton accuracyButton;
    @FXML
    private ToggleButton estimateButton;
    @FXML
    private Pane appearancePane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ToggleGroup tg = new ToggleGroup();
        this.appearanceButton.setToggleGroup(tg);
        this.behaviorButton.setToggleGroup(tg);
        this.accuracyButton.setToggleGroup(tg);
        this.estimateButton.setToggleGroup(tg);
        tg.selectToggle(this.appearanceButton);
    }    

    @FXML
    private void handleSettingReturn(ActionEvent event) throws IOException {
        App.setRoot("primary");
    }

    @FXML
    private void handleAppearanceButton(ActionEvent event) {
       App.setCSS("application_dark");
    }

    @FXML
    private void handleBehaviorButton(ActionEvent event) {
    }

    @FXML
    private void handleAccuracyButton(ActionEvent event) {
    }

    @FXML
    private void handleEstimateButton(ActionEvent event) {
    }
}
