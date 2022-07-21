package com.example.project.controllers.GameControllers;

import com.example.project.controllers.GameControllers.GameSettingController;
import com.example.project.controllers.GlobalChatController;
import com.example.project.models.*;
import com.example.project.models.Building.Building;
import com.example.project.models.Building.BuildingEnum;
import com.example.project.models.Technology.Tech;
import com.example.project.models.Technology.TechEnum;
import com.example.project.models.Tile.Tile;
import com.example.project.models.Units.Combat.CombatUnit;
import com.example.project.models.Units.Nonecombat.BuilderUnit;
import com.example.project.models.Units.Nonecombat.NoneCombatUnit;
import com.example.project.models.Units.UnitNameEnum;

import java.util.ArrayList;
import java.util.regex.Matcher;

public class PlayGameMenuController {
    private GameMap gameMap;
    private ArrayList<Player> players;

    public PlayGameMenuController(GameMap gameMap, ArrayList<Player> players) {
        setGameMap(gameMap);
        setPlayers(players);
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public GameMap getGameMap() {
        return gameMap;
    }

    public void setGameMap(GameMap gameMap) {
        this.gameMap = gameMap;
    }

    public NoneCombatUnit findSettler(Matcher matcher, Player player) {
        int iCoordinate = Integer.parseInt(matcher.group("iCoordinate"));
        int jCoordinate = Integer.parseInt(matcher.group("jCoordinate"));
        if (gameMap.getTile(iCoordinate, jCoordinate).getNoneCombatUnits() == null)
            return null;
        if (gameMap.getTile(iCoordinate, jCoordinate).getNoneCombatUnits().getUnitNameEnum() != UnitNameEnum.SETTLER)
            return null;
        if (gameMap.getTile(iCoordinate, jCoordinate).getNoneCombatUnits().getPlayer() != player)
            return null;
        return gameMap.getTile(iCoordinate, jCoordinate).getNoneCombatUnits();
    }

    public BuilderUnit findBuilder(Matcher matcher, Player player) {
        int iCoordinate = Integer.parseInt(matcher.group("iCoordinate"));
        int jCoordinate = Integer.parseInt(matcher.group("jCoordinate"));
        if (gameMap.getTile(iCoordinate, jCoordinate).getNoneCombatUnits() == null)
            return null;
        if (gameMap.getTile(iCoordinate, jCoordinate).getNoneCombatUnits().getUnitNameEnum() != UnitNameEnum.WORKER)
            return null;
        if (gameMap.getTile(iCoordinate, jCoordinate).getNoneCombatUnits().getPlayer() != player)
            return null;
        return (BuilderUnit) gameMap.getTile(iCoordinate, jCoordinate).getNoneCombatUnits();
    }

    public CombatUnit findCombatUnit(Matcher matcher, Player player) {
        int iCoordinate = Integer.parseInt(matcher.group("iCoordinate"));
        int jCoordinate = Integer.parseInt(matcher.group("jCoordinate"));
        if (gameMap.getTile(iCoordinate, jCoordinate).getCombatUnits() == null)
            return null;
        if (gameMap.getTile(iCoordinate, jCoordinate).getCombatUnits().getUnitNameEnum() == UnitNameEnum.SETTLER
                || gameMap.getTile(iCoordinate, jCoordinate).getCombatUnits().getUnitNameEnum() == UnitNameEnum.WORKER)
            return null;
        if (gameMap.getTile(iCoordinate, jCoordinate).getCombatUnits().getPlayer() != player)
            return null;
        return gameMap.getTile(iCoordinate, jCoordinate).getCombatUnits();
    }

    public void createCity(NoneCombatUnit settler, Player player) {
        Tile tile = settler.getPosition();
        City newCity = new City(tile, this.gameMap, player.getUser().getUsername() + player.getCities().size());
        player.getCities().add(newCity);
        tile.setNoneCombatUnits(null);
        player.getUnits().remove(settler);
        new Food(newCity);
    }

    public int nextPlayer(int number, ArrayList<Player> players) {
        number++;
        if (number == players.size())
            number = 0;
        return number;
    }

    public void research(TechEnum technologyEnum, Player player) {
        if (player.getResearchedTechByEnum(technologyEnum) == null) {
            Tech technology = new Tech(technologyEnum);
            player.setTechInResearch(technology);
            player.getResearchedTechs().add(technology);
            return;
        }
        Tech technology = player.getResearchedTechByEnum(technologyEnum);
        player.setTechInResearch(technology);
    }

    public void startGame(ArrayList<Player> players, int difficulty) {
        for (int i = 0; i < players.size(); i++) {
            players.get(i).startGame(difficulty);
        }
    }

    public void increaseTurn(Player player, int amount) {
        for (int i = 0; i < amount; i++)
            player.endTurn(this.gameMap, true);
    }

    public void increaseGold(Player player, int amount) {
        Gold.addGold(player, amount);
    }

    public void increaseFood(City city, int amount) {
        Food.editSavedFood(city, amount);
    }

    public void increaseHappiness(Player player, int amount) {
        Happiness.addPlayerHappiness(player, amount);
    }

    public void createCombatUnit(Player player, City city, UnitNameEnum unitNameEnum, String mode) {
        if (mode.equals("slow"))
            city.setBeingBuild(new BeingBuild(new CombatUnit(city.getCenter(), unitNameEnum, player)));
        if (mode.equals("fast")) {
            CombatUnit combatUnit = new CombatUnit(city.getCenter(), unitNameEnum, player);
            player.getUnits().add(combatUnit);
            city.getCenter().setCombatUnits(combatUnit);
            player.setGold(player.getGold() - unitNameEnum.getCost());
        }
    }

    public void createCivilian(Player player, City city, UnitNameEnum unitNameEnum, String mode) {
        if (mode.equals("slow"))
            city.setBeingBuild(new BeingBuild(new NoneCombatUnit(city.getCenter(), unitNameEnum, player)));
        if (mode.equals("fast")) {
            NoneCombatUnit noneCombatUnit = new NoneCombatUnit(city.getCenter(), unitNameEnum, player);
            player.getUnits().add(noneCombatUnit);
            city.getCenter().setNoneCombatUnits(noneCombatUnit);
            player.setGold(player.getGold() - unitNameEnum.getCost());
        }
    }

    public void createBuilder(Player player, City city, UnitNameEnum unitNameEnum, String mode) {
        if (mode.equals("slow"))
            city.setBeingBuild(new BeingBuild(new BuilderUnit(city.getCenter(), unitNameEnum, player)));
        if (mode.equals("fast")) {
            BuilderUnit builderUnit = new BuilderUnit(city.getCenter(), unitNameEnum, player);
            player.getUnits().add(builderUnit);
            city.getCenter().setNoneCombatUnits(builderUnit);
            player.setGold(player.getGold() - unitNameEnum.getCost());
        }
    }

    public void createBuilding(Player player, City city, BuildingEnum buildingName, String mode) {
        if (mode.equals("fast")) {
            city.addBuilding(new Building(buildingName));
            player.setGold(player.getGold() - buildingName.getCost());
        } else if (mode.equals("slow")) {
            city.setBeingBuild(new BeingBuild(new Building(buildingName)));
        }
    }

    public boolean haveEnoughGoldForUnit(Player player, UnitNameEnum unitNameEnum) {
        return player.getGold() >= unitNameEnum.getCost();
    }

    public boolean haveEnoughGoldForBuilding(Player player, BuildingEnum buildingEnum) {
        return player.getGold() >= buildingEnum.getCost();
    }

    public boolean isRuinTileSeenBefore(Tile tile) {
        return Game.getInstance().getThisTurnPlayer().getRuinTileSeen().contains(tile);
    }

    public void ruinFound(Tile tile) {
        Player player = Game.getInstance().getThisTurnPlayer();
        player.getRuinTileSeen().add(tile);
        player.setGold(player.getGold() + 500);
        player.setScience(player.getScience() + 500);
        Happiness.setHappiness(player, 10);
    }

    public void destroyCity(City city) {
        Game.getInstance().getThisTurnPlayer().removeCity(city);
    }


    public String buyCityTile(Player player, City city, String xCord, String yCord) {
        Tile[][] map = Game.getInstance().getGameMap().getMap();
        GameMap gameMap = Game.getInstance().getGameMap();
        Tile tile;
        int x = Integer.parseInt(xCord), y = Integer.parseInt(yCord);
        if (x < 0 || y < 0 || x > map.length || y > map[0].length)
            return "invalid coordination";
        tile = map[x][y];
        if (tile == null) return "invalid tile";
        boolean isValid = false;
        for (Tile cityTile : city.getTiles()) {
            if (Tile.isNeighbor(gameMap.getIndexI(cityTile), gameMap.getIndexJ(cityTile), x, y)) isValid = true;
        }
        if (!isValid) return "tile should be near your city";
        if (city.getTiles().contains(tile)) return "this tile is already yours";
        if (player.getGold() < 50) return "not enough gold!";
        player.setGold(player.getGold() - 50);
        city.getTiles().add(tile);
        return "ok";
    }

    public void changeAutoSaveType(boolean selected, Player player) {
        if (selected) player.setAutoSaveType(AutoSaveType.EVERY_10_MIN);
        else player.setAutoSaveType(null);
    }
}