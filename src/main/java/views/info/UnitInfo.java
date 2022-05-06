package views.info;

import controllers.GameControllers.GameMenuCommandController;
import controllers.Output;
import models.GameMap;
import models.Player;
import models.Units.Combat.CombatUnits;
import models.Units.Nonecombat.BuilderUnit;
import models.Units.Nonecombat.NoneCombatUnits;
import models.Units.Unit;
import models.Units.UnitNameEnum;
import models.UsersDatabase;
import views.Menu;
import views.PlayGameCommandsRegex;
import views.PlayGameMenu;

import java.util.ArrayList;
import java.util.regex.Matcher;

public class UnitInfo extends Menu {
    private Player player;
    private GameMenuCommandController gameMenuCommandController;
    private GameMap gamemap;
    private ArrayList<Player> players;

    public UnitInfo(UsersDatabase usersDatabase, Player player, GameMenuCommandController gameMenuCommandController,
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
            if ((matcher = getCommandMatcher(input, UnitInfoEnum.SHOW_ALL_UNITS.toString())) != null) {
                showAllUnits();
            } else if ((matcher = getCommandMatcher(input, UnitInfoEnum.SELECT_UNIT.toString())) != null) {
                selectUnit(matcher);
            } else if ((matcher = getCommandMatcher(input, UnitInfoEnum.EXIT.toString())) != null) {
                return;
            } else {
                System.out.println("invalid command!");
            }
        }
    }

    private void selectUnit(Matcher matcher) {
        Output output = gameMenuCommandController.selectUnitByNumber(matcher, player);
        if (output != null) {
            System.out.println(output);
            return;
        }
        Unit unit = player.getUnits().get(Integer.parseInt(matcher.group("number")) - 1);
        if (unit instanceof CombatUnits) {
            giveCommandToCombatUnit((CombatUnits) unit);
        } else if (unit instanceof BuilderUnit) {
            giveCommandToBuilder((BuilderUnit) unit);
        } else if (unit.getUnitNameEnum() == UnitNameEnum.SETTLER) {
            giveCommandToSettler((NoneCombatUnits) unit);
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

    private void giveCommandToCombatUnit(CombatUnits combatUnit) {
        String input;
        Matcher matcher;
        while (true) {
            input = super.scanner.nextLine();
            if (false) {
                // TODO ---
            } else if ((matcher = getCommandMatcher(input, PlayGameCommandsRegex.SLEEP.toString())) != null) {
                System.out.println(gameMenuCommandController.sleepCombatUnit(combatUnit));
            } else if ((matcher = getCommandMatcher(input, PlayGameCommandsRegex.WAKE.toString())) != null) {
                System.out.println(gameMenuCommandController.wakeCombatUnit(combatUnit));
            } else if ((matcher = getCommandMatcher(input, PlayGameCommandsRegex.ALERT.toString())) != null) {
                System.out.println(gameMenuCommandController.alertCombatUnit(combatUnit));
            } else if ((matcher = getCommandMatcher(input, PlayGameCommandsRegex.GARRISON.toString())) != null) {
                System.out.println(gameMenuCommandController.garrisonCombatUnit(combatUnit));
            } else if ((matcher = getCommandMatcher(input, PlayGameCommandsRegex.FORTIFY.toString())) != null) {
                System.out.println(gameMenuCommandController.fortifyCombatUnit(combatUnit));
            } else if ((matcher = getCommandMatcher(input, PlayGameCommandsRegex.DELETE.toString())) != null) {
                System.out.println(gameMenuCommandController.deleteCombatUnit(combatUnit));
            } else if ((matcher = getCommandMatcher(input, PlayGameCommandsRegex.PILLAGE.toString())) != null) {
                System.out.println(gameMenuCommandController.pillageTile(combatUnit));
            } else if ((matcher = getCommandMatcher(input, PlayGameCommandsRegex.ATTACK_UNIT.toString())) != null) {
                System.out.println(gameMenuCommandController.attackUnit(combatUnit, matcher, gamemap, player));
            } else if ((matcher = getCommandMatcher(input, PlayGameCommandsRegex.ATTACK_CITY.toString())) != null) {
                System.out.println(gameMenuCommandController.attackCity(combatUnit, matcher, player, players));
            } else if ((matcher = getCommandMatcher(input, PlayGameCommandsRegex.MOVE_CIVILIAN.toString())) != null) {
                System.out.println(gameMenuCommandController.moveCivilian(matcher, gamemap, player));
            } else if ((matcher = getCommandMatcher(input, PlayGameCommandsRegex.END.toString())) != null)
                return;
            else
                System.out.println("invalid command!");
        }
    }

    private void giveCommandToBuilder(BuilderUnit builder) {
        String input;
        Matcher matcher;
        while (true) {
            input = super.scanner.nextLine();
            if (false) {
                // TODO ---
            } else if ((matcher = getCommandMatcher(input, PlayGameCommandsRegex.CLEAR_LAND.toString())) != null) {
                System.out.println(gameMenuCommandController.clearLand(builder));
            } else if ((matcher = getCommandMatcher(input, PlayGameCommandsRegex.IMPLEMENT_IMPROVEMENT.toString())) != null) {
                System.out.println(gameMenuCommandController.implementImprovement(matcher, builder));
            } else if ((matcher = getCommandMatcher(input, PlayGameCommandsRegex.REPAIR_IMPROVEMENT.toString())) != null) {
                System.out.println(gameMenuCommandController.repairImprovement(builder));
            } else if ((matcher = getCommandMatcher(input, PlayGameCommandsRegex.REPAIR_BUILDING.toString())) != null) {
                System.out.println(gameMenuCommandController.repairBuilding(matcher, builder));
            } else if ((matcher = getCommandMatcher(input, PlayGameCommandsRegex.MOVE_CIVILIAN.toString())) != null) {
                System.out.println(gameMenuCommandController.moveCivilian(matcher, gamemap, player));
            } else if ((matcher = getCommandMatcher(input, PlayGameCommandsRegex.END.toString())) != null)
                return;
            else
                System.out.println("invalid command!");
        }
    }

    private void giveCommandToSettler(NoneCombatUnits settler) {
        Matcher matcher;
        String input;
        while (true) {
            input = super.scanner.nextLine();
            if ((matcher = getCommandMatcher(input, PlayGameCommandsRegex.CREATE_CITY.toString())) != null) {
                Output output = gameMenuCommandController.createCity(matcher, settler, player, players);
                System.out.println(output);
                if (output == Output.CITY_CREATED)
                    return;
            } else if ((matcher = getCommandMatcher(input, PlayGameCommandsRegex.MOVE_CIVILIAN.toString())) != null) {
                System.out.println(gameMenuCommandController.moveCivilian(matcher, gamemap, player));
            } else if ((matcher = getCommandMatcher(input, PlayGameCommandsRegex.END.toString())) != null)
                return;
            else
                System.out.println("invalid command!");
        }
    }

}