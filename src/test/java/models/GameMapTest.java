package models;

import models.Tile.Tile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class GameMapTest {
    private GameMap gameMap;
    private Player player;
    private User user;
    private Tile tile;
    Tile[][] tiles = new Tile[30][30];

    private ArrayList<Player> players = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        player = new Player(user);
        players.add(player);
        gameMap = new GameMap(tiles);
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


}