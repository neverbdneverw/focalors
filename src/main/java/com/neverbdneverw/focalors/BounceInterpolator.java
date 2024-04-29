/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.neverbdneverw.focalors;

import javafx.animation.Interpolator;

/**
 *
 * @author HUAWEI-Pc
 */
public class BounceInterpolator extends Interpolator {

    private double val(double t, double sx, double ex, double maxVal) {
        double v = (t - sx) * (ex - t);
        double max = (ex - sx) / 2;
        return maxVal * v / (max * max);
    }

    @Override
    protected double curve(double t) {
        double x;
        if (t <= 0.37) {
            x = val(t, -0.37, 0.37, 1);
        } else if (t <= 0.73) {
            x = val(t, 0.37, 0.73, 0.25);
        } else if (t <= 0.91) {
            x = val(t, 0.73, 0.91, 0.08);
        } else {
            x = val(t, 0.91, 1, 0.03);
        }
        return 1 - x;
    }
    
}
