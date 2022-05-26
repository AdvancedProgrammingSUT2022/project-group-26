package models;

import com.example.project.models.Feature.TileFeature;
import com.example.project.models.Feature.TileFeatureEnum;
import com.example.project.models.GameMap;
import com.example.project.models.Player;
import com.example.project.models.Tile.Tile;
import com.example.project.models.Tile.TileMode;
import com.example.project.models.Tile.TileModeEnum;
import com.example.project.models.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class GameMapTest {
    private GameMap gameMap;
    private GameMap mainGameMap;
    private GameMap tileGameMap;
    private Player player;
    private User user;
    private Tile tile;
    private Tile[][] tiles = new Tile[30][30];

    private ArrayList<Player> players = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        player = new Player(user);
        players.add(player);
        gameMap = new GameMap(players);
        mainGameMap = new GameMap(players);
        tileGameMap = new GameMap(players);
        tile = new Tile(null, null, null);
    }

    @Test
    public void initialGameMapTest() {
        gameMap = new GameMap(players);
        Assertions.assertEquals(null, players.get(0).getGameMap().getTile(0, 0));
    }

    @Test
    public void getIndexITest(){
        Assertions.assertEquals(0, gameMap.getIndexI(gameMap.getTile(0,0)));
    }

    @Test
    public void getIndexJTest(){
        Assertions.assertEquals(0, gameMap.getIndexJ(gameMap.getTile(0,0)));
    }

    @Test
    public void getInsightTilesTest(){
        for (int i=0; i<3; i++){
            for (int j=0; j<3; j++){
                tiles[i][j] = new Tile(new TileMode(TileModeEnum.SNOW), null, null);
                tiles[i][j].setFeature(new TileFeature(TileFeatureEnum.ICE));
            }
        }
        gameMap = new GameMap(tiles);
        ArrayList<Tile> insightTiles = new ArrayList<>(Arrays.asList(gameMap.getTile(0,1), gameMap.getTile(0,2), gameMap.getTile(1,1), gameMap.getTile(1,2), gameMap.getTile(1,0), gameMap.getTile(2,1), gameMap.getTile(2,0)));
        Assertions.assertEquals(insightTiles.size()+1, gameMap.getUnitInSightTiles(gameMap.getTile(0,0)).size());
    }

    @Test
    public void getCityInsightTilesTest(){
        ArrayList<Tile> insightTiles = new ArrayList<>(Arrays.asList(gameMap.getTile(0,1), gameMap.getTile(1,0)));
        Assertions.assertEquals(insightTiles.size()+1, gameMap.getCityInSightTiles(gameMap.getTile(0,0)).size());
    }

    @Test
    public void getCorrespondingTileTest(){
        Assertions.assertEquals(mainGameMap.getTile(0, 0), GameMap.getCorrespondingTile(tileGameMap.getTile(0, 0), tileGameMap, mainGameMap));
    }

    @Test
    public void wrongGetCorrespondingTileTest(){
        Assertions.assertEquals(null, GameMap.getCorrespondingTile(tileGameMap.getTile(-1, 0), tileGameMap, mainGameMap));
    }

}