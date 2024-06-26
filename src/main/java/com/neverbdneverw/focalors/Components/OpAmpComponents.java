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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 *
 * @author HUAWEI-Pc
 */
public class OpAmpComponents extends Components {
    private OpAmpType amplificationType;
    private double resistorR1;
    private double resistorR2;
    private double inputFilterResistor;
    private double outputFilterResistor;
    private double capacitorInput;
    private double capacitorOutput;
    
    private double signalVoltage;
    private double biasingVoltage;
    private double inputFrequency;

    public OpAmpComponents() {
        this.setType("OpAmpComponents");
    }
    
    private void calculateGainResistors(OpAmpType amplification, double voltageGain) {
        this.setResistorR1(1000);
        this.setAmplificationType(amplification);

        if (amplification.equals(OpAmpAmplificationProcessor.OpAmpType.INVERTING)) {
            this.setResistorR2(voltageGain * resistorR1);
        } else {
            this.setResistorR2(resistorR1 * (voltageGain - 1));
        }
    }
    
    private void calculateFilterComponents(double lowCutoffFrequency, double highCutoffFrequency) {
        this.setCapacitorInput(0.000001);
        this.setCapacitorOutput(0.000001);

        this.setInputFilterResistor(1 / (2 * Math.PI * highCutoffFrequency * this.getCapacitorInput()));
        this.setOutputFilterResistor(1 / (2 * Math.PI * lowCutoffFrequency * this.getCapacitorOutput()));
    }

    public void setParameters(OpAmpType amplificationType, double voltageGain, double peakToPeakSignalVoltage, double biasingVoltage, double inputFrequency, double lowCutoffFrequency, double highCutoffFrequency) {
        calculateGainResistors(amplificationType, voltageGain);
        calculateFilterComponents(lowCutoffFrequency, highCutoffFrequency);

        this.setSignalVoltage(peakToPeakSignalVoltage);
        this.setBiasingVoltage(biasingVoltage);
        this.setInputFrequency(inputFrequency);
    }
    
    public OpAmpType getAmplificationType() {
        return amplificationType;
    }
    
    public void setAmplificationType(OpAmpType amplification) {
        this.amplificationType = amplification;
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

    public double getInputFilterResistor() {
        return inputFilterResistor;
    }

    public void setInputFilterResistor(double inputFilterResistor) {
        this.inputFilterResistor = inputFilterResistor;
    }

    public double getOutputFilterResistor() {
        return outputFilterResistor;
    }

    public void setOutputFilterResistor(double outputFilterResistor) {
        this.outputFilterResistor = outputFilterResistor;
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

    @Override
    public void updateSummary(ListView<String> componentsList, ImageView circuitImageView) {
        ObservableList<String> items = FXCollections.observableArrayList (
            String.format("Type: %s", String.valueOf(this.getAmplificationType())),
            String.format("Driver: OP AMP"),
            String.format("R1: %s", Utils.cleanDouble(this.getResistorR1(), "r")),
            String.format("R2: %s", Utils.cleanDouble(this.getResistorR2(), "r")),
            String.format("Cin: %s", Utils.cleanDouble(this.getCapacitorInput(), "c")),
            String.format("Cout: %s", Utils.cleanDouble(this.getCapacitorOutput(), "c")),
            String.format("Rin: %s", Utils.cleanDouble(this.getInputFilterResistor(), "r")),
            String.format("Rout: %s", Utils.cleanDouble(this.getOutputFilterResistor(), "r")),
            String.format("VCC: %s V", this.getBiasingVoltage()),
            String.format("-VCC: -%s V", this.getBiasingVoltage()),
            String.format("Source: %s V", this.getSignalVoltage())
        );

        componentsList.setItems(items);

        if (this.getAmplificationType().equals(OpAmpType.INVERTING)) {
            circuitImageView.setImage(new Image(App.class.getResource("/icons/circuit_inverting.png").toExternalForm()));
        } else {
            circuitImageView.setImage(new Image(App.class.getResource("/icons/circuit_noninverting.png").toExternalForm()));
        }
    }
}
