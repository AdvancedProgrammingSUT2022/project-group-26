package com.example.project.views;

import com.example.project.models.Game;
import com.example.project.models.Player;
import com.example.project.models.Technology.Tech;
import com.example.project.models.Technology.TechEnum;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;


public class TechTreePage {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private Label invalidTech;
    @FXML
    private TextField techName;
    @FXML
    private Rectangle agriculture;
    @FXML
    private Rectangle pottery;
    @FXML
    private Rectangle animalHusbandry;
    @FXML
    private Rectangle archery;
    @FXML
    private Rectangle mining;
    @FXML
    private Rectangle calendar;
    @FXML
    private Rectangle writing;
    @FXML
    private Rectangle trapping;
    @FXML
    private Rectangle theWheel;
    @FXML
    private Rectangle masonry;
    @FXML
    private Rectangle bronzeWorking;
    @FXML
    private Rectangle philosophy;
    @FXML
    private Rectangle horsebackRiding;
    @FXML
    private Rectangle mathematics;
    @FXML
    private Rectangle construction;
    @FXML
    private Rectangle ironWorking;
    @FXML
    private Rectangle theology;
    @FXML
    private Rectangle civilService;
    @FXML
    private Rectangle currency;
    @FXML
    private Rectangle engineering;
    @FXML
    private Rectangle metalCasting;
    @FXML
    private Rectangle education;
    @FXML
    private Rectangle chivalry;
    @FXML
    private Rectangle machinery;
    @FXML
    private Rectangle physics;
    @FXML
    private Rectangle steel;
    @FXML
    private Rectangle acoustics;
    @FXML
    private Rectangle banking;
    @FXML
    private Rectangle printingPress;
    @FXML
    private Rectangle gunpowder;
    @FXML
    private Rectangle economics;
    @FXML
    private Rectangle chemistry;
    @FXML
    private Rectangle metallurgy;
    @FXML
    private Rectangle scientificTheory;
    @FXML
    private Rectangle militaryScience;
    @FXML
    private Rectangle fertilizer;
    @FXML
    private Rectangle rifling;
    @FXML
    private Rectangle biology;
    @FXML
    private Rectangle steamPower;
    @FXML
    private Rectangle electricity;
    @FXML
    private Rectangle replaceableParts;
    @FXML
    private Rectangle railroad;
    @FXML
    private Rectangle dynamite;
    @FXML
    private Rectangle telegraph;
    @FXML
    private Rectangle radio;
    @FXML
    private Rectangle combustion;

    public void initialize() {
        paintAvailable();
        paintCurrentlyResearching();
        paintResearched();
    }

    public void searchTech(MouseEvent mouseEvent) {
        invalidTech.setVisible(false);
        double x = searchTechPosition(techName.getText());
        scrollPane.setHvalue(x / 6400);
        if (x == -1) {
            invalidTech.setVisible(true);
            techName.setStyle("-fx-border-color: red");
        }
    }

    public void changeVisibility(KeyEvent keyEvent) {
        invalidTech.setVisible(false);
        techName.setStyle("-fx-border-color: #11313d");
    }

    private double searchTechPosition(String techName) {
        if (techName.equals("Agriculture")) return agriculture.getLayoutX();
        else if (techName.equals("Pottery")) return pottery.getLayoutX();
        else if (techName.equals("Animal Husbandry")) return animalHusbandry.getLayoutX();
        else if (techName.equals("Archery")) return archery.getLayoutX();
        else if (techName.equals("Mining")) return mining.getLayoutX();
        else if (techName.equals("Calendar")) return calendar.getLayoutX();
        else if (techName.equals("Writing")) return writing.getLayoutX();
        else if (techName.equals("Trapping")) return trapping.getLayoutX();
        else if (techName.equals("The Wheel")) return theWheel.getLayoutX();
        else if (techName.equals("Masonry")) return masonry.getLayoutX();
        else if (techName.equals("Bronze Working")) return bronzeWorking.getLayoutX();
        else if (techName.equals("Philosophy")) return philosophy.getLayoutX();
        else if (techName.equals("Horseback Riding")) return horsebackRiding.getLayoutX();
        else if (techName.equals("Mathematics")) return mathematics.getLayoutX();
        else if (techName.equals("Construction")) return construction.getLayoutX();
        else if (techName.equals("Iron Working")) return ironWorking.getLayoutX();
        else if (techName.equals("Theology")) return theology.getLayoutX();
        else if (techName.equals("Civil Service")) return civilService.getLayoutX();
        else if (techName.equals("Currency")) return currency.getLayoutX();
        else if (techName.equals("Engineering")) return engineering.getLayoutX();
        else if (techName.equals("Metal Casting")) return metalCasting.getLayoutX();
        else if (techName.equals("Education")) return education.getLayoutX();
        else if (techName.equals("Chivalry")) return chivalry.getLayoutX();
        else if (techName.equals("Machinery")) return machinery.getLayoutX();
        else if (techName.equals("Physics")) return physics.getLayoutX();
        else if (techName.equals("Steel")) return steel.getLayoutX();
        else if (techName.equals("Acoustics")) return acoustics.getLayoutX();
        else if (techName.equals("Banking")) return banking.getLayoutX();
        else if (techName.equals("Printing Press")) return printingPress.getLayoutX();
        else if (techName.equals("Gunpowder")) return gunpowder.getLayoutX();
        else if (techName.equals("Economics")) return economics.getLayoutX();
        else if (techName.equals("Chemistry")) return chemistry.getLayoutX();
        else if (techName.equals("Metallurgy")) return metallurgy.getLayoutX();
        else if (techName.equals("Scientific Theory")) return scientificTheory.getLayoutX();
        else if (techName.equals("Military Science")) return militaryScience.getLayoutX();
        else if (techName.equals("Fertilizer")) return fertilizer.getLayoutX();
        else if (techName.equals("Rifling")) return rifling.getLayoutX();
        else if (techName.equals("Biology")) return biology.getLayoutX();
        else if (techName.equals("Steam Power")) return steamPower.getLayoutX();
        else if (techName.equals("Electricity")) return electricity.getLayoutX();
        else if (techName.equals("Replaceable Parts")) return replaceableParts.getLayoutX();
        else if (techName.equals("Railroad")) return railroad.getLayoutX();
        else if (techName.equals("Dynamite")) return dynamite.getLayoutX();
        else if (techName.equals("Telegraph")) return telegraph.getLayoutX();
        else if (techName.equals("Radio")) return radio.getLayoutX();
        else if (techName.equals("Combustion")) return combustion.getLayoutX();
        else return -1;
    }

    private void paintAvailable() {
        Player player = Game.getInstance().getThisTurnPlayer();
        ArrayList<Tech> availableTech = player.getPossibleTechnology();
        ArrayList<TechEnum> availableTechEnum = new ArrayList<>();
        for (Tech tech : availableTech) {
            availableTechEnum.add(tech.getTechName());
        }

        if (availableTechEnum.contains(TechEnum.AGRICULTURE)) agriculture.setFill(Paint.valueOf("19831e"));
        if (availableTechEnum.contains(TechEnum.POTTERY)) pottery.setFill(Paint.valueOf("19831e"));
        if (availableTechEnum.contains(TechEnum.ANIMAL_HUSBANDRY))
            animalHusbandry.setFill(Paint.valueOf("19831e"));
        if (availableTechEnum.contains(TechEnum.ARCHERY)) archery.setFill(Paint.valueOf("19831e"));
        if (availableTechEnum.contains(TechEnum.MINING)) mining.setFill(Paint.valueOf("19831e"));
        if (availableTechEnum.contains(TechEnum.CALENDAR)) calendar.setFill(Paint.valueOf("19831e"));
        if (availableTechEnum.contains(TechEnum.WRITING)) writing.setFill(Paint.valueOf("19831e"));
        if (availableTechEnum.contains(TechEnum.TRAPPING)) trapping.setFill(Paint.valueOf("19831e"));
        if (availableTechEnum.contains(TechEnum.THE_WHEEL)) theWheel.setFill(Paint.valueOf("19831e"));
        if (availableTechEnum.contains(TechEnum.MASONRY)) masonry.setFill(Paint.valueOf("19831e"));
        if (availableTechEnum.contains(TechEnum.BRONZE_WORKING))
            bronzeWorking.setFill(Paint.valueOf("19831e"));
        if (availableTechEnum.contains(TechEnum.PHILOSOPHY)) philosophy.setFill(Paint.valueOf("19831e"));
        if (availableTechEnum.contains(TechEnum.HORSEBACK_RIDING))
            horsebackRiding.setFill(Paint.valueOf("19831e"));
        if (availableTechEnum.contains(TechEnum.MATHEMATICS)) mathematics.setFill(Paint.valueOf("19831e"));
        if (availableTechEnum.contains(TechEnum.CONSTRUCTION))
            construction.setFill(Paint.valueOf("19831e"));
        if (availableTechEnum.contains(TechEnum.IRON_WORKING)) ironWorking.setFill(Paint.valueOf("19831e"));
        if (availableTechEnum.contains(TechEnum.THEOLOGY)) theology.setFill(Paint.valueOf("19831e"));
        if (availableTechEnum.contains(TechEnum.CIVIL_SERVICE))
            civilService.setFill(Paint.valueOf("19831e"));
        if (availableTechEnum.contains(TechEnum.CURRENCY)) currency.setFill(Paint.valueOf("19831e"));
        if (availableTechEnum.contains(TechEnum.ENGINEERING)) engineering.setFill(Paint.valueOf("19831e"));
        if (availableTechEnum.contains(TechEnum.METAL_CASTING))
            metalCasting.setFill(Paint.valueOf("19831e"));
        if (availableTechEnum.contains(TechEnum.EDUCATION)) education.setFill(Paint.valueOf("19831e"));
        if (availableTechEnum.contains(TechEnum.CHIVALRY)) chivalry.setFill(Paint.valueOf("19831e"));
        if (availableTechEnum.contains(TechEnum.MACHINERY)) machinery.setFill(Paint.valueOf("19831e"));
        if (availableTechEnum.contains(TechEnum.PHYSICS)) physics.setFill(Paint.valueOf("19831e"));
        if (availableTechEnum.contains(TechEnum.STEEL)) steel.setFill(Paint.valueOf("19831e"));
        if (availableTechEnum.contains(TechEnum.ACOUSTICS)) acoustics.setFill(Paint.valueOf("19831e"));
        if (availableTechEnum.contains(TechEnum.BANKING)) banking.setFill(Paint.valueOf("19831e"));
        if (availableTechEnum.contains(TechEnum.PRINTING_PRESS))
            printingPress.setFill(Paint.valueOf("19831e"));
        if (availableTechEnum.contains(TechEnum.GUN_POWDER)) gunpowder.setFill(Paint.valueOf("19831e"));
        if (availableTechEnum.contains(TechEnum.ECONOMICS)) economics.setFill(Paint.valueOf("19831e"));
        if (availableTechEnum.contains(TechEnum.CHEMISTRY)) chemistry.setFill(Paint.valueOf("19831e"));
        if (availableTechEnum.contains(TechEnum.METALLURGY)) metallurgy.setFill(Paint.valueOf("19831e"));
        if (availableTechEnum.contains(TechEnum.SCIENTIFIC_THEORY))
            scientificTheory.setFill(Paint.valueOf("19831e"));
        if (availableTechEnum.contains(TechEnum.MILITARY_SCIENCE))
            militaryScience.setFill(Paint.valueOf("19831e"));
        if (availableTechEnum.contains(TechEnum.FERTILIZER)) fertilizer.setFill(Paint.valueOf("19831e"));
        if (availableTechEnum.contains(TechEnum.RIFLING)) rifling.setFill(Paint.valueOf("19831e"));
        if (availableTechEnum.contains(TechEnum.BIOLOGY)) biology.setFill(Paint.valueOf("19831e"));
        if (availableTechEnum.contains(TechEnum.STEAM_POWER)) steamPower.setFill(Paint.valueOf("19831e"));
        if (availableTechEnum.contains(TechEnum.ELECTRICITY)) electricity.setFill(Paint.valueOf("19831e"));
        if (availableTechEnum.contains(TechEnum.REPLACEABLE_PARTS))
            replaceableParts.setFill(Paint.valueOf("19831e"));
        if (availableTechEnum.contains(TechEnum.RAILROAD)) railroad.setFill(Paint.valueOf("19831e"));
        if (availableTechEnum.contains(TechEnum.DYNAMITE)) dynamite.setFill(Paint.valueOf("19831e"));
        if (availableTechEnum.contains(TechEnum.TELEGRAPH)) telegraph.setFill(Paint.valueOf("19831e"));
        if (availableTechEnum.contains(TechEnum.RADIO)) radio.setFill(Paint.valueOf("19831e"));
        if (availableTechEnum.contains(TechEnum.COMBUSTION)) combustion.setFill(Paint.valueOf("19831e"));

    }

    private void paintCurrentlyResearching() {
        Player player = Game.getInstance().getThisTurnPlayer();
        TechEnum current = null;
        if (player.getTechInResearch() != null)
            current = player.getTechInResearch().getTechName();

        if (current == TechEnum.AGRICULTURE) agriculture.setFill(Paint.valueOf("005eab"));
        else if (current == TechEnum.POTTERY) pottery.setFill(Paint.valueOf("005eab"));
        else if (current == TechEnum.ANIMAL_HUSBANDRY) animalHusbandry.setFill(Paint.valueOf("005eab"));
        else if (current == TechEnum.ARCHERY) archery.setFill(Paint.valueOf("005eab"));
        else if (current == TechEnum.MINING) mining.setFill(Paint.valueOf("005eab"));
        else if (current == TechEnum.CALENDAR) calendar.setFill(Paint.valueOf("005eab"));
        else if (current == TechEnum.WRITING) writing.setFill(Paint.valueOf("005eab"));
        else if (current == TechEnum.TRAPPING) trapping.setFill(Paint.valueOf("005eab"));
        else if (current == TechEnum.THE_WHEEL) theWheel.setFill(Paint.valueOf("005eab"));
        else if (current == TechEnum.MASONRY) masonry.setFill(Paint.valueOf("005eab"));
        else if (current == TechEnum.BRONZE_WORKING) bronzeWorking.setFill(Paint.valueOf("005eab"));
        else if (current == TechEnum.PHILOSOPHY) philosophy.setFill(Paint.valueOf("005eab"));
        else if (current == TechEnum.HORSEBACK_RIDING) horsebackRiding.setFill(Paint.valueOf("005eab"));
        else if (current == TechEnum.MATHEMATICS) mathematics.setFill(Paint.valueOf("005eab"));
        else if (current == TechEnum.CONSTRUCTION) construction.setFill(Paint.valueOf("005eab"));
        else if (current == TechEnum.IRON_WORKING) ironWorking.setFill(Paint.valueOf("005eab"));
        else if (current == TechEnum.THEOLOGY) theology.setFill(Paint.valueOf("005eab"));
        else if (current == TechEnum.CIVIL_SERVICE) civilService.setFill(Paint.valueOf("005eab"));
        else if (current == TechEnum.CURRENCY) currency.setFill(Paint.valueOf("005eab"));
        else if (current == TechEnum.ENGINEERING) engineering.setFill(Paint.valueOf("005eab"));
        else if (current == TechEnum.METAL_CASTING) metalCasting.setFill(Paint.valueOf("005eab"));
        else if (current == TechEnum.EDUCATION) education.setFill(Paint.valueOf("005eab"));
        else if (current == TechEnum.CHIVALRY) chivalry.setFill(Paint.valueOf("005eab"));
        else if (current == TechEnum.MACHINERY) machinery.setFill(Paint.valueOf("005eab"));
        else if (current == TechEnum.PHYSICS) physics.setFill(Paint.valueOf("005eab"));
        else if (current == TechEnum.STEEL) steel.setFill(Paint.valueOf("005eab"));
        else if (current == TechEnum.ACOUSTICS) acoustics.setFill(Paint.valueOf("005eab"));
        else if (current == TechEnum.BANKING) banking.setFill(Paint.valueOf("005eab"));
        else if (current == TechEnum.PRINTING_PRESS) printingPress.setFill(Paint.valueOf("005eab"));
        else if (current == TechEnum.GUN_POWDER) gunpowder.setFill(Paint.valueOf("005eab"));
        else if (current == TechEnum.ECONOMICS) economics.setFill(Paint.valueOf("005eab"));
        else if (current == TechEnum.CHEMISTRY) chemistry.setFill(Paint.valueOf("005eab"));
        else if (current == TechEnum.METALLURGY) metallurgy.setFill(Paint.valueOf("005eab"));
        else if (current == TechEnum.SCIENTIFIC_THEORY) scientificTheory.setFill(Paint.valueOf("005eab"));
        else if (current == TechEnum.MILITARY_SCIENCE) militaryScience.setFill(Paint.valueOf("005eab"));
        else if (current == TechEnum.FERTILIZER) fertilizer.setFill(Paint.valueOf("005eab"));
        else if (current == TechEnum.RIFLING) rifling.setFill(Paint.valueOf("005eab"));
        else if (current == TechEnum.BIOLOGY) biology.setFill(Paint.valueOf("005eab"));
        else if (current == TechEnum.STEAM_POWER) steamPower.setFill(Paint.valueOf("005eab"));
        else if (current == TechEnum.ELECTRICITY) electricity.setFill(Paint.valueOf("005eab"));
        else if (current == TechEnum.REPLACEABLE_PARTS) replaceableParts.setFill(Paint.valueOf("005eab"));
        else if (current == TechEnum.RAILROAD) railroad.setFill(Paint.valueOf("005eab"));
        else if (current == TechEnum.DYNAMITE) dynamite.setFill(Paint.valueOf("005eab"));
        else if (current == TechEnum.TELEGRAPH) telegraph.setFill(Paint.valueOf("005eab"));
        else if (current == TechEnum.RADIO) radio.setFill(Paint.valueOf("005eab"));
        else if (current == TechEnum.COMBUSTION) combustion.setFill(Paint.valueOf("005eab"));
    }

    private void paintResearched() {
        Player player = Game.getInstance().getThisTurnPlayer();
        ArrayList<Tech> researchedTech = player.getFullyResearchedTechs();
        ArrayList<TechEnum> researchedTechEnum = new ArrayList<>();
        for (Tech tech : researchedTech) {
            researchedTechEnum.add(tech.getTechName());
        }
        if (researchedTechEnum.contains(TechEnum.AGRICULTURE))
            agriculture.setFill(Paint.valueOf("a3bf00"));
        if (researchedTechEnum.contains(TechEnum.POTTERY))
            pottery.setFill(Paint.valueOf("a3bf00"));
        if (researchedTechEnum.contains(TechEnum.ANIMAL_HUSBANDRY))
            animalHusbandry.setFill(Paint.valueOf("a3bf00"));
        if (researchedTechEnum.contains(TechEnum.ARCHERY))
            archery.setFill(Paint.valueOf("a3bf00"));
        if (researchedTechEnum.contains(TechEnum.MINING))
            mining.setFill(Paint.valueOf("a3bf00"));
        if (researchedTechEnum.contains(TechEnum.CALENDAR))
            calendar.setFill(Paint.valueOf("a3bf00"));
        if (researchedTechEnum.contains(TechEnum.WRITING))
            writing.setFill(Paint.valueOf("a3bf00"));
        if (researchedTechEnum.contains(TechEnum.TRAPPING))
            trapping.setFill(Paint.valueOf("a3bf00"));
        if (researchedTechEnum.contains(TechEnum.THE_WHEEL))
            theWheel.setFill(Paint.valueOf("a3bf00"));
        if (researchedTechEnum.contains(TechEnum.MASONRY))
            masonry.setFill(Paint.valueOf("a3bf00"));
        if (researchedTechEnum.contains(TechEnum.BRONZE_WORKING))
            bronzeWorking.setFill(Paint.valueOf("a3bf00"));
        if (researchedTechEnum.contains(TechEnum.PHILOSOPHY))
            philosophy.setFill(Paint.valueOf("a3bf00"));
        if (researchedTechEnum.contains(TechEnum.HORSEBACK_RIDING))
            horsebackRiding.setFill(Paint.valueOf("a3bf00"));
        if (researchedTechEnum.contains(TechEnum.MATHEMATICS))
            mathematics.setFill(Paint.valueOf("a3bf00"));
        if (researchedTechEnum.contains(TechEnum.CONSTRUCTION))
            construction.setFill(Paint.valueOf("a3bf00"));
        if (researchedTechEnum.contains(TechEnum.IRON_WORKING))
            ironWorking.setFill(Paint.valueOf("a3bf00"));
        if (researchedTechEnum.contains(TechEnum.THEOLOGY))
            theology.setFill(Paint.valueOf("a3bf00"));
        if (researchedTechEnum.contains(TechEnum.CIVIL_SERVICE))
            civilService.setFill(Paint.valueOf("a3bf00"));
        if (researchedTechEnum.contains(TechEnum.CURRENCY))
            currency.setFill(Paint.valueOf("a3bf00"));
        if (researchedTechEnum.contains(TechEnum.ENGINEERING))
            engineering.setFill(Paint.valueOf("a3bf00"));
        if (researchedTechEnum.contains(TechEnum.METAL_CASTING))
            metalCasting.setFill(Paint.valueOf("a3bf00"));
        if (researchedTechEnum.contains(TechEnum.EDUCATION))
            education.setFill(Paint.valueOf("a3bf00"));
        if (researchedTechEnum.contains(TechEnum.CHIVALRY))
            chivalry.setFill(Paint.valueOf("a3bf00"));
        if (researchedTechEnum.contains(TechEnum.MACHINERY))
            machinery.setFill(Paint.valueOf("a3bf00"));
        if (researchedTechEnum.contains(TechEnum.PHYSICS))
            physics.setFill(Paint.valueOf("a3bf00"));
        if (researchedTechEnum.contains(TechEnum.STEEL))
            steel.setFill(Paint.valueOf("a3bf00"));
        if (researchedTechEnum.contains(TechEnum.ACOUSTICS))
            acoustics.setFill(Paint.valueOf("a3bf00"));
        if (researchedTechEnum.contains(TechEnum.BANKING))
            banking.setFill(Paint.valueOf("a3bf00"));
        if (researchedTechEnum.contains(TechEnum.PRINTING_PRESS))
            printingPress.setFill(Paint.valueOf("a3bf00"));
        if (researchedTechEnum.contains(TechEnum.GUN_POWDER))
            gunpowder.setFill(Paint.valueOf("a3bf00"));
        if (researchedTechEnum.contains(TechEnum.ECONOMICS))
            economics.setFill(Paint.valueOf("a3bf00"));
        if (researchedTechEnum.contains(TechEnum.CHEMISTRY))
            chemistry.setFill(Paint.valueOf("a3bf00"));
        if (researchedTechEnum.contains(TechEnum.METALLURGY))
            metallurgy.setFill(Paint.valueOf("a3bf00"));
        if (researchedTechEnum.contains(TechEnum.SCIENTIFIC_THEORY))
            scientificTheory.setFill(Paint.valueOf("a3bf00"));
        if (researchedTechEnum.contains(TechEnum.MILITARY_SCIENCE))
            militaryScience.setFill(Paint.valueOf("a3bf00"));
        if (researchedTechEnum.contains(TechEnum.FERTILIZER))
            fertilizer.setFill(Paint.valueOf("a3bf00"));
        if (researchedTechEnum.contains(TechEnum.RIFLING))
            rifling.setFill(Paint.valueOf("a3bf00"));
        if (researchedTechEnum.contains(TechEnum.BIOLOGY))
            biology.setFill(Paint.valueOf("a3bf00"));
        if (researchedTechEnum.contains(TechEnum.STEAM_POWER))
            steamPower.setFill(Paint.valueOf("a3bf00"));
        if (researchedTechEnum.contains(TechEnum.ELECTRICITY))
            electricity.setFill(Paint.valueOf("a3bf00"));
        if (researchedTechEnum.contains(TechEnum.REPLACEABLE_PARTS))
            replaceableParts.setFill(Paint.valueOf("a3bf00"));
        if (researchedTechEnum.contains(TechEnum.RAILROAD))
            railroad.setFill(Paint.valueOf("a3bf00"));
        if (researchedTechEnum.contains(TechEnum.DYNAMITE))
            dynamite.setFill(Paint.valueOf("a3bf00"));
        if (researchedTechEnum.contains(TechEnum.TELEGRAPH))
            telegraph.setFill(Paint.valueOf("a3bf00"));
        if (researchedTechEnum.contains(TechEnum.RADIO))
            radio.setFill(Paint.valueOf("a3bf00"));
        if (researchedTechEnum.contains(TechEnum.COMBUSTION))
            combustion.setFill(Paint.valueOf("a3bf00"));
    }

    public void back(MouseEvent mouseEvent) {
        PlayGamePage.getInstance().setOnTechTree(false);
        MenuChanger.resetGameRequestFocus();
    }
}