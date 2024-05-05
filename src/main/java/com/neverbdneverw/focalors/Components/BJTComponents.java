/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.neverbdneverw.focalors.Components;

import com.neverbdneverw.focalors.Components.Components;
import com.neverbdneverw.focalors.AmplificationProcessors.OpAmpAmplificationProcessor;
import com.neverbdneverw.focalors.AmplificationProcessors.OpAmpAmplificationProcessor.OpAmpType;
import com.neverbdneverw.focalors.App;
import java.math.BigDecimal;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author HUAWEI-Pc
 */
public class BJTComponents extends Components {
    private double resistorR1;
    private double resistorR2;
    private double resistorRC;
    private double resistorRE;
    private double capacitorInput;
    private double capacitorOutput;
    private double capacitorBypass;
    
    private double signalVoltage;
    private double biasingVoltage;
    private double inputFrequency;
    
    private double acEmitterResistance;
    
    static final double COLLECTOR_EMITTER_SATURATION_VOLTAGE = 0.2;
    static final double BASE_EMITTER_JUNCTION_VOLTAGE_DROP = 0.7;
    static final double THERMAL_VOLTAGE = 0.026;

    public BJTComponents() {
        this.setType("BJTComponents");
    }
    
    public void calculateRCRE(double gain, double current, double biasingVoltage) {
        acEmitterResistance = BJTComponents.THERMAL_VOLTAGE / current;
        
        this.setResistorRC(acEmitterResistance * gain);
        
        double saturationCurrent = current * 2;
        double emitterResistance = ((biasingVoltage - BJTComponents.COLLECTOR_EMITTER_SATURATION_VOLTAGE) / saturationCurrent) - this.getResistorRC();
        this.setResistorRE(emitterResistance);
    }
    
    public void calculateR2(double hfe) {
        double r2 = 0.8 * ((hfe * this.getResistorRE()) / 2); // 10 times of r2 should be lower than the current gain ratio (hfe) multiplied by RE
        this.setResistorR2(r2);
    }
    
    public void calculateR1(double collectorCurrent, double biasingVoltage) {
        double emitterVoltage = collectorCurrent * this.getResistorRE(); // Assuming the difference between Ic and Ie is negligible
        double voltageAtR2 = emitterVoltage + BJTComponents.BASE_EMITTER_JUNCTION_VOLTAGE_DROP; // base voltage is almost equal to the voltage at r2.
        
        double voltageAtR1 = biasingVoltage - voltageAtR2;
        
        double r1 = (voltageAtR1 / voltageAtR2) * this.getResistorR2();
        this.setResistorR1(r1);
    }
    
    public void calculateCapacitors(double hfe, double lowCutoffFrequency) {
        double equivalentResistanceIn = 1 / ((1 / this.getResistorR1() + (1 / this.getResistorR2()) + (1 / (hfe + 1) * acEmitterResistance)));
        
        this.setCapacitorInput(1 / (2 * Math.PI * equivalentResistanceIn * lowCutoffFrequency));
        this.setCapacitorOutput(1 / (2 * Math.PI * this.getResistorRC() * lowCutoffFrequency));
        
        double r1r2TheveninEquivalent = 1 / ((1 / this.getResistorR1()) + ( 1 / this.getResistorR2()));
        double equivalentResistanceRe = 1 / ((1 / this.getResistorRE()) + (1 / ((r1r2TheveninEquivalent / hfe) + acEmitterResistance)));
        
        this.setCapacitorBypass(1 / (2 * Math.PI * equivalentResistanceRe * lowCutoffFrequency));
    }

    public void setParameters(double voltageGain, double collectorCurrent, double hfe, double inputFrequency, double peakToPeakSignalVoltage, double biasingVoltage, double lowCutoffFrequency) {
        calculateRCRE(voltageGain, collectorCurrent, biasingVoltage);
        calculateR2(hfe);
        calculateR1(collectorCurrent, biasingVoltage);
        calculateCapacitors(hfe, lowCutoffFrequency);
        
        this.setSignalVoltage(peakToPeakSignalVoltage);
        this.setBiasingVoltage(biasingVoltage);
        this.setInputFrequency(inputFrequency);
    }
    
    public double getResistorR1() {
        return resistorR1;
    }

    public void setResistorR1(double resistorR1) {
        this.resistorR1 = resistorR1;
    }

    public double getResistorR2() {
        return resistorR2;
    }

    public void setResistorR2(double resistorR2) {
        this.resistorR2 = resistorR2;
    }

    public double getResistorRC() {
        return resistorRC;
    }

    public void setResistorRC(double resistorRC) {
        this.resistorRC = resistorRC;
    }

    public double getResistorRE() {
        return resistorRE;
    }

    public void setResistorRE(double resistorRE) {
        this.resistorRE = resistorRE;
    }

    public double getCapacitorInput() {
        return capacitorInput;
    }

    public void setCapacitorInput(double capacitorInput) {
        this.capacitorInput = capacitorInput;
    }

    public double getCapacitorOutput() {
        return capacitorOutput;
    }

    public void setCapacitorOutput(double capacitorOutput) {
        this.capacitorOutput = capacitorOutput;
    }

    public double getSignalVoltage() {
        return signalVoltage;
    }

    public void setSignalVoltage(double signalVoltage) {
        this.signalVoltage = signalVoltage;
    }

    public double getBiasingVoltage() {
        return biasingVoltage;
    }

    public void setBiasingVoltage(double biasingVoltage) {
        this.biasingVoltage = biasingVoltage;
    }

    public double getInputFrequency() {
        return inputFrequency;
    }

    public void setInputFrequency(double inputFrequency) {
        this.inputFrequency = inputFrequency;
    }

    public double getCapacitorBypass() {
        return capacitorBypass;
    }

    public void setCapacitorBypass(double capacitorBypass) {
        this.capacitorBypass = capacitorBypass;
    }

    @Override
    public void updateSummary(ListView<String> componentsList, ImageView circuitImageView) {
        ObservableList<String> items = FXCollections.observableArrayList (
            "Type: INVERTING",
            "Driver: Bipolar Junction Transistor",
            "Mode: Common Emitter Amplifier (Bypassed)",
            String.format("R1: %s Ω", new BigDecimal(this.getResistorR1()).toPlainString()),
            String.format("R2: %s Ω", new BigDecimal(this.getResistorR2()).toPlainString()),
            String.format("RC: %s Ω", new BigDecimal(this.getResistorRC()).toPlainString()),
            String.format("RE: %s Ω", new BigDecimal(this.getResistorRE()).toPlainString()),
            String.format("Cin: %s F", new BigDecimal(this.getCapacitorInput()).toPlainString()),
            String.format("Cout: %s F", new BigDecimal(this.getCapacitorOutput()).toPlainString()),
            String.format("CE: %s F", new BigDecimal(this.getCapacitorBypass()).toPlainString()),
            String.format("VCC: %s V", new BigDecimal(this.getBiasingVoltage()).toPlainString()),
            String.format("Source: %s V", new BigDecimal(this.getSignalVoltage()).toPlainString())
        );

        componentsList.setItems(items);
        circuitImageView.setImage(new Image(App.class.getResource("/icons/circuit_bjt.png").toExternalForm()));
    }
}
