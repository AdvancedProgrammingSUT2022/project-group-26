package models;

import java.util.HashMap;

public class Food {
    private static HashMap<City, Integer> citiesSavedFood = new HashMap<>();


    public static int getFoodProduction(City city) {
        return 0;
    }

    public static void addToSavedFood(City city, int food) {
    }

    public static void resetSavedFood(City city) {
    }

    public static void addCitizen(City city) {
    }

    public static void removeCitizen(City city) {
    }

    public static HashMap<City, Integer> getCitiesSavedFood() {
        return citiesSavedFood;
    }

    public static void setCitiesSavedFood(HashMap<City, Integer> citiesSavedFood) {
        Food.citiesSavedFood = citiesSavedFood;
    }
}
