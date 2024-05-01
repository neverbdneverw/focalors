/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.neverbdneverw.focalors;

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
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author HUAWEI-Pc
 */
public class SummaryViewController implements Initializable {

    @FXML
    private AnchorPane summaryViewPane;
    @FXML
    private Button returnToFreqResButton;
    
    private AnchorPane homePagePane;
    @FXML
    private ImageView previousImageView;
    @FXML
    private ImageView nextImageView;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void handleReturnToFreqResButton(ActionEvent event) throws IOException {
        AnchorPane frequencyResponsePane = (AnchorPane) App.loadFXML("frequencyResponseOptions");
        
        homePagePane = (AnchorPane) summaryViewPane.getParent();
        homePagePane.getChildren().add(frequencyResponsePane);

        frequencyResponsePane.translateXProperty().set(-1 * homePagePane.getWidth() / 4);
        frequencyResponsePane.setOpacity(0);

        homePagePane.setTopAnchor(frequencyResponsePane, 0.0);
        homePagePane.setBottomAnchor(frequencyResponsePane, 0.0);
        homePagePane.setLeftAnchor(frequencyResponsePane, 0.0);
        homePagePane.setRightAnchor(frequencyResponsePane, 0.0);

        KeyValue summaryViewPaneKV = new KeyValue(summaryViewPane.translateXProperty(), summaryViewPane.getWidth(), new BounceInterpolator());
        KeyFrame summaryViewPaneKF = new KeyFrame(Duration.millis(300), summaryViewPaneKV);
        KeyValue frequencyResponsePaneKV = new KeyValue(frequencyResponsePane.translateXProperty(), 0, new BounceInterpolator());
        KeyFrame frequencyResponsePaneKF = new KeyFrame(Duration.millis(300), frequencyResponsePaneKV);
        KeyValue frequencyResponsePaneOpacityKV = new KeyValue(frequencyResponsePane.opacityProperty(), 1, Interpolator.EASE_IN);
        KeyFrame frequencyResponsePaneOpacityKF = new KeyFrame(Duration.millis(300), frequencyResponsePaneOpacityKV);

        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(summaryViewPaneKF);
        timeline.getKeyFrames().add(frequencyResponsePaneKF);
        timeline.getKeyFrames().add(frequencyResponsePaneOpacityKF);

        timeline.setOnFinished((e) -> {
            homePagePane.getChildren().remove(summaryViewPane);
        });

        timeline.play();
    }
    
}
