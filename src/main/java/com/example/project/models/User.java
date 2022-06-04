package com.example.project.models;

import com.example.project.models.GlobalChat.PrivateChat;
import com.example.project.models.GlobalChat.Room;

import java.util.ArrayList;

public class User {
    private String username;
    private String password;
    private String nickname;
    private int highScore = 0;
    private boolean online = false;
    private ArrayList<PrivateChat> privateChats = new ArrayList<>();
    private ArrayList<Room> rooms = new ArrayList<>();

    public User(String username, String password, String nickname) {
        setUsername(username);
        setNickname(nickname);
        setPassword(password);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getHighScore() {
        return highScore;
    }

    public void setHighScore(int highScore) {
        this.highScore = highScore;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }

    public ArrayList<PrivateChat> getPrivateChats() {
        return privateChats;
    }

    public void setPrivateChats(ArrayList<PrivateChat> privateChats) {
        this.privateChats = privateChats;
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public void setRooms(ArrayList<Room> rooms) {
        this.rooms = rooms;
    }
}