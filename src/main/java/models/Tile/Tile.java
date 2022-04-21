package models.Tile;

import models.Feature.TileFeature;
import models.Feature.TileFeatureEnum;
import models.Improvement.TileImprovement;
import models.Resource.TileResource;
import models.Units.Combat.CombatUnits;
import models.Units.Nonecombat.NoneCombatUnits;

import java.util.ArrayList;

public class Tile {
    private TileMode mode;
    private TileResource resource;
    private ArrayList<TileFeature> features;
    private TileImprovement improvement;
    private NoneCombatUnits noneCombatUnits;
    private CombatUnits combatUnits;

    public Tile(TileMode mode, TileResource resource, ArrayList<TileFeature> features) {
        setMode(mode);
        setResource(resource);
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

    public boolean hasRiver() {
        for (int i = 0; i < this.features.size(); i++) {
            if(this.features.get(i).getFeatureName() == TileFeatureEnum.river)
                return true;
        }
        return false;
    }


}