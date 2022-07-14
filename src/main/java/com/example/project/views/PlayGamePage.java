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
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
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
            players.add(new Player(new User("mammad", "ad", "")));
            players.add(new Player(new User("mammad", "ad", "")));
            players.add(new Player(new User("mammad", "ad", "")));
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
    private VBox tileDataVBox;
    @FXML
    private VBox combatUnitDataVBox;
    @FXML
    private VBox noneCombatUnitData;
    @FXML
    private VBox unitCommandVbox;


    public void initialize() throws MalformedURLException {
        tileDataVBox.setVisible(false);
        combatUnitDataVBox.setVisible(false);
        noneCombatUnitData.setVisible(false);
        ShowMapFXController.getInstance().setData(mapPane, tileDataVBox, combatUnitDataVBox, noneCombatUnitData);
        UnitCommandFxController.getInstance().setUp(unitCommandVbox);
        ShowMapFXController.getInstance().showMap();
        update();
    }

    private void update() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(500), actionEvent -> {
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

    public void moveMap(KeyEvent keyEvent) {
        if (keyEvent.getCode().getName().equals("Left"))
            ShowMapFXController.getInstance().moveLeft();
        if (keyEvent.getCode().getName().equals("Right"))
            ShowMapFXController.getInstance().moveRight();
        if (keyEvent.getCode().getName().equals("Up"))
            ShowMapFXController.getInstance().moveUp();
        if (keyEvent.getCode().getName().equals("Down"))
            ShowMapFXController.getInstance().moveDown();
    }

    public void closeTileData(MouseEvent mouseEvent) {
        tileDataVBox.setVisible(false);
    }

    public void closeCombatUnitData(MouseEvent mouseEvent) {
        combatUnitDataVBox.setVisible(false);
    }

    public void closeNoneCombatUnitData(MouseEvent mouseEvent) {
        noneCombatUnitData.setVisible(false);
    }
}