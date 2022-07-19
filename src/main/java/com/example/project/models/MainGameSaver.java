package com.example.project.models;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.AnyTypePermission;


public class MainGameSaver {
    private ArrayList<User> users;
    private HashMap<City, Integer> food;
    private HashMap<Player, Integer> happiness;
    private HashMap<Player, Integer> gold;
    private ArrayList<River> river;
    private ArrayList<Player> players;
    private GameMap gameMap;
    private int turn;
    private Player thisTurnPlayer;

    private MainGameSaver() {
    }

    public static MainGameSaver getInstance() {
        MainGameSaver data = new MainGameSaver();
        data.users = UsersDatabase.getInstance().getUsers();
        data.food = Food.getCitiesSavedFood();
        data.happiness = Happiness.getPlayersHappiness();
        data.gold = Gold.getPlayersSavedGold();
        data.river = River.getRivers();
        data.players = Game.getInstance().getPlayers();
        data.gameMap = Game.getInstance().getGameMap();
        data.turn = Game.getInstance().getTurn();
        data.thisTurnPlayer = Game.getInstance().getThisTurnPlayer();
        return data;
    }

    public void setToGameDataBase() {
        UsersDatabase.getInstance().setUsers(users);
        Food.setCitiesSavedFood(food);
        Happiness.setPlayersHappiness(happiness);
        Gold.setPlayersSavedGold(gold);
        River.setRivers(river);
        Game.getInstance().setPlayers(players);
        Game.getInstance().setGameMap(gameMap);
        Game.getInstance().setTurn(turn);
        Game.getInstance().setThisTurnPlayer(thisTurnPlayer);
    }


    public static void saveGame() {
        try {
            FileWriter fileWriter;
            if (Files.exists(Paths.get("data/gameInformation.xml")))
                fileWriter = new FileWriter("data/gameInformation.xml", false);
            else {
                new File("data").mkdir();
                fileWriter = new FileWriter("data/gameInformation.xml", false);
            }
            XStream xStream = new XStream();
            fileWriter.write(xStream.toXML(MainGameSaver.getInstance()));
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadGame() {
        try {
            String xml = new String(Files.readAllBytes(Paths.get("data/gameInformation.xml")));
            XStream xStream = new XStream();
            xStream.addPermission(AnyTypePermission.ANY);
            if (xml.length() != 0) {
                MainGameSaver game = (MainGameSaver) xStream.fromXML(xml);
                game.setToGameDataBase();
            }
        } catch (IOException ignored) {
        }
    }

}
