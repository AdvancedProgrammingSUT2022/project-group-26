package views.info;

import controllers.GameControllers.GameMenuCommandController;
import models.GameMap;
import models.Player;
import models.Resource.TileResource;
import models.UsersDatabase;
import views.Menu;
import views.PlayGameCommandsRegex;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.regex.Matcher;

public class DemographicInfo extends Menu {
    private Player player;
    private GameMenuCommandController gameMenuCommandController;
    private GameMap gamemap;
    private ArrayList<Player> players;
    private final String ANSI_RESET = "\u001B[0m";
    private final String ANSI_BLACK = "\u001B[30m";
    private final String ANSI_RED = "\u001B[31m";

    public DemographicInfo(UsersDatabase usersDatabase, Player player, GameMenuCommandController gameMenuCommandController,
                           ArrayList<Player> players, GameMap gamemap) {
        super(usersDatabase);
        this.player = player;
        this.gameMenuCommandController = gameMenuCommandController;
        this.players = players;
        this.gamemap = gamemap;
    }

    @Override
    public void run() {
        String input;
        Matcher matcher;
        while (true) {
            input = scanner.nextLine();
            if ((matcher = getCommandMatcher(input, DemographicInfoEnum.EXIT.toString())) != null) {
                return;
            } else if ((matcher = getCommandMatcher(input, DemographicInfoEnum.SHOW_GOLD.toString())) != null) {
                showGold();
            } else if ((matcher = getCommandMatcher(input, DemographicInfoEnum.SHOW_POPULATION.toString())) != null) {
                showPopulation();
            } else if ((matcher = getCommandMatcher(input, DemographicInfoEnum.SHOW_NUM_OF_TILES.toString())) != null) {
                showNumOfTiles();
            } else if ((matcher = getCommandMatcher(input, DemographicInfoEnum.SHOW_HAPPINESS.toString())) != null) {
                showHappiness();
            } else if ((matcher = getCommandMatcher(input, DemographicInfoEnum.SHOW_MILITARY.toString())) != null) {
                showMilitary();
            } else if ((matcher = getCommandMatcher(input, DemographicInfoEnum.SHOW_SCIENCE.toString())) != null) {
                showScience();
            } else if ((matcher = getCommandMatcher(input, DemographicInfoEnum.SHOW_STRATEGIC.toString())) != null) {
                showStrategic();
            } else {
                System.out.println("invalid command!");
            }
        }
    }

    private void showStrategic() {
        ArrayList<Integer> numOfResources = new ArrayList<>();
        ArrayList<Player> allPlayers = new ArrayList<>(players);
        for (Player player : allPlayers)
            numOfResources.add(player.getStrategicResources().size());
        sortArraylist(numOfResources, allPlayers);
        for (int i = 0; i < allPlayers.size(); i++) {
            if (allPlayers.get(i) == this.player)
                System.out.print(ANSI_RED);
            System.out.println((i + 1) + "- name: " + allPlayers.get(i).getUser().getUsername() +
                    " strategic recourse number: " + numOfResources.get(i));
            System.out.print(ANSI_RESET);
        }
    }

    private void showScience() {
        ArrayList<Integer> numOfScience = new ArrayList<>();
        ArrayList<Player> allPlayers = new ArrayList<>(players);
        for (Player player : allPlayers)
            numOfScience.add(player.getScience());
        sortArraylist(numOfScience, allPlayers);
        for (int i = 0; i < allPlayers.size(); i++) {
            if (allPlayers.get(i) == this.player)
                System.out.print(ANSI_RED);
            System.out.println((i + 1) + "- name: " + allPlayers.get(i).getUser().getUsername() +
                    " science: " + numOfScience.get(i));
            System.out.print(ANSI_RESET);
        }
    }


    private void showMilitary() {
        ArrayList<Integer> numOfMilitary = new ArrayList<>();
        ArrayList<Player> allPlayers = new ArrayList<>(players);
        for (Player player : allPlayers)
            numOfMilitary.add(player.getCombatUnits().size());
        sortArraylist(numOfMilitary, allPlayers);
        for (int i = 0; i < allPlayers.size(); i++) {
            if (allPlayers.get(i) == this.player)
                System.out.print(ANSI_RED);
            System.out.println((i + 1) + "- name: " + allPlayers.get(i).getUser().getUsername() +
                    " combat unit number: " + numOfMilitary.get(i));
            System.out.print(ANSI_RESET);
        }
    }

    private void showHappiness() {
        ArrayList<Integer> numOfHappiness = new ArrayList<>();
        ArrayList<Player> allPlayers = new ArrayList<>(players);
        for (Player player : allPlayers)
            numOfHappiness.add(player.getHappiness());
        sortArraylist(numOfHappiness, allPlayers);
        for (int i = 0; i < allPlayers.size(); i++) {
            if (allPlayers.get(i) == this.player)
                System.out.print(ANSI_RED);
            System.out.println((i + 1) + "- name: " + allPlayers.get(i).getUser().getUsername() +
                    " happiness: " + numOfHappiness.get(i));
            System.out.print(ANSI_RESET);
        }
    }

    private void showNumOfTiles() {
        ArrayList<Integer> numOfTiles = new ArrayList<>();
        ArrayList<Player> allPlayers = new ArrayList<>(players);
        for (Player player : allPlayers)
            numOfTiles.add(player.getTiles().size());
        sortArraylist(numOfTiles, allPlayers);
        for (int i = 0; i < allPlayers.size(); i++) {
            if (allPlayers.get(i) == this.player)
                System.out.print(ANSI_RED);
            System.out.println((i + 1) + "- name: " + allPlayers.get(i).getUser().getUsername() +
                    " num of tiles: " + numOfTiles.get(i));
            System.out.print(ANSI_RESET);
        }
    }

    private void showPopulation() {
        ArrayList<Integer> population = new ArrayList<>();
        ArrayList<Player> allPlayers = new ArrayList<>(players);
        for (Player player : allPlayers)
            population.add(player.getTotalPopulation());
        sortArraylist(population, allPlayers);
        for (int i = 0; i < allPlayers.size(); i++) {
            if (allPlayers.get(i) == this.player)
                System.out.print(ANSI_RED);
            System.out.println((i + 1) + "- name: " + allPlayers.get(i).getUser().getUsername() +
                    " population: " + population.get(i));
            System.out.print(ANSI_RESET);
        }
    }

    private void showGold() {
        ArrayList<Integer> gold = new ArrayList<>();
        ArrayList<Player> allPlayers = new ArrayList<>(players);
        for (Player player : allPlayers)
            gold.add(player.getGold());
        sortArraylist(gold, allPlayers);
        for (int i = 0; i < allPlayers.size(); i++) {
            if (allPlayers.get(i) == this.player)
                System.out.print(ANSI_RED);
            System.out.println((i + 1) + "- name: " + allPlayers.get(i).getUser().getUsername() +
                    " gold: " + gold.get(i));
            System.out.print(ANSI_RESET);
        }
    }


    private void sortArraylist(ArrayList<Integer> numbers, ArrayList<Player> allPlayers) {
        for (int i = 0; i < allPlayers.size(); i++)
            for (int j = i + 1; j < allPlayers.size(); j++)
                if (numbers.get(i) < numbers.get(j)) {
                    Integer integerTemp = numbers.get(i);
                    numbers.set(i, numbers.get(j));
                    numbers.set(j, integerTemp);

                    Player playerTemp = allPlayers.get(i);
                    allPlayers.set(i, allPlayers.get(j));
                    allPlayers.set(j, playerTemp);
                }
    }
}