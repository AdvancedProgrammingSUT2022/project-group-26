package views.info;

import controllers.GameControllers.GameMenuCommandController;
import controllers.Output;
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

    private Player player;
    private GameMenuCommandController gameMenuCommandController;

    public TechnologyInfo(UsersDatabase usersDatabase, Player player, GameMenuCommandController gameMenuCommandController) {
        super(usersDatabase);
        setPlayer(player);
        setGameMenuCommandController(gameMenuCommandController);
    }

    public void setGameMenuCommandController(GameMenuCommandController gameMenuCommandController) {
        this.gameMenuCommandController = gameMenuCommandController;
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
            } else if ((matcher = getCommandMatcher(input, TechnologyInfoEnum.SHOW_POSSIBLE_TECHNOLOGY.toString())) != null) {
                showPossibleTechnology();
            } else if ((matcher = getCommandMatcher(input, TechnologyInfoEnum.RESEARCH.toString())) != null) {
                System.out.println(gameMenuCommandController.research(matcher, player));
            } else if ((matcher = getCommandMatcher(input, TechnologyInfoEnum.SHOW_TECH_IN_RESEARCH.toString())) != null) {
                showTechInResearch();
            } else if ((matcher = getCommandMatcher(input, TechnologyInfoEnum.SHOW_RESEARCHED_TECHS.toString())) != null) {
                showResearchedTechs();
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
            if(turn <= 0) turn = 1;
            System.out.println(possibleTechs.get(i).getTechName().getName() + " turns left: " + turn);
        }
    }

    private void showTechInResearch() {
        if (player.getTechInResearch() != null) {
            int turn = (int) Math.ceil((double) (player.getTechInResearch().getCost() -
                    player.getTechInResearch().getEarnedCost() - player.getScience()) / player.getTurnScience());
            if(turn <= 0) turn = 1;
            System.out.println(player.getTechInResearch().getTechName().getName() + " turns left: " + turn);
        } else System.out.println("you don't have in research technology");
    }

    private void showResearchedTechs() {
        for (int i = 0; i < player.getFullyResearchedTechs().size(); i++)
            System.out.println((i + 1) + "- " + player.getFullyResearchedTechs().get(i).getTechName().getName());
    }
}