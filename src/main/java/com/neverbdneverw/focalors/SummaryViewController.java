/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.neverbdneverw.focalors;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
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
        frequencyResponsePane.translateXProperty().set(-1 * homePagePane.getWidth());
        homePagePane.setTopAnchor(frequencyResponsePane, 0.0);
        homePagePane.setBottomAnchor(frequencyResponsePane, 0.0);
        homePagePane.setLeftAnchor(frequencyResponsePane, 0.0);
        homePagePane.setRightAnchor(frequencyResponsePane, 0.0);

        KeyValue homePaneKV = new KeyValue(summaryViewPane.translateXProperty(), summaryViewPane.getWidth(), new BounceInterpolator());
        KeyFrame homePaneKF = new KeyFrame(Duration.millis(300), homePaneKV);
        KeyValue rootPaneKV = new KeyValue(frequencyResponsePane.translateXProperty(), 0, new BounceInterpolator());
        KeyFrame rootPaneKF = new KeyFrame(Duration.millis(300), rootPaneKV);

        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(homePaneKF);
        timeline.getKeyFrames().add(rootPaneKF);

        timeline.setOnFinished((e) -> {
            homePagePane.getChildren().remove(summaryViewPane);
        });

        timeline.play();
    }
    
}
