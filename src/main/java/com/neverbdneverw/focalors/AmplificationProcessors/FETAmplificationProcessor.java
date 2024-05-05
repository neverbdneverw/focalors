/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.neverbdneverw.focalors.AmplificationProcessors;

import com.neverbdneverw.focalors.Components.BJTComponents;
import com.neverbdneverw.focalors.Components.Components;
import com.neverbdneverw.focalors.Components.FETComponents;

/**
 *
 * @author HUAWEI-Pc
 */
public class FETAmplificationProcessor extends AmplificationProcessor {
    private double transconductanceParameter;
    private double thresholdVoltage;
    
    @Override
    public String getType() {
        return "fet";
    }
    
    public void setDesiredOutput(double voltageGain, double transconductanceParameter, double thresholdVoltage) {
        this.voltageGain = voltageGain;
        this.transconductanceParameter = transconductanceParameter;
        this.thresholdVoltage = thresholdVoltage;
    }
    
    @Override
    public String setInputParameters(double inputFrequency, double peakToPeakInputVoltage, double sourceVoltage) {
        if (peakToPeakInputVoltage > sourceVoltage) {
            return "The biasing voltage cannot be lower than the input voltage.";
        } else if ((peakToPeakInputVoltage * voltageGain) > sourceVoltage) {
            return "The source voltage is too low to achieve the desired gain.";
        }
        
        this.inputFrequency = inputFrequency;
        this.peakToPeakSignalVoltage = peakToPeakInputVoltage;
        this.biasingVoltage = sourceVoltage;
        
        return "Success.";
    }
    
    @Override
    public String setFrequencyResponse(double lowCutoffFrequency, double highCutoffFrequency) {
        if (highCutoffFrequency != 1) {
            System.out.println("Where'd you even get this thing?");
        }
        
        this.lowCutoffFrequency = lowCutoffFrequency;
        
        return "Success";
    }

    public Components getComponents() {
        FETComponents components = new FETComponents();
        components.setParameters(this.voltageGain, this.thresholdVoltage, this.transconductanceParameter, this.inputFrequency, this.peakToPeakSignalVoltage, this.biasingVoltage, this.lowCutoffFrequency);
        return components;
    }
}
