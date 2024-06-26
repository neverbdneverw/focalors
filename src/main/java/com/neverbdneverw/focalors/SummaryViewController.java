/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.neverbdneverw.focalors;

import com.neverbdneverw.focalors.AmplificationProcessors.AmplificationProcessor;
import com.neverbdneverw.focalors.Utilities.Utils;
import com.neverbdneverw.focalors.AmplificationProcessors.Processors;
import com.neverbdneverw.focalors.AmplificationProcessors.OpAmpAmplificationProcessor;
import com.neverbdneverw.focalors.AmplificationProcessors.OpAmpAmplificationProcessor.OpAmpType;
import com.neverbdneverw.focalors.Components.BJTComponents;
import com.neverbdneverw.focalors.Components.Components;
import com.neverbdneverw.focalors.Components.FETComponents;
import com.neverbdneverw.focalors.Components.OpAmpComponents;
import com.neverbdneverw.focalors.Utilities.Utils.Direction;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.prefs.Preferences;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author HUAWEI-Pc
 */
public class SummaryViewController extends ProcedureSwitchingPaneController implements Initializable {

    @FXML
    private AnchorPane summaryViewPane;
    @FXML
    private Button returnToFreqResButton;
    
    private AnchorPane homePagePane;
    @FXML
    private ImageView previousImageView;
    @FXML
    private ImageView nextImageView;
    @FXML
    private Button saveButton;
    @FXML
    private ListView<String> componentsList;
    @FXML
    private Pane circuitPane;
    @FXML
    private ImageView circuitImageView;
    
    private Preferences settings;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        settings = App.getPreferences();
        previousImageView.setImage(Utils.getImage("back", Color.WHITE));
        nextImageView.setImage(Utils.getImage("save", Color.WHITE));
        
        componentsList.setCellFactory(param -> new ListCell<String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                    setStyle(null);
                } else {
                    if (item.startsWith("R")) {
                        setText(item);
                        setStyle("-fx-background-color: " + settings.get("resistorColor", "") + ";");
                    } else if (item.startsWith("C")) {
                        setText(item);
                        setStyle("-fx-background-color: " + settings.get("capacitorColor", "") + ";");
                    } else {
                        setText(item);
                    }
                }
            }
        });
        
        Utils.buttonAddHoverEffect(saveButton);
        Utils.buttonAddHoverEffect(returnToFreqResButton);
        
        this.setPaneName("Summary");
        
        summaryViewPane.translateXProperty().addListener((ob, old, nw) -> {
            if (nw.doubleValue() == 00) {
                AmplificationProcessor processor = Processors.getActiveProcessor("");
                showComponentsView(processor.getComponents());
            }
        });
        
        circuitImageView.fitWidthProperty().bind(circuitPane.widthProperty());
        circuitImageView.fitHeightProperty().bind(circuitPane.heightProperty());
    }    

    @FXML
    private void handleReturnToFreqResButton(ActionEvent event) throws IOException {
        AnchorPane frequencyResponsePane = (AnchorPane) App.loadFXML("frequencyResponseOptions");
        
        homePagePane = (AnchorPane) summaryViewPane.getParent();
        
        switchPane(homePagePane, summaryViewPane, frequencyResponsePane, Direction.BACKWARD);
    }

    private void showComponentsView(Components components) {
        if (components.getType().equals("OpAmpComponents")) {
            OpAmpComponents opAmpComponents = (OpAmpComponents) components;
            opAmpComponents.updateSummary(componentsList, circuitImageView);
        } else if (components.getType().equals("BJTComponents")) {
            BJTComponents bjtComponents = (BJTComponents) components;
            bjtComponents.updateSummary(componentsList, circuitImageView);
        } else if (components.getType().equals("FETComponents")) {
            FETComponents fetComponents = (FETComponents) components;
            fetComponents.updateSummary(componentsList, circuitImageView);
        }
    }
}
