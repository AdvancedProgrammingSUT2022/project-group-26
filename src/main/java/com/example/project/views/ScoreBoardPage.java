package com.example.project.views;

import com.example.project.models.DataBase;
import com.example.project.models.User;
import com.example.project.models.UsersDatabase;
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
import java.util.ArrayList;

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
        showBoard();
    }

    public void showBoard() {
        secondBox.getChildren().clear();
        ArrayList<User> scoreboardData = UsersDatabase.getInstance().getUsers();
        HBox[] hBoxes = new HBox[Math.min(10, scoreboardData.size())];
        Label[] ranks = new Label[Math.min(10, scoreboardData.size())];
        Label[] nicknames = new Label[Math.min(10, scoreboardData.size())];
        Label[] scores = new Label[Math.min(10, scoreboardData.size())];
        Label[] isOnline = new Label[Math.min(10, scoreboardData.size())];

        for (int i = 0; i < Math.min(10, scoreboardData.size()); i++) {
            hBoxes[i] = new HBox();
            ranks[i] = new Label();
            nicknames[i] = new Label();
            scores[i] = new Label();
            isOnline[i] = new Label();
        }

        int counter = 0;
        for (int i = 0; i < Math.min(10, scoreboardData.size()); i++) {
            ImageView imageView = scoreboardData.get(i).isOnline() ? new ImageView(green) : new ImageView(red);
            imageView.setFitWidth(15);
            imageView.setFitHeight(15);
            if (i != 0 && scoreboardData.get(i).getHighScore() == scoreboardData.get(i - 1).getHighScore()) {
                ranks[i].setText(String.valueOf(i - counter));
                nicknames[i].setText(scoreboardData.get(i).getNickname());
                scores[i].setText(String.valueOf(scoreboardData.get(i).getHighScore()));
                counter++;
            } else {
                counter = 0;
                ranks[i].setText(String.valueOf(i + 1));
                nicknames[i].setText(scoreboardData.get(i).getNickname());
                scores[i].setText(String.valueOf(scoreboardData.get(i).getHighScore()));
            }
            isOnline[i].setGraphic(imageView);
        }
        HBox hBox1 = new HBox();
        Label rank1 = new Label("Rank");
        Label nickname1 = new Label("Nickname");
        Label score1 = new Label("Score");
        Label label = new Label("Online");
        hBox1.getChildren().add(rank1);
        hBox1.getChildren().add(nickname1);
        hBox1.getChildren().add(score1);
        hBox1.getChildren().add(label);
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

        nickname1.setPrefHeight(20);
        nickname1.setPrefWidth(325);
        nickname1.setId("nickname");
        nickname1.setStyle("-fx-text-fill: #ffd500");
        nickname1.setPadding(new Insets(10, 10, 10, 10));

        score1.setPrefHeight(20);
        score1.setPrefWidth(100);
        score1.setId("score");
        score1.setStyle("-fx-text-fill: #ffd500");
        score1.setPadding(new Insets(10, 10, 10, 10));
        score1.setAlignment(Pos.CENTER);

        label.setPrefHeight(20);
        label.setPrefWidth(100);
        label.setId("Online");
        label.setStyle("-fx-text-fill: #ffd500");
        label.setPadding(new Insets(10, 10, 10, 10));
        label.setAlignment(Pos.CENTER);
        secondBox.getChildren().add(hBox1);
        for (HBox hBox : hBoxes) secondBox.getChildren().add(hBox);
        for (int i = 0; i < hBoxes.length; i++) {
            hBoxes[i].getChildren().add(ranks[i]);
            hBoxes[i].getChildren().add(nicknames[i]);
            hBoxes[i].getChildren().add(scores[i]);
            hBoxes[i].getChildren().add(isOnline[i]);
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

            nicknames[i].setPrefHeight(20);
            nicknames[i].setPrefWidth(325);
            nicknames[i].setId("nickname");
            nicknames[i].setPadding(new Insets(10, 10, 10, 10));

            scores[i].setPrefHeight(20);
            scores[i].setPrefWidth(100);
            scores[i].setId("score");
            scores[i].setPadding(new Insets(10, 10, 10, 10));
            scores[i].setAlignment(Pos.CENTER);

            isOnline[i].setPrefHeight(20);
            isOnline[i].setPrefWidth(100);
            isOnline[i].setId("Online");
            isOnline[i].setPadding(new Insets(10, 10, 10, 10));
            isOnline[i].setAlignment(Pos.CENTER);
        }
        for (int i = 0; i < nicknames.length; i++) {
            if (DataBase.getInstance().getLoggedInUser().getNickname().equals(nicknames[i].getText())) {
                hBoxes[i].setStyle("-fx-scale-x: 1.01; -fx-scale-y: 1.01; -fx-scale-z: 1.01; -fx-border-color: #ffd500; -fx-border-radius: 10;");
                return;
            }
        }
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
