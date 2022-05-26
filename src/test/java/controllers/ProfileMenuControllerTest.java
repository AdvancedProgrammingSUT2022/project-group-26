package controllers;

import com.example.project.controllers.Output;
import com.example.project.controllers.ProfileMenuController;
import com.example.project.models.User;
import com.example.project.models.UsersDatabase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.example.project.views.ProfileMenuCommandsRegex;

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

    //changePassword
    @Test
    public void changePasswordTest() {
        String input = "profile change -p --current Ilya1234 --new Ilya12345";
        String regex = ProfileMenuCommandsRegex.CHANGE_PASSWORD.toString();
        Matcher matcher = Pattern.compile(regex).matcher(input);
        if (matcher.matches()) {
            Output output = profileMenuController.changePassword(matcher);
            Assertions.assertEquals(Output.PASSWORD_CHANGED, output);
        }
    }

    @Test
    public void wrongPasswordChangePasswordTest() {
        String input = "profile change -p --current Ilya234 --new Ilya12345";
        String regex = ProfileMenuCommandsRegex.CHANGE_PASSWORD.toString();
        Matcher matcher = Pattern.compile(regex).matcher(input);
        if (matcher.matches()) {
            Output output = profileMenuController.changePassword(matcher);
            Assertions.assertEquals(Output.WRONG_PASSWORD, output);
        }
    }

    @Test
    public void samePasswordChangePasswordTest() {
        String input = "profile change -p --current Ilya1234 --new Ilya1234";
        String regex = ProfileMenuCommandsRegex.CHANGE_PASSWORD.toString();
        Matcher matcher = Pattern.compile(regex).matcher(input);
        if (matcher.matches()) {
            Output output = profileMenuController.changePassword(matcher);
            Assertions.assertEquals(Output.SAME_PASSWORD, output);
        }
    }

    @Test
    public void invalidPasswordChangePasswordTest() {
        String input = "profile change -p --current Ilya1234 --new Ilya123!45";
        String regex = ProfileMenuCommandsRegex.CHANGE_PASSWORD.toString();
        Matcher matcher = Pattern.compile(regex).matcher(input);
        if (matcher.matches()) {
            Output output = profileMenuController.changePassword(matcher);
            Assertions.assertEquals(Output.INVALID_PASSWORD, output);
        }
    }

    @Test
    public void weakPasswordChangePasswordTest() {
        String input = "profile change -p --current Ilya1234 --new Ily12";
        String regex = ProfileMenuCommandsRegex.CHANGE_PASSWORD.toString();
        Matcher matcher = Pattern.compile(regex).matcher(input);
        if (matcher.matches()) {
            Output output = profileMenuController.changePassword(matcher);
            Assertions.assertEquals(Output.WEAK_PASSWORD, output);
        }
    }

    @Test
    public void removeUserTest() {
        String input = "user remove -p Ilya1234";
        String regex = ProfileMenuCommandsRegex.REMOVE_USER.toString();
        Matcher matcher = Pattern.compile(regex).matcher(input);
        if (matcher.matches()) {
            Output output = profileMenuController.removeUser(matcher);
            Assertions.assertEquals(Output.userRemove, output);
        }
    }

    @Test
    public void wrongPasswordRemoveUserTest() {
        String input = "user remove -p Ilya123334";
        String regex = ProfileMenuCommandsRegex.REMOVE_USER.toString();
        Matcher matcher = Pattern.compile(regex).matcher(input);
        if (matcher.matches()) {
            Output output = profileMenuController.removeUser(matcher);
            Assertions.assertEquals(Output.INCORRECT_PASSWORD, output);
        }
    }

    @Test
    public void isRemovedTest() {
        boolean result = profileMenuController.isRemoved(Output.userRemove);
        Assertions.assertTrue(result);
    }

    @Test
    public void wrongIsRemovedTest() {
        boolean result = profileMenuController.isRemoved(null);
        Assertions.assertFalse(result);
    }
}
