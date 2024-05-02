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
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
/**
 * FXML Controller class
 *
 * @author HUAWEI-Pc
 */
public class FeedbackController implements Initializable {

    @FXML
    private AnchorPane feedbackPane;
    @FXML
    private Button giveFeedbackButton;
    @FXML
    private StackPane feedBackSwitcherPane;
    @FXML
    private AnchorPane secondFeedbackPane;
    @FXML
    private AnchorPane firstFeedbackPane;
    @FXML
    private Label emailLinkText;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        feedbackPane.setId("feedbackAnchorPane");
        Utils.buttonAddHoverEffect(giveFeedbackButton);
    }

    @FXML
    private void handleGiveFeedbackButtonClicked(ActionEvent event) {
        secondFeedbackPane.setVisible(true);
        secondFeedbackPane.translateXProperty().set(feedbackPane.getWidth());

        KeyValue secFePaneKV = new KeyValue(secondFeedbackPane.translateXProperty(), 0, new BounceInterpolator());
        KeyFrame secFePaneKF = new KeyFrame(Duration.millis(300), secFePaneKV);
        KeyValue fiFePaneKV = new KeyValue(firstFeedbackPane.translateXProperty(), -1 * feedbackPane.getWidth() / 4, new BounceInterpolator());
        KeyFrame fiFePaneKF = new KeyFrame(Duration.millis(300), fiFePaneKV);
        KeyValue fiFePaneOpKV = new KeyValue(firstFeedbackPane.opacityProperty(), 0, new BounceInterpolator());
        KeyFrame fiFePaneOpKF = new KeyFrame(Duration.millis(50), fiFePaneOpKV);

        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(secFePaneKF);
        timeline.getKeyFrames().add(fiFePaneKF);
        timeline.getKeyFrames().add(fiFePaneOpKF);
        timeline.play();
        
        timeline.setOnFinished(eh -> {
            firstFeedbackPane.setVisible(false);
        });
    }
}
