package com.neverbdneverw.focalors;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.prefs.Preferences;
import javafx.scene.paint.Color;
import javafx.stage.StageStyle;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private static Preferences preferences;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("primary"), 1024, 600);
        App.setCSS(false);
        stage.setTitle("FOCALORS");
        stage.setScene(scene);
        
        setDefaults();
        
        stage.show();
    }
    
    public void setDefaults() {
        if (preferences.get("darkMode", "") == null) {
            preferences.putBoolean("darkMode", false);
        }
        
        if (preferences.get("resistorColor", "") == null) {
            preferences.put("resistorColor", "#0066cc");
        }
        
        if (preferences.get("capacitorColor", "") == null) {
            preferences.put("capacitorColor", "#ff3131");
        }
        
        if (preferences.get("preferredAmp", "") == null) {
            preferences.put("preferredAmp", "ask always");
        }
        
        if (preferences.get("alwaysCeil", "") == null) {
            preferences.putBoolean("alwaysCeil", false);
        }
        
        if (preferences.get("decimalPlaces", "") == null) {
            preferences.put("decimalPlaces", "0.01");
        }
        
        if (preferences.get("resistanceUnit", "") == null) {
            preferences.put("resistanceUnit", "Ω");
        }
        
        if (preferences.get("capacitanceUnit", "") == null) {
            preferences.put("capacitanceUnit", "uF");
        }
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }
    
    static void setSceneRoot(Parent root) {
        scene.setRoot(root);
    }
    
        
    public static Preferences getPreferences() {
        if (preferences == null) {
            preferences = Preferences.userNodeForPackage(App.class);
        }
        
        return preferences;
    }

    public static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
    
    public static void setCSS (boolean darkMode) {
        if (darkMode) {
            scene.getStylesheets().add(App.class.getResource("/styles/application_dark.css").toExternalForm());
        } else {
            scene.getStylesheets().add(App.class.getResource("/styles/application.css").toExternalForm());
        }
    }

    public static void main(String[] args) {
        launch();
    }

}