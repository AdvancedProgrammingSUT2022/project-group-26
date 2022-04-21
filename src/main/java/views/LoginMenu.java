package views;

import controllers.LoginMenuController;
import models.User;
import models.UsersDatabase;

import java.util.ArrayList;
import java.util.regex.Matcher;

public class LoginMenu extends Menu {
    LoginMenuController loginMenuController;

    public LoginMenu(UsersDatabase usersDatabase) {
        super(usersDatabase);
        this.loginMenuController = new LoginMenuController(usersDatabase);
    }

    @Override
    public void run() {
        Matcher matcher;
        String input;
        while (true) {
            input = super.scanner.nextLine();
            if ((matcher = getCommandMatcher(input, LoginMenuCommandsRegex.REGISTER.toString())) != null) {
                System.out.println(loginMenuController.register(matcher).toString());
            } else if ((matcher = getCommandMatcher(input, LoginMenuCommandsRegex.USER_LOGIN.toString())) != null) {
                System.out.println(loginMenuController.login(matcher).toString());
                loginMenuController.enterMainMenu(loginMenuController.login(matcher), matcher);
            } else if ((matcher = getCommandMatcher(input, LoginMenuCommandsRegex.LIST_OF_USERS.toString())) != null) {
                printListOfUsers();
            } else if ((matcher = getCommandMatcher(input, LoginMenuCommandsRegex.EXIT.toString())) != null) {
                return;
            } else if ((matcher = getCommandMatcher(input, LoginMenuCommandsRegex.SHOW_MENU.toString())) != null) {
                System.out.println("Login Menu");
            } else if ((matcher = getCommandMatcher(input, MainMenuCommandsRegex.ENTER_MENU.toString())) != null) {
                System.out.println("menu navigation is not possible");
            } else {
                System.out.println("invalid command!");
            }
        }
    }

    private void printListOfUsers() {
        ArrayList<User> users = this.usersDatabase.getUsers();
        if (users.size() == 0) {
            System.out.println("there is no registered user!");
            return;
        }
        System.out.println(users.size() + " users has registered:");
        for (int i = 0; i < users.size(); i++) {
            System.out.println((i + 1) + "- nickname: " + users.get(i).getNickname());
        }
    }
}