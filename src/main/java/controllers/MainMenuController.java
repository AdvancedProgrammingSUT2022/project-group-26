package controllers;

import models.Player;
import models.User;
import models.UsersDatabase;
import views.PlayGameMenu;
import views.ProfileMenu;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainMenuController {
    private final String ADD_PLAYER = "\\s*--player(?<number>\\d) (?<username>\\S+)";
    private User user;
    private UsersDatabase usersDatabase;

    public MainMenuController(User user, UsersDatabase usersDatabase) {
        this.user = user;
        this.usersDatabase = usersDatabase;
    }

    public Output isValidMenu(Matcher matcher) {
        String menuName = matcher.group("menuName");
        if (!menuName.equals("Profile"))
            return Output.invalidMenu;
        return null;
    }

    public ArrayList<User> sortUsers(ArrayList<User> users) {
        return users;
    }

    public void enterMenu() {
        ProfileMenu profileMenu = new ProfileMenu(user, usersDatabase);
        profileMenu.run();
    }

    public void enterGameMenu(ArrayList<Player> players, UsersDatabase usersDatabase) {
        PlayGameMenu playGameMenu = new PlayGameMenu(players, usersDatabase);
        playGameMenu.run();
    }

    public ArrayList<User> sortUsersScores(ArrayList<User> users) {
        for (int i = 0; i < users.size(); i++) {
            for (int j = i + 1; j < users.size(); j++) {
                if (users.get(i).getHighScore() > users.get(j).getHighScore()) {
                    User temp = users.get(i);
                    users.set(i, users.get(j));
                    users.set(j, temp);
                }
            }
        }
        return users;
    }

    public Output checkPlayers(String input, UsersDatabase usersDatabase) {
        Matcher matcher = Pattern.compile(ADD_PLAYER).matcher(input);
        int playersCount = 0;
        while (matcher.find()) {
            if (usersDatabase.getUserByUsername(matcher.group("username")) == null) return Output.INCORRECT_USERNAME;
            if (Integer.parseInt(matcher.group("number")) != (playersCount + 1)) return Output.INVALID_COMMAND;
                playersCount++;
        }
        if(playersCount > 6) return Output.EXTRA_PLAYER_NUMBERS;
        if (playersCount < 2) return Output.NOT_ENOUGH_INPUT;
        return Output.VALID_PLAYERS;
    }

    public ArrayList<Player> returnPlayers(String input, UsersDatabase usersDatabase) {
        Player tempPlayer;
        ArrayList<Player> players = new ArrayList<>();
        Matcher matcher = Pattern.compile(ADD_PLAYER).matcher(input);
        while (matcher.find()) {
            tempPlayer = new Player(usersDatabase.getUserByUsername(matcher.group("username")));
            players.add(tempPlayer);
        }
        return players;
    }
}