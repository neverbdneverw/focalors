/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.neverbdneverw.focalors.AmplificationProcessors;

import com.neverbdneverw.focalors.AmplificationProcessors.AmplificationProcessor;
import com.neverbdneverw.focalors.Components.Components;
import com.neverbdneverw.focalors.Components.OpAmpComponents;
import java.util.Dictionary;
import java.util.Hashtable;

/**
 *
 * @author HUAWEI-Pc
 */
public class OpAmpAmplificationProcessor extends AmplificationProcessor {
    public OpAmpType amplificationType;

    @Override
    public String getType() {
        return "opamp";
    }
    
    public enum OpAmpType {
        NON_INVERTING,
        INVERTING
    }
    
    public void setDesiredOutput(double voltageGain, OpAmpType type) {
        this.voltageGain = voltageGain;
        this.amplificationType = type;
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
        if (highCutoffFrequency < lowCutoffFrequency) {
            return "The low cutoff frequency must obviously be lower than the high cutoff frequency";
        }
        
        this.lowCutoffFrequency = lowCutoffFrequency;
        this.highCutoffFrequency = highCutoffFrequency;
        
        return "Success";
    }

    public Components getComponents() {
        OpAmpComponents components = new OpAmpComponents();
        components.setParameters(this.amplificationType, this.voltageGain,
                this.peakToPeakSignalVoltage, this.biasingVoltage, this.inputFrequency, 
                this.lowCutoffFrequency, this.highCutoffFrequency);
        return components;
    }
}
