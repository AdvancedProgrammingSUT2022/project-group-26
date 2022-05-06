package views.info;

public enum CityInfoEnum {

    SHOW_CITY_BANNER("show city banner (--city|-c) (?<cityName>\\S+)"),
    SHOW_CITY_FOOD("show food (--city|-c) (?<cityName>\\S+)"),
    EXIT("exit"),
    SHOW_CITIES("show all cities"),
    ECONOMIC_INFO("economic info"),
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