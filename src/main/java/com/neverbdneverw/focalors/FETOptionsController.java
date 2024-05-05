/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.neverbdneverw.focalors;

import com.neverbdneverw.focalors.AmplificationProcessors.BJTAmplificationProcessor;
import com.neverbdneverw.focalors.AmplificationProcessors.FETAmplificationProcessor;
import com.neverbdneverw.focalors.AmplificationProcessors.Processors;
import com.neverbdneverw.focalors.Utilities.Utils;
import com.neverbdneverw.focalors.Utilities.Utils.Direction;
import javafx.scene.control.Button;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
/**
 *
 * @author HUAWEI-Pc
 */
public class FETOptionsController extends ProcedureSwitchingPaneController implements Initializable {
    @FXML
    private Button returnToMainQueueButton;
    @FXML
    private AnchorPane fetOptionsPane;
    @FXML
    private ImageView previousImageView;
    @FXML
    private ImageView nextImageView;
    @FXML
    private Button fetToInputsButton;
    @FXML
    private TextField transconductanceParameterInputField;
    @FXML
    private TextField voltageGainInputField;
    @FXML
    private TextField thresholdVoltageInputField;
    @FXML
    private ComboBox thresholdVoltageUnitCombo;
    
    private AnchorPane mainQueuePane;
    private AnchorPane homePagePane;
    private AnchorPane inputsPane;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        previousImageView.setImage(Utils.getImage("back", Color.WHITE));
        nextImageView.setImage(Utils.getImage("next", Color.WHITE));
        
        Utils.buttonAddHoverEffect(fetToInputsButton);
        Utils.buttonAddHoverEffect(returnToMainQueueButton);
        
        this.setPaneName("FET Options");
        
        String[] thresholdVoltageUnits = {"mV", "V"};
        
        thresholdVoltageUnitCombo.setItems(FXCollections.observableArrayList(thresholdVoltageUnits));
        
        fetOptionsPane.translateXProperty().addListener((ob, old, nw) -> {
            if (nw.doubleValue() == 00) {
                fetToInputsButton.setDisable(true);
            }
        });
        
        ChangeListener listener = new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (Utils.isNumeric(transconductanceParameterInputField.getText()) && 
                    Utils.isNumeric(voltageGainInputField.getText()) && 
                    Utils.isNumeric(thresholdVoltageInputField.getText()) &&
                    thresholdVoltageUnitCombo.getSelectionModel().getSelectedItem() != null) {
                    
                    if (Double.parseDouble(transconductanceParameterInputField.getText()) <= 0 &&
                            Double.parseDouble(voltageGainInputField.getText()) <= 0 &&
                            Double.parseDouble(thresholdVoltageInputField.getText()) <= 0) {
                        fetToInputsButton.setDisable(true);
                        return;
                    }
                    
                    fetToInputsButton.setDisable(false);
                } else {
                    fetToInputsButton.setDisable(true);
                }
            }
        };
        
        transconductanceParameterInputField.textProperty().addListener(listener);
        voltageGainInputField.textProperty().addListener(listener);
        thresholdVoltageInputField.textProperty().addListener(listener);
        thresholdVoltageUnitCombo.getSelectionModel().selectedItemProperty().addListener(listener);
    }
    
    @FXML
    private void handleReturnToMainQueueButton (ActionEvent event) throws IOException {
        mainQueuePane = (AnchorPane) App.loadFXML("mainqueue");

        homePagePane = (AnchorPane) fetOptionsPane.getParent();
        switchPane(homePagePane, fetOptionsPane, mainQueuePane, Direction.BACKWARD);
    }

    @FXML
    private void handleFETToInputsButton(ActionEvent event) throws IOException {
        inputsPane = (AnchorPane) App.loadFXML("inputOptions");
        
        double thresholdVoltage = Double.parseDouble(thresholdVoltageInputField.getText());
        
        if (thresholdVoltageUnitCombo.getSelectionModel().getSelectedItem().equals("μA")) {
            thresholdVoltage /= 1000;
        }
        
        double transconductanceParameter = Double.parseDouble(transconductanceParameterInputField.getText());
        
        FETAmplificationProcessor processor = (FETAmplificationProcessor) Processors.getActiveProcessor("fet");
        processor.setDesiredOutput(Double.parseDouble(voltageGainInputField.getText()), transconductanceParameter, thresholdVoltage);
        
        homePagePane = (AnchorPane) fetOptionsPane.getParent();
        switchPane(homePagePane, fetOptionsPane, inputsPane, Direction.FORWARD);
    }
}
