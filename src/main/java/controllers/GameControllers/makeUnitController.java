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

    public void newTurn(Player player) {
        Gold.addGold(player, player.getGoldProduction());
        handleFoodOfPlayer(player);
        // TODO : handle troops waiting for command
        // TODO : handle research waiting / handle city building something
        // TODO : add jaam !//ilya hastam: boro to player.endTurn ro bebin!
         buildForPlayer(player);
    }

    private void handleFoodOfPlayer(Player player) {
        for (City city : player.getCities()) {
            Food.handleFoodOFCity(city);
        }
    }

    private void buildForPlayer(Player player) {
        for (City city : player.getCities()) {
            Object save;
            if ((save = city.build()) != null) {
                if (save instanceof Unit) {
                    player.getUnits().add((Unit) save);
                }
                if (save instanceof Building) {
                    city.getBuildings().add((Building) save);
                }
            }
        }
    }
}