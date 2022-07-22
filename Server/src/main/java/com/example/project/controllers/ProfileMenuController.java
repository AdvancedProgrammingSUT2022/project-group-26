package com.example.project.controllers;

import com.example.project.models.DataBase;
import com.example.project.models.Output;
import com.example.project.models.User;
import com.example.project.models.UsersDatabase;

import java.util.regex.Matcher;

public class ProfileMenuController {
    private User user;
    private UsersDatabase usersDatabase = UsersDatabase.getInstance();
    private DataBase dataBase = DataBase.getInstance();

    public ProfileMenuController(User user) {
        this.user = user;
    }

    public ProfileMenuController() {
        // don't need this but this will prevent debugging for now
        user = dataBase.getLoggedInUser();
    }

    public boolean isValidInput(String input) {
        if (input == null)
            return false;
        if (input.length() > 15)
            return false;
        if (!input.matches("\\w+"))
            return false;
        return true;
    }

    public boolean isStrongPassword(String password) {
        if (password == null)
            return false;
        if (password.length() < 8)
            return false;
        if (!password.matches(".*[A-Z].*") || !password.matches(".*[a-z].*") || !password.matches(".*\\d.*"))
            return false;
        return true;
    }

    public Output changeNickname(Matcher matcher) {
        String nickname = matcher.group("nickname");
        if (usersDatabase.getUserByNickname(nickname) != null)
            return Output.REPEATED_NICKNAME;
        if (!isValidInput(nickname))
            return Output.INVALID_NICKNAME;
        user.setNickname(nickname);
        return Output.NICKNAME_CHANGED;
    }

    public Output changeNickname(String nickname) {
        if (usersDatabase.getUserByNickname(nickname) != null)
            return Output.REPEATED_NICKNAME;
        if (!isValidInput(nickname))
            return Output.INVALID_NICKNAME;
        user.setNickname(nickname);
        return Output.NICKNAME_CHANGED;
    }

    public Output changePassword(Matcher matcher) {
        String newPassword = matcher.group("newPassword");
        String currentPassword = matcher.group("currentPassword");
        if (!user.getPassword().equals(currentPassword))
            return Output.WRONG_PASSWORD;
        if (newPassword.equals(currentPassword))
            return Output.SAME_PASSWORD;
        if (!isValidInput(newPassword))
            return Output.INVALID_PASSWORD;
        if (!isStrongPassword(newPassword))
            return Output.WEAK_PASSWORD;
        user.setPassword(newPassword);
        return Output.PASSWORD_CHANGED;
    }

    public Output changePassword(String newPassword) {
        if (newPassword.equals(DataBase.getInstance().getLoggedInUser().getPassword()))
            return Output.SAME_PASSWORD;
        if (!isValidInput(newPassword))
            return Output.INVALID_PASSWORD;
        if (!isStrongPassword(newPassword))
            return Output.WEAK_PASSWORD;
        user.setPassword(newPassword);
        return Output.PASSWORD_CHANGED;
    }

    public Output removeUser(Matcher matcher) {
        String password = matcher.group("password");
        if (!password.equals(user.getPassword()))
            return Output.INCORRECT_PASSWORD;
        usersDatabase.removeUser(user);
        return Output.userRemove;
    }

    public boolean isRemoved(Output output) {
        if (output == Output.userRemove)
            return true;
        return false;
    }

}