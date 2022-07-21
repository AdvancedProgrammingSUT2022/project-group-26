package com.example.project.models;

public enum AutoSaveType {
    EVERY_ROUND,
    EVERY_ATTACK,
    EVERY_10_MIN;

    public static AutoSaveType getFromString(String type) {
        if (type.equals("every round")) return EVERY_ROUND;
        if (type.equals("every attack")) return EVERY_ATTACK;
        if (type.equals("every 10 min")) return EVERY_10_MIN;
        return null;
    }
}
