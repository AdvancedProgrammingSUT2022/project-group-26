package controllers.GameControllers;

import models.GameMap;
import models.Tile.Tile;
import models.Units.Units;

import java.util.ArrayList;

public class MovementController {
    private GameMap gameMap;

    public ArrayList<ArrayList<Tile>> returnRoutes(Tile start, Tile end, Units unit) {
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

    public ArrayList<Tile> movingRoute(Tile start, Tile end, Units unit) {
        // TODO : check if unit and start tile match up !
        // TODO : end should not be an enemy
        return returnBestMovingRoute(returnRoutes(start, end, unit));
    }

    public ArrayList<Tile> attackingRoute(Tile start, Tile end, Units unit) {
        // TODO : check if start unit can attack !
        // TODO : end should be an enemy
        return returnBestAttackingRoute(returnRoutes(start, end, unit), unit);
    }

    private ArrayList<Tile> returnBestAttackingRoute(ArrayList<ArrayList<Tile>> possibleRoutes, Units unit) {
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
        tempTile = gameMap.getTile(i1 + 1, j1);
        if (checkIfItsPossible(tempTile, movement)) {
            clonedRoute = (ArrayList) route.clone();
            clonedRoute.add(tempTile);
            makePossibleRoutes(i1 + 1, j1, i2, j2, routes, clonedRoute, movement - tempTile.getMp());
        }
        tempTile = gameMap.getTile(i1 - 1, j1);
        if (checkIfItsPossible(tempTile, movement)) {
            clonedRoute = (ArrayList) route.clone();
            clonedRoute.add(tempTile);
            makePossibleRoutes(i1 - 1, j1, i2, j2, routes, clonedRoute, movement - tempTile.getMp());
        }
        tempTile = gameMap.getTile(i1, j1 + 1);
        if (checkIfItsPossible(tempTile, movement)) {
            clonedRoute = (ArrayList) route.clone();
            clonedRoute.add(tempTile);
            makePossibleRoutes(i1, j1 + 1, i2, j2, routes, clonedRoute, movement - tempTile.getMp());
        }
        tempTile = gameMap.getTile(i1, j1 - 1);
        if (checkIfItsPossible(tempTile, movement)) {
            clonedRoute = (ArrayList) route.clone();
            clonedRoute.add(tempTile);
            makePossibleRoutes(i1, j1 - 1, i2, j2, routes, clonedRoute, movement - tempTile.getMp());
        }
        tempTile = gameMap.getTile(i1 + 1, j1 - 1);
        if (checkIfItsPossible(tempTile, movement)) {
            clonedRoute = (ArrayList) route.clone();
            clonedRoute.add(tempTile);
            makePossibleRoutes(i1 + 1, j1 - 1, i2, j2, routes, clonedRoute, movement);
        }
        tempTile = gameMap.getTile(i1 + 1, j1 + 1);
        if (checkIfItsPossible(tempTile, movement)) {
            clonedRoute = (ArrayList) route.clone();
            clonedRoute.add(tempTile);
            makePossibleRoutes(i1 + 1, j1 + 1, i2, j2, routes, clonedRoute, movement);
        }
    }

    public boolean checkIfItsPossible(Tile tile, Double movement) {
        if (tile.getMp() > movement) return false;
        return true;
    }

}