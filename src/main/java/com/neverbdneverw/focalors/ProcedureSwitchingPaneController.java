/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.neverbdneverw.focalors;

import com.neverbdneverw.focalors.Utilities.BounceInterpolator;
import com.neverbdneverw.focalors.Utilities.Utils.Direction;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author HUAWEI-Pc
 */
public class ProcedureSwitchingPaneController extends BaseController {

    @Override
    public void switchPane(AnchorPane parent, AnchorPane fromPane, AnchorPane toPane, Direction direction) {
        if (!parent.getChildren().contains(toPane)) {
            parent.getChildren().add(toPane);
        }
        
        KeyValue fromPaneKV = null;
        KeyFrame paneOpacityKF = null;
        
        if (direction.equals(Direction.BACKWARD)) {
            toPane.setOpacity(0);
            toPane.translateXProperty().set(-1 * parent.getWidth() / 4);
            fromPaneKV = new KeyValue(fromPane.translateXProperty(), parent.getWidth(), new BounceInterpolator());
            paneOpacityKF = new KeyFrame(Duration.millis(300), new KeyValue(toPane.opacityProperty(), 1, new BounceInterpolator()));
        } else if (direction.equals(Direction.FORWARD)) {
            toPane.translateXProperty().set(parent.getWidth());
            fromPaneKV = new KeyValue(fromPane.translateXProperty(), -1 * parent.getWidth(), new BounceInterpolator());
            paneOpacityKF = new KeyFrame(Duration.millis(50), new KeyValue(fromPane.opacityProperty(), 0, new BounceInterpolator()));
        } else {
            return;
        }

        parent.setTopAnchor(toPane, 0.0);
        parent.setBottomAnchor(toPane, 0.0);
        parent.setLeftAnchor(toPane, 0.0);
        parent.setRightAnchor(toPane, 0.0);

        KeyFrame fromPaneKF = new KeyFrame(Duration.millis(300), fromPaneKV);
        KeyValue toPaneKV = new KeyValue(toPane.translateXProperty(), 0, new BounceInterpolator());
        KeyFrame toPaneKF = new KeyFrame(Duration.millis(300), toPaneKV);

        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(fromPaneKF);
        timeline.getKeyFrames().add(toPaneKF);
        timeline.getKeyFrames().add(paneOpacityKF);

        timeline.setOnFinished((e) -> {
            parent.getChildren().remove(fromPane);
        });

        timeline.play();
    }

    @Override
    public void setPaneName(String name) {
        this.paneName = name;
    }
}
