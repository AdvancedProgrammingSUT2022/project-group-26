package models;

import models.Feature.TileFeature;
import models.Improvement.TileImprovement;
import models.Resource.TileResource;

public class Tile {
    private TileMode mode;
    private TileResource resource;
    private TileFeature feature;
    private TileImprovement improvement;

    public Tile() {
    }

    public int getMp() {
        return 0;
    }

    public int getGold() {
        return 0;
    }

    public int getFood() {
        return 0;
    }

    public float getCombatImpact() {
        return 0;
    }


}
