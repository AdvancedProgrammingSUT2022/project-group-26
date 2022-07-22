package com.example.project.controllers;

import com.example.project.models.DataBase;
import com.example.project.models.GlobalChat.Message;
import com.example.project.models.GlobalChat.PrivateChat;
import com.example.project.models.GlobalChat.PublicChat;
import com.example.project.models.Request;
import com.example.project.models.User;

import java.util.ArrayList;

public class GlobalChatController {

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

    public void addPublicMessage(Request request, User user) {
        Message message = new Message(user, (String) request.getParams().get("message"), (String) request.getParams().get("time"));
        PublicChat.getInstance().addMessage(message);
    }
}