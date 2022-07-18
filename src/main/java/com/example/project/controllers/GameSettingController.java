package com.example.project.controllers;

import com.example.project.models.Player;

import java.util.ArrayList;

public class GameSettingController {
    private static GameSettingController instance;

    public static GameSettingController getInstance() {
        if (instance == null) instance = new GameSettingController();
        return instance;
    }

    private ArrayList<Player> players = new ArrayList<>();
    private int numberOfPlayers;

    public Output addPlayer(Player player){
        if(players.size() == numberOfPlayers)
            return Output.UNABLE_TO_ADD_MORE_PLAYERS;
        return null;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }
}
