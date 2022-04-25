package views;

import controllers.GameControllers.GameMenuCommandController;
import controllers.GameControllers.PlayGameMenuController;
import controllers.GameControllers.ShowMapController;
import models.*;
import controllers.*;
import models.Tile.Tile;

import java.util.ArrayList;
import java.util.regex.Matcher;

public class PlayGameMenu extends Menu {
    ArrayList<Player> players;
    GameMap gamemap;
    ShowMapController showMapController;
    GameMenuCommandController gameMenuCommandController = new GameMenuCommandController();

    public PlayGameMenu(ArrayList<Player> players, UsersDatabase usersDatabase) {
        super(usersDatabase);
        this.players = players;
        gamemap = new GameMap(this.players);
        this.showMapController = new ShowMapController(gamemap, players);
    }

    @Override
    public void run() {
        String input;
        int playerNumber = 0;
        while (true) {
            players.get(playerNumber).updateMap(gamemap);
            Matcher matcher;
            input = super.scanner.nextLine();
            if ((matcher = getCommandMatcher(input, PlayGameCommandsRegex.SHOW_MAP.toString())) != null) {
                showMapCommand(matcher, playerNumber);
            } else if ((matcher = getCommandMatcher(input, PlayGameCommandsRegex.END_GAME.toString())) != null) {
                return;
            } else if ((matcher = getCommandMatcher(input, PlayGameCommandsRegex.SHOW_MENU.toString())) != null) {
                System.out.println("Game Menu");
            } else if ((matcher = getCommandMatcher(input, PlayGameCommandsRegex.END_TURN.toString())) != null) {
                playerNumber = gameMenuCommandController.nextPlayer(playerNumber, this.players);
            } else if ((matcher = getCommandMatcher(input, PlayGameCommandsRegex.MOVE_COMBAT_UNIT.toString())) != null) {
                System.out.println(gameMenuCommandController.moveCombatUnit(matcher, gamemap, players.get(playerNumber)));
            } else if ((matcher = getCommandMatcher(input, PlayGameCommandsRegex.MOVE_CIVILIAN.toString())) != null) {
                gameMenuCommandController.moveCivilian(matcher, gamemap, players.get(playerNumber));
            } else {
                System.out.println("invalid command!");
            }
        }
    }

    public void showMap(int iCoordinate, int jCoordinate, int playerNumber) {
        Player player = this.players.get(playerNumber);
        player.updateMap(this.gamemap);
        Tile[][] tilesToShow = new Tile[3][6];
        this.showMapController.setTileArrayToPrint(iCoordinate, jCoordinate, tilesToShow, player.getGameMap());
        String[][] toPrint = new String[80][80];
        this.showMapController.setToPrintStrings(toPrint, tilesToShow, iCoordinate, jCoordinate, playerNumber);
        for (int i = 0; i <= 21; i++) {
            for (int j = 0; j < 51; j++) {
                System.out.print(toPrint[i][j]);
            }
            System.out.println();
        }
    }

    public void showMapCommand(Matcher matcher, int playerNumber) {
        Output output = this.gameMenuCommandController.showMap(matcher);
        if (output != null) {
            System.out.println(output.toString());
            return;
        } else {
            int iCoordinate = Integer.parseInt(matcher.group("iCoordinate"));
            int jCoordinate = Integer.parseInt(matcher.group("jCoordinate"));
            showMap(iCoordinate, jCoordinate, playerNumber);
            changeDirection(iCoordinate, jCoordinate, playerNumber);
        }
    }

    private void changeDirection(int iCoordinate, int jCoordinate, int playerNumber) {// TODO: clean function
        Output output;
        String input;
        while (true) {
            input = super.scanner.nextLine();
            if (getCommandMatcher(input, PlayGameCommandsRegex.LEFT.toString()) != null) {
                output = gameMenuCommandController.changeShowMapDirection(iCoordinate, jCoordinate - 1);
                if (output != null)
                    System.out.println(output.toString());
                else {
                    jCoordinate--;
                    showMap(iCoordinate, jCoordinate, playerNumber);
                }
            } else if (getCommandMatcher(input, PlayGameCommandsRegex.DOWN.toString()) != null) {
                output = gameMenuCommandController.changeShowMapDirection(iCoordinate + 1, jCoordinate);
                if (output != null)
                    System.out.println(output.toString());
                else {
                    iCoordinate++;
                    showMap(iCoordinate, jCoordinate, playerNumber);
                }
            } else if (getCommandMatcher(input, PlayGameCommandsRegex.RIGHT.toString()) != null) {
                output = gameMenuCommandController.changeShowMapDirection(iCoordinate, jCoordinate + 1);
                if (output != null)
                    System.out.println(output.toString());
                else {
                    jCoordinate++;
                    showMap(iCoordinate, jCoordinate, playerNumber);
                }
            } else if (getCommandMatcher(input, PlayGameCommandsRegex.UP.toString()) != null) {
                output = gameMenuCommandController.changeShowMapDirection(iCoordinate - 1, jCoordinate);
                if (output != null)
                    System.out.println(output.toString());
                else {
                    iCoordinate--;
                    showMap(iCoordinate, jCoordinate, playerNumber);
                }
            } else if (getCommandMatcher(input, PlayGameCommandsRegex.END.toString()) != null) return;
            else System.out.println("invalid command!");
        }
    }
}