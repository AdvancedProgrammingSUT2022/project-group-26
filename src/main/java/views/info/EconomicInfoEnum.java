package views.info;

public enum EconomicInfoEnum {
    SHOW_CITY_BANNER("show city banner (--city|-c) (?<cityName>\\S+)"),
    SHOW_CITY_FOOD("show food (--city|-c) (?<cityName>\\S+)"),
    EXIT("exit"),
    SHOW_CITIES("show all cities"),
    CITY_DATA("city data (--name|-n) (?<cityName>\\S+)")
    ;

    private String regex;

    EconomicInfoEnum(String regex) {
        this.regex = regex;
    }

    public String toString() {
        return this.regex;
    }
}
