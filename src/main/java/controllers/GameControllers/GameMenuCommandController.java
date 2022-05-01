package controllers.GameControllers;

import controllers.Output;
import models.*;
import models.Technology.Tech;
import models.Technology.TechEnum;
import models.Tile.Tile;
import models.Units.Nonecombat.NoneCombatUnits;
import models.Units.UnitNameEnum;
import views.PlayGameCommandsRegex;
import views.PlayGameMenu;

import java.util.ArrayList;
import java.util.regex.Matcher;

public class GameMenuCommandController {
    PlayGameMenuController playGameMenuController;

    public GameMenuCommandController(PlayGameMenuController playGameMenuController) {
        this.playGameMenuController = playGameMenuController;
    }

    private boolean isValidCityName(String name) {
        return name.matches("[a-zA-Z]+");
    }

    public Output showMap(Matcher matcher) {
        int iCoordinate = Integer.parseInt(matcher.group("iCoordinate"));
        int jCoordinate = Integer.parseInt(matcher.group("jCoordinate"));
        if (!isValidCoordinate(iCoordinate, jCoordinate))
            return Output.invalidCoordinate;
        return null;
    }

    public Output changeShowMapDirection(int iCoordinate, int jCoordinate) {
        if (!isValidCoordinate(iCoordinate, jCoordinate))
            return Output.invalidCoordinate;
        return null;
    }

    private boolean isValidCoordinate(int iCoordinate, int jCoordinate) {
        if (iCoordinate < 0 || jCoordinate < 0) return false;
        if (iCoordinate > 27 || jCoordinate > 24) return false;
        return true;
    }

    public Output moveCombatUnit(Matcher matcher, GameMap gameMap, Player player) {
        MovementController movementController = new MovementController(gameMap);
        int i1, j1, i2, j2;
        i1 = Integer.parseInt(matcher.group("indexStartI"));
        i2 = Integer.parseInt(matcher.group("indexEndI"));
        j1 = Integer.parseInt(matcher.group("indexStartJ"));
        j2 = Integer.parseInt(matcher.group("indexEndJ"));
        if (!isValidCoordinate(i1, j1) || !isValidCoordinate(i2, j2))
            return Output.invalidCoordinate;
        if (gameMap.getTile(i1, j1).getCombatUnits() == null)
            return Output.NO_EXISTING_COMBAT_UNITS;
        return movementController.moveUnits(gameMap.getTile(i1, j1), gameMap.getTile(i2, j2), gameMap.getTile(i1, j1).getCombatUnits(), player);
    }

    public Output moveCivilian(Matcher matcher, GameMap gameMap, Player player) {
        MovementController movementController = new MovementController(gameMap);
        int i1, j1, i2, j2;
        i1 = Integer.parseInt(matcher.group("indexStartI"));
        i2 = Integer.parseInt(matcher.group("indexEndI"));
        j1 = Integer.parseInt(matcher.group("indexStartJ"));
        j2 = Integer.parseInt(matcher.group("indexEndJ"));
        if (!isValidCoordinate(i1, j1) || !isValidCoordinate(i2, j2))
            return Output.invalidCoordinate;
        if (gameMap.getTile(i1, j1).getNoneCombatUnits() == null)
            return Output.NO_EXISTING_NONE_COMBAT_UNITS;
        return movementController.moveUnits(gameMap.getTile(i1, j1), gameMap.getTile(i2, j2), gameMap.getTile(i1, j1).getNoneCombatUnits(), player);
    }

    public Output addCombatUnitRoute(Matcher matcher, GameMap gameMap, Player player) {
        MovementController movementController = new MovementController(gameMap);
        int i1, j1, i2, j2;
        i1 = Integer.parseInt(matcher.group("indexStartI"));
        i2 = Integer.parseInt(matcher.group("indexEndI"));
        j1 = Integer.parseInt(matcher.group("indexStartJ"));
        j2 = Integer.parseInt(matcher.group("indexEndJ"));
        if (!isValidCoordinate(i1, j1) || !isValidCoordinate(i2, j2))
            return Output.invalidCoordinate;
        if (gameMap.getTile(i1, j1).getCombatUnits() == null)
            return Output.NO_EXISTING_COMBAT_UNITS;
        return movementController.addASavedRoute(gameMap.getTile(i1, j1), gameMap.getTile(i2, j2), gameMap.getTile(i1, j1).getCombatUnits(), player);
    }

    public Output addCivilianRoute(Matcher matcher, GameMap gameMap, Player player) {
        MovementController movementController = new MovementController(gameMap);
        int i1, j1, i2, j2;
        i1 = Integer.parseInt(matcher.group("indexStartI"));
        i2 = Integer.parseInt(matcher.group("indexEndI"));
        j1 = Integer.parseInt(matcher.group("indexStartJ"));
        j2 = Integer.parseInt(matcher.group("indexEndJ"));
        if (!isValidCoordinate(i1, j1) || !isValidCoordinate(i2, j2))
            return Output.invalidCoordinate;
        if (gameMap.getTile(i1, j1).getNoneCombatUnits() == null)
            return Output.NO_EXISTING_NONE_COMBAT_UNITS;
        return movementController.addASavedRoute(gameMap.getTile(i1, j1), gameMap.getTile(i2, j2), gameMap.getTile(i1, j1).getNoneCombatUnits(), player);
    }

    public Output selectSettler(Matcher matcher, Player player, GameMap gameMap) {
        int iCoordinate = Integer.parseInt(matcher.group("iCoordinate"));
        int jCoordinate = Integer.parseInt(matcher.group("jCoordinate"));
        if (!isValidCoordinate(iCoordinate, jCoordinate))
            return Output.invalidCoordinate;
        if (gameMap.getTile(iCoordinate, jCoordinate).getNoneCombatUnits() == null)
            return Output.NO_EXISTING_SETTLER;
        if (gameMap.getTile(iCoordinate, jCoordinate).getNoneCombatUnits().getUnitNameEnum() != UnitNameEnum.SETTLER)
            return Output.NO_EXISTING_SETTLER;
        if (gameMap.getTile(iCoordinate, jCoordinate).getNoneCombatUnits().getPlayer() != player)
            return Output.SETTLER_NOT_YOURS;
        return null;
    }

    public Output createCity(Matcher matcher, NoneCombatUnits settler, Player player, ArrayList<Player> players) {
        String name = matcher.group("cityName");
        if (!isValidCityName(name))
            return Output.INVALID_CITY_NAME;
        Tile settlerTile = settler.getPosition();
        for (int i = 0; i < players.size(); i++)
            if (players.get(i).hasTile(settlerTile))
                return Output.UNABLE_CREATE_CITY;
        playGameMenuController.createCity(settler, player, name);
        return Output.CITY_CREATED;
    }

    public boolean isValidTechnology(String technologyName) {
        TechEnum[] allTechnology = TechEnum.values();
        for (TechEnum tech : allTechnology) {
            if (tech.getName().equals(technologyName))
                return true;
        }
        return false;
    }


    public Output research(Matcher matcher, Player player) {
        String technologyName = matcher.group("technologyName");
        if (!isValidTechnology(technologyName))
            return Output.INVALID_TECHNOLOGY_NAME;
        if (player.getFullyResearchedTechByEnum(Tech.getEnumByString(technologyName)) != null)
            return Output.RESEARCHED_TECHNOLOGY;
        if (player.getTechInResearch() != null &&
                player.getTechInResearch().getTechName() == Tech.getEnumByString(technologyName))
            return Output.IS_RESEARCHING;
        playGameMenuController.research(Tech.getEnumByString(technologyName), player);
        return Output.RESEARCHED;
    }

    public Output enterTechnologyInfo(Player player) {
        if (player.getCities().size() == 0)
            return Output.NO_CITY;
        return null;
    }

    public Output showMapByCity(Matcher matcher, Player player) {
        String cityName = matcher.group("cityName");
        if (player.getCityBYName(cityName) == null)
            return Output.INVALID_CITY;
        return null;
    }

    public void increaseTurn(Matcher matcher, Player player) {
        int amount = Integer.parseInt(matcher.group("amount"));
        if (amount > 0) {
            playGameMenuController.increaseTurn(player, amount);
        }
    }

    public void increaseGold(Matcher matcher, Player player) {
        int amount = Integer.parseInt(matcher.group("amount"));
        if (amount > 0) {
            playGameMenuController.increaseGold(player, amount);
        }
    }

    public void increaseHappiness(Matcher matcher, Player player){
        int amount = Integer.parseInt(matcher.group("amount"));
        if (amount > 0) {
            playGameMenuController.increaseHappiness(player, amount);
        }
    }

    public void increaseFood(Matcher matcher, Player player){
        int amount = Integer.parseInt(matcher.group("amount"));
        String cityName = matcher.group("cityName");
        City city = player.getCityBYName(cityName);
        if (city != null && amount > 0) {
            playGameMenuController.increaseFood(city, amount);
        }
    }

    public Output showCityFood(Matcher matcher, Player player){
        String cityName = matcher.group("cityName");
        if(player.getCityBYName(cityName) == null)
            return Output.INVALID_CITY;
        return null;
    }
}