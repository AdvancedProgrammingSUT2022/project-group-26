package views;

import models.*;
import controllers.*;

import java.util.ArrayList;
import java.util.regex.Matcher;

public class MainMenu extends Menu {
    private User user;
    private MainMenuController mainMenuController;

    public MainMenu(User user, UsersDatabase usersDatabase) {
        super(usersDatabase);
        this.user = user;
        this.mainMenuController = new MainMenuController(user, usersDatabase);
    }

    @Override
    public void run() {
        Matcher matcher;
        String input;
        while (true) {
            input = super.scanner.nextLine();
            if ((matcher = getCommandMatcher(input, MainMenuCommandsRegex.ENTER_MENU.toString())) != null) {
                goToMenu(matcher);
                if (isUserRemoved())
                    return;
            } else if ((matcher = getCommandMatcher(input, MainMenuCommandsRegex.SHOW_SCOREBOARD.toString())) != null) {
                showScoreBoard();
            } else if ((matcher = getCommandMatcher(input, MainMenuCommandsRegex.SHOW_MENU.toString())) != null) {
                System.out.println("Main Menu");
            } else if ((matcher = getCommandMatcher(input, MainMenuCommandsRegex.LOGOUT.toString())) != null) {
                System.out.println("user logged out successfully!");
                return;
            } else if ((matcher = getCommandMatcher(input, MainMenuCommandsRegex.START_GAME.toString())) != null) {
                startGame(matcher);
            } else {
                System.out.println("invalid command!");
            }
        }
    }


    public void goToMenu(Matcher matcher) {
        if (mainMenuController.isValidMenu(matcher) != null)
            System.out.println(mainMenuController.isValidMenu(matcher).toString());
        else
            enterProfileMenu();
    }


    public void enterProfileMenu() {
        ProfileMenu profileMenu = new ProfileMenu(user, usersDatabase);
        profileMenu.run();
    }

    public void showScoreBoard() {
        ArrayList<User> users = new ArrayList<User>(usersDatabase.getUsers());
        users = mainMenuController.sortUsersScores(users);
        for (int i = 0; i < users.size(); i++) {
            System.out.println((i + 1) + "- " + users.get(i).getNickname() + ": " + users.get(i).getHighScore());
        }
    }

    public boolean isUserRemoved() {
        if (this.usersDatabase.getUserByUsername(this.user.getUsername()) == null)
            return true;
        return false;
    }

    public void startGame(Matcher matcher) {
        Output output = mainMenuController.isValidGameDifficulty(matcher);
        if (output != null) {
            System.out.println(output);
            return;
        }
        Output outputSave = mainMenuController.checkPlayers(matcher.group("input"));
        System.out.println(outputSave.toString());
        if (outputSave == Output.VALID_PLAYERS) {
            ArrayList<Player> players = mainMenuController.returnPlayers(matcher.group("input"));
            int difficulty = mainMenuController.getStartGameDifficulty(matcher);
            enterGameMenu(players, usersDatabase, difficulty);
        }
    }


    public void enterGameMenu(ArrayList<Player> players, UsersDatabase usersDatabase, int difficulty) {
        PlayGameMenu playGameMenu = new PlayGameMenu(players, usersDatabase, difficulty);
        playGameMenu.run();
    }

}