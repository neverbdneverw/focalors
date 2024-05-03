/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.neverbdneverw.focalors;

import com.neverbdneverw.focalors.Utilities.Utils;
import com.neverbdneverw.focalors.AmplificationProcessors.Processors;
import com.neverbdneverw.focalors.AmplificationProcessors.AmplificationProcessor;
import com.neverbdneverw.focalors.Utilities.Utils.Direction;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author HUAWEI-Pc
 */
public class InputOptionsController extends ProcedureSwitchingPaneController implements Initializable {

    @FXML
    private Button returnToOutputsButton;
    @FXML
    private AnchorPane inputsPane;
    
    private AnchorPane sourceOutputPane;
    private AnchorPane homePagePane;
    @FXML
    private Button toFrequencyResponseButton;
    @FXML
    private ImageView previousImageView;
    @FXML
    private ImageView nextImageView;
    @FXML
    private ComboBox<String> signalVoltageCombo;
    @FXML
    private ComboBox<String> biasingVoltageCombo;
    @FXML
    private ComboBox<String> frequencyCombo;
    @FXML
    private TextField signalInputField;
    @FXML
    private TextField biasingInputField;
    @FXML
    private TextField frequencyInputField;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        previousImageView.setImage(Utils.getImage("back", Color.WHITE));
        nextImageView.setImage(Utils.getImage("next", Color.WHITE));
        
        Utils.buttonAddHoverEffect(returnToOutputsButton);
        Utils.buttonAddHoverEffect(toFrequencyResponseButton);
        
        this.setPaneName("Input Options");
        
        signalInputField.setPromptText("Enter the peak-to-peak signal input voltage...");
        biasingInputField.setPromptText("Enter the biasing DC Voltage...");
        frequencyInputField.setPromptText("Enter the signal frequency...");
        
        String[] signalVoltageUnits = {"mVpp", "Vpp"};
        String[] biasingVoltageUnits = {"mV", "V"};
        String[] frequencyUnits = {"Hz", "kHz", "MHz"};
        
        signalVoltageCombo.setItems(FXCollections.observableArrayList(signalVoltageUnits));
        biasingVoltageCombo.setItems(FXCollections.observableArrayList(biasingVoltageUnits));
        frequencyCombo.setItems(FXCollections.observableArrayList(frequencyUnits));
        
        inputsPane.translateXProperty().addListener((ob, old, nw) -> {
            if (nw.doubleValue() == 00) {
                toFrequencyResponseButton.setDisable(true);
            }
        });
        
        ChangeListener listener = new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (Utils.isNumeric(signalInputField.getText()) && 
                    Utils.isNumeric(biasingInputField.getText()) && 
                    Utils.isNumeric(frequencyInputField.getText()) &&
                    signalVoltageCombo.getSelectionModel().getSelectedItem() != null &&
                    biasingVoltageCombo.getSelectionModel().getSelectedItem() != null &&
                    frequencyCombo.getSelectionModel().getSelectedItem() != null) {
                    
                    if (Double.parseDouble(signalInputField.getText()) <= 0 &&
                            Double.parseDouble(biasingInputField.getText()) <= 0 &&
                            Double.parseDouble(frequencyInputField.getText()) <= 0) {
                        toFrequencyResponseButton.setDisable(true);
                        return;
                    }
                    
                    toFrequencyResponseButton.setDisable(false);
                } else {
                    toFrequencyResponseButton.setDisable(true);
                }
            }
        };
        
        signalInputField.textProperty().addListener(listener);
        biasingInputField.textProperty().addListener(listener);
        frequencyInputField.textProperty().addListener(listener);
        signalVoltageCombo.getSelectionModel().selectedItemProperty().addListener(listener);
        biasingVoltageCombo.getSelectionModel().selectedItemProperty().addListener(listener);
        frequencyCombo.getSelectionModel().selectedItemProperty().addListener(listener);
    }    

    @FXML
    private void handleReturnToOutputsButton(ActionEvent event) throws IOException {
        sourceOutputPane = (AnchorPane) App.loadFXML("bjtOptions");
        
        homePagePane = (AnchorPane) inputsPane.getParent();
        switchPane(homePagePane, inputsPane, sourceOutputPane, Direction.BACKWARD);
    }

    @FXML
    private void handleToFrequencyResponseButton(ActionEvent event) throws IOException {
        AnchorPane frequencyResponsePane = (AnchorPane) App.loadFXML("frequencyResponseOptions");
        
        double signalInputVoltage = Double.parseDouble(signalInputField.getText());
        double biasingInputVoltage = Double.parseDouble(biasingInputField.getText());
        double frequency = Double.parseDouble(frequencyInputField.getText());
        
        if (signalVoltageCombo.getSelectionModel().getSelectedItem().equals("mVpp")) {
            signalInputVoltage /= 1000;
        }
        
        if (biasingVoltageCombo.getSelectionModel().getSelectedItem().equals("mV")) {
            biasingInputVoltage /= 1000;
        }
        
        if (frequencyCombo.getSelectionModel().getSelectedItem().equals("kHz")) {
            frequency *= 1000;
        } else if (frequencyCombo.getSelectionModel().getSelectedItem().equals("MHz")) {
            frequency *= 1000000;
        }
        
        AmplificationProcessor processor = Processors.getActiveProcessor("");
        processor.setInputParameters(frequency, signalInputVoltage, biasingInputVoltage);
        
        homePagePane = (AnchorPane) inputsPane.getParent();
        switchPane(homePagePane, inputsPane, frequencyResponsePane, Direction.FORWARD);
    }
}
