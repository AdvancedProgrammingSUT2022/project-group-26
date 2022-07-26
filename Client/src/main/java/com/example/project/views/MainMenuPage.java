package com.example.project.views;

import com.example.project.models.*;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.AnyTypePermission;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

public class MainMenuPage {
    private final DataBase dataBase = DataBase.getInstance();

    @FXML
    private ImageView exitButton;
    @FXML
    private VBox notificationMainVBox;
    @FXML
    private Label notificationMessage;
    private String labelMessage = null;

    private Timeline timeline;

    public void initialize() {
        notificationMainVBox.setVisible(false);
        updateMessageLabel();
        responseUpdates();
    }

    public void updateMessageLabel() {
        timeline = new Timeline(new KeyFrame(Duration.millis(100), actionEvent -> {
            if (labelMessage != null) {
                synchronized (this) {
                    notificationMessage.setText(labelMessage);
                    notificationMainVBox.setVisible(true);
                }
            }
        }));
        timeline.setCycleCount(-1);
        timeline.play();
    }


    public void responseUpdates() {
        Thread waitForResponseThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    Response response = null;
                    response = Network.getInstance().getResponse();
                    if (response.getOutput() == Output.INVITATION_REQUEST) {
                        labelMessage = "  you have an invitation request from " + response.getData();
                    } else if (response.getOutput() == Output.STOP_THREAD) {
                        timeline.stop();
                        return;
                    } else {
                        StringBuilder xml = new StringBuilder("");
                        xml.append(response.getData());
                        for (int i = 0; i < 29; i++)
                            xml.append(Network.getInstance().getResponse().getData());
                        XStream xStream = new XStream();
                        xStream.addPermission(AnyTypePermission.ANY);
                        if (xml.length() != 0) {
                            GameNetworkData game = (GameNetworkData) xStream.fromXML(xml.toString());
                            game.setToGameDataBase();
                        }
                        PlayGamePage.getInstance().setUp();
                        MenuChanger.changeMenu("Game");
                        timeline.stop();
                        return;
                    }
                }
            }
        });
        waitForResponseThread.start();
    }

    public void startGame(MouseEvent mouseEvent) {
        timeline.stop();
        Network.getInstance().sendRequestWithoutResponse(new Request(RequestEnum.GO_TO_PLAY_GAME_SETTINGS));
        MenuChanger.changeMenu("PlayGameMenu");
    }

    public void scoreboardMenu(MouseEvent mouseEvent) {
        timeline.stop();
        Network.getInstance().sendRequestWithoutResponse(new Request(RequestEnum.GO_TO_SCORE_BOARD));
        MenuChanger.changeMenu("ScoreBoardPage");
    }

    public void profileMenu(MouseEvent mouseEvent) {
        timeline.stop();
        Network.getInstance().sendRequestWithoutResponse(new Request(RequestEnum.GO_TO_PROFILE_MENU));
        MenuChanger.changeMenu("ProfileMenu");
    }

    public void openChat(MouseEvent mouseEvent) {
        timeline.stop();
        Network.getInstance().sendRequestWithoutResponse(new Request(RequestEnum.GO_TO_GLOBAL_CHAT));
        MenuChanger.changeMenu("GlobalChat");
    }

    public void logout(MouseEvent mouseEvent) {
        timeline.stop();
        Network.getInstance().sendRequestWithoutResponse(new Request(RequestEnum.BACK));
        MenuChanger.changeMenu("LoginMenu");
    }

    public void exit(MouseEvent mouseEvent) {
        timeline.stop();
        Platform.exit();
    }

    public void closeNotificationBox(MouseEvent mouseEvent) {
        synchronized (this) {
            labelMessage = null;
        }
        notificationMainVBox.setVisible(false);
    }

    public void rejectInvitation(MouseEvent mouseEvent) {
        synchronized (this) {
            labelMessage = null;
        }
        notificationMainVBox.setVisible(false);
    }

    public void acceptInvitation(MouseEvent mouseEvent) {
        Request request = new Request(RequestEnum.INVITATION_ACCEPTED);
        request.addToParams("username", DataBase.getInstance().getLoggedInUser().getUsername());
        Network.getInstance().sendRequestWithoutResponse(request);
        synchronized (this) {
            labelMessage = null;
        }
        notificationMainVBox.setVisible(false);
    }

}