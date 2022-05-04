package controllers.GameControllers;

import models.City;
import models.Player;
import models.Tile.Tile;

import java.util.ArrayList;
import java.util.Comparator;

public class CitizenController {
    public void assignCitizensOfPlayer(Player player, String mode) {
        for (City city : player.getCities()) {
            assignCitizensOfCity(city, mode);
        }
    }

    private void assignCitizensOfCity(City city, String mode) {
        ArrayList<Tile> sortedTiles = (ArrayList<Tile>) city.getTiles().clone();
        switch (mode) {
            case "food":
                sortedTiles.sort(Comparator.comparing(Tile::getFood));
                break;
            case "gold":
                sortedTiles.sort(Comparator.comparing(Tile::getGold));
                break;
            case "production":
                sortedTiles.sort(Comparator.comparing(Tile::getProduction));
                break;
            case "economy":
                sortedTiles.sort(Comparator.comparing(Tile::getEconomy));
                break;
            default:
                // wrong mode !!
        }
        ArrayList<Tile> save = city.getUnderWorkTiles();
        save.clear();
        for (int i = 0; i < city.getMaxPopulation(); i++) {
            save.add(sortedTiles.get(i));
        }
    }
}
