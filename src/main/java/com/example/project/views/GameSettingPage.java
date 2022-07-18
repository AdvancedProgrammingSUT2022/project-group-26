package com.example.project.views;

import com.example.project.controllers.GameSettingController;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class GameSettingPage {
    @FXML
    private Slider saveNumber;
    @FXML
    private Slider playersNumber;
    @FXML
    private Label playerNumberLabel;
    @FXML
    private Label saveNumberLabel;
    @FXML
    private ChoiceBox savingChoiceBox;
    @FXML
    private Label savedFilesLabel;
    @FXML
    private CheckBox autoSaveCheck;
    @FXML
    private ImageView addPlayerButton;
    @FXML
    private VBox playersInGame;
    @FXML
    private Pane searchPane;
    @FXML
    private TextField searchTextField;
    @FXML
    private VBox suggestionPlayers;

    public void initialize() {
        playersNumber.setTooltip(new Tooltip("set your preferred number of players"));
        autoSaveCheck.setTooltip(new Tooltip("enable auto save"));
        saveNumber.setTooltip(new Tooltip("set your preferred max number of saved files"));
        savingChoiceBox.setTooltip(new Tooltip("set saving periods"));
        playersNumber.setOnDragDone(dragEvent -> {
            clearSuggestionPlayers();
        });
        searchTextField.setOnKeyReleased(keyEvent -> {
            updateSearchPlayersToAdd();
        });
        updateEverything();
    }

    private void clearSuggestionPlayers() {
        suggestionPlayers.getChildren().clear();
        addLoggedInUserToPlayers();
    }

    private void updateEverything() {
        final Timeline timeline = new Timeline(
                new KeyFrame(Duration.millis(1),
                        event -> {
                            playerNumberLabel.setText(String.valueOf((int) playersNumber.getValue()));
                            GameSettingController.getInstance().setNumberOfPlayers((int) playersNumber.getValue());
                            saveNumberLabel.setText(String.valueOf((int) saveNumber.getValue()));
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
        saveNumberLabel.setVisible(true);
        savedFilesLabel.setVisible(true);
        savingChoiceBox.setVisible(true);
    }

    public void openSearchPane(MouseEvent mouseEvent) {
        searchPane.setVisible(!searchPane.isVisible());
        searchTextField.requestFocus();
    }


    private void updateSearchPlayersToAdd() {

    }

    private void addLoggedInUserToPlayers(){

    }
}
