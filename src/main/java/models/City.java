package models;

import java.util.ArrayList;

import models.Building.Building;
import models.Tile.Tile;
import models.Units.Unit;

public class City {

    private String name;
    private Food1 food1;

    private transient Food food;
    private ArrayList<Tile> tiles = new ArrayList<>();
    private Tile center;
    private ArrayList<Tile> underWorkTiles = new ArrayList<>();
    private int maxPopulation = 1;
    // TODO: shouldn't be population?(without specialists)
    private ArrayList<Building> buildings = new ArrayList<>();
    private int HP = 20; // TODO : ?!?
    private Unit garrison; // TODO : ?!!?
    private BeingBuild beingBuild = null;

    public City(Tile center, GameMap gameMap, String name) {
        setName(name);
        food = new Food(this);
        setCenter(center);
        setTiles(center, gameMap);
    }

    private void setTiles(Tile center, GameMap gameMap) {
        this.tiles.add(center);
        for (int i = 0; i < gameMap.getMap().length; i++)
            for (int j = 0; j < gameMap.getMap()[0].length; j++)
                if (Tile.isNeighbor(gameMap.getIndexI(center), gameMap.getIndexJ(center), i, j))
                    if (!this.tiles.contains(gameMap.getTile(i, j)))
                        this.tiles.add(gameMap.getTile(i, j));
    }

    public Object build() {
        if (getBeingBuild() == null) return null;
        getBeingBuild().removeFromProductionCost(getProduction());
        if (getBeingBuild().isBuilt()) {
            Object save = getBeingBuild().getGettingBuild();
            setBeingBuild(null);
            return save;
        }
        return null;
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

    public void removeOneFromMaxPopulation() {
        setMaxPopulation(getMaxPopulation() - 1);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Tile> getNeighborTiles(GameMap gameMap) {
        ArrayList<Tile> neighborTiles = new ArrayList<>();
        for (int k = 0; k < tiles.size(); k++) {
            for (int i = 0; i < gameMap.getMap().length; i++) {
                for (int j = 0; j < gameMap.getMap()[i].length; j++) {
                    if (Tile.isNeighbor(i, j, gameMap.getIndexI(tiles.get(k)), gameMap.getIndexJ(tiles.get(k))))
                        neighborTiles.add(gameMap.getTile(i, j));
                }
            }
        }
        return neighborTiles;
    }
}