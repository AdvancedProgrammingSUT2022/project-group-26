package com.example.project.views;

import com.example.project.models.DataBase;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class MainMenuPage {
    private final DataBase dataBase = DataBase.getInstance();


    @FXML
    private ImageView exitButton;

    public void startGame(MouseEvent mouseEvent) {
        MenuChanger.changeMenu("PlayGameMenu");
    }

    public void scoreboardMenu(MouseEvent mouseEvent) {
        MenuChanger.changeMenu("ScoreBoardPage");
    }

    public void profileMenu(MouseEvent mouseEvent) {
        // profile menu
    }

    public void openChat(MouseEvent mouseEvent) {
        MenuChanger.changeMenu("GlobalChat");
    }

    public void logout(MouseEvent mouseEvent) {
        dataBase.setLoggedInUser(null);
        MenuChanger.changeMenu("LoginMenu");
    }

    public void exit(MouseEvent mouseEvent) {
        Platform.exit();
    }
}
