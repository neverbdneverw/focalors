package com.neverbdneverw.focalors;

import com.neverbdneverw.focalors.Utils.Direction;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.beans.property.DoubleProperty;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

public class PrimaryController implements Initializable {

    @FXML
    private ToggleButton returnHomeButton;
    @FXML
    private ToggleButton settingButton;
    @FXML
    private ToggleButton tutorialButton;
    @FXML
    private ToggleButton feedbackButton;
    @FXML
    private Pane imagePane;
    @FXML
    private AnchorPane sideBarPane;
    @FXML
    private AnchorPane homePagePane;
    @FXML
    private AnchorPane mainPane;
    @FXML
    private HBox mainHBox;
    @FXML
    private AnchorPane homePane;
    @FXML
    private ImageView homeImageView;
    @FXML
    private ImageView tutorialImageView;
    @FXML
    private ImageView settingImageView;
    @FXML
    private ImageView feedbackImageView;
    
    private AnchorPane mainQueuePane;
    private VBox sideBox;
    @FXML
    private Button primaryButton;
    @FXML
    private ImageView startImageView;
    @FXML
    private Label tutorialText;
    @FXML
    private Label settingText;
    @FXML
    private Label feedbackText;
    @FXML
    private Label returnHomeText;
    
    private AnchorPane activePane;
    @FXML
    private ToggleButton aboutButton;
    @FXML
    private ImageView aboutImageView;
    @FXML
    private Label aboutText;
    @FXML
    private ToggleGroup sideBarButtons;
    @FXML
    private VBox sideBarButtonBox;
    
    private ProcedureSwitchingPaneController procedureController;
    private NavigationSwitchingPaneController navigationController;
    
    private AnchorPane[] mainPages;
    
    private int oldSidebarIndex = 0;
    private int newSidebarIndex = 0;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            this.mainPages = new AnchorPane[]{homePane, (AnchorPane) App.loadFXML("tutorial"), (AnchorPane) App.loadFXML("settings"), (AnchorPane) App.loadFXML("feedback"), (AnchorPane) App.loadFXML("aboutApp")};
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        procedureController = new ProcedureSwitchingPaneController();
        navigationController = new NavigationSwitchingPaneController();
        
        sideBox = (VBox) sideBarPane.getChildren().get(0);
        
        homePane.setId("homePane");
        
        homeImageView.setImage(Utils.getImage("home", Color.WHITE));
        tutorialImageView.setImage(Utils.getImage("tutorial", Color.WHITE));
        settingImageView.setImage(Utils.getImage("setting", Color.WHITE));
        feedbackImageView.setImage(Utils.getImage("feedback", Color.WHITE));
        aboutImageView.setImage(Utils.getImage("about", Color.WHITE));
        
        startImageView.setImage(Utils.getImage("start", Color.WHITE));
        
        handleHoveredAndSelected();
        
        Utils.buttonAddHoverEffect(primaryButton);
        
        activePane = homePane;
        
        sideBarButtons.selectedToggleProperty().addListener((observer, oldButton, newButton) -> {
            if (oldButton != null && newButton == null) {
                oldSidebarIndex = newSidebarIndex = sideBarButtonBox.getChildren().indexOf(oldButton);
            } else if (oldButton == null && newButton != null) {
                int newButtonIndex = sideBarButtonBox.getChildren().indexOf(newButton);
                
                if (oldSidebarIndex != newButtonIndex) {
                    newSidebarIndex = newButtonIndex;
                }
            } else if (oldButton == null && newButton == null) {
                System.out.println("it apparently happens :<");
            } else {
                oldSidebarIndex = sideBarButtonBox.getChildren().indexOf(oldButton);
                newSidebarIndex = sideBarButtonBox.getChildren().indexOf(newButton);
            }
            
            if (oldSidebarIndex != newSidebarIndex) {
                if (newSidebarIndex > oldSidebarIndex) {
                    navigationController.switchPane(homePagePane, mainPages[oldSidebarIndex], mainPages[newSidebarIndex], Direction.DOWNWARD);
                } else {
                    navigationController.switchPane(homePagePane, mainPages[oldSidebarIndex], mainPages[newSidebarIndex], Direction.UPWARD);
                }
            }
        });
    }
    
    @FXML
    private void switchToMainQueue() throws IOException {
        mainQueuePane = (AnchorPane) App.loadFXML("mainqueue");
        
        procedureController.switchPane(homePagePane, homePane, mainQueuePane, Direction.FORWARD);
    }
    
    @FXML
    private void handleReturnHomeButtonEvent(ActionEvent event) throws IOException {
        AnchorPane removablePane = (AnchorPane) homePagePane.getChildren().get(0);
        
        if (!Arrays.asList(mainPages).contains(removablePane)) {
            procedureController.switchPane(homePagePane, removablePane, homePane, Direction.BACKWARD);
        }
    }
    
    @FXML
    private void handleTutorialButtonEvent(ActionEvent event) throws IOException {
        handleReturnHomeButtonEvent(event);
    }
    
    @FXML
    private void handleSettingButtonEvent(ActionEvent event) throws IOException {
        handleReturnHomeButtonEvent(event);
    }

    @FXML
    private void handleFeedbackButtonEvent(ActionEvent event) throws IOException {
        handleReturnHomeButtonEvent(event);
    }
    
    @FXML
    private void handleAboutButtonEvent(ActionEvent event) throws IOException {
        handleReturnHomeButtonEvent(event);
    }

    private void handleHoveredAndSelected () {
        returnHomeButton.hoverProperty().addListener((observable, oldValue, hovered) -> {
            if (hovered) {
                returnHomeText.getStyleClass().add("sideBarButtonLabel");
                returnHomeButton.getStyleClass().add("sideBarButtonHighlight");
                homeImageView.setImage(Utils.getImage("home", Color.BLACK));
            } else {
                returnHomeText.getStyleClass().remove("sideBarButtonLabel");
                returnHomeButton.getStyleClass().remove("sideBarButtonHighlight");
                homeImageView.setImage(Utils.getImage("home", Color.WHITE));
            }
        });

        tutorialButton.hoverProperty().addListener((observable, oldValue, hovered) -> {
            if (hovered) {
                tutorialText.getStyleClass().add("sideBarButtonLabel");
                tutorialButton.getStyleClass().add("sideBarButtonHighlight");
                tutorialImageView.setImage(Utils.getImage("tutorial", Color.BLACK));
            } else {
                tutorialText.getStyleClass().remove("sideBarButtonLabel");
                tutorialButton.getStyleClass().remove("sideBarButtonHighlight");
                tutorialImageView.setImage(Utils.getImage("tutorial", Color.WHITE));
            }
        });
//        
        settingButton.hoverProperty().addListener((observable, oldValue, hovered) -> {
            if (hovered) {
                settingText.getStyleClass().add("sideBarButtonLabel");
                settingButton.getStyleClass().add("sideBarButtonHighlight");
                settingImageView.setImage(Utils.getImage("setting", Color.BLACK));
            } else {
                settingText.getStyleClass().remove("sideBarButtonLabel");
                settingButton.getStyleClass().remove("sideBarButtonHighlight");
                settingImageView.setImage(Utils.getImage("setting", Color.WHITE));
            }
        });

        feedbackButton.hoverProperty().addListener((observable, oldValue, hovered) -> {
            if (hovered) {
                feedbackText.getStyleClass().add("sideBarButtonLabel");
                feedbackButton.getStyleClass().add("sideBarButtonHighlight");
                feedbackImageView.setImage(Utils.getImage("feedback", Color.BLACK));
            } else {
                feedbackText.getStyleClass().remove("sideBarButtonLabel");
                feedbackButton.getStyleClass().remove("sideBarButtonHighlight");
                feedbackImageView.setImage(Utils.getImage("feedback", Color.WHITE));
            }
        });
        
        aboutButton.hoverProperty().addListener((observable, oldValue, hovered) -> {
            if (hovered) {
                aboutText.getStyleClass().add("sideBarButtonLabel");
                aboutButton.getStyleClass().add("sideBarButtonHighlight");
                aboutImageView.setImage(Utils.getImage("about", Color.BLACK));
            } else {
                aboutText.getStyleClass().remove("sideBarButtonLabel");
                aboutButton.getStyleClass().remove("sideBarButtonHighlight");
                aboutImageView.setImage(Utils.getImage("about", Color.WHITE));
            }
        });
    }
}
