package com.example.project.views;

import com.example.project.models.*;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;

public class ScoreBoardPage {
    private final Image green = new Image(new FileInputStream("src/main/resources/Image/Menu/Icon/green.png"));
    private final Image red = new Image(new FileInputStream("src/main/resources/Image/Menu/Icon/red.png"));
    @FXML
    private VBox secondBox;
    @FXML
    private AnchorPane pane;
    @FXML
    private VBox firstBox;

    public ScoreBoardPage() throws FileNotFoundException {
    }

    public void initialize() {
        secondBox.setPadding(new Insets(10, 10, 10, 10));

        Response response = null;
        response = Network.getInstance().sendRequestAndGetResponse(new Request(RequestEnum.UPDATE_SCOREBOARD_DATA));

        ArrayList<User> data = new ArrayList<>();
        if (response != null && response.getOutput() == Output.DATA) {
            data = new GsonBuilder().create().fromJson(response.getData(), new TypeToken<ArrayList<User>>() {
            }.getType());
        }

        showBoard(data);

        //todo : complete
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while (true) {
//                    if (false) break; // if back
//                    // if listener -->
//                    // get data
//                }
//            }
//        }).start();
    }


    public void showBoard(ArrayList<User> scoreboardData) {
        secondBox.getChildren().clear();

//        ArrayList<User> scoreboardData = UsersDatabase.getInstance().getUsers();
//        sortUsers(scoreboardData);

        HBox[] hBoxes = new HBox[Math.min(10, scoreboardData.size())];
        Label[] ranks = new Label[Math.min(10, scoreboardData.size())];
        Label[] usernames = new Label[Math.min(10, scoreboardData.size())];
        Label[] lastLogins = new Label[Math.min(10, scoreboardData.size())];
        Label[] scores = new Label[Math.min(10, scoreboardData.size())];
        Label[] avatars = new Label[Math.min(10, scoreboardData.size())];

        for (int i = 0; i < Math.min(10, scoreboardData.size()); i++) {
            hBoxes[i] = new HBox();
            ranks[i] = new Label();
            usernames[i] = new Label();
            lastLogins[i] = new Label();
            scores[i] = new Label();
            avatars[i] = new Label();
        }

        int counter = 0;
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-HH:mm");
        for (int i = 0; i < Math.min(10, scoreboardData.size()); i++) {
            ImageView imageView = new ImageView(new Image(String.valueOf(scoreboardData.get(i).getAvatarURL())));
            imageView.setFitWidth(20);
            imageView.setFitHeight(20);
            if (i != 0 && scoreboardData.get(i).getHighScore() == scoreboardData.get(i - 1).getHighScore()) {
                ranks[i].setText(String.valueOf(i - counter));
                usernames[i].setText(scoreboardData.get(i).getUsername());
//                lastLogins[i].setText(dtf.format(scoreboardData.get(i).getLastLogin()));
                scores[i].setText(String.valueOf(scoreboardData.get(i).getHighScore()));
                counter++;
            } else {
                counter = 0;
                ranks[i].setText(String.valueOf(i + 1));
                usernames[i].setText(scoreboardData.get(i).getUsername());
//                lastLogins[i].setText(dtf.format(scoreboardData.get(i).getLastLogin()));
                scores[i].setText(String.valueOf(scoreboardData.get(i).getHighScore()));
            }
            avatars[i].setGraphic(imageView);
        }
        HBox hBox1 = new HBox();
        Label rank1 = new Label("Rank");
        Label username1 = new Label("Username");
        Label lastLogin1 = new Label("LastLogin");
        Label score1 = new Label("Score");
        Label avatar1 = new Label("avatar");

        hBox1.getChildren().add(rank1);
        hBox1.getChildren().add(username1);
        hBox1.getChildren().add(lastLogin1);
        hBox1.getChildren().add(score1);
        hBox1.getChildren().add(avatar1);

        hBox1.setId("row");
        hBox1.setSpacing(10);
        hBox1.setPadding(new Insets(5, 5, 5, 10));
        hBox1.setPrefHeight(30);
        hBox1.setPrefWidth(650);

        rank1.setPrefHeight(20);
        rank1.setPrefWidth(75);
        rank1.setId("rank");
        rank1.setStyle("-fx-text-fill: #ffd500");
        rank1.setAlignment(Pos.CENTER);
        rank1.setPadding(new Insets(10, 10, 10, 10));

        username1.setPrefHeight(20);
        username1.setPrefWidth(200);
        username1.setId("username");
        username1.setStyle("-fx-text-fill: #ffd500");
        username1.setPadding(new Insets(10, 10, 10, 10));

        lastLogin1.setPrefHeight(20);
        lastLogin1.setPrefWidth(125);
        lastLogin1.setId("username");
        lastLogin1.setStyle("-fx-text-fill: #ffd500");
        lastLogin1.setPadding(new Insets(10, 10, 10, 10));

        score1.setPrefHeight(20);
        score1.setPrefWidth(100);
        score1.setId("score");
        score1.setStyle("-fx-text-fill: #ffd500");
        score1.setPadding(new Insets(10, 10, 10, 10));
        score1.setAlignment(Pos.CENTER);

        avatar1.setPrefHeight(20);
        avatar1.setPrefWidth(100);
        avatar1.setId("avatar");
        avatar1.setStyle("-fx-text-fill: #ffd500");
        avatar1.setPadding(new Insets(10, 10, 10, 10));
        avatar1.setAlignment(Pos.CENTER);


        secondBox.getChildren().add(hBox1);
        for (HBox hBox : hBoxes) secondBox.getChildren().add(hBox);
        for (int i = 0; i < hBoxes.length; i++) {
            hBoxes[i].getChildren().add(ranks[i]);
            hBoxes[i].getChildren().add(usernames[i]);
            hBoxes[i].getChildren().add(lastLogins[i]);
            hBoxes[i].getChildren().add(scores[i]);
            hBoxes[i].getChildren().add(avatars[i]);


            hBoxes[i].setSpacing(10);
            hBoxes[i].setPadding(new Insets(5, 5, 5, 10));
            hBoxes[i].setId("row");
            hBoxes[i].setPrefHeight(30);
            hBoxes[i].setPrefWidth(700);

            ranks[i].setPrefHeight(20);
            ranks[i].setPrefWidth(75);
            ranks[i].setId("rank");
            ranks[i].setPadding(new Insets(10, 10, 10, 10));
            ranks[i].setAlignment(Pos.CENTER);

            usernames[i].setPrefHeight(20);
            usernames[i].setPrefWidth(200);
            usernames[i].setId("username");
            usernames[i].setPadding(new Insets(10, 10, 10, 10));

            lastLogins[i].setPrefHeight(20);
            lastLogins[i].setPrefWidth(125);
            lastLogins[i].setId("lastLogin");
            lastLogins[i].setPadding(new Insets(10, 10, 10, 10));

            scores[i].setPrefHeight(20);
            scores[i].setPrefWidth(100);
            scores[i].setId("score");
            scores[i].setPadding(new Insets(10, 10, 10, 10));
            scores[i].setAlignment(Pos.CENTER);

            avatars[i].setPrefHeight(20);
            avatars[i].setPrefWidth(100);
            avatars[i].setId("avatar");
            avatars[i].setPadding(new Insets(10, 10, 10, 10));
            avatars[i].setAlignment(Pos.CENTER);
        }
        for (int i = 0; i < usernames.length; i++) {
            if (DataBase.getInstance().getLoggedInUser().getNickname().equals(usernames[i].getText())) {
                hBoxes[i].setStyle("-fx-scale-x: 1.01; -fx-scale-y: 1.01; -fx-scale-z: 1.01; -fx-border-color: #ffd500; -fx-border-radius: 10;");
                return;
            }
        }
    }

    private void sortUsers(ArrayList<User> users) {
        users.sort(Comparator.comparing(User::getHighScore)
                .thenComparing(User::getHighScoreTime)
                .thenComparing(User::getNickname)
                .thenComparing(User::getUsername).reversed());
    }

    public void back(MouseEvent mouseEvent) {
        MenuChanger.changeMenu("MainMenu");
    }

    public void nextTrack(MouseEvent mouseEvent) {
    }

    public void playPauseMusic(MouseEvent mouseEvent) {
    }

    public void muteUnmuteMusic(MouseEvent mouseEvent) {
    }
}
