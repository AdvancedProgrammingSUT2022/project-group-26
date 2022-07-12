package com.example.project.models.Resource;

import com.example.project.App;
import com.google.gson.annotations.SerializedName;
import javafx.scene.image.Image;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public enum TileResourceEnum {

    @SerializedName("0")
    BANANA("banana", 1, 0, 0, false),
    @SerializedName("1")
    COW("cow", 1, 0, 0, false),
    @SerializedName("2")
    GAZELLE("gazelle", 1, 0, 0, false),
    @SerializedName("3")
    SHEEP("sheep", 1, 0, 0, false),
    @SerializedName("4")
    WHEAT("wheat", 1, 0, 0, false),
    @SerializedName("5")
    COAL("coal", 0, 1, 0, false),
    @SerializedName("6")
    HORSE("horse", 0, 1, 0, false),
    @SerializedName("7")
    IRON("iron", 0, 1, 0, false),
    @SerializedName("8")
    COTTON("cotton", 0, 0, 2, true),
    @SerializedName("9")
    FUR("fur", 0, 0, 2, true),
    @SerializedName("10")
    GEM_STONES("gemStones", 0, 0, 2, true),
    @SerializedName("11")
    PAINT("paint", 0, 0, 2, true),
    @SerializedName("12")
    GOLD("gold", 0, 0, 3, true),
    @SerializedName("13")
    INCENSE("incense", 0, 0, 2, true),
    @SerializedName("14")
    TUSK("tusk", 0, 0, 2, true),
    @SerializedName("15")
    MARBLE("marble", 0, 0, 2, true),
    @SerializedName("16")
    SILK("silk", 0, 0, 2, true),
    @SerializedName("17")
    SILVER("silver", 0, 0, 2, true),
    @SerializedName("18")
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

    private static final HashMap<TileResourceEnum, Image> images = new HashMap<>();

    static {
        try {
            for (TileResourceEnum resourceEnum : TileResourceEnum.values())
                images.put(resourceEnum, new Image(String.valueOf(new URL(App.class.getResource
                                        ("/Image/Game/Tile/resource/" + resourceEnum.getName() + ".png").toString()))));

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public static HashMap<TileResourceEnum, Image> getImages() {
        return images;
    }
}