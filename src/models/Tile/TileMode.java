package models.Tile;

import models.Resource.TileResourceEnum;
import models.Feature.TileFeatureEnum;

import java.util.ArrayList;

public class TileMode {
    private TileModeEnum TileName;
    private int MovementCost;
    private int food;
    private int production;
    private int gold;
    private float combatBonus;
    private ArrayList<TileFeatureEnum> possibleFeature;
    private ArrayList<TileResourceEnum> possibleResources;

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

    public float getCombatBonus() {
        return combatBonus;
    }

    public void setCombatBonus(float combatBonus) {
        this.combatBonus = combatBonus;
    }


    public int getMovementCost() {
        return MovementCost;
    }

    public void setMovementCost(int movementCost) {
        MovementCost = movementCost;
    }
}
