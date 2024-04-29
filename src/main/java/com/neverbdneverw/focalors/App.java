package com.neverbdneverw.focalors;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("primary"), 1024, 600);
        App.setCSS("application");
        stage.setTitle("FOCALORS");
        stage.setScene(scene);
        
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }
    
    static void setSceneRoot(Parent root) {
        scene.setRoot(root);
    }

    public static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }
    
    public static void setCSS (String themeFilename) {
        scene.getStylesheets().clear();
        setUserAgentStylesheet(null);
        scene.getStylesheets().add(App.class.getResource(String.format("/styles/%s.css", themeFilename)).toExternalForm());
    }

    public static void main(String[] args) {
        launch();
    }

}