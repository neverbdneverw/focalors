package com.neverbdneverw.focalors;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.scene.paint.Color;
import javafx.stage.StageStyle;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("primary"), 1024, 600);
        App.setCSS(false);
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