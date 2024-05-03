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
public class FrequencyResponseOptionsController extends ProcedureSwitchingPaneController implements Initializable {

    @FXML
    private Button returnToInputsButton;
    @FXML
    private Button toSummaryViewButton;
    
    private AnchorPane homePagePane;
    @FXML
    private AnchorPane frequencyResponsePane;
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
        
        Utils.buttonAddHoverEffect(toSummaryViewButton);
        Utils.buttonAddHoverEffect(returnToInputsButton);
        
        this.setPaneName("Frequency Response Options");
    }    

    @FXML
    private void handleReturnToInputsButton(ActionEvent event) throws IOException {
        AnchorPane inputsPane = (AnchorPane) App.loadFXML("inputOptions");
        homePagePane = (AnchorPane) frequencyResponsePane.getParent();
        
        switchPane(homePagePane, frequencyResponsePane, inputsPane, Direction.BACKWARD);
    }

    @FXML
    private void handleToSummaryViewButton(ActionEvent event) throws IOException {
        AnchorPane summaryViewPane = (AnchorPane) App.loadFXML("summaryView");
        homePagePane = (AnchorPane) frequencyResponsePane.getParent();
        
        switchPane(homePagePane, frequencyResponsePane, summaryViewPane, Direction.FORWARD);
    }
}
