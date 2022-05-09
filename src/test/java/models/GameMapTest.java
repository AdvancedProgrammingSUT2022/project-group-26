package models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class GameMapTest {
    private GameMap gameMap;
    private Player player;
    private User user;

    private ArrayList<Player> players = new ArrayList<>();

    @BeforeEach
    public void setUp() {
        player = new Player(user);
        players.add(player);
    }

    @Test
    public void lj() {
        gameMap = new GameMap(players);
        Assertions.assertEquals(null, players.get(0).getGameMap().getTile(0, 0));
    }
}