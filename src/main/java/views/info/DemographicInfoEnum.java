package views.info;

public enum DemographicInfoEnum {
    EXIT("exit"),
    SHOW_GOLD("show gold"),
    SHOW_MILITARY("show military"),
    SHOW_NUM_OF_TILES("show num of tiles"),
    SHOW_POPULATION("show population"),
    SHOW_HAPPINESS("show happiness"),
    SHOW_SCIENCE("show science"),
    SHOW_STRATEGIC("show strategic resources"),
    ;

    private String regex;

    DemographicInfoEnum(String regex) {
        this.regex = regex;
    }

    @Override
    public String toString() {
        return this.regex;
    }
}