package controllers;

import controllers.GameControllers.CitizenController;
import models.City;
import models.GameMap;
import models.Improvement.TileImprovement;
import models.Improvement.TileImprovementEnum;
import models.Player;
import models.Resource.TileResource;
import models.Resource.TileResourceEnum;
import models.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class CitizenControllerTest {

    private Player player1;
    private Player player2;
    private ArrayList<Player> players;
    private GameMap gameMap;

    @BeforeEach
    public void setUp(){
        player1 = new Player(new User("1", "1", "1"));
        player2 = new Player(new User("2", "2", "2"));
        players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        gameMap = new GameMap(players);
        player1.getCities().add(new City(gameMap.getTile(5,5),gameMap, "player1"));
        player2.getCities().add(new City(gameMap.getTile(10,10),gameMap, "player2"));
    }

    public void addSomeFeatureToTiles(GameMap gameMap){
        for(int i = 0; i< gameMap.getMap().length; i++)
            for (int j = 0; j < gameMap.getMap()[0].length;j++){
                gameMap.getMap()[i][j].setResource(new TileResource(TileResourceEnum.BANANA));
                gameMap.getMap()[i][j].setImprovement(new TileImprovement(TileImprovementEnum.FARM));

            }
    }

    @Test
    public void assignCitizenOfCityFoodTest(){
        addSomeFeatureToTiles(gameMap);
        CitizenController.assignCitizensOfCity(player1.getCities().get(0), "food");
        Assertions.assertEquals(1, player1.getCities().get(0).getUnderWorkTiles().size());
    }

    @Test
    public void assignCitizenOfPlayerGoldTest(){
        addSomeFeatureToTiles(gameMap);
        CitizenController.assignCitizensOfPlayer(player1, "gold");
        Assertions.assertEquals(1, player1.getCities().get(0).getUnderWorkTiles().size());
    }

    @Test
    public void assignCitizenOfCityProductionTest(){
        addSomeFeatureToTiles(gameMap);
        CitizenController.assignCitizensOfCity(player1.getCities().get(0), "production");
        Assertions.assertEquals(1, player1.getCities().get(0).getUnderWorkTiles().size());
    }

    @Test
    public void assignCitizenOfCityEconomyTest(){
        addSomeFeatureToTiles(gameMap);
        CitizenController.assignCitizensOfCity(player1.getCities().get(0), "economy");
        Assertions.assertEquals(1, player1.getCities().get(0).getUnderWorkTiles().size());
    }

    @Test
    public void removeCitizenFromTileTest(){
        City city = player1.getCities().get(0);
        city.getUnderWorkTiles().add(city.getCenter());
        CitizenController.removeCitizenFromATile(city, city.getCenter());
        Assertions.assertFalse(city.getUnderWorkTiles().contains(city.getCenter()));
    }

    @Test
    public void assignCitizenToTileTest(){
        City city = player1.getCities().get(0);
        CitizenController.assignCitizenToATile(city, city.getCenter());
        Assertions.assertTrue(city.getUnderWorkTiles().contains(city.getCenter()));
    }



}
