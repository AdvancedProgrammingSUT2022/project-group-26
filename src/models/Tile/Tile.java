package models.Tile;

import models.Feature.TileFeature;
import models.Food;
import models.Gold;
import models.Improvement.TileImprovement;
import models.Resource.TileResource;
import models.Units.Combat.CombatUnits;
import models.Units.Nonecombat.NoneCombatUnits;

public class Tile {
    private TileMode mode;
    private TileResource resource;
    private TileFeature feature;
    private TileImprovement improvement;
    private NoneCombatUnits noneCombatUnits;
    private CombatUnits combatUnits;

    public Tile(TileMode mode, TileResource resource, TileFeature feature) {
        setMode(mode);
        setResource(resource);
        setFeature(feature);
    }

    public Double getMp() {
        return mode.getMovementCost() + feature.getMovementCost();
    }

    public int getGold() {
        return mode.getGold() + resource.getGold() + feature.getGold() + improvement.getGold();
    }

    public int getFood() {
        return mode.getFood() + resource.getFood() + feature.getFood() + improvement.getFood();
    }

    public double getCombatBonus() {
        return mode.getTroopBoost() + feature.getTroopBoost();
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

    public TileFeature getFeature() {
        return feature;
    }

    public void setFeature(TileFeature feature) {
        this.feature = feature;
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
}