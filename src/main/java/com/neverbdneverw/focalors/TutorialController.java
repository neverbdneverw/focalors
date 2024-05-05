/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package com.neverbdneverw.focalors;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
/**
 * FXML Controller class
 *
 * @author HUAWEI-Pc
 */
public class TutorialController extends ProcedureSwitchingPaneController implements Initializable {

    @FXML
    private Button bjt_tutorial;
    
    @FXML
    private Button next1_bjt;
    
    @FXML
    private Button next2_bjt;
    
    @FXML
    private Button next3_bjt;
    
    @FXML
    private Button mosfet_tutorial;
    
    @FXML
    private Button next1_mos;
    
    @FXML
    private Button next2_mos;
    
    @FXML
    private Button next3_mos;
    
    @FXML
    private Button opamp_tutorial;
    
    @FXML
    private Button next1_opam;
    
    @FXML
    private Button next2_opam;
    
    @FXML
    private Button next3_opam;
    
    @FXML
    private AnchorPane bjt_pane;
    
    @FXML
    private AnchorPane front_bjt_pane;
    
    @FXML
    private AnchorPane dov_bjt_pane;
    
    @FXML
    private AnchorPane ip_bjt_pane;
    
    @FXML
    private AnchorPane mosfet_pane;
    
    @FXML
    private AnchorPane front_mos_pane;
    
    @FXML
    private AnchorPane dov_mos_pane;
    
    @FXML
    private AnchorPane ip_mos_pane;
   
    @FXML
    private AnchorPane opamp_pane;
    
    @FXML
    private AnchorPane front_opam_pane;
    
    @FXML
    private AnchorPane dov_opam_pane;
    
    @FXML
    private AnchorPane ip_opam_pane;
    
    @FXML
    private Button settingReturn;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.setPaneName("Tutorial");
        bjt_tutorial.setOnAction(this::switchToBJTPane);
        mosfet_tutorial.setOnAction(this::switchToMosfetPane);
        opamp_tutorial.setOnAction(this::switchToOpampPane);
        next1_bjt = (Button) bjt_pane.lookup("#next1_bjt");
        next1_bjt.setOnAction(this::switchToDOVBJTPane);
        next2_bjt = (Button) dov_bjt_pane.lookup("#next2_bjt");
        next2_bjt.setOnAction(this::switchToIPBJTPane);
        next3_bjt = (Button) ip_bjt_pane.lookup("#next3_bjt");
        next3_bjt.setOnAction(this::switchToFRBJTPane);
        next1_mos = (Button) mosfet_pane.lookup("#next1_mos");
        next1_mos.setOnAction(this::switchToDOVMOSPane);
        next2_mos = (Button) dov_mos_pane.lookup("#next2_mos");
        next2_mos.setOnAction(this::switchToIPMOSPane);
        next3_mos = (Button) ip_mos_pane.lookup("#next3_mos");
        next3_mos.setOnAction(this::switchToFRMOSPane);
        next1_opam = (Button) opamp_pane.lookup("#next1_opam");
        next1_opam.setOnAction(this::switchToDOVOPPane);
        next2_opam = (Button) dov_opam_pane.lookup("#next2_opam");
        next2_opam.setOnAction(this::switchToIPOPPane);
        next3_opam = (Button) ip_opam_pane.lookup("#next3_opam");
        next3_opam.setOnAction(this::switchToFROPPane);
    }    
    
    @FXML
    private void handleSettingReturn(ActionEvent event) throws IOException {
        App.setRoot("primary");      
    }
    @FXML
    private void switchToBJTPane(ActionEvent event) {
        bjt_pane.setVisible(true);
        if (!front_bjt_pane.isVisible()) {
            dov_bjt_pane.setVisible(true); 
            ip_bjt_pane.setVisible(true); 
            front_bjt_pane.setVisible(true);
        }
        front_bjt_pane.toFront();
    }
    @FXML
    private void switchToDOVBJTPane(ActionEvent event) {
    front_bjt_pane.setVisible(false); 
    dov_bjt_pane.toFront();
    }
    @FXML
    private void switchToIPBJTPane(ActionEvent event) {
    front_bjt_pane.setVisible(false); 
    dov_bjt_pane.setVisible(false); 
    ip_bjt_pane.toFront();
    }
    @FXML
    private void switchToFRBJTPane(ActionEvent event) {
    front_bjt_pane.setVisible(false); 
    dov_bjt_pane.setVisible(false); 
    ip_bjt_pane.setVisible(false); 
    }
    @FXML
    private void switchToMosfetPane(ActionEvent event) {
        bjt_pane.setVisible(false);
        mosfet_pane.setVisible(true);
        if (!front_mos_pane.isVisible()) {
            dov_mos_pane.setVisible(true); 
            ip_mos_pane.setVisible(true); 
            front_mos_pane.setVisible(true);
        }
        front_mos_pane.toFront();
    }
    @FXML
    private void switchToDOVMOSPane(ActionEvent event) {
    front_mos_pane.setVisible(false); 
    dov_mos_pane.toFront();
    }
    @FXML
    private void switchToIPMOSPane(ActionEvent event) {
    front_mos_pane.setVisible(false); 
    dov_mos_pane.setVisible(false); 
    ip_mos_pane.toFront();
    }
    @FXML
    private void switchToFRMOSPane(ActionEvent event) {
    front_mos_pane.setVisible(false); 
    dov_mos_pane.setVisible(false); 
    ip_mos_pane.setVisible(false); 
    }
    @FXML
    private void switchToOpampPane(ActionEvent event) {
        bjt_pane.setVisible(false);
        mosfet_pane.setVisible(false);
        opamp_pane.setVisible(true);
        if (!front_opam_pane.isVisible()) {
            dov_opam_pane.setVisible(true); 
            ip_opam_pane.setVisible(true); 
            front_opam_pane.setVisible(true);
        }
        front_opam_pane.toFront();
    }
    @FXML
    private void switchToDOVOPPane(ActionEvent event) {
    front_opam_pane.setVisible(false); 
    dov_opam_pane.toFront();
    }
    @FXML
    private void switchToIPOPPane(ActionEvent event) {
    front_opam_pane.setVisible(false); 
    dov_opam_pane.setVisible(false); 
    ip_opam_pane.toFront();
    }
    @FXML
    private void switchToFROPPane(ActionEvent event) {
    front_opam_pane.setVisible(false); 
    dov_opam_pane.setVisible(false); 
    ip_opam_pane.setVisible(false); 
    }  
}
