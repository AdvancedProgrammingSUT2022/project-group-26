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
    private ArrayList<Enum> whereCanBeFind;

    public TileImprovement(TileImprovementEnum improvementName) {
        setImprovementName(improvementName);
        setFood(improvementName.getFood());
        setGold(improvementName.getGold());
        setProduction(improvementName.getProduction());
        requiredTech = findRequiredTech(improvementName);
        whereCanBeFind = findWhereCanBeFind(improvementName);
    }

    public static Tech findRequiredTech(TileImprovementEnum improvementName) {
        //TODO : fill function
        return null;
    }

    public static ArrayList<Enum> findWhereCanBeFind(TileImprovementEnum improvementName) {
        //TODO : fill function
        return null;
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

    public ArrayList<Enum> getWhereCanBeFind() {
        return this.whereCanBeFind;
    }

    public void setWhereCanBeFind(ArrayList<Enum> whereCanBeFind) {
        this.whereCanBeFind = whereCanBeFind;
    }

    public Tech getRequiredTech() {
        return requiredTech;
    }

    public void setRequiredTech(Tech requiredTech) {
        this.requiredTech = requiredTech;
    }
}