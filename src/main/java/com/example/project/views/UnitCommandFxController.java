package com.example.project.views;

import com.example.project.controllers.GameControllers.BuilderController;
import com.example.project.controllers.GameControllers.GameMenuCommandController;
import com.example.project.controllers.Output;
import com.example.project.models.City;
import com.example.project.models.Game;
import com.example.project.models.GameMap;
import com.example.project.models.Player;
import com.example.project.models.Tile.Tile;
import com.example.project.models.Units.Combat.CombatUnit;
import com.example.project.models.Units.Nonecombat.BuilderUnit;
import com.example.project.models.Units.Nonecombat.NoneCombatUnit;
import com.example.project.models.Units.Unit;
import com.example.project.models.Units.UnitNameEnum;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class UnitCommandFxController {
    private static UnitCommandFxController instance;
    private final GameMenuCommandController gameMenuCommandController = PlayGamePage.getInstance().getGameMenuCommandController();
    private final GameMap mainGameMap = Game.getInstance().getGameMap();

    public static UnitCommandFxController getInstance() {
        if (instance == null) instance = new UnitCommandFxController();
        return instance;
    }

    private boolean userMustSelectATile = false;

    public void setUp(VBox unitCommandVbox, HBox commandData) {
        this.unitCommandVbox = unitCommandVbox;
        this.commandData = commandData;
        commandName = (Label) commandData.getChildren().get(0);

        wakeUp = (ImageView) unitCommandVbox.getChildren().get(0);
        sleep = (ImageView) unitCommandVbox.getChildren().get(1);
        alert = (ImageView) unitCommandVbox.getChildren().get(2);
        doNothing = (ImageView) unitCommandVbox.getChildren().get(3);
        attack = (ImageView) unitCommandVbox.getChildren().get(4);
        buildRoad = (ImageView) unitCommandVbox.getChildren().get(5);
        foundCity = (ImageView) unitCommandVbox.getChildren().get(6);
        move = (ImageView) unitCommandVbox.getChildren().get(7);
        repairBuilding = (ImageView) unitCommandVbox.getChildren().get(8);
        fortify = (ImageView) unitCommandVbox.getChildren().get(9);
        deleteUnit = (ImageView) unitCommandVbox.getChildren().get(10);
        implementImprovement = (ImageView) unitCommandVbox.getChildren().get(11);
        clearLand = (ImageView) unitCommandVbox.getChildren().get(12);
        repairImprovement = (ImageView) unitCommandVbox.getChildren().get(13);
        rangedAttack = (ImageView) unitCommandVbox.getChildren().get(14);
        setUp = (ImageView) unitCommandVbox.getChildren().get(15);
        pillage = (ImageView) unitCommandVbox.getChildren().get(16);
        garrison = (ImageView) unitCommandVbox.getChildren().get(17);
        setDataHandler();
        setDataSelect();
        unitCommandVbox.getChildren().clear();
        unitCommandVbox.setVisible(false);
    }

    private void setDataHandler() {
        wakeUp.setOnMouseMoved(mouseEvent -> setCommandDataHBox(mouseEvent, "awake"));
        sleep.setOnMouseMoved(mouseEvent -> setCommandDataHBox(mouseEvent, "sleep"));
        alert.setOnMouseMoved(mouseEvent -> setCommandDataHBox(mouseEvent, "alert"));
        doNothing.setOnMouseMoved(mouseEvent -> setCommandDataHBox(mouseEvent, "do nothing"));
        attack.setOnMouseMoved(mouseEvent -> setCommandDataHBox(mouseEvent, "attack"));
        buildRoad.setOnMouseMoved(mouseEvent -> setCommandDataHBox(mouseEvent, "build road"));
        foundCity.setOnMouseMoved(mouseEvent -> setCommandDataHBox(mouseEvent, "found city"));
        move.setOnMouseMoved(mouseEvent -> setCommandDataHBox(mouseEvent, "move"));
        repairBuilding.setOnMouseMoved(mouseEvent -> setCommandDataHBox(mouseEvent, "repair building"));
        fortify.setOnMouseMoved(mouseEvent -> setCommandDataHBox(mouseEvent, "fortify"));
        deleteUnit.setOnMouseMoved(mouseEvent -> setCommandDataHBox(mouseEvent, "delete unit"));
        implementImprovement.setOnMouseMoved(mouseEvent -> setCommandDataHBox(mouseEvent, "implement improvement"));
        clearLand.setOnMouseMoved(mouseEvent -> setCommandDataHBox(mouseEvent, "clear land"));
        repairImprovement.setOnMouseMoved(mouseEvent -> setCommandDataHBox(mouseEvent, "repair improvement"));
        rangedAttack.setOnMouseMoved(mouseEvent -> setCommandDataHBox(mouseEvent, "ranged attack"));
        setUp.setOnMouseMoved(mouseEvent -> setCommandDataHBox(mouseEvent, "set up"));
        pillage.setOnMouseMoved(mouseEvent -> setCommandDataHBox(mouseEvent, "pillage"));
        garrison.setOnMouseMoved(mouseEvent -> setCommandDataHBox(mouseEvent, "garrison"));

    }

    private void setDataSelect() {
        wakeUp.setOnMouseClicked(mouseEvent -> {
            gameMenuCommandController.wakeUnit(selectedUnit);
            userMustSelectATile = false;
            update();
        });
        sleep.setOnMouseClicked(mouseEvent -> {
            gameMenuCommandController.sleepUnit(selectedUnit);
            userMustSelectATile = false;
            update();
        });
        alert.setOnMouseClicked(mouseEvent -> {
            gameMenuCommandController.alertUnit(selectedUnit);
            userMustSelectATile = false;
            update();
        });
        doNothing.setOnMouseClicked(mouseEvent -> {
            userMustSelectATile = false;
            update();
        });
        attack.setOnMouseClicked(mouseEvent -> {
            userMustSelectATile = true;
            update();
        });
        buildRoad.setOnMouseClicked(mouseEvent -> {
            Output output = gameMenuCommandController.buildRoad((BuilderUnit) selectedUnit, Game.getInstance().getGameMap(),
                    Game.getInstance().getThisTurnPlayer());
            new PopupMessage(Alert.AlertType.INFORMATION, output.toString());
            userMustSelectATile = false;
            update();
        });
        foundCity.setOnMouseClicked(mouseEvent -> {
            gameMenuCommandController.createCity(selectedUnit.getPlayer().getUser().getUsername()
                    , (NoneCombatUnit) selectedUnit, selectedUnit.getPlayer(), Game.getInstance().getPlayers());
            noSelect();
        });
        move.setOnMouseClicked(mouseEvent -> {
            userMustSelectATile = true;
            isMoveSelected = true;
            update();
        });
        repairBuilding.setOnMouseClicked(mouseEvent -> {
            NoneCombatUnit noneCombatUnit = (NoneCombatUnit) selectedUnit;
            Output output = new BuilderController().repairBuilding(
                    Game.getInstance().getThisTurnPlayer(), (BuilderUnit) noneCombatUnit);
            new PopupMessage(Alert.AlertType.INFORMATION, output.toString());
            userMustSelectATile = false;
            update();
        });
        fortify.setOnMouseClicked(mouseEvent -> {
            Output output = gameMenuCommandController.fortifyCombatUnit((CombatUnit) selectedUnit);
            new PopupMessage(Alert.AlertType.INFORMATION, output.toString());
            userMustSelectATile = false;
            update();
        });
        deleteUnit.setOnMouseClicked(mouseEvent -> {
            gameMenuCommandController.deleteUnit(selectedUnit);
            noSelect();
        });
        implementImprovement.setOnMouseClicked(mouseEvent -> {
            NoneCombatUnit noneCombatUnit = (NoneCombatUnit) selectedUnit;
            ShowInfoFXController.getInstance().setImprovements((BuilderUnit) noneCombatUnit);
            userMustSelectATile = false;
            update();
        });
        clearLand.setOnMouseClicked(mouseEvent -> {
            NoneCombatUnit noneCombatUnit = (NoneCombatUnit) selectedUnit;
            Output output = new BuilderController().removeTileFeature(Game.getInstance().getThisTurnPlayer(), (BuilderUnit) noneCombatUnit);
            new PopupMessage(Alert.AlertType.INFORMATION, output.toString());
            gameMenuCommandController.clearLand((BuilderUnit) selectedUnit);
            userMustSelectATile = false;
            update();
        });
        repairImprovement.setOnMouseClicked(mouseEvent -> {
            Output output = gameMenuCommandController.repairImprovement(
                    (BuilderUnit) selectedUnit, Game.getInstance().getThisTurnPlayer());
            new PopupMessage(Alert.AlertType.INFORMATION, output.toString());
            userMustSelectATile = false;
            update();
        });
        rangedAttack.setOnMouseClicked(mouseEvent -> {
            userMustSelectATile = true;
            update();
        });
        setUp.setOnMouseClicked(mouseEvent -> {
            Output output = gameMenuCommandController.siegeSetup((CombatUnit) selectedUnit);
            new PopupMessage(Alert.AlertType.INFORMATION, output.toString());
            userMustSelectATile = false;
            update();
        });
        pillage.setOnMouseClicked(mouseEvent -> {
            Output output = gameMenuCommandController.pillageTile((CombatUnit) selectedUnit);
            new PopupMessage(Alert.AlertType.INFORMATION, output.toString());
            userMustSelectATile = false;
            update();
        });
        garrison.setOnMouseClicked(mouseEvent -> {
            Output output = gameMenuCommandController.garrisonCombatUnit((CombatUnit) selectedUnit);
            new PopupMessage(Alert.AlertType.INFORMATION, output.toString());
            userMustSelectATile = false;
            update();
        });
    }

    private void setCommandDataHBox(MouseEvent mouseEvent, String commandNameString) {
        PlayGamePage.getInstance().setMouseOnTile(false);
        commandName.setText(commandNameString);
        commandData.setLayoutX(mouseEvent.getScreenX() - 130);
        commandData.setLayoutY(mouseEvent.getScreenY() - 20);
        commandData.setVisible(true);
    }

    private Unit selectedUnit;
    private Tile selectedTile;
    private boolean isMoveSelected;

    private VBox unitCommandVbox;
    private HBox commandData;
    private Label commandName;

    private ImageView wakeUp;
    private ImageView sleep;
    private ImageView alert;
    private ImageView doNothing;
    private ImageView attack;
    private ImageView buildRoad;
    private ImageView foundCity;
    private ImageView move;
    private ImageView repairBuilding;
    private ImageView fortify;
    private ImageView deleteUnit;
    private ImageView implementImprovement;
    private ImageView clearLand;
    private ImageView repairImprovement;
    private ImageView rangedAttack;
    private ImageView setUp;
    private ImageView pillage;
    private ImageView garrison;

    public VBox getUnitCommandVbox() {
        return unitCommandVbox;
    }

    public void setSelectedUnit(Unit selectedUnit) {
        if (selectedUnit.getPlayer() == Game.getInstance().getThisTurnPlayer())
            if (selectedUnit instanceof CombatUnit)
                this.selectedUnit = GameMap.getCorrespondingTile(selectedUnit.getPosition(), selectedUnit.getPlayer().getGameMap(),
                        Game.getInstance().getGameMap()).getCombatUnits();
            else
                this.selectedUnit = GameMap.getCorrespondingTile(selectedUnit.getPosition(), selectedUnit.getPlayer().getGameMap(),
                        Game.getInstance().getGameMap()).getNoneCombatUnits();
        ShowPanelFXController.getInstance().showUnitData(selectedUnit);
        update();
    }

    public void setSelectedTile(Tile tile) {
        this.selectedTile = GameMap.getCorrespondingTile(tile,
                Game.getInstance().getThisTurnPlayer().getGameMap(), Game.getInstance().getGameMap());
    }

    public void update() {
        if (selectedUnit == null) {
            unitCommandVbox.setVisible(false);
            return;
        }
        unitCommandVbox.setVisible(true);
        unitCommandVbox.getChildren().clear();
        if (selectedUnit.isACombatUnit())
            if (selectedUnit.isARangedCombatUnit())
                fillForRangedUnit();
            else if (selectedUnit.isASiege())
                fillForSiege();
            else
                fillForCombatUnit();
        else if (selectedUnit.getUnitNameEnum() == UnitNameEnum.SETTLER)
            fillForSettler();
        else if (selectedUnit.getUnitNameEnum() == UnitNameEnum.WORKER)
            fillForWorker();
    }

    private void fillForWorker() {
        unitCommandVbox.getChildren().add(move);
        unitCommandVbox.getChildren().add(doNothing);
        if (selectedUnit.isSleeping())
            unitCommandVbox.getChildren().add(wakeUp);
        else unitCommandVbox.getChildren().add(sleep);
        unitCommandVbox.getChildren().add(buildRoad);
        unitCommandVbox.getChildren().add(clearLand);
        unitCommandVbox.getChildren().add(implementImprovement);
        unitCommandVbox.getChildren().add(repairBuilding);
        if (selectedUnit.getPosition().getImprovement() != null && selectedUnit.getPosition().getImprovement().getIsBroken())
            unitCommandVbox.getChildren().add(repairImprovement);
        unitCommandVbox.getChildren().add(deleteUnit);
    }

    private void fillForSettler() {
        unitCommandVbox.getChildren().add(foundCity);
        unitCommandVbox.getChildren().add(move);
        unitCommandVbox.getChildren().add(doNothing);
        if (selectedUnit.isSleeping())
            unitCommandVbox.getChildren().add(wakeUp);
        else {
            unitCommandVbox.getChildren().add(sleep);
        }
        unitCommandVbox.getChildren().add(deleteUnit);
    }

    private void fillForCombatUnit() {
        unitCommandVbox.getChildren().add(attack);
        unitCommandVbox.getChildren().add(move);
        if (City.isCityCenter(mainGameMap.getIndexI(selectedUnit.getPosition()), mainGameMap.getIndexJ(selectedUnit.getPosition()),
                selectedUnit.getPlayer()) && selectedUnit.getPlayer().getCities().contains(selectedUnit.getPlayer().getCityByTile(selectedUnit.getPosition())))
            unitCommandVbox.getChildren().add(garrison);
        unitCommandVbox.getChildren().add(doNothing);
        if (selectedUnit.isSleeping()) {
            unitCommandVbox.getChildren().add(wakeUp);
            attack.setDisable(true);
        } else {
            unitCommandVbox.getChildren().add(alert);
            unitCommandVbox.getChildren().add(sleep);
            attack.setDisable(false);
        }
        unitCommandVbox.getChildren().add(fortify);
        unitCommandVbox.getChildren().add(pillage);
        unitCommandVbox.getChildren().add(deleteUnit);
    }

    private void fillForSiege() {
        unitCommandVbox.getChildren().add(setUp);
        unitCommandVbox.getChildren().add(rangedAttack);
        unitCommandVbox.getChildren().add(move);
        if (City.isCityCenter(mainGameMap.getIndexI(selectedUnit.getPosition()), mainGameMap.getIndexJ(selectedUnit.getPosition()),
                selectedUnit.getPlayer()) && selectedUnit.getPlayer().getCities().contains(selectedUnit.getPlayer().getCityByTile(selectedUnit.getPosition())))
            unitCommandVbox.getChildren().add(garrison);
        unitCommandVbox.getChildren().add(doNothing);
        if (selectedUnit.isSleeping()) {
            unitCommandVbox.getChildren().add(wakeUp);
            rangedAttack.setDisable(true);
        } else {
            unitCommandVbox.getChildren().add(alert);
            unitCommandVbox.getChildren().add(sleep);
            rangedAttack.setDisable(false);
        }
        unitCommandVbox.getChildren().add(fortify);
        unitCommandVbox.getChildren().add(pillage);
        unitCommandVbox.getChildren().add(deleteUnit);
    }

    private void fillForRangedUnit() {
        unitCommandVbox.getChildren().add(rangedAttack);
        unitCommandVbox.getChildren().add(move);
        if (City.isCityCenter(mainGameMap.getIndexI(selectedUnit.getPosition()), mainGameMap.getIndexJ(selectedUnit.getPosition()),
                selectedUnit.getPlayer()) && selectedUnit.getPlayer().getCities().contains(selectedUnit.getPlayer().getCityByTile(selectedUnit.getPosition())))
            unitCommandVbox.getChildren().add(garrison);
        unitCommandVbox.getChildren().add(doNothing);
        if (selectedUnit.isSleeping()) {
            rangedAttack.setDisable(true);
            unitCommandVbox.getChildren().add(wakeUp);
        } else {
            rangedAttack.setDisable(false);
            unitCommandVbox.getChildren().add(alert);
            unitCommandVbox.getChildren().add(sleep);
        }
        unitCommandVbox.getChildren().add(fortify);
        unitCommandVbox.getChildren().add(pillage);
        unitCommandVbox.getChildren().add(deleteUnit);
    }

    public HBox getCommandData() {
        return commandData;
    }

    private void noSelect() {
        selectedUnit = null;
        update();
    }

    public boolean isUserMustSelectATile() {
        return userMustSelectATile;
    }

    public void setUserMustSelectATile(boolean userMustSelectATile) {
        this.userMustSelectATile = userMustSelectATile;
    }

    public boolean isMoveSelected() {
        return isMoveSelected;
    }

    public void setMoveSelected(boolean moveSelected) {
        isMoveSelected = moveSelected;
    }

    public void doAction() {
        if (isMoveSelected) {
            //TODO: error for illegal move
            gameMenuCommandController.addRoute(mainGameMap.getIndexI(selectedTile), mainGameMap.getIndexJ(selectedTile),
                    mainGameMap, selectedUnit, Game.getInstance().getThisTurnPlayer());
            gameMenuCommandController.moveFromRoute(selectedUnit);
        } else {
            boolean result = gameMenuCommandController.attackToATile((CombatUnit) selectedUnit, selectedTile);
            if (!result)
                new PopupMessage(Alert.AlertType.ERROR, Output.INVALID_SELECTED_TILE_TO_ATTACK.toString());
        }
        selectedTile = null;
        isMoveSelected = false;
        userMustSelectATile = false;
    }
}