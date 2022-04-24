package controllers;

import models.User;
import models.UsersDatabase;
import views.MainMenu;

import java.util.ArrayList;
import java.util.regex.Matcher;

public class LoginMenuController {
    private UsersDatabase usersDatabase;

    public LoginMenuController(UsersDatabase usersDatabase) {
        this.usersDatabase = usersDatabase;
    }

    private boolean isValidInput(String input) {
        if (input == null)
            return false;
        if (input.length() > 15)
            return false;
        if (!input.matches("\\w+"))
            return false;
        return true;
    }

    private boolean isStrongPassword(String password) {
        if (password == null)
            return false;
        if (password.length() < 8)
            return false;
        if (!password.matches(".*[A-Z].*") || !password.matches(".*[a-z].*") || !password.matches(".*\\d.*"))
            return false;
        return true;
    }

    public Output register(Matcher matcher) {
        String username = matcher.group("username");
        String password = matcher.group("password");
        String nickname = matcher.group("nickname");
        if (!isValidInput(username))
            return Output.INVALID_USERNAME;
        if (!isValidInput(password))
            return Output.INCORRECT_PASSWORD;
        if (!isValidInput(nickname))
            return Output.INVALID_NICKNAME;
        if (usersDatabase.getUserByUsername(username) != null)
            return Output.REPEATED_USERNAME;
        if (usersDatabase.getUserByNickname(nickname) != null)
            return Output.REPEATED_NICKNAME;
        if (!isStrongPassword(password))
            return Output.WEAK_PASSWORD;
        User user = new User(username, password, nickname);
        usersDatabase.addUser(user);
        return Output.REGISTERED;
    }

    public Output login(Matcher matcher) {
        String username = matcher.group("username");
        String password = matcher.group("password");
        User user = usersDatabase.getUserByUsername(username);
        if (user == null)
            return Output.NO_EXISTING_USER;
        if (!user.getPassword().equals(password) || !user.getUsername().equals(username))
            return Output.INCORRECT_PASSWORD_OR_USERNAME;
        return Output.LOGGED_IN;
    }

    public void enterMainMenu(Output output, Matcher matcher) {
        if (output == Output.LOGGED_IN) {
            String username = matcher.group("username");
            User user = usersDatabase.getUserByUsername(username);
            MainMenu mainMenu = new MainMenu(user, this.usersDatabase);
            mainMenu.run();
        }
    }
}