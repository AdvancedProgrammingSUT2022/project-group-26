package models;

import models.Tile.Tile;

public class GameMap {
    private Tile[][] map;


    public GameMap() {

    }


    public Tile[][] getMap() {
        return this.map;
    }

    public void setMap(Tile[][] map) {
        this.map = map;
    }

}
