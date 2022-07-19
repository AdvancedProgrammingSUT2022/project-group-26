package com.example.project.models;

import java.util.ArrayList;

public class Game {
    private static Game instance;

    public static Game getInstance() {
        if (instance == null) instance = new Game();
        return instance;
    }

    public void startGame(ArrayList<User> users) {
        players = new ArrayList<>();
        for (User user : users)
            players.add(new Player(user));
        gameMap = new GameMap(players);
        thisTurnPlayer = players.get(0);
    }

    private ArrayList<Player> players; // player
    private GameMap gameMap; // ok
    private int turn; // ok
    private Player thisTurnPlayer; // player

    public Game() {
    }

    public ArrayList<Player> getPlayers() {
        return this.players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public int getTurn() {
        return this.turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public GameMap getGameMap() {
        return gameMap;
    }

    public void setGameMap(GameMap gameMap) {
        this.gameMap = gameMap;
    }

    public Player getThisTurnPlayer() {
        return thisTurnPlayer;
    }

    public void setThisTurnPlayer(Player thisTurnPlayer) {
        this.thisTurnPlayer = thisTurnPlayer;
    }

    public void nextTurn() {
        thisTurnPlayer.endTurn(gameMap, false);
        int index = players.indexOf(thisTurnPlayer);
        index = (index + 1) % players.size();
        thisTurnPlayer = players.get(index);
    }
}