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
import java.util.Properties;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.Toggle;
import javafx.util.Duration;
import java.util.prefs.Preferences;

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
    
    private Preferences settings;

    private int activePaneIndex;
    private ArrayList<AnchorPane> settingPanes = new ArrayList<AnchorPane>();
    private ArrayList<ToggleButton> toggleButtons = new ArrayList<ToggleButton>();
    private ArrayList<String> amplifierPreference = new ArrayList<String>();
    private boolean darkModeState;
    private boolean ceilState;
    /**
     * Initializes the controller class.
     */
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        settings = App.getPreferences();
        settingAnchorPane.setId("settingAnchorPane");
        
        settingPanes.add(appearancePane);
        settingPanes.add(behaviorPane);
        settingPanes.add(accuracyPane);
        
        toggleButtons.add(appearanceButton);
        toggleButtons.add(behaviorButton);
        toggleButtons.add(accuracyButton);
        
        handleSavedSettings();
        
        this.setPaneName("Settings");
        
        resistorColor.selectedToggleProperty().addListener((ob, old, newValue) -> {
            if (newValue != null) {
                String color = ((ToggleButton) newValue).getStyle().split(" ")[1].replace(";", "");
                settings.put("resistorColor", color);
            }
        });
        
        capacitorColor.selectedToggleProperty().addListener((ob, old, newValue) -> {
            if (newValue != null) {
                String color = ((ToggleButton) newValue).getStyle().split(" ")[1].replace(";", "");
                settings.put("capacitorColor", color);
            }
        });
        
        amplifierPref.selectedToggleProperty().addListener((ob, old, newValue) -> {
            if (newValue != null) {
                settings.put("preferredAmp", ((ToggleButton) newValue).getText().toLowerCase().strip());
            }
        });
        
        decimalPlacesGroup.selectedToggleProperty().addListener((ob, old, newValue) -> {
            if (newValue != null) {
                settings.put("decimalPlaces", ((ToggleButton) newValue).getText());
            }
        });
        
        resistorUnitsGroup.selectedToggleProperty().addListener((ob, old, newValue) -> {
            if (newValue != null) {
                settings.put("resistanceUnit", ((ToggleButton) newValue).getText());
            }
        });
        
        capacitorUnitsGroup.selectedToggleProperty().addListener((ob, old, newValue) -> {
            if (newValue != null) {
                settings.put("capacitanceUnit", ((ToggleButton) newValue).getText());
            }
        });
    }
    
    public void handleSavedSettings() {
        ArrayList<String> resistorColors = new ArrayList<String>();
        resistorColors.add("#0066cc");
        resistorColors.add("#cb6ce6");
        resistorColors.add("#ff66c4");
        resistorColors.add("#5ce1e6");
        resistorColors.add("#c1ff72");
        resistorColors.add("#ffde59");
        resistorColors.add("#ffbd59");
        
        ArrayList<String> capacitorColors = new ArrayList<String>();
        capacitorColors.add("#ff3131");
        capacitorColors.add("#ff914d");
        capacitorColors.add("#ffbd59");
        capacitorColors.add("#ffde59");
        capacitorColors.add("#c1ff72");
        capacitorColors.add("#5ce1e6");
        capacitorColors.add("#ff66c4");
        
        amplifierPreference = new ArrayList<String>();
        amplifierPreference.add("ask always");
        amplifierPreference.add("bjt");
        amplifierPreference.add("mosfet");
        amplifierPreference.add("op amp");
        
        ArrayList<String> decimalPlaces = new ArrayList<String>();
        decimalPlaces.add("0");
        decimalPlaces.add("0.1");
        decimalPlaces.add("0.01");
        decimalPlaces.add("0.001");
        decimalPlaces.add("0.0001");
        
        ArrayList<String> resistanceUnits = new ArrayList<String>();
        resistanceUnits.add("Ω");
        resistanceUnits.add("kΩ");
        resistanceUnits.add("MΩ");
        
        ArrayList<String> capacitanceUnits = new ArrayList<String>();
        capacitanceUnits.add("pF");
        capacitanceUnits.add("nF");
        capacitanceUnits.add("μF");
        capacitanceUnits.add("mF");
        capacitanceUnits.add("F");
        
        settingAnchorPane.translateYProperty().addListener((ob, o, n) -> {
            if (n.doubleValue() == 0) {
                String resistor = settings.get("resistorColor", "");
                resistorColor.selectToggle(resistorColor.getToggles().get(resistorColors.indexOf(resistor)));

                String capacitor = settings.get("capacitorColor", "");
                capacitorColor.selectToggle(capacitorColor.getToggles().get(capacitorColors.indexOf(capacitor)));
                
                darkModeState = settings.getBoolean("darkMode", false);
                this.handleDarkModePrefClicked(null);
                
                String ampPref = settings.get("preferredAmp", "askalways");
                amplifierPref.selectToggle(amplifierPref.getToggles().get(amplifierPreference.indexOf(ampPref)));
                
                ceilState = settings.getBoolean("alwaysCeil", false);
                this.handleCeilButtonClicked(null);
                
                String decimalPlace = settings.get("decimalPlaces", "0.01");
                decimalPlacesGroup.selectToggle(decimalPlacesGroup.getToggles().get(decimalPlaces.indexOf(decimalPlace)));
                
                String resistanceUnit = settings.get("resistanceUnit", "Ω");
                resistorUnitsGroup.selectToggle(resistorUnitsGroup.getToggles().get(resistanceUnits.indexOf(resistanceUnit)));
                
                String capacitanceUnit = settings.get("capacitanceUnit", "μF");
                capacitorUnitsGroup.selectToggle(capacitorUnitsGroup.getToggles().get(capacitanceUnits.indexOf(capacitanceUnit)));
            }
        });
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
        
        if (event != null) {
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
            
            settings.putBoolean("darkMode", darkModeState);
        } else {
            if (!darkModeState) {
                switchKV = new KeyValue(stateIndicator.translateXProperty(), 0, new BounceInterpolator());
                lightImageView.setImage(Utils.getImage("sun", Color.rgb(0, 102, 204)));
                darkImageView.setImage(Utils.getImage("moon", Color.BLACK));
            } else {
                switchKV = new KeyValue(stateIndicator.translateXProperty(), 32, new BounceInterpolator());
                lightImageView.setImage(Utils.getImage("sun", Color.BLACK));
                darkImageView.setImage(Utils.getImage("moon", Color.rgb(0, 102, 204)));
            }
        }
        
        KeyFrame switchKF = new KeyFrame(Duration.millis(250), switchKV);
        
        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(switchKF);
        timeline.play();
    }

    @FXML
    private void handleCeilButtonClicked(MouseEvent event) {
        KeyValue switchKV = null;
        
        if (event != null) {
            if (ceilState) {
                switchKV = new KeyValue(ceilStateIndicator.translateXProperty(), 0, new BounceInterpolator());
                ceilState = false;
            } else {
                switchKV = new KeyValue(ceilStateIndicator.translateXProperty(), 32, new BounceInterpolator());
                ceilState = true;
            }
            
            settings.putBoolean("alwaysCeil", ceilState);
        } else {
            if (!ceilState) {
                switchKV = new KeyValue(ceilStateIndicator.translateXProperty(), 0, new BounceInterpolator());
            } else {
                switchKV = new KeyValue(ceilStateIndicator.translateXProperty(), 32, new BounceInterpolator());
            }
        }
        
        KeyFrame switchKF = new KeyFrame(Duration.millis(250), switchKV);
        
        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(switchKF);
        timeline.play();
    }
}
