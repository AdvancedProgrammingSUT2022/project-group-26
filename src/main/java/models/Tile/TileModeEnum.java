package models.Tile;

import com.google.gson.annotations.SerializedName;

public enum TileModeEnum {
    @SerializedName("0")
    DESERT("desert", 0, 0, 0, -0.33, 1.0),
    @SerializedName("1")
    GRASSLAND("grassland", 2, 0, 0, -0.33, 1.0),
    @SerializedName("2")
    HILL("hill", 0, 2, 0, 0.25, 2.0),
    @SerializedName("3")
    MOUNTAIN("mountain", 0, 0, 0, 0, Double.POSITIVE_INFINITY),
    @SerializedName("4")
    OCEAN("ocean", 0, 0, 0, 0, Double.POSITIVE_INFINITY),
    @SerializedName("5")
    PLAIN("plain", 1, 1, 0, -0.33, 1.0),
    @SerializedName("6")
    SNOW("snow", 0, 0, 0, -0.33, 1.0),
    @SerializedName("7")
    TUNDRA("tundra", 1, 0, 0, -0.33, 1.0);

    private final String name;
    private final int food;
    private final int production;
    private final int gold;
    private final double troopBoost;
    private final Double movementCost;


    TileModeEnum(String name, int food, int production, int gold, double troopBoost, Double movementCost) {
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