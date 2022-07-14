package com.example.project.views;

import com.example.project.models.City;
import com.example.project.models.Player;
import com.example.project.models.Units.Combat.CombatUnits;
import com.example.project.models.Units.Nonecombat.BuilderUnit;
import com.example.project.models.Units.Nonecombat.NoneCombatUnits;
import com.example.project.models.Units.Unit;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ShowPanelFXController {
    private static ShowPanelFXController instance = null;

    @FXML
    private Pane pane;
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
    private Label strengthOrProgress;
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


    private ShowPanelFXController() {
    }

    public static ShowPanelFXController getInstance() {
        if (instance == null) instance = new ShowPanelFXController();
        return instance;
    }


    public void setPane(Pane pane) {
        this.pane = pane;
    }

    public void setResearchBar(ProgressBar researchBar) {
        this.researchBar = researchBar;
    }

    public void setResearchImage(Circle researchImage) {
        this.researchImage = researchImage;
    }

    public void setHealthBar(ProgressBar healthBar) {
        this.healthBar = healthBar;
    }

    public void setCombatBar(ProgressBar combatBar) {
        this.combatBar = combatBar;
    }

    public void setMovementBar(ProgressBar movementBar) {
        this.movementBar = movementBar;
    }

    public void setTroopImage(Circle troopImage) {
        this.troopImage = troopImage;
    }

    public void setGoldAmount(Label goldAmount) {
        this.goldAmount = goldAmount;
    }

    public void setHappinessAmount(Label happinessAmount) {
        this.happinessAmount = happinessAmount;
    }

    public void setScienceAmount(Label scienceAmount) {
        this.scienceAmount = scienceAmount;
    }

    public void setUnitData(VBox unitData) {
        this.unitData = unitData;
    }

    public void setUnitSection(HBox unitSection) {
        this.unitSection = unitSection;
    }

    public void setStrengthOrProgress(Label strengthOrProgress) {
        this.strengthOrProgress = strengthOrProgress;
    }

    public void setupPanels() {
        setupStatusBar();
//        setupDataBar();
//        setupSelectedObjectData();

    }

    //    private void setupSelectedObjectData() {
//        // hbox
//        // in it circle and hbox
//        HBox objectData = new HBox();
//
//        objectData.setPrefWidth(700);
//        objectData.setPrefHeight(100);
//        objectData.setLayoutY(650);
//        objectData.setPadding(new Insets(15, 12, 15, 12));
//        objectData.setSpacing(10);
//        objectData.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, null, null)));
//
//        Circle researchImage = new Circle();
//        researchImage.setRadius(90);
//        researchImage.setFill(Color.BLUEVIOLET);
//
//        VBox data = new VBox();
//
//        Label name = new Label();
////        Label health = new Label();
////        Label attack = new Label();
////        Label name = new Label();
//        data.getChildren().addAll();
//        objectData.getChildren().addAll(researchImage, data);
//
//        pane.getChildren().add(objectData);
//
//    }
//
//    private void setupDataBar() {
//        VBox dataBar = new VBox();
//
//        dataBar.setPrefWidth(90);
//        dataBar.setPrefHeight(820);
//        dataBar.setPadding(new Insets(15, 12, 15, 12));
//        dataBar.setSpacing(10);
//        dataBar.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, null, null)));
//
//
//        // todo : add a hbox -> in it : circle and shit
//        Circle researchImage = new Circle();
//        researchImage.setRadius(30); // ??!
//        researchImage.setFill(Color.BLUEVIOLET);
////        researchImage.setFill(new ImagePattern(new Image(new FileInputStream("src of the research"))));
//
//        // add a custom vbox that fills and erases ?!
//
//    }
//


    private void setupStatusBar() {
        goldAmount.setTextFill(Color.GOLD);
        happinessAmount.setTextFill(Color.DARKGRAY);
        scienceAmount.setTextFill(Color.DARKVIOLET);

        goldAmount.setFont(new Font(18));
        happinessAmount.setFont(new Font(18));
        scienceAmount.setFont(new Font(18));

    }


    // todo : call this on start of the turn
    public void updateResearchBar(){
        // todo : give a correct pic!
        try {
            researchImage.setFill(new ImagePattern(new Image(new FileInputStream("src/main/resources/Image/Game/Tech/acoustics.png"))));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        researchBar.setProgress(0.5); // todo : ask the src code from ilya
    }
    public void updateStatusBar() {
        Player player = PlayGamePage.getInstance().getThisTurnPlayer();
        goldAmount.setText(String.valueOf(player.getGold()));
        happinessAmount.setText(String.valueOf(player.getHappiness()));
        scienceAmount.setText(String.valueOf(player.getScience()));

        // <<<----------------->>>
    }

    public void setupPics(HBox topPicPane, VBox downPicPane) {
        try {
            ((Circle) downPicPane.getChildren().get(0)).setFill(new ImagePattern(new Image(new FileInputStream("src/main/resources/Image/Game/Panel/cheat.png"))));
            ((Circle) downPicPane.getChildren().get(1)).setFill(new ImagePattern(new Image(new FileInputStream("src/main/resources/Image/Game/Panel/next.png"))));
            ((Circle) topPicPane.getChildren().get(0)).setFill(new ImagePattern(new Image(new FileInputStream("src/main/resources/Image/Game/Panel/research.png"))));
            ((Circle) topPicPane.getChildren().get(1)).setFill(new ImagePattern(new Image(new FileInputStream("src/main/resources/Image/Game/Panel/cities.png"))));
            ((Circle) topPicPane.getChildren().get(2)).setFill(new ImagePattern(new Image(new FileInputStream("src/main/resources/Image/Game/Panel/troops.png"))));
            ((Circle) topPicPane.getChildren().get(3)).setFill(new ImagePattern(new Image(new FileInputStream("src/main/resources/Image/Game/Panel/diplomacy.png"))));
            ((Circle) topPicPane.getChildren().get(4)).setFill(new ImagePattern(new Image(new FileInputStream("src/main/resources/Image/Game/Panel/backArrow.png"))));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void showCityData(City city) {

    }

    public void showUnitData(Unit unit) {
        // todo : fix this
        try {
            troopImage.setFill(new ImagePattern(new Image(new FileInputStream("src/main/resources/Image/Game/Unit/archer.png"))));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        ((Label) unitData.getChildren().get(0)).setText("unit name : " + unit.getUnitNameEnum().getName());
        ((Label) unitData.getChildren().get(1)).setText("unit type : " + unit.getUnitTypeEnum().getName());
        ((Label) unitData.getChildren().get(2)).setText("unit cost : " + unit.getUnitNameEnum().getCost());
        ((Label) unitData.getChildren().get(3)).setText("unit status : " + unit.getStatus()); // todo : finish get status func
        if (unit.isACombatUnit()) showCombatData((CombatUnits) unit);
        else if (unit.isAWorker()) showBuilderData((BuilderUnit) unit);
        else if (unit.isACivilian()) showCivilianData((NoneCombatUnits) unit);
    }

    private void showCombatData(CombatUnits combatUnits) {
        // health -- strength -- movement -- work
        strengthOrProgress.setText("strength");
        combatBar.setProgress(combatUnits.calculateAttack() / combatUnits.getCombatStrength());
        healthBar.setProgress(combatUnits.getHealth() / 100); // todo *** using a const field
        movementBar.setProgress(combatUnits.getMovement() / combatUnits.getMaxMovement());
    }

    private void showBuilderData(BuilderUnit builderUnit) {
        strengthOrProgress.setText("progress");
        movementBar.setProgress(builderUnit.getMovement() / builderUnit.getMaxMovement());
        combatBar.setProgress(1); // todo : fix
        healthBar.setProgress(1);
    }

    private void showCivilianData(NoneCombatUnits civilian) {
        strengthOrProgress.setText("available");
        movementBar.setProgress(civilian.getMovement() / civilian.getMaxMovement());
        combatBar.setProgress(1); // todo : fix
        healthBar.setProgress(1);
    }

}
