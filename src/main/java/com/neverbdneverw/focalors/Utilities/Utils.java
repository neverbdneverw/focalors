/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.neverbdneverw.focalors.Utilities;

import com.neverbdneverw.focalors.App;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Properties;
import java.util.prefs.Preferences;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBase;
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
    private static Properties propertiesInstance;
    public enum Direction {
        BACKWARD,
        FORWARD,
        UPWARD,
        DOWNWARD
    }
    
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
    
    public static void buttonAddHoverEffect(ButtonBase button) {
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
    
    public static boolean isNumeric(String str) { 
        try {  
          Double.parseDouble(str);  
          return true;
        } catch(NumberFormatException e){  
          return false;  
        }  
    }
    
    public static String cleanDouble (double value, String type) {
        Preferences settings = App.getPreferences();
        BigDecimal decimal = new BigDecimal(value);
        String precision = settings.get("decimalPlaces", "0.01");
        boolean ceil = settings.getBoolean("alwaysCeil", false);
        int scale = precision_to_scale(precision);
        
        String output = "";
        
        if (type.equals("r")) {
            String resistanceUnit = settings.get("resistanceUnit", "Ω");
            
            if (resistanceUnit.equals("kΩ")) {
                decimal = decimal.divide(new BigDecimal(1000));
            } else if (resistanceUnit.equals("MΩ")) {
                decimal = decimal.divide(new BigDecimal(1000000));
            }
            
            if (ceil) {
                decimal = decimal.setScale(scale, RoundingMode.CEILING);
            } else {
                decimal = decimal.setScale(scale, RoundingMode.HALF_UP);
            }
            
            output = decimal.toPlainString() + " " + resistanceUnit;
        } else if (type.equals("c")) {
            String capacitanceUnit = settings.get("capacitanceUnit", "uF");
            
            if (capacitanceUnit.equals("pF")) {
                decimal = decimal.multiply(new BigDecimal(1000000000));
                decimal = decimal.multiply(new BigDecimal(1000));
            } else if (capacitanceUnit.equals("nF")) {
                decimal = decimal.multiply(new BigDecimal(1000000000));
            } else if (capacitanceUnit.equals("uF")) {
                decimal = decimal.multiply(new BigDecimal(1000000));
            } else if (capacitanceUnit.equals("mF")) {
                decimal = decimal.multiply(new BigDecimal(1000));
            }
            
            if (ceil) {
                decimal = decimal.setScale(scale, RoundingMode.CEILING);
            } else {
                decimal = decimal.setScale(scale, RoundingMode.HALF_UP);
            }
            
            output = decimal.toPlainString() + " " + capacitanceUnit;
        }
        
        return output;
    }
    
    private static int precision_to_scale(String precision) {
        if (!precision.contains(".")) {
            return 0;
        }
        
        return precision.indexOf("1") - 1;
    }
}
