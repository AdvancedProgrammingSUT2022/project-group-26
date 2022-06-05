package com.example.project.views;

import com.example.project.controllers.GameControllers.EditGameMapController;
import com.example.project.controllers.Output;
import com.example.project.models.GameMap;
import com.example.project.models.Player;
import com.example.project.models.Tile.Tile;
import com.example.project.models.User;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;

import java.util.ArrayList;
import java.util.Arrays;

public class EditGameMap {

    private static GameMap gameMap = new GameMap(new ArrayList<>(Arrays.asList(new Player(new User("", "", ""))
            , new Player(new User("", "", "")), new Player(new User("", "", "")))));

    private static EditGameMap instance;

    public static EditGameMap getInstance() {
        if (instance != null) instance = new EditGameMap();
        EditGameMapController.getInstance().setGameMap(gameMap);
        return instance;
    }


    @FXML
    private MenuButton tileFeatureBar;
    @FXML
    private MenuButton tileResourceBar;
    @FXML
    private MenuButton tileModeBar;

    @FXML
    private MenuItem desert;
    @FXML
    private MenuItem plain;
    @FXML
    private MenuItem hill;
    @FXML
    private MenuItem mountain;
    @FXML
    private MenuItem grassland;
    @FXML
    private MenuItem ocean;
    @FXML
    private MenuItem snow;
    @FXML
    private MenuItem tundra;

    private MenuItem[] tileModes;

    @FXML
    private MenuItem swamp;
    @FXML
    private MenuItem oasis;
    @FXML
    private MenuItem plainFeature;
    @FXML
    private MenuItem ice;
    @FXML
    private MenuItem forest;
    @FXML
    private MenuItem denseForrest;
    @FXML
    private MenuItem noFeature;

    @FXML
    private MenuItem banana;
    @FXML
    private MenuItem cow;
    @FXML
    private MenuItem gazelle;
    @FXML
    private MenuItem sheep;
    @FXML
    private MenuItem wheat;
    @FXML
    private MenuItem coal;
    @FXML
    private MenuItem horse;
    @FXML
    private MenuItem iron;
    @FXML
    private MenuItem cotton;
    @FXML
    private MenuItem fur;
    @FXML
    private MenuItem gemStones;
    @FXML
    private MenuItem paint;
    @FXML
    private MenuItem gold;
    @FXML
    private MenuItem incense;
    @FXML
    private MenuItem tusk;
    @FXML
    private MenuItem marble;
    @FXML
    private MenuItem silk;
    @FXML
    private MenuItem silver;
    @FXML
    private MenuItem sugar;
    @FXML
    private MenuItem noResource;

    @FXML
    private MenuButton iCoordinateBar;
    @FXML
    private MenuButton jCoordinateBar;


    public void initialize() {
        MenuItem[] tileModes = {desert, plain, hill, mountain, grassland, ocean, snow, tundra};
        this.tileModes = tileModes;
        for (MenuItem menuItem : tileModes)
            menuItem.setOnAction(actionEvent -> tileModeBar.setText(menuItem.getText()));
        MenuItem[] tileFeatures = {swamp, oasis, plainFeature, ice, forest, denseForrest, noFeature};
        for (MenuItem menuItem : tileFeatures)
            menuItem.setOnAction(actionEvent -> tileFeatureBar.setText(menuItem.getText()));
        MenuItem[] tileResources = {banana, cow, gazelle, sheep, wheat, coal, horse, iron, cotton, fur, gemStones, paint, gold,
                incense, tusk, marble, silk, silver, sugar, noResource};
        for (MenuItem menuItem : tileResources)
            menuItem.setOnAction(actionEvent -> tileResourceBar.setText(menuItem.getText()));
        setCoordinateBars();
        EditGameMapController.getInstance().setGameMap(gameMap);
    }

    private void setCoordinateBars() {
        for (int i = 0; i < gameMap.getMap().length; i++) {
            MenuItem menuItem = new MenuItem(Integer.toString(i));
            iCoordinateBar.getItems().add(menuItem);
            menuItem.setOnAction(actionEvent -> {
                EditGameMapController.getInstance().chooseICoordinate();
                iCoordinateBar.setText(menuItem.getText());
                updateAfterChooseCoordinate();
            });
        }
        for (int i = 0; i < gameMap.getMap()[0].length; i++) {
            MenuItem menuItem = new MenuItem(Integer.toString(i));
            jCoordinateBar.getItems().add(menuItem);
            menuItem.setOnAction(actionEvent -> {
                EditGameMapController.getInstance().chooseJCoordinate();
                jCoordinateBar.setText(menuItem.getText());
                updateAfterChooseCoordinate();
            });
        }
    }

    public void submit(MouseEvent mouseEvent) {
        Output output = EditGameMapController.getInstance().changeTile(Integer.parseInt(iCoordinateBar.getText()),
                Integer.parseInt(jCoordinateBar.getText()), tileModeBar.getText(), tileResourceBar.getText(), tileFeatureBar.getText());
        if (output != Output.CHANGE_TILE_SUCCESSFULLY)
            new PopupMessage(Alert.AlertType.ERROR, output.toString());
        else new PopupMessage(Alert.AlertType.INFORMATION, output.toString());
    }

    public void updateAfterChooseCoordinate() {
        if (EditGameMapController.getInstance().getShouldUpdate()) {
            int iCoordinate = Integer.parseInt(iCoordinateBar.getText());
            int jCoordinate = Integer.parseInt(jCoordinateBar.getText());
            Tile chosenTile = gameMap.getTile(iCoordinate, jCoordinate);
            tileModeBar.setText(chosenTile.getMode().getTileName().getName());
            if (chosenTile.getResource() != null)
                tileResourceBar.setText(chosenTile.getResource().getResourceName().getName());
            else tileResourceBar.setText("");
            if (chosenTile.getFeature() != null)
                tileFeatureBar.setText(chosenTile.getFeature().getFeatureName().getName());
            else tileFeatureBar.setText("");
        }
    }
}