package com.example.project.models;

import java.util.ArrayList;

public class Game {
    private static Game instance;

    public static Game getInstance() {
        if (instance == null) instance = new Game();
        return instance;
    }

    public void startGame(){
        players.add(new Player(new User("ilya", "ilya", "ilya")));
        players.add(new Player(new User("mammad", "ad", "")));
        players.add(new Player(new User("mammad", "ad", "")));
        players.add(new Player(new User("mammad", "ad", "")));
        players.add(new Player(new User("mammad", "ad", "")));
        players.add(new Player(new User("mammad", "ad", "")));

        gamemap = new GameMap(players);
    }

    private ArrayList<Player> players;
    private GameMap gamemap;
    private int turn;

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

    public GameMap getGamemap() {
        return gamemap;
    }

    public void setGamemap(GameMap gamemap) {
        this.gamemap = gamemap;
    }
}