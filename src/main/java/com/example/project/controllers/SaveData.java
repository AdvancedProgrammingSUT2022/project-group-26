package com.example.project.controllers;

import com.google.gson.GsonBuilder;
import com.example.project.models.UsersDatabase;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class SaveData {
    public static void saveUserDataBase(UsersDatabase usersDatabase) {
        try {
            FileWriter myWriter = new FileWriter("src/main/Json/resources/usernames.json");
            myWriter.write(new GsonBuilder().create().toJson(usersDatabase));
            myWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // bugs : static fields -- enum types

    public static UsersDatabase loadAndReturnUserDataBase() {
        String usernames = readFile("src/main/resources/Json/usernames.json");
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