package com.example.project.controllers;

import com.example.project.models.DataBase;
import com.example.project.models.GlobalChat.PrivateChat;
import com.example.project.models.User;
import com.example.project.views.GlobalChatMenu;

import java.util.ArrayList;

public class GlobalChatController {
    private static GlobalChatController instance;

    public static void setNull(){
        instance = null;
    }

    public static GlobalChatController getInstance() {
        if (instance == null) instance = new GlobalChatController();
        return instance;
    }

    public boolean isValidLongMessage(String message) {
        return !(message.length() > 25);
    }

    public boolean isValidShortMessage(String message) {
        return !(message.length() <= 0);
    }

    public ArrayList<User> showUsernamesStartsWithString(String username) {
        ArrayList<User> users = new ArrayList<>();
        for (User user : DataBase.getInstance().getUsersDatabase().getUsers())
            if (user.getUsername().startsWith(username))
                if (user != DataBase.getInstance().getLoggedInUser())
                    users.add(user);
        return users;
    }

    public PrivateChat getUserPrivateChat(User user) {
        for (PrivateChat privateChat : DataBase.getInstance().getLoggedInUser().getPrivateChats())
            if (privateChat.getOtherUser(DataBase.getInstance().getLoggedInUser()) == user)
                return privateChat;
        PrivateChat privateChat = new PrivateChat(DataBase.getInstance().getLoggedInUser(), user);
        user.getPrivateChats().add(privateChat);
        DataBase.getInstance().getLoggedInUser().getPrivateChats().add(privateChat);
        return privateChat;
    }

    public static void setGlobalChatNull() {
        GlobalChatController.setNull();
        GlobalChatMenu.setNull();
    }
}