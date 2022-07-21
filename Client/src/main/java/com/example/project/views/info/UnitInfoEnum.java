package com.example.project.views.info;

public enum UnitInfoEnum {
    SHOW_ALL_UNITS("show all units"), // done
    SELECT_UNIT("select unit (--number|-n) (?<number>\\d+)"), // done needs debugging
    EXIT("exit"), // done
    MILITARY_INFO("military info"), // not completed in unit info and i don't know what is it
    ;

    private String regex;

    UnitInfoEnum(String regex) {
        this.regex = regex;
    }

    @Override
    public String toString() {
        return this.regex;
    }
}
