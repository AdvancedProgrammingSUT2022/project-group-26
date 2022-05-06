package views.info;

public enum UnitInfoEnum {
    SHOW_ALL_UNITS("show all units"),
    SELECT_UNIT("select unit (--number|-n) (?<number>\\d+)"),
    EXIT("exit"),
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
