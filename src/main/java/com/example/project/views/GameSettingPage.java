package com.example.project.views;

import com.example.project.controllers.GameSettingController;
import com.example.project.models.MainGameSaver;
import com.example.project.controllers.Output;
import com.example.project.models.User;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.net.MalformedURLException;
import java.util.ArrayList;

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

    private boolean isAutoSaveSelected = false;

    public void initialize() {
        playersNumber.setTooltip(new Tooltip("set your preferred number of players"));
        autoSaveCheck.setTooltip(new Tooltip("enable auto save"));
        saveNumber.setTooltip(new Tooltip("set your preferred max number of saved files"));
        savingChoiceBox.setTooltip(new Tooltip("set saving periods"));
        suggestionPlayers.setSpacing(5);
        playersInGame.setSpacing(2);
        searchTextField.setOnKeyReleased(keyEvent -> {
            try {
                updateSearchPlayersToAdd();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        });
        updateEverything();
    }

    private void clearPlayerInGames() {
        GameSettingController.getInstance().clearPlayers();
        updatePlayersInGame();
    }

    private void updateEverything() {
        final Timeline timeline = new Timeline(
                new KeyFrame(Duration.millis(1),
                        event -> {
                            playerNumberLabel.setText(String.valueOf((int) playersNumber.getValue()));
                            if ((int) playersNumber.getValue() < GameSettingController.getInstance().getUsers().size())
                                clearPlayerInGames();
                            GameSettingController.getInstance().setNumberOfPlayers((int) playersNumber.getValue());
                            saveNumberLabel.setText(String.valueOf((int) saveNumber.getValue()));
                        }
                )
        );
        timeline.setCycleCount(-1);
        timeline.play();
        updatePlayersInGame();
    }

    public void startGame(MouseEvent mouseEvent) {
        Output output = GameSettingController.getInstance().startGame(isAutoSaveSelected, savingChoiceBox);
        if (output != null) new PopupMessage(Alert.AlertType.INFORMATION, output.toString());
        else {
            PlayGamePage.getInstance().setUp();
            MenuChanger.changeMenu("Game");
        }
    }

    public void continueGame(MouseEvent mouseEvent) {
        MainGameSaver.loadGame();
        PlayGamePage.getInstance().setUp();
        MenuChanger.changeMenu("Game");
    }

    public void back(MouseEvent mouseEvent) {
        MenuChanger.changeMenu("MainMenu");
    }

    public void exit(MouseEvent mouseEvent) {
        Platform.exit();
    }

    public void autoSave(MouseEvent mouseEvent) {
        if (!isAutoSaveSelected) {
            //TODO: logic
            saveNumber.setVisible(true);
            saveNumberLabel.setVisible(true);
            savedFilesLabel.setVisible(true);
            savingChoiceBox.setVisible(true);
        } else {
            saveNumber.setVisible(false);
            saveNumberLabel.setVisible(false);
            savedFilesLabel.setVisible(false);
            savingChoiceBox.setVisible(false);
        }
        isAutoSaveSelected = !isAutoSaveSelected;
    }

    public void openSearchPane(MouseEvent mouseEvent) {
        searchPane.setVisible(!searchPane.isVisible());
        searchTextField.requestFocus();
    }


    private void updateSearchPlayersToAdd() throws MalformedURLException {
        ArrayList<User> usersToShow = GameSettingController.getInstance().showUsernamesStartsWithString(searchTextField.getText());
        suggestionPlayers.getChildren().clear();
        for (User user : usersToShow) {
            addSuggestionPane(user);
        }
    }

    private void updatePlayersInGame() {
        playersInGame.getChildren().clear();
        for (User user : GameSettingController.getInstance().getUsers())
            addPlayersInGamePane(user);
    }

    private void addLabelToSuggestionPane(Pane pane, String username) {
        Label label = new Label(username);
        label.setPrefHeight(20);
        label.setLayoutX(15);
        label.setLayoutY(8);
        label.setStyle("-fx-font-family: \"High Tower Text\";" +
                "       -fx-font-size: 18");
        pane.getChildren().add(label);
    }


    private void addSuggestionPane(User user) {
        Pane pane = new Pane();
        pane.setPrefHeight(40);
        pane.setStyle("-fx-background-color: #dfc10c;" +
                "-fx-border-radius: 30 30 30 30;" +
                "-fx-background-radius: 30 30 30 30;");
        addLabelToSuggestionPane(pane, user.getUsername());
        pane.setCursor(Cursor.HAND);
        suggestionPlayers.getChildren().add(pane);
        pane.setOnMouseClicked(mouseEvent -> {
            Output output = GameSettingController.getInstance().addPlayer(user);
            if (output != null) new PopupMessage(Alert.AlertType.INFORMATION, output.toString());
            else updatePlayersInGame();
        });
    }

    private void addPlayersInGamePane(User user) {
        Pane pane = new Pane();
        pane.setPrefHeight(25);
        pane.setPrefWidth(50);
        pane.setStyle("-fx-background-color: #1a5bb0;" +
                "-fx-border-radius: 30 30 30 30;" +
                "-fx-background-radius: 30 30 30 30;");
        addLabelToPlayerInGamePane(pane, user.getUsername());
        playersInGame.getChildren().add(pane);
    }

    private void addLabelToPlayerInGamePane(Pane pane, String username) {
        Label label = new Label(username);
        label.setPrefHeight(20);
        label.setLayoutX(15);
        label.setLayoutY(1);
        label.setStyle("-fx-font-family: \"High Tower Text\";" +
                "       -fx-font-size: 18;" +
                "-fx-text-fill: #d8c965");
        pane.getChildren().add(label);
    }
}
