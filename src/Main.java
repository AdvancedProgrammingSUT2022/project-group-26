import models.UsersDatabase;
import views.LoginMenu;

public class Main {

    public static void main(String[] args) {
        UsersDatabase usersDatabase = new UsersDatabase();
        LoginMenu loginMenu = new LoginMenu(usersDatabase);
        loginMenu.run();        
    }
}