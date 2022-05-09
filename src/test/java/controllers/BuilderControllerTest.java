package controllers;

import controllers.GameControllers.BuilderController;
import models.Feature.TileFeature;
import models.Feature.TileFeatureEnum;
import models.Improvement.TileImprovement;
import models.Improvement.TileImprovementEnum;
import models.Player;
import models.Resource.TileResource;
import models.Resource.TileResourceEnum;
import models.Technology.TechEnum;
import models.Tile.Tile;
import models.Tile.TileMode;
import models.Tile.TileModeEnum;
import models.Units.Nonecombat.BuilderUnit;
import models.Units.Unit;
import models.Units.UnitNameEnum;
import models.User;
import models.UsersDatabase;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

import static org.mockito.Mockito.when;

public class BuilderControllerTest {
    private Player player;
    private BuilderUnit unit;
    private Tile tile;
    private BuilderController builderController;

    @BeforeEach
    public void setUp() {
        player = new Player(new User("ilya", "Ilya1234", "ilya"));
        tile = new Tile(new TileMode(TileModeEnum.GRASSLAND), new TileResource(TileResourceEnum.HORSE),
                null);
        tile.setFeature(new TileFeature(TileFeatureEnum.FOREST));
        unit = new BuilderUnit(tile, UnitNameEnum.WORKER, player);
        tile.setImprovement(new TileImprovement(TileImprovementEnum.FARMING));
        builderController = new BuilderController();
    }

    //repairImprovement
    @Test
    public void repairImprovementTest() {
        tile.getImprovement().setIsBroken(true);
        Output output = builderController.repairImprovement(player, unit);
        Assertions.assertEquals(Output.IMPROVEMENT_GETTING_REPAIRED, output);
    }

    @Test
    public void wrongPlayerRepairImprovementTest() {
        unit.setPlayer(null);
        Output output = builderController.repairImprovement(player, unit);
        Assertions.assertEquals(Output.UNIT_NOT_YOURS, output);
    }

    @Test
    public void notWorkerRepairImprovementTest() {
        unit.setUnitNameEnum(UnitNameEnum.SETTLER);
        Output output = builderController.repairImprovement(player, unit);
        Assertions.assertEquals(Output.NOT_A_WORKER, output);
    }

    @Test
    public void workingWorkerRepairImprovementTest() {
        unit.setIsWorking(true);
        Output output = builderController.repairImprovement(player, unit);
        Assertions.assertEquals(Output.WORKER_IS_BUSY, output);
    }

    @Test
    public void notBrokenTileRepairImprovementTest() {
        Output output = builderController.repairImprovement(player, unit);
        Assertions.assertEquals(Output.IMPROVEMENT_IS_NOT_BROKEN, output);
    }

    //removeTileFeature
    @Test
    public void removeTileFeatureTest() {
        Output output = builderController.removeTileFeature(player, unit);
        Assertions.assertEquals(Output.REMOVING_FEATURE, output);
    }

    @Test
    public void wrongPlayerRemoveTileFeatureTest() {
        unit.setPlayer(null);
        Output output = builderController.removeTileFeature(player, unit);
        Assertions.assertEquals(Output.UNIT_NOT_YOURS, output);
    }

    @Test
    public void notWorkerRemoveTileFeatureTest() {
        unit.setUnitNameEnum(UnitNameEnum.SETTLER);
        Output output = builderController.removeTileFeature(player, unit);
        Assertions.assertEquals(Output.NOT_A_WORKER, output);
    }

    @Test
    public void workingWorkerRemoveTileFeatureTest() {
        unit.setIsWorking(true);
        Output output = builderController.removeTileFeature(player, unit);
        Assertions.assertEquals(Output.WORKER_IS_BUSY, output);
    }

    @Test
    public void noFeatureRemoveTileFeatureTest() {
        tile.setFeature(null);
        Output output = builderController.removeTileFeature(player, unit);
        Assertions.assertEquals(Output.NO_FEATURE_TO_REMOVE, output);
    }

    //makeARoad
    @Test
    public void makeARoadTest() {
        Output output = builderController.makeARoad(player, unit);
        Assertions.assertEquals(Output.BUILDING_ROAD, output);
    }

    @Test
    public void wrongPlayerMakeARoadTest() {
        unit.setPlayer(null);
        Output output = builderController.makeARoad(player, unit);
        Assertions.assertEquals(Output.UNIT_NOT_YOURS, output);
    }

    @Test
    public void notWorkerMakeARoadTest() {
        unit.setUnitNameEnum(UnitNameEnum.SETTLER);
        Output output = builderController.makeARoad(player, unit);
        Assertions.assertEquals(Output.NOT_A_WORKER, output);
    }

    @Test
    public void workingWorkerMakeARoadTest() {
        unit.setIsWorking(true);
        Output output = builderController.makeARoad(player, unit);
        Assertions.assertEquals(Output.WORKER_IS_BUSY, output);
    }

    @Test
    public void hasRoadTileMakeARoadTest() {
        tile.setHasRoad(true);
        Output output = builderController.makeARoad(player, unit);
        Assertions.assertEquals(Output.TILE_HAS_ROAD, output);
    }

    //improveTile
    @Test
    public void wrongPlayerImproveTileTest() {
        unit.setPlayer(null);
        Output output = builderController.improveTile(player, unit, TileImprovementEnum.FARM);
        Assertions.assertEquals(Output.UNIT_NOT_YOURS, output);
    }

    @Test
    public void notWorkerImproveTileTest() {
        unit.setUnitNameEnum(UnitNameEnum.SETTLER);
        Output output = builderController.improveTile(player, unit, TileImprovementEnum.FARM);
        Assertions.assertEquals(Output.NOT_A_WORKER, output);
    }

    @Test
    public void workingWorkerImproveTileTest() {
        unit.setIsWorking(true);
        Output output = builderController.improveTile(player, unit, TileImprovementEnum.FARM);
        Assertions.assertEquals(Output.WORKER_IS_BUSY, output);
    }

    @Test
    public void cantBuildImproveTileTest() {
        Output output = builderController.improveTile(player, unit, TileImprovementEnum.FARM);
        Assertions.assertEquals(Output.CANT_PUT_AN_IMPROVEMENT_HERE, output);
    }
}