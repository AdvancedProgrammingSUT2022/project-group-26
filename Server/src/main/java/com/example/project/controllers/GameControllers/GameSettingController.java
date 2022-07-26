package com.example.project.controllers.GameControllers;

import com.example.project.models.*;

import java.io.IOException;
import java.util.ArrayList;

public class GameSettingController {
    private Network network;

    public GameSettingController(Network network) {
        this.network = network;
        Game.getNetworksInGame().clear();
        Game.getNetworksInGame().add(network);
    }

    private int numberOfPlayers;

    public Output addPlayerStatues(String username) {
        if (Game.getNetworksInGame().size() == numberOfPlayers)
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
        for (Network network1 : DataBase.getOnlineNetworks()) {
            if (network1.getLoggedInUser() != null)
                if (network1.getLoggedInUser().getUsername().startsWith(username) && network1.isOnMainMenu()) {
                    if (!network1.getLoggedInUser().getUsername().equals(network.getLoggedInUser().getUsername())
                            && !userIsInGame(network1))
                        users.add(network1.getLoggedInUser());
                }
        }
        return users;
    }

    public boolean userIsInGame(Network network) {
        return Game.getNetworksInGame().contains(network);
    }

    public void clearPlayers(Network network) {
        Game.getNetworksInGame().clear();
        Game.getNetworksInGame().add(network);
    }

    public void sendInvitationRequest(String username, Network network) {
        for (int i = 0; i < DataBase.getOnlineNetworks().size(); i++)
            if (DataBase.getOnlineNetworks().get(i).getLoggedInUser().getUsername().equals(username))
                if (!Game.getNetworksInGame().contains(DataBase.getOnlineNetworks().get(i))) {
                    try {
                        DataBase.getOnlineNetworks().get(i).sendResponse(
                                new Response(Output.INVITATION_REQUEST, network.getLoggedInUser().getUsername()));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                }
    }

    public ArrayList<User> getUsers() {
        ArrayList<User> users = new ArrayList<>();
        for (Network network : Game.getNetworksInGame())
            users.add(network.getLoggedInUser());
        return users;
    }

    public void startGame() {
        for (Network network1 : Game.getNetworksInGame()) {
            if (network1 != network)
                GameNetworkData.sendGame(network1);
        }
    }
}
