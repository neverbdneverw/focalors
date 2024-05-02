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
import javafx.scene.paint.Color;
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
        previousImageView.setImage(Utils.getImage("back", Color.WHITE));
        nextImageView.setImage(Utils.getImage("next", Color.WHITE));
        
        Utils.buttonAddHoverEffect(toSummaryViewButton);
        Utils.buttonAddHoverEffect(returnToInputsButton);
    }    

    @FXML
    private void handleReturnToInputsButton(ActionEvent event) throws IOException {
        AnchorPane inputsPane = (AnchorPane) App.loadFXML("inputOptions");
        
        homePagePane = (AnchorPane) frequencyResponsePane.getParent();
        homePagePane.getChildren().add(inputsPane);

        inputsPane.translateXProperty().set(-1 * homePagePane.getWidth() / 4);
        inputsPane.setOpacity(0);

        homePagePane.setTopAnchor(inputsPane, 0.0);
        homePagePane.setBottomAnchor(inputsPane, 0.0);
        homePagePane.setLeftAnchor(inputsPane, 0.0);
        homePagePane.setRightAnchor(inputsPane, 0.0);

        KeyValue frequencyResponsePaneKV = new KeyValue(frequencyResponsePane.translateXProperty(), frequencyResponsePane.getWidth(), new BounceInterpolator());
        KeyFrame frequencyResponsePaneKF = new KeyFrame(Duration.millis(300), frequencyResponsePaneKV);
        KeyValue inputsPaneKV = new KeyValue(inputsPane.translateXProperty(), 0, new BounceInterpolator());
        KeyFrame inputsPaneKF = new KeyFrame(Duration.millis(300), inputsPaneKV);
        KeyValue inputsPaneOpacityKV = new KeyValue(inputsPane.opacityProperty(), 1, Interpolator.EASE_IN);
        KeyFrame inputsPaneOpacityKF = new KeyFrame(Duration.millis(300), inputsPaneOpacityKV);

        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(frequencyResponsePaneKF);
        timeline.getKeyFrames().add(inputsPaneKF);
        timeline.getKeyFrames().add(inputsPaneOpacityKF);

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

        KeyValue frequencyResponsePaneKV = new KeyValue(frequencyResponsePane.translateXProperty(), -1 * frequencyResponsePane.getWidth(), new BounceInterpolator());
        KeyFrame frequencyResponsePaneKF = new KeyFrame(Duration.millis(300), frequencyResponsePaneKV);
        KeyValue frequencyResponsePaneOpacityKV = new KeyValue(frequencyResponsePane.opacityProperty(), 0, Interpolator.EASE_IN);
        KeyFrame frequencyResponsePaneOpacityKF = new KeyFrame(Duration.millis(50), frequencyResponsePaneOpacityKV);
        KeyValue summaryViewPaneKV = new KeyValue(summaryViewPane.translateXProperty(), 0, new BounceInterpolator());
        KeyFrame summaryViewPaneKF = new KeyFrame(Duration.millis(300), summaryViewPaneKV);

        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(frequencyResponsePaneKF);
        timeline.getKeyFrames().add(frequencyResponsePaneOpacityKF);
        timeline.getKeyFrames().add(summaryViewPaneKF);

        timeline.setOnFinished((e) -> {
            homePagePane.getChildren().remove(frequencyResponsePane);
        });

        timeline.play();
    }
}
