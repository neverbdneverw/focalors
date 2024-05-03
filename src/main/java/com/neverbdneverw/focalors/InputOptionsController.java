/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.neverbdneverw.focalors;

import com.neverbdneverw.focalors.Utils.Direction;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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
        
        homePagePane = (AnchorPane) inputsPane.getParent();
        switchPane(homePagePane, inputsPane, frequencyResponsePane, Direction.FORWARD);
    }
}
