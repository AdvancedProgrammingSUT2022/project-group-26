package com.example.project.controllers;

import com.example.project.models.Game;
import com.example.project.models.GameMap;
import com.example.project.models.Player;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.AnyTypePermission;


public class MainGameSaver {
    private int turn;
    private GameMap gameMap;
    private ArrayList<Player> players;


    private MainGameSaver() {
    }

    public static MainGameSaver getInstance() {
        MainGameSaver data = new MainGameSaver();
        data.turn = Game.getInstance().getTurn();
        data.gameMap = Game.getInstance().getGamemap();
        data.players = Game.getInstance().getPlayers();
        return data;
    }

    public void setToGameDataBase() {
        Game.getInstance().setTurn(turn);
        Game.getInstance().setGamemap(gameMap);
        Game.getInstance().setPlayers(players);
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
