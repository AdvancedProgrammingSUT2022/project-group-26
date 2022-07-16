package com.example.project.views.info;

public enum EconomicInfoEnum {
    SHOW_CITY_BANNER("show city banner (--city|-c) (?<cityName>\\S+)"), // done
    SHOW_CITY_FOOD("show food (--city|-c) (?<cityName>\\S+)"), // done
    EXIT("exit"), // done
    SHOW_CITIES("show all cities"), // done
    CITY_DATA("city data (--name|-n) (?<cityName>\\S+)") // todo *
     ;

    private String regex;

    EconomicInfoEnum(String regex) {
        this.regex = regex;
    }

    public String toString() {
        return this.regex;
    }
    }
