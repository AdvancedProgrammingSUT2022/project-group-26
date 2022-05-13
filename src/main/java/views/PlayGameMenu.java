package views;

import controllers.GameControllers.GameMenuCommandController;
import controllers.GameControllers.PlayGameMenuController;
import controllers.GameControllers.ShowMapController;
import models.*;
import controllers.*;
import models.Tile.Tile;
import models.Units.Combat.CombatUnits;
import models.Units.Nonecombat.BuilderUnit;
import models.Units.Nonecombat.NoneCombatUnits;
import views.info.*;

import java.util.ArrayList;
import java.util.regex.Matcher;

public class PlayGameMenu extends Menu {
    private ArrayList<Player> players;
    private GameMap gamemap;
    private ShowMapController showMapController;
    private GameMenuCommandController gameMenuCommandController;
    private PlayGameMenuController playGameMenuController;
    private int difficult;

    public PlayGameMenu(ArrayList<Player> players, UsersDatabase usersDatabase, int difficult) {
        super(usersDatabase);
        this.players = players;
        gamemap = new GameMap(this.players);
        this.showMapController = new ShowMapController(gamemap, players);
        playGameMenuController = new PlayGameMenuController(gamemap, players);
        gameMenuCommandController = new GameMenuCommandController(playGameMenuController, gamemap);
        playGameMenuController.startGame(players, difficult);
        this.difficult = difficult;
    }

    @Override
    public void run() {
        String input;
        int playerNumber = 0;
        while (true) {
            players.get(playerNumber).updateMap(gamemap);
            showPlayerName(playerNumber);
            showMapAfterEachMove(playerNumber);
            Matcher matcher;
            input = super.scanner.nextLine();
            if ((matcher = getCommandMatcher(input, PlayGameCommandsRegex.SHOW_MAP.toString())) != null) {
                showMapCommand(matcher, playerNumber);
            } else if ((matcher = getCommandMatcher(input, PlayGameCommandsRegex.END_GAME.toString())) != null) {
                // TODO : add json command
                return;
            } else if ((matcher = getCommandMatcher(input, PlayGameCommandsRegex.SHOW_MENU.toString())) != null) {
                System.out.println("Game Menu");
            } else if ((matcher = getCommandMatcher(input, PlayGameCommandsRegex.END_TURN.toString())) != null) {
                players.get(playerNumber).endTurn(this.gamemap, false);
                playerNumber = playGameMenuController.nextPlayer(playerNumber, this.players);
                NotificationInfo.showUnseenNotifications(players.get(playerNumber));
            }
//            else if ((matcher = getCommandMatcher(input, PlayGameCommandsRegex.MOVE_COMBAT_UNIT.toString())) != null) {
//                System.out.println(gameMenuCommandController.moveCombatUnit(matcher, gamemap, players.get(playerNumber)));
//            } else if ((matcher = getCommandMatcher(input, PlayGameCommandsRegex.MOVE_CIVILIAN.toString())) != null) {
//                System.out.println(gameMenuCommandController.moveCivilian(matcher, gamemap, players.get(playerNumber)));
//            }
            else if ((matcher = getCommandMatcher(input, PlayGameCommandsRegex.SHOW_MAP_BY_CITY.toString())) != null) {
                showMapByCity(matcher, players.get(playerNumber));
            } else if ((matcher = getCommandMatcher(input, PlayGameCommandsRegex.SELECT_SETTLER.toString())) != null) {
                selectSettler(matcher, players.get(playerNumber));
            } else if ((matcher = getCommandMatcher(input, PlayGameCommandsRegex.SELECT_BUILDER.toString())) != null) {
                selectBuilder(matcher, players.get(playerNumber));
            } else if ((matcher = getCommandMatcher(input, PlayGameCommandsRegex.SELECT_COMBAT_UNIT.toString())) != null) {
                selectCombatUnit(matcher, players.get(playerNumber));
            } else if ((matcher = getCommandMatcher(input, PlayGameCommandsRegex.BUILD_IN_CITY.toString())) != null) {
                System.out.println(gameMenuCommandController.buildInCity(matcher, players.get(playerNumber), false));
            } else if ((matcher = getCommandMatcher(input, PlayGameCommandsRegex.INSTANT_BUILD_IN_CITY.toString())) != null) {
                System.out.println(gameMenuCommandController.buildInCity(matcher, players.get(playerNumber), true));
            } else if ((matcher = getCommandMatcher(input, PlayGameCommandsRegex.CITY_ATTACK.toString())) != null) {
                System.out.println(gameMenuCommandController.cityAttack(matcher, players.get(playerNumber), players, gamemap));
            } else if ((matcher = getCommandMatcher(input, PlayGameCommandsRegex.ASSIGN_ALL_PLAYER_CITIZENS_AUTOMATICALLY.toString())) != null) {
                System.out.println(gameMenuCommandController.assignForPlayer(matcher, players.get(playerNumber)));
            } else if ((matcher = getCommandMatcher(input, PlayGameCommandsRegex.ASSIGN_ALL_CITY_CITIZENS_AUTOMATICALLY.toString())) != null) {
                System.out.println(gameMenuCommandController.assignForCity(matcher, players.get(playerNumber)));
            } else if ((matcher = getCommandMatcher(input, PlayGameCommandsRegex.ASSIGN_A_CITIZEN_IN_CITY.toString())) != null) {
                System.out.println(gameMenuCommandController.assignACitizenOfACityToATile(matcher, players.get(playerNumber), gamemap));
            } else if ((matcher = getCommandMatcher(input, PlayGameCommandsRegex.REMOVE_A_CITIZEN_IN_CITY.toString())) != null) {                //
                System.out.println(gameMenuCommandController.removeACitizenOfACityFromATile(matcher, players.get(playerNumber), gamemap));
            } else if ((matcher = getCommandMatcher(input, PlayGameCommandsRegex.ENTER_TECHNOLOGY_MENU.toString())) != null) {
                technologyInfo(players.get(playerNumber));
            } else if ((matcher = getCommandMatcher(input, PlayGameCommandsRegex.INCREASE_TURN.toString())) != null) {
                gameMenuCommandController.increaseTurn(matcher, players.get(playerNumber));
            } else if ((matcher = getCommandMatcher(input, PlayGameCommandsRegex.INCREASE_FOOD.toString())) != null) {
                gameMenuCommandController.increaseFood(matcher, players.get(playerNumber));
            } else if ((matcher = getCommandMatcher(input, PlayGameCommandsRegex.INCREASE_HAPPINESS.toString())) != null) {
                gameMenuCommandController.increaseHappiness(matcher, players.get(playerNumber));
            } else if ((matcher = getCommandMatcher(input, PlayGameCommandsRegex.INCREASE_GOLD.toString())) != null) {
                gameMenuCommandController.increaseGold(matcher, players.get(playerNumber));
            } else if ((matcher = getCommandMatcher(input, PlayGameCommandsRegex.BUY_TECHNOLOGY.toString())) != null) {
                gameMenuCommandController.buyTechnology(matcher, players.get(playerNumber));
            } else if ((matcher = getCommandMatcher(input, PlayGameCommandsRegex.INCREASE_SCIENCE.toString())) != null) {
                gameMenuCommandController.increaseScience(matcher, players.get(playerNumber));
            } else if ((matcher = getCommandMatcher(input, PlayGameCommandsRegex.WIN.toString())) != null) {
                return;//with this player as winner
            } else if ((matcher = getCommandMatcher(input, PlayGameCommandsRegex.INCREASE_MOVEMENT.toString())) != null) {
                gameMenuCommandController.increaseMovement(matcher, players.get(playerNumber), gamemap);
            } else if ((matcher = getCommandMatcher(input, PlayGameCommandsRegex.ATTACH_CITY.toString())) != null) {
                gameMenuCommandController.attachCity(matcher, players.get(playerNumber), players);
            } else if ((matcher = getCommandMatcher(input, PlayGameCommandsRegex.LOSE.toString())) != null) {
                System.out.println(gameMenuCommandController.loseCheatCode(players.get(playerNumber)));
            } else if ((matcher = getCommandMatcher(input, PlayGameCommandsRegex.SHOW_GOLD.toString())) != null) {
                showGold(players.get(playerNumber));
            } else if ((matcher = getCommandMatcher(input, PlayGameCommandsRegex.SHOW_HAPPINESS.toString())) != null) {
                showHappiness(players.get(playerNumber));
            } else if ((matcher = getCommandMatcher(input, PlayGameCommandsRegex.SHOW_CITY_FOOD.toString())) != null) {
                showCityFood(players.get(playerNumber), matcher);
            } else if ((matcher = getCommandMatcher(input, PlayGameCommandsRegex.REMOVE_CITY.toString())) != null) {
                System.out.println(gameMenuCommandController.removeCity(matcher, players.get(playerNumber)));
            } else if ((matcher = getCommandMatcher(input, PlayGameCommandsRegex.BUY_TILE_CITY.toString())) != null) {
                System.out.println(gameMenuCommandController.buyCityTile(players.get(playerNumber), matcher, gamemap, players));
            } else if ((matcher = getCommandMatcher(input, PlayGameCommandsRegex.CITY_INFO.toString())) != null) {
                cityInfo(players.get(playerNumber));
            } else if ((matcher = getCommandMatcher(input, PlayGameCommandsRegex.UNIT_INFO.toString())) != null) {
                unitInfo(players.get(playerNumber));
            } else if ((matcher = getCommandMatcher(input, PlayGameCommandsRegex.MILITARY_INFO.toString())) != null) {
                militaryInfo(players.get(playerNumber));
            } else if ((matcher = getCommandMatcher(input, PlayGameCommandsRegex.ECONOMIC_INFO.toString())) != null) {
                economicInfo(players.get(playerNumber));
            } else if ((matcher = getCommandMatcher(input, PlayGameCommandsRegex.DEMOGRAPHIC_INFO.toString())) != null) {
                demoGraphicInfo(players.get(playerNumber));
            } else if ((matcher = getCommandMatcher(input, PlayGameCommandsRegex.NOTIFICATION_INFO.toString())) != null) {
                notificationInfo(players.get(playerNumber));
            } else {
                System.out.println("invalid command!");
            }
            // TODO: move Move methods to select unit command
        }
    }

    public void showMap(int iCoordinate, int jCoordinate, int playerNumber) {
        Player player = this.players.get(playerNumber);
        player.updateMap(this.gamemap);
        Tile[][] tilesToShow = new Tile[3][6];
        this.showMapController.setTileArrayToPrint(iCoordinate, jCoordinate, tilesToShow, player.getGameMap().getMap());
        String[][] toPrint = new String[80][80];
        this.showMapController.setToPrintStrings(toPrint, tilesToShow, iCoordinate, jCoordinate, playerNumber);
        for (int i = 0; i <= 21; i++) {
            for (int j = 0; j < 51; j++) {
                System.out.print(toPrint[i][j]);
            }
            System.out.println();
        }
    }

    public void showMapAfterEachMove(int playerNumber) {
        if (playerNumber < 3)
            showMap(8, (playerNumber % 3) * 6 + 5, playerNumber);
        else showMap(17, (playerNumber % 3) * 6 + 5, playerNumber);
    }

    public void showMapCommand(Matcher matcher, int playerNumber) {
        Output output = this.gameMenuCommandController.showMap(matcher);
        if (output != null) {
            System.out.println(output.toString());
            return;
        } else {
            int iCoordinate = Integer.parseInt(matcher.group("iCoordinate"));
            int jCoordinate = Integer.parseInt(matcher.group("jCoordinate"));
            showMap(iCoordinate, jCoordinate, playerNumber);
            changeDirection(iCoordinate, jCoordinate, playerNumber);
        }
    }

    public void showMapByCity(Matcher matcher, Player player) {
        Output output = this.gameMenuCommandController.isValidCity(matcher, player);
        if (output != null) {
            System.out.println(output);
            return;
        }
        showMapByCity(player, player.getCityByName(matcher.group("cityName")));
    }

    private void showMapByCity(Player player, City city) {
        int iCoordinate = this.gamemap.getIndexI(city.getCenter());
        int jCoordinate = this.gamemap.getIndexJ(city.getCenter());
        showMap(iCoordinate - 1, jCoordinate - 2, players.indexOf(player));
        changeDirection(iCoordinate, jCoordinate, players.indexOf(player));
    }

    private void changeDirection(int iCoordinate, int jCoordinate, int playerNumber) {
        String input;
        while (true) {
            input = super.scanner.nextLine();
            Matcher matcher;
            if ((matcher = getCommandMatcher(input, PlayGameCommandsRegex.LEFT.toString())) != null) {
                int moveCount = -(Integer.parseInt(matcher.group("moveCount")));
                changeDirectionHorizontal(iCoordinate, jCoordinate, playerNumber, moveCount);
                if (gameMenuCommandController.changeShowMapDirection(iCoordinate, jCoordinate + moveCount) == null)
                    jCoordinate += moveCount;
            } else if ((matcher = getCommandMatcher(input, PlayGameCommandsRegex.DOWN.toString())) != null) {
                int moveCount = Integer.parseInt(matcher.group("moveCount"));
                changeDirectionVertical(iCoordinate, jCoordinate, playerNumber, moveCount);
                if (gameMenuCommandController.changeShowMapDirection(iCoordinate + moveCount, jCoordinate) == null)
                    iCoordinate += moveCount;
            } else if ((matcher = getCommandMatcher(input, PlayGameCommandsRegex.RIGHT.toString())) != null) {
                int moveCount = Integer.parseInt(matcher.group("moveCount"));
                changeDirectionHorizontal(iCoordinate, jCoordinate, playerNumber, moveCount);
                if (gameMenuCommandController.changeShowMapDirection(iCoordinate, jCoordinate + moveCount) == null)
                    jCoordinate += moveCount;
            } else if ((matcher = getCommandMatcher(input, PlayGameCommandsRegex.UP.toString())) != null) {
                int moveCount = -(Integer.parseInt(matcher.group("moveCount")));
                changeDirectionVertical(iCoordinate, jCoordinate, playerNumber, moveCount);
                if (gameMenuCommandController.changeShowMapDirection(iCoordinate + moveCount, jCoordinate) == null)
                    iCoordinate += moveCount;
            } else if (getCommandMatcher(input, PlayGameCommandsRegex.END.toString()) != null) return;
            else System.out.println("invalid command!");
        }
    }

    private void changeDirectionHorizontal(int iCoordinate, int jCoordinate, int playerNumber, int moveCount) {
        Output output = gameMenuCommandController.changeShowMapDirection(iCoordinate, jCoordinate + moveCount);
        if (output != null)
            System.out.println(output.toString());
        else {
            jCoordinate += moveCount;
            showMap(iCoordinate, jCoordinate, playerNumber);
        }
    }

    private void changeDirectionVertical(int iCoordinate, int jCoordinate, int playerNumber, int moveCount) {
        Output output = gameMenuCommandController.changeShowMapDirection(iCoordinate + moveCount, jCoordinate);
        if (output != null)
            System.out.println(output.toString());
        else {
            iCoordinate += moveCount;
            showMap(iCoordinate, jCoordinate, playerNumber);
        }
    }

    private void selectSettler(Matcher matcher, Player player) {
        Output output = gameMenuCommandController.selectSettler(matcher, player, this.gamemap);
        if (output != null) {
            System.out.println(output.toString());
            return;
        }
        giveCommandToSettler(matcher, player);
    }

    private void selectBuilder(Matcher matcher, Player player) {
        Output output = gameMenuCommandController.selectBuilder(matcher, player, this.gamemap);
        if (output != null) {
            System.out.println(output.toString());
            return;
        }
        giveCommandToBuilder(matcher, player);
    }

    private void selectCombatUnit(Matcher matcher, Player player) {
        Output output = gameMenuCommandController.selectCombatUnit(matcher, player, this.gamemap);
        if (output != null) {
            System.out.println(output.toString());
            return;
        }
        giveCommandToCombatUnit(matcher, player);
    }


    private void giveCommandToSettler(Matcher matcher, Player player) {
        NoneCombatUnits settler = playGameMenuController.findSettler(matcher, player);
        String input;
        while (true) {
            input = super.scanner.nextLine();
            if ((matcher = getCommandMatcher(input, PlayGameCommandsRegex.CREATE_CITY.toString())) != null) {
                Output output = gameMenuCommandController.createCity(matcher, settler, player, players);
                System.out.println(output);
                if (output == Output.CITY_CREATED)
                    return;
            } else if ((matcher = getCommandMatcher(input, PlayGameCommandsRegex.SLEEP.toString())) != null) {
                System.out.println(gameMenuCommandController.sleepUnit(settler));
            } else if ((matcher = getCommandMatcher(input, PlayGameCommandsRegex.WAKE.toString())) != null) {
                System.out.println(gameMenuCommandController.wakeUnit(settler));
            } else if ((matcher = getCommandMatcher(input, PlayGameCommandsRegex.ALERT.toString())) != null) {
                System.out.println(gameMenuCommandController.alertUnit(settler));
            } else if ((matcher = getCommandMatcher(input, PlayGameCommandsRegex.ADD_ROUTE.toString())) != null) {
                System.out.println(gameMenuCommandController.addRoute(matcher, gamemap, settler, player));
            } else if ((matcher = getCommandMatcher(input, PlayGameCommandsRegex.RESET_ROUTE.toString())) != null) {
                System.out.println(gameMenuCommandController.resetRoute(settler));
            } else if ((matcher = getCommandMatcher(input, PlayGameCommandsRegex.MOVE_FROM_ROUTE.toString())) != null) {
                System.out.println(gameMenuCommandController.moveFromRoute(settler));
            } else if ((matcher = getCommandMatcher(input, PlayGameCommandsRegex.DELETE.toString())) != null) {
                System.out.println(gameMenuCommandController.deleteUnit(settler));
            } else if ((matcher = getCommandMatcher(input, PlayGameCommandsRegex.END.toString())) != null)
                return;
            else
                System.out.println("invalid command!");
        }
    }

    private void giveCommandToCombatUnit(Matcher matcher, Player player) {
        CombatUnits combatUnit = playGameMenuController.findCombatUnit(matcher, player);
        String input;
        while (true) {
            input = super.scanner.nextLine();
            if (false) {
                // TODO ---
                // todo : sleep for non combats ?
                // todo : 1 command  a turn !
            } else if ((matcher = getCommandMatcher(input, PlayGameCommandsRegex.SLEEP.toString())) != null) {
                System.out.println(gameMenuCommandController.sleepUnit(combatUnit));
            } else if ((matcher = getCommandMatcher(input, PlayGameCommandsRegex.WAKE.toString())) != null) {
                System.out.println(gameMenuCommandController.wakeUnit(combatUnit));
            } else if ((matcher = getCommandMatcher(input, PlayGameCommandsRegex.ALERT.toString())) != null) {
                System.out.println(gameMenuCommandController.alertUnit(combatUnit));
            } else if ((matcher = getCommandMatcher(input, PlayGameCommandsRegex.GARRISON.toString())) != null) {
                System.out.println(gameMenuCommandController.garrisonCombatUnit(combatUnit));
            } else if ((matcher = getCommandMatcher(input, PlayGameCommandsRegex.FORTIFY.toString())) != null) {
                System.out.println(gameMenuCommandController.fortifyCombatUnit(combatUnit));
            } else if ((matcher = getCommandMatcher(input, PlayGameCommandsRegex.DELETE.toString())) != null) {
                System.out.println(gameMenuCommandController.deleteUnit(combatUnit));
            } else if ((matcher = getCommandMatcher(input, PlayGameCommandsRegex.PILLAGE.toString())) != null) {
                System.out.println(gameMenuCommandController.pillageTile(combatUnit));
            } else if ((matcher = getCommandMatcher(input, PlayGameCommandsRegex.SIEGE_SETUP.toString())) != null) {
                System.out.println(gameMenuCommandController.siegeSetup(combatUnit));
            } else if ((matcher = getCommandMatcher(input, PlayGameCommandsRegex.ATTACK_UNIT.toString())) != null) {
                System.out.println(gameMenuCommandController.attackUnit(combatUnit, matcher, gamemap, player));
            } else if ((matcher = getCommandMatcher(input, PlayGameCommandsRegex.ATTACK_CITY.toString())) != null) {
                System.out.println(gameMenuCommandController.attackCity(combatUnit, matcher, player, players));
            } else if ((matcher = getCommandMatcher(input, PlayGameCommandsRegex.ADD_ROUTE.toString())) != null) {
                System.out.println(gameMenuCommandController.addRoute(matcher, gamemap, combatUnit, player));
            } else if ((matcher = getCommandMatcher(input, PlayGameCommandsRegex.RESET_ROUTE.toString())) != null) {
                System.out.println(gameMenuCommandController.resetRoute(combatUnit));
            } else if ((matcher = getCommandMatcher(input, PlayGameCommandsRegex.MOVE_FROM_ROUTE.toString())) != null) {
                System.out.println(gameMenuCommandController.moveFromRoute(combatUnit));
            } else if ((matcher = getCommandMatcher(input, PlayGameCommandsRegex.END.toString())) != null)
                return;
            else
                System.out.println("invalid command!");
        }
    }

    private void giveCommandToBuilder(Matcher matcher, Player player) {
        BuilderUnit builder = playGameMenuController.findBuilder(matcher, player);
        String input;
        while (true) {
            input = super.scanner.nextLine();
            if (false) {
                // TODO ---
            } else if ((matcher = getCommandMatcher(input, PlayGameCommandsRegex.SLEEP.toString())) != null) {
                System.out.println(gameMenuCommandController.sleepUnit(builder));
            } else if ((matcher = getCommandMatcher(input, PlayGameCommandsRegex.WAKE.toString())) != null) {
                System.out.println(gameMenuCommandController.wakeUnit(builder));
            } else if ((matcher = getCommandMatcher(input, PlayGameCommandsRegex.ALERT.toString())) != null) {
                System.out.println(gameMenuCommandController.alertUnit(builder));
            } else if ((matcher = getCommandMatcher(input, PlayGameCommandsRegex.BUILD_ROAD.toString())) != null) {
                // todo  !! -- how to save my roads ? -- dont :)
                System.out.println(gameMenuCommandController.buildRoad(builder, gamemap, player));
            } else if ((matcher = getCommandMatcher(input, PlayGameCommandsRegex.CLEAR_LAND.toString())) != null) {
                System.out.println(gameMenuCommandController.clearLand(builder, player));
            } else if ((matcher = getCommandMatcher(input, PlayGameCommandsRegex.IMPLEMENT_IMPROVEMENT.toString())) != null) {
                System.out.println(gameMenuCommandController.implementImprovement(matcher, builder, player));
            } else if ((matcher = getCommandMatcher(input, PlayGameCommandsRegex.REPAIR_IMPROVEMENT.toString())) != null) {
                System.out.println(gameMenuCommandController.repairImprovement(builder, player));
            } else if ((matcher = getCommandMatcher(input, PlayGameCommandsRegex.REPAIR_BUILDING.toString())) != null) {
                System.out.println(gameMenuCommandController.repairBuilding(matcher, builder, player));
            } else if ((matcher = getCommandMatcher(input, PlayGameCommandsRegex.ADD_ROUTE.toString())) != null) {
                System.out.println(gameMenuCommandController.addRoute(matcher, gamemap, builder, player));
            } else if ((matcher = getCommandMatcher(input, PlayGameCommandsRegex.RESET_ROUTE.toString())) != null) {
                System.out.println(gameMenuCommandController.resetRoute(builder));
            } else if ((matcher = getCommandMatcher(input, PlayGameCommandsRegex.MOVE_FROM_ROUTE.toString())) != null) {
                System.out.println(gameMenuCommandController.moveFromRoute(builder));
            } else if ((matcher = getCommandMatcher(input, PlayGameCommandsRegex.DELETE.toString())) != null) {
                System.out.println(gameMenuCommandController.deleteUnit(builder));
            } else if ((matcher = getCommandMatcher(input, PlayGameCommandsRegex.END.toString())) != null)
                return;
            else
                System.out.println("invalid command!");
        }
    }

    private void technologyInfo(Player player) {
        Output output = gameMenuCommandController.hasMadeCity(player);
        if (output != null) {
            System.out.println(output);
            return;
        }
        TechnologyInfo technologyInfo = new TechnologyInfo(usersDatabase, player, gameMenuCommandController);
        technologyInfo.run();
    }

    private void showGold(Player player) {
        System.out.println("gold: " + player.getGold());
        if (player.getGoldProduction() <= 0)
            System.out.println("gold production: " + player.getGoldProduction());
        else System.out.println("gold production: +" + player.getGoldProduction());
    }

    private void showHappiness(Player player) {
        System.out.println("happiness: " + player.getHappiness());
    }

    private void showCityFood(Player player, Matcher matcher) {
        Output output = gameMenuCommandController.isValidCity(matcher, player);
        if (output != null) {
            System.out.println(output);
            return;
        }
        City city = player.getCityByName(matcher.group("cityName"));
        System.out.println("city: " + city.getName() + " food: " + Food.getFoodProduction(city));
    }

    public void showPlayerName(int playerNumber) {
        System.out.println("player" + playerNumber + " name: " + players.get(playerNumber).getUser().getUsername());
        System.out.println("Character: " + Character.toString('A' + playerNumber));
    }

    public void cityInfo(Player player) {
        Output output = gameMenuCommandController.hasMadeCity(player);
        if (output != null) {
            System.out.println(output);
            return;
        }
        CityInfo cityInfo = new CityInfo(usersDatabase, gameMenuCommandController, player, players);
        cityInfo.run();
    }

    public void unitInfo(Player player) {
        UnitInfo unitInfo = new UnitInfo(usersDatabase, player, gameMenuCommandController, players, gamemap);
        unitInfo.run();
    }

    public void militaryInfo(Player player) {
        MilitaryInfo militaryInfo = new MilitaryInfo(usersDatabase, player, gameMenuCommandController, players, gamemap);
        militaryInfo.run();
    }

    public void economicInfo(Player player) {
        Output output = gameMenuCommandController.hasMadeCity(player);
        if (output != null) {
            System.out.println(output);
            return;
        }
        EconomicInfo economicInfo = new EconomicInfo(usersDatabase, gameMenuCommandController, player, players);
        economicInfo.run();
    }

    public void demoGraphicInfo(Player player) {
        DemographicInfo demographicInfo = new DemographicInfo(usersDatabase, player, gameMenuCommandController, players, gamemap);
        demographicInfo.run();
    }

    public void notificationInfo(Player player) {
        NotificationInfo notificationInfo = new NotificationInfo(usersDatabase, player, gameMenuCommandController, players, gamemap);
        notificationInfo.run();
    }
}