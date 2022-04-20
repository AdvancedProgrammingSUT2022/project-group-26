package controllers.GameControllers;

import models.GameMap;
import models.Player;
import models.Tile.Tile;
import models.Tile.TileModeEnum;

import java.util.ArrayList;

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

    public void setArrayToPrint(int iCoordinate, int jCoordinate, Tile[][] tilesToShow) {
        for (int i = iCoordinate; i < iCoordinate + 3; i++)
            for (int j = jCoordinate; j < jCoordinate + 6; j++)
                tilesToShow[i - iCoordinate][j - jCoordinate] = this.gameMap.getMap()[i][j];
    }

    public void setToPrintStrings(String[][] toPrint, Tile[][] tilesToShow, int iCoordinate, int jCoordinate) {
        setAllSpace(toPrint);
        setUpDownPolygon(toPrint);
        setLeftRightPolygon(toPrint);
        int[][][] centerPoints = getCenters();
        setCooridante(toPrint, iCoordinate, jCoordinate, centerPoints);
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

    private void setCooridante(String[][] toPrint, int iCoordinate, int jCoordinate, int[][][] centerPoints) {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 6; j++) {
                int iCoordinateToPrint = iCoordinate + i;
                int jCoordinateToPrint = jCoordinate + j;
                toPrint[centerPoints[i][j][0]][centerPoints[i][j][1]] = ",";
                if (iCoordinateToPrint < 10) {
                    toPrint[centerPoints[i][j][0]][centerPoints[i][j][1] - 1] = Integer.toString(iCoordinateToPrint);
                } else {
                    toPrint[centerPoints[i][j][0]][centerPoints[i][j][1] - 1] = Integer.toString(iCoordinateToPrint % 10);
                    toPrint[centerPoints[i][j][0]][centerPoints[i][j][1] - 2] = Integer.toString(iCoordinateToPrint / 10);
                }
                if (jCoordinateToPrint < 10) {
                    toPrint[centerPoints[i][j][0]][centerPoints[i][j][1] + 1] = Integer.toString(jCoordinateToPrint);
                } else {
                    toPrint[centerPoints[i][j][0]][centerPoints[i][j][1] + 2] = Integer.toString(jCoordinateToPrint % 10);
                    toPrint[centerPoints[i][j][0]][centerPoints[i][j][1] + 1] = Integer.toString(jCoordinateToPrint / 10);
                }
            }
        }
    }


}