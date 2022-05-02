package controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.graph.GraphAdapterBuilder;
import com.google.gson.reflect.TypeToken;
import models.Game;
import models.GameMap;
import models.User;
import models.UsersDatabase;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class SaveData {
    public static void saveUserDataBase(UsersDatabase usersDatabase) {
        try {
            FileWriter myWriter = new FileWriter("src/main/resources/usernames.json");
            myWriter.write(new GsonBuilder().create().toJson(usersDatabase));
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // bugs : static fields -- enum types

    public static UsersDatabase loadAndReturnUserDataBase() {
        try {
            String usernames = new String(Files.readAllBytes(Paths.get("src/main/resources/usernames.json")));
            return (new GsonBuilder().create().fromJson(usernames, UsersDatabase.class));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void saveGame(Game game) {
        try {
            FileWriter myWriter = new FileWriter("src/main/resources/game.json");
            GsonBuilder gsonBuilder = new GsonBuilder();
            new GraphAdapterBuilder().addType(Game.class).registerOn(gsonBuilder);
            myWriter.write(gsonBuilder.create().toJson(game));
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static Game loadGame() {
        try {
            String gameData = new String(Files.readAllBytes(Paths.get("src/main/resources/gameMap.json")));
            return (new GsonBuilder().create().fromJson(gameData, Game.class));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static void saveMap(GameMap game) {
        try {
            FileWriter myWriter = new FileWriter("src/main/resources/gameMap.json");
            GsonBuilder gsonBuilder = new GsonBuilder();
//            new GraphAdapterBuilder().addType(GameMap.class).registerOn(gsonBuilder);
            myWriter.write(gsonBuilder.create().toJson(game));
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static GameMap loadMap() {
        try {
            String gameData = new String(Files.readAllBytes(Paths.get("src/main/resources/game.json")));
            return (new GsonBuilder().create().fromJson(gameData, GameMap.class));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
