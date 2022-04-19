package views;

import models.*;
import controllers.*;
import models.Tile.Tile;

import java.util.ArrayList;

public class PlayGameMenu extends Menu {
    ArrayList<Player> players;
    GameMap gamemap;

    public PlayGameMenu(ArrayList<Player> players, UsersDatabase usersDatabase) {
        super(usersDatabase);
        this.players = new ArrayList<>();
        this.players.add(new Player(usersDatabase.getUserByUsername("ilya")));
        this.players.add(new Player(usersDatabase.getUserByUsername("paria")));
        this.players.add(new Player(usersDatabase.getUserByUsername("mammad")));
        this.players.add(new Player(usersDatabase.getUserByUsername("ali")));
        gamemap = new GameMap();
    }

    @Override
    public void run() {
        System.out.println("new game");

        super.scanner.nextLine();
    }

    public void showMap(Player player) {

    }

    public void showMap(int iCordinate, int jCordinate) {
        Tile[][] mapToShow = new Tile[6][3];
        for (int i = iCordinate; i < iCordinate + 6; i++) {
            for (int j = jCordinate; j < jCordinate + 3; j++) {

            }
        }
    }


}
