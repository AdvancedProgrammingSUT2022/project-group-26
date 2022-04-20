package controllers;

import models.User;
import models.UsersDatabase;

import java.util.regex.Matcher;

public class ProfileMenuController {
    private User user;
    private UsersDatabase usersDatabase;

    public ProfileMenuController(User user, UsersDatabase usersDatabase) {
        this.user = user;
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

    public Output changeNickname(Matcher matcher) {
        String nickname = matcher.group("nickname");
        if (usersDatabase.getUserByNickname(nickname) != null)
            return Output.repeatedNickname;
        if (!isValidInput(nickname))
            return Output.invalidNickname;
        user.setNickname(nickname);
        return Output.nicknameChanged;
    }

    public Output changePassword(Matcher matcher) {
        String newPassword = matcher.group("newPassword");
        String currentPassword = matcher.group("currentPassword");
        if (!user.getPassword().equals(currentPassword))
            return Output.wrongPass;
        if (newPassword.equals(currentPassword))
            return Output.samePass;
        if (!isValidInput(newPassword))
            return Output.invalidPassword;
        if (!isStrongPassword(newPassword))
            return Output.weakPassword;
        user.setPassword(newPassword);
        return Output.passwordChanged;
    }
    
    public Output removeUser(Matcher matcher) {
        String password = matcher.group("password");
        if (!password.equals(user.getPassword()))
            return Output.incorrectPassword;
        usersDatabase.removeUser(user);
        return Output.userRemove;
    }

    public boolean isRemoved(Output output){
        if(output == Output.userRemove)
            return true;
        return false;
    }

}