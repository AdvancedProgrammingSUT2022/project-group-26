package models;

import java.util.HashMap;

public class Happiness {
    private static HashMap<Player, Integer> playersHappiness = new HashMap<>();

    public Happiness(Player player){
        playersHappiness.put(player, 0);
    }

    public static int getHappiness(Player player) {
        return playersHappiness.get(player);
    }

    public static void setHappiness(Player player, int happiness) {
        playersHappiness.put(player, happiness);
    }

    public static HashMap<Player, Integer> getPlayersHappiness() {
        return playersHappiness;
    }

    public static void setPlayersHappiness(HashMap<Player, Integer> playersHappiness) {
        playersHappiness = playersHappiness;
    }

    public static void addPlayerHappiness(Player player, int happiness) {
        playersHappiness.put(player, getHappiness(player) + happiness);
    }

    public static void removePlayerHappiness(Player player, int happiness) {
        playersHappiness.put(player, getHappiness(player) - happiness);
    }
}
