package com.neverbdneverw.focalors;

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

public class MainQueueController implements Initializable {
    
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
        if (event.getSource().equals(bjtButton)) {
            bjtOptionsPane = (AnchorPane) App.loadFXML("bjtOptions");
            
            homePagePane = (AnchorPane) mainQueuePane.getParent();
            homePagePane.getChildren().add(bjtOptionsPane);
            bjtOptionsPane.translateXProperty().set(homePagePane.getWidth());
            homePagePane.setTopAnchor(bjtOptionsPane, 0.0);
            homePagePane.setBottomAnchor(bjtOptionsPane, 0.0);
            homePagePane.setLeftAnchor(bjtOptionsPane, 0.0);
            homePagePane.setRightAnchor(bjtOptionsPane, 0.0);

            KeyValue mainQueuePaneKV = new KeyValue(mainQueuePane.translateXProperty(), -1 * mainQueuePane.getWidth(), new BounceInterpolator());
            KeyFrame mainQueuePaneKF = new KeyFrame(Duration.millis(300), mainQueuePaneKV);
            KeyValue mainQueuePaneOpacityKV = new KeyValue(mainQueuePane.opacityProperty(), 0, Interpolator.EASE_IN);
            KeyFrame mainQueuePaneOpacityKF = new KeyFrame(Duration.millis(50), mainQueuePaneOpacityKV);
            KeyValue bjtOptionsPaneKV = new KeyValue(bjtOptionsPane.translateXProperty(), 0, new BounceInterpolator());
            KeyFrame bjtOptionsPaneKF = new KeyFrame(Duration.millis(300), bjtOptionsPaneKV);

            Timeline timeline = new Timeline();
            timeline.getKeyFrames().add(mainQueuePaneKF);
            timeline.getKeyFrames().add(bjtOptionsPaneKF);
            timeline.getKeyFrames().add(mainQueuePaneOpacityKF);

            timeline.setOnFinished((e) -> {
                homePagePane.getChildren().remove(mainQueuePane);
            });

            timeline.play();
        }
    }
    
    @FXML
    private void handleFETChosenEvent (ActionEvent event) throws IOException {
        if (event.getSource().equals(fetButton)) {
            fetOptionsPane = (AnchorPane) App.loadFXML("fetOptions");
            
            homePagePane = (AnchorPane) mainQueuePane.getParent();
            homePagePane.getChildren().add(fetOptionsPane);
            fetOptionsPane.translateXProperty().set(homePagePane.getWidth());
            homePagePane.setTopAnchor(fetOptionsPane, 0.0);
            homePagePane.setBottomAnchor(fetOptionsPane, 0.0);
            homePagePane.setLeftAnchor(fetOptionsPane, 0.0);
            homePagePane.setRightAnchor(fetOptionsPane, 0.0);

            KeyValue mainQueuePaneKV = new KeyValue(mainQueuePane.translateXProperty(), -1 * mainQueuePane.getWidth(), new BounceInterpolator());
            KeyFrame mainQueuePaneKF = new KeyFrame(Duration.millis(300), mainQueuePaneKV);
            KeyValue mainQueuePaneOpacityKV = new KeyValue(mainQueuePane.opacityProperty(), 0, Interpolator.EASE_IN);
            KeyFrame mainQueuePaneOpacityKF = new KeyFrame(Duration.millis(50), mainQueuePaneOpacityKV);
            KeyValue fetOptionsPaneKV = new KeyValue(fetOptionsPane.translateXProperty(), 0, new BounceInterpolator());
            KeyFrame fetOptionsPaneKF = new KeyFrame(Duration.millis(300), fetOptionsPaneKV);

            Timeline timeline = new Timeline();
            timeline.getKeyFrames().add(mainQueuePaneKF);
            timeline.getKeyFrames().add(fetOptionsPaneKF);
            timeline.getKeyFrames().add(mainQueuePaneOpacityKF);

            timeline.setOnFinished((e) -> {
                homePagePane.getChildren().remove(mainQueuePane);
            });

            timeline.play();
        }
    }
    
    @FXML
    private void handleOpAmpChosenEvent (ActionEvent event) throws IOException {
        if (event.getSource().equals(opAmpButton)) {
            opAmpOptionsPane = (AnchorPane) App.loadFXML("opAmpOptions");
            
            homePagePane = (AnchorPane) mainQueuePane.getParent();
            homePagePane.getChildren().add(opAmpOptionsPane);
            opAmpOptionsPane.translateXProperty().set(homePagePane.getWidth());
            homePagePane.setTopAnchor(opAmpOptionsPane, 0.0);
            homePagePane.setBottomAnchor(opAmpOptionsPane, 0.0);
            homePagePane.setLeftAnchor(opAmpOptionsPane, 0.0);
            homePagePane.setRightAnchor(opAmpOptionsPane, 0.0);

            KeyValue mainQueuePaneKV = new KeyValue(mainQueuePane.translateXProperty(), -1 * mainQueuePane.getWidth(), new BounceInterpolator());
            KeyFrame mainQueuePaneKF = new KeyFrame(Duration.millis(300), mainQueuePaneKV);
            KeyValue mainQueuePaneOpacityKV = new KeyValue(mainQueuePane.opacityProperty(), 0, Interpolator.EASE_IN);
            KeyFrame mainQueuePaneOpacityKF = new KeyFrame(Duration.millis(50), mainQueuePaneOpacityKV);
            KeyValue opAmpOptionsPaneKV = new KeyValue(opAmpOptionsPane.translateXProperty(), 0, new BounceInterpolator());
            KeyFrame opAmpOptionsPaneKF = new KeyFrame(Duration.millis(300), opAmpOptionsPaneKV);

            Timeline timeline = new Timeline();
            timeline.getKeyFrames().add(mainQueuePaneKF);
            timeline.getKeyFrames().add(opAmpOptionsPaneKF);
            timeline.getKeyFrames().add(mainQueuePaneOpacityKF);

            timeline.setOnFinished((e) -> {
                homePagePane.getChildren().remove(mainQueuePane);
            });

            timeline.play();
        }
    }
}