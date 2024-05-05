/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.neverbdneverw.focalors.Components;

import java.util.Dictionary;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;

/**
 *
 * @author HUAWEI-Pc
 */
public abstract class Components {
    private String type;
    public void setType(String typeName) {
        type = typeName;
    }
    
    public String getType() {
        return this.type;
    }

    public abstract void updateSummary(ListView<String> componentsList, ImageView circuitImageView);
}