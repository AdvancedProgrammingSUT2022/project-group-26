package com.example.project.views;

import com.example.project.models.*;
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

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;

import static com.example.project.models.MainGameSaver.getXStreamToRead;

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
    private Timeline timeline;

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

    private void clearPlayerInGames() throws IOException {
        Network.getInstance().sendRequestWithoutResponse(new Request(RequestEnum.CLEAR_PLAYERS_IN_GAME));
        updatePlayersInGame();
    }

    private void updateEverything() {
        timeline = new Timeline(
                new KeyFrame(Duration.millis(500),
                        event -> {
                            playerNumberLabel.setText(String.valueOf((int) playersNumber.getValue()));
                            Request request = new Request(RequestEnum.UPDATE_IN_GAME_PLAYERS);
                            ArrayList<User> inGamePlayers = (ArrayList<User>) getXStreamToRead().fromXML(Network.getInstance().sendRequestAndGetResponse(request).getData());
                            if ((int) playersNumber.getValue() < inGamePlayers.size()) {
                                try {
                                    clearPlayerInGames();
                                } catch (IOException e) {
                                    throw new RuntimeException(e);
                                }
                            }
                            Request request1 = new Request(RequestEnum.SET_NUMBER_OF_PLAYERS);
                            request1.addToParams("number", playersNumber.getValue());
                            Network.getInstance().sendRequestWithoutResponse(request1);
                            saveNumberLabel.setText(String.valueOf((int) saveNumber.getValue()));
                            updatePlayersInGame();
                        }
                )
        );
        timeline.setCycleCount(-1);
        timeline.play();
        updatePlayersInGame();
    }

    public void startGame(MouseEvent mouseEvent) {
        timeline.stop();
        GameNetworkData.getGame();
        PlayGamePage.getInstance().setUp();
        System.out.println(103);
        MenuChanger.changeMenu("Game");
        System.out.println(105);
    }

    public void back(MouseEvent mouseEvent) throws IOException {
        timeline.stop();
        Network.getInstance().sendRequestWithoutResponse(new Request(RequestEnum.BACK));
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
        Request request = new Request(RequestEnum.UPDATE_SEARCHED_PLAYERS);
        request.addToParams("searched", searchTextField.getText());
        ArrayList<User> usersToShow =
                (ArrayList<User>) MainGameSaver.getXStreamToRead().fromXML(Network.getInstance().sendRequestAndGetResponse(request).getData());
        suggestionPlayers.getChildren().clear();
        for (User user : usersToShow) {
            addSuggestionPane(user);
        }
    }

    private void updatePlayersInGame() {
        playersInGame.getChildren().clear();
        Request request = new Request(RequestEnum.UPDATE_IN_GAME_PLAYERS);
        ArrayList<User> inGamePlayers = (ArrayList<User>) MainGameSaver.getXStreamToRead().fromXML(Network.getInstance().sendRequestAndGetResponse(request).getData());
        for (User user : inGamePlayers)
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
            Request request = new Request(RequestEnum.ADD_PLAYER);
            request.addToParams("user", user.getUsername());
            Output output = Network.getInstance().sendRequestAndGetResponseOutput(request);
            if (output != null) new PopupMessage(Alert.AlertType.INFORMATION, output.toString());
            else {
                sendInvitation(user.getUsername());
                new PopupMessage(Alert.AlertType.INFORMATION, "invitation sent successfully!");
                updatePlayersInGame();
            }
        });
    }

    private void sendInvitation(String username) {
        Request request = new Request(RequestEnum.SEND_INVITATION_REQUEST);
        request.addToParams("username", username);
        Network.getInstance().sendRequestWithoutResponse(request);
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
