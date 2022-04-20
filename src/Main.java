import models.UsersDatabase;
import views.LoginMenu;
import views.LoginMenuCommandsRegex;

public class Main {

    public static void main(String[] args) {
        System.out.println(LoginMenuCommandsRegex.register);
        UsersDatabase usersDatabase = new UsersDatabase();
        LoginMenu loginMenu = new LoginMenu(usersDatabase);
        loginMenu.run();        
    }
}