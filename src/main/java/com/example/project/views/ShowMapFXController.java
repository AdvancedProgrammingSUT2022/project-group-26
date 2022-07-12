package com.example.project.views;

import com.example.project.App;
import com.example.project.models.Feature.TileFeatureEnum;
import com.example.project.models.GameMap;
import com.example.project.models.Player;
import com.example.project.models.Resource.TileResourceEnum;
import com.example.project.models.Tile.TileModeEnum;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

public class ShowMapFXController {

    private static ShowMapFXController instance;
    private final Image fogOfWar =
            new Image(String.valueOf(new URL(App.class.getResource("/Image/Game/Tile/mode/fogOfWar.png").toString())));
    private final Image revealedImage =
            new Image(String.valueOf(new URL(App.class.getResource("/Image/Game/Tile/revealed.png").toString())));


    public ShowMapFXController() throws MalformedURLException {
    }

    private int iCoordinateToShow = 7;
    private int jCoordinateToShow = 3;

    public void moveLeft() {
        jCoordinateToShow--;
        if (jCoordinateToShow < 0)
            jCoordinateToShow = 0;
    }

    public void moveRight() {
        if (jCoordinateToShow != gameMap.getMap()[0].length - 1)
            jCoordinateToShow++;
    }

    public void moveUp() {
        iCoordinateToShow--;
        if (iCoordinateToShow < 0)
            iCoordinateToShow = 0;
    }

    public void moveDown() {
        if (iCoordinateToShow != gameMap.getMap().length - 1)
            iCoordinateToShow++;
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

    private final double tileSideLength = 90;
    private final double tilePaneLength = 2 * tileSideLength;


    public void showMap() throws MalformedURLException {
        playerGameMap = PlayGamePage.getInstance().getThisTurnPlayer().getGameMap();
        PlayGamePage.getInstance().getThisTurnPlayer().updateMap(this.gameMap);
        pane.getChildren().clear();
        showTiles();
        showFeatures();
        showResources();
        showInSightTiles();
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
                if (j % 2 == 1)
                    imageView.setY(tilePaneLength * toShowI - tilePaneLength / 2);
                else
                    imageView.setY(tilePaneLength * toShowI + tilePaneLength / 2 - tilePaneLength / 2);
                imageView.setX((tileSideLength * 3 / 2) * toShowJ - tilePaneLength / 2);

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
                    if (j % 2 == 1)
                        imageView.setY(tilePaneLength * toShowI - tilePaneLength / 2);
                    else
                        imageView.setY(tilePaneLength * toShowI + tilePaneLength / 2 - tilePaneLength / 2);
                    imageView.setX((tileSideLength * 3 / 2) * toShowJ - tilePaneLength / 2);
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
                    if (j % 2 == 1)
                        imageView.setY(tilePaneLength * toShowI - tilePaneLength / 2 + 120);
                    else
                        imageView.setY(tilePaneLength * toShowI + tilePaneLength / 2 - tilePaneLength / 2 + 120);
                    imageView.setX((tileSideLength * 3 / 2) * toShowJ - tilePaneLength / 2 + 65);
                    imageView.setFitWidth(50);
                    imageView.setFitHeight(50);
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
                        && PlayGamePage.getInstance().getThisTurnPlayer().isVisible(playerGameMap.getTile(i, j), this.gameMap)
                        && new Random().nextInt() % 2 == 0) {
                    ImageView imageView =
                            new ImageView(revealedImage);
                    imageView.setFitWidth(tilePaneLength);
                    imageView.setFitHeight(tilePaneLength);
                    if (j % 2 == 1)
                        imageView.setY(tilePaneLength * toShowI - tilePaneLength / 2);
                    else
                        imageView.setY(tilePaneLength * toShowI + tilePaneLength / 2 - tilePaneLength / 2);
                    imageView.setX((tileSideLength * 3 / 2) * toShowJ - tilePaneLength / 2);
                    this.pane.getChildren().add(imageView);
                }
            }
    }

    public void setPane(Pane pane) {
        this.pane = pane;
    }

}