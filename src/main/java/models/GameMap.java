package models;

import com.sun.tools.javac.Main;
import models.Feature.TileFeature;
import models.Feature.TileFeatureEnum;
import models.Improvement.TileImprovement;
import models.Improvement.TileImprovementEnum;
import models.Resource.TileResource;
import models.Resource.TileResourceEnum;
import models.Tile.Tile;
import models.Tile.TileMode;
import models.Tile.TileModeEnum;
import models.Units.Combat.CombatUnits;
import models.Units.Nonecombat.NoneCombatUnits;
import models.Units.UnitNameEnum;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class GameMap {
    private Tile[][] map;


    public GameMap(ArrayList<Player> players) {
        setMap();
        setPlayersMap(players);
    }

    public GameMap(Tile[][] map) {
        setMap(map);
    }


    private void setPlayersMap(ArrayList<Player> players) {
        for (int i = 0; i < players.size(); i++) {
            setPlayerTiles(players.get(i), i);
        }
    }

    private void setPlayerTiles(Player player, int number) {
        Tile[][] playerMap = new Tile[30][30];
        int leftICoordinate = 8;
        if (number > 2) leftICoordinate = 17;
        int leftJCoordinate = 6 + (number % 3) * 6;
        setPlayerUnits(player, leftICoordinate, leftJCoordinate);
        for (int j = 1; j <= 2; j++) {
            if (map[leftICoordinate + 1][leftJCoordinate + j].getMode().getTileName() == TileModeEnum.MOUNTAIN) {
                map[leftICoordinate + 1][leftJCoordinate + j] = new Tile(new TileMode(TileModeEnum.PLAIN), null, null);
            }
        }
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 4; j++)
                playerMap[i + leftICoordinate][j + leftJCoordinate] = this.map[i + leftICoordinate][j + leftJCoordinate].clone();
        playerMap[leftICoordinate + 2][leftJCoordinate] = null;
        playerMap[leftICoordinate][leftJCoordinate + 3] = null;
        player.setGameMap(new GameMap(playerMap));
    }

    private void setPlayerUnits(Player player, int leftICoordinate, int leftJCoordinate) {
        CombatUnits combatUnit = new CombatUnits(map[leftICoordinate + 1][leftJCoordinate + 1], UnitNameEnum.SCOUT, player);
        NoneCombatUnits noneCombatUnit = new NoneCombatUnits(map[leftICoordinate + 1][leftJCoordinate + 2], UnitNameEnum.SETTLER, player);
        player.getUnits().add(combatUnit);
        player.getUnits().add(noneCombatUnit);
        map[leftICoordinate + 1][leftJCoordinate + 1].setCombatUnits(combatUnit);
        map[leftICoordinate + 1][leftJCoordinate + 2].setNoneCombatUnits(noneCombatUnit);
    }

    public Tile[][] getMap() {
        return this.map;
    }

    public void setMap(Tile[][] map) {
        this.map = map;
    }

    private void setMap() {
        map = new Tile[30][30];
        addInitialTerrains();
        addRandomTerrains();
        setRivers();
        setFeatures();
        setResources();
    }

    private void addInitialTerrains() {
        for (int i = 0; i < 30; i++) {
            for (int j = 0; j < 30; j++) {
                map[i][j] = new Tile(new TileMode(TileModeEnum.GRASSLAND), null, null);
                if (i <= 2 || i >= 27 || j <= 2 || j >= 27)
                    map[i][j] = new Tile(new TileMode(TileModeEnum.OCEAN), null, null);
                else if (j >= 24)
                    map[i][j] = new Tile(new TileMode(TileModeEnum.PLAIN), null, null);
                else if (i <= 4)
                    map[i][j] = new Tile(new TileMode(TileModeEnum.PLAIN), null, null);
                else if (i <= 6 && j >= 22)
                    map[i][j] = new Tile(new TileMode(TileModeEnum.PLAIN), null, null);
                else if (j >= 3 && j <= 7 && i >= 24)
                    map[i][j] = new Tile(new TileMode(TileModeEnum.SNOW), null, null);
            }
        }
    }

    private void addRandomTerrains() {
        Random random = new Random();
        for (int i = 0; i < 300; i++) {
            int randSeed = Math.abs(random.nextInt()) % 4;
            int iCoordinate = Math.abs(random.nextInt()) % 24 + 3;
            int jCoordinate = Math.abs(random.nextInt()) % 24 + 3;
            switch (randSeed) {
                case 0:
                    map[iCoordinate][jCoordinate] = new Tile(new TileMode(TileModeEnum.DESERT), null, null);
                    break;
                case 1:
                    map[iCoordinate][jCoordinate] = new Tile(new TileMode(TileModeEnum.HILL), null, null);
                    break;
                case 2:
                    map[iCoordinate][jCoordinate] = new Tile(new TileMode(TileModeEnum.MOUNTAIN), null, null);
                    break;
                case 3:
                    map[iCoordinate][jCoordinate] = new Tile(new TileMode(TileModeEnum.PLAIN), null, null);
                    break;
            }
        }
        map[0][Math.abs(random.nextInt() % 30)] = new Tile(new TileMode(TileModeEnum.TUNDRA), null, null);
        map[1][Math.abs(random.nextInt() % 30)] = new Tile(new TileMode(TileModeEnum.TUNDRA), null, null);
        map[28][Math.abs(random.nextInt() % 30)] = new Tile(new TileMode(TileModeEnum.TUNDRA), null, null);
        map[29][Math.abs(random.nextInt() % 30)] = new Tile(new TileMode(TileModeEnum.TUNDRA), null, null);
        map[Math.abs(random.nextInt() % 30)][1] = new Tile(new TileMode(TileModeEnum.TUNDRA), null, null);
        map[Math.abs(random.nextInt() % 30)][0] = new Tile(new TileMode(TileModeEnum.TUNDRA), null, null);
        map[Math.abs(random.nextInt() % 30)][28] = new Tile(new TileMode(TileModeEnum.TUNDRA), null, null);
        map[Math.abs(random.nextInt() % 30)][29] = new Tile(new TileMode(TileModeEnum.TUNDRA), null, null);
    }

    private void setRivers() {
        Random random = new Random();
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                for (int k = 0; k < map.length; k++) {
                    for (int l = 0; l < map[0].length; l++) {
                        if (Tile.isNeighbor(i, j, k, l)) {
                            if (map[i][j].getMode().getTileName() != TileModeEnum.DESERT
                                    && map[k][l].getMode().getTileName() != TileModeEnum.DESERT) {
                                if (random.nextInt() % 4 == 0) {
                                    new River(map[i][j], map[k][l]);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private void setFeatures() {
        Random random = new Random();
        TileFeatureEnum[] featureArray = {TileFeatureEnum.PLAIN, TileFeatureEnum.OASIS,
                TileFeatureEnum.FOREST, TileFeatureEnum.ICE,
                TileFeatureEnum.DENSE_FOREST, TileFeatureEnum.SWAMP};
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (River.hasRiver(map[i][j])) {
                    if (Math.abs(random.nextInt()) % 2 == 0)
                        map[i][j].getFeatures().add(new TileFeature(featureArray[0]));
                    else {
                        if (map[i][j].getMode().getTileName() == TileModeEnum.DESERT)
                            map[i][j].getFeatures().add(new TileFeature(featureArray[Math.abs(random.nextInt()) % 5 + 1]));
                        else
                            map[i][j].getFeatures().add(new TileFeature(featureArray[Math.abs(random.nextInt()) % 4 + 2]));
                    }
                } else if (map[i][j].getMode().getTileName() == TileModeEnum.DESERT) {
                    if (Math.abs(random.nextInt()) % 2 == 0)
                        map[i][j].getFeatures().add(new TileFeature(featureArray[1]));
                    else map[i][j].getFeatures().add(new TileFeature(featureArray[Math.abs(random.nextInt()) % 4 + 2]));
                } else map[i][j].getFeatures().add(new TileFeature(featureArray[Math.abs(random.nextInt()) % 4 + 2]));
            }
        }
    }

    private void setResources() {
        Random random = new Random();
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                ArrayList<TileResourceEnum> possibleResources = TileResource.findPossibleResources(map[i][j]);
                if (possibleResources.size() > 0 && random.nextInt() % 4 == 0)
                    map[i][j].setResource(new TileResource(possibleResources.get(Math.abs(random.nextInt()) % possibleResources.size())));
            }
        }
    }

    public Tile getTile(int i, int j) {
        if (i < 0 || j < 0 || i > map.length - 1 || j > map[0].length - 1) return null;
        return getMap()[i][j];
    }

    // TODO : check !?
    public int getIndexI(Tile tile) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (getMap()[i][j] == tile) return i;
            }
        }
        return -1;
    }

    public int getIndexJ(Tile tile) {
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (getMap()[i][j] == tile) return j;
            }
        }
        return -1;
    }

    public ArrayList<Tile> getUnitInSightTiles(Tile tile) {
        boolean isOnBlock = (tile.getMode().getTileName() == TileModeEnum.MOUNTAIN) ||
                tile.hasFeature(TileFeatureEnum.FOREST) || tile.getMode().getTileName() == TileModeEnum.HILL;
        int iCoordinate = this.getIndexI(tile);
        int jCoordinate = this.getIndexJ(tile);
        ArrayList<Tile> inSightTiles = new ArrayList<>();
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (Tile.isNeighbor(iCoordinate, jCoordinate, i, j)) {
                    if (!inSightTiles.contains(map[i][j]))
                        inSightTiles.add(map[i][j]);
                    if ((map[i][j].getMode().getTileName() != TileModeEnum.MOUNTAIN
                            && !map[i][j].hasFeature(TileFeatureEnum.FOREST)
                            && map[i][j].getMode().getTileName() != TileModeEnum.HILL) || isOnBlock) {
                        for (int k = 0; k < map.length; k++) {
                            for (int l = 0; l < map[k].length; l++) {
                                if (Tile.isNeighbor(i, j, k, l)) {
                                    if (!inSightTiles.contains(map[k][l]))
                                        inSightTiles.add(map[k][l]);
                                }
                            }
                        }
                    }
                }
            }
        }
        return inSightTiles;
    }

    public ArrayList<Tile> getCityInSightTiles(Tile tile) {
        int iCoordinate = this.getIndexI(tile);
        int jCoordinate = this.getIndexJ(tile);
        ArrayList<Tile> inSightTiles = new ArrayList<>();
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[i].length; j++) {
                if (Tile.isNeighbor(iCoordinate, jCoordinate, i, j)) {
                    inSightTiles.add(map[i][j]);
                }
            }
        }
        return inSightTiles;
    }


    public static Tile getCorrespondingTile(Tile tile, GameMap tileGameMap, GameMap mainGameMap) {
        int iCoordinate = tileGameMap.getIndexI(tile);
        int jCoordinate = tileGameMap.getIndexJ(tile);
        if (iCoordinate >= 0 && iCoordinate <= mainGameMap.getMap().length)
            if (jCoordinate >= 0 && jCoordinate <= mainGameMap.getMap()[0].length)
                return mainGameMap.getTile(iCoordinate, jCoordinate);
        return null;
    }

}