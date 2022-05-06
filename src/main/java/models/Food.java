package models;

import models.Tile.Tile;
import models.Units.Unit;
import models.Units.UnitNameEnum;

import java.util.HashMap;

public class Food {
    private static HashMap<City, Integer> citiesSavedFood = new HashMap<>();

    public Food(City city) {
        getCitiesSavedFood().put(city, 0);
    }

    public static void handleFoodOFCity(City city) {
        editSavedFood(city, city.getMaxPopulation() * -2);
        editSavedFood(city, Food.getFoodProduction(city));

        if (getCityFood(city) > Math.pow(2, city.getMaxPopulation())) {

            addCitizen(city);
            resetSavedFood(city);
        }

        if (getCityFood(city) < -4) { /// TODO : اینجا یه عدد میخواد ک فعلا نمیدونم کمه یا زیاد
            removeCitizen(city);
            resetSavedFood(city);
        }
        if (city.getBeingBuild() != null) {
            Unit unit = (Unit) city.getBeingBuild().getGettingBuild();
            if (unit.getUnitNameEnum() == UnitNameEnum.SETTLER)
                setCityFood(city, 0);
        }
    }

    public static int getFoodProduction(City city) {
        int sum = 0;
        for (Tile tile : city.getUnderWorkTiles()) {
            if (tile == null) continue;
            sum += tile.getFood();
        }
        return sum;
    }

    public static void editSavedFood(City city, int food) {
        getCitiesSavedFood().put(city, getCitiesSavedFood().get(city) + food);
    }

    public static void resetSavedFood(City city) {
        getCitiesSavedFood().put(city, 0);
    }

    public static void addCitizen(City city) {
        city.addOneToMaxPopulation();
    }

    public static void removeCitizen(City city) {
        city.removeOneFromMaxPopulation();
    }

    public static HashMap<City, Integer> getCitiesSavedFood() {
        return citiesSavedFood;
    }

    public static void setCitiesSavedFood(HashMap<City, Integer> citiesSavedFood) {
        Food.citiesSavedFood = citiesSavedFood;
    }

    public static void setCityFood(City city, int food) {
        citiesSavedFood.put(city, food);
    }

    public static int getCityFood(City city) {
        return citiesSavedFood.get(city);
    }
}