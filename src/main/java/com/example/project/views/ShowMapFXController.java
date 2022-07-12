package com.example.project.views;

import com.example.project.App;
import com.example.project.models.Feature.TileFeatureEnum;
import com.example.project.models.GameMap;
import com.example.project.models.Player;
import com.example.project.models.Resource.TileResourceEnum;
import com.example.project.models.Tile.Tile;
import com.example.project.models.Tile.TileModeEnum;
import com.example.project.models.Units.UnitNameEnum;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class ShowMapFXController {

    private static ShowMapFXController instance;
    private final Image fogOfWar =
            new Image(String.valueOf(new URL(App.class.getResource("/Image/Game/Tile/mode/fogOfWar.png").toString())));
    private final Image revealedImage =
            new Image(String.valueOf(new URL(App.class.getResource("/Image/Game/Tile/revealed.png").toString())));

    private GameMap gameMap;
    private ArrayList<Player> players;
    private GameMap playerGameMap;

    private Pane pane;
    private VBox vBox;
    //VBox
    private Label mode;
    private Label feature;
    private Label resource;
    private Label gold;
    private Label food;


    public void setData(Pane pane, VBox vBox) {
        this.pane = pane;
        this.vBox = vBox;
        mode = (Label) ((Pane) vBox.getChildren().get(0)).getChildren().get(0);
        feature = (Label) ((Pane) vBox.getChildren().get(0)).getChildren().get(1);
        resource = (Label) ((Pane) vBox.getChildren().get(0)).getChildren().get(2);
        gold = (Label) ((Pane) vBox.getChildren().get(0)).getChildren().get(3);
        food = (Label) ((Pane) vBox.getChildren().get(0)).getChildren().get(4);
    }


    public ShowMapFXController() throws MalformedURLException {
    }


    private int iCoordinateToShow = 7;
    private int jCoordinateToShow = 3;

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

    public void setUp(GameMap gameMap, ArrayList<Player> players) {
        this.gameMap = gameMap;
        this.players = players;
    }

    private final double tileSideLength = 90;
    private final double tilePaneLength = 2 * tileSideLength;


    public void showMap() throws MalformedURLException {
        playerGameMap = PlayGamePage.getInstance().getThisTurnPlayer().getGameMap();
        PlayGamePage.getInstance().getThisTurnPlayer().updateMap(this.gameMap);
        pane.getChildren().clear();
        showTiles();
        showFeatures();
        showResources();
        showCombatUnits();
        showNoneCombatUnits();
        showInSightTiles();
        showVBox();
    }

    private void showTiles() {
        for (int i = iCoordinateToShow; i < iCoordinateToShow + 6; i++)
            for (int j = jCoordinateToShow; j < jCoordinateToShow + 12; j++) {
                int toShowI = i - iCoordinateToShow;
                int toShowJ = j - jCoordinateToShow;
                ImageView imageView;
                if (playerGameMap.getTile(i, j) != null) {
                    imageView =
                            new ImageView(TileModeEnum.getImages().get(playerGameMap.getTile(i, j).getMode().getTileName()));
                } else imageView = new ImageView(fogOfWar);
                imageView.setFitWidth(tilePaneLength);
                imageView.setFitHeight(tilePaneLength);
                double xCoordinate;
                double yCoordinate;
                if (j % 2 == 1)
                    yCoordinate = tilePaneLength * toShowI - tilePaneLength / 2;
                else
                    yCoordinate = tilePaneLength * toShowI + tilePaneLength / 2 - tilePaneLength / 2;
                xCoordinate = (tileSideLength * 3 / 2) * toShowJ - tilePaneLength / 2;
                imageView.setX(xCoordinate);
                imageView.setY(yCoordinate);
                imageView.setCursor(Cursor.HAND);
                if (playerGameMap.getTile(i, j) != null) {
                    int finalI = i;
                    int finalJ = j;
                    imageView.setOnMouseClicked(mouseEvent -> {
                        showTileData(playerGameMap.getTile(finalI, finalJ), xCoordinate, yCoordinate);
                    });
                }
                this.pane.getChildren().add(imageView);
            }
    }

    private void showFeatures() {
        for (int i = iCoordinateToShow; i < iCoordinateToShow + 6; i++)
            for (int j = jCoordinateToShow; j < jCoordinateToShow + 12; j++) {
                int toShowI = i - iCoordinateToShow;
                int toShowJ = j - jCoordinateToShow;
                if (playerGameMap.getTile(i, j) != null && playerGameMap.getTile(i, j).getFeature() != null) {
                    ImageView imageView =
                            new ImageView(TileFeatureEnum.getImages().get(playerGameMap.getTile(i, j).getFeature().getFeatureName()));
                    imageView.setFitWidth(tilePaneLength);
                    imageView.setFitHeight(tilePaneLength);
                    double xCoordinate;
                    double yCoordinate;
                    if (j % 2 == 1)
                        yCoordinate = tilePaneLength * toShowI - tilePaneLength / 2;
                    else
                        yCoordinate = tilePaneLength * toShowI + tilePaneLength / 2 - tilePaneLength / 2;
                    xCoordinate = (tileSideLength * 3 / 2) * toShowJ - tilePaneLength / 2;
                    imageView.setX(xCoordinate);
                    imageView.setY(yCoordinate);
                    imageView.setCursor(Cursor.HAND);
                    int finalI = i;
                    int finalJ = j;
                    imageView.setOnMouseClicked(mouseEvent ->
                            showTileData(playerGameMap.getTile(finalI, finalJ), xCoordinate, yCoordinate));

                    this.pane.getChildren().add(imageView);
                }
            }
    }

    private void showResources() {
        for (int i = iCoordinateToShow; i < iCoordinateToShow + 6; i++)
            for (int j = jCoordinateToShow; j < jCoordinateToShow + 12; j++) {
                int toShowI = i - iCoordinateToShow;
                int toShowJ = j - jCoordinateToShow;
                if (playerGameMap.getTile(i, j) != null && playerGameMap.getTile(i, j).getResource() != null) {
                    ImageView imageView =
                            new ImageView(TileResourceEnum.getImages().get(playerGameMap.getTile(i, j).getResource().getResourceName()));
                    imageView.setFitWidth(tilePaneLength);
                    imageView.setFitHeight(tilePaneLength);
                    double xCoordinate;
                    double yCoordinate;
                    if (j % 2 == 1)
                        yCoordinate = tilePaneLength * toShowI - tilePaneLength / 2;
                    else
                        yCoordinate = tilePaneLength * toShowI + tilePaneLength / 2 - tilePaneLength / 2;
                    xCoordinate = (tileSideLength * 3 / 2) * toShowJ - tilePaneLength / 2;

                    imageView.setY(yCoordinate + 120);
                    imageView.setX(xCoordinate + 65);
                    imageView.setFitWidth(50);
                    imageView.setFitHeight(50);
                    imageView.setCursor(Cursor.HAND);

                    int finalI = i;
                    int finalJ = j;
                    imageView.setOnMouseClicked(mouseEvent ->
                            showTileData(playerGameMap.getTile(finalI, finalJ), xCoordinate, yCoordinate));
                    this.pane.getChildren().add(imageView);
                }
            }
    }

    private void showCombatUnits() {
        for (int i = iCoordinateToShow; i < iCoordinateToShow + 6; i++)
            for (int j = jCoordinateToShow; j < jCoordinateToShow + 12; j++) {
                int toShowI = i - iCoordinateToShow;
                int toShowJ = j - jCoordinateToShow;
                if (playerGameMap.getTile(i, j) != null && playerGameMap.getTile(i, j).getCombatUnits() != null) {
                    ImageView imageView =
                            new ImageView(UnitNameEnum.getImages().get(playerGameMap.getTile(i, j).getCombatUnits().getUnitNameEnum()));
                    imageView.setFitWidth(tilePaneLength);
                    imageView.setFitHeight(tilePaneLength);
                    if (j % 2 == 1)
                        imageView.setY(tilePaneLength * toShowI - tilePaneLength / 2 + 30);
                    else
                        imageView.setY(tilePaneLength * toShowI + tilePaneLength / 2 - tilePaneLength / 2 + 30);
                    imageView.setX((tileSideLength * 3 / 2) * toShowJ - tilePaneLength / 2 + 20);
                    imageView.setFitWidth(70);
                    imageView.setFitHeight(70);
                    this.pane.getChildren().add(imageView);
                }
            }
    }

    private void showNoneCombatUnits() {
        for (int i = iCoordinateToShow; i < iCoordinateToShow + 6; i++)
            for (int j = jCoordinateToShow; j < jCoordinateToShow + 12; j++) {
                int toShowI = i - iCoordinateToShow;
                int toShowJ = j - jCoordinateToShow;
                if (playerGameMap.getTile(i, j) != null && playerGameMap.getTile(i, j).getNoneCombatUnits() != null) {
                    ImageView imageView =
                            new ImageView(UnitNameEnum.getImages().get(playerGameMap.getTile(i, j).getNoneCombatUnits().getUnitNameEnum()));
                    imageView.setFitWidth(tilePaneLength);
                    imageView.setFitHeight(tilePaneLength);
                    if (j % 2 == 1)
                        imageView.setY(tilePaneLength * toShowI - tilePaneLength / 2 + 30);
                    else
                        imageView.setY(tilePaneLength * toShowI + tilePaneLength / 2 - tilePaneLength / 2 + 30);
                    imageView.setX((tileSideLength * 3 / 2) * toShowJ - tilePaneLength / 2 + 85);
                    imageView.setFitWidth(70);
                    imageView.setFitHeight(70);
                    this.pane.getChildren().add(imageView);
                }
            }
    }

    public void showInSightTiles() {
        for (int i = iCoordinateToShow; i < iCoordinateToShow + 6; i++)
            for (int j = jCoordinateToShow; j < jCoordinateToShow + 12; j++) {
                int toShowI = i - iCoordinateToShow;
                int toShowJ = j - jCoordinateToShow;
                if (playerGameMap.getTile(i, j) != null
                        && !PlayGamePage.getInstance().getThisTurnPlayer().isVisible(playerGameMap.getTile(i, j), this.gameMap)) {
                    ImageView imageView =
                            new ImageView(revealedImage);
                    imageView.setFitWidth(tilePaneLength);
                    imageView.setFitHeight(tilePaneLength);
                    double xCoordinate;
                    double yCoordinate;
                    if (j % 2 == 1)
                        yCoordinate = tilePaneLength * toShowI - tilePaneLength / 2;
                    else
                        yCoordinate = tilePaneLength * toShowI + tilePaneLength / 2 - tilePaneLength / 2;
                    xCoordinate = (tileSideLength * 3 / 2) * toShowJ - tilePaneLength / 2;
                    imageView.setX(xCoordinate);
                    imageView.setY(yCoordinate);
                    imageView.setCursor(Cursor.HAND);
                    int finalI = i;
                    int finalJ = j;
                    imageView.setOnMouseClicked(mouseEvent -> {
                        showTileData(playerGameMap.getTile(finalI, finalJ), xCoordinate, yCoordinate);
                    });
                    this.pane.getChildren().add(imageView);
                }
            }
    }

    public void showTileData(Tile tile, double xCoordinate, double yCoordinate) {
        mode.setText(tile.getMode().getTileName().getName());
        if (tile.getFeature() != null)
            feature.setText(tile.getFeature().getFeatureName().getName());
        else feature.setText("no feature!");
        if (tile.getResource() != null)
            resource.setText(tile.getResource().getResourceName().getName());
        else resource.setText("no resource!");
        gold.setText(String.valueOf(tile.getGold()));
        food.setText(String.valueOf(tile.getFood()));
        vBox.setLayoutX(xCoordinate - 7);
        vBox.setLayoutY(yCoordinate - 10);
        vBox.setVisible(true);
    }

    private void showVBox() {
        if (vBox.isVisible())
            this.pane.getChildren().add(vBox);
    }

    public void moveLeft() {
        jCoordinateToShow--;
        vBox.setVisible(false);
        if (jCoordinateToShow < 0) {
            vBox.setVisible(true);
            jCoordinateToShow = 0;
        }
    }

    public void moveRight() {
        if (jCoordinateToShow != gameMap.getMap()[0].length - 1) {
            jCoordinateToShow++;
            vBox.setVisible(false);
        }
    }

    public void moveUp() {
        iCoordinateToShow--;
        vBox.setVisible(false);
        if (iCoordinateToShow < 0) {
            iCoordinateToShow = 0;
            vBox.setVisible(true);
        }
    }

    public void moveDown() {
        if (iCoordinateToShow != gameMap.getMap().length - 1) {
            vBox.setVisible(false);
            iCoordinateToShow++;
        }
    }

}