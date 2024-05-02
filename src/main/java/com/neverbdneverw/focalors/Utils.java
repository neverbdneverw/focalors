/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.neverbdneverw.focalors;

import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.util.Duration;

/**
 *
 * @author HUAWEI-Pc
 */
public class Utils {
    public static Image getImage(String iconName, Color color) {
        Image img = new Image(App.class.getResource("/icons/" + iconName + ".png").toExternalForm());
        PixelReader reader = img.getPixelReader();
        int width = (int) img.getWidth();
        int height = (int) img.getHeight();
                
        WritableImage output = new WritableImage(width, height);
        PixelWriter writer = output.getPixelWriter();
        
        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                int argb = reader.getArgb(x, y);
                int a = (argb >> 24) & 0xFF;
                
                int r = (int) (color.getRed() * 255);
                int g = (int) (color.getGreen() * 255);
                int b = (int) (color.getBlue() * 255);
                
                argb = (a << 24) | (r << 16) | (g << 8) | b;
                
                writer.setArgb(x, y, argb);
            }
        }
        
        return output;
    }
    
    public static void buttonAddHoverEffect(Button button) {
        button.hoverProperty().addListener((a, b, hovered) -> {
            if (hovered) {
                KeyValue buttonHoverXKV = new KeyValue(button.scaleXProperty(), 1.125, new BounceInterpolator());
                KeyFrame buttonHoverXKF = new KeyFrame(Duration.millis(300), buttonHoverXKV);
                KeyValue buttonHoverYKV = new KeyValue(button.scaleYProperty(), 1.125, new BounceInterpolator());
                KeyFrame buttonHoverYKF = new KeyFrame(Duration.millis(300), buttonHoverYKV);
                
                Timeline timeline = new Timeline();
                timeline.getKeyFrames().add(buttonHoverXKF);
                timeline.getKeyFrames().add(buttonHoverYKF);
                timeline.play();
            } else {
                KeyValue buttonHoverXKV = new KeyValue(button.scaleXProperty(), 1, new BounceInterpolator());
                KeyFrame buttonHoverXKF = new KeyFrame(Duration.millis(300), buttonHoverXKV);
                KeyValue buttonHoverYKV = new KeyValue(button.scaleYProperty(), 1, new BounceInterpolator());
                KeyFrame buttonHoverYKF = new KeyFrame(Duration.millis(300), buttonHoverYKV);
                
                Timeline timeline = new Timeline();
                timeline.getKeyFrames().add(buttonHoverXKF);
                timeline.getKeyFrames().add(buttonHoverYKF);
                timeline.play();
            }
        });
    }
}
