package com.example.project.views;

import com.example.project.controllers.GameControllers.GameMenuCommandController;
import com.example.project.controllers.GameControllers.PlayGameMenuController;
import com.example.project.models.Game;
import com.example.project.models.Player;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.util.Duration;

import java.net.MalformedURLException;

public class PlayGamePage {
    public final static int SCREEN_WIDTH = 1530;
    public final static int SCREEN_HEIGHT = 800;

    //instance variables
    private GameMenuCommandController gameMenuCommandController;
    private PlayGameMenuController playGameMenuController;
    private boolean isMouseOnTile = true;

    public GameMenuCommandController getGameMenuCommandController() {
        return gameMenuCommandController;
    }

    public PlayGameMenuController getPlayGameMenuController() {
        return playGameMenuController;
    }

    private static PlayGamePage instance;

    public void setUp() {
        Game.getInstance().startGame();
        ShowMapFXController.getInstance().setUp(Game.getInstance().getGameMap(), Game.getInstance().getPlayers());
        this.playGameMenuController = new PlayGameMenuController(Game.getInstance().getGameMap(), Game.getInstance().getPlayers());
        gameMenuCommandController = new GameMenuCommandController(playGameMenuController, Game.getInstance().getGameMap());
    }

    public static PlayGamePage getInstance() {
        if (instance == null) instance = new PlayGamePage();
        return instance;
    }

    @FXML
    private VBox cityBannerVBox;
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
    @FXML
    private Pane unitPane;
    @FXML
    private VBox infoVBox;

    // for cheat pane :

    @FXML
    private Pane cheatPane;
    @FXML
    private Label cheatLabel;
    @FXML
    private TextField cheatTextField;
    @FXML
    private ScrollPane scrollPane;


    public void initialize() throws MalformedURLException {
        infoVBox.setBackground(new Background(new BackgroundFill(Color.DARKGREY, new CornerRadii(20), null)));
        scrollPane.setBackground(new Background(new BackgroundFill(Color.AQUAMARINE.darker(), new CornerRadii(20), null)));

        scrollPane.setVisible(false);
        combatUnitDataVBox.setVisible(false);
        noneCombatUnitData.setVisible(false);
        unitCommandData.setVisible(false);
        cityBannerVBox.setVisible(false);

        CheatPanelFXController.getInstance().setFields(cheatPane, cheatTextField, cheatLabel);
        CheatPanelFXController.getInstance().setControllers(instance.gameMenuCommandController);

        //
        unitPane.setBackground(new Background(new BackgroundFill(Color.DARKGRAY, new CornerRadii(50), null)));
        //


        cheatTextField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.ENTER) {
                    CheatPanelFXController.getInstance().checkCheat();
                }
            }
        });

        ShowMapFXController.getInstance().setData(mapPane, panelPane, tileDataVBox, combatUnitDataVBox, noneCombatUnitData, cityBannerVBox);
        UnitCommandFxController.getInstance().setUp(unitCommandVbox, unitCommandData);

        setFieldsOfPanelController();
        ShowPanelFXController.getInstance().setupPics(topPicPane, downPicPane);
        ShowPanelFXController.getInstance().setupPanels();

        ShowInfoFXController.getInstance().setInfoBox(infoVBox);
        ShowInfoFXController.getInstance().setScrollPane(scrollPane);

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
            Game.getInstance().nextTurn();
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

    public void openResearchInfo(MouseEvent mouseEvent) {
        ShowInfoFXController.getInstance().research();
    }

    public void openCityInfo(MouseEvent mouseEvent) {
        ShowInfoFXController.getInstance().city();
    }

    public void openTroopInfo(MouseEvent mouseEvent) {
        ShowInfoFXController.getInstance().troop();
    }

    public void closeInfo(MouseEvent mouseEvent) {
        ShowInfoFXController.getInstance().clearBox();
    }

    public void openDiplomacyInfo(MouseEvent mouseEvent) {
        ShowInfoFXController.getInstance().diplomacy();
    }

    public void openCheatWindow(MouseEvent mouseEvent) {
        if (!cheatPane.isVisible()) cheatPane.setVisible(true);
        else cheatPane.setVisible(false);
    }

    public void nextTurn(MouseEvent mouseEvent) {
        // todo : next turn
    }

    public void notificationInfo(MouseEvent mouseEvent) {
        ShowInfoFXController.getInstance().notification();
    }

    public void closeCityBanner(MouseEvent mouseEvent) {
        cityBannerVBox.setVisible(false);
    }
}