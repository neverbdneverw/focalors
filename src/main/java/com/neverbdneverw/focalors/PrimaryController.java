package com.neverbdneverw.focalors;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public class PrimaryController {

    @FXML
    private Button primaryButton;
    @FXML
    private Button settingButton;
    @FXML
    private Button tutorialButton;
    @FXML
    private Button feedbackButton;
    @FXML
    private Pane imagePane;

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }

    @FXML
    private void handleSettingButtonEvent(ActionEvent event) throws IOException {
        if (event.getSource().equals(settingButton)) {
            App.setRoot("settings");
        }
    }

    @FXML
    private void handleTutorialButtonEvent(ActionEvent event) throws IOException {
        if (event.getSource().equals(tutorialButton)) {
            App.setRoot("tutorial");
        }
    }

    @FXML
    private void handleFeedbackButtonEvent(ActionEvent event) throws IOException {
        if (event.getSource().equals(feedbackButton)) {
            App.setRoot("feedback");
        }
    }
}
