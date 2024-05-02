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
public class OpAmpOptionsController implements Initializable {
    @FXML
    private Button returnToMainQueueButton;
    @FXML
    private AnchorPane opAmpOptionsPane;
    @FXML
    private ImageView previousImageView;
    @FXML
    private ImageView nextImageView;
    @FXML
    private Button opAmpToInputsButton;
    
    private AnchorPane mainQueuePane;
    private AnchorPane homePagePane;
    private AnchorPane inputsPane;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        previousImageView.setImage(Utils.getImage("back", Color.WHITE));
        nextImageView.setImage(Utils.getImage("next", Color.WHITE));
        
        Utils.buttonAddHoverEffect(opAmpToInputsButton);
        Utils.buttonAddHoverEffect(returnToMainQueueButton);
    }
    
    @FXML
    private void handleReturnToMainQueueButton (ActionEvent event) {
        if (event.getSource().equals(returnToMainQueueButton)) {
            try {
                mainQueuePane = (AnchorPane) App.loadFXML("mainqueue");
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            
            homePagePane = (AnchorPane) opAmpOptionsPane.getParent();
            homePagePane.getChildren().add(mainQueuePane);

            mainQueuePane.translateXProperty().set(-1 * homePagePane.getWidth() / 4);
            mainQueuePane.setOpacity(0);

            homePagePane.setTopAnchor(mainQueuePane, 0.0);
            homePagePane.setBottomAnchor(mainQueuePane, 0.0);
            homePagePane.setLeftAnchor(mainQueuePane, 0.0);
            homePagePane.setRightAnchor(mainQueuePane, 0.0);

            KeyValue opAmpOptionsPaneKV = new KeyValue(opAmpOptionsPane.translateXProperty(), opAmpOptionsPane.getWidth(), new BounceInterpolator());
            KeyFrame opAmpOptionsPaneKF = new KeyFrame(Duration.millis(300), opAmpOptionsPaneKV);
            KeyValue mainQueuePaneKV = new KeyValue(mainQueuePane.translateXProperty(), 0, new BounceInterpolator());
            KeyFrame mainQueuePaneKF = new KeyFrame(Duration.millis(300), mainQueuePaneKV);
            KeyValue mainQueuePaneOpacityKV = new KeyValue(mainQueuePane.opacityProperty(), 1, Interpolator.EASE_IN);
            KeyFrame mainQueuePaneOpacityKF = new KeyFrame(Duration.millis(300), mainQueuePaneOpacityKV);

            Timeline timeline = new Timeline();
            timeline.getKeyFrames().add(opAmpOptionsPaneKF);
            timeline.getKeyFrames().add(mainQueuePaneKF);
            timeline.getKeyFrames().add(mainQueuePaneOpacityKF);

            timeline.setOnFinished((e) -> {
                homePagePane.getChildren().remove(opAmpOptionsPane);
            });

            timeline.play();
        }
    }

    @FXML
    private void handleOpAmpToInputsButton(ActionEvent event) throws IOException {
        inputsPane = (AnchorPane) App.loadFXML("inputOptions");
        
        homePagePane = (AnchorPane) opAmpOptionsPane.getParent();
        homePagePane.getChildren().add(inputsPane);
        inputsPane.translateXProperty().set(homePagePane.getWidth());
        homePagePane.setTopAnchor(inputsPane, 0.0);
        homePagePane.setBottomAnchor(inputsPane, 0.0);
        homePagePane.setLeftAnchor(inputsPane, 0.0);
        homePagePane.setRightAnchor(inputsPane, 0.0);

        KeyValue opAmpOptionsPaneKV = new KeyValue(opAmpOptionsPane.translateXProperty(), -1 * opAmpOptionsPane.getWidth(), new BounceInterpolator());
        KeyFrame opAmpOptionsPaneKF = new KeyFrame(Duration.millis(300), opAmpOptionsPaneKV);
        KeyValue opAmpOptionsPaneOpacityKV = new KeyValue(opAmpOptionsPane.opacityProperty(), 0, Interpolator.EASE_IN);
        KeyFrame opAmpOptionsPaneOpacityKF = new KeyFrame(Duration.millis(50), opAmpOptionsPaneOpacityKV);
        KeyValue inputsPaneKV = new KeyValue(inputsPane.translateXProperty(), 0, new BounceInterpolator());
        KeyFrame inputsPaneKF = new KeyFrame(Duration.millis(300), inputsPaneKV);

        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(opAmpOptionsPaneKF);
        timeline.getKeyFrames().add(inputsPaneKF);
        timeline.getKeyFrames().add(opAmpOptionsPaneOpacityKF);

        timeline.setOnFinished((e) -> {
            homePagePane.getChildren().remove(opAmpOptionsPane);
        });

        timeline.play();
    }
}
