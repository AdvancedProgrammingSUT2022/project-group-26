package models;

import java.util.ArrayList;

import models.Building.Building;
import models.Tile.Tile;
import models.Units.Units;

public class City {

    private Food food;
    private ArrayList<Tile> tiles;
    private Tile center;
    private ArrayList<Tile> underWorkTiles;
    private int maxPopulation;
    private ArrayList<Building> buildings;
    private int HP;
    private ArrayList<Units> garrison;
    private BeingBuild beingBuild;

    public City() {
    }

    public int getNumOfUnemployedWorkers() {
        return maxPopulation - underWorkTiles.size();
    }

    public int getFoodProduction() {
        return Food.getFoodProduction(this);
    }

    public int getProduction() {
        int sum = 0;
        for (Tile tile : getUnderWorkTiles()) {
            sum += tile.getProduction();
        }
        return sum;
    }

    public int getGoldProduction() {
        int sum = 0;
        for (Tile tile : getUnderWorkTiles()) {
            sum += tile.getGold();
        }
        return sum;
    }

    public int getCombatStrength() {
        return 0;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public ArrayList<Tile> getTiles() {
        return tiles;
    }

    public void setTiles(ArrayList<Tile> tiles) {
        this.tiles = tiles;
    }

    public Tile getCenter() {
        return center;
    }

    public void setCenter(Tile center) {
        this.center = center;
    }

    public ArrayList<Tile> getUnderWorkTiles() {
        return underWorkTiles;
    }

    public void setUnderWorkTiles(ArrayList<Tile> underWorkTiles) {
        this.underWorkTiles = underWorkTiles;
    }

    public int getMaxPopulation() {
        return maxPopulation;
    }

    public void setMaxPopulation(int maxPopulation) {
        this.maxPopulation = maxPopulation;
    }

    public ArrayList<Building> getBuildings() {
        return buildings;
    }

    public void setBuildings(ArrayList<Building> buildings) {
        this.buildings = buildings;
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public ArrayList<Units> getGarrison() {
        return garrison;
    }

    public void setGarrison(ArrayList<Units> garrison) {
        this.garrison = garrison;
    }

    public BeingBuild getBeingBuild() {
        return beingBuild;
    }

    public void setBeingBuild(BeingBuild beingBuild) {
        this.beingBuild = beingBuild;
    }

    public void addOneToMaxPopulation() {
        setMaxPopulation(getMaxPopulation() + 1);
    }
    public void removeOneToMaxPopulation() {
        setMaxPopulation(getMaxPopulation() -1);
    }
}