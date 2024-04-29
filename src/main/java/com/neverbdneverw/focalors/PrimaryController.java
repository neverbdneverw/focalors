package com.neverbdneverw.focalors;

import java.io.IOException;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class PrimaryController {

    @FXML
    private Button primaryButton;
    @FXML
    private Button settingButton;
    @FXML
    private Button tutorialButton;
    @FXML
    private Button feedbackButton;
    @FXML
    private Pane imagePane;
    @FXML
    private AnchorPane sideBarPane;
    @FXML
    private AnchorPane homePagePane;
    @FXML
    private AnchorPane mainPane;
    @FXML
    private HBox mainHBox;
    @FXML
    private BorderPane homePane;

    @FXML
    private void switchToMainQueue() throws IOException {
        AnchorPane root = (AnchorPane) App.loadFXML("mainqueue");
        
        homePagePane.getChildren().add(root);
        root.translateXProperty().set(mainHBox.getWidth());
        homePagePane.setTopAnchor(root, 0.0);
        homePagePane.setBottomAnchor(root, 0.0);
        homePagePane.setLeftAnchor(root, 0.0);
        homePagePane.setRightAnchor(root, 0.0);
        
        
        KeyValue homePaneKV = new KeyValue(homePane.translateXProperty(), -1 * homePane.getWidth(), Interpolator.EASE_IN);
        KeyFrame homePaneKF = new KeyFrame(Duration.millis(300), homePaneKV);
        KeyValue rootPaneKV = new KeyValue(root.translateXProperty(), 0, Interpolator.EASE_IN);
        KeyFrame rootPaneKF = new KeyFrame(Duration.millis(300), rootPaneKV);

        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(homePaneKF);
        timeline.getKeyFrames().add(rootPaneKF);
        
        timeline.setOnFinished((e) -> {
            homePagePane.getChildren().remove(homePane);
        });
        
        timeline.play();
    }

    @FXML
    private void handleSettingButtonEvent(ActionEvent event) throws IOException {
        if (event.getSource().equals(settingButton)) {
            Parent root = App.loadFXML("settings");
            
            root.translateYProperty().set(homePagePane.getScene().getHeight());
            App.setSceneRoot(root);

            KeyValue rootPaneKV = new KeyValue(root.translateYProperty(), 0, Interpolator.EASE_IN);
            KeyFrame rootPaneKF = new KeyFrame(Duration.millis(250), rootPaneKV);

            Timeline timeline = new Timeline();
            timeline.getKeyFrames().add(rootPaneKF);
            timeline.play();
        }
    }

    @FXML
    private void handleTutorialButtonEvent(ActionEvent event) throws IOException {
        if (event.getSource().equals(tutorialButton)) {
            App.setRoot("tutorial");
        }
    }

    @FXML
    private void handleFeedbackButtonEvent(ActionEvent event) throws IOException {
        if (event.getSource().equals(feedbackButton)) {
            App.setRoot("feedback");
        }
    }
}
