import controllers.SaveData;
import models.User;
import controllers.SaveData;
import models.Player;
import models.User;
import models.UsersDatabase;
import views.LoginMenu;
import views.LoginMenuCommandsRegex;
import views.LoginMenuCommandsRegex;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        UsersDatabase usersDatabase = new UsersDatabase();
        LoginMenu loginMenu = new LoginMenu(usersDatabase);
        loginMenu.run();

        // TODO : save users - save map - save player
    }
}