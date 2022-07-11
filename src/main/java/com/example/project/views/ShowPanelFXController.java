package com.example.project.views;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class ShowPanelFXController {
    private static ShowPanelFXController instance = null;

    @FXML
    private Pane pane;

    private ShowPanelFXController() {
    }

    public static ShowPanelFXController getInstance() {
        if (instance == null) instance = new ShowPanelFXController();
        return instance;
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

        ImageView goldImage = new ImageView();
        ImageView happinessImage = new ImageView();
        ImageView scienceImage = new ImageView();
        Label goldAmount = new Label();
        Label happinessAmount = new Label();
        Label scienceAmount = new Label();

        // todo : find images
        // todo : fill images
        // need a updater


        statusBar.getChildren().addAll(goldImage, goldAmount, happinessImage, happinessAmount, scienceImage, scienceAmount);


        pane.getChildren().add(statusBar);
    }
}
