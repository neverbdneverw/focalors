/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.neverbdneverw.focalors;

import javafx.scene.control.Button;
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
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;
/**
 *
 * @author HUAWEI-Pc
 */
public class FETOptionsController implements Initializable {
    @FXML
    private Button returnToMainQueueButton;
    @FXML
    private AnchorPane fetOptionsPane;
    @FXML
    private ImageView previousImageView;
    @FXML
    private ImageView nextImageView;
    @FXML
    private Button fetToInputsButton;
    
    private AnchorPane mainQueuePane;
    private AnchorPane homePagePane;
    private AnchorPane inputsPane;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        previousImageView.setImage(Utils.getImage("back", Color.WHITE));
        nextImageView.setImage(Utils.getImage("next", Color.WHITE));
    }
    
    @FXML
    private void handleReturnToMainQueueButton (ActionEvent event) {
        if (event.getSource().equals(returnToMainQueueButton)) {
            try {
                mainQueuePane = (AnchorPane) App.loadFXML("mainqueue");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            
            homePagePane = (AnchorPane) fetOptionsPane.getParent();
            homePagePane.getChildren().add(mainQueuePane);
            
            mainQueuePane.translateXProperty().set(-1 * homePagePane.getWidth() / 4);
            mainQueuePane.setOpacity(0);
            
            homePagePane.setTopAnchor(mainQueuePane, 0.0);
            homePagePane.setBottomAnchor(mainQueuePane, 0.0);
            homePagePane.setLeftAnchor(mainQueuePane, 0.0);
            homePagePane.setRightAnchor(mainQueuePane, 0.0);

            KeyValue fetOptionsPaneKV = new KeyValue(fetOptionsPane.translateXProperty(), fetOptionsPane.getWidth(), new BounceInterpolator());
            KeyFrame fetOptionsPaneKF = new KeyFrame(Duration.millis(300), fetOptionsPaneKV);
            KeyValue mainQueuePaneKV = new KeyValue(mainQueuePane.translateXProperty(), 0, new BounceInterpolator());
            KeyFrame mainQueuePaneKF = new KeyFrame(Duration.millis(300), mainQueuePaneKV);
            KeyValue mainQueuePaneOpacityKV = new KeyValue(mainQueuePane.opacityProperty(), 1, Interpolator.EASE_IN);
            KeyFrame mainQueuePaneOpacityKF = new KeyFrame(Duration.millis(300), mainQueuePaneOpacityKV);

            Timeline timeline = new Timeline();
            timeline.getKeyFrames().add(fetOptionsPaneKF);
            timeline.getKeyFrames().add(mainQueuePaneKF);
            timeline.getKeyFrames().add(mainQueuePaneOpacityKF);

            timeline.setOnFinished((e) -> {
                homePagePane.getChildren().remove(fetOptionsPane);
            });

            timeline.play();
        }
    }

    @FXML
    private void handleFETToInputsButton(ActionEvent event) throws IOException {
        inputsPane = (AnchorPane) App.loadFXML("inputOptions");
        
        homePagePane = (AnchorPane) fetOptionsPane.getParent();
        homePagePane.getChildren().add(inputsPane);
        inputsPane.translateXProperty().set(homePagePane.getWidth());
        homePagePane.setTopAnchor(inputsPane, 0.0);
        homePagePane.setBottomAnchor(inputsPane, 0.0);
        homePagePane.setLeftAnchor(inputsPane, 0.0);
        homePagePane.setRightAnchor(inputsPane, 0.0);

        KeyValue fetOptionsPaneKV = new KeyValue(fetOptionsPane.translateXProperty(), -1 * fetOptionsPane.getWidth(), new BounceInterpolator());
        KeyFrame fetOptionsPaneKF = new KeyFrame(Duration.millis(300), fetOptionsPaneKV);
        KeyValue fetOptionsPaneOpacityKV = new KeyValue(fetOptionsPane.opacityProperty(), 0, Interpolator.EASE_IN);
        KeyFrame fetOptionsPaneOpacityKF = new KeyFrame(Duration.millis(50), fetOptionsPaneOpacityKV);
        KeyValue inputsPaneKV = new KeyValue(inputsPane.translateXProperty(), 0, new BounceInterpolator());
        KeyFrame inputsPaneKF = new KeyFrame(Duration.millis(300), inputsPaneKV);

        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(fetOptionsPaneKF);
        timeline.getKeyFrames().add(inputsPaneKF);
        timeline.getKeyFrames().add(fetOptionsPaneOpacityKF);

        timeline.setOnFinished((e) -> {
            homePagePane.getChildren().remove(fetOptionsPane);
        });

        timeline.play();
    }
}
