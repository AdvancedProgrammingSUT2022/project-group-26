package com.example.project.controllers;

import com.example.project.models.Player;
import com.example.project.models.User;
import com.example.project.models.UsersDatabase;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainMenuController {
    private final String ADD_PLAYER = "\\s*--player(?<number>\\d+) (?<username>\\S+)";

    private User user;
    private UsersDatabase usersDatabase;

    public MainMenuController(User user, UsersDatabase usersDatabase) {
        this.user = user;
        this.usersDatabase = usersDatabase;
    }

    public Output isValidGameDifficulty(Matcher matcher) {
        String difficulty  = matcher.group("difficulty");
        if(!((difficulty.equals("easy")) || (difficulty.equals("medium")) || (difficulty.equals("hard"))))
            return Output.INVALID_DIFFICULTY;
        return null;
    }

    public Output isValidMenu(Matcher matcher) {
        String menuName = matcher.group("menuName");
        if (!menuName.equals("Profile"))
            return Output.INVALID_MENU;
        return null;
    }

    public ArrayList<User> sortUsersScores(ArrayList<User> users) {
        for (int i = 0; i < users.size(); i++) {
            for (int j = i + 1; j < users.size(); j++) {
                if (users.get(i).getHighScore() < users.get(j).getHighScore()) {
                    User temp = users.get(i);
                    users.set(i, users.get(j));
                    users.set(j, temp);
                }
            }
        }
        return users;
    }

    public Output checkPlayers(String input) {
        Matcher matcher = Pattern.compile(ADD_PLAYER).matcher(input);
        int playersCount = 0;
        while (matcher.find()) {
            if (usersDatabase.getUserByUsername(matcher.group("username")) == null) return Output.INCORRECT_USERNAME;
            if (Integer.parseInt(matcher.group("number")) != (playersCount + 1)) return Output.INVALID_COMMAND;
            playersCount++;
        }
        if (playersCount > 6) return Output.EXTRA_PLAYER_NUMBERS;
        if (playersCount < 2) return Output.NOT_ENOUGH_INPUT;
        return Output.VALID_PLAYERS;
    }

    public ArrayList<Player> returnPlayers(String input) {
        Player tempPlayer;
        ArrayList<Player> players = new ArrayList<>();
        Matcher matcher = Pattern.compile(ADD_PLAYER).matcher(input);
        while (matcher.find()) {
            tempPlayer = new Player(usersDatabase.getUserByUsername(matcher.group("username")));
            players.add(tempPlayer);
        }
        return players;
    }

    public int getStartGameDifficulty(Matcher matcher) {
        String difficulty = matcher.group("difficulty");
        switch (difficulty) {
            case "easy":
                return 0;
            case "medium":
                return 1;
            case "hard":
                return 2;
        }
        return -1;
    }
}