package com.example.project.views;

import com.example.project.App;
import com.example.project.models.Feature.TileFeatureEnum;
import com.example.project.models.GameMap;
import com.example.project.models.Player;
import com.example.project.models.Resource.TileResourceEnum;
import com.example.project.models.Tile.Tile;
import com.example.project.models.Tile.TileModeEnum;
import com.example.project.models.Units.Combat.CombatUnits;
import com.example.project.models.Units.Nonecombat.BuilderUnit;
import com.example.project.models.Units.Nonecombat.NoneCombatUnits;
import com.example.project.models.Units.UnitNameEnum;
import javafx.scene.Cursor;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.HBox;
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
    private VBox tileVBox;
    private VBox combatUnitVBox;
    private VBox noneCombatUnitVBox;

    //tileVBox
    private Label tileMode;
    private Label tileFeature;
    private Label tileResource;
    private Label tileGold;
    private Label tileFood;

    //combatUnitVBox
    private Label combatUnitName;
    private Label combatUnitHealth;
    private Label combatUnitCombatStrength;
    private Label combatUnitMovementPoint;

    //noneCombatUnit
    private Label noneCombatUnitName;
    private Label noneCombatUnitMovementPoint;
    private HBox noneCombatUnitHBox;
    private Label noneCombatUnitBuild;


    private boolean isNotificationOpen = false;

    public void setData(Pane pane, VBox tileVBox, VBox combatUnitVBox, VBox noneCombatUnitVBox) {
        this.pane = pane;
        this.tileVBox = tileVBox;
        tileMode = (Label) ((Pane) tileVBox.getChildren().get(0)).getChildren().get(0);
        tileFeature = (Label) ((Pane) tileVBox.getChildren().get(0)).getChildren().get(1);
        tileResource = (Label) ((Pane) tileVBox.getChildren().get(0)).getChildren().get(2);
        tileGold = (Label) ((Pane) tileVBox.getChildren().get(0)).getChildren().get(3);
        tileFood = (Label) ((Pane) tileVBox.getChildren().get(0)).getChildren().get(4);

        this.combatUnitVBox = combatUnitVBox;
        combatUnitName = (Label) ((Pane) combatUnitVBox.getChildren().get(0)).getChildren().get(0);
        combatUnitHealth = (Label) ((Pane) combatUnitVBox.getChildren().get(0)).getChildren().get(1);
        combatUnitCombatStrength = (Label) ((Pane) combatUnitVBox.getChildren().get(0)).getChildren().get(2);
        combatUnitMovementPoint = (Label) ((Pane) combatUnitVBox.getChildren().get(0)).getChildren().get(3);

        this.noneCombatUnitVBox = noneCombatUnitVBox;
        noneCombatUnitName = (Label) ((Pane) noneCombatUnitVBox.getChildren().get(0)).getChildren().get(0);
        noneCombatUnitMovementPoint = (Label) ((Pane) noneCombatUnitVBox.getChildren().get(0)).getChildren().get(1);
        noneCombatUnitHBox = (HBox) ((Pane) noneCombatUnitVBox.getChildren().get(0)).getChildren().get(2);
        noneCombatUnitBuild = (Label) ((Pane) noneCombatUnitHBox.getChildren().get(0)).getChildren().get(0);
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


    private double tileSideLength = 90;
    private double tilePaneLength = 2 * tileSideLength;


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
        showVBoxes();
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
                        inVisibleAll();
                        if (mouseEvent.getButton() == MouseButton.SECONDARY)
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
                    imageView.setOnMouseClicked(mouseEvent -> {
                        inVisibleAll();
                        if (mouseEvent.getButton() == MouseButton.SECONDARY)
                            showTileData(playerGameMap.getTile(finalI, finalJ), xCoordinate, yCoordinate);
                    });
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
                    imageView.setOnMouseClicked(mouseEvent -> {
                        inVisibleAll();
                        if (mouseEvent.getButton() == MouseButton.SECONDARY)
                            showTileData(playerGameMap.getTile(finalI, finalJ), xCoordinate, yCoordinate);
                    });
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
                    double xCoordinate;
                    double yCoordinate;
                    if (j % 2 == 1)
                        yCoordinate = tilePaneLength * toShowI - tilePaneLength / 2;
                    else
                        yCoordinate = tilePaneLength * toShowI + tilePaneLength / 2 - tilePaneLength / 2;
                    xCoordinate = (tileSideLength * 3 / 2) * toShowJ - tilePaneLength / 2;

                    imageView.setY(yCoordinate + 30);
                    imageView.setX(xCoordinate + 20);
                    imageView.setFitWidth(70);
                    imageView.setFitHeight(70);
                    imageView.setCursor(Cursor.HAND);

                    int finalI = i;
                    int finalJ = j;
                    imageView.setOnMouseClicked(mouseEvent -> {
                        inVisibleAll();
                        UnitCommandFxController.getInstance().setSelectedUnit(
                                playerGameMap.getTile(finalI, finalJ).getCombatUnits());
                        if (mouseEvent.getButton() == MouseButton.SECONDARY)
                            showCombatData(playerGameMap.getTile(finalI, finalJ).getCombatUnits(), xCoordinate, yCoordinate);
                    });

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

                    double xCoordinate;
                    double yCoordinate;
                    if (j % 2 == 1)
                        yCoordinate = tilePaneLength * toShowI - tilePaneLength / 2;
                    else
                        yCoordinate = tilePaneLength * toShowI + tilePaneLength / 2 - tilePaneLength / 2;
                    xCoordinate = (tileSideLength * 3 / 2) * toShowJ - tilePaneLength / 2;

                    imageView.setY(yCoordinate + 30);
                    imageView.setX(xCoordinate + 85);
                    imageView.setFitWidth(70);
                    imageView.setFitHeight(70);
                    imageView.setCursor(Cursor.HAND);

                    int finalI = i;
                    int finalJ = j;
                    imageView.setOnMouseClicked(mouseEvent -> {
                        inVisibleAll();
                        UnitCommandFxController.getInstance().setSelectedUnit(
                                playerGameMap.getTile(finalI, finalJ).getNoneCombatUnits());
                        if (mouseEvent.getButton() == MouseButton.SECONDARY)
                            showNoneCombatData(playerGameMap.getTile(finalI, finalJ).getNoneCombatUnits(), xCoordinate, yCoordinate);
                    });

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
                        inVisibleAll();
                        if (mouseEvent.getButton() == MouseButton.SECONDARY)
                            showTileData(playerGameMap.getTile(finalI, finalJ), xCoordinate, yCoordinate);
                    });
                    this.pane.getChildren().add(imageView);
                }
            }
    }

    public void showTileData(Tile tile, double xCoordinate, double yCoordinate) {
        if (isNotificationOpen) {
            combatUnitVBox.setVisible(false);
            noneCombatUnitVBox.setVisible(false);
        }
        tileMode.setText(tile.getMode().getTileName().getName());
        if (tile.getFeature() != null)
            tileFeature.setText(tile.getFeature().getFeatureName().getName());
        else tileFeature.setText("no feature!");
        if (tile.getResource() != null)
            tileResource.setText(tile.getResource().getResourceName().getName());
        else tileResource.setText("no resource!");
        tileGold.setText(String.valueOf(tile.getGold()));
        tileFood.setText(String.valueOf(tile.getFood()));
        tileVBox.setLayoutX(xCoordinate - 7);
        tileVBox.setLayoutY(yCoordinate - 10);
        tileVBox.setVisible(true);
        isNotificationOpen = true;
    }

    public void showCombatData(CombatUnits combatUnits, double xCoordinate, double yCoordinate) {
        if (isNotificationOpen) {
            tileVBox.setVisible(false);
            noneCombatUnitVBox.setVisible(false);
        }
        combatUnitName.setText(combatUnits.getUnitNameEnum().getName());
        combatUnitHealth.setText(String.format("%.1f", combatUnits.getHealth()));
        combatUnitCombatStrength.setText(String.valueOf(combatUnits.getCombatStrength()));
        combatUnitMovementPoint.setText(String.format("%.1f", combatUnits.getMovement()));
        combatUnitVBox.setLayoutX(xCoordinate);
        combatUnitVBox.setLayoutY(yCoordinate);
        combatUnitVBox.setVisible(true);
        isNotificationOpen = true;
    }

    public void showNoneCombatData(NoneCombatUnits noneCombatUnits, double xCoordinate, double yCoordinate) {
        if (isNotificationOpen) {
            tileVBox.setVisible(false);
            combatUnitVBox.setVisible(false);
        }
        noneCombatUnitName.setText(noneCombatUnits.getUnitNameEnum().getName());
        noneCombatUnitMovementPoint.setText(String.format("%.1f", noneCombatUnits.getMovement()));
        if (noneCombatUnits instanceof BuilderUnit && noneCombatUnits.isAWorker()) {//TODO: error for this if
            noneCombatUnitBuild.setText(((BuilderUnit) noneCombatUnits).getWork());
            noneCombatUnitHBox.setVisible(true);
        } else noneCombatUnitHBox.setVisible(false);
        noneCombatUnitVBox.setLayoutX(xCoordinate);
        noneCombatUnitVBox.setLayoutY(yCoordinate);
        noneCombatUnitVBox.setVisible(true);
        isNotificationOpen = true;
    }

    private void showVBoxes() {
        if (tileVBox.isVisible())
            this.pane.getChildren().add(tileVBox);
        if (combatUnitVBox.isVisible())
            this.pane.getChildren().add(combatUnitVBox);
        if (noneCombatUnitVBox.isVisible())
            this.pane.getChildren().add(noneCombatUnitVBox);
        this.pane.getChildren().add(UnitCommandFxController.getInstance().getUnitCommandVbox());
    }

    public void moveLeft() {
        jCoordinateToShow--;
        tileVBox.setVisible(false);
        if (jCoordinateToShow < 0) {
            tileVBox.setVisible(true);
            jCoordinateToShow = 0;
        }
    }

    public void moveRight() {
        if (jCoordinateToShow != gameMap.getMap()[0].length - 1) {
            jCoordinateToShow++;
            tileVBox.setVisible(false);
        }
    }

    public void moveUp() {
        iCoordinateToShow--;
        tileVBox.setVisible(false);
        if (iCoordinateToShow < 0) {
            iCoordinateToShow = 0;
            tileVBox.setVisible(true);
        }
    }

    public void moveDown() {
        if (iCoordinateToShow != gameMap.getMap().length - 1) {
            tileVBox.setVisible(false);
            iCoordinateToShow++;
        }
    }

    public void inVisibleAll() {
        this.noneCombatUnitVBox.setVisible(false);
        this.combatUnitVBox.setVisible(false);
        this.tileVBox.setVisible(false);
    }

    public double getTilePaneLength() {
        return tilePaneLength;
    }

    public void setTilePaneLength(double tilePaneLength) {
        this.tilePaneLength = tilePaneLength;
        this.tileSideLength = tilePaneLength / 2;
    }
}