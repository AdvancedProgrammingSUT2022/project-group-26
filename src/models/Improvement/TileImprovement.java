package models.Improvement;

import java.util.*;

import models.Technology.Tech;
import models.Tile.TileModeEnum;

public class TileImprovement {
    private TileImprovementEnum improvementName;
    private int production;
    private int food;
    private int gold;
    private Tech requiredTech;
    private ArrayList<TileModeEnum> whereCanBeFind;

    public TileImprovement(TileImprovementEnum improvementName) {

    }


    public TileImprovementEnum getImprovementName() {
        return this.improvementName;
    }

    public void setImprovementName(TileImprovementEnum improvementName) {
        this.improvementName = improvementName;
    }

    public int getProduction() {
        return this.production;
    }

    public void setProduction(int production) {
        this.production = production;
    }

    public int getFood() {
        return this.food;
    }

    public void setFood(int food) {
        this.food = food;
    }

    public int getGold() {
        return this.gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public ArrayList<TileModeEnum> getWhereCanBeFind() {
        return this.whereCanBeFind;
    }

    public void setWhereCanBeFind(ArrayList<TileModeEnum> whereCanBeFind) {
        this.whereCanBeFind = whereCanBeFind;
    }

    public Tech getRequiredTech() {
        return requiredTech;
    }

    public void setRequiredTech(Tech requiredTech) {
        this.requiredTech = requiredTech;
    }
}