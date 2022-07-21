package com.example.project.views;

import com.example.project.models.Output;
import com.example.project.controllers.ProfileMenuController;
import com.example.project.models.User;
import com.example.project.models.UsersDatabase;


import java.util.regex.Matcher;

public class ProfileMenu extends Menu {
    private User user;
    private ProfileMenuController profileMenuController;

    public ProfileMenu(User user, UsersDatabase usersDatabase) {
        super(usersDatabase);
        this.user = user;
        this.profileMenuController = new ProfileMenuController(user, usersDatabase);
    }

    @Override
    public void run() {
        Matcher matcher;
        String input;
        while (true) {
            input = super.scanner.nextLine();
            if ((matcher = getCommandMatcher(input, ProfileMenuCommandsRegex.CHANGE_NICKNAME.toString())) != null) {
                System.out.println(profileMenuController.changeNickname(matcher).toString());
            } else if ((matcher = getCommandMatcher(input,
                    ProfileMenuCommandsRegex.CHANGE_PASSWORD.toString())) != null) {
                System.out.println(profileMenuController.changePassword(matcher).toString());
            } else if ((matcher = getCommandMatcher(input,
                    ProfileMenuCommandsRegex.SHOW_INFORMATION.toString())) != null) {
                showInformation();
            } else if ((matcher = getCommandMatcher(input, ProfileMenuCommandsRegex.EXIT.toString())) != null) {
                return;
            } else if ((matcher = getCommandMatcher(input, ProfileMenuCommandsRegex.SHOW_MENU.toString())) != null) {
                System.out.println("Profile Menu");
            } else if ((matcher = getCommandMatcher(input, ProfileMenuCommandsRegex.CHANGE_USERNAME.toString())) != null) {
                System.out.println("you can't change your username!");
            } else if ((matcher = getCommandMatcher(input, MainMenuCommandsRegex.ENTER_MENU.toString())) != null) {
                System.out.println("menu navigation is not possible");
            } else if ((matcher = getCommandMatcher(input, ProfileMenuCommandsRegex.REMOVE_USER.toString())) != null) {
                if(removeUser(matcher))
                    return;
            } else {
                System.out.println("invalid command!");
            }
        }
    }

    public void showInformation() {
        System.out.println("Username: " + user.getUsername());
        System.out.println("Nickname: " + user.getNickname());
        System.out.println("high score: " + user.getHighScore());
    }

    public boolean removeUser(Matcher matcher) {
        Output output = profileMenuController.removeUser(matcher);
        System.out.println(output.toString());
        if(profileMenuController.isRemoved(output)) {
            return true;
        }
        return false;
    }
}
