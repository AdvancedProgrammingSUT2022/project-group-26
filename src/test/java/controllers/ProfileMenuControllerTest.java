package controllers;

import models.User;
import models.UsersDatabase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import views.ProfileMenu;
import views.ProfileMenuCommandsRegex;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ProfileMenuControllerTest {
    private User ilya;
    private User paria;
    private UsersDatabase usersDatabase;
    private ProfileMenuController profileMenuController;

    @BeforeEach
    public void setUp() {
        ilya = new User("ilya", "Ilya1234", "ilya");
        paria = new User("paria", "Paria1234", "paria");
        usersDatabase = new UsersDatabase();
        usersDatabase.addUser(ilya);
        usersDatabase.addUser(paria);
        profileMenuController = new ProfileMenuController(ilya, usersDatabase);
    }

    //isValidInput
    @Test
    public void isValidInputTest() {
        String input = "mammad";
        Assertions.assertTrue(profileMenuController.isValidInput(input));
    }

    @Test
    public void patternIsValidInputTest() {
        String input = "mammad352-()";
        Assertions.assertFalse(profileMenuController.isValidInput(input));
    }

    @Test
    public void lengthIsValidInputTest() {
        String input = "mammadfgjdgkjdgjdfjgjdsg";
        Assertions.assertFalse(profileMenuController.isValidInput(input));
    }

    @Test
    public void nullIsValidInputTest() {
        String input = null;
        Assertions.assertFalse(profileMenuController.isValidInput(input));
    }

    //isStrongPassword
    @Test
    public void isStrongPasswordTest() {
        String input = "Ilya50123";
        Assertions.assertTrue(profileMenuController.isStrongPassword(input));
    }

    @Test
    public void patternIsStrongPasswordTest() {
        String input = "ilyadgdagdgd";
        Assertions.assertFalse(profileMenuController.isStrongPassword(input));
    }

    @Test
    public void lengthIsStrongPasswordTest() {
        String input = "1mma";
        Assertions.assertFalse(profileMenuController.isStrongPassword(input));
    }

    @Test
    public void nullIsStrongPasswordTest() {
        String input = null;
        Assertions.assertFalse(profileMenuController.isStrongPassword(input));
    }

    //changeNickname
    @Test
    public void changeNickNameTest() {
        String input = "profile change -n realIlya";
        String regex = ProfileMenuCommandsRegex.CHANGE_NICKNAME.toString();
        Matcher matcher = Pattern.compile(regex).matcher(input);
        if (matcher.matches()) {
            Output output = profileMenuController.changeNickname(matcher);
            Assertions.assertEquals(Output.NICKNAME_CHANGED, output);
        }
    }

    @Test
    public void repeatedNickNameChangeNickNameTest() {
        String input = "profile change -n paria";
        String regex = ProfileMenuCommandsRegex.CHANGE_NICKNAME.toString();
        Matcher matcher = Pattern.compile(regex).matcher(input);
        if (matcher.matches()) {
            Output output = profileMenuController.changeNickname(matcher);
            Assertions.assertEquals(Output.REPEATED_NICKNAME, output);
        }
    }

    @Test
    public void invalidNickNameChangeNickNameTest() {
        String input = "profile change -n pari!a";
        String regex = ProfileMenuCommandsRegex.CHANGE_NICKNAME.toString();
        Matcher matcher = Pattern.compile(regex).matcher(input);
        if (matcher.matches()) {
            Output output = profileMenuController.changeNickname(matcher);
            Assertions.assertEquals(Output.INVALID_NICKNAME, output);
        }
    }

    


}
