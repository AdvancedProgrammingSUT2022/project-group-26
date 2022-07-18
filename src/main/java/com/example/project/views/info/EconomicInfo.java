package com.example.project.views.info;

import com.example.project.controllers.GameControllers.GameMenuCommandController;
import com.example.project.controllers.Output;
import com.example.project.models.Building.Building;
import com.example.project.models.City;
import com.example.project.models.Food;
import com.example.project.models.Player;
import com.example.project.models.Units.Unit;
import com.example.project.models.UsersDatabase;
import com.example.project.views.Menu;

import java.util.ArrayList;
import java.util.regex.Matcher;

public class EconomicInfo extends Menu {
    private GameMenuCommandController gameMenuCommandController;
    private Player player;
    private ArrayList<Player> players;

    public EconomicInfo(UsersDatabase usersDatabase, GameMenuCommandController gameMenuCommandController, Player player, ArrayList<Player> players) {
        super(usersDatabase);
        this.gameMenuCommandController = gameMenuCommandController;
        this.player = player;
        this.players = players;
    }

    @Override
    public void run() {
        String input;
        Matcher matcher;
        while (true) {
            input = super.scanner.nextLine();
            if ((matcher = getCommandMatcher(input, EconomicInfoEnum.EXIT.toString())) != null) {
                return;
            } else if ((matcher = getCommandMatcher(input, EconomicInfoEnum.CITY_DATA.toString())) != null) {
                showCityData(matcher);
            } else {
                System.out.println("invalid command!");
            }
        }
    }

    public void showCityData(Matcher matcher) {
        Output output = gameMenuCommandController.isValidCity(matcher, player);
        if (output != null) {
            System.out.println(output);
            return;
        }
        City city = player.getCityByName(matcher.group("cityName"));
        System.out.println("city: " + city.getName());
        System.out.println("population: " + city.getMaxPopulation());
        System.out.println("HP: " + (int) city.getHealth());
        if (city.getBeingBuild() != null) {
            String toPrintTurnsLeft = "";
            if (city.getProduction() == 0) toPrintTurnsLeft = "no production!";
            else {
                int turnsLeft = (int) Math.ceil((double) city.getBeingBuild().getProductionCost() / (double) city.getProduction());
                if (turnsLeft <= 0) turnsLeft = 1;
                toPrintTurnsLeft = Integer.toString(turnsLeft);
            }
            String name = "";
            if (city.getBeingBuild().getGettingBuild() instanceof Building) {
                name = ((Building) city.getBeingBuild().getGettingBuild()).getName().getName();
            } else if (city.getBeingBuild().getGettingBuild() instanceof Unit) {
                name = ((Unit) city.getBeingBuild().getGettingBuild()).getUnitNameEnum().getName();
            }
            System.out.println("being build: " + name + " turns left: " + toPrintTurnsLeft);
        } else System.out.println("nothing is getting build!");
        System.out.println("saved food: " + Food.getCityFood(city) + " food production: " + city.getFoodProduction());
        System.out.println("production: " + city.getProduction());
        System.out.println("science: " + player.getScience());
        System.out.println("gold: " + player.getGold());
    }
}