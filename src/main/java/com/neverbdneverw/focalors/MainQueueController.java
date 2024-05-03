package com.neverbdneverw.focalors;

import com.neverbdneverw.focalors.Utils.Direction;
import java.io.IOException;
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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.util.Duration;

public class MainQueueController extends ProcedureSwitchingPaneController implements Initializable {
    
    @FXML
    private Button bjtButton;
    @FXML
    private Button fetButton;
    @FXML
    private Button opAmpButton;
    
    @FXML
    private AnchorPane mainQueuePane;
    
    private AnchorPane bjtOptionsPane;
    private AnchorPane fetOptionsPane;
    private AnchorPane opAmpOptionsPane;
    private AnchorPane homePagePane;
    @FXML
    private BorderPane marginPane;
    @FXML
    private ImageView bjtImageView;
    @FXML
    private ImageView mosfetImageView;
    @FXML
    private ImageView opampImageView;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        bjtImageView.setImage(new Image(App.class.getResource("/icons/bjt.png").toExternalForm()));
        mosfetImageView.setImage(new Image(App.class.getResource("/icons/mosfet.png").toExternalForm()));
        opampImageView.setImage(new Image(App.class.getResource("/icons/opamp.png").toExternalForm()));
        
        Utils.buttonAddHoverEffect(bjtButton);
        Utils.buttonAddHoverEffect(fetButton);
        Utils.buttonAddHoverEffect(opAmpButton);
    }
    
    @FXML
    private void handleBJTChosenEvent (ActionEvent event) throws IOException {
        bjtOptionsPane = (AnchorPane) App.loadFXML("bjtOptions");

        homePagePane = (AnchorPane) mainQueuePane.getParent();
        switchPane(homePagePane, mainQueuePane, bjtOptionsPane, Direction.FORWARD);
    }
    
    @FXML
    private void handleFETChosenEvent (ActionEvent event) throws IOException {
        fetOptionsPane = (AnchorPane) App.loadFXML("fetOptions");

        homePagePane = (AnchorPane) mainQueuePane.getParent();
        switchPane(homePagePane, mainQueuePane, fetOptionsPane, Direction.FORWARD);
    }
    
    @FXML
    private void handleOpAmpChosenEvent (ActionEvent event) throws IOException {
        opAmpOptionsPane = (AnchorPane) App.loadFXML("opAmpOptions");

        homePagePane = (AnchorPane) mainQueuePane.getParent();
        switchPane(homePagePane, mainQueuePane, opAmpOptionsPane, Direction.FORWARD);
    }
}