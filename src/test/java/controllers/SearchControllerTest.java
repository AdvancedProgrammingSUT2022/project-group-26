package controllers;

import controllers.GameControllers.SearchController;
import models.City;
import models.GameMap;
import models.Player;
import models.Tile.Tile;
import models.Tile.TileMode;
import models.Tile.TileModeEnum;
import models.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class SearchControllerTest {
    private Player player1;
    private Player player2;
    private ArrayList<Player>players;

    @BeforeEach
    public void setUp(){
        player1 = new Player(new User("1", "1", "1"));
        player2 = new Player(new User("2", "2", "2"));
        players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        GameMap gameMap = new GameMap(players);
        player1.getCities().add(new City(gameMap.getTile(5,5),gameMap, "player1"));
        player2.getCities().add(new City(gameMap.getTile(10,10),gameMap, "player2"));
    }

    @Test
    public void findCityTest(){
        City city = SearchController.findCity(players, "player1");
        Assertions.assertEquals( player1.getCities().get(0), city);
    }

    @Test
    public void wrongFindCityTest(){
        City city = SearchController.findCity(players, "playerd1");
        Assertions.assertEquals(null, city);
    }

    @Test
    public void findPlayerCityTest(){
        Player player = SearchController.findPlayerOfCity(players, player1.getCities().get(0));
        Assertions.assertEquals(player1, player);
    }

    @Test
    public void wrongFindPlayerCityTest(){
        City city = player2.getCities().get(0);
        players.remove(player2);
        Player player = SearchController.findPlayerOfCity(players, city);
        Assertions.assertEquals(null, player);
    }

    @Test
    public void searchCityByCenterTest(){
        Tile tile = player1.getCities().get(0).getCenter();
        City city = SearchController.searchCityWithCenter(tile);
        Assertions.assertEquals(player1.getCities().get(0), city);
    }

    @Test
    public void wrongSearchCityByCenterTest(){
        City city = SearchController.searchCityWithCenter(null);
        Assertions.assertEquals(null, city);
    }

}
