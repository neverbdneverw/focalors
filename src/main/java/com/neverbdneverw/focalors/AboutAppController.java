/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.neverbdneverw.focalors;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author HUAWEI-Pc
 */
public class AboutAppController extends ProcedureSwitchingPaneController implements Initializable {

    @FXML
    private AnchorPane aboutAppPane;
    @FXML
    private Label goToGithubButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        aboutAppPane.setId("aboutAppAnchorPane");
        
        setPaneName("About App");
    }

    @FXML
    private void goToGithub(ActionEvent event) {
        if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
            // windows
            Desktop desktop = Desktop.getDesktop();
            try {
                desktop.browse(URI.create("http://github.com/neverbdneverw/focalors"));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
