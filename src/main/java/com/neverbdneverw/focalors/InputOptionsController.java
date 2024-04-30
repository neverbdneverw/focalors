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
import javafx.scene.paint.Color;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author HUAWEI-Pc
 */
public class InputOptionsController implements Initializable {

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
    }    

    @FXML
    private void handleReturnToOutputsButton(ActionEvent event) throws IOException {
        sourceOutputPane = (AnchorPane) App.loadFXML("bjtOptions");
        
        homePagePane = (AnchorPane) inputsPane.getParent();
        homePagePane.getChildren().add(sourceOutputPane);
        sourceOutputPane.translateXProperty().set(-1 * homePagePane.getWidth());
        homePagePane.setTopAnchor(sourceOutputPane, 0.0);
        homePagePane.setBottomAnchor(sourceOutputPane, 0.0);
        homePagePane.setLeftAnchor(sourceOutputPane, 0.0);
        homePagePane.setRightAnchor(sourceOutputPane, 0.0);

        KeyValue homePaneKV = new KeyValue(inputsPane.translateXProperty(), inputsPane.getWidth(), new BounceInterpolator());
        KeyFrame homePaneKF = new KeyFrame(Duration.millis(300), homePaneKV);
        KeyValue rootPaneKV = new KeyValue(sourceOutputPane.translateXProperty(), 0, new BounceInterpolator());
        KeyFrame rootPaneKF = new KeyFrame(Duration.millis(300), rootPaneKV);

        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(homePaneKF);
        timeline.getKeyFrames().add(rootPaneKF);

        timeline.setOnFinished((e) -> {
            homePagePane.getChildren().remove(inputsPane);
        });

        timeline.play();
    }

    @FXML
    private void handleToFrequencyResponseButton(ActionEvent event) throws IOException {
        AnchorPane frequencyResponsePane = (AnchorPane) App.loadFXML("frequencyResponseOptions");
        
        homePagePane = (AnchorPane) inputsPane.getParent();
        homePagePane.getChildren().add(frequencyResponsePane);
        frequencyResponsePane.translateXProperty().set(homePagePane.getWidth());
        homePagePane.setTopAnchor(frequencyResponsePane, 0.0);
        homePagePane.setBottomAnchor(frequencyResponsePane, 0.0);
        homePagePane.setLeftAnchor(frequencyResponsePane, 0.0);
        homePagePane.setRightAnchor(frequencyResponsePane, 0.0);

        KeyValue homePaneKV = new KeyValue(inputsPane.translateXProperty(), -1 * inputsPane.getWidth(), new BounceInterpolator());
        KeyFrame homePaneKF = new KeyFrame(Duration.millis(300), homePaneKV);
        KeyValue rootPaneKV = new KeyValue(frequencyResponsePane.translateXProperty(), 0, new BounceInterpolator());
        KeyFrame rootPaneKF = new KeyFrame(Duration.millis(300), rootPaneKV);

        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(homePaneKF);
        timeline.getKeyFrames().add(rootPaneKF);

        timeline.setOnFinished((e) -> {
            homePagePane.getChildren().remove(inputsPane);
        });

        timeline.play();
    }

    
}
