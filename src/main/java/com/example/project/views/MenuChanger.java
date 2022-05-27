package com.example.project.views;

import com.example.project.App;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class MenuChanger extends Application {
    private static Stage stage;
    private static Scene scene;
    private static Parent root;

    public static void main(String[] args) {
        launch();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        URL address = new URL(App.class.getResource("/Fxml/GlobalChat.fxml").toString());
        root = FXMLLoader.load(address);
        scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setFullScreen(true);
        primaryStage.setResizable(false);
        primaryStage.centerOnScreen();
        primaryStage.show();
        stage = primaryStage;
        PopupMessage.setStage(stage);
        PopupMessage.setRoot(root);
    }
}
