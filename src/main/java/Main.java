import controllers.SaveData;
import models.Player;
import models.Tile.Tile;
import models.Tile.TileMode;
import models.Tile.TileModeEnum;
import models.Units.Unit;
import models.User;
import models.UsersDatabase;
import views.LoginMenu;
import views.LoginMenuCommandsRegex;

import java.util.ArrayList;

public class Main {

    public static void main(String[] args) {
        UsersDatabase usersDatabase = new UsersDatabase();
        LoginMenu loginMenu = new LoginMenu(usersDatabase);
        loginMenu.run();
    }
}