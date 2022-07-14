package com.example.project.controllers.GameControllers;

import com.example.project.models.*;
import com.example.project.models.Technology.Tech;
import com.example.project.models.Technology.TechEnum;
import com.example.project.models.Tile.Tile;
import com.example.project.models.Units.Combat.CombatUnits;
import com.example.project.models.Units.Nonecombat.BuilderUnit;
import com.example.project.models.Units.Nonecombat.NoneCombatUnits;
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

    public NoneCombatUnits findSettler(Matcher matcher, Player player) {
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

    public CombatUnits findCombatUnit(Matcher matcher, Player player) {
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

    public void createCity(NoneCombatUnits settler, Player player, String name) {
        Tile tile = settler.getPosition();
        City newCity = new City(tile, this.gameMap, name);
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
}