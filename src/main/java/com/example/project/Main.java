package com.example.project;

import com.example.project.controllers.SaveData;
import com.example.project.models.UsersDatabase;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import com.example.project.views.LoginMenu;

import java.net.URL;

public class Main extends Application {
    public static void main(String[] args) {
//        launch();
        UsersDatabase usersDatabase = SaveData.loadAndReturnUserDataBase();
        LoginMenu loginMenu = new LoginMenu(usersDatabase);
        loginMenu.run();
        SaveData.saveUserDataBase(usersDatabase);
    }

    @Override
    public void start(Stage stage) throws Exception {
        URL address = new URL(Main.class.getResource("/Fxml/sample.fxml").toString());
        Parent root = FXMLLoader.load(address);
        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Login menu");
        stage.setFullScreen(true);
        stage.setResizable(false);
        stage.show();
    }
}