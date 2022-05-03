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


    public void moveFromSavedRoute(Unit unit) {
        ArrayList<Tile> route = unit.getSavedRoute();
        if (route == null) return;
        if (!checkIfIndexIsPossible(route, unit, route.size() - 1)) {
            unit.setSavedRoute(null);
            return;
        }
        Double movement = unit.getMovement();
        int index = -1;
        for (int i = 0; i < route.size(); i++) {
            if (movement <= 0) break;
            movement -= route.get(i).getMp();
            index = i;
        }
        while (true) {
            if (index < 0) return;
            if (!checkIfIndexIsPossible(route, unit, index)) {
                movement += route.get(index).getMp();
                index--;
            } else {
                break;
            }
        }
        if (movement < 0) movement = 0D;
        unit.setMovement(movement);
        changePlaces(unit.getPosition(), route.get(index), unit);
        route.subList(0, index + 1).clear();
    }

    private boolean checkIfIndexIsPossible(ArrayList<Tile> route, Unit unit, int index) {
        return (route.get(index).getCombatUnits() != null && unit.isACombatUnit())
                || (route.get(index).getNoneCombatUnits() != null && unit.isACivilian())
                || (route.get(index).getCombatUnits() != null && route.get(index).getCombatUnits().getPlayer() != unit.getPlayer())
                || (route.get(index).getNoneCombatUnits() != null && route.get(index).getNoneCombatUnits().getPlayer() != unit.getPlayer());
    }

    public void changePlaces(Tile start, Tile end, Unit unit) {
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
    }

    public Output addASavedRoute(Tile start, Tile end, Unit unit, Player player) {
        if (start != unit.getPosition()) return Output.startTileAndUnitDontMatchUp;
        if (unit.getPlayer() != player) return Output.youDontOwnThisUnit;
        if (end.getCombatUnits() != null && end.getCombatUnits().getPlayer() != player)
            return Output.enemyCombatUnitOnThatTile;
        if (end.getNoneCombatUnits() != null && end.getNoneCombatUnits().getPlayer() != player)
            return Output.enemyNonCombatUnitOnThatTile;
        if ((end.getNoneCombatUnits() != null && unit.isACivilian()) || (end.getCombatUnits() != null && unit.isACombatUnit()))
            return Output.youAlreadyHaveATroopThere;
        if (player.getGameMap().getMap()[this.gameMap.getIndexI(end)][this.gameMap.getIndexJ(end)] == null)
            return Output.FOG_OF_WAR;
        unit.setSavedRoute(returnBestMovingRoute(returnRoutes(start, end, unit, 5)));
        if (unit.getSavedRoute() == null) return Output.LONG_ROUTE;
        return Output.ADDED_ROUTE;
    }

    public ArrayList<ArrayList<Tile>> returnRoutes(Tile start, Tile end, Unit unit, int turns) {
        ArrayList<ArrayList<Tile>> possibleRoutes = new ArrayList<>();
        int startIndexI = gameMap.getIndexI(start);
        int startIndexJ = gameMap.getIndexJ(start);
        int endIndexI = gameMap.getIndexI(end);
        int endIndexJ = gameMap.getIndexJ(end);
        makePossibleRoutes(startIndexI, startIndexJ, endIndexI, endIndexJ, possibleRoutes, new ArrayList<>(), unit.getMaxMovement() * turns);
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


    public void makePossibleRoutes(int i1, int j1, int i2, int j2, ArrayList<ArrayList<Tile>> routes, ArrayList<Tile> route, double movement) {
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


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public Output moveUnits(Tile start, Tile end, Unit unit, Player player) {
        if (start != unit.getPosition()) return Output.startTileAndUnitDontMatchUp;
        if (unit.getPlayer() != player) return Output.youDontOwnThisUnit;
        if (end.getCombatUnits() != null && end.getCombatUnits().getPlayer() != player)
            return Output.enemyCombatUnitOnThatTile;
        if (end.getNoneCombatUnits() != null && end.getNoneCombatUnits().getPlayer() != player)
            return Output.enemyNonCombatUnitOnThatTile;
        if ((end.getNoneCombatUnits() != null && unit.isACivilian()) || (end.getCombatUnits() != null && unit.isACombatUnit()))
            return Output.youAlreadyHaveATroopThere;
        if (player.getGameMap().getMap()[this.gameMap.getIndexI(end)][this.gameMap.getIndexJ(end)] == null)
            return Output.FOG_OF_WAR;

        ArrayList<Tile> route = returnBestMovingRoute(returnRoutes(start, end, unit, 1));
        if (route == null) return Output.NOT_ENOUGH_MOVEMENT_POINTS;
        unit.setMovement(unit.getMaxMovement() - returnMovementCost(route));
        changePlaces(start, end, unit);
        return Output.movedSuccessfully;
    }
//
//    public ArrayList<Tile> attackingRoute(Tile start, Tile end, Unit unit) {
//        // TODO : check if start unit can attack !
//        // TODO : end should be an enemy
//        return returnBestAttackingRoute(returnRoutes(start, end, unit, 1), unit);
//    }
//
//    private ArrayList<Tile> returnBestAttackingRoute(ArrayList<ArrayList<Tile>> possibleRoutes, Unit unit) {
//        // TODO : how does the troopBoost system work ? & melee attack tile ?!
//        ArrayList<Tile> bestRoute = null;
//        Double minMovementCost = Double.POSITIVE_INFINITY, movementCost;
//        Integer range = unit.getRange();
//        if (range == null) range = 1;
//        for (ArrayList<Tile> possibleRoute : possibleRoutes) {
//            movementCost = 0.0;
//            for (int i = 0; i < possibleRoute.size() - range; i++) {
//                movementCost += possibleRoute.get(i).getMp();
//            }
//            if (movementCost < minMovementCost
//                    || (movementCost == minMovementCost
//                    && bestRoute.get(bestRoute.size() - range - 1).getCombatBonus() < possibleRoute.get(possibleRoute.size() - range - 1).getCombatBonus())) {
//                minMovementCost = movementCost;
//                bestRoute = possibleRoute;
//            }
//        }
//        return bestRoute;
//    }


}