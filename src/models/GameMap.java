package models;

import models.Tile.Tile;
import models.Tile.TileMode;
import models.Tile.TileModeEnum;

public class GameMap {
    private Tile[][] map;


    public GameMap() {
        map = new Tile[20][20];
        for (int i = 0; i < 20; i++) {
            for (int j = 0; j < 20; j++) {
                map[i][j] = new Tile(new TileMode(TileModeEnum.desert), null, null);
            }
        }
        new River(map[2][2], map[2][3]);
    }


    public Tile[][] getMap() {
        return this.map;
    }

    public void setMap(Tile[][] map) {
        this.map = map;
    }

    public int getICoordinate(Tile tile) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (tile == this.map[i][j])
                    return i;
            }
        }
        return -1;
    }

    public int getJCoordinate(Tile tile) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (tile == this.map[i][j])
                    return j;
            }
        }
        return -1;
    }
}
