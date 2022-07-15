package com.example.project.controllers.GameControllers;

import com.example.project.controllers.Output;
import com.example.project.models.*;
import com.example.project.models.Building.Building;
import com.example.project.models.Building.BuildingEnum;
import com.example.project.models.Improvement.TileImprovementEnum;
import com.example.project.models.Technology.Tech;
import com.example.project.models.Technology.TechEnum;
import com.example.project.models.Tile.Tile;
import com.example.project.models.Units.Combat.CombatUnits;
import com.example.project.models.Units.Combat.SiegeUnit;
import com.example.project.models.Units.Nonecombat.BuilderUnit;
import com.example.project.models.Units.Nonecombat.NoneCombatUnits;
import com.example.project.models.Units.Unit;
import com.example.project.models.Units.UnitNameEnum;
import com.example.project.views.PlayGamePage;
import com.example.project.views.PopupMessage;
import javafx.scene.control.Alert;

import java.util.ArrayList;
import java.util.regex.Matcher;

public class GameMenuCommandController {
    PlayGameMenuController playGameMenuController;
    BuilderController builderController = new BuilderController();
    CombatController combatController;
    MovementController movementController;

    public GameMenuCommandController(PlayGameMenuController playGameMenuController, GameMap gamemap) {
        this.playGameMenuController = playGameMenuController;
        this.movementController = new MovementController(gamemap);
        this.combatController = new CombatController(gamemap);
    }

    public boolean isNewCityName(String cityName, ArrayList<Player> players) {
        for (Player player : players) {
            for (City city : player.getCities()) {
                if (city.getName().equals(cityName))
                    return false;
            }
        }
        return true;
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

//    public Output moveCombatUnit(Matcher matcher, GameMap gameMap, Player player) {
//        int i1, j1, i2, j2;
//        i1 = Integer.parseInt(matcher.group("indexStartI"));
//        i2 = Integer.parseInt(matcher.group("indexEndI"));
//        j1 = Integer.parseInt(matcher.group("indexStartJ"));
//        j2 = Integer.parseInt(matcher.group("indexEndJ"));
//        if (!isValidCoordinate(i1, j1) || !isValidCoordinate(i2, j2))
//            return Output.invalidCoordinate;
//        if (gameMap.getTile(i1, j1).getCombatUnits() == null)
//            return Output.NO_EXISTING_COMBAT_UNITS;
//        return movementController.moveUnits(gameMap.getTile(i1, j1), gameMap.getTile(i2, j2), gameMap.getTile(i1, j1).getCombatUnits(), player);
//    }
//
//    public Output moveCivilian(Matcher matcher, GameMap gameMap, Player player) {
//        int i1, j1, i2, j2;
//        i1 = Integer.parseInt(matcher.group("indexStartI"));
//        i2 = Integer.parseInt(matcher.group("indexEndI"));
//        j1 = Integer.parseInt(matcher.group("indexStartJ"));
//        j2 = Integer.parseInt(matcher.group("indexEndJ"));
//        if (!isValidCoordinate(i1, j1) || !isValidCoordinate(i2, j2))
//            return Output.invalidCoordinate;
//        if (gameMap.getTile(i1, j1).getNoneCombatUnits() == null)
//            return Output.NO_EXISTING_NONE_COMBAT_UNITS;
//        return movementController.moveUnits(gameMap.getTile(i1, j1), gameMap.getTile(i2, j2), gameMap.getTile(i1, j1).getNoneCombatUnits(), player);
//    }


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

    public Output selectBuilder(Matcher matcher, Player player, GameMap gameMap) {
        int iCoordinate = Integer.parseInt(matcher.group("iCoordinate"));
        int jCoordinate = Integer.parseInt(matcher.group("jCoordinate"));
        if (!isValidCoordinate(iCoordinate, jCoordinate))
            return Output.invalidCoordinate;
        if (gameMap.getTile(iCoordinate, jCoordinate).getNoneCombatUnits() == null)
            return Output.NO_EXISTING_BUILDER;
        if (gameMap.getTile(iCoordinate, jCoordinate).getNoneCombatUnits().getUnitNameEnum() != UnitNameEnum.WORKER)
            return Output.NO_EXISTING_BUILDER;
        if (gameMap.getTile(iCoordinate, jCoordinate).getNoneCombatUnits().getPlayer() != player)
            return Output.BUILDER_NOT_YOURS;
        return null;
    }

    public Output selectCombatUnit(Matcher matcher, Player player, GameMap gameMap) {
        int iCoordinate = Integer.parseInt(matcher.group("iCoordinate"));
        int jCoordinate = Integer.parseInt(matcher.group("jCoordinate"));
        if (!isValidCoordinate(iCoordinate, jCoordinate))
            return Output.invalidCoordinate;
        if (gameMap.getTile(iCoordinate, jCoordinate).getCombatUnits() == null)
            return Output.NO_EXISTING_COMBAT_UNITS;
        if (gameMap.getTile(iCoordinate, jCoordinate).getCombatUnits().getUnitNameEnum() == UnitNameEnum.SETTLER
                || gameMap.getTile(iCoordinate, jCoordinate).getCombatUnits().getUnitNameEnum() == UnitNameEnum.WORKER)
            return Output.NO_EXISTING_COMBAT_UNITS;
        if (gameMap.getTile(iCoordinate, jCoordinate).getCombatUnits().getPlayer() != player)
            return Output.COMBAT_UNIT_NOT_YOURS;
        return null;
    }


    public Output createCity(String name, NoneCombatUnits settler, Player player, ArrayList<Player> players) {
        if (!isValidCityName(name))
            return Output.INVALID_CITY_NAME;
        if (!isNewCityName(name, players))
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
        if (player.getCityByName(cityName) == null)
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

    public void increaseHappiness(Matcher matcher, Player player) {
        int amount = Integer.parseInt(matcher.group("amount"));
        if (amount > 0) {
            playGameMenuController.increaseHappiness(player, amount);
        }
    }

    public void increaseFood(Matcher matcher, Player player) {
        int amount = Integer.parseInt(matcher.group("amount"));
        String cityName = matcher.group("cityName");
        City city = player.getCityByName(cityName);
        if (city != null && amount > 0) {
            playGameMenuController.increaseFood(city, amount);
        }
    }

    public Output showCityFood(Matcher matcher, Player player) {
        String cityName = matcher.group("cityName");
        if (player.getCityByName(cityName) == null)
            return Output.INVALID_CITY;
        return null;
    }

    public void buyTechnology(Matcher matcher, Player player) {
        String technology = matcher.group("technology");
        TechEnum techEnum = Tech.getEnumByString(technology);
        if (techEnum == null) return;
        if (player.getFullyResearchedTechByEnum(techEnum) == null) {
            player.getFullyResearchedTechs().add(new Tech(techEnum));
            if (player.getTechInResearch().getTechName() == techEnum) player.setTechInResearch(null);
            if (player.getResearchedTechByEnum(techEnum) != null)
                player.getResearchedTechs().remove(techEnum);
        }
    }

    public void increaseMovement(Matcher matcher, Player player, GameMap gameMap) {
        int iCoordinate = Integer.parseInt(matcher.group("iCoordinate"));
        int jCoordinate = Integer.parseInt(matcher.group("jCoordinate"));
        int amount = Integer.parseInt(matcher.group("amount"));
        String unitName = matcher.group("name");
        if (!isValidCoordinate(iCoordinate, jCoordinate)) return;
        Tile tile = gameMap.getTile(iCoordinate, jCoordinate);
        if (amount > 0)
            if (tile.getCombatUnits() != null
                    && unitName.equals(tile.getCombatUnits().getUnitNameEnum().getName()))
                tile.getCombatUnits().setMovement(tile.getCombatUnits().getMovement() + amount);
            else if (tile.getNoneCombatUnits() != null
                    && unitName.equals(tile.getNoneCombatUnits().getUnitNameEnum().getName()))
                tile.getNoneCombatUnits().setMovement(tile.getNoneCombatUnits().getMovement() + amount);
    }

    public void increaseScience(Matcher matcher, Player player) {
        int amount = Integer.parseInt(matcher.group("amount"));
        if (amount > 0) player.setScience(player.getTurnScience() + amount);
    }

    public void attachCity(Matcher matcher, Player player, ArrayList<Player> players) {
        String cityName = matcher.group("cityName");
        City city = null;
        Player owner = null;
        for (Player otherPlayer : players) {
            for (City tempCity : otherPlayer.getCities())
                if (tempCity.getName().equals(cityName)) {
                    city = tempCity;
                    owner = otherPlayer;
                }
        }
        if (city != null)
            player.attachCity(city, owner);
    }

    public Output loseCheatCode(Player player) {
        player.setGold(0);
        player.setScience(0);
        player.setTechInResearch(null);
        player.getFullyResearchedTechs().clear();
        return Output.LOSE_CHEAT_CODE;
    }

    public Output buildInCity(Matcher matcher, Player player, boolean instant) {
        City city = player.getCityByName(matcher.group("cityName"));
        UnitNameEnum unitName = UnitNameEnum.valueOfLabel(matcher.group("build"));
        BuildingEnum buildingName = BuildingEnum.valueOfLabel(matcher.group("build"));

        if (city == null) return Output.INVALID_CITY_NAME;
        if (unitName == null && buildingName == null) return Output.INVALID_BUILD_NAME;
        if (city.getBeingBuild() != null) return Output.CITY_IS_BUSY;
        if (unitName != null) {
            if (unitName.getTechnologyRequired() != null && player.getFullyResearchedTechByEnum(unitName.getTechnologyRequired()) == null)
                return Output.YOUR_TECH_IS_BEHIND;
            if (unitName.getResourcesRequired() != null && player.getAvailableResourcesByEnum(unitName.getResourcesRequired()) == null)
                return Output.DONT_HAVE_THE_NEEDED_RESOURCES;
            if (instant && unitName.getCost() > player.getGold()) return Output.NOT_ENOUGH_GOLD;

            if (instant) {
                if (unitName.getName().equals("worker")) {
                    playGameMenuController.createBuilder(player, city, unitName, "fast");
                } else if (unitName.getName().equals("settler")) {
                    playGameMenuController.createCivilian(player, city, unitName, "fast");
                } else {
                    playGameMenuController.createCombatUnit(player, city, unitName, "fast");
                }
                return Output.UNIT_CREATED;
            } else {
                if (unitName.getName().equals("worker")) {
                    playGameMenuController.createBuilder(player, city, unitName, "slow");
                } else if (unitName.getName().equals("settler")) {
                    playGameMenuController.createCivilian(player, city, unitName, "slow");
                } else {
                    playGameMenuController.createCombatUnit(player, city, unitName, "slow");
                }
                return Output.UNIT_GETTING_CREATED;
            }
        } else if (buildingName != null) {
            if (buildingName.getTechEnum() != null && player.getFullyResearchedTechByEnum(buildingName.getTechEnum()) == null)
                return Output.YOUR_TECH_IS_BEHIND;
            if (instant && buildingName.getCost() > player.getGold()) return Output.NOT_ENOUGH_GOLD;
            if (instant) {
                playGameMenuController.createBuilding(player, city, buildingName, "fast");
                return Output.BUILDING_CREATED;
            } else {
                playGameMenuController.createBuilding(player, city, buildingName, "slow");
                return Output.BUILDING_GETTING_CREATED;
            }
        }
        return null;
    }

    public Output buyCityTile(Player player, Matcher matcher, GameMap gameMap, ArrayList<Player> players) {
        String cityName = matcher.group("cityName");
        int iCoordinate = Integer.parseInt(matcher.group("iCoordinate"));
        int jCoordinate = Integer.parseInt(matcher.group("jCoordinate"));
        if (!isValidCoordinate(iCoordinate, jCoordinate)) return Output.invalidCoordinate;
        City city = player.getCityByName(cityName);
        if (city == null) return Output.INVALID_CITY;
        Tile tile = gameMap.getTile(iCoordinate, jCoordinate);
        if (Tile.hasOwner(tile, players)) return Output.INVALID_TILE;
        if (!player.canBuyTile(tile, gameMap, city)) return Output.INVALID_TILE;
        if (player.getGold() < (50 + 30 * player.getBoughtTilesNumber())) return Output.NOT_ENOUGH_GOLD;

        player.setGold(player.getGold() - (50 + 30 * player.getBoughtTilesNumber()));
        player.setBoughtTilesNumber(player.getBoughtTilesNumber() + 1);
        city.getTiles().add(tile);
        return Output.BUY_TILE_SUCCESSFULLY;
    }

    public Output removeCity(Matcher matcher, Player player) {
        String cityName = matcher.group("cityName");
        City city = player.getCityByName(cityName);
        if (city == null) return Output.INVALID_CITY;
        player.setGold(player.getGold() + city.getTiles().size() * 30);
        player.getCities().remove(city);
        return Output.REMOVE_CITY;
    }

    public Output assignForPlayer(Matcher matcher, Player player) {
        String mode = matcher.group("type");
        CitizenController.assignCitizensOfPlayer(player, mode);
        return Output.ALL_CITIZENS_ASSIGNED_SUCCESSFULLY;
    }

    public Output assignForCity(Matcher matcher, Player player) {
        String mode = matcher.group("type");
        String cityName = matcher.group("cityName");
        City tempCity = player.getCityByName(cityName);
        if (tempCity == null) return Output.INVALID_CITY;
        CitizenController.assignCitizensOfCity(tempCity, mode);
        return Output.ALL_CITIZENS_ASSIGNED_SUCCESSFULLY;

    }

    public Output assignACitizenOfACityToATile(Matcher matcher, Player player, GameMap gameMap) {
        String cityName = matcher.group("cityName");
        City tempCity = player.getCityByName(cityName);
        if (tempCity == null) return Output.INVALID_CITY;
        int iCoordinate = Integer.parseInt(matcher.group("iCoordinate"));
        int jCoordinate = Integer.parseInt(matcher.group("jCoordinate"));
        if (!isValidCoordinate(iCoordinate, jCoordinate)) return Output.invalidCoordinate; // isValid درسته ؟!؟
        return CitizenController.assignCitizenToATile(tempCity, gameMap.getTile(iCoordinate, jCoordinate));
    }

    public Output removeACitizenOfACityFromATile(Matcher matcher, Player player, GameMap gameMap) {
        String cityName = matcher.group("cityName");
        City tempCity = player.getCityByName(cityName);
        if (tempCity == null) return Output.INVALID_CITY;
        int iCoordinate = Integer.parseInt(matcher.group("iCoordinate"));
        int jCoordinate = Integer.parseInt(matcher.group("jCoordinate"));
        if (!isValidCoordinate(iCoordinate, jCoordinate)) return Output.invalidCoordinate; // isValid درسته ؟!؟
        return CitizenController.removeCitizenFromATile(tempCity, gameMap.getTile(iCoordinate, jCoordinate));
    }

    public Output attackUnit(CombatUnits combatUnit, Tile toAttackTile, Player player) {
        if (combatUnit.isSleeping()) return Output.UNIT_IS_SLEEPING;
        return combatController.attackUnits(combatUnit.getPosition(), toAttackTile, player);
    }

    public Output attackCity(CombatUnits combatUnit, City city, Player player, ArrayList<Player> players) {
        if (city == null) return Output.INVALID_CITY;
        return combatController.attackToCity(combatUnit.getPosition(), city, player, players);
    }

    public Output isValidCity(Matcher matcher, Player player) {
        String cityName = matcher.group("cityName");
        if (player.getCityByName(cityName) == null) return Output.INVALID_CITY;
        return null;
    }

    public Output hasMadeCity(Player player) {
        if (player.getCities().size() == 0) return Output.NO_CITY;
        return null;
    }

    public Output sleepUnit(Unit unit) {
        if (unit.isSleeping()) return Output.ALREADY_SLEEP;
        unit.setSleeping(true);
        unit.setAlert(false);
        return Output.COMMAND_SUCCESSFUL;
    }

    public Output wakeUnit(Unit unit) {
        if (!unit.isSleeping() && !unit.isAlert()) return Output.UNIT_IS_NOT_SLEEP;
        unit.setSleeping(false);
        unit.setAlert(false);
        return Output.COMMAND_SUCCESSFUL;
    }

    public Output alertUnit(Unit unit) {
        if (unit.isSleeping()) return Output.UNIT_IS_SLEEPING;
        if (!unit.isAlert()) return Output.ALREADY_ALERT;
        unit.setSleeping(false);
        unit.setAlert(true);
        return Output.COMMAND_SUCCESSFUL;
    }

    public Output garrisonCombatUnit(CombatUnits combatUnit) {
        if (combatUnit.isSleeping()) return Output.UNIT_IS_SLEEPING;
        City city;
        if ((city = SearchController.searchCityWithCenter(combatUnit.getPosition())) == null)
            return Output.NOT_ON_CITY_CENTER;
        if (city.getGarrison() != null) return Output.CITY_HAS_GARRISON;
        city.setGarrison(combatUnit);
        combatUnit.setGarrison(true);
        return Output.COMMAND_SUCCESSFUL;
    }

    public Output fortifyCombatUnit(CombatUnits combatUnit) {
        if (combatUnit.isSleeping()) return Output.UNIT_IS_SLEEPING;
        combatUnit.setFortified(true);
        return Output.COMMAND_SUCCESSFUL;
    }

    public Output deleteCombatUnit(CombatUnits combatUnit) {
        Gold.addGold(combatUnit.getPlayer(), combatUnit.getUnitNameEnum().getCost() * 8 / 10);
        combatUnit.died();
        return Output.COMMAND_SUCCESSFUL;
    }

    public Output pillageTile(CombatUnits combatUnit) {
        if (combatUnit.isSleeping()) return Output.UNIT_IS_SLEEPING;
        combatController.pillage(combatUnit);
        return Output.COMMAND_SUCCESSFUL;
    }


    public Output clearLand(BuilderUnit builder, Player player) {
        return builderController.removeTileFeature(player, builder);
    }

    public Output implementImprovement(Matcher matcher, BuilderUnit builder, Player player) {
        TileImprovementEnum tileImprovement = TileImprovementEnum.valueOfLabel(matcher.group("improvementName"));
        if (tileImprovement == null) return Output.NOT_A_VALID_IMPROVEMENT;
        return builderController.improveTile(player, builder, tileImprovement);
    }

    public Output repairImprovement(BuilderUnit builder, Player player) {
        return builderController.repairImprovement(player, builder);
    }

    public Output repairBuilding(Matcher matcher, BuilderUnit builder, Player player) {
        // not for this phase
        return builderController.repairBuilding(player, builder);
    }

    public Output buildRoad(BuilderUnit builder, GameMap gamemap, Player player) {
        return builderController.makeARoad(player, builder);
    }

    public Output cityAttack(Matcher matcher, Player player, ArrayList<Player> players, GameMap gameMap) {
        City city = SearchController.findCity(players, matcher.group("cityName"));
        if (city == null) return Output.INVALID_CITY;
        if (SearchController.findPlayerOfCity(players, city) != player) return Output.CITY_NOT_YOURS;
        int iCoordinate = Integer.parseInt(matcher.group("iCoordinate"));
        int jCoordinate = Integer.parseInt(matcher.group("jCoordinate"));
        if (!isValidCoordinate(iCoordinate, jCoordinate)) return Output.invalidCoordinate;
        combatController.attackFromCity(city, gameMap.getTile(iCoordinate, jCoordinate), player);
        return Output.attackSuccessFull;
    }

    public Output deleteUnit(Unit unit) {
        Gold.addGold(unit.getPlayer(), unit.getUnitNameEnum().getCost() * 8 / 10);
        unit.getPlayer().getUnits().remove(unit);
        if (unit instanceof CombatUnits) {// garrisons delete
            unit.getPosition().setCombatUnits(null);
        } else {
            unit.getPosition().setNoneCombatUnits(null);
        }
        return Output.COMMAND_SUCCESSFUL;
    }

    public Output siegeSetup(CombatUnits combatUnit) {
        if (!(combatUnit instanceof SiegeUnit)) return Output.NOT_A_SIEGE;
        ((SiegeUnit) combatUnit).setSetUp(true);
        return Output.SETUP_SIEGE_SUCCESSFULLY;
    }

    public Output addRoute(int iCoordinate, int jCoordinate, GameMap gamemap, Unit unit, Player player) {
        if (!isValidCoordinate(iCoordinate, jCoordinate)) return Output.invalidCoordinate;
        return movementController.addASavedRoute(gamemap.getTile(iCoordinate, jCoordinate), unit, player);
    }

    public Output resetRoute(Unit unit) {
        unit.setSavedRoute(null);
        return Output.COMMAND_SUCCESSFUL;
    }

    public Output moveFromRoute(Unit unit) {
        return movementController.moveFromSavedRoute(unit);
    }

    public Output sleepCombatUnit(CombatUnits combatUnit) {
        if (combatUnit.isSleeping()) return Output.ALREADY_SLEEP;
        combatUnit.setSleeping(true);
        combatUnit.setAlert(false);
        return Output.COMMAND_SUCCESSFUL;
    }

    public Output wakeCombatUnit(CombatUnits combatUnit) {
        if (!combatUnit.isSleeping() || !combatUnit.isAlert()) return Output.UNIT_IS_NOT_SLEEP;
        combatUnit.setSleeping(false);
        combatUnit.setAlert(false);
        return Output.COMMAND_SUCCESSFUL;
    }

    public Output alertCombatUnit(CombatUnits combatUnit) {
        if (!combatUnit.isAlert()) return Output.ALREADY_ALERT;
        combatUnit.setSleeping(false);
        combatUnit.setAlert(true);
        return Output.COMMAND_SUCCESSFUL;
    }

    public Output clearLand(BuilderUnit builder) {
        // todo : clear land ----
        return Output.COMMAND_SUCCESSFUL;
    }

    public Output implementImprovement(Matcher matcher, BuilderUnit builder) {

        return Output.COMMAND_SUCCESSFUL;
    }

    public Output repairImprovement(BuilderUnit builder) {

        return Output.COMMAND_SUCCESSFUL;
    }

    public Output selectUnitByNumber(Matcher matcher, Player player) {
        int number = Integer.parseInt(matcher.group("number"));
        if (player.getUnits().size() < number || number <= 0) return Output.INVALID_NUMBER;
        return null;
    }

    public boolean attackToATile(CombatUnits combatUnits, Tile selectedTile) {
        City toAttackCity = getCityToAttack(selectedTile);
        if (toAttackCity != null) {
            new PopupMessage(Alert.AlertType.ERROR, attackCity(combatUnits, toAttackCity, combatUnits.getPlayer(), PlayGamePage.getInstance().getPlayers()).toString());
            return true;
        }
        if (selectedTile.getCombatUnits() != null) {
            new PopupMessage(Alert.AlertType.ERROR, attackUnit(combatUnits, selectedTile, combatUnits.getPlayer()).toString());
            return true;
        }
        return false;
    }

    public City getCityToAttack(Tile selectedTile) {
        for (Player player : PlayGamePage.getInstance().getPlayers())
            if (player != PlayGamePage.getInstance().getThisTurnPlayer())
                for (City city : player.getCities())
                    if (city.getCenter() == selectedTile)
                        return city;
        return null;
    }
}