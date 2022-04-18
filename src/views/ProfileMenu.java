package views;

import models.*;

import java.util.regex.Matcher;

import controllers.*;

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
            if ((matcher = getCommandMatcher(input, ProfileMenuCommandsRegex.changeNickname.toString())) != null) {
                System.out.println(profileMenuController.changeNickname(matcher).toString());
            } else if ((matcher = getCommandMatcher(input,
                    ProfileMenuCommandsRegex.changePassword.toString())) != null) {
                System.out.println(profileMenuController.changePassword(matcher).toString());
            } else if ((matcher = getCommandMatcher(input,
                    ProfileMenuCommandsRegex.showInformation.toString())) != null) {
                showInformation();
            } else if ((matcher = getCommandMatcher(input, ProfileMenuCommandsRegex.exit.toString())) != null) {
                return;
            } else if ((matcher = getCommandMatcher(input, ProfileMenuCommandsRegex.showMenu.toString())) != null) {
                System.out.println("Profile Menu");
            } else if ((matcher = getCommandMatcher(input, MainMenuCommandsRegex.enterMenu.toString())) != null) {
                System.out.println("menu navigation is not possible");
            } else if ((matcher = getCommandMatcher(input, ProfileMenuCommandsRegex.removeUser.toString())) != null) {
                if(removeUser(matcher)) {
                    return;
                }
            } else {
                System.out.println("invalid command!");
            }
        }
    }

    public void showInformation() {
        System.out.println("Usermane: " + user.getUsername());
        System.out.println("Nickname: " + user.getNickname());
        System.out.println("highscore: " + user.getHighScore());
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
