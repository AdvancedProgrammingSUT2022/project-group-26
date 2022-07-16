package com.example.project.views;

import com.example.project.App;
import com.example.project.controllers.Output;
import com.example.project.models.Building.Building;
import com.example.project.models.Building.BuildingEnum;
import com.example.project.models.City;
import com.example.project.models.Feature.TileFeatureEnum;
import com.example.project.models.GameMap;
import com.example.project.models.Player;
import com.example.project.models.Resource.TileResourceEnum;
import com.example.project.models.Tile.Tile;
import com.example.project.models.Tile.TileModeEnum;
import com.example.project.models.Units.UnitNameEnum;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.Alert;
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
    private final Image selectedForMoveImage =
            new Image(String.valueOf(new URL(App.class.getResource("/Image/Game/selectedForMove.png").toString())));
    private final Image selectedForAttackImage =
            new Image(String.valueOf(new URL(App.class.getResource("/Image/Game/selectedForAttack.png").toString())));
    private final Image cityBorderImage =
            new Image(String.valueOf(new URL(App.class.getResource("/Image/Game/cityBorder.png").toString())));
    private final Image cityCapitalBuildingImage =
            new Image(String.valueOf(new URL(App.class.getResource("/Image/Game/capitalBuilding.png").toString())));
    private final Image ruinImage =
            new Image(String.valueOf(new URL(App.class.getResource("/Image/Game/ruin.png").toString())));

    private boolean isMouseOnTile = false;

    private GameMap gameMap;
    private ArrayList<Player> players;
    private GameMap playerGameMap;

    private Pane pane;
    private Pane infoPanel;
    private Node[] infoPanelNodes;

    private VBox tileVBox;
    private VBox combatUnitVBox;
    private VBox noneCombatUnitVBox;
    private VBox cityBannerVBox;

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

    //cityBanner
    private Label cityName;
    private Label cityCombatStrength;

    private boolean isNotificationOpen = false;

    private int iCoordinateToShow = 7;
    private int jCoordinateToShow = 3;

    private double tileSideLength = 90;
    private double tilePaneLength = 2 * tileSideLength;


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

    public void setUp(GameMap gameMap, ArrayList<Player> players) {
        this.gameMap = gameMap;
        this.players = players;
    }

    public void setData(Pane pane, Pane infoPanel, VBox tileVBox, VBox combatUnitVBox, VBox noneCombatUnitVBox, VBox cityBannerVBox) {
        this.pane = pane;
        this.infoPanel = infoPanel;
        infoPanelNodes = infoPanel.getChildren().toArray(new Node[4]);
        this.tileVBox = tileVBox;
        tileMode = (Label) ((Pane) tileVBox.getChildren().get(0)).getChildren().get(0);
        tileFeature = (Label) ((Pane) tileVBox.getChildren().get(0)).getChildren().get(1);
        tileResource = (Label) ((Pane) tileVBox.getChildren().get(0)).getChildren().get(2);
        tileGold = (Label) ((Pane) tileVBox.getChildren().get(0)).getChildren().get(3);
        tileFood = (Label) ((Pane) tileVBox.getChildren().get(0)).getChildren().get(4);

        this.cityBannerVBox = cityBannerVBox;
        cityName = (Label) ((Pane) cityBannerVBox.getChildren().get(0)).getChildren().get(0);
        cityCombatStrength = (Label) ((Pane) cityBannerVBox.getChildren().get(0)).getChildren().get(1);

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

    public void showMap() throws MalformedURLException {
        isMouseOnTile = false;
        PlayGamePage.getInstance().getThisTurnPlayer().updateMap(PlayGamePage.getInstance().getGameMap());
        this.playerGameMap = PlayGamePage.getInstance().getThisTurnPlayer().getGameMap();
        pane.getChildren().clear();
        showTiles();
        showFeatures();
        showResources();
        showCityCapital();
        showCombatUnits();
        showNoneCombatUnits();
        showInSightTiles();
        showCityBorder();
        showBuildings();
        showRuins();
        showVBoxes();
        addInfoPanel();
    }

    private double getXCoordinate(int i, int j) {
        int toShowI = i - iCoordinateToShow;
        int toShowJ = j - jCoordinateToShow;
        return (tileSideLength * 3 / 2) * toShowJ - tilePaneLength / 2;
    }

    private double getYCoordinate(int i, int j) {
        int toShowI = i - iCoordinateToShow;
        int toShowJ = j - jCoordinateToShow;
        if (j % 2 == 1)
            return tilePaneLength * toShowI - tilePaneLength / 2;
        else
            return tilePaneLength * toShowI + tilePaneLength / 2 - tilePaneLength / 2;
    }

    private void setOnTileMouseMoved(ImageView imageView, int finalI, int finalJ, double xCoordinate, double yCoordinate) {
        imageView.setOnMouseMoved(mouseEvent -> {
            if (!isMouseOnTile && UnitCommandFxController.getInstance().isUserMustSelectATile())
                addForSelectImage(xCoordinate, yCoordinate);
            PlayGamePage.getInstance().setMouseOnTile(true);
            showTileData(playerGameMap.getTile(finalI, finalJ));
        });
    }

    private void addInfoPanel() {
        for (Node infoPanelNode : infoPanelNodes) {
            pane.getChildren().add(infoPanelNode);
        }
    }

    private void showTiles() {
        for (int i = iCoordinateToShow; i < iCoordinateToShow + 6; i++)
            for (int j = jCoordinateToShow; j < jCoordinateToShow + 12; j++) {
                ImageView imageView;
                if (playerGameMap.getTile(i, j) != null) {
                    imageView =
                            new ImageView(TileModeEnum.getImages().get(playerGameMap.getTile(i, j).getMode().getTileName()));
                } else imageView = new ImageView(fogOfWar);
                imageView.setFitWidth(tilePaneLength);
                imageView.setFitHeight(tilePaneLength);
                double xCoordinate = getXCoordinate(i, j);
                double yCoordinate = getYCoordinate(i, j);

                imageView.setX(xCoordinate);
                imageView.setY(yCoordinate);
                if (UnitCommandFxController.getInstance().isUserMustSelectATile() && playerGameMap.getTile(i, j) != null)
                    imageView.setCursor(Cursor.HAND);

                int finalI = i;
                int finalJ = j;
                imageView.setOnMouseMoved(mouseEvent -> {
                    if (!isMouseOnTile && UnitCommandFxController.getInstance().isUserMustSelectATile()
                            && playerGameMap.getTile(finalI, finalJ) != null)
                        addForSelectImage(xCoordinate, yCoordinate);
                    PlayGamePage.getInstance().setMouseOnTile(true);
                    if (playerGameMap.getTile(finalI, finalJ) != null)
                        showTileData(playerGameMap.getTile(finalI, finalJ));
                });
                if (UnitCommandFxController.getInstance().isUserMustSelectATile())
                    imageView.setOnMouseClicked(mouseEvent -> {
                        UnitCommandFxController.getInstance().setSelectedTile(playerGameMap.getTile(finalI, finalJ));
                        UnitCommandFxController.getInstance().doAction();
                    });
                this.pane.getChildren().add(imageView);
            }
    }

    private void showFeatures() {
        for (int i = iCoordinateToShow; i < iCoordinateToShow + 6; i++)
            for (int j = jCoordinateToShow; j < jCoordinateToShow + 12; j++) {
                if (playerGameMap.getTile(i, j) != null && playerGameMap.getTile(i, j).getFeature() != null) {
                    ImageView imageView =
                            new ImageView(TileFeatureEnum.getImages().get(playerGameMap.getTile(i, j).getFeature().getFeatureName()));
                    imageView.setFitWidth(tilePaneLength);
                    imageView.setFitHeight(tilePaneLength);
                    double xCoordinate = getXCoordinate(i, j);
                    double yCoordinate = getYCoordinate(i, j);

                    imageView.setX(xCoordinate);
                    imageView.setY(yCoordinate);
                    if (UnitCommandFxController.getInstance().isUserMustSelectATile())
                        imageView.setCursor(Cursor.HAND);
                    int finalI = i;
                    int finalJ = j;
                    setOnTileMouseMoved(imageView, finalI, finalJ, xCoordinate, yCoordinate);
                    if (UnitCommandFxController.getInstance().isUserMustSelectATile())
                        imageView.setOnMouseClicked(mouseEvent -> {
                            UnitCommandFxController.getInstance().setSelectedTile(playerGameMap.getTile(finalI, finalJ));
                            UnitCommandFxController.getInstance().doAction();
                        });
                    this.pane.getChildren().add(imageView);
                }
            }
    }

    private void showResources() {
        for (int i = iCoordinateToShow; i < iCoordinateToShow + 6; i++)
            for (int j = jCoordinateToShow; j < jCoordinateToShow + 12; j++) {
                if (playerGameMap.getTile(i, j) != null && playerGameMap.getTile(i, j).getResource() != null) {
                    ImageView imageView =
                            new ImageView(TileResourceEnum.getImages().get(playerGameMap.getTile(i, j).getResource().getResourceName()));
                    imageView.setFitWidth(tilePaneLength);
                    imageView.setFitHeight(tilePaneLength);
                    double xCoordinate = getXCoordinate(i, j);
                    double yCoordinate = getYCoordinate(i, j);


                    imageView.setY(yCoordinate + 120);
                    imageView.setX(xCoordinate + 65);
                    imageView.setFitWidth(50);
                    imageView.setFitHeight(50);
                    if (UnitCommandFxController.getInstance().isUserMustSelectATile())
                        imageView.setCursor(Cursor.HAND);
                    int finalI = i;
                    int finalJ = j;
                    setOnTileMouseMoved(imageView, finalI, finalJ, xCoordinate, yCoordinate);
                    if (UnitCommandFxController.getInstance().isUserMustSelectATile())
                        imageView.setOnMouseClicked(mouseEvent -> {
                            UnitCommandFxController.getInstance().setSelectedTile(playerGameMap.getTile(finalI, finalJ));
                            UnitCommandFxController.getInstance().doAction();
                        });
                    this.pane.getChildren().add(imageView);
                }
            }
    }

    private void showCombatUnits() {
        for (int i = iCoordinateToShow; i < iCoordinateToShow + 6; i++)
            for (int j = jCoordinateToShow; j < jCoordinateToShow + 12; j++) {
                if (playerGameMap.getTile(i, j) != null && playerGameMap.getTile(i, j).getCombatUnits() != null) {
                    ImageView imageView =
                            new ImageView(UnitNameEnum.getImages().get(playerGameMap.getTile(i, j).getCombatUnits().getUnitNameEnum()));
                    double xCoordinate = getXCoordinate(i, j);
                    double yCoordinate = getYCoordinate(i, j);


                    imageView.setY(yCoordinate + 30);
                    imageView.setX(xCoordinate + 20);
                    imageView.setFitWidth(70);
                    imageView.setFitHeight(70);
                    if (playerGameMap.getTile(i, j).getCombatUnits().getPlayer() == PlayGamePage.getInstance().getThisTurnPlayer())
                        imageView.setCursor(Cursor.HAND);

                    int finalI = i;
                    int finalJ = j;
                    imageView.setOnMouseClicked(mouseEvent -> {
                        inVisibleAll();
                        UnitCommandFxController.getInstance().setSelectedUnit(
                                playerGameMap.getTile(finalI, finalJ).getCombatUnits());
                    });

                    this.pane.getChildren().add(imageView);
                }
            }
    }

    private void showNoneCombatUnits() {
        for (int i = iCoordinateToShow; i < iCoordinateToShow + 6; i++)
            for (int j = jCoordinateToShow; j < jCoordinateToShow + 12; j++) {
                if (playerGameMap.getTile(i, j) != null && playerGameMap.getTile(i, j).getNoneCombatUnits() != null) {
                    ImageView imageView =
                            new ImageView(UnitNameEnum.getImages().get(playerGameMap.getTile(i, j).getNoneCombatUnits().getUnitNameEnum()));

                    double xCoordinate = getXCoordinate(i, j);
                    double yCoordinate = getYCoordinate(i, j);


                    imageView.setY(yCoordinate + 30);
                    imageView.setX(xCoordinate + 85);
                    imageView.setFitWidth(70);
                    imageView.setFitHeight(70);
                    if (playerGameMap.getTile(i, j).getNoneCombatUnits().getPlayer() == PlayGamePage.getInstance().getThisTurnPlayer())
                        imageView.setCursor(Cursor.HAND);

                    int finalI = i;
                    int finalJ = j;
                    imageView.setOnMouseClicked(mouseEvent -> {
                        inVisibleAll();
                        UnitCommandFxController.getInstance().setSelectedUnit(
                                playerGameMap.getTile(finalI, finalJ).getNoneCombatUnits());
                    });
                    this.pane.getChildren().add(imageView);
                }
            }
    }

    public void showInSightTiles() {
        for (int i = iCoordinateToShow; i < iCoordinateToShow + 6; i++)
            for (int j = jCoordinateToShow; j < jCoordinateToShow + 12; j++) {
                if (playerGameMap.getTile(i, j) != null
                        && !PlayGamePage.getInstance().getThisTurnPlayer().isVisible(playerGameMap.getTile(i, j), this.gameMap)) {
                    ImageView imageView =
                            new ImageView(revealedImage);
                    imageView.setFitWidth(tilePaneLength);
                    imageView.setFitHeight(tilePaneLength);
                    double xCoordinate = getXCoordinate(i, j);
                    double yCoordinate = getYCoordinate(i, j);

                    imageView.setX(xCoordinate);
                    imageView.setY(yCoordinate);
                    imageView.setCursor(Cursor.HAND);
                    int finalI = i;
                    int finalJ = j;
                    imageView.setOnMouseMoved(mouseEvent -> {
                        showTileData(playerGameMap.getTile(finalI, finalJ));
                    });
                    this.pane.getChildren().add(imageView);
                }
            }
    }

    public void showTileData(Tile tile) {
        tileMode.setText(tile.getMode().getTileName().getName());
        if (tile.getFeature() != null)
            tileFeature.setText(tile.getFeature().getFeatureName().getName());
        else tileFeature.setText("no feature!");
        if (tile.getResource() != null)
            tileResource.setText(tile.getResource().getResourceName().getName());
        else tileResource.setText("no resource!");
        tileGold.setText(String.valueOf(tile.getGold()));
        tileFood.setText(String.valueOf(tile.getFood()));
    }

    public void showCityBanner(Tile tile, double xCoordinate, double yCoordinate) {
        if (isNotificationOpen) {
            noneCombatUnitVBox.setVisible(false);
            combatUnitVBox.setVisible(false);
        }
        cityName.setText(PlayGamePage.getInstance().getThisTurnPlayer().getCityByTile(
                GameMap.getCorrespondingTile(tile, playerGameMap, this.gameMap)).getName());
        cityCombatStrength.setText(String.valueOf(PlayGamePage.getInstance().getThisTurnPlayer().getCityByTile(GameMap.getCorrespondingTile(tile, playerGameMap, this.gameMap)).getCombatStrength()));
        cityBannerVBox.setLayoutX(xCoordinate);
        cityBannerVBox.setLayoutY(yCoordinate);
        cityBannerVBox.setVisible(true);
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
        if (!PlayGamePage.getInstance().isMouseOnTile())
            this.pane.getChildren().add(UnitCommandFxController.getInstance().getCommandData());
        this.pane.getChildren().add(cityBannerVBox);
    }

    public void moveLeft() {
        inVisibleAll();
        jCoordinateToShow--;
        if (jCoordinateToShow < 0) {
            jCoordinateToShow = 0;
        }
    }

    public void moveRight() {
        inVisibleAll();
        if (jCoordinateToShow != gameMap.getMap()[0].length - 1) {
            jCoordinateToShow++;
        }
    }

    public void moveUp() {
        inVisibleAll();
        iCoordinateToShow--;
        if (iCoordinateToShow < 0) {
            iCoordinateToShow = 0;
        }
    }

    public void moveDown() {
        inVisibleAll();
        if (iCoordinateToShow != gameMap.getMap().length - 1) {
            iCoordinateToShow++;
        }
    }

    public void inVisibleAll() {
        this.noneCombatUnitVBox.setVisible(false);
        this.combatUnitVBox.setVisible(false);
        this.cityBannerVBox.setVisible(false);
    }

    public double getTilePaneLength() {
        return tilePaneLength;
    }

    public void setTilePaneLength(double tilePaneLength) {
        this.tilePaneLength = tilePaneLength;
        this.tileSideLength = tilePaneLength / 2;
    }

    private void addForSelectImage(double xCoordinate, double yCoordinate) {
        isMouseOnTile = true;
        if (UnitCommandFxController.getInstance().isMoveSelected()) {
            ImageView imageView = new ImageView(selectedForMoveImage);
            imageView.setFitHeight(80);
            imageView.setFitWidth(80);
            imageView.setX(xCoordinate + 50);
            imageView.setY(yCoordinate + 50);
            pane.getChildren().add(imageView);
        } else {
            ImageView imageView = new ImageView(selectedForAttackImage);
            imageView.setFitHeight(80);
            imageView.setFitWidth(80);
            imageView.setX(xCoordinate + 50);
            imageView.setY(yCoordinate + 50);
            pane.getChildren().add(imageView);
        }
    }

    private void showCityBorder() {
        for (int i = iCoordinateToShow; i < iCoordinateToShow + 6; i++)
            for (int j = jCoordinateToShow; j < jCoordinateToShow + 12; j++) {
                if (playerGameMap.getTile(i, j) != null && City.isCity(i, j, PlayGamePage.getInstance().getThisTurnPlayer())) {
                    ImageView imageView =
                            new ImageView(cityBorderImage);
                    imageView.setFitWidth(tilePaneLength);
                    imageView.setFitHeight(tilePaneLength);
                    double xCoordinate = getXCoordinate(i, j);
                    double yCoordinate = getYCoordinate(i, j);

                    imageView.setX(xCoordinate);
                    imageView.setY(yCoordinate);
                    if (UnitCommandFxController.getInstance().isUserMustSelectATile())
                        imageView.setCursor(Cursor.HAND);
                    int finalI = i;
                    int finalJ = j;
                    setOnTileMouseMoved(imageView, finalI, finalJ, xCoordinate, yCoordinate);
                    if (UnitCommandFxController.getInstance().isUserMustSelectATile())
                        imageView.setOnMouseClicked(mouseEvent -> {
                            UnitCommandFxController.getInstance().setSelectedTile(playerGameMap.getTile(finalI, finalJ));
                            UnitCommandFxController.getInstance().doAction();
                        });
                    this.pane.getChildren().add(imageView);
                }
            }
    }

    private void showCityCapital() {
        for (int i = iCoordinateToShow; i < iCoordinateToShow + 6; i++)
            for (int j = jCoordinateToShow; j < jCoordinateToShow + 12; j++) {
                if (playerGameMap.getTile(i, j) != null && City.isCityCenter(i, j, PlayGamePage.getInstance().getThisTurnPlayer())) {
                    ImageView imageView =
                            new ImageView(cityCapitalBuildingImage);
                    imageView.setFitWidth(90);
                    imageView.setFitHeight(90);
                    imageView.setCursor(Cursor.HAND);
                    double xCoordinate = getXCoordinate(i, j);
                    double yCoordinate = getYCoordinate(i, j);

                    imageView.setX(xCoordinate + 45);
                    imageView.setY(yCoordinate + 15);
                    int finalI = i;
                    int finalJ = j;

                    imageView.setOnMouseClicked(mouseEvent -> {
                        if (mouseEvent.getButton() == MouseButton.SECONDARY)
                            showCityBanner(playerGameMap.getTile(finalI, finalJ), xCoordinate, yCoordinate);
                    });
                    setOnTileMouseMoved(imageView, finalI, finalJ, xCoordinate, yCoordinate);
                    this.pane.getChildren().add(imageView);
                }
            }
    }

    private void showBuildings() {
        for (int i = iCoordinateToShow; i < iCoordinateToShow + 6; i++)
            for (int j = jCoordinateToShow; j < jCoordinateToShow + 12; j++) {
                if (playerGameMap.getTile(i, j) != null
                        && GameMap.getCorrespondingTile(playerGameMap.getTile(i, j), this.playerGameMap, this.gameMap).getBuilding() != null) {
                    Building building = GameMap.getCorrespondingTile(playerGameMap.getTile(i, j), this.playerGameMap, this.gameMap).getBuilding();
                    ImageView imageView = new ImageView(BuildingEnum.getImages().get(
                            building.getName()));
                    imageView.setFitWidth(50);
                    imageView.setFitHeight(50);
                    double xCoordinate = getXCoordinate(i, j);
                    double yCoordinate = getYCoordinate(i, j);

                    imageView.setX(xCoordinate + 67);
                    imageView.setY(yCoordinate + 70);
                    if (UnitCommandFxController.getInstance().isUserMustSelectATile())
                        imageView.setCursor(Cursor.HAND);

                    int finalI = i;
                    int finalJ = j;
                    setOnTileMouseMoved(imageView, finalI, finalJ, xCoordinate, yCoordinate);
                    if (UnitCommandFxController.getInstance().isUserMustSelectATile())
                        imageView.setOnMouseClicked(mouseEvent -> {
                            UnitCommandFxController.getInstance().setSelectedTile(playerGameMap.getTile(finalI, finalJ));
                            UnitCommandFxController.getInstance().doAction();
                        });
                    this.pane.getChildren().add(imageView);
                }
            }
    }

    private void showRuins() {
        for (int i = iCoordinateToShow; i < iCoordinateToShow + 6; i++)
            for (int j = jCoordinateToShow; j < jCoordinateToShow + 12; j++) {
                if (playerGameMap.getTile(i, j) != null
                        && playerGameMap.getTile(i, j).isRuined()) {
                    Tile mainTile = GameMap.getCorrespondingTile(playerGameMap.getTile(i, j), playerGameMap, gameMap);
                    if (!PlayGamePage.getInstance().getPlayGameMenuController().isRuinTileSeenBefore(mainTile)) {
                        new PopupMessage(Alert.AlertType.INFORMATION, Output.RUIN_FOUND.toString());
                        PlayGamePage.getInstance().getPlayGameMenuController().ruinFound(mainTile);
                    }
                    ImageView imageView = new ImageView(ruinImage);
                    imageView.setFitWidth(80);
                    imageView.setFitHeight(80);
                    double xCoordinate = getXCoordinate(i, j);
                    double yCoordinate = getYCoordinate(i, j);
                    imageView.setX(xCoordinate + 52);
                    imageView.setY(yCoordinate + 50);

                    if (UnitCommandFxController.getInstance().isUserMustSelectATile())
                        imageView.setCursor(Cursor.HAND);

                    int finalI = i;
                    int finalJ = j;
                    setOnTileMouseMoved(imageView, finalI, finalJ, xCoordinate, yCoordinate);
                    if (UnitCommandFxController.getInstance().isUserMustSelectATile())
                        imageView.setOnMouseClicked(mouseEvent -> {
                            UnitCommandFxController.getInstance().setSelectedTile(playerGameMap.getTile(finalI, finalJ));
                            UnitCommandFxController.getInstance().doAction();
                        });
                    this.pane.getChildren().add(imageView);
                }
            }
    }
}