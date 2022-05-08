import controllers.LoginMenuController;
import models.UsersDatabase;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

public class loginMenuControllerTest {
    @Mock
    UsersDatabase usersDatabase;

    private LoginMenuController loginMenuController;

    @BeforeEach
    public void setUp(){
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
}