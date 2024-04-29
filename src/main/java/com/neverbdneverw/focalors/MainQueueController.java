package com.neverbdneverw.focalors;

import java.io.IOException;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

public class MainQueueController {
    
    @FXML
    private Button bjtButton;
    @FXML
    private Button fetButton;
    @FXML
    private Button opAmpButton;
    
    @FXML
    private AnchorPane mainQueuePane;
    
    private AnchorPane bjtOptionsPane;
    private AnchorPane fetOptionsPane;
    private AnchorPane opAmpOptionsPane;
    private AnchorPane homePagePane;
    
    @FXML
    private void handleBJTChosenEvent (ActionEvent event) throws IOException {
        if (event.getSource().equals(bjtButton)) {
            bjtOptionsPane = (AnchorPane) App.loadFXML("bjtOptions");
            
            homePagePane = (AnchorPane) mainQueuePane.getParent();
            homePagePane.getChildren().add(bjtOptionsPane);
            bjtOptionsPane.translateXProperty().set(homePagePane.getWidth());
            homePagePane.setTopAnchor(bjtOptionsPane, 0.0);
            homePagePane.setBottomAnchor(bjtOptionsPane, 0.0);
            homePagePane.setLeftAnchor(bjtOptionsPane, 0.0);
            homePagePane.setRightAnchor(bjtOptionsPane, 0.0);


            KeyValue homePaneKV = new KeyValue(mainQueuePane.translateXProperty(), -1 * mainQueuePane.getWidth(), new BounceInterpolator());
            KeyFrame homePaneKF = new KeyFrame(Duration.millis(300), homePaneKV);
            KeyValue rootPaneKV = new KeyValue(bjtOptionsPane.translateXProperty(), 0, new BounceInterpolator());
            KeyFrame rootPaneKF = new KeyFrame(Duration.millis(300), rootPaneKV);

            Timeline timeline = new Timeline();
            timeline.getKeyFrames().add(homePaneKF);
            timeline.getKeyFrames().add(rootPaneKF);

            timeline.setOnFinished((e) -> {
                homePagePane.getChildren().remove(mainQueuePane);
            });

            timeline.play();
        }
    }
    
    @FXML
    private void handleFETChosenEvent (ActionEvent event) throws IOException {
        if (event.getSource().equals(fetButton)) {
            fetOptionsPane = (AnchorPane) App.loadFXML("fetOptions");
            
            homePagePane = (AnchorPane) mainQueuePane.getParent();
            homePagePane.getChildren().add(fetOptionsPane);
            fetOptionsPane.translateXProperty().set(homePagePane.getWidth());
            homePagePane.setTopAnchor(fetOptionsPane, 0.0);
            homePagePane.setBottomAnchor(fetOptionsPane, 0.0);
            homePagePane.setLeftAnchor(fetOptionsPane, 0.0);
            homePagePane.setRightAnchor(fetOptionsPane, 0.0);


            KeyValue homePaneKV = new KeyValue(mainQueuePane.translateXProperty(), -1 * mainQueuePane.getWidth(), new BounceInterpolator());
            KeyFrame homePaneKF = new KeyFrame(Duration.millis(300), homePaneKV);
            KeyValue rootPaneKV = new KeyValue(fetOptionsPane.translateXProperty(), 0, new BounceInterpolator());
            KeyFrame rootPaneKF = new KeyFrame(Duration.millis(300), rootPaneKV);

            Timeline timeline = new Timeline();
            timeline.getKeyFrames().add(homePaneKF);
            timeline.getKeyFrames().add(rootPaneKF);

            timeline.setOnFinished((e) -> {
                homePagePane.getChildren().remove(mainQueuePane);
            });

            timeline.play();
        }
    }
    
    @FXML
    private void handleOpAmpChosenEvent (ActionEvent event) throws IOException {
        if (event.getSource().equals(opAmpButton)) {
            opAmpOptionsPane = (AnchorPane) App.loadFXML("opAmpOptions");
            
            homePagePane = (AnchorPane) mainQueuePane.getParent();
            homePagePane.getChildren().add(opAmpOptionsPane);
            opAmpOptionsPane.translateXProperty().set(homePagePane.getWidth());
            homePagePane.setTopAnchor(opAmpOptionsPane, 0.0);
            homePagePane.setBottomAnchor(opAmpOptionsPane, 0.0);
            homePagePane.setLeftAnchor(opAmpOptionsPane, 0.0);
            homePagePane.setRightAnchor(opAmpOptionsPane, 0.0);

            KeyValue homePaneKV = new KeyValue(mainQueuePane.translateXProperty(), -1 * mainQueuePane.getWidth(), new BounceInterpolator());
            KeyFrame homePaneKF = new KeyFrame(Duration.millis(300), homePaneKV);
            KeyValue rootPaneKV = new KeyValue(opAmpOptionsPane.translateXProperty(), 0, new BounceInterpolator());
            KeyFrame rootPaneKF = new KeyFrame(Duration.millis(300), rootPaneKV);

            Timeline timeline = new Timeline();
            timeline.getKeyFrames().add(homePaneKF);
            timeline.getKeyFrames().add(rootPaneKF);

            timeline.setOnFinished((e) -> {
                homePagePane.getChildren().remove(mainQueuePane);
            });

            timeline.play();
        }
    }
}