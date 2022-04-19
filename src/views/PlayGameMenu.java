package views;

import controllers.GameControllers.GameMenuCommandController;
import controllers.GameControllers.PlayGameMenuController;
import controllers.GameControllers.ShowMapController;
import models.*;
import controllers.*;
import models.Tile.Tile;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.regex.Matcher;

public class PlayGameMenu extends Menu {
    ArrayList<Player> players;
    GameMap gamemap;
    ShowMapController showMapController;
    GameMenuCommandController gameMenuController = new GameMenuCommandController();

    public PlayGameMenu(ArrayList<Player> players, UsersDatabase usersDatabase) {
        super(usersDatabase);
        this.players = new ArrayList<>();
        this.players.add(new Player(usersDatabase.getUserByUsername("ilya")));
        this.players.add(new Player(usersDatabase.getUserByUsername("paria")));
        this.players.add(new Player(usersDatabase.getUserByUsername("mammad")));
        this.players.add(new Player(usersDatabase.getUserByUsername("ali")));
        gamemap = new GameMap();
        this.showMapController = new ShowMapController(gamemap, players);
    }

    @Override
    public void run() {
        String input;
        while (true) {
            Matcher matcher;
            input = super.scanner.nextLine();
            if ((matcher = getCommandMatcher(input, PlayGameCommandsRegex.ShowMap.toString())) != null) {
                showMapCommand(matcher);
            } else if ((matcher = getCommandMatcher(input, PlayGameCommandsRegex.endGame.toString())) != null) {
                return;
            } else if ((matcher = getCommandMatcher(input, PlayGameCommandsRegex.showMenu.toString())) != null) {
                System.out.println("Game Menu");
            } else {
                System.out.println("invalid command!");
            }
        }


    }

    public void showMap(@NotNull Matcher matcher) {
        int iCoordinate = Integer.parseInt(matcher.group("iCoordinate"));
        int jCoordinate = Integer.parseInt(matcher.group("jCoordinate"));
        Tile[][] tilesToShow = new Tile[6][3];
        this.showMapController.setArrayToPrint(iCoordinate, jCoordinate, tilesToShow);
        String[][] toPrint = new String[80][80];
        this.showMapController.setToPrintStrings(toPrint, tilesToShow);
        for (int i = 0; i <= 21; i++) {
            for (int j = 0; j < 51; j++) {
                System.out.print(toPrint[i][j]);
            }
            System.out.println();
        }
    }

    public void showMapCommand(Matcher matcher) {
        Output output = this.gameMenuController.showMap(matcher);
        if (output != null) {
            System.out.println(output.toString());
            return;
        } else {
            showMap(matcher);
        }
    }

}