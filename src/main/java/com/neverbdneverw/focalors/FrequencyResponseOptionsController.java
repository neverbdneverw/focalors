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
public class FrequencyResponseOptionsController implements Initializable {

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
        // TODO
    }    

    @FXML
    private void handleReturnToInputsButton(ActionEvent event) throws IOException {
        AnchorPane inputsPane = (AnchorPane) App.loadFXML("inputOptions");
        
        homePagePane = (AnchorPane) frequencyResponsePane.getParent();
        homePagePane.getChildren().add(inputsPane);
        inputsPane.translateXProperty().set(-1 * homePagePane.getWidth());
        homePagePane.setTopAnchor(inputsPane, 0.0);
        homePagePane.setBottomAnchor(inputsPane, 0.0);
        homePagePane.setLeftAnchor(inputsPane, 0.0);
        homePagePane.setRightAnchor(inputsPane, 0.0);

        KeyValue homePaneKV = new KeyValue(frequencyResponsePane.translateXProperty(), frequencyResponsePane.getWidth(), new BounceInterpolator());
        KeyFrame homePaneKF = new KeyFrame(Duration.millis(300), homePaneKV);
        KeyValue rootPaneKV = new KeyValue(inputsPane.translateXProperty(), 0, new BounceInterpolator());
        KeyFrame rootPaneKF = new KeyFrame(Duration.millis(300), rootPaneKV);

        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(homePaneKF);
        timeline.getKeyFrames().add(rootPaneKF);

        timeline.setOnFinished((e) -> {
            homePagePane.getChildren().remove(frequencyResponsePane);
        });

        timeline.play();
    }

    @FXML
    private void handleToSummaryViewButton(ActionEvent event) throws IOException {
        AnchorPane summaryViewPane = (AnchorPane) App.loadFXML("summaryView");
        
        homePagePane = (AnchorPane) frequencyResponsePane.getParent();
        homePagePane.getChildren().add(summaryViewPane);
        summaryViewPane.translateXProperty().set(homePagePane.getWidth());
        homePagePane.setTopAnchor(summaryViewPane, 0.0);
        homePagePane.setBottomAnchor(summaryViewPane, 0.0);
        homePagePane.setLeftAnchor(summaryViewPane, 0.0);
        homePagePane.setRightAnchor(summaryViewPane, 0.0);

        KeyValue homePaneKV = new KeyValue(frequencyResponsePane.translateXProperty(), -1 * frequencyResponsePane.getWidth(), new BounceInterpolator());
        KeyFrame homePaneKF = new KeyFrame(Duration.millis(300), homePaneKV);
        KeyValue rootPaneKV = new KeyValue(summaryViewPane.translateXProperty(), 0, new BounceInterpolator());
        KeyFrame rootPaneKF = new KeyFrame(Duration.millis(300), rootPaneKV);

        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(homePaneKF);
        timeline.getKeyFrames().add(rootPaneKF);

        timeline.setOnFinished((e) -> {
            homePagePane.getChildren().remove(frequencyResponsePane);
        });

        timeline.play();
    }
}
