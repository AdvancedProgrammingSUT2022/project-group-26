package com.example.project.controllers.GameControllers;

import com.example.project.models.*;

import java.io.IOException;
import java.util.ArrayList;

public class GameSettingController {
    private static GameSettingController instance;

    public static void setNull() {
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

    public Output addPlayerStatues(String username) {
        if (users.size() == numberOfPlayers)
            return Output.UNABLE_TO_ADD_MORE_PLAYERS;
        return null;
    }

    public void addPlayer(String username) {
        for (int i = 0; i < DataBase.getInstance().getUsersDatabase().getUsers().size(); i++)
            if (DataBase.getInstance().getUsersDatabase().getUsers().get(i).getUsername().equals(username) && !userIsInGame(DataBase.getInstance().getUsersDatabase().getUsers().get(i)))
                users.add(DataBase.getInstance().getUsersDatabase().getUsers().get(i));
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
                if (!user.getUsername().equals(network.getLoggedInUser().getUsername()) && !userIsInGame(user) && user.isOnline())
                    users.add(user);
        return users;
    }

    public boolean userIsInGame(User user) {
        return users.contains(user);
    }

    public void clearPlayers(Network network) {
        users.clear();
        users.add(network.getLoggedInUser());
    }

    public void sendInvitationRequest(String username, Network network) {
        for (int i = 0; i < DataBase.getInstance().getUsersDatabase().getUsers().size(); i++)
            if (DataBase.getInstance().getUsersDatabase().getUsers().get(i).getUsername().equals(username)) {
                try {
                    network.sendResponse(new Response(Output.INVITATION_REQUEST, username));
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
    }
}
