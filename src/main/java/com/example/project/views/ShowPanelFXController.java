package com.example.project.views;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class ShowPanelFXController {
    private static ShowPanelFXController instance = null;

    @FXML
    private Pane pane;

    private Label goldAmount = new Label();
    private Label happinessAmount = new Label();
    private Label scienceAmount = new Label();

    private ShowPanelFXController() {
    }

    public static ShowPanelFXController getInstance() {
        if (instance == null) instance = new ShowPanelFXController();
        return instance;
    }

    public Label getGoldAmount() {
        return goldAmount;
    }

    public Label getHappinessAmount() {
        return happinessAmount;
    }

    public Label getScienceAmount() {
        return scienceAmount;
    }

    public void setPane(Pane pane) {
        this.pane = pane;
    }

    public void setupPanels() {
        setupStatusBar();
        // todo : setups
    }

    private void setupStatusBar() {
        HBox statusBar = new HBox();

        statusBar.setPrefWidth(1530);
        statusBar.setPrefHeight(40);
        statusBar.setSpacing(5);
        statusBar.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));

        ImageView goldImage = null;
        ImageView happinessImage = null;
        ImageView scienceImage = null;
        try {
            goldImage = new ImageView(new Image(new FileInputStream("src/main/resources/Image/Game/Panel/StatusBar/Gold.png")));
            happinessImage = new ImageView(new Image(new FileInputStream("src/main/resources/Image/Game/Panel/StatusBar/Happiness.png")));
            scienceImage = new ImageView(new Image(new FileInputStream("src/main/resources/Image/Game/Panel/StatusBar/Science.png")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        goldImage.setFitHeight(25);
        goldImage.setFitHeight(25);
        happinessImage.setFitHeight(25);
        happinessImage.setFitHeight(25);
        scienceImage.setFitHeight(25);
        scienceImage.setFitHeight(25);

        statusBar.getChildren().addAll(goldImage, goldAmount, happinessImage, happinessAmount, scienceImage, scienceAmount);


        pane.getChildren().add(statusBar);
    }
}
