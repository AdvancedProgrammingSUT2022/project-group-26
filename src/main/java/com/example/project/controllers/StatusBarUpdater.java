package com.example.project.controllers;

import com.example.project.models.Player;
import com.example.project.views.PlayGamePage;
import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class StatusBarUpdater implements Runnable {
    @FXML
    Label gold;
    @FXML
    Label happiness;
    @FXML
    Label science;


    public StatusBarUpdater(Label gold, Label happiness, Label science) {
        this.gold = gold;
        this.happiness = happiness;
        this.science = science;
    }

    @Override
    public void run() {
        try {
            while (true) {
                Thread.sleep(3000);
                Player currentPlayer = PlayGamePage.getInstance().getThisTurnPlayer();
//                gold.setText(String.valueOf(currentPlayer.getGold()));
//                happiness.setText(String.valueOf(currentPlayer.getHappiness()));
//                science.setText(String.valueOf(currentPlayer.getScience()));
                gold.setText("hi");
                happiness.setText("there");
                science.setText("khoodayaaa");
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
