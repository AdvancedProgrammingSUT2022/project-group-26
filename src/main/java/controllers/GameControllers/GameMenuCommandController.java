package controllers.GameControllers;

import controllers.Output;
import models.GameMap;
import models.Player;
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

    public int nextPlayer(int number, ArrayList<Player> players) {
        number++;
        if (number == players.size())
            number = 0;
        return number;
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

    public Output createCity(NoneCombatUnits settler, Player player, ArrayList<Player> players) {
        Tile settlerTile = settler.getPosition();
        for (int i = 0; i < players.size(); i++)
            if (players.get(i).hasTile(settlerTile))
                return Output.UNABLE_CREATE_CITY;
        playGameMenuController.createCity(settler, player);
        return Output.CITY_CREATED;
    }
}