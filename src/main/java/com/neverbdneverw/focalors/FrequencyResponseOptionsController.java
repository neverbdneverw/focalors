/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.neverbdneverw.focalors;

import com.neverbdneverw.focalors.Utilities.Utils;
import com.neverbdneverw.focalors.AmplificationProcessors.Processors;
import com.neverbdneverw.focalors.AmplificationProcessors.AmplificationProcessor;
import com.neverbdneverw.focalors.Utilities.Utils.Direction;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author HUAWEI-Pc
 */
public class FrequencyResponseOptionsController extends ProcedureSwitchingPaneController implements Initializable {

    @FXML
    private Button returnToInputsButton;
    @FXML
    private Button toSummaryViewButton;
    
    private AnchorPane homePagePane;
    @FXML
    private AnchorPane frequencyResponsePane;
    @FXML
    private ImageView previousImageView;
    @FXML
    private ImageView nextImageView;
    @FXML
    private Slider lowCutoffSlider;
    @FXML
    private Slider highCutoffSlider;
    @FXML
    private Label lowCutoffLabel;
    @FXML
    private Label highCutoffLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        previousImageView.setImage(Utils.getImage("back", Color.WHITE));
        nextImageView.setImage(Utils.getImage("next", Color.WHITE));
        
        Utils.buttonAddHoverEffect(toSummaryViewButton);
        Utils.buttonAddHoverEffect(returnToInputsButton);
        
        this.setPaneName("Frequency Response Options");
        
        ChangeListener listener = new ChangeListener<Double>() {
            @Override
            public void changed(ObservableValue<? extends Double> observable, Double oldValue, Double newValue) {
                XYChart.Series series = new XYChart.Series(); 
                series.setName("Desired Bode Plot"); 
                if (lowCutoffSlider.getValue() == highCutoffSlider.getValue() ||
                        lowCutoffSlider.getValue() > highCutoffSlider.getValue()) {
                    toSummaryViewButton.setDisable(true);
                } else {
                    toSummaryViewButton.setDisable(false);
                }
                
                lowCutoffLabel.setText(String.valueOf((int) lowCutoffSlider.getValue()) + " Hz");
                highCutoffLabel.setText(String.valueOf((int) highCutoffSlider.getValue()) + " Hz");
            }
        };
        
        frequencyResponsePane.translateXProperty().addListener((ob, old, nw) -> {
            if (nw.doubleValue() == 00) {
                toSummaryViewButton.setDisable(true);
            }
        });
        
        lowCutoffSlider.valueProperty().addListener(listener);
        highCutoffSlider.valueProperty().addListener(listener);
    }    

    @FXML
    private void handleReturnToInputsButton(ActionEvent event) throws IOException {
        AnchorPane inputsPane = (AnchorPane) App.loadFXML("inputOptions");
        homePagePane = (AnchorPane) frequencyResponsePane.getParent();
        
        switchPane(homePagePane, frequencyResponsePane, inputsPane, Direction.BACKWARD);
    }

    @FXML
    private void handleToSummaryViewButton(ActionEvent event) throws IOException {
        AnchorPane summaryViewPane = (AnchorPane) App.loadFXML("summaryView");
        homePagePane = (AnchorPane) frequencyResponsePane.getParent();
        
        double lowCutoffFrequency = lowCutoffSlider.getValue();
        double highCutoffFrequency = highCutoffSlider.getValue();
        
        AmplificationProcessor processor = Processors.getActiveProcessor("");
        processor.setFrequencyResponse(lowCutoffFrequency, highCutoffFrequency);
        
        switchPane(homePagePane, frequencyResponsePane, summaryViewPane, Direction.FORWARD);
    }
}
