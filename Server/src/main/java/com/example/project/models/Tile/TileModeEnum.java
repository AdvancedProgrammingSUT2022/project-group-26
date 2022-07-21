package com.example.project.models.Tile;

import com.example.project.App;
import com.example.project.models.Feature.TileFeatureEnum;
import com.google.gson.annotations.SerializedName;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

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

    public static TileModeEnum getEnumByString(String mode) {
        for (TileModeEnum tileModeEnum : TileModeEnum.values())
            if (tileModeEnum.getName().equals(mode))
                return tileModeEnum;
        return null;
    }

    public static ArrayList<TileFeatureEnum> getValidFeatures(TileModeEnum mode) {
        if (mode == TileModeEnum.DESERT)
            return new ArrayList<>(Arrays.asList(TileFeatureEnum.PLAIN, TileFeatureEnum.OASIS));
        if (mode == TileModeEnum.GRASSLAND)
            return new ArrayList<>(Arrays.asList(TileFeatureEnum.FOREST, TileFeatureEnum.SWAMP));
        if (mode == TileModeEnum.HILL)
            return new ArrayList<>(Arrays.asList(TileFeatureEnum.FOREST, TileFeatureEnum.DENSE_FOREST));
        if (mode == TileModeEnum.PLAIN)
            return new ArrayList<>(Arrays.asList(TileFeatureEnum.FOREST, TileFeatureEnum.DENSE_FOREST));
        if (mode == TileModeEnum.TUNDRA)
            return new ArrayList<>(Arrays.asList(TileFeatureEnum.FOREST));
        return new ArrayList<>();
    }

}