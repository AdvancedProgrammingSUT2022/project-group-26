package models.Tile;

import models.Feature.TileFeature;
import models.Feature.TileFeatureEnum;
import models.Improvement.TileImprovement;
import models.Resource.TileResource;
import models.Units.Combat.CombatUnits;
import models.Units.Nonecombat.NoneCombatUnits;
import models.Units.Units;

import java.util.ArrayList;

public class Tile {
    private TileMode mode;
    private TileResource resource;
    private ArrayList<TileFeature> features = new ArrayList<>();
    private TileImprovement improvement;
    private NoneCombatUnits noneCombatUnits;
    private CombatUnits combatUnits;
    /*in tike vase code toe goftam bashe...
    private ArrayList<TileFeature> features;
    private TileImprovement improvement;
    private Unit noneCombatUnit;
    private Unit combatUnit;*/

    public Tile(TileMode mode, TileResource resource, ArrayList<TileFeature> features) {
        setMode(mode);
        setResource(resource);
        if (features != null)
            setFeatures(features);
    }

    public Double addUpFeaturesMovementCosts(ArrayList<TileFeature> features) {
        Double sum = 0.0;
        for (TileFeature feature : features) {
            sum += feature.getMovementCost();
        }
        return sum;
    }

    public double addUpFeaturesTroopBoost(ArrayList<TileFeature> features) {
        double sum = 0.0;
        for (TileFeature feature : features) {
            sum += feature.getTroopBoost();
        }
        return sum;
    }

    public int addUpFeaturesGold(ArrayList<TileFeature> features) {
        int sum = 0;
        for (TileFeature feature : features) {
            sum += feature.getGold();
        }
        return sum;
    }

    public int addUpFeaturesFood(ArrayList<TileFeature> features) {
        int sum = 0;
        for (TileFeature feature : features) {
            sum += feature.getFood();
        }
        return sum;
    }

    public int addUpFeaturesProduction(ArrayList<TileFeature> features) {
        int sum = 0;
        for (TileFeature feature : features) {
            sum += feature.getProduction();
        }
        return sum;
    }


    public Double getMp() {
        return mode.getMovementCost() + addUpFeaturesMovementCosts(features);
    }

    public int getGold() {
        return mode.getGold() + resource.getGold() + addUpFeaturesGold(features) + improvement.getGold();
    }

    public int getFood() {
        return mode.getFood() + resource.getFood() + addUpFeaturesFood(features) + improvement.getFood();
    }


    public int getProduction() {
        return mode.getProduction() + resource.getProduction() + addUpFeaturesProduction(features) + improvement.getProduction();
    }

    public double getCombatBonus() {
        return mode.getTroopBoost() + addUpFeaturesTroopBoost(features);
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


    public ArrayList<TileFeature> getFeatures() {
        return features;
    }

    public void setFeatures(ArrayList<TileFeature> features) {
        this.features = features;
    }


    public boolean hasFeature(TileFeatureEnum featureName) {
        for (int i = 0; i < this.features.size(); i++) {
            if (this.features.get(i).getFeatureName() == featureName)
                return true;
        }
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


    // ali inaro nemidoonam dast nazadem beheshoon

    /*public Units getCombatUnit() {
        return combatUnit;
    }

    public void setCombatUnit(Units combatUnit) {
        this.combatUnit = combatUnit;
    }

    public Units getNoneCombatUnit() {
        return noneCombatUnit;
    }

    public void setNoneCombatUnit(Units noneCombatUnit) {
        this.noneCombatUnit = noneCombatUnit;
    }*/
}