package com.example.project.models.Tile;

import com.example.project.App;
import com.example.project.models.Feature.TileFeatureEnum;
import com.example.project.models.Resource.TileResourceEnum;
import com.google.gson.annotations.SerializedName;
import javafx.scene.image.Image;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class TileMode {
    private TileModeEnum tileName;
    private Double MovementCost;
    private int food;
    private int production;
    private int gold;
    private double troopBoost;
    //////////////////////

    /////////////////////
    private ArrayList<TileFeatureEnum> possibleFeature;
    private ArrayList<TileResourceEnum> possibleResources;

    public TileMode(TileModeEnum tileName) {
        setTileName(tileName);
        setMovementCost(tileName.getMovementCost());
        setFood(tileName.getFood());
        setProduction(tileName.getProduction());
        setGold(tileName.getGold());
        setTroopBoost(tileName.getTroopBoost());
    }

    public TileMode(TileMode tileMode) {
        setTileName(tileMode.getTileName());
        setMovementCost(tileMode.getMovementCost());
        setFood(tileMode.getFood());
        setProduction(tileMode.getProduction());
        setGold(tileMode.getGold());
        setTroopBoost(tileMode.getTroopBoost());
    }

    public TileMode clone() {
        return new TileMode(this);
    }

    public ArrayList<TileFeatureEnum> getPossibleFeature() {
        return possibleFeature;
    }

    public void setPossibleFeature(ArrayList<TileFeatureEnum> possibleFeature) {
        this.possibleFeature = possibleFeature;
    }

    public ArrayList<TileResourceEnum> getPossibleResources() {
        return possibleResources;
    }

    public void setPossibleResources(ArrayList<TileResourceEnum> possibleResources) {
        this.possibleResources = possibleResources;
    }

    public TileModeEnum getTileName() {
        return tileName;
    }

    public void setTileName(TileModeEnum tileName) {
        this.tileName = tileName;
    }

    public int getFood() {
        return food;
    }

    public void setFood(int food) {
        this.food = food;
    }

    public int getProduction() {
        return production;
    }

    public void setProduction(int production) {
        this.production = production;
    }

    public int getGold() {
        return gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public Double getMovementCost() {
        return MovementCost;
    }

    public void setMovementCost(Double movementCost) {
        MovementCost = movementCost;
    }

    public double getTroopBoost() {
        return troopBoost;
    }

    public void setTroopBoost(double troopBoost) {
        this.troopBoost = troopBoost;
    }
}