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

    public Output addPlayer(String username) {
        if (users.size() == numberOfPlayers)
            return Output.UNABLE_TO_ADD_MORE_PLAYERS;
        for (int i = 0; i<DataBase.getInstance().getUsersDatabase().getUsers().size(); i++)
            if (DataBase.getInstance().getUsersDatabase().getUsers().get(i).getUsername().equals(username) && !userIsInGame(DataBase.getInstance().getUsersDatabase().getUsers().get(i)))
                users.add(DataBase.getInstance().getUsersDatabase().getUsers().get(i));
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
                if (!user.getUsername().equals(network.getLoggedInUser().getUsername()) && !userIsInGame(user))
                    users.add(user);
        return users;
    }

    public boolean userIsInGame(User user){
        for (int i=0; i<users.size(); i++)
            if (users.get(i).equals(user))
                return true;
        return false;
    }

    public void clearPlayers(Network network) {
        users.clear();
        users.add(network.getLoggedInUser());
    }
}
