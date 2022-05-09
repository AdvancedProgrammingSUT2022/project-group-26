package controllers;

import controllers.LoginMenuController;
import controllers.Output;
import models.User;
import models.UsersDatabase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import views.LoginMenuCommandsRegex;
import views.MainMenu;
import views.Menu;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class loginMenuControllerTest {

    private UsersDatabase usersDatabase;
    private User paria;


    private LoginMenuController loginMenuController;

    @BeforeEach
    public void setUp() {
        usersDatabase = new UsersDatabase();
        paria = new User("paria", "Paria1234", "paria");
        usersDatabase.addUser(paria);
        loginMenuController = new LoginMenuController(usersDatabase);
    }

    //isValidInput
    @Test
    public void isValidInputTest() {
        String input = "mammad";
        Assertions.assertTrue(loginMenuController.isValidInput(input));
    }

    @Test
    public void patternIsValidInputTest() {
        String input = "mammad352-()";
        Assertions.assertFalse(loginMenuController.isValidInput(input));
    }

    @Test
    public void lengthIsValidInputTest() {
        String input = "mammadfgjdgkjdgjdfjgjdsg";
        Assertions.assertFalse(loginMenuController.isValidInput(input));
    }

    @Test
    public void nullIsValidInputTest() {
        String input = null;
        Assertions.assertFalse(loginMenuController.isValidInput(input));
    }

    //isStrongPassword
    @Test
    public void isStrongPasswordTest() {
        String input = "Ilya50123";
        Assertions.assertTrue(loginMenuController.isStrongPassword(input));
    }

    @Test
    public void patternIsStrongPasswordTest() {
        String input = "ilyadgdagdgd";
        Assertions.assertFalse(loginMenuController.isStrongPassword(input));
    }

    @Test
    public void lengthIsStrongPasswordTest() {
        String input = "1mma";
        Assertions.assertFalse(loginMenuController.isStrongPassword(input));
    }

    @Test
    public void nullIsStrongPasswordTest() {
        String input = null;
        Assertions.assertFalse(loginMenuController.isStrongPassword(input));
    }

    //register
    @Test
    public void registerTest() {
        String input = "register -u ilya -p Ilya1234 -n ilya";
        String regex = LoginMenuCommandsRegex.REGISTER.toString();
        Matcher matcher = Pattern.compile(regex).matcher(input);
        if (matcher.matches()) {
            Output output = loginMenuController.register(matcher);
            Assertions.assertEquals(Output.REGISTERED, output);
        }
    }

    @Test
    public void validUserNameRegisterTest() {
        String input = "register -u ily@a -p Ilya1234 -n ilya";
        String regex = LoginMenuCommandsRegex.REGISTER.toString();
        Matcher matcher = Pattern.compile(regex).matcher(input);
        if (matcher.matches()) {
            Output output = loginMenuController.register(matcher);
            Assertions.assertEquals(Output.INVALID_USERNAME, output);
        }
    }

    @Test
    public void validPasswordRegisterTest() {
        String input = "register -u ilya -p Ilya123@4 -n ilya";
        String regex = LoginMenuCommandsRegex.REGISTER.toString();
        Matcher matcher = Pattern.compile(regex).matcher(input);
        if (matcher.matches()) {
            Output output = loginMenuController.register(matcher);
            Assertions.assertEquals(Output.INVALID_PASSWORD, output);
        }
    }

    @Test
    public void validNicknameRegisterTest() {
        String input = "register -u ilya -p Ilya1234 -n ily@a";
        String regex = LoginMenuCommandsRegex.REGISTER.toString();
        Matcher matcher = Pattern.compile(regex).matcher(input);
        if (matcher.matches()) {
            Output output = loginMenuController.register(matcher);
            Assertions.assertEquals(Output.INVALID_NICKNAME, output);
        }
    }

    @Test
    public void strongPasswordRegisterTest() {
        String input = "register -u ilya -p Ilya124 -n ilya";
        String regex = LoginMenuCommandsRegex.REGISTER.toString();
        Matcher matcher = Pattern.compile(regex).matcher(input);
        if (matcher.matches()) {
            Output output = loginMenuController.register(matcher);
            Assertions.assertEquals(Output.WEAK_PASSWORD, output);
        }
    }

    @Test
    public void repeatedUsernameRegisterTest() {
        String input = "register -u paria -p Ilya1234 -n ilya";
        String regex = LoginMenuCommandsRegex.REGISTER.toString();
        Matcher matcher = Pattern.compile(regex).matcher(input);
        if (matcher.matches()) {
            Output output = loginMenuController.register(matcher);
            Assertions.assertEquals(Output.REPEATED_USERNAME, output);
        }
    }

    @Test
    public void repeatedNicknameRegisterTest() {
        String input = "register -u ilya -p Ilya124 -n paria";
        String regex = LoginMenuCommandsRegex.REGISTER.toString();
        Matcher matcher = Pattern.compile(regex).matcher(input);
        if (matcher.matches()) {
            Output output = loginMenuController.register(matcher);
            Assertions.assertEquals(Output.REPEATED_NICKNAME, output);
        }
    }

    //login
    @Test
    public void loginTest() {
        String input = "user login -u paria -p Paria1234";
        String regex = LoginMenuCommandsRegex.USER_LOGIN.toString();
        Matcher matcher = Pattern.compile(regex).matcher(input);
        if (matcher.matches()) {
            Output output = loginMenuController.login(matcher);
            Assertions.assertEquals(Output.LOGGED_IN, output);
        }
    }

    @Test
    public void usernameLoginTest() {
        String input = "user login -u ilya -p Paria1234";
        String regex = LoginMenuCommandsRegex.USER_LOGIN.toString();
        Matcher matcher = Pattern.compile(regex).matcher(input);
        if (matcher.matches()) {
            Output output = loginMenuController.login(matcher);
            Assertions.assertEquals(Output.INCORRECT_PASSWORD_OR_USERNAME, output);
        }
    }

    @Test
    public void passwordLoginTest() {
        String input = "user login -u paria -p Pari1234";
        String regex = LoginMenuCommandsRegex.USER_LOGIN.toString();
        Matcher matcher = Pattern.compile(regex).matcher(input);
        if (matcher.matches()) {
            Output output = loginMenuController.login(matcher);
            Assertions.assertEquals(Output.INCORRECT_PASSWORD_OR_USERNAME, output);
        }
    }
}