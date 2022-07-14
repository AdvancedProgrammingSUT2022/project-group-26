package com.example.project.views;

import com.example.project.controllers.GameControllers.GameMenuCommandController;
import com.example.project.controllers.GameControllers.PlayGameMenuController;
import com.example.project.models.DataBase;
import com.example.project.models.GameMap;
import com.example.project.models.Player;
import com.example.project.models.User;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
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
        playGameMenuController = new PlayGameMenuController(gamemap, players);
        gameMenuCommandController = new GameMenuCommandController(playGameMenuController, gamemap);
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

    // for panel :
    @FXML
    private Pane panelPane;
    @FXML
    private ProgressBar researchBar;
    @FXML
    private Circle researchImage;
    @FXML
    private ProgressBar healthBar;
    @FXML
    private ProgressBar combatBar;
    @FXML
    private ProgressBar movementBar;
    @FXML
    private Circle troopImage;
    @FXML
    private Label goldAmount;
    @FXML
    private Label happinessAmount;
    @FXML
    private Label scienceAmount;
    @FXML
    private VBox unitData;
    @FXML
    private HBox unitSection;
    @FXML
    private Label StrengthOrProgress;
    @FXML
    private HBox topPicPane; // for giving a pic
    @FXML
    private VBox downPicPane; // for giving a pic

    // for cheat pane :

    @FXML
    private Pane cheatPane;
    @FXML
    private Label cheatLabel;
    @FXML
    private TextField cheatTextField;


    public void initialize() throws MalformedURLException {
        tileDataVBox.setVisible(false);
        combatUnitDataVBox.setVisible(false);
        noneCombatUnitData.setVisible(false);

        CheatPanelFXController.getInstance().setFields(cheatPane, cheatTextField, cheatLabel);
        CheatPanelFXController.getInstance().setControllers(gameMenuCommandController);
        if (gameMenuCommandController == null) {
            gameMenuCommandController = new GameMenuCommandController(new PlayGameMenuController(gamemap, players), gamemap);
            CheatPanelFXController.getInstance().setControllers(gameMenuCommandController);
        }
        cheatTextField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.ENTER) CheatPanelFXController.getInstance().checkCheat();
            }
        });

        ShowMapFXController.getInstance().setData(mapPane, tileDataVBox, combatUnitDataVBox, noneCombatUnitData);
        ShowMapFXController.getInstance().showMap();


        setFieldsOfPanelController();
        ShowPanelFXController.getInstance().setupPics(topPicPane, downPicPane);
        ShowPanelFXController.getInstance().setupPanels();


        cheatPane.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
        cheatPane.setVisible(false);

        update();
    }

    private void setFieldsOfPanelController() {
        ShowPanelFXController temp = ShowPanelFXController.getInstance();
        temp.setPane(panelPane);
        temp.setResearchBar(researchBar);
        temp.setResearchImage(researchImage);
        temp.setHealthBar(healthBar);
        temp.setCombatBar(combatBar);
        temp.setMovementBar(movementBar);
        temp.setTroopImage(troopImage);
        temp.setUnitData(unitData);
        temp.setGoldAmount(goldAmount);
        temp.setHappinessAmount(happinessAmount);
        temp.setScienceAmount(scienceAmount);
        temp.setUnitSection(unitSection);
        temp.setStrengthOrProgress(StrengthOrProgress);
    }

    private void update() {
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(500), actionEvent -> {
            try {
                ShowMapFXController.getInstance().showMap();
                ShowPanelFXController.getInstance().updateStatusBar();
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

    public void openResearchInfo(MouseEvent mouseEvent) {
        //
    }

    public void openCityInfo(MouseEvent mouseEvent) {

    }

    public void openTroopInfo(MouseEvent mouseEvent) {

    }

    public void closeInfo(MouseEvent mouseEvent) {

    }

    public void openDiplomacyInfo(MouseEvent mouseEvent) {

    }

    public void openCheatWindow(MouseEvent mouseEvent) {
        if (!cheatPane.isVisible()) cheatPane.setVisible(true);
        else cheatPane.setVisible(false);
    }

    public void nextTurn(MouseEvent mouseEvent) {
        // todo : next turn
    }
}