package controllers.GameControllers;

import controllers.Output;
import models.City;
import models.GameMap;
import models.Player;
import models.Tile.Tile;
import models.Units.Nonecombat.NoneCombatUnits;
import models.Units.UnitNameEnum;

import java.util.ArrayList;
import java.util.regex.Matcher;

public class PlayGameMenuController {
    GameMap gameMap;
    ArrayList<Player> players;

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

    public void endGame() {

    }

    public void playATurn() {
    }

    public Output research(Matcher matcher, Object idk) {
        return null;
    }

    public Output buildInCity(Matcher matcher, Object idk) {
        return null;
    }

    public boolean canResearch(Object idk) {
        return true;
    }

    public boolean canBuild(Object idk) {
        return true;
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

    public void createCity(NoneCombatUnits settler, Player player) {
        Tile tile = settler.getPosition();
        City newCity = new City(tile, this.gameMap);
        player.getCities().add(newCity);
        tile.setNoneCombatUnits(null);
        player.getUnits().remove(settler);
    }


}