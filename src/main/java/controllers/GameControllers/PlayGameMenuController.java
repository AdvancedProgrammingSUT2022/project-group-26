package controllers.GameControllers;

import controllers.Output;
import models.*;
import models.Technology.Tech;
import models.Technology.TechEnum;
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

    public void startGame(ArrayList<Player> players) {
        for (int i = 0; i < players.size(); i++) {
            players.get(i).startGame();
        }
    }

    public void increaseTurn(Player player, int amount) {
        for (int i = 0; i < amount; i++)
            player.endTurn(this.gameMap);
    }

    public void increaseGold(Player player, int amount) {
        Gold.addGold(player, amount);
    }

    public void increaseFood(City city, int amount){
        Food.addToSavedFood(city, amount);
    }

    public void increaseHappiness(Player player, int amount){
       // Happiness.
    }
}