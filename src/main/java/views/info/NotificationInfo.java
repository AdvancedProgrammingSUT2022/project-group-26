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

    public static void showUnseenNotifications(Player player){
        if (player.getTechInResearch() != null) {
            int turn = (int) Math.ceil((double) (player.getTechInResearch().getCost() -
                    player.getTechInResearch().getEarnedCost() - player.getScience()) / player.getTurnScience());
            if(turn <= 0) turn = 1;
            System.out.println(player.getTechInResearch().getTechName().getName() + " turns left: " + turn);
            player.addNotification(player.getTechInResearch().getTechName().getName() + " turns left: " + turn);
        } else {
            System.out.println("why don't you start researching " + player.getUser().getUsername() + "?");
            player.addNotification("why don't you start researching " + player.getUser().getUsername() + "?");
        }
        for (int i=0; i<player.getCities().size(); i++) {
            City city = player.getCities().get(i);
            if (city.getBeingBuild() != null) {
                String toPrintTurnsLeft = "";
                if (city.getProduction() == 0) toPrintTurnsLeft = "no production!";
                else {
                    int turnsLeft = (int) Math.ceil((double) city.getBeingBuild().getProductionCost() / (double) city.getProduction());
                    if (turnsLeft <= 0) turnsLeft = 1;
                    toPrintTurnsLeft = Integer.toString(turnsLeft);
                }
                String name = "";
                if (city.getBeingBuild().getGettingBuild() instanceof Building) {
                    name = ((Building) city.getBeingBuild().getGettingBuild()).getName().getName();
                } else if (city.getBeingBuild().getGettingBuild() instanceof Unit) {
                    name = ((Unit) city.getBeingBuild().getGettingBuild()).getUnitNameEnum().getName();
                }
                System.out.println("being build: " + name + " turns left: " + toPrintTurnsLeft);
                player.addNotification("being build: " + name + " turns left: " + toPrintTurnsLeft);
            } else {
                System.out.println("why don't you start building " + player.getUser().getUsername() + "?");
                player.addNotification("why don't you start building " + player.getUser().getUsername() + "?");
            }
        }
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

    public void showAllNotifications(){
        for (int i=0; i<player.getNotifications().size(); i++)
            System.out.println(player.getNotifications().get(i));
        if (player.getNotifications().size() == 0)
            System.out.println("you have never received notifications!");
    }
}