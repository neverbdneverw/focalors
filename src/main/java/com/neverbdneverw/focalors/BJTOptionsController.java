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
public class BJTOptionsController implements Initializable {
    @FXML
    private Button returnToMainQueueButton;
    @FXML
    private AnchorPane bjtOptionsPane;
    @FXML
    private Button bjtToInputsButton;
    
    private AnchorPane mainQueuePane;
    private AnchorPane homePagePane;
    private AnchorPane inputsPane;
    @FXML
    private ImageView previousImageView;
    @FXML
    private ImageView nextImageView;
    
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
            
            homePagePane = (AnchorPane) bjtOptionsPane.getParent();
            homePagePane.getChildren().add(mainQueuePane);
            mainQueuePane.translateXProperty().set(-1 * homePagePane.getWidth());
            homePagePane.setTopAnchor(mainQueuePane, 0.0);
            homePagePane.setBottomAnchor(mainQueuePane, 0.0);
            homePagePane.setLeftAnchor(mainQueuePane, 0.0);
            homePagePane.setRightAnchor(mainQueuePane, 0.0);

            KeyValue homePaneKV = new KeyValue(bjtOptionsPane.translateXProperty(), bjtOptionsPane.getWidth(), new BounceInterpolator());
            KeyFrame homePaneKF = new KeyFrame(Duration.millis(300), homePaneKV);
            KeyValue rootPaneKV = new KeyValue(mainQueuePane.translateXProperty(), 0, new BounceInterpolator());
            KeyFrame rootPaneKF = new KeyFrame(Duration.millis(300), rootPaneKV);

            Timeline timeline = new Timeline();
            timeline.getKeyFrames().add(homePaneKF);
            timeline.getKeyFrames().add(rootPaneKF);

            timeline.setOnFinished((e) -> {
                homePagePane.getChildren().remove(bjtOptionsPane);
            });

            timeline.play();
        }
    }
    
    @FXML
    private void handleBJTToInputsButton (ActionEvent event) throws IOException {
        inputsPane = (AnchorPane) App.loadFXML("inputOptions");
        
        homePagePane = (AnchorPane) bjtOptionsPane.getParent();
        homePagePane.getChildren().add(inputsPane);
        inputsPane.translateXProperty().set(homePagePane.getWidth());
        homePagePane.setTopAnchor(inputsPane, 0.0);
        homePagePane.setBottomAnchor(inputsPane, 0.0);
        homePagePane.setLeftAnchor(inputsPane, 0.0);
        homePagePane.setRightAnchor(inputsPane, 0.0);

        KeyValue homePaneKV = new KeyValue(bjtOptionsPane.translateXProperty(), -1 * bjtOptionsPane.getWidth(), new BounceInterpolator());
        KeyFrame homePaneKF = new KeyFrame(Duration.millis(300), homePaneKV);
        KeyValue rootPaneKV = new KeyValue(inputsPane.translateXProperty(), 0, new BounceInterpolator());
        KeyFrame rootPaneKF = new KeyFrame(Duration.millis(300), rootPaneKV);

        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(homePaneKF);
        timeline.getKeyFrames().add(rootPaneKF);

        timeline.setOnFinished((e) -> {
            homePagePane.getChildren().remove(bjtOptionsPane);
        });

        timeline.play();
    }
}
