package views.info;

import models.Player;
import models.Technology.Tech;
import models.Technology.TechEnum;
import models.UsersDatabase;
import views.Menu;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;

public class TechnologyInfo extends Menu {

    Player player;

    public TechnologyInfo(UsersDatabase usersDatabase, Player player) {
        super(usersDatabase);
        setPlayer(player);
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public void run() {
        String input;
        Matcher matcher;
        while (true) {
            input = super.scanner.nextLine();
            if ((matcher = getCommandMatcher(input, TechnologyInfoEnum.EXIT.toString())) != null) {
                return;
            }
            if ((matcher = getCommandMatcher(input, TechnologyInfoEnum.SHOW_POSSIBLE_TECHNOLOGY.toString())) != null) {
                showPossibleTechnology();
            } else {
                System.out.println("invalid command!");
            }
        }
    }

    private void showPossibleTechnology() {
        ArrayList<Tech> possibleTechs = player.getPossibleTechnology();
        for (int i = 0; i < possibleTechs.size(); i++) {
            int turn = (int) Math.ceil((double) (possibleTechs.get(i).getCost() -
                    possibleTechs.get(i).getEarnedCost() - player.getScience()) / player.getTurnScience());
            System.out.println(possibleTechs.get(i).getTechName().getName() + " turns left: " + turn);
        }
    }
}