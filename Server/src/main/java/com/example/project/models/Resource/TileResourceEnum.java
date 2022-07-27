package com.example.project.models.Resource;

import com.example.project.App;

import java.net.URL;
import java.util.HashMap;

public enum TileResourceEnum {

    BANANA("banana", 1, 0, 0, false),
    COW("cow", 1, 0, 0, false),
    GAZELLE("gazelle", 1, 0, 0, false),
    SHEEP("sheep", 1, 0, 0, false),
    WHEAT("wheat", 1, 0, 0, false),
    COAL("coal", 0, 1, 0, false),
    HORSE("horse", 0, 1, 0, false),
    IRON("iron", 0, 1, 0, false),
    COTTON("cotton", 0, 0, 2, true),
    FUR("fur", 0, 0, 2, true),

    GEM_STONES("gemStones", 0, 0, 2, true),

    PAINT("paint", 0, 0, 2, true),

    GOLD("gold", 0, 0, 3, true),

    INCENSE("incense", 0, 0, 2, true),

    TUSK("tusk", 0, 0, 2, true),

    MARBLE("marble", 0, 0, 2, true),

    SILK("silk", 0, 0, 2, true),

    SILVER("silver", 0, 0, 2, true),

    SUGAR("sugar", 0, 0, 2, true);

    private final String name;
    private final int food;
    private final int production;
    private final int gold;
    private final boolean luxury;

    TileResourceEnum(String name, int food, int production, int gold, boolean luxury) {
        this.name = name;
        this.food = food;
        this.production = production;
        this.gold = gold;
        this.luxury = luxury;
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

    public boolean getLuxury() {
        return luxury;
    }

    public static TileResourceEnum getEnumByString(String resource) {
        for (TileResourceEnum tileResourceEnum : TileResourceEnum.values())
            if (tileResourceEnum.getName().equals(resource))
                return tileResourceEnum;
        return null;
    }
}