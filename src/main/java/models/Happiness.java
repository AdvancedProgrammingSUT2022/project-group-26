package models;

import java.util.HashMap;

public class Happiness {
    private static HashMap<Player, Integer> PlayersHappiness = new HashMap<>();

    //TODO : add happiness methods
    // TODO : add constructor

    public static int getHappiness(Player player) {
        return 0;
    }

    public static void setHappiness(Player player) {
    }

    public static void editHappiness(Player player, int happiness) {
    }

    public static HashMap<Player, Integer> getPlayersHappiness() {
        return PlayersHappiness;
    }

    public static void setPlayersHappiness(HashMap<Player, Integer> playersHappiness) {
        PlayersHappiness = playersHappiness;
    }
}
