package controllers.GameControllers;

import models.Feature.TileFeatureEnum;
import models.GameMap;
import models.Improvement.TileImprovementEnum;
import models.Player;
import models.River;
import models.Tile.Tile;
import models.Tile.TileModeEnum;

import java.util.ArrayList;
import java.util.concurrent.RecursiveTask;

public class ShowMapController {

    GameMap gameMap;
    ArrayList<Player> players;

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public static final String ANSI_BLACK_BACKGROUND = "\u001B[40m";
    public static final String ANSI_RED_BACKGROUND = "\u001B[41m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";
    public static final String ANSI_YELLOW_BACKGROUND = "\u001B[43m";
    public static final String ANSI_BLUE_BACKGROUND = "\u001B[44m";
    public static final String ANSI_PURPLE_BACKGROUND = "\u001B[45m";
    public static final String ANSI_CYAN_BACKGROUND = "\u001B[46m";
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[107m";

    public static final String ANSI_GREY_BACKGROUND = "\u001B[100m";
    public static final String ANSI_LIGHT_GREEN_BACKGROUND = "\u001B[102m";

    public static final String ANSI_LIGHT_YELLOW_BACKGROUND = "\u001B[103m";
    public static final String ANSI_PURPLE_BOLD = "\033[1;35m";
    public static final String ANSI_BOLD = "\u001B[1m";
    public static final String ANSI_BLACK_BOLD = "\033[1;30m";
    public static final String ANSI_RED_BOLD = "\033[1;31m";
    public static final String ANSI_GREEN_BOLD = "\033[1;32m";
    public static final String ANSI_YELLOW_BOLD = "\033[1;33m";
    public static final String ANSI_BLUE_BOLD = "\033[1;34m";
    public static final String ANSI_CYAN_BOLD = "\033[1;96m";
    public static final String ANSI_WHITE_BOLD = "\033[1;37m";
    public static final String ANSI_UNDERLINED = "\u001B[4m";


    public int[][][] getCenters() {
        int[][][] centerPoints = new int[3][6][2];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 6; j++) {
                if (j % 2 == 0)
                    centerPoints[i][j][0] = 3 + 6 * i;
                else
                    centerPoints[i][j][0] = 6 + 6 * i;
                centerPoints[i][j][1] = (j * 8) + 5;
            }
        }
        return centerPoints;
    }

    public ShowMapController(GameMap gameMap, ArrayList<Player> players) {
        this.gameMap = gameMap;
        this.players = players;
    }

    public void setTileArrayToPrint(int iCoordinate, int jCoordinate, Tile[][] tilesToShow, Tile[][] playerMap) {
        if (jCoordinate % 2 == 1) {
            for (int i = iCoordinate; i < iCoordinate + 3; i++)
                for (int j = jCoordinate; j < jCoordinate + 6; j++)
                    tilesToShow[i - iCoordinate][j - jCoordinate] = playerMap[i][j];
        } else {
            for (int i = iCoordinate; i < iCoordinate + 3; i++) {
                for (int j = jCoordinate; j < jCoordinate + 6; j++) {
                    if (j % 2 == 0) tilesToShow[i - iCoordinate][j - jCoordinate] = playerMap[i][j];
                    else tilesToShow[i - iCoordinate][j - jCoordinate] = playerMap[i + 1][j];
                }
            }

        }

    }

    public void setToPrintStrings(String[][] toPrint, Tile[][] tilesToShow, int iCoordinate, int jCoordinate, int playerNumber) {
        setAllSpace(toPrint);
        setUpDownPolygon(toPrint);
        setLeftRightPolygon(toPrint);
        int[][][] centerPoints = getCenters();
        setCooridante(toPrint, iCoordinate, jCoordinate, centerPoints, tilesToShow);
        setPlayerTag(toPrint, centerPoints, playerNumber, tilesToShow);
        setRivers(toPrint, centerPoints, tilesToShow);
        setFeatures(toPrint, centerPoints, tilesToShow);
        setUnits(toPrint, centerPoints, tilesToShow);
        setResources(toPrint, centerPoints, tilesToShow, players.get(playerNumber));
        setImprovements(toPrint, centerPoints, tilesToShow, players.get(playerNumber));
        setColor(toPrint, tilesToShow, centerPoints);
    }


    private void setAllSpace(String[][] toPrint) {
        for (int i = 0; i < toPrint.length; i++)
            for (int j = 0; j < toPrint[0].length; j++)
                toPrint[i][j] = " ";
    }

    private void setUpDownPolygon(String[][] toPrint) {
        for (int i = 0; i <= 12; i += 6) {
            for (int j = 3; j < 48; j += 16) {
                for (int k = 0; k < 5; k++) {
                    toPrint[i][j + k] = "_";
                    toPrint[i + 3][j + k + 8] = "_";
                    toPrint[i + 6][j + k] = "_";
                    toPrint[i + 9][j + k + 8] = "_";
                }
            }
        }
    }

    private void setLeftRightPolygon(String[][] toPrint) {
        int counter = 0;
        for (int row = 0; row <= 12; row += 6) {
            for (int k = 0; k < 48; k += 16) {
                for (int i = 3; i >= 1; i--) {
                    toPrint[i + row][k + counter] = "/";
                    toPrint[(7 - i) + row][k + counter] = "\\";
                    toPrint[i + row][k + (10 - counter)] = "\\";
                    toPrint[(7 - i) + row][k + (10 - counter)] = "/";

                    toPrint[i + row + 3][k + (10 - counter) + 8] = "\\";
                    toPrint[(7 - i) + row + 3][k + counter + 8] = "\\";
                    toPrint[(7 - i) + row + 3][k + (10 - counter) + 8] = "/";
                    counter++;
                }
                counter = 0;
            }
        }
    }

    private void setColor(String[][] toPrint, Tile[][] tilesToShow, int[][][] centerPoints) {
        for (int i = 0; i < 22; i++) {
            for (int j = 0; j < 51; j++) {
                for (int k = 0; k < 3; k++) {
                    for (int l = 0; l < 6; l++) {
                        if ((((centerPoints[k][l][0] - i < 3 && centerPoints[k][l][0] - i >= 0) || (i - centerPoints[k][l][0] < 4 && i - centerPoints[k][l][0] >= 0))
                                && ((centerPoints[k][l][1] - j < 3 && centerPoints[k][l][1] - j >= 0) || (j - centerPoints[k][l][1] < 3 && j - centerPoints[k][l][1] >= 0))))
                            toPrint[i][j] = getTileColor(tilesToShow[k][l]) + toPrint[i][j] + ANSI_RESET;
                        if ((((centerPoints[k][l][0] - i < 2 && centerPoints[k][l][0] - i >= 0) || (i - centerPoints[k][l][0] < 3 && i - centerPoints[k][l][0] >= 0))
                                && ((centerPoints[k][l][1] - j < 4 && centerPoints[k][l][1] - j >= 0) || (j - centerPoints[k][l][1] < 4 && j - centerPoints[k][l][1] >= 0))))
                            toPrint[i][j] = getTileColor(tilesToShow[k][l]) + toPrint[i][j] + ANSI_RESET;
                        if ((((centerPoints[k][l][0] - i < 1 && centerPoints[k][l][0] - i >= 0) || (i - centerPoints[k][l][0] < 2 && i - centerPoints[k][l][0] >= 0))
                                && ((centerPoints[k][l][1] - j < 5 && centerPoints[k][l][1] - j >= 0) || (j - centerPoints[k][l][1] < 5 && j - centerPoints[k][l][1] >= 0))))
                            toPrint[i][j] = getTileColor(tilesToShow[k][l]) + toPrint[i][j] + ANSI_RESET;
                    }
                }
            }
        }
    }

    private String getTileColor(Tile tile) {
        if (tile == null)
            return ANSI_GREY_BACKGROUND;
        String mode = tile.getMode().getTileName().getName();
        if (mode.equals(TileModeEnum.desert.getName()))
            return ANSI_LIGHT_YELLOW_BACKGROUND;
        if (mode.equals(TileModeEnum.grassland.getName()))
            return ANSI_GREEN_BACKGROUND;
        if (mode.equals(TileModeEnum.hill.getName()))
            return ANSI_YELLOW_BACKGROUND;
        if (mode.equals(TileModeEnum.ocean.getName()))
            return ANSI_BLUE_BACKGROUND;
        if (mode.equals(TileModeEnum.snow.getName()))
            return ANSI_WHITE_BACKGROUND;
        if (mode.equals(TileModeEnum.PLAIN.getName()))
            return ANSI_LIGHT_GREEN_BACKGROUND;
        if (mode.equals(TileModeEnum.mountain.getName()))
            return ANSI_RED_BACKGROUND;
        if (mode.equals(TileModeEnum.tundra.getName()))
            return ANSI_CYAN_BACKGROUND;
        return null;
    }

    private void setCooridante(String[][] toPrint, int iCoordinate, int jCoordinate, int[][][] centerPoints, Tile[][] tilesToShow) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 6; j++) {
                if (tilesToShow[i][j] != null) {
                    int iCoordinateToPrint = this.gameMap.getIndexI(tilesToShow[i][j]);
                    int jCoordinateToPrint = this.gameMap.getIndexJ(tilesToShow[i][j]);
                    toPrint[centerPoints[i][j][0] - 1][centerPoints[i][j][1]] = ",";
                    if (iCoordinateToPrint < 10) {
                        toPrint[centerPoints[i][j][0] - 1][centerPoints[i][j][1] - 1] = Integer.toString(iCoordinateToPrint);
                    } else {
                        toPrint[centerPoints[i][j][0] - 1][centerPoints[i][j][1] - 1] = Integer.toString(iCoordinateToPrint % 10);
                        toPrint[centerPoints[i][j][0] - 1][centerPoints[i][j][1] - 2] = Integer.toString(iCoordinateToPrint / 10);
                    }
                    if (jCoordinateToPrint < 10) {
                        toPrint[centerPoints[i][j][0] - 1][centerPoints[i][j][1] + 1] = Integer.toString(jCoordinateToPrint);
                    } else {
                        toPrint[centerPoints[i][j][0] - 1][centerPoints[i][j][1] + 2] = Integer.toString(jCoordinateToPrint % 10);
                        toPrint[centerPoints[i][j][0] - 1][centerPoints[i][j][1] + 1] = Integer.toString(jCoordinateToPrint / 10);
                    }
                }
            }
        }
    }

    private void setPlayerTag(String[][] toPrint, int[][][] centerPoints, int playerNumber, Tile[][] tilesToShow) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 6; j++) {
                if (tilesToShow[i][j] != null) {
                    int centerICoordinates = centerPoints[i][j][0];
                    int centerJCoordinates = centerPoints[i][j][1];
                    toPrint[centerICoordinates - 2][centerJCoordinates] =
                            getPlayerColor(playerNumber) + Character.toString(playerNumber + 'A');
                }
            }
        }
    }

    private String getPlayerColor(int playerNumber) {
        if (playerNumber == 0) return ANSI_PURPLE_BOLD;
        else if (playerNumber == 1) return ANSI_BLUE_BOLD;
        else if (playerNumber == 2) return ANSI_RED_BOLD;
        else if (playerNumber == 3) return ANSI_GREEN_BOLD;
        else if (playerNumber == 4) return ANSI_CYAN_BOLD;
        else if (playerNumber == 5) return ANSI_BLACK_BOLD;
        return null;
    }

    private void setRivers(String[][] toPrint, int[][][] centerPoints, Tile[][] tilesToShow) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 6; j++) {
                for (int k = 0; k < 3; k++) {
                    for (int l = 0; l < 6; l++) {
                        if (tilesToShow[i][j] != null && tilesToShow[k][l] != null)
                            if (River.hasRiver(tilesToShow[i][j], tilesToShow[k][l]))
                                setRiverOfTiles(toPrint, centerPoints[i][j], centerPoints[k][l]);
                    }
                }
            }
        }
    }

    private void setRiverOfTiles(String[][] toPrint, int[] firstCenterCoordinate, int[] secondCenterCoordinates) {
        int iCoordinateDifference = firstCenterCoordinate[0] - secondCenterCoordinates[0];
        int jCoordinateDifference = firstCenterCoordinate[1] - secondCenterCoordinates[1];
        if (iCoordinateDifference * jCoordinateDifference > 0)
            setRiverType1(toPrint, firstCenterCoordinate, secondCenterCoordinates);
        else if (iCoordinateDifference * jCoordinateDifference < 0)
            setRiverType2(toPrint, firstCenterCoordinate, secondCenterCoordinates);
    }

    private void setRiverType1(String[][] toPrint, int[] firstCenterCoordinate, int[] secondCenterCoordinate) {
        toPrint[(firstCenterCoordinate[0] + secondCenterCoordinate[0]) / 2 + 1][(firstCenterCoordinate[1] + secondCenterCoordinate[1]) / 2] =
                ANSI_BLUE_BACKGROUND + toPrint[(firstCenterCoordinate[0] + secondCenterCoordinate[0]) / 2 + 1][(firstCenterCoordinate[1] + secondCenterCoordinate[1]) / 2] +
                        ANSI_RESET;
        toPrint[(firstCenterCoordinate[0] + secondCenterCoordinate[0]) / 2][(firstCenterCoordinate[1] + secondCenterCoordinate[1]) / 2 + 1] =
                ANSI_BLUE_BACKGROUND + toPrint[(firstCenterCoordinate[0] + secondCenterCoordinate[0]) / 2][(firstCenterCoordinate[1] + secondCenterCoordinate[1]) / 2 + 1] +
                        ANSI_RESET;
        toPrint[(firstCenterCoordinate[0] + secondCenterCoordinate[0]) / 2 + 2][(firstCenterCoordinate[1] + secondCenterCoordinate[1]) / 2 - 1] =
                ANSI_BLUE_BACKGROUND + toPrint[(firstCenterCoordinate[0] + secondCenterCoordinate[0]) / 2 + 2][(firstCenterCoordinate[1] + secondCenterCoordinate[1]) / 2 - 1] +
                        ANSI_RESET;
    }

    private void setRiverType2(String[][] toPrint, int[] firstCenterCoordinate, int[] secondCenterCoordinate) {
        toPrint[(firstCenterCoordinate[0] + secondCenterCoordinate[0]) / 2 + 1][(firstCenterCoordinate[1] + secondCenterCoordinate[1]) / 2] =
                ANSI_BLUE_BACKGROUND + toPrint[(firstCenterCoordinate[0] + secondCenterCoordinate[0]) / 2 + 1][(firstCenterCoordinate[1] + secondCenterCoordinate[1]) / 2] +
                        ANSI_RESET;
        toPrint[(firstCenterCoordinate[0] + secondCenterCoordinate[0]) / 2][(firstCenterCoordinate[1] + secondCenterCoordinate[1]) / 2 - 1] =
                ANSI_BLUE_BACKGROUND + toPrint[(firstCenterCoordinate[0] + secondCenterCoordinate[0]) / 2][(firstCenterCoordinate[1] + secondCenterCoordinate[1]) / 2 - 1] +
                        ANSI_RESET;
        toPrint[(firstCenterCoordinate[0] + secondCenterCoordinate[0]) / 2 + 2][(firstCenterCoordinate[1] + secondCenterCoordinate[1]) / 2 + 1] =
                ANSI_BLUE_BACKGROUND + toPrint[(firstCenterCoordinate[0] + secondCenterCoordinate[0]) / 2 + 2][(firstCenterCoordinate[1] + secondCenterCoordinate[1]) / 2 + 1] +
                        ANSI_RESET;
    }

    private void setFeatures(String[][] toPrint, int[][][] centerPoints, Tile[][] tilesToShow) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 6; j++) {
                if (tilesToShow[i][j] != null) {
                    if (tilesToShow[i][j].hasFeature(TileFeatureEnum.plain))
                        addPlainFeature(toPrint, centerPoints[i][j][0], centerPoints[i][j][1]);
                    else if (tilesToShow[i][j].hasFeature(TileFeatureEnum.forest))
                        addForestFeature(toPrint, centerPoints[i][j][0], centerPoints[i][j][1]);
                    else if (tilesToShow[i][j].hasFeature(TileFeatureEnum.denceForest))
                        addDenseForestFeature(toPrint, centerPoints[i][j][0], centerPoints[i][j][1]);
                    else if (tilesToShow[i][j].hasFeature(TileFeatureEnum.ice))
                        addIceFeature(toPrint, centerPoints[i][j][0], centerPoints[i][j][1]);
                    else if (tilesToShow[i][j].hasFeature(TileFeatureEnum.oasis))
                        addOasisFeature(toPrint, centerPoints[i][j][0], centerPoints[i][j][1]);
                    else if (tilesToShow[i][j].hasFeature(TileFeatureEnum.swamp))
                        addSwampFeature(toPrint, centerPoints[i][j][0], centerPoints[i][j][1]);
                }
            }
        }
    }

    private void addPlainFeature(String[][] toPrint, int centerICoordinate, int centerJCoordinate) {
        toPrint[centerICoordinate + 1][centerJCoordinate - 1] = ANSI_BOLD + "j" + ANSI_RESET;
        toPrint[centerICoordinate + 1][centerJCoordinate] = ANSI_BOLD + "o" + ANSI_RESET;
        toPrint[centerICoordinate + 1][centerJCoordinate + 1] = ANSI_BOLD + "l" + ANSI_RESET;
    }

    private void addForestFeature(String[][] toPrint, int centerICoordinate, int centerJCoordinate) {
        toPrint[centerICoordinate + 1][centerJCoordinate] = ANSI_BOLD + "j" + ANSI_RESET;
    }

    private void addDenseForestFeature(String[][] toPrint, int centerICoordinate, int centerJCoordinate) {
        toPrint[centerICoordinate + 1][centerJCoordinate - 1] = ANSI_BOLD + "j" + ANSI_RESET;
        toPrint[centerICoordinate + 1][centerJCoordinate] = ANSI_BOLD + "a" + ANSI_RESET;
    }

    private void addIceFeature(String[][] toPrint, int centerICoordinate, int centerJCoordinate) {
        toPrint[centerICoordinate + 1][centerJCoordinate - 2] = ANSI_BOLD + "y" + ANSI_RESET;
        toPrint[centerICoordinate + 1][centerJCoordinate - 1] = ANSI_BOLD + "a" + ANSI_RESET;
        toPrint[centerICoordinate + 1][centerJCoordinate] = ANSI_BOLD + "k" + ANSI_RESET;
        toPrint[centerICoordinate + 1][centerJCoordinate + 1] = ANSI_BOLD + "h" + ANSI_RESET;
    }

    private void addOasisFeature(String[][] toPrint, int centerICoordinate, int centerJCoordinate) {
        toPrint[centerICoordinate + 1][centerJCoordinate - 1] = ANSI_BOLD + "v" + ANSI_RESET;
        toPrint[centerICoordinate + 1][centerJCoordinate] = ANSI_BOLD + "a" + ANSI_RESET;
    }

    private void addSwampFeature(String[][] toPrint, int centerICoordinate, int centerJCoordinate) {
        toPrint[centerICoordinate + 1][centerJCoordinate - 3] = ANSI_BOLD + "m" + ANSI_RESET;
        toPrint[centerICoordinate + 1][centerJCoordinate - 2] = ANSI_BOLD + "o" + ANSI_RESET;
        toPrint[centerICoordinate + 1][centerJCoordinate - 1] = ANSI_BOLD + "r" + ANSI_RESET;
        toPrint[centerICoordinate + 1][centerJCoordinate] = ANSI_BOLD + "d" + ANSI_RESET;
        toPrint[centerICoordinate + 1][centerJCoordinate + 1] = ANSI_BOLD + "a" + ANSI_RESET;
        toPrint[centerICoordinate + 1][centerJCoordinate + 2] = ANSI_BOLD + "b" + ANSI_RESET;
    }

    private void setUnits(String[][] toPrint, int[][][] centerPoints, Tile[][] tilesToShow) {
        setCombatUnits(toPrint, centerPoints, tilesToShow);
        setNoncombatUnits(toPrint, centerPoints, tilesToShow);
    }

    private void setCombatUnits(String[][] toPrint, int[][][] centerPoints, Tile[][] tilesToShow) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 6; j++) {
                if (tilesToShow[i][j] != null && tilesToShow[i][j].getCombatUnits() != null)
                    addCombatUnits(toPrint, centerPoints[i][j][0], centerPoints[i][j][1], tilesToShow[i][j]);
            }
        }
    }

    private void addCombatUnits(String[][] toPrint, int centerICoordinate, int centerJCoordinates, Tile tile) {
        String unitName = tile.getCombatUnits().getUnitNameEnum().getName();
        int playerNumber = Player.findCombatUnitOwner(this.players, tile.getCombatUnits());
        toPrint[centerICoordinate][centerJCoordinates + 1] = getPlayerColor(playerNumber) + unitName.charAt(0) + ANSI_RESET;
        toPrint[centerICoordinate][centerJCoordinates + 2] = getPlayerColor(playerNumber) + unitName.charAt(1) + ANSI_RESET;
    }

    private void setNoncombatUnits(String[][] toPrint, int[][][] centerPoints, Tile[][] tilesToShow) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 6; j++) {
                if (tilesToShow[i][j] != null && tilesToShow[i][j].getNoneCombatUnits() != null)
                    addNoncombatUnits(toPrint, centerPoints[i][j][0], centerPoints[i][j][1], tilesToShow[i][j]);
            }
        }
    }

    private void addNoncombatUnits(String[][] toPrint, int centerICoordinate, int centerJCoordinates, Tile tile) {
        String unitName = tile.getNoneCombatUnits().getUnitNameEnum().getName();
        int playerNumber = Player.findNoncombatUnits(this.players, tile.getNoneCombatUnits());
        toPrint[centerICoordinate][centerJCoordinates - 2] = getPlayerColor(playerNumber) + unitName.charAt(0) + ANSI_RESET;
        toPrint[centerICoordinate][centerJCoordinates - 1] = getPlayerColor(playerNumber) + unitName.charAt(1) + ANSI_RESET;
    }

    private void setResources(String[][] toPrint, int[][][] centerPoints, Tile[][] tilesToShow, Player player) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 6; j++) {
                if (tilesToShow[i][j] != null && tilesToShow[i][j].getResource() != null) {
                    addResource(toPrint, centerPoints[i][j][0], centerPoints[i][j][1], tilesToShow[i][j], player);
                }
            }
        }
    }

    private void addResource(String[][] toPrint, int centerICoordinate, int centerJCoordinate, Tile tile, Player player) {
        //TODO : add technology condition
        String resourceName = tile.getResource().getResourceName().getName();
        if (resourceName.length() == 3) {
            toPrint[centerICoordinate + 2][centerJCoordinate - 1] = Character.toString(resourceName.charAt(0));
            toPrint[centerICoordinate + 2][centerJCoordinate] = Character.toString(resourceName.charAt(1));
            toPrint[centerICoordinate + 2][centerJCoordinate + 1] = Character.toString(resourceName.charAt(2));
        } else if (resourceName.length() == 4) {
            toPrint[centerICoordinate + 2][centerJCoordinate - 2] = Character.toString(resourceName.charAt(0));
            toPrint[centerICoordinate + 2][centerJCoordinate - 1] = Character.toString(resourceName.charAt(1));
            toPrint[centerICoordinate + 2][centerJCoordinate] = Character.toString(resourceName.charAt(2));
            toPrint[centerICoordinate + 2][centerJCoordinate + 1] = Character.toString(resourceName.charAt(3));
        } else if (resourceName.length() == 5) {
            toPrint[centerICoordinate + 2][centerJCoordinate - 2] = Character.toString(resourceName.charAt(0));
            toPrint[centerICoordinate + 2][centerJCoordinate - 1] = Character.toString(resourceName.charAt(1));
            toPrint[centerICoordinate + 2][centerJCoordinate] = Character.toString(resourceName.charAt(2));
            toPrint[centerICoordinate + 2][centerJCoordinate + 1] = Character.toString(resourceName.charAt(3));
            toPrint[centerICoordinate + 2][centerJCoordinate + 2] = Character.toString(resourceName.charAt(4));
        } else if (resourceName.length() == 6) {
            toPrint[centerICoordinate + 2][centerJCoordinate - 3] = Character.toString(resourceName.charAt(0));
            toPrint[centerICoordinate + 2][centerJCoordinate - 2] = Character.toString(resourceName.charAt(1));
            toPrint[centerICoordinate + 2][centerJCoordinate - 1] = Character.toString(resourceName.charAt(2));
            toPrint[centerICoordinate + 2][centerJCoordinate] = Character.toString(resourceName.charAt(3));
            toPrint[centerICoordinate + 2][centerJCoordinate + 1] = Character.toString(resourceName.charAt(4));
            toPrint[centerICoordinate + 2][centerJCoordinate + 2] = Character.toString(resourceName.charAt(5));
        } else {
            toPrint[centerICoordinate + 2][centerJCoordinate - 3] = Character.toString(resourceName.charAt(0));
            toPrint[centerICoordinate + 2][centerJCoordinate - 2] = Character.toString(resourceName.charAt(1));
            toPrint[centerICoordinate + 2][centerJCoordinate - 1] = Character.toString(resourceName.charAt(2));
            toPrint[centerICoordinate + 2][centerJCoordinate] = Character.toString(resourceName.charAt(3));
            toPrint[centerICoordinate + 2][centerJCoordinate + 1] = Character.toString(resourceName.charAt(4));
            toPrint[centerICoordinate + 2][centerJCoordinate + 2] = Character.toString(resourceName.charAt(5));
            toPrint[centerICoordinate + 2][centerJCoordinate + 3] = Character.toString(resourceName.charAt(6));
        }
    }

    private void setImprovements(String[][] toPrint, int[][][] centerPoints, Tile[][] tilesToShow, Player player) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 6; j++) {
                if (tilesToShow[i][j] != null && tilesToShow[i][j].getImprovement() != null) {
                    addImprovement(toPrint, centerPoints[i][j][0], centerPoints[i][j][1], tilesToShow[i][j], player);
                }
            }
        }
    }

    private void addImprovement(String[][] toPrint, int centerICoordinate, int centerJCoordinate, Tile tile, Player player) {
        // TODO : add technology condition
        String improvementName = tile.getImprovement().getImprovementName().getName();
        if (!improvementName.equals(TileImprovementEnum.farming.getName())) {
            toPrint[centerICoordinate + 3][centerJCoordinate - 2] = ANSI_UNDERLINED + Character.toString(improvementName.charAt(0)) + ANSI_RESET;
            toPrint[centerICoordinate + 3][centerJCoordinate - 1] = ANSI_UNDERLINED + Character.toString(improvementName.charAt(1)) + ANSI_RESET;
            toPrint[centerICoordinate + 3][centerJCoordinate] = ANSI_UNDERLINED + Character.toString(improvementName.charAt(2)) + ANSI_RESET;
            toPrint[centerICoordinate + 3][centerJCoordinate + 1] = ANSI_UNDERLINED + Character.toString(improvementName.charAt(3)) + ANSI_RESET;
            toPrint[centerICoordinate + 3][centerJCoordinate + 2] = ANSI_UNDERLINED + " " + ANSI_RESET;
        } else {
            toPrint[centerICoordinate + 3][centerJCoordinate - 2] = ANSI_UNDERLINED + "f" + ANSI_RESET;
            toPrint[centerICoordinate + 3][centerJCoordinate - 1] = ANSI_UNDERLINED + "r" + ANSI_RESET;
            toPrint[centerICoordinate + 3][centerJCoordinate] = ANSI_UNDERLINED + "i" + ANSI_RESET;
            toPrint[centerICoordinate + 3][centerJCoordinate + 1] = ANSI_UNDERLINED + "n" + ANSI_RESET;
            toPrint[centerICoordinate + 3][centerJCoordinate + 2] = ANSI_UNDERLINED + "g" + ANSI_RESET;
        }
    }
}