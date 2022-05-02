package models.Feature;

import com.google.gson.annotations.SerializedName;

public enum TileFeatureEnum {
    @SerializedName("0")
    PLAIN("plain", 2, 0, 0, -0.33, 1.0),
    @SerializedName("1")
    FOREST("forest", 1, 1, 0, 0.25, 2.0),
    @SerializedName("2")
    ICE("ice", 0, 0, 0, 0, Double.POSITIVE_INFINITY),
    @SerializedName("3")
    DENSE_FOREST("dense Forest", 1, -1, 0, 0.25, 2.0),
    @SerializedName("4")
    SWAMP("swamp", -1, 0, 0, -0.33, 2.0),
    @SerializedName("5")
    OASIS("oasis", 3, 0, 1, -0.33, 1.0);

    private final String name;
    private final int food;
    private final int production;
    private final int gold;
    private final double troopBoost;
    private final Double movementCost;

    TileFeatureEnum(String name, int food, int production, int gold, double troopBoost, Double movementCost) {
        this.name = name;
        this.food = food;
        this.production = production;
        this.gold = gold;
        this.troopBoost = troopBoost;
        this.movementCost = movementCost;
    }

    public String getName() {
        return name;
    }

    public int getFood() {
        return food;
    }

    public int getProduction() {
        return production;
    }

    public int getGold() {
        return gold;
    }

    public double getTroopBoost() {
        return troopBoost;
    }

    public Double getMovementCost() {
        return movementCost;
    }

}