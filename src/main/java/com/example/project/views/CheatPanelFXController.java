package com.example.project.views;

import com.example.project.controllers.GameControllers.GameMenuCommandController;
import com.example.project.models.DataBase;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheatPanelFXController {
    private GameMenuCommandController gameMenuCommandController;


    private Pane pane;
    private TextField textField;
    private Label label;


    private static CheatPanelFXController instance;

    private CheatPanelFXController() {
    }

    public static CheatPanelFXController getInstance() {
        if (instance == null) instance = new CheatPanelFXController();
        return instance;
    }

    public void setFields(Pane pane, TextField textField, Label label) {
        this.pane = pane;
        this.textField = textField;
        this.label = label;
    }

    public void checkCheat() {
        String input = textField.getText().trim();
        Matcher matcher = null;
        if ((matcher = getCommandMatcher(input, PlayGameCommandsRegex.INCREASE_TURN.toString())) != null) {
            gameMenuCommandController.increaseTurn(matcher, PlayGamePage.getInstance().getThisTurnPlayer());
            label.setText("added cheat");
        } else if ((matcher = getCommandMatcher(input, PlayGameCommandsRegex.INCREASE_FOOD.toString())) != null) {
            gameMenuCommandController.increaseFood(matcher, PlayGamePage.getInstance().getThisTurnPlayer());
            label.setText("added cheat");
        } else if ((matcher = getCommandMatcher(input, PlayGameCommandsRegex.INCREASE_HAPPINESS.toString())) != null) {
            gameMenuCommandController.increaseHappiness(matcher, PlayGamePage.getInstance().getThisTurnPlayer());
            label.setText("added cheat");
        } else if ((matcher = getCommandMatcher(input, PlayGameCommandsRegex.INCREASE_GOLD.toString())) != null) {
            gameMenuCommandController.increaseGold(matcher, PlayGamePage.getInstance().getThisTurnPlayer());
            label.setText("added cheat");
        } else if ((matcher = getCommandMatcher(input, PlayGameCommandsRegex.BUY_TECHNOLOGY.toString())) != null) {
            gameMenuCommandController.buyTechnology(matcher, PlayGamePage.getInstance().getThisTurnPlayer());
            label.setText("added cheat");
        } else if ((matcher = getCommandMatcher(input, PlayGameCommandsRegex.INCREASE_SCIENCE.toString())) != null) {
            gameMenuCommandController.increaseScience(matcher, PlayGamePage.getInstance().getThisTurnPlayer());
            label.setText("added cheat");
        } else if ((matcher = getCommandMatcher(input, PlayGameCommandsRegex.WIN.toString())) != null) {
            label.setText("added cheat");
            return;//with this player as winner
        } else if ((matcher = getCommandMatcher(input, PlayGameCommandsRegex.INCREASE_MOVEMENT.toString())) != null) {
            gameMenuCommandController.increaseMovement(matcher, PlayGamePage.getInstance().getThisTurnPlayer(), PlayGamePage.getInstance().getThisTurnPlayer().getGameMap());
            label.setText("added cheat");
        } else {
            label.setText("invalid cheat code !");
        }
    }

    public Matcher getCommandMatcher(String input, String regex) {
        Matcher matcher = Pattern.compile(regex).matcher(input);

        if (matcher.matches())
            return matcher;

        return null;
    }

    public void setControllers(GameMenuCommandController gameMenuCommandController) {
        this.gameMenuCommandController = gameMenuCommandController;
    }
}
