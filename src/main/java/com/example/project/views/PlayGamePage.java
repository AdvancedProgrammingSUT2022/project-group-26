package com.example.project.views;

import com.example.project.controllers.GameControllers.GameMenuCommandController;
import com.example.project.controllers.GameControllers.PlayGameMenuController;
import com.example.project.models.DataBase;
import com.example.project.models.GameMap;
import com.example.project.models.Player;
import com.example.project.models.User;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

import java.net.MalformedURLException;
import java.util.ArrayList;

public class PlayGamePage {
    private ArrayList<Player> players = new ArrayList<>();
    private Player thisTurnPlayer;
    private GameMap gamemap;
    private GameMenuCommandController gameMenuCommandController;
    private PlayGameMenuController playGameMenuController;
    private int difficult;

    private static PlayGamePage instance;

    public void setUp() {
        for (User user : DataBase.getInstance().getUsersDatabase().getUsers()) {
            players.add(new Player(user));
        }
        gamemap = new GameMap(players);
        ShowMapFXController.getInstance().setUp(gamemap, players);
        thisTurnPlayer = players.get(0);
    }

    public static PlayGamePage getInstance() {
        if (instance == null) instance = new PlayGamePage();
        return instance;
    }

    @FXML
    private Pane mapPane;
    @FXML
    private Pane gamePanel;


    public void initialize() throws MalformedURLException {
        ShowMapFXController.getInstance().setPane(mapPane);
//        update();
        ShowMapFXController.getInstance().showMap();

        ShowPanelFXController.getInstance().setPane(gamePanel);
        ShowPanelFXController.getInstance().setupPanels();
    }

    private void update() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.minutes(10), event -> {
            try {
                ShowMapFXController.getInstance().showMap();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }));
        timeline.setCycleCount(-1);
        timeline.play();
    }

    public Player getThisTurnPlayer() {
        return thisTurnPlayer;
    }

    public void setThisTurnPlayer(Player thisTurnPlayer) {
        this.thisTurnPlayer = thisTurnPlayer;
    }


}
