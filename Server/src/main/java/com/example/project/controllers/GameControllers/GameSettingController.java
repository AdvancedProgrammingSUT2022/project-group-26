package com.example.project.controllers.GameControllers;

import com.example.project.models.*;

import java.util.ArrayList;

public class GameSettingController {
    private static GameSettingController instance;

    public static void setNull(){
        instance = null;
    }

    public GameSettingController(Network network) {
        users.add(network.getLoggedInUser());
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    private ArrayList<User> users = new ArrayList<>();
    private int numberOfPlayers;

    public Output addPlayer(String string) {
        if (users.size() == numberOfPlayers)
            return Output.UNABLE_TO_ADD_MORE_PLAYERS;

        return null;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    public ArrayList<User> showUsernamesStartsWithString(String username, Network network) {
        ArrayList<User> users = new ArrayList<>();
        for (User user : DataBase.getInstance().getUsersDatabase().getUsers())
            if (user.getUsername().startsWith(username))
                if (user.getUsername() != network.getLoggedInUser().getUsername())
                    users.add(user);
        return users;
    }

    public void clearPlayers() {
        users.clear();
        users.add(DataBase.getInstance().getLoggedInUser());
    }
}
