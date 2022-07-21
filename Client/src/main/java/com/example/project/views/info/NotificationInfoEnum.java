package com.example.project.views.info;

public enum NotificationInfoEnum {
    SHOW_INFORMATION("show all previous notifications"), // done
    EXIT("exit"), // done
    ;

    private String regex;

    NotificationInfoEnum(String regex) {
        this.regex = regex;
    }

    public String toString() {
        return this.regex;
    }
}