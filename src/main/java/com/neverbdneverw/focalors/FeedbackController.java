/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.neverbdneverw.focalors;

import com.neverbdneverw.focalors.Utilities.BounceInterpolator;
import com.neverbdneverw.focalors.Utilities.Utils;
import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.util.Duration;
/**
 * FXML Controller class
 *
 * @author HUAWEI-Pc
 */
public class FeedbackController extends NavigationSwitchingPaneController implements Initializable {

    @FXML
    private AnchorPane feedbackPane;
    @FXML
    private Button giveFeedbackButton;
    @FXML
    private StackPane feedBackSwitcherPane;
    @FXML
    private AnchorPane secondFeedbackPane;
    @FXML
    private AnchorPane firstFeedbackPane;
    @FXML
    private Label emailLinkText;
    @FXML
    private ToggleGroup ratingGroup;
    @FXML
    private TextField emailTF;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        feedbackPane.setId("feedbackAnchorPane");
        Utils.buttonAddHoverEffect(giveFeedbackButton);
        
        this.setPaneName("Feedback");
    }
    
    @FXML
    private void submit(ActionEvent event) {
        if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
            // windows
            Desktop desktop = Desktop.getDesktop();
            try {
                desktop.browse(URI.create("https://docs.google.com/forms/d/e/1FAIpQLScxnZkBG8cOEOA242T9hbgpdvhuNccBYGL8fEzsizJCF-O4iw/viewform?usp=pp_url&entry.303980503=" + 
                        ((ToggleButton) ratingGroup.getSelectedToggle()).getText() + "&entry.1473404077=AMAZING&entry.366478589=" + emailTF.getText()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    private void handleGiveFeedbackButtonClicked(ActionEvent event) {
        secondFeedbackPane.setVisible(true);
        secondFeedbackPane.translateXProperty().set(feedbackPane.getWidth());

        KeyValue secFePaneKV = new KeyValue(secondFeedbackPane.translateXProperty(), 0, new BounceInterpolator());
        KeyFrame secFePaneKF = new KeyFrame(Duration.millis(300), secFePaneKV);
        KeyValue fiFePaneKV = new KeyValue(firstFeedbackPane.translateXProperty(), -1 * feedbackPane.getWidth() / 4, new BounceInterpolator());
        KeyFrame fiFePaneKF = new KeyFrame(Duration.millis(300), fiFePaneKV);
        KeyValue fiFePaneOpKV = new KeyValue(firstFeedbackPane.opacityProperty(), 0, new BounceInterpolator());
        KeyFrame fiFePaneOpKF = new KeyFrame(Duration.millis(50), fiFePaneOpKV);

        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(secFePaneKF);
        timeline.getKeyFrames().add(fiFePaneKF);
        timeline.getKeyFrames().add(fiFePaneOpKF);
        timeline.play();
        
        timeline.setOnFinished(eh -> {
            firstFeedbackPane.setVisible(false);
        });
    }
}
