package com.example.project.models.GlobalChat;

import java.util.ArrayList;

public class PublicChat {
    private static PublicChat instance;
    private ArrayList<Message> allMessages = new ArrayList<>();

    public synchronized static PublicChat getInstance() {
        if (instance == null) instance = new PublicChat();
        return instance;
    }

    public synchronized static void setInstance(PublicChat instance1) {
        instance = instance1;
    }

    public ArrayList<Message> getAllMessages() {
        return allMessages;
    }

    public void setAllMessages(ArrayList<Message> allMessages) {
        this.allMessages = allMessages;
    }

    public void addMessage(Message message) {
        allMessages.add(message);
    }
}