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
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;
/**
 *
 * @author HUAWEI-Pc
 */
public class FETOptionsController {
    @FXML
    private Button returnToMainQueueButton;
    
    @FXML
    private AnchorPane fetOptionsPane;
    
    private AnchorPane mainQueuePane;
    private AnchorPane homePagePane;
    
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
            mainQueuePane.translateXProperty().set(-1 * homePagePane.getWidth());
            homePagePane.setTopAnchor(mainQueuePane, 0.0);
            homePagePane.setBottomAnchor(mainQueuePane, 0.0);
            homePagePane.setLeftAnchor(mainQueuePane, 0.0);
            homePagePane.setRightAnchor(mainQueuePane, 0.0);


            KeyValue homePaneKV = new KeyValue(fetOptionsPane.translateXProperty(), fetOptionsPane.getWidth(), new BounceInterpolator());
            KeyFrame homePaneKF = new KeyFrame(Duration.millis(300), homePaneKV);
            KeyValue rootPaneKV = new KeyValue(mainQueuePane.translateXProperty(), 0, new BounceInterpolator());
            KeyFrame rootPaneKF = new KeyFrame(Duration.millis(300), rootPaneKV);

            Timeline timeline = new Timeline();
            timeline.getKeyFrames().add(homePaneKF);
            timeline.getKeyFrames().add(rootPaneKF);

            timeline.setOnFinished((e) -> {
                homePagePane.getChildren().remove(fetOptionsPane);
            });

            timeline.play();
        }
    }
}
