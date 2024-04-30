/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.neverbdneverw.focalors;

import javafx.scene.image.Image;
import javafx.scene.image.PixelReader;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;

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
}
