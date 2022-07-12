package com.example.project.models.Feature;

import com.example.project.App;
import com.example.project.models.Tile.TileModeEnum;
import com.google.gson.annotations.SerializedName;
import javafx.scene.image.Image;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

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

    public static TileFeatureEnum getEnumByString(String feature) {
        for (TileFeatureEnum tileFeatureEnum : TileFeatureEnum.values())
            if (tileFeatureEnum.getName().equals(feature))
                return tileFeatureEnum;
        return null;
    }

    private static final HashMap<TileFeatureEnum, Image> images = new HashMap<>();

    static {
        try {
            for (TileFeatureEnum featureEnum : TileFeatureEnum.values())
                images.put(featureEnum,
                        new Image(String.valueOf(new URL(App.class.getResource("/Image/Game/Tile/feature/" + featureEnum.getName() + ".png").toString()))));;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public static HashMap<TileFeatureEnum, Image> getImages() {
        return images;
    }
}