package controllers.GameControllers;

import controllers.Output;
import models.GameMap;
import models.Player;

import java.util.ArrayList;
import java.util.regex.Matcher;

public class GameMenuCommandController {

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
        if (gameMap.getTile(i1, j1).getCombatUnits() == null)
            return Output.NO_EXISTING_COMBAT_UNITS;
        // TODO: add Output to this function
        return movementController.moveUnits(gameMap.getTile(i1, j1), gameMap.getTile(i2, j2), gameMap.getTile(i1, j1).getCombatUnits(), player);
    }

    public void moveCivilian(Matcher matcher, GameMap gameMap, Player player) {
        MovementController movementController = new MovementController(gameMap);
        int i1, j1, i2, j2;
        i1 = Integer.parseInt(matcher.group("indexStartI"));
        i2 = Integer.parseInt(matcher.group("indexEndI"));
        j1 = Integer.parseInt(matcher.group("indexStartJ"));
        j2 = Integer.parseInt(matcher.group("indexEndJ"));
        movementController.moveUnits(gameMap.getTile(i1, j1), gameMap.getTile(i2, j2), gameMap.getTile(i1, j1).getNoneCombatUnits(), player);
    }

    public int nextPlayer(int number, ArrayList<Player> players) {
        number++;
        if (number == players.size())
            number = 0;
        return number;
    }
}