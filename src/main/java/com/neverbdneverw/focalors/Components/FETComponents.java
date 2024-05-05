/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.neverbdneverw.focalors.Components;

import com.neverbdneverw.focalors.Components.Components;
import com.neverbdneverw.focalors.AmplificationProcessors.OpAmpAmplificationProcessor;
import com.neverbdneverw.focalors.AmplificationProcessors.OpAmpAmplificationProcessor.OpAmpType;
import com.neverbdneverw.focalors.App;
import com.neverbdneverw.focalors.Utilities.Utils;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.prefs.Preferences;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
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
    
    private double transconductance;
    
    private double signalVoltage;
    private double biasingVoltage;
    private double inputFrequency;

    public FETComponents() {
        this.setType("FETComponents");
    }
    
    public void calculateRDRS(double gain, double thresholdVoltage, double transconductanceParameter, double biasingVoltage) {
        // We want to make sure the voltage across the drain VD is of higher potential than VGS which we want to be half the supply
        // This is calculated on the saturation region thus there is no significant current gains.
        double transconductanceFactor = (3 / (2 * transconductanceParameter)) * 1000;
        double gainFactor = (Math.pow(gain, 2) / biasingVoltage);
        double drainResistor =  transconductanceFactor * gainFactor;
        this.setResistorRD(drainResistor);
        
        transconductance = gain / drainResistor;
        
        // Overdrive voltage is the difference between Vgs and Vth.
        double overdriveVoltage = (transconductance / transconductanceParameter) * 1000;
        double gateSourceVoltage = overdriveVoltage + thresholdVoltage;
        
        double gateVoltage = biasingVoltage / 2;
        double sourceVoltage = gateVoltage - gateSourceVoltage;
        
        // We have the source voltage so we need the drain current which is approx. equal to the source current to gete the source resistor.
        double drainCurrentAtSaturation = ((transconductanceParameter / 1000) * (Math.pow(overdriveVoltage, 2))) / 2;
        double sourceResistor = sourceVoltage / drainCurrentAtSaturation;
        
        this.setResistorRS(sourceResistor);
    }
    
    public void calculateCapacitors(double lowCutoffFrequency) {
        double equivalentResistanceRi = 1 / ((1 / this.getResistorR1()) + (1 / this.getResistorR2()));
        
        this.setCapacitorInput(1 / (2 * Math.PI * equivalentResistanceRi * lowCutoffFrequency));
        this.setCapacitorOutput(1 / (2 * Math.PI * this.getResistorRD() * lowCutoffFrequency));
        
        double equivalentResistanceRs = 1 / ((1 / this.getResistorRS()) + transconductance);
        
        this.setCapacitorBypass(1 / (2 * Math.PI * equivalentResistanceRs * lowCutoffFrequency));
    }

    public void setParameters(double voltageGain, double thresholdVoltage, double transconductanceParameter, double inputFrequency, double peakToPeakSignalVoltage, double biasingVoltage, double lowCutoffFrequency) {
        this.setResistorR1(1000000); // We want to bias the gate to half the supply voltage while keeping the input impedance high.
        this.setResistorR2(1000000);

        calculateRDRS(voltageGain, thresholdVoltage, transconductanceParameter, biasingVoltage);
        calculateCapacitors(lowCutoffFrequency);
        
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
        ObservableList<String> items = FXCollections.observableArrayList (
            "Type: INVERTING",
            "Driver: Metal Oxide Semiconductor Field Effect Transistor",
            "Mode: Common Source Amplifier (Bypassed)",
            String.format("R1: %s", Utils.cleanDouble(this.getResistorR1(), "r")),
            String.format("R2: %s", Utils.cleanDouble(this.getResistorR2(), "r")),
            String.format("RD: %s", Utils.cleanDouble(this.getResistorRD(), "r")),
            String.format("RS: %s", Utils.cleanDouble(this.getResistorRS(), "r")),
            String.format("Cin: %s", Utils.cleanDouble(this.getCapacitorInput(), "c")),
            String.format("Cout: %s", Utils.cleanDouble(this.getCapacitorOutput(), "c")),
            String.format("Cs: %s", Utils.cleanDouble(this.getCapacitorBypass(), "c")),
            String.format("VDD: %s V", this.getBiasingVoltage()),
            String.format("Source: %s V", this.getSignalVoltage())
        );

        componentsList.setItems(items);
        circuitImageView.setImage(new Image(App.class.getResource("/icons/circuit_mosfet.png").toExternalForm()));
    }
}
