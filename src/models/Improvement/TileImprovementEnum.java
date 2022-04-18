package models.Improvement;

public enum TileImprovementEnum {
    camp("camp", 0, 0, 0),
    farm("farm", 1, 0, 0),
    lumberMill("lumberMill", 0, 1, 0),
    mine("mine", 0, 1, 0),
    pasture("pasture", 0, 0, 0),
    farming("farming", 0, 0, 0),
    stoneMine("stoneMine", 0, 0, 0),
    tradingPost("Trading Post", 0, 0, 1),
    factory("factory", 0, 2, 0);


    private final String name;
    private final int food;
    private final int production;
    private final int gold;

    TileImprovementEnum(String name, int food, int production, int gold) {
        this.name = name;
        this.food = food;
        this.production = production;
        this.gold = gold;
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
}