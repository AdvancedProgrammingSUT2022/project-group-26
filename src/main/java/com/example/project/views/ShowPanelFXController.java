package com.example.project.views;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
        setupDataBar();
        setupSelectedObjectData();
    }

    private void setupSelectedObjectData() {
        // hbox
        // in it circle and hbox
        HBox objectData = new HBox();

        objectData.setPrefWidth(700);
        objectData.setPrefHeight(100);
        objectData.setLayoutY(650);
        objectData.setPadding(new Insets(15, 12, 15, 12));
        objectData.setSpacing(10);
        objectData.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, null, null)));

        Circle researchImage = new Circle();
        researchImage.setRadius(90);
        researchImage.setFill(Color.BLUEVIOLET);

        VBox data = new VBox();

        Label name = new Label();
//        Label health = new Label();
//        Label attack = new Label();
//        Label name = new Label();
        data.getChildren().addAll();
        objectData.getChildren().addAll(researchImage, data);

        pane.getChildren().add(objectData);

    }

    private void setupDataBar() {
        VBox dataBar = new VBox();

        dataBar.setPrefWidth(90);
        dataBar.setPrefHeight(820);
        dataBar.setPadding(new Insets(15, 12, 15, 12));
        dataBar.setSpacing(10);
        dataBar.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, null, null)));


        // todo : add a hbox -> in it : circle and shit
        Circle researchImage = new Circle();
        researchImage.setRadius(30); // ??!
        researchImage.setFill(Color.BLUEVIOLET);
//        researchImage.setFill(new ImagePattern(new Image(new FileInputStream("src of the research"))));

        // add a custom vbox that fills and erases ?!

    }

    private void setupStatusBar() {
        HBox statusBar = new HBox();

        statusBar.setPrefWidth(1600);
        statusBar.setPrefHeight(30);
        statusBar.setPadding(new Insets(15, 12, 15, 12));
        statusBar.setSpacing(10);
        statusBar.setBackground(new Background(new BackgroundFill(Color.TRANSPARENT, null, null)));


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

        goldImage.setFitHeight(30);
        goldImage.setFitWidth(30);
        happinessImage.setFitHeight(30);
        happinessImage.setFitWidth(30);
        scienceImage.setFitHeight(30);
        scienceImage.setFitWidth(30);

        goldAmount.setTextFill(Color.GOLD);
        happinessAmount.setTextFill(Color.DARKGRAY);
        scienceAmount.setTextFill(Color.DARKVIOLET);

        goldAmount.setPrefHeight(25);
        goldAmount.setPrefWidth(75);
        happinessAmount.setPrefHeight(25);
        happinessAmount.setPrefWidth(75);
        scienceAmount.setPrefHeight(25);
        scienceAmount.setPrefWidth(75);

        goldAmount.setText("random");
        happinessAmount.setText("random");
        scienceAmount.setText("random");

        goldAmount.setFont(new Font(15));
        happinessAmount.setFont(new Font(15));
        scienceAmount.setFont(new Font(15));

        statusBar.getChildren().addAll(goldImage, goldAmount, happinessImage, happinessAmount, scienceImage, scienceAmount);


        pane.getChildren().add(statusBar);
    }
}
