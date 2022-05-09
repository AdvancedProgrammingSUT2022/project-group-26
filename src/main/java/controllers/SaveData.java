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
        String usernames = readFile("src/main/resources/usernames.json");
        return (new GsonBuilder().create().fromJson(usernames, UsersDatabase.class));
    }

    public static String readFile(String path) {
        try {
            return new String(Files.readAllBytes(Paths.get(path)));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}