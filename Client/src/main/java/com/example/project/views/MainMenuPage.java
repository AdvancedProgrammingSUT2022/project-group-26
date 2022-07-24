package com.example.project.views;

import com.example.project.models.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.util.Duration;

import java.io.IOException;

public class MainMenuPage {
    private final DataBase dataBase = DataBase.getInstance();


    @FXML
    private ImageView exitButton;
    @FXML
    private VBox notificationMainVBox;

    public void initialize() {
        responseUpdates();
    }

    public void responseUpdates() {
         Timeline timeline = new Timeline(
                new KeyFrame(Duration.millis(10000),
                        event -> {
                            Response response = Network.getInstance().getResponse();
                            if (response.getOutput() == Output.INVITATION_REQUEST){
                                notificationMainVBox.getChildren().clear();
                                Label label = new Label();
                                label.setStyle("-fx-text-fill: #aba110");
                                label.setText("  you have an invitation request from " + response.getData());
                                label.setFont(Font.font(16));
                                notificationMainVBox.getChildren().add(label);
                                notificationMainVBox.setVisible(true);
                            }
                        }
                )
        );
        timeline.setCycleCount(-1);
        timeline.play();
    }

    public void startGame(MouseEvent mouseEvent) throws IOException {
        Network.getInstance().sendRequestWithoutResponse(new Request(RequestEnum.GO_TO_PLAY_GAME_SETTINGS));
        MenuChanger.changeMenu("PlayGameMenu");
    }

    public void scoreboardMenu(MouseEvent mouseEvent) {
        MenuChanger.changeMenu("ScoreBoardPage");
    }

    public void profileMenu(MouseEvent mouseEvent) throws IOException {
        Network.getInstance().sendRequestWithoutResponse(new Request(RequestEnum.GO_TO_PROFILE_MENU));
        MenuChanger.changeMenu("ProfileMenu");
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

    public void closeNotificationBox(MouseEvent mouseEvent) {
        notificationMainVBox.setVisible(false);
    }

    public void rejectInvitation(MouseEvent mouseEvent) {
        notificationMainVBox.setVisible(false);
//        Network.getInstance().sendRequestWithoutResponse(new Request(RequestEnum.INVITATION_REJECTED));
    }

    public void acceptInvitation(MouseEvent mouseEvent) {
        notificationMainVBox.setVisible(false);
        Request request = new Request(RequestEnum.INVITATION_ACCEPTED);
        request.addToParams("username", DataBase.getInstance().getLoggedInUser().getUsername());
        Network.getInstance().sendRequestWithoutResponse(request);
    }
}