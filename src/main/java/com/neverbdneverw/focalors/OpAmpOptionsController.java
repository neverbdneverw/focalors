/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.neverbdneverw.focalors;

import com.neverbdneverw.focalors.Utilities.Utils;
import com.neverbdneverw.focalors.AmplificationProcessors.Processors;
import com.neverbdneverw.focalors.AmplificationProcessors.OpAmpAmplificationProcessor;
import com.neverbdneverw.focalors.AmplificationProcessors.OpAmpAmplificationProcessor.OpAmpType;
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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
/**
 *
 * @author HUAWEI-Pc
 */
public class OpAmpOptionsController extends ProcedureSwitchingPaneController implements Initializable {
    @FXML
    private Button returnToMainQueueButton;
    @FXML
    private AnchorPane opAmpOptionsPane;
    @FXML
    private ImageView previousImageView;
    @FXML
    private ImageView nextImageView;
    @FXML
    private Button opAmpToInputsButton;
    
    private AnchorPane mainQueuePane;
    private AnchorPane homePagePane;
    private AnchorPane inputsPane;
    @FXML
    private ToggleButton nonInvertingButton;
    @FXML
    private ToggleGroup opAmpTypeGroup;
    @FXML
    private ToggleButton invertingButton;
    @FXML
    private TextField gainInputField;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        previousImageView.setImage(Utils.getImage("back", Color.WHITE));
        nextImageView.setImage(Utils.getImage("next", Color.WHITE));
        
        Utils.buttonAddHoverEffect(opAmpToInputsButton);
        Utils.buttonAddHoverEffect(returnToMainQueueButton);
        Utils.buttonAddHoverEffect(nonInvertingButton);
        Utils.buttonAddHoverEffect(invertingButton);
        
        this.setPaneName("Operational Amplifier Options");
        
        opAmpOptionsPane.translateXProperty().addListener((ob, old, nw) -> {
            if (nw.doubleValue() == 00) {
                opAmpToInputsButton.setDisable(true);
            }
        });
        
        ChangeListener listener = new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                if (Utils.isNumeric(gainInputField.getText()) && (opAmpTypeGroup.getSelectedToggle() != null)) {
                    if (Double.parseDouble(gainInputField.getText()) <= 0) {
                        opAmpToInputsButton.setDisable(true);
                        return;
                    }
                    opAmpToInputsButton.setDisable(false);
                } else {
                    opAmpToInputsButton.setDisable(true);
                }
            }
        };
        
        gainInputField.setPromptText("Enter voltage gain here...");
        
        gainInputField.textProperty().addListener((ob, old, newText) -> {
            if (Utils.isNumeric(newText) && (opAmpTypeGroup.getSelectedToggle() != null)) {
                if (Double.parseDouble(gainInputField.getText()) <= 0) {
                    opAmpToInputsButton.setDisable(true);
                    return;
                }

                opAmpToInputsButton.setDisable(false);
            } else {
                opAmpToInputsButton.setDisable(true);
            }
        });
        
        opAmpTypeGroup.selectedToggleProperty().addListener((ob, old, nw) -> {
            if (Utils.isNumeric(gainInputField.getText()) && (nw != null)) {
                if (Double.parseDouble(gainInputField.getText()) <= 0) {
                    opAmpToInputsButton.setDisable(true);
                    return;
                }

                opAmpToInputsButton.setDisable(false);
            } else {
                opAmpToInputsButton.setDisable(true);
            }
        });
    }
    
    @FXML
    private void handleReturnToMainQueueButton (ActionEvent event) throws IOException {
        mainQueuePane = (AnchorPane) App.loadFXML("mainqueue");

        homePagePane = (AnchorPane) opAmpOptionsPane.getParent();
        switchPane(homePagePane, opAmpOptionsPane, mainQueuePane, Direction.BACKWARD);
    }

    @FXML
    private void handleOpAmpToInputsButton(ActionEvent event) throws IOException {
        inputsPane = (AnchorPane) App.loadFXML("inputOptions");
        
        OpAmpType type = opAmpTypeGroup.getSelectedToggle().equals(invertingButton) ? OpAmpType.INVERTING : OpAmpType.NON_INVERTING;
        
        OpAmpAmplificationProcessor processor = (OpAmpAmplificationProcessor) Processors.getActiveProcessor("opamp");
        processor.setDesiredOutput(Double.parseDouble(gainInputField.getText()), type);
        
        homePagePane = (AnchorPane) opAmpOptionsPane.getParent();
        switchPane(homePagePane, opAmpOptionsPane, inputsPane, Direction.FORWARD);
    }
}
