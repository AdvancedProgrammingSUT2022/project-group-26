package views.info;

import controllers.GameControllers.GameMenuCommandController;
import controllers.Output;
import models.*;
import views.Menu;

import java.util.ArrayList;
import java.util.regex.Matcher;

public class CityInfo extends Menu {
    GameMenuCommandController gameMenuCommandController;
    Player player;
    ArrayList<Player> players;

    public CityInfo(UsersDatabase usersDatabase, GameMenuCommandController gameMenuCommandController, Player player, ArrayList<Player> players) {
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
            input = scanner.nextLine();
            if ((matcher = getCommandMatcher(input, CityInfoEnum.SHOW_CITY_BANNER.toString())) != null) {
                showCityBanner(player, matcher);
            } else if ((matcher = getCommandMatcher(input, CityInfoEnum.SHOW_CITY_FOOD.toString())) != null) {
                showCityFood(player, matcher);
            } else if ((matcher = getCommandMatcher(input, CityInfoEnum.EXIT.toString())) != null) {
                return;
            } else if ((matcher = getCommandMatcher(input, CityInfoEnum.SHOW_CITIES.toString())) != null) {
                showAllCities();
            } else {
                System.out.println("invalid command!");
            }
        }
    }


    public void showCityBanner(Player player, Matcher matcher) {
        Output output = gameMenuCommandController.isValidCity(matcher, player);
        if (output != null) System.out.println(output);
        else {
            City city = player.getCityByName(matcher.group("cityName"));
            System.out.println("name: " + city.getName() + " HP: " + city.getHealth());
        }
    }

    private void showCityFood(Player player, Matcher matcher) {
        Output output = gameMenuCommandController.isValidCity(matcher, player);
        if (output != null) {
            System.out.println(output);
            return;
        }
        City city = player.getCityByName(matcher.group("cityName"));
        System.out.println("city: " + city.getName() + " food: " + Food.getCityFood(city));
    }

    public void showAllCities(){
        for(int i = 0; i < player.getCities().size(); i++){
            System.out.println((i + 1) + "- " + player.getCities().get(i).getName());
        }
    }
}
