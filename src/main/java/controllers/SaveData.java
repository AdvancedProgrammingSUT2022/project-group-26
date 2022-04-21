package controllers;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import models.Game;
import models.User;
import models.UsersDatabase;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class SaveData {
    public static void saveUsers(UsersDatabase usersDatabase) {
        try {
            FileWriter myWriter = new FileWriter("src/main/resources/usernames.json");
            myWriter.write(new Gson().toJson(usersDatabase.getUsers()));
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void loadUsers(UsersDatabase usersDatabase) {
        try {
            String usernames = new String(Files.readAllBytes(Paths.get("src/main/resources/usernames.json")));
            usersDatabase.setUsers(new Gson().fromJson(usernames, new TypeToken<List<User>>() {
            }.getType()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void savePlayers() {

    }

    public static void loadPlayers() {

    }

    public static void saveGame(Game game) {

    }

    public static void loadGame(Game game) {

    }
}
