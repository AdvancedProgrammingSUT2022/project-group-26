package com.example.project.views.info;

public enum CityInfoEnum {

    SHOW_CITY_BANNER("show city banner (--city|-c) (?<cityName>\\S+)"), // done
    SHOW_CITY_FOOD("show food (--city|-c) (?<cityName>\\S+)"), // done
    EXIT("exit"), // done
    SHOW_CITIES("show all cities"), // done
    ECONOMIC_INFO("economic info"), // nothing in view ?
    ;

    private String regex;

    CityInfoEnum(String regex) {
        this.regex = regex;
    }

    @Override
    public String toString() {
        return this.regex;
    }
}