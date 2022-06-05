package com.example.project.models.GlobalChat;

import com.example.project.models.User;

import java.util.ArrayList;

public class Room {
    private String roomName;
    private ArrayList<User> participants = new ArrayList<>();
    private ArrayList<Message> messages = new ArrayList<>();

    public Room(ArrayList<User> participants, String roomName) {
        setParticipants(participants);
        setRoomName(roomName);
    }

    public ArrayList<Message> getMessages() {
        return messages;
    }

    public void setMessages(ArrayList<Message> messages) {
        this.messages = messages;
    }

    public ArrayList<User> getParticipants() {
        return participants;
    }

    public void setParticipants(ArrayList<User> participants) {
        this.participants = participants;
    }

    public String getRoomName() {
        return roomName;
    }

    public void setRoomName(String roomName) {
        this.roomName = roomName;
    }
}
