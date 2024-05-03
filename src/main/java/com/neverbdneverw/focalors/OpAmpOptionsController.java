/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.neverbdneverw.focalors;

import com.neverbdneverw.focalors.Utils.Direction;
import javafx.scene.control.Button;
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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        previousImageView.setImage(Utils.getImage("back", Color.WHITE));
        nextImageView.setImage(Utils.getImage("next", Color.WHITE));
        
        Utils.buttonAddHoverEffect(opAmpToInputsButton);
        Utils.buttonAddHoverEffect(returnToMainQueueButton);
        
        this.setPaneName("Operational Amplifier Options");
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
        
        homePagePane = (AnchorPane) opAmpOptionsPane.getParent();
        switchPane(homePagePane, opAmpOptionsPane, inputsPane, Direction.FORWARD);
    }
}
