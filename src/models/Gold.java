package models;

import java.util.HashMap;

public class Gold {
    private static HashMap<Player, Integer> playersSavedGold = new HashMap<>();

    public static int getGoldProduction(Player player) {
        return 0;
    }

    public static void addGold(Player player, int gold) {
    }

    public static void removeGold(Player player, int gold) {
    }

    public static HashMap<Player, Integer> getPlayersSavedGold() {
        return playersSavedGold;
    }

    public static void setPlayersSavedGold(HashMap<Player, Integer> playersSavedGold) {
        Gold.playersSavedGold = playersSavedGold;
    }
}