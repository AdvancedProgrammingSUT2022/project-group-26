package com.example.project.views.info;

public enum MilitaryInfoEnum {
    SHOW_ALL_UNITS("show all units"),
    EXIT("exit"),
    ;

    private String regex;

    MilitaryInfoEnum(String regex) {
        this.regex = regex;
    }

    @Override
    public String toString() {
        return this.regex;
    }
}
