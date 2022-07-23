package com.example.project.controllers.GameControllers;

import com.example.project.models.City;
import com.example.project.models.Food;
import com.example.project.models.Player;
import com.example.project.models.Tile.Tile;

import java.util.ArrayList;

public class SearchController {
    public static City findCity(ArrayList<Player> players, String cityName) {
        for (Player player : players) {
            if (player.getCityByName(cityName) != null) return player.getCityByName(cityName);
        }
        return null;
    }

    public static Player findPlayerOfCity(ArrayList<Player> players, City city) {
        for (Player player : players) {
            if (player.getCities().contains(city)) return player;
        }
        return null;
    }

    public static City searchCityWithCenter(Tile position) {
        // kinda a bad way to get all cities but works for now
        for (City city : Food.getCitiesSavedFood().keySet()) {
            if (city.getCenter() == position) return city;
        }
        return null;
    }
}
