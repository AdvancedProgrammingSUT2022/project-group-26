package views;

import models.*;
import controllers.*;

import java.util.ArrayList;

public class PlayGameMenu extends Menu {
    ArrayList<Player> players;

    public PlayGameMenu(ArrayList<Player> players, UsersDatabase usersDatabase) {
        super(usersDatabase);
        this.players = players;
    }

    @Override
    public void run() {
    }

    public void showMap(Player player) {
    }

}
