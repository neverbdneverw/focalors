package com.neverbdneverw.focalors;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class PrimaryController {

    @FXML
    private Button primaryButton;
    @FXML
    private Button settingButton;

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
}
