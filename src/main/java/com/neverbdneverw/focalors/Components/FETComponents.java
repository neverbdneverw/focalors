/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.neverbdneverw.focalors.Components;

import com.neverbdneverw.focalors.Components.Components;
import com.neverbdneverw.focalors.AmplificationProcessors.OpAmpAmplificationProcessor;
import com.neverbdneverw.focalors.AmplificationProcessors.OpAmpAmplificationProcessor.OpAmpType;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;

/**
 *
 * @author HUAWEI-Pc
 */
public class FETComponents extends Components {
    private double resistorR1;
    private double resistorR2;
    private double resistorRD;
    private double resistorRS;
    private double capacitorInput;
    private double capacitorOutput;
    private double capacitorBypass;
    
    private double signalVoltage;
    private double biasingVoltage;
    private double inputFrequency;

    public FETComponents() {
        this.setType("BJTComponents");
    }
    
    public void calculateRDRS(double gain, double thresholdVoltage, double transconductanceParameter, double biasingVoltage) {
        // We want to make sure the voltage across the drain VD is of higher potential than VGS which we want to be half the supply
        // This is calculated on the saturation region thus there is no significant current gains.
        double drainResistor = (3 / 2 * transconductanceParameter) * ((gain * gain) / biasingVoltage);
        this.setResistorRD(drainResistor);
        
        double transconductance = gain / drainResistor;
        
        // Overdrive voltage is the difference between Vgs and Vth.
        double overdriveVoltage = transconductance / transconductanceParameter;
        double gateSourceVoltage = overdriveVoltage + thresholdVoltage;
        
        double gateVoltage = biasingVoltage / 2;
        double sourceVoltage = gateVoltage - gateSourceVoltage;
        
        // We have the source voltage so we need the drain current which is approx. equal to the source current to gete the source resistor.
        double drainCurrentAtSaturation = (transconductanceParameter * (overdriveVoltage * overdriveVoltage)) / 2;
        double sourceResistor = sourceVoltage / drainCurrentAtSaturation;
        
        this.setResistorRS(sourceResistor);
    }
    
    public void calculateCapacitors(double hfe, double lowCutoffFrequency) {
        
    }

    public void setParameters(double voltageGain, double thresholdVoltage, double transconductanceParameter, double inputFrequency, double peakToPeakSignalVoltage, double biasingVoltage, double lowCutoffFrequency) {
        calculateRDRS(voltageGain, thresholdVoltage, transconductanceParameter, biasingVoltage);
//        calculateCapacitors(hfe, lowCutoffFrequency);
        
        this.setResistorR1(1000000); // We want to bias the gate to half the supply voltage while keeping the input impedance high.
        this.setResistorR2(1000000);
        this.setSignalVoltage(peakToPeakSignalVoltage);
        this.setBiasingVoltage(biasingVoltage);
        this.setInputFrequency(inputFrequency);
    }
    
    public double getResistorRD() {
        return resistorRD;
    }

    public void setResistorRD(double resistorRD) {
        this.resistorRD = resistorRD;
    }

    public double getResistorRS() {
        return resistorRS;
    }

    public void setResistorRS(double resistorRS) {
        this.resistorRS = resistorRS;
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
//        ObservableList<String> items = FXCollections.observableArrayList (
//            "Type: INVERTING",
//            "Driver: Bipolar Junction Transistor",
//            "Mode: Common Emitter Amplifier (Bypassed)",
//            String.format("R1: %s", String.valueOf(this.getResistorR1())),
//            String.format("R2: %s", String.valueOf(this.getResistorR2())),
//            String.format("RC: %s", String.valueOf(this.getResistorRC())),
//            String.format("RE: %s", String.valueOf(this.getResistorRE())),
//            String.format("Cin: %s F", String.valueOf(this.getCapacitorInput())),
//            String.format("Cout: %s F", String.valueOf(this.getCapacitorOutput())),
//            String.format("CE: %s F", String.valueOf(this.getCapacitorBypass())),
//            String.format("VCC: %s V", String.valueOf(this.getBiasingVoltage())),
//            String.format("Source: %s V", String.valueOf(this.getSignalVoltage()))
//        );
//
//        componentsList.setItems(items);
    }
}