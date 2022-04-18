package models.Feature;

import models.Resource.TileResourceEnum;

import java.util.ArrayList;

public class TileFeature {
    private TileFeatureEnum featureName;
    private int food;
    private int production;
    private int gold;
    private float combatBonus;
    private int movementCost;
    private ArrayList<TileResourceEnum> resources;

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

    public float getCombatBonus() {
        return combatBonus;
    }

    public void setCombatBonus(float combatBonus) {
        this.combatBonus = combatBonus;
    }

    public int getMovementCost() {
        return movementCost;
    }

    public void setMovementCost(int movementCost) {
        this.movementCost = movementCost;
    }

    public ArrayList<TileResourceEnum> getResources() {
        return resources;
    }

    public void setResources(ArrayList<TileResourceEnum> resources) {
        this.resources = resources;
    }
}
