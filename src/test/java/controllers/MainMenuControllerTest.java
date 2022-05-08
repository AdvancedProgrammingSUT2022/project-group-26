package controllers;

import models.Player;
import models.User;
import models.UsersDatabase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import views.LoginMenuCommandsRegex;
import views.MainMenuCommandsRegex;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.mockito.Mockito.when;

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
        usersDatabase.addUser(paria);
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

    //sortUsers
    @Test
    public void sortUsers() {
        ilya.setHighScore(10);
        paria.setHighScore(50);
        ArrayList<User> sortedUsers = mainMenuController.sortUsersScores(usersDatabase.getUsers());
        boolean result = sortedUsers.get(0) == paria && sortedUsers.get(1) == ilya;
        Assertions.assertTrue(result);
    }

    //checkPlayers
    @Test
    public void checkPlayersTest() {
        String input = "--player1 ilya --player2 paria";
        Output output = mainMenuController.checkPlayers(input);
        Assertions.assertEquals(Output.VALID_PLAYERS, output);
    }

    @Test
    public void userNameCheckPlayersTest() {
        String input = "--player1 ilsya --player2 paria";
        Output output = mainMenuController.checkPlayers(input);
        Assertions.assertEquals(Output.INCORRECT_USERNAME, output);
    }

    @Test
    public void numberOrderCheckPlayersTest() {
        String input = "--player2 ilya --player1 paria";
        Output output = mainMenuController.checkPlayers(input);
        Assertions.assertEquals(Output.INVALID_COMMAND, output);
    }

    @Test
    public void onePlayerCheckPlayersTest() {
        String input = "--player1 ilya";
        Output output = mainMenuController.checkPlayers(input);
        Assertions.assertEquals(Output.NOT_ENOUGH_INPUT, output);
    }

    @Test
    public void morePlayerPlayersTest() {
        String input = "--player1 ilya " +
                "--player2 paria --player3 paria --player4 paria --player5 paria --player6 paria --player7 paria";
        Output output = mainMenuController.checkPlayers(input);
        Assertions.assertEquals(Output.EXTRA_PLAYER_NUMBERS, output);
    }

    //returnPlayers
    @Test
    public void returnPlayers() {
        String input = "--player1 ilya --player2 paria";
        ArrayList<Player> players = mainMenuController.returnPlayers(input);
        boolean result = players.get(0).getUser().getUsername().equals("ilya")
                && players.get(1).getUser().getUsername().equals("paria");
        Assertions.assertTrue(result);
    }

    //getStartGameDifficulty
    @Test
    public void hardGetStartGameDifficultyTest() {
        String input = "start game -d hard --player1 ilya --player2 paria";
        String regex = MainMenuCommandsRegex.START_GAME.toString();
        Matcher matcher = Pattern.compile(regex).matcher(input);
        if (matcher.matches()) {
            int result = mainMenuController.getStartGameDifficulty(matcher);
            Assertions.assertEquals(2, result);
        }
    }

    @Test
    public void mediumStartGameDifficultyTest() {
        String input = "start game -d medium --player1 ilya --player2 paria";
        String regex = MainMenuCommandsRegex.START_GAME.toString();
        Matcher matcher = Pattern.compile(regex).matcher(input);
        if (matcher.matches()) {
            int result = mainMenuController.getStartGameDifficulty(matcher);
            Assertions.assertEquals(1, result);
        }
    }

    @Test
    public void easyGetStartGameDifficultyTest() {
        String input = "start game -d easy --player1 ilya --player2 paria";
        String regex = MainMenuCommandsRegex.START_GAME.toString();
        Matcher matcher = Pattern.compile(regex).matcher(input);
        if (matcher.matches()) {
            int result = mainMenuController.getStartGameDifficulty(matcher);
            Assertions.assertEquals(0, result);
        }
    }

    @Test
    public void wrongGetStartGameDifficultyTest() {
        String input = "start game -d hasrd --player1 ilya --player2 paria";
        String regex = MainMenuCommandsRegex.START_GAME.toString();
        Matcher matcher = Pattern.compile(regex).matcher(input);
        if (matcher.matches()) {
            int result = mainMenuController.getStartGameDifficulty(matcher);
            Assertions.assertEquals(-1, result);
        }
    }

    @Test
    public void outputTest(){
        Assertions.assertEquals(Output.INVALID_NUMBER.toString(), "invalid number!");
    }


}
