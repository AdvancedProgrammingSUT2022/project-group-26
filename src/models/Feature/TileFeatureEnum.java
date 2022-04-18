package models.Feature;

public enum TileFeatureEnum {
    plain("plain", 2, 0, 0, -0.33, 1.0),
    forest("forest", 1, 1, 0, 0.25, 2.0),
    ice("ice", 0, 0, 0, 0, Double.POSITIVE_INFINITY),
    denceForest("denceForest", 1, -1, 0, 0.25, 2.0),
    swamp("swamp", -1, 0, 0, -0.33, 2.0),
    oasis("oasis", 3, 0, 1, -0.33, 1.0),
    river("river", 0, 0, 1, 0, Double.NEGATIVE_INFINITY);

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