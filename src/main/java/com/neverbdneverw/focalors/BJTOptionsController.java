/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.neverbdneverw.focalors;

import com.neverbdneverw.focalors.AmplificationProcessors.BJTAmplificationProcessor;
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
public class BJTOptionsController extends ProcedureSwitchingPaneController implements Initializable {
    @FXML
    private Button returnToMainQueueButton;
    @FXML
    private AnchorPane bjtOptionsPane;
    @FXML
    private Button bjtToInputsButton;
    @FXML
    private ImageView previousImageView;
    @FXML
    private ImageView nextImageView;
    @FXML
    private TextField collectorCurrentInputField;
    @FXML
    private ComboBox<String> currentUnitCombo;
    @FXML
    private TextField voltageGainInputField;
    @FXML
    private TextField hfeInputField;
    
    private AnchorPane mainQueuePane;
    private AnchorPane homePagePane;
    private AnchorPane inputsPane;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        previousImageView.setImage(Utils.getImage("back", Color.WHITE));
        nextImageView.setImage(Utils.getImage("next", Color.WHITE));
        
        Utils.buttonAddHoverEffect(bjtToInputsButton);
        Utils.buttonAddHoverEffect(returnToMainQueueButton);
        
        this.setPaneName("BJT Options");
        
        String[] currentUnits = {"μA", "mA", "A"};
        
        currentUnitCombo.setItems(FXCollections.observableArrayList(currentUnits));
        
        bjtOptionsPane.translateXProperty().addListener((ob, old, nw) -> {
            if (nw.doubleValue() == 00) {
                bjtToInputsButton.setDisable(true);
            }
        });
        
        ChangeListener listener = new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (Utils.isNumeric(collectorCurrentInputField.getText()) && 
                    Utils.isNumeric(voltageGainInputField.getText()) && 
                    Utils.isNumeric(hfeInputField.getText()) &&
                    currentUnitCombo.getSelectionModel().getSelectedItem() != null) {
                    
                    if (Double.parseDouble(collectorCurrentInputField.getText()) <= 0 &&
                            Double.parseDouble(voltageGainInputField.getText()) <= 0 &&
                            Double.parseDouble(hfeInputField.getText()) <= 0) {
                        bjtToInputsButton.setDisable(true);
                        return;
                    }
                    
                    bjtToInputsButton.setDisable(false);
                } else {
                    bjtToInputsButton.setDisable(true);
                }
            }
        };
        
        collectorCurrentInputField.textProperty().addListener(listener);
        voltageGainInputField.textProperty().addListener(listener);
        hfeInputField.textProperty().addListener(listener);
        currentUnitCombo.getSelectionModel().selectedItemProperty().addListener(listener);
    }
    
    @FXML
    private void handleReturnToMainQueueButton (ActionEvent event) throws IOException {
        mainQueuePane = (AnchorPane) App.loadFXML("mainqueue");

        homePagePane = (AnchorPane) bjtOptionsPane.getParent();
        switchPane(homePagePane, bjtOptionsPane, mainQueuePane, Direction.BACKWARD);
    }
    
    @FXML
    private void handleBJTToInputsButton (ActionEvent event) throws IOException {
        inputsPane = (AnchorPane) App.loadFXML("inputOptions");
        
        double collectorCurrent = Double.parseDouble(collectorCurrentInputField.getText());
        
        if (currentUnitCombo.getSelectionModel().getSelectedItem().equals("μA")) {
            collectorCurrent /= 1000000;
        } else if (currentUnitCombo.getSelectionModel().getSelectedItem().equals("mA")) {
            collectorCurrent /= 1000;
        }
        
        BJTAmplificationProcessor processor = (BJTAmplificationProcessor) Processors.getActiveProcessor("bjt");
        processor.setDesiredOutput(Double.parseDouble(voltageGainInputField.getText()), collectorCurrent, Double.parseDouble(hfeInputField.getText()));
        
        homePagePane = (AnchorPane) bjtOptionsPane.getParent();
        switchPane(homePagePane, bjtOptionsPane, inputsPane, Direction.FORWARD);
    }
}
