package com.example.project.views;

public enum MainMenuCommandsRegex {
    ENTER_MENU("menu enter (?<menuName>\\S+)"),
    EXIT("menu exit"),
    SHOW_MENU("menu show-current"),
    LOGOUT("user logout"),
    SHOW_SCOREBOARD("show scoreboard"),
    START_GAME("start game (-d|--difficulty) (?<difficulty>\\S+) (?<input>.*)");

    private String regex;

    MainMenuCommandsRegex(String regex) {
        this.regex = regex;
    }

    @Override
    public String toString() {
        return this.regex;
    }
}