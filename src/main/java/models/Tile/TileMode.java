package models.Tile;

import models.Resource.TileResourceEnum;
import models.Feature.TileFeatureEnum;

import java.util.ArrayList;

public class TileMode {
    private TileModeEnum TileName;
    private Double MovementCost;
    private int food;
    private int production;
    private int gold;
    private double troopBoost;
    private ArrayList<TileFeatureEnum> possibleFeature;
    private ArrayList<TileResourceEnum> possibleResources;

    public TileMode(TileModeEnum tileName) {
        setTileName(tileName);
        setMovementCost(tileName.getMovementCost());
        setFood(tileName.getFood());
        setProduction(tileName.getProduction());
        setGold(tileName.getGold());
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
        return TileName;
    }

    public void setTileName(TileModeEnum tileName) {
        TileName = tileName;
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