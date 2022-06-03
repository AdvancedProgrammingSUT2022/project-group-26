package com.example.project.views;

import com.example.project.App;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
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
//        URL address = new URL(App.class.getResource("/Fxml/EditGameMap.fxml").toString());
        URL address = new URL(App.class.getResource("/Fxml/GlobalChat.fxml").toString());
        root = FXMLLoader.load(address);
        scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setFullScreen(true);
        primaryStage.setResizable(false);
        primaryStage.centerOnScreen();
        primaryStage.show();
        stage = primaryStage;
        PopupMessage.setStage(primaryStage);
        PopupMessage.setRoot(root);
    }

    public static void changeMenu(String menu) {
        try {
            URL address = new URL(App.class.getResource("/Fxml/" + menu + ".fxml").toString());
            root = FXMLLoader.load(address);
            scene.setRoot(root);
            MenuChanger.stage.setScene(scene);
            PopupMessage.setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}