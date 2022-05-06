package views.info;

import models.Player;
import models.Units.Combat.CombatUnits;
import models.Units.Nonecombat.BuilderUnit;
import models.Units.Unit;
import models.UsersDatabase;
import views.Menu;

import java.util.regex.Matcher;

public class UnitInfo extends Menu {
    private Player player;

    public UnitInfo(UsersDatabase usersDatabase, Player player) {
        super(usersDatabase);
        this.player = player;
    }

    @Override
    public void run() {
        String input;
        Matcher matcher;
        while (true) {
            input = super.scanner.nextLine();
            if ((matcher = getCommandMatcher(input, UnitIInfoEnum.SHOW_ALL_UNITS.toString())) != null) {
                showAllUnits();
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