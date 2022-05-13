package views.info;

import controllers.GameControllers.GameMenuCommandController;
import models.Building.Building;
import models.City;
import models.GameMap;
import models.Player;
import models.Units.Unit;
import models.UsersDatabase;
import views.Menu;

import java.util.ArrayList;
import java.util.regex.Matcher;

public class NotificationInfo extends Menu {
    private Player player;
    private GameMenuCommandController gameMenuCommandController;
    private GameMap gamemap;
    private ArrayList<Player> players;

    public NotificationInfo(UsersDatabase usersDatabase, Player player, GameMenuCommandController gameMenuCommandController,
                            ArrayList<Player> players, GameMap gamemap) {
        super(usersDatabase);
        this.player = player;
        this.gameMenuCommandController = gameMenuCommandController;
        this.players = players;
        this.gamemap = gamemap;
    }

    public static void showUnseenNotifications(Player player) {
        for(int i = 0; i < player.getUnseenNotifications().size(); i++){
            System.out.println(player.getUnseenNotifications().get(i));
            player.getNotifications().add(player.getUnseenNotifications().get(i));
        }
        player.getUnseenNotifications().clear();
    }

    @Override
    public void run() {
        String input;
        Matcher matcher;
        while (true) {
            input = scanner.nextLine();
            if ((matcher = getCommandMatcher(input, NotificationInfoEnum.EXIT.toString())) != null) {
                return;
            } else if ((matcher = getCommandMatcher(input, NotificationInfoEnum.SHOW_INFORMATION.toString())) != null) {
                showAllNotifications();
            } else {
                System.out.println("invalid command!");
            }
        }
    }

    public void showAllNotifications() {
        for (int i = player.getNotifications().size() - 1; i >= 0; i--)
            System.out.println((player.getNotifications().size() - i) + "- " + player.getNotifications().get(i));
        if (player.getNotifications().size() == 0)
            System.out.println("you have never received notifications!");
    }
}