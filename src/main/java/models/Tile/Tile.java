package models.Tile;

import models.Feature.TileFeature;
import models.Feature.TileFeatureEnum;
import models.Game;
import models.GameMap;
import models.Improvement.TileImprovement;
import models.Player;
import models.Resource.TileResource;
import models.Units.Combat.CombatUnits;
import models.Units.Nonecombat.NoneCombatUnits;
import models.Units.Unit;

import java.util.ArrayList;

public class Tile {
    private TileMode mode;
    private TileResource resource;
    private ArrayList<TileFeature> features = new ArrayList<>(); //TODO : fix ?!?

    private TileFeature feature;

    private TileImprovement improvement;
    private NoneCombatUnits noneCombatUnits;
    private CombatUnits combatUnits;

    private boolean hasRoad = false;

    public Tile(TileMode mode, TileResource resource, ArrayList<TileFeature> features) {
        setMode(mode);
        setResource(resource);
        if (features != null)
            setFeatures(features);
    }

    public Tile(Tile tile) {
        if (tile.getMode() != null)
            setMode(tile.getMode().clone());
        if (tile.getResource() != null)
            setResource(tile.getResource().clone());
        if (tile.getFeatures() != null)
            setFeatures(new ArrayList<>(tile.getFeatures()));
        if (tile.getImprovement() != null)
            setImprovement(tile.getImprovement().clone());
        if (tile.getNoneCombatUnits() != null)
            setNoneCombatUnits(new NoneCombatUnits(this,
                    tile.getNoneCombatUnits().getUnitNameEnum(), tile.getNoneCombatUnits().getPlayer()));
        if (tile.getCombatUnits() != null)
            setCombatUnits(new CombatUnits(
                    this, tile.getCombatUnits().getUnitNameEnum(), tile.getCombatUnits().getPlayer()));
    }

    public Tile clone() {
        return new Tile(this);
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

    public int getEconomy() {
        return getProduction() + getFood() + getGold();
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
            if (getFeature() != null && whereCanBeFind.contains(getFeature())) return true;
            if (getImprovement() != null && whereCanBeFind.contains((getImprovement()))) return true;
            if (getMode() != null && whereCanBeFind.contains(getMode())) return true;
            if (getResource() != null && whereCanBeFind.contains(getResource())) return true;
        }
        return false;
    }

    public static boolean hasOwner(Tile tile, ArrayList<Player> players) {
        for (int i = 0; i < players.size(); i++)
            if (players.get(i).hasTile(tile))
                return true;
        return false;
    }
}