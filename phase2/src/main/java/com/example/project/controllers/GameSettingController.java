package com.example.project.controllers;

import com.example.project.models.DataBase;
import com.example.project.models.Game;
import com.example.project.models.User;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;

import java.util.ArrayList;

public class GameSettingController {
    private static GameSettingController instance;

    public static void setNull(){
        instance = null;
    }

    public static GameSettingController getInstance() {
        if (instance == null) instance = new GameSettingController();
        return instance;
    }

    private GameSettingController() {
        users.add(DataBase.getInstance().getLoggedInUser());
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    private ArrayList<User> users = new ArrayList<>();
    private int numberOfPlayers;

    public Output addPlayer(User user) {
        if (users.size() == numberOfPlayers)
            return Output.UNABLE_TO_ADD_MORE_PLAYERS;
        if (users.contains(user))
            return Output.ALREADY_IN_GAME;
        users.add(user);
        return null;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    public ArrayList<User> showUsernamesStartsWithString(String username) {
        ArrayList<User> users = new ArrayList<>();
        for (User user : DataBase.getInstance().getUsersDatabase().getUsers())
            if (user.getUsername().startsWith(username))
                if (user != DataBase.getInstance().getLoggedInUser())
                    users.add(user);
        return users;
    }

    public void clearPlayers() {
        users.clear();
        users.add(DataBase.getInstance().getLoggedInUser());
    }

    public Output startGame(boolean isAutoSaveSelected, ChoiceBox autoSaveType) {
        if (users.size() != numberOfPlayers)
            return Output.INVALID_NUMBER_OF_PLAYERS;
        Game.getInstance().startGame(users, isAutoSaveSelected, autoSaveType);
        return null;
    }
}
