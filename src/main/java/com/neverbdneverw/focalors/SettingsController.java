/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.neverbdneverw.focalors;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author HUAWEI-Pc
 */
public class SettingsController implements Initializable {

    @FXML
    private ToggleButton appearanceButton;
    @FXML
    private ToggleButton behaviorButton;
    @FXML
    private ToggleButton accuracyButton;
    @FXML
    private ToggleButton estimateButton;
    @FXML
    private StackPane settingPane;
    @FXML
    private AnchorPane appearancePane;
    @FXML
    private AnchorPane behaviorPane;
    @FXML
    private AnchorPane accuracyPane;
    @FXML
    private AnchorPane estimationPane;
    
    private ToggleGroup toggleGroup;
    private int activePaneIndex;
    private ArrayList<AnchorPane> settingPanes = new ArrayList<AnchorPane>();
    private ArrayList<ToggleButton> toggleButtons = new ArrayList<ToggleButton>();
    @FXML
    private AnchorPane settingAnchorPane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        settingAnchorPane.setId("settingAnchorPane");
        toggleGroup = new ToggleGroup();
        this.appearanceButton.setToggleGroup(toggleGroup);
        this.behaviorButton.setToggleGroup(toggleGroup);
        this.accuracyButton.setToggleGroup(toggleGroup);
        this.estimateButton.setToggleGroup(toggleGroup);
        toggleGroup.selectToggle(this.appearanceButton);
        
        settingPanes.add(appearancePane);
        settingPanes.add(behaviorPane);
        settingPanes.add(accuracyPane);
        settingPanes.add(estimationPane);
        
        toggleButtons.add(appearanceButton);
        toggleButtons.add(behaviorButton);
        toggleButtons.add(accuracyButton);
        toggleButtons.add(estimateButton);
    }

    @FXML
    private void handleAppearanceButton(ActionEvent event) {
//        view.setImage(getImage(Color.rgb(0, 204, 102)));
        animatePaneSwitching(appearancePane);
    }

    @FXML
    private void handleBehaviorButton(ActionEvent event) {
        animatePaneSwitching(behaviorPane);
    }

    @FXML
    private void handleAccuracyButton(ActionEvent event) {
        animatePaneSwitching(accuracyPane);
    }

    @FXML
    private void handleEstimateButton(ActionEvent event) {
        animatePaneSwitching(estimationPane);
    }
    
    private void animatePaneSwitching(AnchorPane pane) {
        double oldPaneStart, oldPaneEnd, newPaneStart, newPaneEnd;
        if (settingPanes.indexOf(pane) == activePaneIndex) {
            toggleGroup.selectToggle(toggleButtons.get(settingPanes.indexOf(pane)));
            return;
        } else if (settingPanes.indexOf(pane) < activePaneIndex) {
            oldPaneStart = newPaneEnd = 0;
            oldPaneEnd = settingPane.getWidth();
            newPaneStart = -1 * settingPane.getWidth();
        } else {
            oldPaneStart = newPaneEnd = 0;
            oldPaneEnd = -1 * settingPane.getWidth();
            newPaneStart = settingPane.getWidth();
        }
        
        AnchorPane oldPane = settingPanes.get(activePaneIndex);
        oldPane.translateXProperty().set(oldPaneStart);
        
        pane.setVisible(true);
        pane.translateXProperty().set(newPaneStart);

        KeyValue oldPaneKV = new KeyValue(oldPane.translateXProperty(), oldPaneEnd, new BounceInterpolator());
        KeyFrame oldPaneKF = new KeyFrame(Duration.millis(500), oldPaneKV);
        KeyValue newPaneKV = new KeyValue(pane.translateXProperty(), newPaneEnd, new BounceInterpolator());
        KeyFrame newPaneKF = new KeyFrame(Duration.millis(500), newPaneKV);
        
        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(oldPaneKF);
        timeline.getKeyFrames().add(newPaneKF);
        
        timeline.setOnFinished(t -> {
            settingPanes.forEach((settingPane) -> {
                if (settingPane != pane) {
                    settingPane.setVisible(false);
                } else {
                    activePaneIndex = settingPanes.indexOf(settingPane);
                }
            });
        });
        timeline.play();
    }
}
