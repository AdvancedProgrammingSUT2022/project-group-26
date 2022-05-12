package controllers;

import controllers.GameControllers.GameMenuCommandController;
import controllers.GameControllers.PlayGameMenuController;
import controllers.GameControllers.ShowMapController;
import models.GameMap;
import models.Improvement.TileImprovement;
import models.Improvement.TileImprovementEnum;
import models.Player;
import models.Tile.Tile;
import models.Tile.TileMode;
import models.Tile.TileModeEnum;
import models.Units.Nonecombat.NoneCombatUnits;
import models.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class ShowMapControllerTest {
    private ArrayList<Player> players = new ArrayList<>();
    private Player player1;
    private Player player2;
    private Player player3;
    private Player player4;
    private Player player5;
    private Player player6;
    private GameMap gameMap;
    private ShowMapController showMapController;
    private PlayGameMenuController playGameMenuController;

    @BeforeEach
    public void setUp() {
        player1 = new Player(new User("ilya", "", "ilya"));
        player2 = new Player(new User("ilya", "", "ilya"));
        player3 = new Player(new User("ilya", "", "ilya"));
        player4 = new Player(new User("ilya", "", "ilya"));
        player5 = new Player(new User("ilya", "", "ilya"));
        player6 = new Player(new User("ilya", "", "ilya"));
        players.add(player1);
        players.add(player2);
        players.add(player3);
        players.add(player4);
        players.add(player5);
        players.add(player6);
        gameMap = new GameMap(players);
        player1.setGameMap(gameMap);
        showMapController = new ShowMapController(gameMap, players);
        playGameMenuController = new PlayGameMenuController(gameMap, players);
        playGameMenuController.createCity((NoneCombatUnits) player1.getUnits().get(1), player1, "mammad");
    }

    //getCenters
    @Test
    public void getCentersTest() {
        int[][][] centers = showMapController.getCenters();
        Assertions.assertEquals(3, centers[0][0][0]);
    }

    //setTileArrayToPrint
    @Test
    public void setTileArrayToPrintTest() {
        int iCoordinate = 8;
        int jCoordinate = 5;
        Tile[][] tilesToShow = new Tile[3][6];
        showMapController.setTileArrayToPrint(iCoordinate, jCoordinate, tilesToShow, player1.getGameMap().getMap());
        Assertions.assertEquals(tilesToShow[0][0], player1.getGameMap().getTile(8, 5));
    }

    @Test
    public void oddSetTileArrayToPrintTest() {
        int iCoordinate = 8;
        int jCoordinate = 6;
        Tile[][] tilesToShow = new Tile[3][6];
        showMapController.setTileArrayToPrint(iCoordinate, jCoordinate, tilesToShow, player1.getGameMap().getMap());
        Assertions.assertEquals(tilesToShow[0][0], player1.getGameMap().getTile(8, 6));
    }

    //setToPrintStrings
    @Test
    public void player1SetToPrintStrings() {
        int iCoordinate = 8;
        int jCoordinate = 6;
        Tile[][] tilesToShow = new Tile[3][6];
        showMapController.setTileArrayToPrint(iCoordinate, jCoordinate, tilesToShow, player1.getGameMap().getMap());
        String[][] toPrint = new String[80][80];
        showMapController.setToPrintStrings(toPrint, tilesToShow, iCoordinate, jCoordinate, 0);
        Assertions.assertEquals(toPrint[0][0], " ");
    }

    @Test
    public void player2SetToPrintStrings() {
        int iCoordinate = 8;
        int jCoordinate = 11;
        Tile[][] tilesToShow = new Tile[3][6];
        showMapController.setTileArrayToPrint(iCoordinate, jCoordinate, tilesToShow, player2.getGameMap().getMap());
        String[][] toPrint = new String[80][80];
        showMapController.setToPrintStrings(toPrint, tilesToShow, iCoordinate, jCoordinate, 1);
        Assertions.assertEquals(toPrint[0][0], " ");
    }

    @Test
    public void player3SetToPrintStrings() {
        int iCoordinate = 8;
        int jCoordinate = 6;
        Tile[][] tilesToShow = new Tile[3][6];
        showMapController.setTileArrayToPrint(iCoordinate, jCoordinate, tilesToShow, player3.getGameMap().getMap());
        String[][] toPrint = new String[80][80];
        showMapController.setToPrintStrings(toPrint, tilesToShow, iCoordinate, jCoordinate, 2);
        Assertions.assertEquals(toPrint[0][0], " ");
    }

    @Test
    public void player4SetToPrintStrings() {
        int iCoordinate = 8;
        int jCoordinate = 6;
        Tile[][] tilesToShow = new Tile[3][6];
        showMapController.setTileArrayToPrint(iCoordinate, jCoordinate, tilesToShow, player4.getGameMap().getMap());
        String[][] toPrint = new String[80][80];
        showMapController.setToPrintStrings(toPrint, tilesToShow, iCoordinate, jCoordinate, 3);
        Assertions.assertEquals(toPrint[0][0], " ");
    }

    @Test
    public void player5SetToPrintStrings() {
        int iCoordinate = 8;
        int jCoordinate = 6;
        Tile[][] tilesToShow = new Tile[3][6];
        showMapController.setTileArrayToPrint(iCoordinate, jCoordinate, tilesToShow, player5.getGameMap().getMap());
        String[][] toPrint = new String[80][80];
        showMapController.setToPrintStrings(toPrint, tilesToShow, iCoordinate, jCoordinate, 4);
        Assertions.assertEquals(toPrint[0][0], " ");
    }

    @Test
    public void Player6SetToPrintStrings() {
        int iCoordinate = 8;
        int jCoordinate = 6;
        Tile[][] tilesToShow = new Tile[3][6];
        showMapController.setTileArrayToPrint(iCoordinate, jCoordinate, tilesToShow, player6.getGameMap().getMap());
        String[][] toPrint = new String[80][80];
        showMapController.setToPrintStrings(toPrint, tilesToShow, iCoordinate, jCoordinate, 5);
        Assertions.assertEquals(toPrint[0][0], " ");
    }

    @Test
    public void tundraPlayer1SetToPrintStrings() {
        Tile tile = player1.getGameMap().getTile(0, 0);
        tile.setMode(new TileMode(TileModeEnum.TUNDRA));
        int iCoordinate = 0;
        int jCoordinate = 0;
        Tile[][] tilesToShow = new Tile[3][6];
        showMapController.setTileArrayToPrint(iCoordinate, jCoordinate, tilesToShow, player1.getGameMap().getMap());
        String[][] toPrint = new String[80][80];
        showMapController.setToPrintStrings(toPrint, tilesToShow, iCoordinate, jCoordinate, 0);
        Assertions.assertEquals(toPrint[0][0], " ");
    }

    @Test
    public void snowPlayer1SetToPrintStrings() {
        Tile tile = player1.getGameMap().getTile(0, 0);
        tile.setMode(new TileMode(TileModeEnum.SNOW));
        tile.setImprovement(new TileImprovement(TileImprovementEnum.FARMING));

        int iCoordinate = 0;
        int jCoordinate = 0;
        Tile[][] tilesToShow = new Tile[3][6];
        showMapController.setTileArrayToPrint(iCoordinate, jCoordinate, tilesToShow, player1.getGameMap().getMap());
        String[][] toPrint = new String[80][80];
        showMapController.setToPrintStrings(toPrint, tilesToShow, iCoordinate, jCoordinate, 0);
        Assertions.assertEquals(toPrint[0][0], " ");
    }

    @Test
    public void mountainPlayer1SetToPrintStrings() {
        Tile tile = player1.getGameMap().getTile(0, 0);
        tile.setMode(new TileMode(TileModeEnum.MOUNTAIN));
        tile.setImprovement(new TileImprovement(TileImprovementEnum.FARM));

        int iCoordinate = 0;
        int jCoordinate = 0;
        Tile[][] tilesToShow = new Tile[3][6];
        showMapController.setTileArrayToPrint(iCoordinate, jCoordinate, tilesToShow, player1.getGameMap().getMap());
        String[][] toPrint = new String[80][80];
        showMapController.setToPrintStrings(toPrint, tilesToShow, iCoordinate, jCoordinate, 0);
        Assertions.assertEquals(toPrint[0][0], " ");
    }
}