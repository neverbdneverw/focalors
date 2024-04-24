package com.neverbdneverw.focalors;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class SecondaryController {

    @FXML
    private Button secondaryButton;
    @FXML
    private Button settingButton;

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primary");
    }

    @FXML
    private void handleSettingButtonEvent(ActionEvent event) throws IOException {
        App.setRoot("settings");
    }
}