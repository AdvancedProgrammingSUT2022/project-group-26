package com.example.project.models.Tile;

import com.example.project.models.Building.Building;
import com.example.project.models.Feature.TileFeature;
import com.example.project.models.Feature.TileFeatureEnum;
import com.example.project.models.Improvement.TileImprovement;
import com.example.project.models.Improvement.TileImprovementEnum;
import com.example.project.models.Player;
import com.example.project.models.Resource.TileResource;
import com.example.project.models.Technology.TechEnum;
import com.example.project.models.Units.Combat.CombatUnit;
import com.example.project.models.Units.Nonecombat.BuilderUnit;
import com.example.project.models.Units.Nonecombat.NoneCombatUnit;
import com.example.project.models.Units.UnitNameEnum;

import java.util.ArrayList;

public class Tile {
    private TileMode mode;
    private TileResource resource;
    private TileFeature feature;
    private TileImprovement improvement;
    private NoneCombatUnit noneCombatUnit;
    private CombatUnit combatUnit;
    private Building building;
    private boolean isRuined = false;
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
            if (tile.getNoneCombatUnits().getUnitNameEnum() == UnitNameEnum.WORKER)
                setNoneCombatUnits(new BuilderUnit(this,
                        tile.getNoneCombatUnits().getUnitNameEnum(), tile.getNoneCombatUnits().getPlayer()));
            else
                setNoneCombatUnits(new NoneCombatUnit(this,
                        tile.getNoneCombatUnits().getUnitNameEnum(), tile.getNoneCombatUnits().getPlayer()));
        }
        if (tile.getCombatUnits() != null) {
            CombatUnit clonedCombatUnit = new CombatUnit(
                    this, tile.getCombatUnits().getUnitNameEnum(), tile.getCombatUnits().getPlayer());
            clonedCombatUnit.setHealth(tile.combatUnit.getHealth());
            setCombatUnits(clonedCombatUnit);
        }
        if (tile.getBuilding() != null) {
            setBuilding(this.building);
        }
        setRuined(tile.isRuined);
        setHasRoad(tile.hasRoad);
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

    public NoneCombatUnit getNoneCombatUnits() {
        return noneCombatUnit;
    }

    public void setNoneCombatUnits(NoneCombatUnit noneCombatUnit) {
        this.noneCombatUnit = noneCombatUnit;
    }

    public CombatUnit getCombatUnits() {
        return combatUnit;
    }

    public void setCombatUnits(CombatUnit combatUnit) {
        this.combatUnit = combatUnit;
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

    public boolean isRuined() {
        return isRuined;
    }

    public void setRuined(boolean ruined) {
        isRuined = ruined;
    }

    public ArrayList<TileImprovementEnum> getPossibleImprovements(Player player) {
        //
        ArrayList<TileImprovementEnum> possibleImprovements = new ArrayList<>();
        if (player.getFullyResearchedTechByEnum(TechEnum.TRAPPING) != null)
            if (mode.getTileName() == TileModeEnum.TUNDRA || mode.getTileName() == TileModeEnum.HILL
                    || mode.getTileName() == TileModeEnum.PLAIN)
                possibleImprovements.add(TileImprovementEnum.CAMP);
            else if (feature != null && (feature.getFeatureName() == TileFeatureEnum.FOREST))
                possibleImprovements.add(TileImprovementEnum.CAMP);
        //
        if (player.getFullyResearchedTechByEnum(TechEnum.AGRICULTURE) != null)
            if (mode.getTileName() == TileModeEnum.PLAIN || mode.getTileName() == TileModeEnum.DESERT
                    || mode.getTileName() == TileModeEnum.GRASSLAND)
                possibleImprovements.add(TileImprovementEnum.FARM);
        //
        if (player.getFullyResearchedTechByEnum(TechEnum.CONSTRUCTION) != null)
            possibleImprovements.add(TileImprovementEnum.LUMBER_MILL);
        else if (feature != null && (feature.getFeatureName() == TileFeatureEnum.FOREST))
            possibleImprovements.add(TileImprovementEnum.LUMBER_MILL);
        //
        if (player.getFullyResearchedTechByEnum(TechEnum.MINING) != null)
            if (mode.getTileName() == TileModeEnum.PLAIN || mode.getTileName() == TileModeEnum.DESERT
                    || mode.getTileName() == TileModeEnum.GRASSLAND || mode.getTileName() == TileModeEnum.TUNDRA
                    || mode.getTileName() == TileModeEnum.SNOW || mode.getTileName() == TileModeEnum.HILL)
                possibleImprovements.add(TileImprovementEnum.MINE);
            else if (feature != null && (feature.getFeatureName() == TileFeatureEnum.FOREST ||
                    feature.getFeatureName() == TileFeatureEnum.DENSE_FOREST ||
                    feature.getFeatureName() == TileFeatureEnum.SWAMP))
                possibleImprovements.add(TileImprovementEnum.MINE);
        //
        if (player.getFullyResearchedTechByEnum(TechEnum.ENGINEERING) != null)
            if (mode.getTileName() == TileModeEnum.PLAIN || mode.getTileName() == TileModeEnum.DESERT
                    || mode.getTileName() == TileModeEnum.GRASSLAND || mode.getTileName() == TileModeEnum.TUNDRA || mode.getTileName() == TileModeEnum.SNOW)
                possibleImprovements.add(TileImprovementEnum.FACTORY);
        //
        if (player.getFullyResearchedTechByEnum(TechEnum.ANIMAL_HUSBANDRY) != null)
            if (mode.getTileName() == TileModeEnum.PLAIN || mode.getTileName() == TileModeEnum.DESERT
                    || mode.getTileName() == TileModeEnum.GRASSLAND || mode.getTileName() == TileModeEnum.TUNDRA || mode.getTileName() == TileModeEnum.HILL)
                possibleImprovements.add(TileImprovementEnum.PASTURE);
        //
        if (player.getFullyResearchedTechByEnum(TechEnum.TRAPPING) != null)
            if (mode.getTileName() == TileModeEnum.PLAIN || mode.getTileName() == TileModeEnum.DESERT
                    || mode.getTileName() == TileModeEnum.GRASSLAND || mode.getTileName() == TileModeEnum.TUNDRA)
                possibleImprovements.add(TileImprovementEnum.TRADING_POST);
        //
        if (player.getFullyResearchedTechByEnum(TechEnum.MASONRY) != null)
            if (mode.getTileName() == TileModeEnum.PLAIN || mode.getTileName() == TileModeEnum.DESERT
                    || mode.getTileName() == TileModeEnum.GRASSLAND || mode.getTileName() == TileModeEnum.TUNDRA || mode.getTileName() == TileModeEnum.HILL)
                possibleImprovements.add(TileImprovementEnum.STONE_MINE);
        //
        if (player.getFullyResearchedTechByEnum(TechEnum.CALENDAR) != null)
            if (mode.getTileName() == TileModeEnum.PLAIN || mode.getTileName() == TileModeEnum.DESERT
                    || mode.getTileName() == TileModeEnum.GRASSLAND)
                possibleImprovements.add(TileImprovementEnum.FARMING);
            else if (feature != null && (feature.getFeatureName() == TileFeatureEnum.FOREST || feature.getFeatureName() == TileFeatureEnum.DENSE_FOREST || feature.getFeatureName() == TileFeatureEnum.SWAMP || feature.getFeatureName() == TileFeatureEnum.OASIS))
                possibleImprovements.add(TileImprovementEnum.FARMING);
        if (this.improvement != null) possibleImprovements.remove(this.improvement.getImprovementName());

        return possibleImprovements;
    }
}