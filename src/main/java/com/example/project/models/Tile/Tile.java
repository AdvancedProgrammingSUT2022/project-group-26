package com.example.project.models.Tile;

import com.example.project.models.Building.Building;
import com.example.project.models.Feature.TileFeature;
import com.example.project.models.Feature.TileFeatureEnum;
import com.example.project.models.Improvement.TileImprovement;
import com.example.project.models.Player;
import com.example.project.models.Resource.TileResource;
import com.example.project.models.Units.Combat.CombatUnits;
import com.example.project.models.Units.Nonecombat.NoneCombatUnits;

import java.util.ArrayList;

public class Tile {
    private TileMode mode;
    private TileResource resource;
    private TileFeature feature;
    private TileImprovement improvement;
    private NoneCombatUnits noneCombatUnits;
    private CombatUnits combatUnits;
    private Building building;

    private boolean hasRoad = false;

    public Tile(TileMode mode, TileResource resource, TileFeature feature) {
        setMode(mode);
        setResource(resource);
        if (feature != null)
            setFeature(feature);
    }

    public Tile(Tile tile) {
        if (tile.getMode() != null)
            setMode(tile.getMode().clone());
        if (tile.getResource() != null)
            setResource(tile.getResource().clone());
        if (tile.getFeature() != null)
            setFeature(tile.getFeature().clone());
        if (tile.getImprovement() != null)
            setImprovement(tile.getImprovement().clone());
        if (tile.getNoneCombatUnits() != null) {
            setNoneCombatUnits(new NoneCombatUnits(this,
                    tile.getNoneCombatUnits().getUnitNameEnum(), tile.getNoneCombatUnits().getPlayer()));
        }
        if (tile.getCombatUnits() != null) {
            CombatUnits clonedCombatUnit = new CombatUnits(
                    this, tile.getCombatUnits().getUnitNameEnum(), tile.getCombatUnits().getPlayer());
            clonedCombatUnit.setHealth(tile.combatUnits.getHealth());
            setCombatUnits(clonedCombatUnit);
        }
        if(tile.getBuilding() != null){
            setBuilding(this.building);
        }
    }

    public Tile clone() {
        return new Tile(this);
    }


    public Double addUpFeaturesMovementCosts(TileFeature feature) {
        if (feature == null) return 0D;
        return feature.getMovementCost();
    }

    public double addUpFeaturesTroopBoost(TileFeature feature) {
        if (feature == null) return 0D;
        return feature.getTroopBoost();
    }

    public int addUpFeaturesGold(TileFeature feature) {
        if (feature == null) return 0;
        return feature.getGold();
    }

    public int addUpFeaturesFood(TileFeature feature) {
        if (feature == null) return 0;
        return feature.getFood();
    }

    public int addUpFeaturesProduction(TileFeature feature) {
        if (feature == null) return 0;
        return feature.getProduction();
    }

    public Double getMp() {
        if (getHasRoad())
            return (mode.getMovementCost() + addUpFeaturesMovementCosts(feature)) * 5 / 10; // ثابت ک میتونه عوض شه
        return mode.getMovementCost() + addUpFeaturesMovementCosts(feature);
    }

    public int getGold() {
        int sum = mode.getGold();
        if (resource != null) sum += resource.getGold();
        if (improvement != null) sum += improvement.getGold();
        if (feature != null) sum += addUpFeaturesGold(feature);
        return sum;
    }

    public int getFood() {
        int sum = mode.getFood();
        if (resource != null) sum += resource.getFood();
        if (improvement != null) sum += improvement.getFood();
        if (feature != null) sum += addUpFeaturesFood(feature);
        return sum;
    }


    public int getProduction() {
        int sum = mode.getProduction();
        if (resource != null) sum += resource.getProduction();
        if (feature != null) sum += addUpFeaturesProduction(feature);
        if (improvement != null) sum += improvement.getProduction();
        return sum;
    }

    public int getEconomy() {
        return getProduction() + getFood() + getGold();
    }

    public double getCombatBonus() {
        return mode.getTroopBoost() + addUpFeaturesTroopBoost(feature);
    }

    public TileMode getMode() {
        return mode;
    }

    public void setMode(TileMode mode) {
        this.mode = mode;
    }

    public TileResource getResource() {
        return resource;
    }

    public void setResource(TileResource resource) {
        this.resource = resource;
    }


    public TileImprovement getImprovement() {
        return improvement;
    }

    public void setImprovement(TileImprovement improvement) {
        this.improvement = improvement;
    }

    public NoneCombatUnits getNoneCombatUnits() {
        return noneCombatUnits;
    }

    public void setNoneCombatUnits(NoneCombatUnits noneCombatUnits) {
        this.noneCombatUnits = noneCombatUnits;
    }

    public CombatUnits getCombatUnits() {
        return combatUnits;
    }

    public void setCombatUnits(CombatUnits combatUnits) {
        this.combatUnits = combatUnits;
    }


    public boolean hasFeature(TileFeatureEnum featureName) {
        if (this.feature != null && this.feature.getFeatureName() == featureName) return true;
        return false;
    }

    public static boolean isNeighbor(int firstI, int firstJ, int secondI, int secondJ) {
        if (firstI == secondI && firstJ == secondJ) return false;
        if (Math.abs(firstI - secondI) >= 2 || Math.abs(firstJ - secondJ) >= 2) return false;
        if (firstJ == secondJ) {
            if (Math.abs(firstI - secondI) <= 1) return true;
            else return false;
        }
        if (firstI == secondI) {
            if (Math.abs(firstJ - secondJ) <= 1) return true;
            else return false;
        }
        if (firstJ % 2 == 1) {
            if (secondI == firstI - 1) return true;
            else return false;
        }
        if (firstJ % 2 == 0) {
            if (secondI == firstI + 1) return true;
            else return false;
        }
        return false;
    }

    public TileFeature getFeature() {
        return feature;
    }

    public void setFeature(TileFeature feature) {
        this.feature = feature;
    }

    public boolean getHasRoad() {
        return hasRoad;
    }

    public void setHasRoad(boolean hasRoad) {
        this.hasRoad = hasRoad;
    }

    public boolean checkEnums(ArrayList<Enum> whereCanBeFind) {
        // TODO :  improve function ?!
        if (whereCanBeFind != null) {
            if (getFeature() != null && whereCanBeFind.contains(getFeature().getFeatureName())) return true;
            if (getImprovement() != null && whereCanBeFind.contains((getImprovement().getImprovementName())))
                return true;
            if (getMode() != null && whereCanBeFind.contains(getMode().getTileName())) return true;
            if (getResource() != null && whereCanBeFind.contains(getResource().getResourceName())) return true;
        }
        return false;
    }

    public static boolean hasOwner(Tile tile, ArrayList<Player> players) {
        for (int i = 0; i < players.size(); i++)
            if (players.get(i).hasTile(tile))
                return true;
        return false;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }
}