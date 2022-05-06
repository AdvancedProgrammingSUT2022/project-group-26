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
        sortStrategicResources(numOfResources, allPlayers);
        for (int i = 0; i < allPlayers.size(); i++) {
            if (allPlayers.get(i) == this.player)
                System.out.print(ANSI_RED);
            System.out.println((i + 1) + "- name: " + allPlayers.get(i).getUser().getUsername() +
                    " strategic recourse number: " + numOfResources.get(i));
            System.out.print(ANSI_RESET);
        }
    }

    private void sortStrategicResources(ArrayList<Integer> numOfResources, ArrayList<Player> allPlayers) {
        for (int i = 0; i < allPlayers.size(); i++)
            for (int j = i + 1; j < allPlayers.size(); j++)
                if (numOfResources.get(i) < numOfResources.get(j)) {
                    Integer integerTemp = numOfResources.get(i);
                    numOfResources.set(i, numOfResources.get(j));
                    numOfResources.set(j, integerTemp);

                    Player playerTemp = allPlayers.get(i);
                    allPlayers.set(i, allPlayers.get(j));
                    allPlayers.set(j, playerTemp);
                }
    }


    private void showScience() {
    }

    private void showMilitary() {
    }

    private void showHappiness() {
    }

    private void showNumOfTiles() {
    }

    private void showPopulation() {
    }

    private void showGold() {
    }
}