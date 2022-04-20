package controllers.GameControllers;

import models.GameMap;
import models.Tile.Tile;
import models.Units.Units;

import java.util.ArrayList;

public class MovementController {
    private GameMap gameMap;

    public ArrayList<Tile> findRoute(Tile start, Tile end, Units unit) {
        // tabe hayi ke darim :
//        int startIndexI = getICordinate(start);
//        int startIndexJ = getJCordinate(start);
//        int endIndexI = getICordinate(end);
//        int endIndexJ = getJCordinate(end);
//        makePossibleRoutes();
        // use the best route ?
        return null;
    }

    public void identifyCommand(Tile start, Tile end, Units unit) {
    }

    public void move(Tile start, Tile end, Units unit) {
    }

    public void makePossibleRoutes(int i1, int j1, int i2, int j2, ArrayList<ArrayList<Tile>> routes, ArrayList<Tile> route, Double movement) {//TODO ; collision handle
        // TODO : split the function
        if (i1 == i2 && j1 == j2) {
            routes.add(route);
            return;
        }
        if (movement == 0) return;
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
        //TODO : check if there was troops in that tile
        //TODO : if mountain or ... --> return false
        if (tile.getMp() > movement) return false;
        return true;
    }

}