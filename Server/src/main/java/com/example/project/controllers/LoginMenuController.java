package com.example.project.controllers;

import com.example.project.models.*;

public class LoginMenuController {
    private UsersDatabase usersDatabase = UsersDatabase.getInstance();

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
        if (!password.matches(".*[A-Z].*")
                || !password.matches(".*[a-z].*")
                || !password.matches(".*\\d.*"))
            return false;
        return true;
    }

    public Output register(Request request) {
        String username = (String) request.getParams().get("username");
        String nickname = (String) request.getParams().get("nickname");
        String password = (String) request.getParams().get("password");
        String password2 = (String) request.getParams().get("confirm password");
        if (!isValidInput(username))
            return Output.INVALID_USERNAME;
        if (!isValidInput(nickname))
            return Output.INVALID_NICKNAME;
        if (!isValidInput(password))
            return Output.INVALID_PASSWORD;
        if (!password.equals(password2))
            return Output.WRONG_REPEATED_PASSWORD;
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

    public Output login(Request request, Network network) {
        String username = (String) request.getParams().get("username");
        String password = (String) request.getParams().get("password");
        User user = usersDatabase.getUserByUsername(username);
        if (user == null)
            return Output.INCORRECT_PASSWORD_OR_USERNAME;
        if (!user.getPassword().equals(password) || !user.getUsername().equals(username))
            return Output.INCORRECT_PASSWORD_OR_USERNAME;
//        user.setLastLogin(LocalDateTime.now());
        user.setOnline(true);
        network.setLoggedInUser(user);
        DataBase.getOnlineNetworks().add(network);
        network.setOnMainMenu(true);
        return Output.LOGGED_IN;
    }
}