package com.example.project.models;

import java.util.HashMap;

public class Gold {
    private static HashMap<Player, Integer> playersSavedGold = new HashMap<>();

    public Gold(Player player) {
        playersSavedGold.put(player, 0);
    }

    public static int getGoldProduction(Player player) {
        int sum = 0;
        for (City city : player.getCities()) {
            sum += city.getGoldProduction();
        }
        return sum;
    }

    public static int getPlayerGold(Player player) {
        if (getPlayersSavedGold().get(player) == null) return 0;
        return getPlayersSavedGold().get(player);
    }

    public static void addOneTurnOfGoldProduction(Player player) {
        addGold(player, getGoldProduction(player));
    }


    public static void addGold(Player player, int gold) {
        getPlayersSavedGold().put(player,getPlayerGold(player) + gold);
    }

    public static void removeGold(Player player, int gold) {
        getPlayersSavedGold().put(player, getPlayerGold(player) - gold);
    }

    public static HashMap<Player, Integer> getPlayersSavedGold() {
        return playersSavedGold;
    }

    public static void setPlayersSavedGold(HashMap<Player, Integer> playersSavedGold) {
        Gold.playersSavedGold = playersSavedGold;
    }

    public static void setPlayerGold(Player player, int gold) {
        playersSavedGold.put(player, gold);
    }

    public static void maintainBuilding(Player player) {

    }
}