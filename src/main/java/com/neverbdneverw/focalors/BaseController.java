/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.neverbdneverw.focalors;

import com.neverbdneverw.focalors.Utils.Direction;
import javafx.scene.layout.AnchorPane;

/**
 *
 * @author HUAWEI-Pc
 */

public abstract class BaseController {
    public String paneName;
    public abstract void switchPane(AnchorPane parent, AnchorPane fromPane, AnchorPane toPane, Direction direction);
    public abstract void setPaneName(String name);
}
