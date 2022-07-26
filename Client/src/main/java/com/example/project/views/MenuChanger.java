package com.example.project.views;

import com.example.project.App;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.Pane;
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
        URL address = new URL(App.class.getResource("/Fxml/LoginMenu.fxml").toString());
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
            PopupMessage.setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void goToTechTree() {
        try {
            URL address = new URL(App.class.getResource("/Fxml/TechTreeComponents.fxml").toString());
            ScrollPane techRoot = FXMLLoader.load(address);
//            PlayGamePage.getInstance().getInstanceGameMapPane().getChildren().clear();
//            PlayGamePage.getInstance().getInstanceGameMapPane().getChildren().add(techRoot);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void resetGameRequestFocus() {
        root.requestFocus();
    }

    public static Scene getScene() {
        return scene;
    }

    public static Parent getRoot() {
        return root;
    }

    public static Stage getStage() {
        return stage;
    }
}