package com.example.project.controllers.GameControllers;

import com.example.project.controllers.Output;
import com.example.project.models.Building.BuildingEnum;
import com.example.project.models.Feature.TileFeatureEnum;
import com.example.project.models.Improvement.TileImprovement;
import com.example.project.models.Improvement.TileImprovementEnum;
import com.example.project.models.Player;
import com.example.project.models.Units.Nonecombat.BuilderUnit;

public class BuilderController {
    public Output repairImprovement(Player player, BuilderUnit unit) {
        if (unit.isSleeping() || unit.isAlert()) return Output.UNIT_IS_SLEEPING;
        if (unit.getPlayer() != player) return Output.UNIT_NOT_YOURS;
        if (!unit.isAWorker()) return Output.NOT_A_WORKER;
        if (unit.getIsWorking()) return Output.WORKER_IS_BUSY;
        if (!unit.getPosition().getImprovement().getIsBroken()) return Output.IMPROVEMENT_IS_NOT_BROKEN;
        assignWorker(unit, "repair improvement"); // todo : no duplicate improvement
        return Output.IMPROVEMENT_GETTING_REPAIRED;
    }

    // this doesn't mather
    public Output repairBuilding(Player player, BuilderUnit unit) {
        // exception  handling
        assignWorker(unit, "repair <Building>"); // for phase 2
        return null;
    }

    // this is the main func
    public Output repairBuilding(Player player, BuilderUnit unit, BuildingEnum buildingEnum) {
        // exception  handling
        // todo : get sure that no exception handling in needed
        assignWorker(unit, "repair " + buildingEnum.getName()); // for phase 2
        return null;
    }

    public Output removeTileFeature(Player player, BuilderUnit unit) {
        if (unit.isSleeping() || unit.isAlert()) return Output.UNIT_IS_SLEEPING;
        if (unit.getPlayer() != player) return Output.UNIT_NOT_YOURS;
        if (!unit.isAWorker()) return Output.NOT_A_WORKER;
        if (unit.getIsWorking()) return Output.WORKER_IS_BUSY;
        if (unit.getPosition().getFeature() == null) return Output.NO_FEATURE_TO_REMOVE;
        if (unit.getPosition().getFeature().getFeatureName() != TileFeatureEnum.FOREST
                && unit.getPosition().getFeature().getFeatureName() != TileFeatureEnum.DENSE_FOREST)
            return Output.INVALID_FEATURE_TO_CLEAR;
        assignWorker(unit, "remove feature");
        return Output.REMOVING_FEATURE;
    }

    public Output makeARoad(Player player, BuilderUnit unit) {
        if (unit.isSleeping() || unit.isAlert()) return Output.UNIT_IS_SLEEPING;
        if (unit.getPlayer() != player) return Output.UNIT_NOT_YOURS;
        if (!unit.isAWorker()) return Output.NOT_A_WORKER;
        if (unit.getIsWorking()) return Output.WORKER_IS_BUSY;
        if (unit.getPosition().getHasRoad()) return Output.TILE_HAS_ROAD;
        assignWorker(unit, "create road");
        return Output.BUILDING_ROAD;
    }


    public Output improveTile(Player player, BuilderUnit unit, TileImprovementEnum improvement) {
        if (unit.isSleeping() || unit.isAlert()) return Output.UNIT_IS_SLEEPING;
        if (unit.getPlayer() != player) return Output.UNIT_NOT_YOURS;
        if (!unit.isAWorker()) return Output.NOT_A_WORKER;
        if (unit.getIsWorking()) return Output.WORKER_IS_BUSY;
        TileImprovement temp = new TileImprovement(improvement);
        temp.getRequiredTech();
        temp.getWhereCanBeFind();
        if (temp.getRequiredTech() != null && player.getFullyResearchedTechByEnum(temp.getRequiredTech()) == null)
            return Output.YOUR_TECH_IS_BEHIND;
        if (!unit.getPosition().checkEnums(temp.getWhereCanBeFind())) return Output.CANT_PUT_AN_IMPROVEMENT_HERE;
        assignWorker(unit, "improve " + improvement.getName());
        return Output.IMPROVING_TILE;
    }

    private void assignWorker(BuilderUnit builder, String work) {
        builder.setIsWorking(true);
        builder.setWork(work);
        builder.setTurn(0);
    }
}