import controllers.SaveData;
import models.*;
import models.Feature.TileFeatureEnum;
import models.Improvement.TileImprovementEnum;
import models.Tile.Tile;
import models.Tile.TileMode;
import models.Tile.TileModeEnum;
import models.Units.Unit;
import models.Units.UnitNameEnum;
import views.LoginMenu;
import views.LoginMenuCommandsRegex;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        UsersDatabase usersDatabase = SaveData.loadAndReturnUserDataBase();
        LoginMenu loginMenu = new LoginMenu(usersDatabase);
        loginMenu.run();
        SaveData.saveUserDataBase(usersDatabase);
    }
}