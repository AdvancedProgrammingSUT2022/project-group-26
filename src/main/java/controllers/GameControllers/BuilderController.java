package controllers.GameControllers;

import controllers.Output;
import models.Player;
import models.Units.Unit;

public class BuilderController {
    //TODO : کلاس ها همشون با یونیتن ولی فک کنم باید تقسیم کنم ؟!
    public Output removeTileFeature(Player player, Unit unit) {
        if (unit.getPlayer() != player) return Output.UNIT_NOT_YOURS;
        if (!unit.isAWorker()) return Output.NOT_A_WORKER;
        if (unit.getPosition().getFeature() == null) return Output.NO_FEATURE_TO_REMOVE;
//        AssignWorker(); //TODO : ...........
        return Output.REMOVING_FEATURE;
    }

    public Output makeARoad(Player player, Unit unit) {
        if (unit.getPlayer() != player) return Output.UNIT_NOT_YOURS;
        if (!unit.isAWorker()) return Output.NOT_A_WORKER;
        if (unit.getPosition().getHasRoad()) return Output.TILE_HAS_ROAD;
        // assign worker // TODO : ........
        return Output.BUILDING_ROAD;
    }

    public Output improveTile(Player player, Unit unit) {
        if (unit.getPlayer() != player) return Output.UNIT_NOT_YOURS;
        if (!unit.isAWorker()) return Output.NOT_A_WORKER;



        return Output.IMPROVING_TILE;
    }
}
