package com.example.project.views;

import com.example.project.controllers.GameControllers.GameMenuCommandController;
import com.example.project.controllers.GameControllers.PlayGameMenuController;
import com.example.project.models.GameMap;
import com.example.project.models.Player;
import com.example.project.models.User;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.net.MalformedURLException;
import java.util.ArrayList;

public class PlayGamePage {
    public final static int SCREEN_WIDTH = 1530;
    private final static int SCREEN_HEIGHT = 800;

    public ArrayList<Player> getPlayers() {
        return players;
    }

    //instance variables
    private ArrayList<Player> players = new ArrayList<>();
    private Player thisTurnPlayer;
    private GameMap gamemap;
    private GameMenuCommandController gameMenuCommandController;
    private PlayGameMenuController playGameMenuController;
    private int difficult;
    private boolean isMouseOnTile = true;

    public GameMenuCommandController getGameMenuCommandController() {
        return gameMenuCommandController;
    }

    public PlayGameMenuController getPlayGameMenuController() {
        return playGameMenuController;
    }

    public GameMap getGameMap() {
        return gamemap;
    }

    private static PlayGamePage instance;

    public void setUp() {
        players.add(new Player(new User("ilya", "ilya", "ilya")));
        players.add(new Player(new User("mammad", "ad", "")));
        gamemap = new GameMap(players);
        ShowMapFXController.getInstance().setUp(gamemap, players);
        thisTurnPlayer = players.get(0);
        playGameMenuController = new PlayGameMenuController(gamemap, players);
        gameMenuCommandController = new GameMenuCommandController(playGameMenuController, gamemap);
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
    @FXML
    private HBox unitCommandData;


    public void initialize() throws MalformedURLException {
        combatUnitDataVBox.setVisible(false);
        noneCombatUnitData.setVisible(false);
        ShowMapFXController.getInstance().setData(mapPane, tileDataVBox, combatUnitDataVBox, noneCombatUnitData);
        UnitCommandFxController.getInstance().setUp(unitCommandVbox, unitCommandData);
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
        if (keyEvent.getCode().getName().equals("Space")) {
            int index = instance.players.indexOf(instance.thisTurnPlayer);
            index = (index + 1) % instance.players.size();
            instance.thisTurnPlayer = instance.players.get(index);
        }
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

    public boolean isMouseOnTile() {
        return isMouseOnTile;
    }

    public void setMouseOnTile(boolean mouseOnTile) {
        isMouseOnTile = mouseOnTile;
    }
}