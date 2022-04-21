package models;

import java.util.HashMap;

public class Gold {
    private static HashMap<Player, Integer> playersSavedGold = new HashMap<>();

    // TODO : add constructor

    public static int getGoldProduction(Player player) {
        int sum = 0;
        for (City city : player.getCities()) {
            sum += city.getGoldProduction();
        }
        return sum;
    }

    public static int getGoldSaved(Player player) {
        return getPlayersSavedGold().get(player);
    }

    public static void addOneTurnOfGoldProduction(Player player) {
        addGold(player, getGoldProduction(player));
    }


    public static void addGold(Player player, int gold) {
        getPlayersSavedGold().put(player, getPlayersSavedGold().get(player) + gold);
    }

    public static void removeGold(Player player, int gold) {
        getPlayersSavedGold().put(player, getPlayersSavedGold().get(player) - gold);
    }

    public static HashMap<Player, Integer> getPlayersSavedGold() {
        return playersSavedGold;
    }

    public static void setPlayersSavedGold(HashMap<Player, Integer> playersSavedGold) {
        Gold.playersSavedGold = playersSavedGold;
    }
}