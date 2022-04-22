package models;

import models.Tile.Tile;

import java.util.ArrayList;

public class River {
    private Tile firstTile;
    private Tile secondTile;

    public static ArrayList<River> rivers = new ArrayList<>();

    public River(Tile firstTile, Tile secondTile) {
        setFirstTile(firstTile);
        setSecondTile(secondTile);
        if (!hasRiver(firstTile, secondTile))
            rivers.add(this);
    }

    public Tile getFirstTile() {
        return firstTile;
    }

    public void setFirstTile(Tile firstTile) {
        this.firstTile = firstTile;
    }

    public Tile getSecondTile() {
        return secondTile;
    }

    public void setSecondTile(Tile secondTile) {
        this.secondTile = secondTile;
    }

    public static boolean hasRiver(Tile firstTile, Tile secondTile) {
        for (int i = 0; i < rivers.size(); i++) {
            if (rivers.get(i).getFirstTile() == firstTile && rivers.get(i).getSecondTile() == secondTile)
                return true;
            if (rivers.get(i).getFirstTile() == secondTile && rivers.get(i).getSecondTile() == firstTile)
                return true;
        }
        return false;
    }

    public static boolean hasRiver(Tile tile) {
        for (int i = 0; i < rivers.size(); i++) {
            if (rivers.get(i).getFirstTile() == tile || rivers.get(i).getSecondTile() == tile)
                return true;
        }
        return false;
    }
}
