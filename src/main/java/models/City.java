package models;

import java.util.ArrayList;

import models.Building.Building;
import models.Tile.Tile;
import models.Units.Unit;

public class City {

    private Food food;
    private ArrayList<Tile> tiles = new ArrayList<>();
    private Tile center;
    private ArrayList<Tile> underWorkTiles = new ArrayList<>();
    private int maxPopulation = 1;
    private ArrayList<Building> buildings = new ArrayList<>();
    private int HP = 20; // TODO : ?!?
    private Unit garrison ; // TODO : ?!!?
    private BeingBuild beingBuild = null;

    public City(Tile center) {
        food = new Food(this);
        setCenter(center);
        /////////////////////////
        // TODO : add indexes : 1 0 | 0 1 | 1 1 | -1 0 | 0 -1 | 1 -1 (akhari shak daram ?)
        /////////////////////////

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
        setMaxPopulation(getMaxPopulation() - 1);
    }

    public Unit getGarrison() {
        return garrison;
    }

    public void setGarrison(Unit garrison) {
        this.garrison = garrison;
    }
}