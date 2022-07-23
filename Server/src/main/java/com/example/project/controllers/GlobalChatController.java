package com.example.project.controllers;

import com.example.project.models.DataBase;
import com.example.project.models.GlobalChat.Message;
import com.example.project.models.GlobalChat.PrivateChat;
import com.example.project.models.GlobalChat.PublicChat;
import com.example.project.models.GlobalChat.Room;
import com.example.project.models.Request;
import com.example.project.models.User;

import java.util.ArrayList;

public class GlobalChatController {

    private PrivateChat privateChat;
    private Room room;
    private int chatMode = 0;

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

    public void updatePublicSeenMessages(User loggedInUser) {
        for (Message message : PublicChat.getInstance().getAllMessages())
            if (message.getUser() != loggedInUser)
                message.setSeen(true);
    }

    public void editMessage(Request request) {
        System.out.println(request.getParams());
        int number = (int) (double) request.getParams().get("number");
        String toEditMessage = (String) request.getParams().get("message");
        if (chatMode == 0)
            PublicChat.getInstance().getAllMessages().get(number).setMessage(toEditMessage);
        if (chatMode == 1)
            privateChat.getMessages().get(number).setMessage(toEditMessage);
        if (chatMode == 2)
            room.getMessages().get(number).setMessage(toEditMessage);
    }

}