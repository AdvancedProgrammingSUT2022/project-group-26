package com.example.project.models;

import java.util.HashMap;

public class Happiness {
    private static HashMap<Player, Integer> playersHappiness = new HashMap<>();

    public Happiness(Player player) {
        playersHappiness.put(player, 10);
    }

    public static int getPlayerHappiness(Player player) {
        if (playersHappiness.get(player)==null) return 0;
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
        playersHappiness.put(player, getPlayerHappiness(player) + happiness);
    }

    public static void removePlayerHappiness(Player player, int happiness) {
        playersHappiness.put(player, getPlayerHappiness(player) - happiness);
    }

    public static void removePlayerHappiness(City city, int happiness) {
        for (Player player : getPlayersHappiness().keySet()) {
            if (player.getCities().contains(city)) playersHappiness.put(player, getPlayerHappiness(player) - happiness);
        }
    }

}
