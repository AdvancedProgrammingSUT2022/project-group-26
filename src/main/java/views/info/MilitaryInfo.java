package views.info;

import controllers.GameControllers.GameMenuCommandController;
import models.GameMap;
import models.Player;
import models.Units.Combat.CombatUnits;
import models.Units.Nonecombat.BuilderUnit;
import models.Units.Unit;
import models.UsersDatabase;
import views.Menu;

import java.util.ArrayList;
import java.util.regex.Matcher;

public class MilitaryInfo extends Menu {
    private Player player;
    private GameMenuCommandController gameMenuCommandController;
    private GameMap gamemap;
    private ArrayList<Player> players;

    public MilitaryInfo(UsersDatabase usersDatabase, Player player, GameMenuCommandController gameMenuCommandController,
                        ArrayList<Player> players, GameMap gamemap) {
        super(usersDatabase);
        this.player = player;
        this.gameMenuCommandController = gameMenuCommandController;
        this.players = players;
        this.gamemap = gamemap;
    }

    @Override
    public void run() {
        String input;
        Matcher matcher;
        while (true) {
            input = super.scanner.nextLine();
            if ((matcher = getCommandMatcher(input, MilitaryInfoEnum.SHOW_ALL_UNITS.toString())) != null) {
                showAllUnits();
            } else if ((matcher = getCommandMatcher(input, MilitaryInfoEnum.EXIT.toString())) != null) {
                return;
            } else {
                System.out.println("invalid command!");
            }
        }
    }

    private void showAllUnits() {
        int counter = 1;
        for (Unit unit : player.getUnits()) {
            System.out.print(counter + "- name: " + unit.getUnitNameEnum().getName());
            if (unit instanceof CombatUnits) {
                System.out.println(" action: " + ((CombatUnits) unit).getActionToString());
            } else if (unit instanceof BuilderUnit) {
                if (((BuilderUnit) unit).getWork() != null)
                    System.out.println(" is building " + ((BuilderUnit) unit).getWork());
                else System.out.println(" no responsibility!");
            } else System.out.println();
            counter++;
        }
    }
}

