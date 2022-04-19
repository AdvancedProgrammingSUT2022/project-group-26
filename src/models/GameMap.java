package models;

import models.Tile.Tile;
import models.Tile.TileMode;
import models.Tile.TileModeEnum;

public class GameMap {
    private Tile[][] map;


    public GameMap() {
        map = new Tile[20][20];
        for(int i = 0; i < 20; i++){
            for(int j = 0; j < 20; j++){
                map[i][j] = new Tile(new TileMode(TileModeEnum.desert), null, null);
            }
        }
    }


    public Tile[][] getMap() {
        return this.map;
    }

    public void setMap(Tile[][] map) {
        this.map = map;
    }

}
