/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.neverbdneverw.focalors;

import com.neverbdneverw.focalors.Utilities.BounceInterpolator;
import com.neverbdneverw.focalors.Utilities.Utils;
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
import javafx.fxml.FXMLLoader;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author HUAWEI-Pc
 */
public class SettingsController extends ProcedureSwitchingPaneController implements Initializable {

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
    @FXML
    private AnchorPane settingAnchorPane;
    @FXML
    private HBox darkModePrefHBox;
    @FXML
    private AnchorPane slidingButton;
    @FXML
    private StackPane stackParent;
    @FXML
    private Region stateIndicator;
    @FXML
    private ImageView lightImageView;
    @FXML
    private ImageView darkImageView;
    @FXML
    private ToggleGroup resistorColor;
    @FXML
    private ToggleGroup capacitorColor;
    @FXML
    private ToggleGroup wireColor;
    @FXML
    private AnchorPane ceilButton;
    @FXML
    private StackPane stackParent1;
    @FXML
    private ImageView ceilNoImageView;
    @FXML
    private ImageView ceilYesImageView;
    @FXML
    private AnchorPane autosaveButton;
    @FXML
    private StackPane stackParent2;
    @FXML
    private ImageView autosaveNoImageView;
    @FXML
    private ImageView autoSaveYesImageView;
    @FXML
    private ToggleGroup amplifierPref;
    @FXML
    private HBox darkModePrefHBox1;
    @FXML
    private AnchorPane estimateResistorValuesButton;
    @FXML
    private StackPane stackParent3;
    @FXML
    private ImageView lightImageView1;
    @FXML
    private ImageView darkImageView1;
    @FXML
    private HBox darkModePrefHBox11;
    @FXML
    private AnchorPane estimateCapacitorValuesButton;
    @FXML
    private StackPane stackParent31;
    @FXML
    private ImageView lightImageView11;
    @FXML
    private ImageView darkImageView11;
    @FXML
    private ToggleGroup decimalPlacesGroup;
    @FXML
    private ToggleGroup resistorUnitsGroup;
    @FXML
    private ToggleGroup capacitorUnitsGroup;
    @FXML
    private Region estimateResistorIndicator;
    @FXML
    private Region estimateCapacitorIndicator;
    @FXML
    private Region ceilStateIndicator;
    @FXML
    private Region autosaveIndicator;
    @FXML
    private ToggleGroup settingNavigationGroup;

    private int activePaneIndex;
    private ArrayList<AnchorPane> settingPanes = new ArrayList<AnchorPane>();
    private ArrayList<ToggleButton> toggleButtons = new ArrayList<ToggleButton>();
    private boolean darkModeState = false;
    private boolean ceilState = false;
    private boolean autoSaveState = false;
    private boolean estimateResistorState = false;
    private boolean estimateCapacitorState = false;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        settingAnchorPane.setId("settingAnchorPane");
        
        settingPanes.add(appearancePane);
        settingPanes.add(behaviorPane);
        settingPanes.add(accuracyPane);
        settingPanes.add(estimationPane);
        
        toggleButtons.add(appearanceButton);
        toggleButtons.add(behaviorButton);
        toggleButtons.add(accuracyButton);
        toggleButtons.add(estimateButton);
        
        this.setPaneName("Settings");
    }

    @FXML
    private void handleAppearanceButton(ActionEvent event) {
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
        double oldPaneStart, oldPaneEnd, newPaneStart, newPaneEnd, oldPaneOpacityStart, oldPaneOpacityEnd, newPaneOpacityStart, newPaneOpacityEnd;
        double oldPaneSwitchingSpeed, newPaneSwitchingSpeed;
        if (settingPanes.indexOf(pane) == activePaneIndex) {
            settingNavigationGroup.selectToggle(toggleButtons.get(settingPanes.indexOf(pane)));
            return;
        } else if (settingPanes.indexOf(pane) < activePaneIndex) {
            oldPaneStart = newPaneEnd = newPaneOpacityStart = oldPaneOpacityEnd = 0;
            oldPaneEnd = settingPane.getWidth();
            newPaneStart = -1 * settingPane.getWidth() / 4;
            oldPaneOpacityStart = newPaneOpacityEnd = 1;
            oldPaneSwitchingSpeed = 200;
            newPaneSwitchingSpeed = 600;
        } else {
            oldPaneStart = newPaneEnd = oldPaneOpacityEnd = newPaneOpacityStart = 0;
            oldPaneEnd = -1 * settingPane.getWidth() / 4;
            newPaneStart = settingPane.getWidth();
            oldPaneOpacityStart = newPaneOpacityEnd = 1;
            oldPaneSwitchingSpeed = 100;
            newPaneSwitchingSpeed = 200;
            
        }
        
        AnchorPane oldPane = settingPanes.get(activePaneIndex);
        oldPane.translateXProperty().set(oldPaneStart);
        oldPane.opacityProperty().set(oldPaneOpacityStart);
        
        pane.setVisible(true);
        pane.translateXProperty().set(newPaneStart);
        pane.opacityProperty().set(newPaneOpacityStart);

        KeyValue oldPaneKV = new KeyValue(oldPane.translateXProperty(), oldPaneEnd, new BounceInterpolator());
        KeyFrame oldPaneKF = new KeyFrame(Duration.millis(500), oldPaneKV);
        KeyValue oldPaneOpacityKV = new KeyValue(oldPane.opacityProperty(), oldPaneOpacityEnd, Interpolator.EASE_IN);
        KeyFrame oldPaneOpacityKF = new KeyFrame(Duration.millis(oldPaneSwitchingSpeed), oldPaneOpacityKV);
        KeyValue newPaneKV = new KeyValue(pane.translateXProperty(), newPaneEnd, new BounceInterpolator());
        KeyFrame newPaneKF = new KeyFrame(Duration.millis(500), newPaneKV);
        KeyValue newPaneOpacityKV = new KeyValue(pane.opacityProperty(), newPaneOpacityEnd, Interpolator.EASE_IN);
        KeyFrame newPaneOpacityKF = new KeyFrame(Duration.millis(newPaneSwitchingSpeed), newPaneOpacityKV);
        
        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(oldPaneKF);
        timeline.getKeyFrames().add(oldPaneOpacityKF);
        timeline.getKeyFrames().add(newPaneKF);
        timeline.getKeyFrames().add(newPaneOpacityKF);
        
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
    
    @FXML
    private void handleDarkModePrefClicked(MouseEvent event) {
        KeyValue switchKV = null;

        if (darkModeState) {
            switchKV = new KeyValue(stateIndicator.translateXProperty(), 0, new BounceInterpolator());
            lightImageView.setImage(Utils.getImage("sun", Color.rgb(0, 102, 204)));
            darkImageView.setImage(Utils.getImage("moon", Color.BLACK));
            darkModeState = false;
        } else {
            switchKV = new KeyValue(stateIndicator.translateXProperty(), 32, new BounceInterpolator());
            lightImageView.setImage(Utils.getImage("sun", Color.BLACK));
            darkImageView.setImage(Utils.getImage("moon", Color.rgb(0, 102, 204)));
            darkModeState = true;
        }
        
        App.setCSS(darkModeState);
        
        KeyFrame switchKF = new KeyFrame(Duration.millis(250), switchKV);
        
        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(switchKF);
        timeline.play();
    }

    @FXML
    private void handleCeilButtonClicked(MouseEvent event) {
        KeyValue switchKV = null;

        if (ceilState) {
            switchKV = new KeyValue(ceilStateIndicator.translateXProperty(), 0, new BounceInterpolator());
            ceilState = false;
        } else {
            switchKV = new KeyValue(ceilStateIndicator.translateXProperty(), 32, new BounceInterpolator());
            ceilState = true;
        }
        
        KeyFrame switchKF = new KeyFrame(Duration.millis(250), switchKV);
        
        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(switchKF);
        timeline.play();
    }

    @FXML
    private void handleAutoSaveButtonClicked(MouseEvent event) {
        KeyValue switchKV = null;

        if (autoSaveState) {
            switchKV = new KeyValue(autosaveIndicator.translateXProperty(), 0, new BounceInterpolator());
            autoSaveState = false;
        } else {
            switchKV = new KeyValue(autosaveIndicator.translateXProperty(), 32, new BounceInterpolator());
            autoSaveState = true;
        }
        
        KeyFrame switchKF = new KeyFrame(Duration.millis(250), switchKV);
        
        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(switchKF);
        timeline.play();
    }

    @FXML
    private void handleEstimateResistorClicked(MouseEvent event) {
        KeyValue switchKV = null;

        if (estimateResistorState) {
            switchKV = new KeyValue(estimateResistorIndicator.translateXProperty(), 0, new BounceInterpolator());
            estimateResistorState = false;
        } else {
            switchKV = new KeyValue(estimateResistorIndicator.translateXProperty(), 32, new BounceInterpolator());
            estimateResistorState = true;
        }
        
        KeyFrame switchKF = new KeyFrame(Duration.millis(250), switchKV);
        
        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(switchKF);
        timeline.play();
    }

    @FXML
    private void handleEstimateCapacitorClicked(MouseEvent event) {
        KeyValue switchKV = null;

        if (estimateCapacitorState) {
            switchKV = new KeyValue(estimateCapacitorIndicator.translateXProperty(), 0, new BounceInterpolator());
            estimateCapacitorState = false;
        } else {
            switchKV = new KeyValue(estimateCapacitorIndicator.translateXProperty(), 32, new BounceInterpolator());
            estimateCapacitorState = true;
        }
        
        KeyFrame switchKF = new KeyFrame(Duration.millis(250), switchKV);
        
        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(switchKF);
        timeline.play();
    }
}
