package com.example.project.models.Improvement;

import com.google.gson.annotations.SerializedName;

public enum TileImprovementEnum {
    CAMP("camp", 0, 0, 0),
    FARM("farm", 1, 0, 0),
    LUMBER_MILL("lumberMill", 0, 1, 0),
    MINE("mine", 0, 1, 0),
    PASTURE("pasture", 0, 0, 0),
    FARMING("farming", 0, 0, 0),
    STONE_MINE("stoneMine", 0, 0, 0),
    TRADING_POST("Trading Post", 0, 0, 1),
    FACTORY("factory", 0, 2, 0);


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

    public static TileImprovementEnum valueOfLabel(String label) {
        for (TileImprovementEnum e : values()) {
            if (e.name.equals(label)) {
                return e;
            }
        }
        return null;
    }
}