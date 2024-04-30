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
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Separator;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;

public class PrimaryController implements Initializable {

    @FXML
    private Button returnHomeButton;
    @FXML
    private Separator returnHomeSeparator;
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
    private AnchorPane homePane;
    @FXML
    private ImageView homeImageView;
    @FXML
    private ImageView tutorialImageView;
    @FXML
    private ImageView settingImageView;
    @FXML
    private ImageView feedbackImageView;
    
    private AnchorPane mainQueuePane;
    private VBox sideBox;
    @FXML
    private Button primaryButton;
    @FXML
    private Button aboutFocalorsButton;
    @FXML
    private Pane imagePane1;
    @FXML
    private ImageView startImageView;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        sideBox = (VBox) sideBarPane.getChildren().get(0);
        sideBox.getChildren().remove(returnHomeButton);
        sideBox.getChildren().remove(returnHomeSeparator);
        
        homeImageView.setImage(Utils.getImage("home", Color.WHITE));
        tutorialImageView.setImage(Utils.getImage("tutorial", Color.WHITE));
        settingImageView.setImage(Utils.getImage("setting", Color.WHITE));
        feedbackImageView.setImage(Utils.getImage("feedback", Color.WHITE));
        
        startImageView.setImage(Utils.getImage("start", Color.WHITE));
    }
    
    @FXML
    private void switchToMainQueue() throws IOException {
        mainQueuePane = (AnchorPane) App.loadFXML("mainqueue");
        
        homePagePane.getChildren().add(mainQueuePane);
        mainQueuePane.translateXProperty().set(mainHBox.getWidth());
        homePagePane.setTopAnchor(mainQueuePane, 0.0);
        homePagePane.setBottomAnchor(mainQueuePane, 0.0);
        homePagePane.setLeftAnchor(mainQueuePane, 0.0);
        homePagePane.setRightAnchor(mainQueuePane, 0.0);
        
        KeyValue homePaneKV = new KeyValue(homePane.translateXProperty(), -1 * homePagePane.getWidth() / 2, new BounceInterpolator());
        KeyFrame homePaneKF = new KeyFrame(Duration.millis(300), homePaneKV);
        KeyValue homePaneOpacityKV = new KeyValue(homePane.opacityProperty(), 0, Interpolator.EASE_OUT);
        KeyFrame homePaneOpacityKF = new KeyFrame(Duration.millis(100), homePaneOpacityKV);
        KeyValue rootPaneKV = new KeyValue(mainQueuePane.translateXProperty(), 0, new BounceInterpolator());
        KeyFrame rootPaneKF = new KeyFrame(Duration.millis(300), rootPaneKV);

        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(homePaneKF);
        timeline.getKeyFrames().add(rootPaneKF);
        timeline.getKeyFrames().add(homePaneOpacityKF);
        
        timeline.setOnFinished((e) -> {
            homePagePane.getChildren().remove(homePane);
            
            sideBox.getChildren().add(0, returnHomeButton);
            sideBox.getChildren().add(1, returnHomeSeparator);
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
            KeyFrame rootPaneKF = new KeyFrame(Duration.millis(300), rootPaneKV);

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
    
    @FXML
    private void handleReturnHomeButtonEvent(ActionEvent event) throws IOException {
        if (event.getSource().equals(returnHomeButton)) {
            homePagePane.getChildren().add(homePane);
            homePane.translateXProperty().set(-1 * homePagePane.getWidth());
            homePagePane.setTopAnchor(homePane, 0.0);
            homePagePane.setBottomAnchor(homePane, 0.0);
            homePagePane.setLeftAnchor(homePane, 0.0);
            homePagePane.setRightAnchor(homePane, 0.0);
            
            homePane.setOpacity(0);
            
            AnchorPane removablePane = (AnchorPane) homePagePane.getChildren().get(0);

            KeyValue homePaneKV = new KeyValue(homePane.translateXProperty(), 0, new BounceInterpolator());
            KeyFrame homePaneKF = new KeyFrame(Duration.millis(300), homePaneKV);
            KeyValue homePaneOpacityKV = new KeyValue(homePane.opacityProperty(), 1, Interpolator.EASE_IN);
            KeyFrame homePaneOpacityKF = new KeyFrame(Duration.millis(300), homePaneOpacityKV);
            KeyValue rootPaneKV = new KeyValue(removablePane.translateXProperty(), mainPane.getWidth(), new BounceInterpolator());
            KeyFrame rootPaneKF = new KeyFrame(Duration.millis(300), rootPaneKV);

            Timeline timeline = new Timeline();
            timeline.getKeyFrames().add(homePaneKF);
            timeline.getKeyFrames().add(rootPaneKF);
            timeline.getKeyFrames().add(homePaneOpacityKF);

            timeline.setOnFinished((e) -> {
                homePagePane.getChildren().remove(removablePane);

                sideBox.getChildren().remove(returnHomeButton);
                sideBox.getChildren().remove(returnHomeSeparator);
            });

            timeline.play();
        }
    }
}
