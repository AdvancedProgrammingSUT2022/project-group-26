package com.example.project.views;

import com.example.project.App;
import com.example.project.models.GameMap;
import com.example.project.models.Player;
import com.example.project.models.Tile.Tile;
import com.example.project.models.Tile.TileModeEnum;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class ShowMapFXController {

    private static ShowMapFXController instance;
    private final Image fogOfWar =
            new Image(String.valueOf(new URL(App.class.getResource("/Image/Game/Tile/mode/fogOfWar.png").toString())));

    public ShowMapFXController() throws MalformedURLException {
    }

    public static ShowMapFXController getInstance() {
        if (instance == null) {
            try {
                instance = new ShowMapFXController();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }

    private GameMap gameMap;
    private ArrayList<Player> players;
    private Pane pane;
    private GameMap playerGameMap;

    public void setUp(GameMap gameMap, ArrayList<Player> players) {
        this.gameMap = gameMap;
        this.players = players;
    }

    private final double tileSideLength = 100;
    private final double tilePaneLength = 200;


    public void showMap() throws MalformedURLException {
        playerGameMap = PlayGamePage.getInstance().getThisTurnPlayer().getGameMap();
        PlayGamePage.getInstance().getThisTurnPlayer().updateMap(this.gameMap);
        pane.getChildren().clear();
        showTiles();
    }

    private void showTiles() {
        gameMap = players.get(0).getGameMap();
        for (int i = 0; i < 20; i++)
            for (int j = 0; j < 20; j++) {
                ImageView imageView;
                if (gameMap.getTile(i, j) != null) {
                    imageView =
                            new ImageView(TileModeEnum.getTileModeImages().get(gameMap.getTile(i, j).getMode().getTileName()));
                } else imageView = new ImageView(fogOfWar);
                imageView.setFitWidth(tilePaneLength);
                imageView.setFitHeight(tilePaneLength);
                if (j % 2 == 1)
                    imageView.setY(tilePaneLength * i);
                else
                    imageView.setY(tilePaneLength * i + tilePaneLength / 2);
                imageView.setX((tileSideLength * 3 / 2) * j);


                Label label = new Label(i + "," + j);
                label.setFont(Font.font(40));
                if (j % 2 == 1)
                    label.setLayoutY(tilePaneLength * i + 80);
                else
                    label.setLayoutY(tilePaneLength * i + tilePaneLength / 2 + 80);
                label.setLayoutX((tileSideLength * 3 / 2) * j + 80);

                this.pane.getChildren().addAll(imageView, label);

            }
    }

    private void setRivers(String[][] toPrint, int[][][] centerPoints, Tile[][] tilesToShow, Player player) {
    }

    private void setRiverOfTiles(String[][] toPrint, int[] firstCenterCoordinate, int[] secondCenterCoordinates) {
    }

    private void setRiverType1(String[][] toPrint, int[] firstCenterCoordinate, int[] secondCenterCoordinate) {
    }

    private void setRiverType2(String[][] toPrint, int[] firstCenterCoordinate, int[] secondCenterCoordinate) {

    }

    private void setFeatures(String[][] toPrint, int[][][] centerPoints, Tile[][] tilesToShow) {

    }

    private void addPlainFeature(String[][] toPrint, int centerICoordinate, int centerJCoordinate) {
    }

    private void addForestFeature(String[][] toPrint, int centerICoordinate, int centerJCoordinate) {
    }

    private void addDenseForestFeature(String[][] toPrint, int centerICoordinate, int centerJCoordinate) {
    }

    private void addIceFeature(String[][] toPrint, int centerICoordinate, int centerJCoordinate) {
    }

    private void addOasisFeature(String[][] toPrint, int centerICoordinate, int centerJCoordinate) {
    }

    private void addSwampFeature(String[][] toPrint, int centerICoordinate, int centerJCoordinate) {
    }

    private void setUnits(String[][] toPrint, int[][][] centerPoints, Tile[][] tilesToShow) {
    }

    private void setCombatUnits(String[][] toPrint, int[][][] centerPoints, Tile[][] tilesToShow) {
    }

    private void addCombatUnits(String[][] toPrint, int centerICoordinate, int centerJCoordinates, Tile tile) {
    }

    private void setNoncombatUnits(String[][] toPrint, int[][][] centerPoints, Tile[][] tilesToShow) {
    }

    private void addNoncombatUnits(String[][] toPrint, int centerICoordinate, int centerJCoordinates, Tile tile) {
    }

    private void setResources(String[][] toPrint, int[][][] centerPoints, Tile[][] tilesToShow, Player player) {
    }

    private void addResource(String[][] toPrint, int centerICoordinate, int centerJCoordinate, Tile tile, Player player) {
    }

    private void setImprovements(String[][] toPrint, int[][][] centerPoints, Tile[][] tilesToShow, Player player) {
    }

    private void addImprovement(String[][] toPrint, int centerICoordinate, int centerJCoordinate, Tile tile, Player player) {
    }

    private void setCityName(String[][] toPrint, Tile[][] tilesToShow, int[][][] centerPoints, Player player) {
    }

    public void setPane(Pane pane) {
        this.pane = pane;
    }

}