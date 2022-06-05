package com.example.project.controllers.GameControllers;

import com.example.project.controllers.Output;
import com.example.project.models.Feature.TileFeature;
import com.example.project.models.Feature.TileFeatureEnum;
import com.example.project.models.GameMap;
import com.example.project.models.Resource.TileResource;
import com.example.project.models.Resource.TileResourceEnum;
import com.example.project.models.Tile.TileMode;
import com.example.project.models.Tile.TileModeEnum;

public class EditGameMapController {
    public static EditGameMapController instance;

    private GameMap gameMap;

    public static EditGameMapController getInstance() {
        if (instance == null) instance = new EditGameMapController();
        return instance;
    }

    public static void clear() {
        instance = null;
    }

    boolean isICCoordinateChosen = false;
    boolean isJCCoordinateChosen = false;

    public void chooseICoordinate() {
        isICCoordinateChosen = true;
    }

    public void chooseJCoordinate() {
        isJCCoordinateChosen = true;
    }

    public boolean getShouldUpdate() {
        return isICCoordinateChosen && isJCCoordinateChosen;
    }

    public Output changeTile(int iCoordinate, int jCoordinate, String tileMode, String tileResource, String tileFeature) {
        TileMode mode = new TileMode(TileModeEnum.getEnumByString(tileMode));
        TileFeature feature = null;
        if (!tileFeature.equals(""))
            feature = new TileFeature(TileFeatureEnum.getEnumByString(tileFeature));
        TileResource resource = null;
        if (!tileResource.equals(""))
            resource = new TileResource(TileResourceEnum.getEnumByString(tileResource));
        if (!TileModeEnum.getValidFeatures
                (TileModeEnum.getEnumByString(tileMode)).contains(TileFeatureEnum.getEnumByString(tileFeature)))
            return Output.INVALID_FEATURE;
        if (resource != null && (!resource.getWhereCanBeFind().contains(TileModeEnum.getEnumByString(tileMode))
                || !resource.getWhereCanBeFind().contains(TileFeatureEnum.getEnumByString(tileFeature))))
            return Output.INVALID_RESOURCE;
        gameMap.getTile(iCoordinate, jCoordinate).setFeature(feature);
        gameMap.getTile(iCoordinate, jCoordinate).setResource(resource);
        gameMap.getTile(iCoordinate, jCoordinate).setMode(mode);
        return Output.CHANGE_TILE_SUCCESSFULLY;
    }

    public void setGameMap(GameMap gameMap) {
        this.gameMap = gameMap;
    }
}
