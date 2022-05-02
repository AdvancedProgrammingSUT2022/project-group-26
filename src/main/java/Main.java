import controllers.SaveData;
import models.*;
import models.Tile.Tile;
import models.Tile.TileMode;
import models.Tile.TileModeEnum;
import models.Units.Unit;
import models.Units.UnitNameEnum;
import views.LoginMenu;
import views.LoginMenuCommandsRegex;

import java.util.ArrayList;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) {
        UsersDatabase usersDatabase = new UsersDatabase();
        LoginMenu loginMenu = new LoginMenu(usersDatabase);
        loginMenu.run();

        //
//        ArrayList<Player> players = new ArrayList<>();
//        players.add(new Player(new User("aa", "a", "aaa")));
//        players.add(new Player(new User("b", "bbb", "bb")));
//        players.add(new Player(new User("cc", "c", "ccc")));
//        Game game = new Game();
//        game.setPlayers(players);
//        game.setGameMap(new GameMap(game.getPlayers()));
//        game.setTurn(3);
//        SaveData.saveGame(game);

//        GameMap gameMap = new GameMap(new Tile[30][30]);
//        SaveData.saveMap(gameMap);
    }
}