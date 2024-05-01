package com.neverbdneverw.focalors;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
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
    private ImageView startImageView;
    @FXML
    private Pane promotionPane;
    @FXML
    private Label tutorialText;
    @FXML
    private Label settingText;
    @FXML
    private Label feedbackText;
    @FXML
    private Label returnHomeText;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        sideBox = (VBox) sideBarPane.getChildren().get(0);
        sideBox.getChildren().remove(returnHomeButton);
        sideBox.getChildren().remove(returnHomeSeparator);
        
        homePane.setId("homePane");
        
        homeImageView.setImage(Utils.getImage("home", Color.WHITE));
        tutorialImageView.setImage(Utils.getImage("tutorial", Color.WHITE));
        settingImageView.setImage(Utils.getImage("setting", Color.WHITE));
        feedbackImageView.setImage(Utils.getImage("feedback", Color.WHITE));
        
        startImageView.setImage(Utils.getImage("start", Color.WHITE));
        
        handleHoveredAndSelected();
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
        KeyFrame homePaneOpacityKF = new KeyFrame(Duration.millis(50), homePaneOpacityKV);
        KeyValue mainQueuePaneKV = new KeyValue(mainQueuePane.translateXProperty(), 0, new BounceInterpolator());
        KeyFrame mainQueuePaneKF = new KeyFrame(Duration.millis(300), mainQueuePaneKV);

        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(homePaneKF);
        timeline.getKeyFrames().add(mainQueuePaneKF);
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
        AnchorPane removablePane = (AnchorPane) homePagePane.getChildren().get(0);
        
        if (!removablePane.getId().equals("homePane")) {
            handleReturnHomeButtonEvent(event);
        }

        AnchorPane settingPane = (AnchorPane) App.loadFXML("settings");
        
        homePagePane.getChildren().add(settingPane);
        settingPane.translateYProperty().set(-1 * mainHBox.getHeight());
        homePagePane.setTopAnchor(settingPane, 0.0);
        homePagePane.setBottomAnchor(settingPane, 0.0);
        homePagePane.setLeftAnchor(settingPane, 0.0);
        homePagePane.setRightAnchor(settingPane, 0.0);
        
        KeyValue homePaneKV = new KeyValue(homePane.translateYProperty(), homePagePane.getHeight(), new BounceInterpolator());
        KeyFrame homePaneKF = new KeyFrame(Duration.millis(300), homePaneKV);
        KeyValue homePaneOpacityKV = new KeyValue(homePane.opacityProperty(), 0, Interpolator.EASE_OUT);
        KeyFrame homePaneOpacityKF = new KeyFrame(Duration.millis(100), homePaneOpacityKV);
        KeyValue rootPaneKV = new KeyValue(settingPane.translateYProperty(), 0, new BounceInterpolator());
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
    private void handleTutorialButtonEvent(ActionEvent event) throws IOException {
        AnchorPane removablePane = (AnchorPane) homePagePane.getChildren().get(0);
        
        if (!removablePane.getId().equals("homePane")) {
            handleReturnHomeButtonEvent(event);
        }

        AnchorPane tutorialPane = (AnchorPane) App.loadFXML("tutorial");
        
        homePagePane.getChildren().add(tutorialPane);
        tutorialPane.translateYProperty().set(-1 * mainHBox.getHeight());
        homePagePane.setTopAnchor(tutorialPane, 0.0);
        homePagePane.setBottomAnchor(tutorialPane, 0.0);
        homePagePane.setLeftAnchor(tutorialPane, 0.0);
        homePagePane.setRightAnchor(tutorialPane, 0.0);
        
        KeyValue homePaneKV = new KeyValue(homePane.translateYProperty(), homePagePane.getHeight(), new BounceInterpolator());
        KeyFrame homePaneKF = new KeyFrame(Duration.millis(300), homePaneKV);
        KeyValue homePaneOpacityKV = new KeyValue(homePane.opacityProperty(), 0, Interpolator.EASE_OUT);
        KeyFrame homePaneOpacityKF = new KeyFrame(Duration.millis(100), homePaneOpacityKV);
        KeyValue rootPaneKV = new KeyValue(tutorialPane.translateYProperty(), 0, new BounceInterpolator());
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
    private void handleFeedbackButtonEvent(ActionEvent event) throws IOException {
        AnchorPane removablePane = (AnchorPane) homePagePane.getChildren().get(0);
        
        if (!removablePane.getId().equals("homePane")) {
            handleReturnHomeButtonEvent(event);
        }

        AnchorPane feedbackPane = (AnchorPane) App.loadFXML("feedback");
        
        homePagePane.getChildren().add(feedbackPane);
        feedbackPane.translateYProperty().set(-1 * mainHBox.getHeight());
        homePagePane.setTopAnchor(feedbackPane, 0.0);
        homePagePane.setBottomAnchor(feedbackPane, 0.0);
        homePagePane.setLeftAnchor(feedbackPane, 0.0);
        homePagePane.setRightAnchor(feedbackPane, 0.0);
        
        KeyValue homePaneKV = new KeyValue(homePane.translateYProperty(), homePagePane.getHeight(), new BounceInterpolator());
        KeyFrame homePaneKF = new KeyFrame(Duration.millis(300), homePaneKV);
        KeyValue homePaneOpacityKV = new KeyValue(homePane.opacityProperty(), 0, Interpolator.EASE_OUT);
        KeyFrame homePaneOpacityKF = new KeyFrame(Duration.millis(100), homePaneOpacityKV);
        KeyValue rootPaneKV = new KeyValue(feedbackPane.translateYProperty(), 0, new BounceInterpolator());
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
    private void handleReturnHomeButtonEvent(ActionEvent event) throws IOException {
        AnchorPane removablePane = (AnchorPane) homePagePane.getChildren().get(0);
        
        if (!homePagePane.getChildren().contains(homePane)) {
            homePagePane.getChildren().add(homePane);
        }

        KeyValue homePaneKV = null;
        KeyValue removablePaneKV = null;

        if (removablePane.getId().equals("settingAnchorPane")) {
            homePane.translateYProperty().set(homePagePane.getHeight());
            homePaneKV = new KeyValue(homePane.translateYProperty(), 0, new BounceInterpolator());
            removablePaneKV = new KeyValue(removablePane.translateYProperty(), -1 * mainPane.getHeight(), new BounceInterpolator());
        } else {
            homePane.translateXProperty().set(-1 * homePagePane.getWidth() / 4);
            homePaneKV = new KeyValue(homePane.translateXProperty(), 0, new BounceInterpolator());
            removablePaneKV = new KeyValue(removablePane.translateXProperty(), mainPane.getWidth(), new BounceInterpolator());
        }

        homePagePane.setTopAnchor(homePane, 0.0);
        homePagePane.setBottomAnchor(homePane, 0.0);
        homePagePane.setLeftAnchor(homePane, 0.0);
        homePagePane.setRightAnchor(homePane, 0.0);

        homePane.setOpacity(0);

        KeyFrame homePaneKF = new KeyFrame(Duration.millis(300), homePaneKV);
        KeyValue homePaneOpacityKV = new KeyValue(homePane.opacityProperty(), 1, Interpolator.EASE_IN);
        KeyFrame homePaneOpacityKF = new KeyFrame(Duration.millis(300), homePaneOpacityKV);
        KeyFrame rootPaneKF = new KeyFrame(Duration.millis(300), removablePaneKV);

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

    private void handleHoveredAndSelected () {
        returnHomeButton.hoverProperty().addListener((observable, oldValue, hovered) -> {
            if (hovered) {
                returnHomeText.getStyleClass().add("sideBarButtonLabel");
                homeImageView.setImage(Utils.getImage("home", Color.BLACK));
            } else {
                returnHomeText.getStyleClass().remove("sideBarButtonLabel");
                homeImageView.setImage(Utils.getImage("home", Color.WHITE));
            }
        });

        tutorialButton.hoverProperty().addListener((observable, oldValue, hovered) -> {
            if (hovered) {
                tutorialText.getStyleClass().add("sideBarButtonLabel");
                tutorialImageView.setImage(Utils.getImage("tutorial", Color.BLACK));
            } else {
                tutorialText.getStyleClass().remove("sideBarButtonLabel");
                tutorialImageView.setImage(Utils.getImage("tutorial", Color.WHITE));
            }
        });
        
        settingButton.hoverProperty().addListener((observable, oldValue, hovered) -> {
            if (hovered) {
                settingText.getStyleClass().add("sideBarButtonLabel");
                settingImageView.setImage(Utils.getImage("setting", Color.BLACK));
            } else {
                settingText.getStyleClass().remove("sideBarButtonLabel");
                settingImageView.setImage(Utils.getImage("setting", Color.WHITE));
            }
        });
        
        feedbackButton.hoverProperty().addListener((observable, oldValue, hovered) -> {
            if (hovered) {
                feedbackText.getStyleClass().add("sideBarButtonLabel");
                feedbackImageView.setImage(Utils.getImage("feedback", Color.BLACK));
            } else {
                feedbackText.getStyleClass().remove("sideBarButtonLabel");
                feedbackImageView.setImage(Utils.getImage("feedback", Color.WHITE));
            }
        });
    }
}
