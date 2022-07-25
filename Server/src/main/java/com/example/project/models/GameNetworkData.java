package com.example.project.models;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.security.AnyTypePermission;

import java.util.ArrayList;
import java.util.HashMap;


public class GameNetworkData {
    private ArrayList<User> users;
    private HashMap<City, Integer> food;
    private HashMap<Player, Integer> happiness;
    private HashMap<Player, Integer> gold;
    private ArrayList<River> river;
    private ArrayList<Player> players;
    private GameMap gameMap;
    private int turn;
    private Player thisTurnPlayer;

    private GameNetworkData() {
    }

    public static GameNetworkData getInstance() {
        GameNetworkData data = new GameNetworkData();
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


    public static Response sendGame() {
        Response response = new Response(Output.GAME_DATA);
        XStream xStream = new XStream();
        String res = xStream.toXML(GameNetworkData.getInstance());
        response.setData(res);
        return response;
    }


    public static void getGame(Request request) {
        String xml = request.getData();
        XStream xStream = new XStream();
        xStream.addPermission(AnyTypePermission.ANY);
        if (xml.length() != 0) {
            GameNetworkData game = (GameNetworkData) xStream.fromXML(xml);
            game.setToGameDataBase();
        }
    }
}
