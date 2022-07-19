package com.example.project.controllers.GameControllers;

import com.example.project.controllers.Output;
import com.example.project.models.City;
import com.example.project.models.Player;
import com.example.project.models.Tile.Tile;

import java.util.ArrayList;
import java.util.Comparator;

public class CitizenController {
    public static void assignCitizensOfPlayer(Player player, String mode) {
        for (City city : player.getCities()) {
            assignCitizensOfCity(city, mode);
        }
    }

    public static void assignCitizensOfCity(City city, String mode) {
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
           /* default:
                // wrong mode !!*/
        }
        ArrayList<Tile> save = city.getUnderWorkTiles();
        save.clear();
        for (int i = 0; i < Math.min(city.getMaxPopulation(),sortedTiles.size()); i++) {
            save.add(sortedTiles.get(i));
        }
    }

    public static Output removeCitizenFromATile(City city, Tile tile) {
        if (!city.getTiles().contains(tile)) return Output.NOT_YOUR_TERRITORY;
        if (!city.getUnderWorkTiles().contains(tile)) return Output.TILE_IS_FREE;
        city.getUnderWorkTiles().remove(tile);
        return Output.TILE_FREED_SUCCESSFULLY;
    }

    public static Output assignCitizenToATile(City city, Tile tile) {
        if (!city.getTiles().contains(tile)) return Output.NOT_YOUR_TERRITORY;
        if (city.getUnderWorkTiles().contains(tile)) return Output.TWO_CITIZEN_ON_A_TILE;
        if (city.getMaxPopulation() <= city.getUnderWorkTiles().size()) return Output.NO_FREE_CITIZEN;
        city.getUnderWorkTiles().add(tile);
        return Output.CITIZEN_ASSIGNED_SUCCESSFULLY;
    }
}