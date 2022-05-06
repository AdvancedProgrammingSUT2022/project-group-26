package views.info;

public enum UnitIInfoEnum {
    SHOW_ALL_UNITS("show all units"),
    ;

    private String regex;
    UnitIInfoEnum(String regex) {
        this.regex = regex;
    }

    @Override
    public String toString(){
        return this.regex;
    }
}
