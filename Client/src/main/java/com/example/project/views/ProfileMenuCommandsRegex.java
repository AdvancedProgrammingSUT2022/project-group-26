package com.example.project.views;

public enum ProfileMenuCommandsRegex {
    CHANGE_NICKNAME("profile change (--nickname|-n) (?<nickname>\\S+)"),
    CHANGE_USERNAME("profile change (--username|-username) (?<username>\\S+)"),
    CHANGE_PASSWORD("profile change(?: (--password|-p) --current (?<currentPassword>\\S+)()" +
            "| --new (?<newPassword>\\S+)()){2}\\3\\5"),
    EXIT("menu exit"),
    SHOW_MENU("menu show-current"),
    SHOW_INFORMATION("show user information"),
    REMOVE_USER("user remove (--password|-p) (?<password>\\S+)");

    private String regex;

    ProfileMenuCommandsRegex(String regex) {
        this.regex = regex;
    }

    @Override
    public String toString() {
        return this.regex;
    }
}