package com.example.project.models.Feature;

import com.example.project.models.Resource.TileResourceEnum;

import java.util.ArrayList;

public class TileFeature {
    private TileFeatureEnum featureName;
    private int food;
    private int production;
    private int gold;
    private double troopBoost;
    private Double movementCost;
    private ArrayList<TileResourceEnum> resources;

    public TileFeature(TileFeatureEnum featureName) {
        setFeatureName(featureName);
        setMovementCost(featureName.getMovementCost());
        setFood(featureName.getFood());
        setProduction(featureName.getProduction());
        setGold(featureName.getGold());
        setTroopBoost(featureName.getTroopBoost());
    }

    public TileFeature(TileFeature tileFeature) {
        setFeatureName(tileFeature.getFeatureName());
        setMovementCost(tileFeature.getMovementCost());
        setFood(tileFeature.getFood());
        setProduction(tileFeature.getProduction());
        setGold(tileFeature.getGold());
        setTroopBoost(tileFeature.getTroopBoost());
    }

    public TileFeature clone(){
        return new TileFeature(this);
    }

    public TileFeatureEnum getFeatureName() {
        return featureName;
    }

    public void setFeatureName(TileFeatureEnum featureName) {
        this.featureName = featureName;
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
        return movementCost;
    }

    public void setMovementCost(Double movementCost) {
        this.movementCost = movementCost;
    }

    public ArrayList<TileResourceEnum> getResources() {
        return resources;
    }

    public void setResources(ArrayList<TileResourceEnum> resources) {
        this.resources = resources;
    }

    public double getTroopBoost() {
        return troopBoost;
    }

    public void setTroopBoost(double troopBoost) {
        this.troopBoost = troopBoost;
    }
}
