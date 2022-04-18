package controllers;

import models.User;
import models.UsersDatabase;
import views.PlayGameMenu;
import views.ProfileMenu;

import java.util.ArrayList;
import java.util.regex.Matcher;

public class MainMenuController {
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
}