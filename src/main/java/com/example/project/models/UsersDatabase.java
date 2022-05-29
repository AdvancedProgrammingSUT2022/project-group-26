package com.example.project.models;

import java.util.ArrayList;

public class UsersDatabase {
    private ArrayList<User> users = new ArrayList<>();
    private static UsersDatabase instance;

    public static UsersDatabase getInstance() {
        if (instance == null) instance = new UsersDatabase();
        return instance;
    }

    public ArrayList<User> getUsers() {
        return users;
    }

    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    public void addUser(User user) {
        this.users.add(user);
    }

    public void removeUser(User user) {
        this.users.remove(user);
    }

    public User getUserByUsername(String username) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getUsername().equals(username))
                return users.get(i);
        }
        return null;
    }

    public User getUserByNickname(String nickname) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getNickname().equals(nickname))
                return users.get(i);
        }
        return null;
    }
}