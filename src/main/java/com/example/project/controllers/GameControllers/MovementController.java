package com.example.project.controllers.GameControllers;

import com.example.project.controllers.Output;
import com.example.project.models.GameMap;
import com.example.project.models.Player;
import com.example.project.models.River;
import com.example.project.models.Tile.Tile;
import com.example.project.models.Units.Combat.CombatUnits;
import com.example.project.models.Units.Nonecombat.NoneCombatUnits;
import com.example.project.models.Units.Unit;
import com.example.project.models.Units.UnitTypeEnum;

import java.util.ArrayList;
import java.util.Collections;

public class MovementController {
    private GameMap gameMap;

    public MovementController(GameMap gameMap) {
        this.gameMap = gameMap;
    }


    // not needed !
//    public Output moveUnits(Tile start, Tile end, Unit unit, Player player) {
//        if (start != unit.getPosition()) return Output.startTileAndUnitDontMatchUp;
//        if (unit.getPlayer() != player) return Output.youDontOwnThisUnit;
//        if (end.getCombatUnits() != null && end.getCombatUnits().getPlayer() != player)
//            return Output.enemyCombatUnitOnThatTile;
//        if (end.getNoneCombatUnits() != null && end.getNoneCombatUnits().getPlayer() != player)
//            return Output.enemyNonCombatUnitOnThatTile;
//        if ((end.getNoneCombatUnits() != null && unit.isACivilian()) || (end.getCombatUnits() != null && unit.isACombatUnit()))
//            return Output.youAlreadyHaveATroopThere;
//        if (player.getGameMap().getMap()[this.gameMap.getIndexI(end)][this.gameMap.getIndexJ(end)] == null)
//            return Output.FOG_OF_WAR;
//        ArrayList<Tile> route = returnBestMovingRoute(returnRoutes(start, end, unit, 1));
//        if (route == null) return Output.NOT_ENOUGH_MOVEMENT_POINTS;
//        unit.setMovement(unit.getMaxMovement() - returnMovementCost(route));
//        changePlaces(start, end, unit);
//        return Output.movedSuccessfully;
//    }

//
//    public ArrayList<ArrayList<Tile>> returnRoutes(Tile start, Tile end, Unit unit, int turns) {
//        ArrayList<ArrayList<Tile>> possibleRoutes = new ArrayList<>();
//        int startIndexI = gameMap.getIndexI(start);
//        int startIndexJ = gameMap.getIndexJ(start);
//        int endIndexI = gameMap.getIndexI(end);
//        int endIndexJ = gameMap.getIndexJ(end);
//        makePossibleRoutes(startIndexI, startIndexJ, endIndexI, endIndexJ, possibleRoutes, new ArrayList<>(), unit.getMaxMovement() * turns);
//        return possibleRoutes;
//    }
//
//    public ArrayList<ArrayList<Tile>> returnRoutes(Tile start, Tile end, int range) {
//        ArrayList<ArrayList<Tile>> possibleRoutes = new ArrayList<>();
//        int startIndexI = gameMap.getIndexI(start);
//        int startIndexJ = gameMap.getIndexJ(start);
//        int endIndexI = gameMap.getIndexI(end);
//        int endIndexJ = gameMap.getIndexJ(end);
//        makePossibleRoutes(startIndexI, startIndexJ, endIndexI, endIndexJ, possibleRoutes, new ArrayList<>(), range);
//        return possibleRoutes;
//    }


//    public ArrayList<Tile> returnBestMovingRoute(ArrayList<ArrayList<Tile>> possibleRoutes) {
//        ArrayList<Tile> bestRoute = null;
//        Double minMovementCost = Double.POSITIVE_INFINITY, movementCost;
//        for (ArrayList<Tile> possibleRoute : possibleRoutes) {
//            movementCost = returnMovementCost(possibleRoute);
//            if (movementCost < minMovementCost) {
//                minMovementCost = movementCost;
//                bestRoute = possibleRoute;
//            }
//        }
//        return bestRoute;
//    }





//    public void makePossibleRoutes(int i1, int j1, int i2, int j2, ArrayList<ArrayList<Tile>> routes, ArrayList<Tile> route, double movement) {
//        if (i1 == i2 && j1 == j2) {
//            routes.add(route);
//            return;
//        }
//        if (movement <= 0) return;
//        ArrayList<Tile> clonedRoute;
//        Tile tempTile;
//        Double tempMovement;
//        tempTile = gameMap.getTile(i1 + 1, j1);
//        if (tempTile != null && checkIfItsPossible(tempTile, movement)) {
//            clonedRoute = (ArrayList) route.clone();
//            clonedRoute.add(tempTile);
//            tempMovement = movement - tempTile.getMp();
//            if (River.hasRiver(gameMap.getTile(i1, j1), tempTile)) tempMovement = 0D;
//            makePossibleRoutes(i1 + 1, j1, i2, j2, routes, clonedRoute, tempMovement);
//        }
//        tempTile = gameMap.getTile(i1 - 1, j1);
//        if (tempTile != null && checkIfItsPossible(tempTile, movement)) {
//            clonedRoute = (ArrayList) route.clone();
//            clonedRoute.add(tempTile);
//            tempMovement = movement - tempTile.getMp();
//            if (River.hasRiver(gameMap.getTile(i1, j1), tempTile)) tempMovement = 0D;
//            makePossibleRoutes(i1 - 1, j1, i2, j2, routes, clonedRoute, tempMovement);
//        }
//        tempTile = gameMap.getTile(i1, j1 + 1);
//        if (tempTile != null && checkIfItsPossible(tempTile, movement)) {
//            clonedRoute = (ArrayList) route.clone();
//            clonedRoute.add(tempTile);
//            tempMovement = movement - tempTile.getMp();
//            if (River.hasRiver(gameMap.getTile(i1, j1), tempTile)) tempMovement = 0D;
//            makePossibleRoutes(i1, j1 + 1, i2, j2, routes, clonedRoute, tempMovement);
//        }
//        tempTile = gameMap.getTile(i1, j1 - 1);
//        if (tempTile != null && checkIfItsPossible(tempTile, movement)) {
//            clonedRoute = (ArrayList) route.clone();
//            clonedRoute.add(tempTile);
//            tempMovement = movement - tempTile.getMp();
//            if (River.hasRiver(gameMap.getTile(i1, j1), tempTile)) tempMovement = 0D;
//            makePossibleRoutes(i1, j1 - 1, i2, j2, routes, clonedRoute, tempMovement);
//        }
//        tempTile = gameMap.getTile(i1 + 1, j1 - 1);
//        if (tempTile != null && checkIfItsPossible(tempTile, movement)) {
//            clonedRoute = (ArrayList) route.clone();
//            clonedRoute.add(tempTile);
//            tempMovement = movement - tempTile.getMp();
//            if (River.hasRiver(gameMap.getTile(i1, j1), tempTile)) tempMovement = 0D;
//            makePossibleRoutes(i1 + 1, j1 - 1, i2, j2, routes, clonedRoute, tempMovement);
//        }
//        tempTile = gameMap.getTile(i1 + 1, j1 + 1);
//        if (tempTile != null && checkIfItsPossible(tempTile, movement)) {
//            clonedRoute = (ArrayList) route.clone();
//            clonedRoute.add(tempTile);
//            tempMovement = movement - tempTile.getMp();
//            if (River.hasRiver(gameMap.getTile(i1, j1), tempTile)) tempMovement = 0D;
//            makePossibleRoutes(i1 + 1, j1 + 1, i2, j2, routes, clonedRoute, tempMovement);
//        }
//    }

    /*
    public ArrayList<ArrayList<Tile>> returnRoutes(Tile start, Tile end, Unit unit, int turns) {
        ArrayList<ArrayList<Tile>> possibleRoutes = new ArrayList<>();
        findPossibleRoutes(start,end,possibleRoutes,new ArrayList<>());
        return possibleRoutes;
    }

    public ArrayList<ArrayList<Tile>> returnRoutes(Tile start, Tile end, int range) {
        ArrayList<ArrayList<Tile>> possibleRoutes = new ArrayList<>();
        findPossibleRoutes(start,end,possibleRoutes,new ArrayList<>());
        return possibleRoutes;
    }
     */


    public boolean checkIfItsPossible(Tile tile, Double movement) {
        if (tile.getMp() == Double.POSITIVE_INFINITY) return false;
        if (movement <= 0) return false;
        return true;
    }

    public boolean checkRange(Tile attacker, Tile defender, Integer range) {
        ArrayList<ArrayList<Tile>> possibleRoutes = new ArrayList<>();
        ArrayList<Tile> route = new ArrayList<>();
        route.add(attacker);
        findPossibleRoutes(attacker, defender, possibleRoutes, route);
        if (possibleRoutes.size() == 0) return false;
        for (ArrayList<Tile> possibleRoute : possibleRoutes) {
            if (possibleRoute.size() <= range + 1) return true;
        }
        return false;
    }


    public static boolean inZoneOfControl(GameMap gameMap, Tile tile) {
        Tile temp;
        int indexI = gameMap.getIndexI(tile);
        int indexJ = gameMap.getIndexJ(tile);
        for (int i = indexI - 2; i < indexI + 3; i++) {
            for (int j = indexJ - 2; j < indexJ + 3; j++) {
                if ((i == -2 && j == 2)
                        || (i == -2 && j == 1)
                        || (i == -2 && j == -1)
                        || (i == -2 && j == -2)
                        || (i == -1 && j == 2)
                        || (i == -1 && j == -2)
                        || (i == 0 && j == 0))
                    continue;
                if ((temp = gameMap.getTile(i, j)) == null) continue; //test if it's a valid tile
                if (temp.getCombatUnits() != null && tile.getCombatUnits() != null
                        && temp.getCombatUnits().getPlayer() != tile.getCombatUnits().getPlayer())
                    return true;
            }
        }
        return false;
    }


    public Output moveFromSavedRoute(Unit unit) {
        ArrayList<Tile> route = unit.getSavedRoute();
        double movement = unit.getMovement();
        if (route == null) return Output.NO_SAVED_ROUTE;
        while (route.size() > 0) {
            if ((route.get(0).getCombatUnits() != null && unit.isACombatUnit())
                    || (route.get(0).getNoneCombatUnits() != null && unit.isACivilian())
                    || (route.get(0).getCombatUnits() != null && route.get(0).getCombatUnits().getPlayer() != unit.getPlayer())
                    || (route.get(0).getNoneCombatUnits() != null && route.get(0).getNoneCombatUnits().getPlayer() != unit.getPlayer())) {
                if (route.size() == 1
                        || (((route.get(1).getCombatUnits() != null && unit.isACombatUnit())
                        || (route.get(1).getNoneCombatUnits() != null && unit.isACivilian())
                        || (route.get(1).getCombatUnits() != null && route.get(1).getCombatUnits().getPlayer() != unit.getPlayer())
                        || (route.get(1).getNoneCombatUnits() != null && route.get(1).getNoneCombatUnits().getPlayer() != unit.getPlayer())))) {
                    return Output.BAD_ROUTE;
                }
                movement -= route.get(0).getMp();
                movement -= route.get(1).getMp();
                if (inZoneOfControl(gameMap, unit.getPosition())) movement = 0;
                if (River.hasRiver(unit.getPosition(), route.get(0))) movement = 0;
                if (River.hasRiver(route.get(0), route.get(1))) movement = 0;
                changePlaces(unit.getPosition(), route.get(1), unit);
                route.remove(0);
                route.remove(0);
            } else {
                movement -= route.get(0).getMp();
                changePlaces(unit.getPosition(), route.get(0), unit);
                route.remove(0);
            }
            if (movement <= 0) break;
        }
        unit.setMovement(movement);
        return Output.MOVED_SUCCESSFULLY;

//        double movement = unit.getMovement();
//        int index = -1;
//        for (int i = 0; i < route.size(); i++) {
//            movement -= route.get(i).getMp();
//            if (inZoneOfControl(gameMap, route.get(i))) movement = 0d;
//            if (i - 1 >= 0 && River.hasRiver(route.get(i - 1), route.get(i))) movement = 0d;
//            index = i;
//            if (movement <= 0) break;
//        }
//        while (true) {
//            if (index < 0) return Output.NO_ROUTE;
//            if ((route.get(index).getCombatUnits() != null && unit.isACombatUnit())
//                    || (route.get(index).getNoneCombatUnits() != null && unit.isACivilian())
//                    || (route.get(index).getCombatUnits() != null && route.get(index).getCombatUnits().getPlayer() != unit.getPlayer())
//                    || (route.get(index).getNoneCombatUnits() != null && route.get(index).getNoneCombatUnits().getPlayer() != unit.getPlayer())) {
//                movement += route.get(index).getMp();
//                index--;
//            } else {
//                break;
//            }
//        }
//        if (movement < 0) movement = 0D;
//        unit.setMovement(movement);
//        changePlaces(unit.getPosition(), route.get(index), unit);
//        route.subList(0, index + 1).clear();
//        return Output.MOVED_SUCCESSFULLY;
    }


    public ArrayList<Tile> returnBestMovingRoute(ArrayList<ArrayList<Tile>> possibleRoutes, Unit unit) {
        for (ArrayList<Tile> possibleRoute : possibleRoutes) {
            if (isAGoodPath(possibleRoute, unit)) return possibleRoute;
        }
        return null;
    }


    private boolean isAGoodPath(ArrayList<Tile> possibleRoute, Unit unit) {
        if (possibleRoute == null || possibleRoute.size() == 0) return false;
        double movementPoints = unit.getMovement() - possibleRoute.get(0).getMp();
        for (int i = 1; i < possibleRoute.size(); i++) {
            movementPoints -= possibleRoute.get(i).getMp();
            if (River.hasRiver(possibleRoute.get(i - 1), possibleRoute.get(i))) movementPoints = 0;
            if (!(movementPoints > 0)) {
                movementPoints = unit.getUnitNameEnum().getMovement();
                if (unit.getUnitTypeEnum() == UnitTypeEnum.CIVILIAN
                        && (possibleRoute.get(i).getNoneCombatUnits() != null
                        || (possibleRoute.get(i).getCombatUnits() != null
                        && possibleRoute.get(i).getCombatUnits().getPlayer() != unit.getPlayer())))
                    return false;
                if (unit.getUnitTypeEnum() != UnitTypeEnum.CIVILIAN
                        && (possibleRoute.get(i).getCombatUnits() != null
                        || (possibleRoute.get(i).getNoneCombatUnits() != null
                        && possibleRoute.get(i).getNoneCombatUnits().getPlayer() != unit.getPlayer())))
                    return false;
            }
        }
        return true;
    }

    public Output backTrackRoute(Tile start, Tile end, Unit unit) {
        ArrayList<ArrayList<Tile>> possibleRoutes = new ArrayList<>();
        ArrayList<Tile> route = new ArrayList<>();
        route.add(start);
        findPossibleRoutes(start, end, possibleRoutes, route);
        if (!(possibleRoutes.size() > 0)) return Output.BAD_ROUTE;
        sortRoutesByMP(possibleRoutes);
        ArrayList<Tile> bestRoute = returnBestMovingRoute(possibleRoutes, unit);
        if (bestRoute == null) return Output.BAD_ROUTE;
        unit.setSavedRoute(bestRoute);
        return Output.COMMAND_SUCCESSFUL;
    }

    private void sortRoutesByMP(ArrayList<ArrayList<Tile>> possibleRoutes) {
        for (int i = 0; i < possibleRoutes.size(); i++) {
            for (int j = i + 1; j < possibleRoutes.size(); j++) {
                if (returnMovementCost(possibleRoutes.get(i)) > returnMovementCost(possibleRoutes.get(j))
                        || (returnMovementCost(possibleRoutes.get(i)) == returnMovementCost(possibleRoutes.get(j))
                        && possibleRoutes.get(i).size() > possibleRoutes.size()))
                    Collections.swap(possibleRoutes, i, j);
            }
        }
    }

    private void findPossibleRoutes(Tile start, Tile end, ArrayList<ArrayList<Tile>> possibleRoutes, ArrayList<Tile> routeTillNow) {
        if (start == end) {
            possibleRoutes.add(routeTillNow);
            return;
        }
        ArrayList<Tile> clonedRoutes;
        for (Tile next : surroundingTiles(start)) {
            if (!routeTillNow.contains(next)
                    && checkIfItsPossible(next)
                    && CheckTrack(routeTillNow, next, start, end)) {
                clonedRoutes = (ArrayList<Tile>) routeTillNow.clone();
                clonedRoutes.add(next);
                findPossibleRoutes(next, end, possibleRoutes, clonedRoutes);
            }
        }
    }

    private boolean CheckTrack(ArrayList<Tile> path, Tile next, Tile start, Tile end) {
        if (path.size() > 7) return false;
        int x1, x2, y1, y2;
        x1 = gameMap.getIndexI(start);
        x2 = gameMap.getIndexI(end);
        y1 = gameMap.getIndexJ(start);
        y2 = gameMap.getIndexJ(end);
        if (gameMap.getIndexI(next) - Math.max(x1, x2) > 3
                || gameMap.getIndexJ(next) - Math.max(y1, y2) > 3
                || Math.min(x1, x2) - gameMap.getIndexI(next) > 3
                || Math.min(y1, y2) - gameMap.getIndexJ(next) > 3
        ) return false;
        return true;
    }

    private ArrayList<Tile> surroundingTiles(Tile tile) {
        ArrayList<Tile> saveSurrounding = new ArrayList<>();
        int x, y;
        Tile tempTile;
        if (gameMap.getIndexJ(tile) % 2 == 1) {
            x = this.gameMap.getIndexI(tile) + 1;
            y = this.gameMap.getIndexJ(tile);
            tempTile = this.gameMap.getTile(x, y);
            if (tempTile != null)
                saveSurrounding.add(tempTile);

            x = this.gameMap.getIndexI(tile) - 1;
            y = this.gameMap.getIndexJ(tile);
            tempTile = this.gameMap.getTile(x, y);
            if (tempTile != null)
                saveSurrounding.add(tempTile);

            x = this.gameMap.getIndexI(tile) - 1;
            y = this.gameMap.getIndexJ(tile) - 1;
            tempTile = this.gameMap.getTile(x, y);
            if (tempTile != null)
                saveSurrounding.add(tempTile);

            x = this.gameMap.getIndexI(tile) - 1;
            y = this.gameMap.getIndexJ(tile) + 1;
            tempTile = this.gameMap.getTile(x, y);
            if (tempTile != null)
                saveSurrounding.add(tempTile);

            x = this.gameMap.getIndexI(tile);
            y = this.gameMap.getIndexJ(tile) - 1;
            tempTile = this.gameMap.getTile(x, y);
            if (tempTile != null)
                saveSurrounding.add(tempTile);

            x = this.gameMap.getIndexI(tile);
            y = this.gameMap.getIndexJ(tile) + 1;
            tempTile = this.gameMap.getTile(x, y);
            if (tempTile != null)
                saveSurrounding.add(tempTile);

        } else {
            x = this.gameMap.getIndexI(tile) + 1;
            y = this.gameMap.getIndexJ(tile);
            tempTile = this.gameMap.getTile(x, y);
            if (tempTile != null)
                saveSurrounding.add(tempTile);

            x = this.gameMap.getIndexI(tile) - 1;
            y = this.gameMap.getIndexJ(tile);
            tempTile = this.gameMap.getTile(x, y);
            if (tempTile != null)
                saveSurrounding.add(tempTile);

            x = this.gameMap.getIndexI(tile) + 1;
            y = this.gameMap.getIndexJ(tile) - 1;
            tempTile = this.gameMap.getTile(x, y);
            if (tempTile != null)
                saveSurrounding.add(tempTile);

            x = this.gameMap.getIndexI(tile) + 1;
            y = this.gameMap.getIndexJ(tile) + 1;
            tempTile = this.gameMap.getTile(x, y);
            if (tempTile != null)
                saveSurrounding.add(tempTile);

            x = this.gameMap.getIndexI(tile);
            y = this.gameMap.getIndexJ(tile) - 1;
            tempTile = this.gameMap.getTile(x, y);
            if (tempTile != null)
                saveSurrounding.add(tempTile);

            x = this.gameMap.getIndexI(tile);
            y = this.gameMap.getIndexJ(tile) + 1;
            tempTile = this.gameMap.getTile(x, y);
            if (tempTile != null)
                saveSurrounding.add(tempTile);
        }
        return saveSurrounding;
    }

    // the new one
    public boolean checkIfItsPossible(Tile tile) {
        if (tile.getMp() == Double.POSITIVE_INFINITY) return false;
        // if for fog of war --> false ?1
        return true;
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

    public Output addASavedRoute(Tile destination, Unit unit, Player player) {
        if (unit.getPlayer() != player) return Output.youDontOwnThisUnit;
        if (destination.getCombatUnits() != null && destination.getCombatUnits().getPlayer() != player)
            return Output.enemyCombatUnitOnThatTile;
        if (destination.getNoneCombatUnits() != null && destination.getNoneCombatUnits().getPlayer() != player)
            return Output.enemyNonCombatUnitOnThatTile;
        if ((destination.getNoneCombatUnits() != null && unit.isACivilian()) || (destination.getCombatUnits() != null && unit.isACombatUnit()))
            return Output.youAlreadyHaveATroopThere;
        if (player.getGameMap().getMap()[this.gameMap.getIndexI(destination)][this.gameMap.getIndexJ(destination)] == null)
            return Output.FOG_OF_WAR;
        return backTrackRoute(unit.getPosition(), destination, unit);
        //unit.setSavedRoute(returnBestMovingRoute(returnRoutes(unit.getPosition(), destination, unit, 5)));
//
//        if (unit.getSavedRoute() == null) return Output.LONG_ROUTE;
//        return Output.ADDED_ROUTE;
    }
    private Double returnMovementCost(ArrayList<Tile> possibleRoute) {
        Double movementCost = 0.0;
        for (Tile tile : possibleRoute) {
            movementCost += tile.getMp();
        }
        return movementCost;
    }


}