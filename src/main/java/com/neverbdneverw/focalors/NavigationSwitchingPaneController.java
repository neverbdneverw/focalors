/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.neverbdneverw.focalors;

import com.neverbdneverw.focalors.Utilities.Utils;
import javafx.animation.Interpolator;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

/**
 *
 * @author HUAWEI-Pc
 */
public class NavigationSwitchingPaneController extends BaseController {

    @Override
    public void switchPane(AnchorPane parent, AnchorPane fromPane, AnchorPane toPane, Utils.Direction direction) {
        if (!parent.getChildren().contains(toPane)) {
            parent.getChildren().add(toPane);
        }
        
        KeyValue fromPaneKV = null;
        
        if (direction.equals(Utils.Direction.UPWARD)) {
            toPane.translateYProperty().set(-1 * parent.getHeight());
            fromPaneKV = new KeyValue(fromPane.translateYProperty(), parent.getHeight(), Interpolator.EASE_IN);
        } else if (direction.equals(Utils.Direction.DOWNWARD)) {
            toPane.translateYProperty().set(parent.getHeight());
            fromPaneKV = new KeyValue(fromPane.translateYProperty(), -1 * parent.getHeight(), Interpolator.EASE_IN);
        } else {
            return;
        }

        parent.setTopAnchor(toPane, 0.0);
        parent.setBottomAnchor(toPane, 0.0);
        parent.setLeftAnchor(toPane, 0.0);
        parent.setRightAnchor(toPane, 0.0);

        KeyFrame fromPaneKF = new KeyFrame(Duration.millis(300), fromPaneKV);
        KeyValue toPaneKV = new KeyValue(toPane.translateYProperty(), 0, Interpolator.EASE_IN);
        KeyFrame toPaneKF = new KeyFrame(Duration.millis(300), toPaneKV);

        Timeline timeline = new Timeline();
        timeline.getKeyFrames().add(fromPaneKF);
        timeline.getKeyFrames().add(toPaneKF);

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
