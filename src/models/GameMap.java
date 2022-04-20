package models;

import com.sun.tools.javac.Main;
import models.Tile.Tile;
import models.Tile.TileMode;
import models.Tile.TileModeEnum;

import java.util.Random;

public class GameMap {
    private Tile[][] map;


    public GameMap() {
        setMap();
    }


    public Tile[][] getMap() {
        return this.map;
    }

    public void setMap(Tile[][] map) {
        this.map = map;
    }

    private void setMap() {
        Random random = new Random();
        map = new Tile[30][30];
        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 30; j++) {
                map[i][j] = new Tile(new TileMode(TileModeEnum.grassland), null, null);
                if (i <= 2 || i >= 27 || j <= 2 || j >= 27)
                    map[i][j] = new Tile(new TileMode(TileModeEnum.ocean), null, null);
                else if (j >= 24)
                    map[i][j] = new Tile(new TileMode(TileModeEnum.PLAIN), null, null);
                else if (i <= 4)
                    map[i][j] = new Tile(new TileMode(TileModeEnum.PLAIN), null, null);
                else if (i <= 6 && j >= 22)
                    map[i][j] = new Tile(new TileMode(TileModeEnum.PLAIN), null, null);
                else if (j >= 3 && j <= 7 && i >= 24)
                    map[i][j] = new Tile(new TileMode(TileModeEnum.snow), null, null);
                else if (i <= 12 && j <= 11)
                    map[i][j] = new Tile(new TileMode(TileModeEnum.desert), null, null);
            }
        }

        for (int i = 0; i < 250; i++) {
            int randSeed = Math.abs(random.nextInt()) % 4;
            int iCoordinate = Math.abs(random.nextInt()) % 24 + 3;
            int jCoordinate = Math.abs(random.nextInt()) % 24 + 3;
            switch (randSeed) {
                case 0:
                    map[iCoordinate][jCoordinate] = new Tile(new TileMode(TileModeEnum.desert), null, null);
                    break;
                case 1:
                    map[iCoordinate][jCoordinate] = new Tile(new TileMode(TileModeEnum.hill), null, null);
                    break;
                case 2:
                    map[iCoordinate][jCoordinate] = new Tile(new TileMode(TileModeEnum.mountain), null, null);
                    break;
                case 3:
                    map[iCoordinate][jCoordinate] = new Tile(new TileMode(TileModeEnum.PLAIN), null, null);
                    break;
            }
        }
        map[0][Math.abs(random.nextInt() % 30)] = new Tile(new TileMode(TileModeEnum.tundra), null, null);
        map[1][Math.abs(random.nextInt() % 30)] = new Tile(new TileMode(TileModeEnum.tundra), null, null);
        map[28][Math.abs(random.nextInt() % 30)] = new Tile(new TileMode(TileModeEnum.tundra), null, null);
        map[29][Math.abs(random.nextInt() % 30)] = new Tile(new TileMode(TileModeEnum.tundra), null, null);
        map[Math.abs(random.nextInt() % 30)][1] = new Tile(new TileMode(TileModeEnum.tundra), null, null);
        map[Math.abs(random.nextInt() % 30)][0] = new Tile(new TileMode(TileModeEnum.tundra), null, null);
        map[Math.abs(random.nextInt() % 30)][28] = new Tile(new TileMode(TileModeEnum.tundra), null, null);
        map[Math.abs(random.nextInt() % 30)][29] = new Tile(new TileMode(TileModeEnum.tundra), null, null);
    }
}