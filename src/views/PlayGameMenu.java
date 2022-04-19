package views;

import controllers.GameControllers.ShowMapController;
import models.*;
import controllers.*;
import models.Tile.Tile;

import java.util.ArrayList;

public class PlayGameMenu extends Menu {
    ArrayList<Player> players;
    GameMap gamemap;
    ShowMapController showMapController;

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
        System.out.println("new game");
        showMap(0,0);
    }

    public void showMap(Player player) {

    }

    public void showMap(int iCordinate, int jCordinate) {
        Tile[][] tilesToShow = new Tile[6][3];
        this.showMapController.setArrayToPrint(iCordinate, jCordinate, tilesToShow);
        String[][] toPrint = new String[80][80];
        this.showMapController.setToPrintStrings(toPrint, tilesToShow);
        for (int i = 0; i <= 21; i++) {
            for (int j = 0; j < 51; j++) {
                System.out.print(toPrint[i][j]);
            }
            System.out.println();
        }
    }


}
