package com.example.project.views;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.util.Duration;

public class GameSettingPage {
    public Slider saveNumber;
    public Slider xMap;
    public Slider yMap;
    public Slider playersNumber;
    public Label playerNumLabel;
    public Label yMapLabel;
    public Label xMapLabel;
    public Label saveNumLabel;
    public ChoiceBox savingChoiceBox;
    public Label savedFilesLabel;
    public CheckBox autoSaveCheck;

    public void initialize() {
        playersNumber.setTooltip(new Tooltip("set your preferred number of players"));
        xMapLabel.setTooltip(new Tooltip("set your preferred number of horizontal tiles"));
        yMapLabel.setTooltip(new Tooltip("set your preferred number of vertical tiles"));
        autoSaveCheck.setTooltip(new Tooltip("enable auto save"));
        saveNumber.setTooltip(new Tooltip("set your preferred max number of saved files"));
        savingChoiceBox.setTooltip(new Tooltip("set saving periods"));
        updateEverything();
    }

    private void updateEverything() {
        final Timeline timeline = new Timeline(
                new KeyFrame(Duration.millis(1),
                        event -> {
                            playerNumLabel.setText(String.valueOf((int) playersNumber.getValue()));
                            xMapLabel.setText(String.valueOf((int) xMap.getValue() * 10));
                            yMapLabel.setText(String.valueOf((int) yMap.getValue() * 10));
                            saveNumLabel.setText(String.valueOf((int) saveNumber.getValue()));
                        }
                )
        );
        timeline.setCycleCount(-1);
        timeline.play();
    }

    public void startGame(MouseEvent mouseEvent) {
        PlayGamePage.getInstance().setUp();
        MenuChanger.changeMenu("Game");
    }

    public void continueGame(MouseEvent mouseEvent) {
    }

    public void back(MouseEvent mouseEvent) {
        MenuChanger.changeMenu("MainMenu");
    }

    public void exit(MouseEvent mouseEvent) {
        Platform.exit();
    }

    public void autoSave(MouseEvent mouseEvent) {
        //TODO: logic
        saveNumber.setVisible(true);
        saveNumLabel.setVisible(true);
        savedFilesLabel.setVisible(true);
        savingChoiceBox.setVisible(true);
    }
}
