/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.neverbdneverw.focalors.AmplificationProcessors;

import com.neverbdneverw.focalors.Components.Components;

/**
 *
 * @author HUAWEI-Pc
 */

public abstract class AmplificationProcessor {
    public double voltageGain;
    public double inputFrequency;
    public double peakToPeakSignalVoltage;
    public double biasingVoltage;
    public double lowCutoffFrequency;
    public double highCutoffFrequency;
    public abstract String getType();
    public abstract Components getComponents();
    public abstract String setInputParameters(double inputFrequency, double peakToPeakInputVoltage, double sourceVoltage);
    public abstract String setFrequencyResponse(double lowCutoffFrequency, double highCutoffFrequency);
}
