package com.example.project.controllers;

import com.example.project.models.Output;
import com.example.project.models.Response;
import com.example.project.models.User;
import com.example.project.models.UsersDatabase;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.Comparator;

public class ScoreBoardController {
    private static ArrayList<User> users = UsersDatabase.getInstance().getUsers();


    public static Response sendData() {
        return new Response(Output.DATA, formatData());
    }

    private static String formatData() {
        sortUsers(users);
        ArrayList<User> data = new ArrayList<>(10);
        for (int i = 0; i < users.size() && i < 10; i++) {
            data.add(users.get(i));
        }
        return new GsonBuilder().create().toJson(users);
    }


    private static void sortUsers(ArrayList<User> users) {
        users.sort(Comparator.comparing(User::getHighScore)
                .thenComparing(User::getHighScoreTime)
                .thenComparing(User::getNickname)
                .thenComparing(User::getUsername).reversed());
    }
}
