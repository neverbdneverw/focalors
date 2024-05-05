package com.neverbdneverw.focalors.AmplificationProcessors;


import com.neverbdneverw.focalors.AmplificationProcessors.OpAmpAmplificationProcessor;
import com.neverbdneverw.focalors.AmplificationProcessors.AmplificationProcessor;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author HUAWEI-Pc
 */
public class Processors {
    private static AmplificationProcessor activeProcessor;
    
    public static AmplificationProcessor getActiveProcessor(String type) {
        if (activeProcessor == null) {
            createNew(type);
        } else {
            if (activeProcessor.getType().equals(type)) {
                return activeProcessor;
            } else if (type.equals("")) {
                return activeProcessor;
            } else {
                createNew(type);
            }
        }
        
        return activeProcessor;
    }
    
    public static void createNew (String type) {
        if (type.equals("opamp")) {
            activeProcessor = new OpAmpAmplificationProcessor();
        } else if (type.equals("bjt")) {
            activeProcessor = new BJTAmplificationProcessor();
        } else if (type.equals("fet")) {
            activeProcessor = new FETAmplificationProcessor();
        }
    }
}
