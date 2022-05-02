package controllers.GameControllers;

import controllers.Output;
import models.*;
import models.Building.Building;
import models.Resource.TileResource;
import models.Technology.Tech;
import models.Units.Unit;
import models.Units.UnitNameEnum;

public class makeUnitController {
    // TODO : methods are in a wrong place - fix it
    public Output createUnit(Player player, City city, UnitNameEnum name) {
        if (city.getBeingBuild() != null) return Output.CITY_IS_BUSY;
        if (!player.getResearchedTechs().contains(new Tech(name.getTechnologyRequired())))
            return Output.YOUR_TECH_IS_BEHIND;
        if (!player.getAvailableResources().contains(new TileResource(name.getResourcesRequired())))
            return Output.DONT_HAVE_THE_NEEDED_RESOURCES;
        city.setBeingBuild(new BeingBuild(new Unit(player, city.getCenter(), name)));
        return Output.GETTING_CREATED;
    }




}