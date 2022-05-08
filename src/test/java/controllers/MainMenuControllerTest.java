package controllers;

import models.User;
import models.UsersDatabase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import views.LoginMenuCommandsRegex;
import views.MainMenuCommandsRegex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainMenuControllerTest {
    private User ilya;
    private User paria;
    private UsersDatabase usersDatabase;
    private MainMenuController mainMenuController;

    @BeforeEach
    public void setUp() {
        ilya = new User("ilya", "Ilya1234", "ilya");
        paria = new User("paria", "Paria1234", "paria");
        usersDatabase = new UsersDatabase();
        usersDatabase.addUser(ilya);
        mainMenuController = new MainMenuController(ilya, usersDatabase);
    }

    //isValidGameDifficulty
    @Test
    public void isValidGameDifficultyTest() {
        String input = "start game -d hard --player1 ilya --player2 paria";
        String regex = MainMenuCommandsRegex.START_GAME.toString();
        Matcher matcher = Pattern.compile(regex).matcher(input);
        if (matcher.matches()) {
            Output output = mainMenuController.isValidGameDifficulty(matcher);
            Assertions.assertEquals(null, output);
        }
    }

    @Test
    public void wrongIsValidGameDifficultyTest() {
        String input = "start game -d sdfa --player1 ilya --player2 paria";
        String regex = MainMenuCommandsRegex.START_GAME.toString();
        Matcher matcher = Pattern.compile(regex).matcher(input);
        if (matcher.matches()) {
            Output output = mainMenuController.isValidGameDifficulty(matcher);
            Assertions.assertEquals(Output.INVALID_DIFFICULTY, output);
        }
    }

    //isValidMenu
    @Test
    public void isValidMenuTest() {
        String input = "menu enter Profile";
        String regex = MainMenuCommandsRegex.ENTER_MENU.toString();
        Matcher matcher = Pattern.compile(regex).matcher(input);
        if (matcher.matches()) {
            Output output = mainMenuController.isValidMenu(matcher);
            Assertions.assertEquals(null, output);
        }
    }

    @Test
    public void wrongIsValidMenuTest() {
        String input = "menu enter Profisle";
        String regex = MainMenuCommandsRegex.ENTER_MENU.toString();
        Matcher matcher = Pattern.compile(regex).matcher(input);
        if (matcher.matches()) {
            Output output = mainMenuController.isValidMenu(matcher);
            Assertions.assertEquals(Output.INVALID_MENU, output);
        }
    }

}
