/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.neverbdneverw.focalors.AmplificationProcessors;

import com.neverbdneverw.focalors.AmplificationProcessors.AmplificationProcessor;
import com.neverbdneverw.focalors.Components.BJTComponents;
import com.neverbdneverw.focalors.Components.Components;
import java.util.Dictionary;
import java.util.Hashtable;

/**
 *
 * @author HUAWEI-Pc
 */
public class BJTAmplificationProcessor extends AmplificationProcessor {
    private double collectorCurrent;
    private double hfe;
    
    @Override
    public String getType() {
        return "bjt";
    }
    
    public void setDesiredOutput(double voltageGain, double collectorCurrent, double hfe) {
        this.voltageGain = voltageGain;
        this.collectorCurrent = collectorCurrent;
        this.hfe = hfe;
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
        if (highCutoffFrequency != 0) {
            System.out.println("Where'd you get this thing?");
        }
        
        this.lowCutoffFrequency = lowCutoffFrequency;
        
        return "Success";
    }

    public Components getComponents() {
        BJTComponents components = new BJTComponents();
        components.setParameters(this.voltageGain, this.collectorCurrent, this.hfe, this.inputFrequency, this.peakToPeakSignalVoltage, this.biasingVoltage, this.lowCutoffFrequency);
        return components;
    }
}
