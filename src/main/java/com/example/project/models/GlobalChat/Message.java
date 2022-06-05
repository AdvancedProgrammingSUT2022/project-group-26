package com.example.project.models.GlobalChat;

import com.example.project.models.User;

public class Message {
    private User user;
    private String message;
    private String clock;
    private boolean seen = false;
    private boolean isDeletedForUser = false;

    public Message(User user, String message, String clock) {
        this.user = user;
        this.message = message;
        this.clock = clock;

    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getClock() {
        return clock;
    }

    public void setClock(String clock) {
        this.clock = clock;
    }

    public boolean isSeen() {
        return seen;
    }

    public void setSeen(boolean seen) {
        this.seen = seen;
    }

    public boolean isDeletedForUser() {
        return isDeletedForUser;
    }

    public void setDeletedForUser(boolean deletedForUser) {
        isDeletedForUser = deletedForUser;
    }
}
