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
    private HashMap<Player, GameMap> maps;
    private GameMap gameMap;
    private int turn;
    private Player thisTurnPlayer;
    private Player allOfGameThisTurnPlayer;

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
        data.allOfGameThisTurnPlayer = Game.getInstance().getAllOfGameThisTurnPlayer();
        data.maps = new HashMap<>();
        for (Player player : data.players) {
            data.maps.put(player, player.getGameMap());
        }
        return data;
    }

    public void setToGameDataBase() {
        Food.setCitiesSavedFood(food);
        Happiness.setPlayersHappiness(happiness);
        Gold.setPlayersSavedGold(gold);
        River.setRivers(river);
        Game.getInstance().setPlayers(players);
        Game.getInstance().setGameMap(gameMap);
        Game.getInstance().setTurn(turn);
        for (Player player : players) {
            if (player.getUser().equals(DataBase.getInstance().getLoggedInUser()))
                Game.getInstance().setThisTurnPlayer(player);
        }
        Game.getInstance().setAllOfGameThisTurnPlayer(allOfGameThisTurnPlayer);
        if (DataBase.getInstance().getLoggedInUser().equals(allOfGameThisTurnPlayer.getUser()))
            Game.setIsYourTurn(true);
        else Game.setIsYourTurn(false);
        maps.forEach((k, v) -> k.setGameMap(v));
    }


    public static void sendGame() {
        XStream xStream = new XStream();
        String data = xStream.toXML(GameNetworkData.getInstance());
        int length = data.length();
        for (int i = 0; i < 50; i++) {
            Request request = new Request(RequestEnum.SEND_DATA,
                    data.substring((i * length) / 50, ((i + 1) * length) / 50));
            Network.getInstance().sendRequestWithoutResponse(request);
        }
    }

    public static void getGame() {
        Request request = new Request(RequestEnum.GET_DATA);
        Network.getInstance().sendRequestWithoutResponse(request);
        StringBuilder xmlBuilder = new StringBuilder("");
        for (int i = 0; i < 50; i++) {
            xmlBuilder.append(Network.getInstance().getResponse().getData());
        }
        String xml = xmlBuilder.toString();
        XStream xStream = new XStream();
        xStream.addPermission(AnyTypePermission.ANY);
        if (xml.length() != 0) {
            GameNetworkData game = (GameNetworkData) xStream.fromXML(xml);
            game.setToGameDataBase();
        }
    }

    public static void getAllOfGame(Response response) {
        StringBuilder xmlBuilder = new StringBuilder("");
        xmlBuilder.append(response.getData());
        for (int i = 0; i < 49; i++) {
            xmlBuilder.append(Network.getInstance().getResponse().getData());
        }
        String xml = xmlBuilder.toString();
        XStream xStream = new XStream();
        xStream.addPermission(AnyTypePermission.ANY);
        if (xml.length() != 0) {
            GameNetworkData game = (GameNetworkData) xStream.fromXML(xml);
            game.setToGameDataBase();
        }
    }
}