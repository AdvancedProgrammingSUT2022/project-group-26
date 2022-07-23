package com.example.project.models;

import javafx.scene.control.ChoiceBox;

import java.util.ArrayList;

public class Game {
    private static Game instance;

    public static void setNull() {
        instance = null;
    }

    public static Game getInstance() {
        if (instance == null) instance = new Game();
        return instance;
    }

    private ArrayList<Player> players; // player
    private GameMap gameMap; // ok
    private int turn; // ok
    private Player thisTurnPlayer; // player

    public Game() {
    }

    public void startGame(ArrayList<User> users) {
        players = new ArrayList<>();
        for (User user : users) {
            Player player = new Player(user);
            players.add(player);
        }
        gameMap = new GameMap(players);
        thisTurnPlayer = players.get(0);
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

    public void removePlayer(Player player) {
        player.updateScore();
        players.remove(player);
    }

    public void updatePlayers() {
        for (int i = Game.getInstance().getPlayers().size() - 1; i >= 0; i--)
            if (Game.getInstance().getPlayers().get(i).getCities().size() == 0 &&
                    Game.getInstance().getPlayers().get(i).getUnits().size() == 0) {
                if (Game.getInstance().thisTurnPlayer == Game.getInstance().getPlayers().get(i))
                    nextTurn();
                Game.getInstance().removePlayer(Game.getInstance().getPlayers().get(i));
            }
    }

    public Player getWinner() {
        if (!(turn == 2050 * 365 || (players.size() == 1 && players.get(0) == thisTurnPlayer))) return null;
        else {
            Player player = players.get(0);
            for (int i = 1; i < players.size(); i++)
                if (players.get(i).calculateScore() >= player.calculateScore())
                    player = players.get(i);
            player.updateScore();
            return player;
        }
    }
}