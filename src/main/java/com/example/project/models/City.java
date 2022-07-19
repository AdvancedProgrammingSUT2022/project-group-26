package com.example.project.models;

import com.example.project.models.Building.Building;
import com.example.project.models.Building.BuildingEnum;
import com.example.project.models.Tile.Tile;
import com.example.project.models.Tile.TileModeEnum;
import com.example.project.models.Units.Combat.CombatUnit;

import java.util.ArrayList;

public class City {
    private boolean isAttached; // ok
    private String name; // ok
    private ArrayList<Tile> tiles = new ArrayList<>(); // ok
    private Tile center; // ok
    private ArrayList<Tile> underWorkTiles = new ArrayList<>(); // ok
    private int maxPopulation = 1; // ok
    private ArrayList<Building> buildings = new ArrayList<>(); // ok
    private float health = 20f; // ok
    private CombatUnit garrison; // ok
    private BeingBuild beingBuild = null; // ok

    public City(Tile center, GameMap gameMap, String name) {
        setName(name);
        new Food(this);
        setCenter(center);
        setTiles(center, gameMap);
        setAttached(false);
        if (getCenter().getMode() != null)
            if (getCenter().getMode().getTileName() == TileModeEnum.HILL) setHealth(30);
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
            if (tile.getGold() != 0 && containsBuilding(BuildingEnum.MINT)) sum += 3;
        }
        if (containsBuilding(BuildingEnum.MARKET)) sum = sum * 5 / 4;
        if (containsBuilding(BuildingEnum.BANK)) sum = sum * 5 / 4;
        if (containsBuilding(BuildingEnum.SATRAP_COURT)) sum = sum * 5 / 4;
        if (containsBuilding(BuildingEnum.STOCK_EXCHANGE)) sum = sum * 4 / 3;
        return sum;
    }

    public int getCombatStrength() {
        return 0;
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

    public CombatUnit getGarrison() {
        return garrison;
    }

    public void setGarrison(CombatUnit garrison) {
        this.garrison = garrison;
    }

    public float calculateAttack() {
        float bonus = 1;
        if (getCenter().getMode().getTileName() == TileModeEnum.HILL) bonus = (float) 1.2;
        if (garrison != null) return (20 + garrison.calculateAttack()) * bonus;
        return 20 * bonus;
    }

    public void takeDamage(float attackerDamage) {
        setHealth(getHealth() - attackerDamage);
    }

    public float calculateDefence() {
        float bonus = 1;
        float sum = 20;
        if (getCenter().getMode().getTileName() == TileModeEnum.HILL) bonus = (float) 1.2;
        if (garrison != null) sum += garrison.calculateAttack();
        if (containsBuilding(BuildingEnum.WALLS)) sum += 5;
        if (containsBuilding(BuildingEnum.CASTLE)) sum += 7.5;
        if (containsBuilding(BuildingEnum.MILITARY_BASE)) sum += 12;
        return sum * bonus;
    }

    public boolean containsBuilding(BuildingEnum buildingEnum) {
        boolean test = false;
        for (Building building : buildings) {
            if (building.getName() == buildingEnum) {
                test = true;
                break;
            }
        }
        return test;
    }

    public static boolean isCity(int i, int j, Player player) {
        for (int k = 0; k < Game.getInstance().getPlayers().size(); k++) {
            if (Game.getInstance().getPlayers().get(k).getTiles().contains(
                    GameMap.getCorrespondingTile(player.getGameMap().getTile(i, j), player.getGameMap(), Game.getInstance().getGameMap())))
                return true;
        }
        return false;
    }

    public static boolean isCityCenter(int i, int j, Player player) {
        for (int k = 0; k < Game.getInstance().getPlayers().size(); k++) {
            for (int l = 0; l < Game.getInstance().getPlayers().get(k).getCities().size(); l++)
                if (Game.getInstance().getPlayers().get(k).getCities().get(l).getCenter().equals(
                        GameMap.getCorrespondingTile(player.getGameMap().getTile(i, j), player.getGameMap()
                                , Game.getInstance().getGameMap())))
                    return true;
        }
        return false;
    }

    public void addBuilding(Building building) {
        buildings.add(building);
        for (Tile tile : tiles)
            if (tile != center && tile.getBuilding() == null) {
                tile.setBuilding(building);
                break;
            }
    }


    public ArrayList<Building> getBuildings() {
        return buildings;
    }

    public void setBuildings(ArrayList<Building> buildings) {
        this.buildings = buildings;
    }

    public ArrayList<BuildingEnum> getBuildingEnums() {
        ArrayList<BuildingEnum> buildingEnums = new ArrayList<>();
        for (Building building : buildings)
            buildingEnums.add(building.getName());
        return buildingEnums;
    }

    public boolean hasRiver() {
        // todo : complete?
        // dont know what to do
        return true;
    }
}