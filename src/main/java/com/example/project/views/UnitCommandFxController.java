package com.example.project.views;

import com.example.project.models.Units.Unit;
import com.example.project.models.Units.UnitNameEnum;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class UnitCommandFxController {
    private static UnitCommandFxController instance;

    public static UnitCommandFxController getInstance() {
        if (instance == null) instance = new UnitCommandFxController();
        return instance;
    }

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
        setDataHandler();
        unitCommandVbox.getChildren().clear();
        unitCommandVbox.setVisible(false);
    }

    private void setDataHandler() {
        wakeUp.setOnMouseMoved(mouseEvent -> setCommandDataHBox(mouseEvent, "wake up"));
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
    }

    private void setCommandDataHBox(MouseEvent mouseEvent, String commandNameString) {
        PlayGamePage.getInstance().setMouseOnTile(false);
        commandName.setText(commandNameString);
        commandData.setLayoutX(mouseEvent.getScreenX() - 130);
        commandData.setLayoutY(mouseEvent.getScreenY() - 20);
    }

    private Unit selectedUnit;

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

    public VBox getUnitCommandVbox() {
        return unitCommandVbox;
    }

    public void setSelectedUnit(Unit selectedUnit) {
        this.selectedUnit = selectedUnit;
        update();
    }

    public void update() {
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
        unitCommandVbox.getChildren().add(alert);
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
        unitCommandVbox.getChildren().add(alert);
        if (selectedUnit.isSleeping())
            unitCommandVbox.getChildren().add(wakeUp);
        else unitCommandVbox.getChildren().add(sleep);
        unitCommandVbox.getChildren().add(deleteUnit);
    }

    private void fillForCombatUnit() {
        unitCommandVbox.getChildren().add(attack);
        unitCommandVbox.getChildren().add(move);
        unitCommandVbox.getChildren().add(doNothing);
        unitCommandVbox.getChildren().add(alert);
        if (selectedUnit.isSleeping())
            unitCommandVbox.getChildren().add(wakeUp);
        else unitCommandVbox.getChildren().add(sleep);
        unitCommandVbox.getChildren().add(fortify);
        unitCommandVbox.getChildren().add(pillage);
        unitCommandVbox.getChildren().add(deleteUnit);
    }

    private void fillForSiege() {
        unitCommandVbox.getChildren().add(setUp);
        unitCommandVbox.getChildren().add(rangedAttack);
        unitCommandVbox.getChildren().add(move);
        unitCommandVbox.getChildren().add(doNothing);
        unitCommandVbox.getChildren().add(alert);
        if (selectedUnit.isSleeping())
            unitCommandVbox.getChildren().add(wakeUp);
        else unitCommandVbox.getChildren().add(sleep);
        unitCommandVbox.getChildren().add(fortify);
        unitCommandVbox.getChildren().add(pillage);
        unitCommandVbox.getChildren().add(deleteUnit);
    }

    private void fillForRangedUnit() {
        unitCommandVbox.getChildren().add(rangedAttack);
        unitCommandVbox.getChildren().add(move);
        unitCommandVbox.getChildren().add(doNothing);
        unitCommandVbox.getChildren().add(alert);
        if (selectedUnit.isSleeping())
            unitCommandVbox.getChildren().add(wakeUp);
        else unitCommandVbox.getChildren().add(sleep);
        unitCommandVbox.getChildren().add(fortify);
        unitCommandVbox.getChildren().add(pillage);
        unitCommandVbox.getChildren().add(deleteUnit);
    }

    public HBox getCommandData() {
        return commandData;
    }
}