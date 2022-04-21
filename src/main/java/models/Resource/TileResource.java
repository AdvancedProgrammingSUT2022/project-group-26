package models.Resource;

import java.util.ArrayList;

import models.Feature.TileFeatureEnum;
import models.Technology.TechEnum;
import models.Improvement.TileImprovementEnum;
import models.Tile.TileModeEnum;

public class TileResource {
    private TileResourceEnum resourceName;
    private int food;
    private int production;
    private int gold;
    private TileImprovementEnum improvement;
    private TechEnum requisiteTech;
    private ArrayList<TileModeEnum> whereCanBeFind;


    public TileResource(TileResourceEnum resourceName) {
        setResourceName(resourceName);
        setFood(resourceName.getFood());
        setProduction(resourceName.getProduction());
        setGold(resourceName.getGold());
    }

    public TileResourceEnum getResourceName() {
        return this.resourceName;
    }

    public void setResourceName(TileResourceEnum resourceName) {
        this.resourceName = resourceName;
    }

    public int getFood() {
        return this.food;
    }

    public void setFood(int food) {
        this.food = food;
    }

    public int getProduction() {
        return this.production;
    }

    public void setProduction(int production) {
        this.production = production;
    }

    public int getGold() {
        return this.gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public TileImprovementEnum getImprovement() {
        return this.improvement;
    }

    public void setImprovement(TileImprovementEnum improvement) {
        this.improvement = improvement;
    }

    public TechEnum getRequisiteTech() {
        return this.requisiteTech;
    }

    public void setRequisiteTech(TechEnum requisiteTech) {
        this.requisiteTech = requisiteTech;
    }

    public ArrayList<TileModeEnum> getWhereCanBeFind() {
        return this.whereCanBeFind;
    }

    public void setWhereCanBeFind(ArrayList<TileModeEnum> whereCanBeFind) {
        this.whereCanBeFind = whereCanBeFind;
    }


}