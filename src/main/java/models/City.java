package models;

import java.util.ArrayList;

import models.Building.Building;
import models.Tile.Tile;
import models.Units.Unit;

public class City {

    private boolean isAttached;
    private String name;
    private transient Food food;
    private ArrayList<Tile> tiles = new ArrayList<>();
    private Tile center;
    private ArrayList<Tile> underWorkTiles = new ArrayList<>();
    private int maxPopulation = 1;
    // TODO: shouldn't be population?(without specialists)
    private ArrayList<Building> buildings = new ArrayList<>();
    private float health = 20f; // TODO : ?!?
    private Unit garrison;
    private BeingBuild beingBuild = null;

    public City(Tile center, GameMap gameMap, String name) {
        setName(name);
        food = new Food(this);
        setCenter(center);
        setTiles(center, gameMap);
        setAttached(false);
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
            if (tile == null) continue;
            sum += tile.getProduction();
        }
        return sum;
    }

    public int getGoldProduction() {
        int sum = 0;
        for (Tile tile : getUnderWorkTiles()) {
            if (tile == null) continue;
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

    public float getHealth() {
        return health;
    }

    public void setHealth(float health) {
        this.health = health;
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

    public boolean isAttached() {
        return isAttached;
    }

    public void setAttached(boolean attached) {
        isAttached = attached;
    }

    public Unit getGarrison() {
        return garrison;
    }

    public void setGarrison(Unit garrison) {
        this.garrison = garrison;
    }

    public float calculateAttack() {
        // TODO ....
        if (garrison != null) return 40;
        return 20;
    }

    public void takeDamage(float attackerDamage) {
        setHealth(getHealth() - attackerDamage);
    }

}