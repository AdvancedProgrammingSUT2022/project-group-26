package com.example.project.models;

import java.util.ArrayList;

public class User {
    private String username;
    private String password;
    private String nickname;
    private int highScore = 0;

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
}