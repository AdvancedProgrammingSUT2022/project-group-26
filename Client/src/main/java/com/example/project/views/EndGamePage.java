package com.example.project.views;

import com.example.project.controllers.GameControllers.PlayGameMenuController;
import com.example.project.models.Player;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class EndGamePage {
    private static EndGamePage instance;
    public static void setNull(){
        instance = null;
    }

    @FXML
    private Label score;
    @FXML
    private Label name;

    public static EndGamePage getInstance() {
        if (instance == null) instance = new EndGamePage();
        return instance;
    }

    public void setWinner(Player winner) {
        this.winner = winner;
    }

    private Player winner;

    public void initialize() {
        name.setText(instance.winner.getUser().getUsername());
        score.setText(String.valueOf(instance.winner.calculateScore()));
    }

    public void exit(MouseEvent mouseEvent) {
        Platform.exit();
    }

    public void back(MouseEvent mouseEvent) {
        MenuChanger.changeMenu("MainMenu");
    }
}