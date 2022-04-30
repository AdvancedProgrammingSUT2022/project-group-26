package models;

import models.Tile.Tile;

import java.util.HashMap;

public class Food {
    private static HashMap<City, Integer> citiesSavedFood = new HashMap<>();

    Food(City city) {
        getCitiesSavedFood().put(city, 0);
    }

    public static void handleFoodOFCity(City city) {
        addToSavedFood(city, Food.getFoodProduction(city));
        // TODO : whats the rules for adding ppl ? -- needs debugging
        if (getCitiesSavedFood().get(city) > 10) {
            addCitizen(city);
            resetSavedFood(city);
        }
        // TODO :  whats the rules for removing ppl ?
        if (getCitiesSavedFood().get(city) < -5) {
            removeCitizen(city);
        }
    }

    public static int getFoodProduction(City city) {
        int sum = 0;
        for (Tile tile : city.getUnderWorkTiles()) {
            sum += tile.getFood();
        }
        return sum;
    }

    public static void addToSavedFood(City city, int food) {
        getCitiesSavedFood().put(city, getCitiesSavedFood().get(city) + food);
    }

    public static void resetSavedFood(City city) {
        getCitiesSavedFood().put(city, 0);
    }

    public static void addCitizen(City city) {
        city.addOneToMaxPopulation();
    }

    public static void removeCitizen(City city) {
        city.removeOneToMaxPopulation();
    }

    public static HashMap<City, Integer> getCitiesSavedFood() {
        return citiesSavedFood;
    }

    public static void setCitiesSavedFood(HashMap<City, Integer> citiesSavedFood) {
        Food.citiesSavedFood = citiesSavedFood;
    }
}
