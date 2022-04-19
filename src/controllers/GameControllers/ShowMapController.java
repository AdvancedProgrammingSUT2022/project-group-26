package controllers.GameControllers;

import models.GameMap;
import models.Player;
import models.Tile.Tile;
import org.jetbrains.annotations.NotNull;

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
    public static final String ANSI_WHITE_BACKGROUND = "\u001B[47m";

    public ShowMapController(GameMap gameMap, ArrayList<Player> players) {
        this.gameMap = gameMap;
        this.players = players;
    }

    public void setArrayToPrint(int iCoordinate, int jCoordinate, Tile[][] tilesToShow) {
        for (int i = iCoordinate; i < iCoordinate + 6; i++)
            for (int j = jCoordinate; j < jCoordinate + 3; j++)
                tilesToShow[i - iCoordinate][j - jCoordinate] = this.gameMap.getMap()[i][j];
    }

    public void setToPrintStrings(String[][] toPrint, Tile[][] tilesToShow) {
        setAllSpace(toPrint);
        setUpDownPolygon(toPrint);
        setLeftRightPolygon(toPrint);
    }

    private void setAllSpace(@NotNull String[][] toPrint) {
        for (int i = 0; i < 80; i++)
            for (int j = 0; j < 80; j++)
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
}