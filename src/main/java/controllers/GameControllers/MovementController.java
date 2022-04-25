package controllers.GameControllers;

import controllers.Output;
import models.GameMap;
import models.Player;
import models.River;
import models.Tile.Tile;
import models.Units.Combat.CombatUnits;
import models.Units.Nonecombat.NoneCombatUnits;
import models.Units.Unit;

import java.util.ArrayList;

public class MovementController {
    private GameMap gameMap;

    public MovementController(GameMap gameMap) {
        this.gameMap = gameMap;
    }


    // TODO : fix bugs --> movement reset / invalid i and j / ro routes !
    public ArrayList<ArrayList<Tile>> returnRoutes(Tile start, Tile end, Unit unit) {
        ArrayList<ArrayList<Tile>> possibleRoutes = new ArrayList<>();
        int startIndexI = gameMap.getIndexI(start);
        int startIndexJ = gameMap.getIndexJ(start);
        int endIndexI = gameMap.getIndexI(end);
        int endIndexJ = gameMap.getIndexJ(end);
        makePossibleRoutes(startIndexI, startIndexJ, endIndexI, endIndexJ, possibleRoutes, new ArrayList<>(), unit.getMovementPoints());
        return possibleRoutes;
    }

    public ArrayList<Tile> returnBestMovingRoute(ArrayList<ArrayList<Tile>> possibleRoutes) {
        ArrayList<Tile> bestRoute = null;
        Double minMovementCost = Double.POSITIVE_INFINITY, movementCost;
        for (ArrayList<Tile> possibleRoute : possibleRoutes) {
            movementCost = returnMovementCost(possibleRoute);
            if (movementCost < minMovementCost) {
                minMovementCost = movementCost;
                bestRoute = possibleRoute;
            }
        }
        return bestRoute;
    }

    private Double returnMovementCost(ArrayList<Tile> possibleRoute) {
        Double movementCost = 0.0;
        for (Tile tile : possibleRoute) {
            movementCost += tile.getMp();
        }
        return movementCost;
    }

    public Output moveUnits(Tile start, Tile end, Unit unit, Player player) {
        if (start != unit.getPosition()) return Output.startTileAndUnitDontMatchUp;
        if (unit.getPlayer() != player) return Output.youDontOwnThisUnit;
        if (end.getCombatUnits() != null && end.getCombatUnits().getPlayer() != player)
            return Output.enemyCombatUnitOnThatTile;
        if (end.getNoneCombatUnits() != null && end.getNoneCombatUnits().getPlayer() != player)
            return Output.enemyNonCombatUnitOnThatTile;
        if ((end.getNoneCombatUnits() != null && unit.isACivilian()) || (end.getCombatUnits() != null && unit.isACombatUnit()))
            return Output.youAlreadyHaveATroopThere;
        if (player.getGameMap()[this.gameMap.getIndexI(end)][this.gameMap.getIndexJ(end)] == null)
            return Output.FOG_OF_WAR;

        ArrayList<Tile> route = returnBestMovingRoute(returnRoutes(start, end, unit));
        if (route == null) return Output.NOT_ENOUGH_MOVEMENT_POINTS;

        // TODO : unit.movement needs someThing to reset from for the next turn / like unit.movement = unit.name.getMovement
        unit.setMovement(unit.getMovementPoints() - routeCost(route));
        if (unit.isACivilian()) {
            start.setNoneCombatUnits(null);
            end.setNoneCombatUnits((NoneCombatUnits) unit);
            unit.setPosition(end);
        }
        if (unit.isACombatUnit()) {
            start.setCombatUnits(null);
            end.setCombatUnits((CombatUnits) unit);
            unit.setPosition(end);
        }
        return Output.movedSuccessfully;
    }

    private Double routeCost(ArrayList<Tile> route) {
        Double sum = 0D;
        for (Tile tile : route) {
            sum += tile.getMp();
        }
        return sum;
    }

    public ArrayList<Tile> attackingRoute(Tile start, Tile end, Unit unit) {
        // TODO : check if start unit can attack !
        // TODO : end should be an enemy
        return returnBestAttackingRoute(returnRoutes(start, end, unit), unit);
    }

    private ArrayList<Tile> returnBestAttackingRoute(ArrayList<ArrayList<Tile>> possibleRoutes, Unit unit) {
        // TODO : how does the troopBoost system work ? & melee attack tile ?!
        ArrayList<Tile> bestRoute = null;
        Double minMovementCost = Double.POSITIVE_INFINITY, movementCost;
        Integer range = unit.getRange();
        if (range == null) range = 1;
        for (ArrayList<Tile> possibleRoute : possibleRoutes) {
            movementCost = 0.0;
            for (int i = 0; i < possibleRoute.size() - range; i++) {
                movementCost += possibleRoute.get(i).getMp();
            }
            if (movementCost < minMovementCost
                    || (movementCost == minMovementCost
                    && bestRoute.get(bestRoute.size() - range - 1).getCombatBonus() < possibleRoute.get(possibleRoute.size() - range - 1).getCombatBonus())) {
                minMovementCost = movementCost;
                bestRoute = possibleRoute;
            }
        }
        return bestRoute;
    }


    public void makePossibleRoutes(int i1, int j1, int i2, int j2, ArrayList<ArrayList<Tile>> routes, ArrayList<Tile> route, double movement) {
        // TODO : split the function
        if (i1 == i2 && j1 == j2) {
            routes.add(route);
            return;
        }
        if (movement <= 0) return;
        ArrayList<Tile> clonedRoute;
        Tile tempTile;
        Double tempMovement;
        tempTile = gameMap.getTile(i1 + 1, j1);
        if (tempTile != null && checkIfItsPossible(tempTile, movement)) {
            clonedRoute = (ArrayList) route.clone();
            clonedRoute.add(tempTile);
            tempMovement = movement - tempTile.getMp();
            if (River.hasRiver(gameMap.getTile(i1, j1), tempTile)) tempMovement = 0D;
            makePossibleRoutes(i1 + 1, j1, i2, j2, routes, clonedRoute, tempMovement);
        }
        tempTile = gameMap.getTile(i1 - 1, j1);
        if (tempTile != null && checkIfItsPossible(tempTile, movement)) {
            clonedRoute = (ArrayList) route.clone();
            clonedRoute.add(tempTile);
            tempMovement = movement - tempTile.getMp();
            if (River.hasRiver(gameMap.getTile(i1, j1), tempTile)) tempMovement = 0D;
            makePossibleRoutes(i1 - 1, j1, i2, j2, routes, clonedRoute, tempMovement);
        }
        tempTile = gameMap.getTile(i1, j1 + 1);
        if (tempTile != null && checkIfItsPossible(tempTile, movement)) {
            clonedRoute = (ArrayList) route.clone();
            clonedRoute.add(tempTile);
            tempMovement = movement - tempTile.getMp();
            if (River.hasRiver(gameMap.getTile(i1, j1), tempTile)) tempMovement = 0D;
            makePossibleRoutes(i1, j1 + 1, i2, j2, routes, clonedRoute, tempMovement);
        }
        tempTile = gameMap.getTile(i1, j1 - 1);
        if (tempTile != null && checkIfItsPossible(tempTile, movement)) {
            clonedRoute = (ArrayList) route.clone();
            clonedRoute.add(tempTile);
            tempMovement = movement - tempTile.getMp();
            if (River.hasRiver(gameMap.getTile(i1, j1), tempTile)) tempMovement = 0D;
            makePossibleRoutes(i1, j1 - 1, i2, j2, routes, clonedRoute, tempMovement);
        }
        tempTile = gameMap.getTile(i1 + 1, j1 - 1);
        if (tempTile != null && checkIfItsPossible(tempTile, movement)) {
            clonedRoute = (ArrayList) route.clone();
            clonedRoute.add(tempTile);
            tempMovement = movement - tempTile.getMp();
            if (River.hasRiver(gameMap.getTile(i1, j1), tempTile)) tempMovement = 0D;
            makePossibleRoutes(i1 + 1, j1 - 1, i2, j2, routes, clonedRoute, tempMovement);
        }
        tempTile = gameMap.getTile(i1 + 1, j1 + 1);
        if (tempTile != null && checkIfItsPossible(tempTile, movement)) {
            clonedRoute = (ArrayList) route.clone();
            clonedRoute.add(tempTile);
            tempMovement = movement - tempTile.getMp();
            if (River.hasRiver(gameMap.getTile(i1, j1), tempTile)) tempMovement = 0D;
            makePossibleRoutes(i1 + 1, j1 + 1, i2, j2, routes, clonedRoute, tempMovement);
        }
    }

    public boolean checkIfItsPossible(Tile tile, Double movement) {
        if (tile.getMp() == Double.POSITIVE_INFINITY) return false;
        if (movement <= 0) return false;
        return true;
    }
}