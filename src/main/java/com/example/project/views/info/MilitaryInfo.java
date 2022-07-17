package com.example.project.views.info;

import com.example.project.controllers.GameControllers.GameMenuCommandController;
import com.example.project.models.GameMap;
import com.example.project.models.Player;
import com.example.project.models.Units.Combat.CombatUnit;
import com.example.project.models.Units.Nonecombat.BuilderUnit;
import com.example.project.models.Units.Unit;
import com.example.project.models.UsersDatabase;
import com.example.project.views.Menu;

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
            if (unit instanceof CombatUnit) {
                System.out.println(" action: " + ((CombatUnit) unit).getActionToString());
            } else if (unit instanceof BuilderUnit) {
                if (((BuilderUnit) unit).getWork() != null)
                    System.out.println(" is building " + ((BuilderUnit) unit).getWork());
                else System.out.println(" no responsibility!");
            } else System.out.println();
            counter++;
        }
    }
}

