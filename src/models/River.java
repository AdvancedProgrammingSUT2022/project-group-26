package models;

import jdk.dynalink.beans.StaticClass;
import models.Tile.Tile;

import java.util.ArrayList;

public class River {
    private Tile firstTile;
    private Tile secondTile;
    static public ArrayList<River> rivers = new ArrayList<>();

    public River(Tile firstTile, Tile secondTile) {
        setFirstTile(firstTile);
        setSecondTile(secondTile);
        if (!rivers.contains(this))
            rivers.add(this);
    }

    static public void clear() {
        rivers.clear();
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
}
