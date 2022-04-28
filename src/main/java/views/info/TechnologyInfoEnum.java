package views.info;

public enum TechnologyInfoEnum {

    EXIT("exit"),
    SHOW_POSSIBLE_TECHNOLOGY("show possible technologies"),

    ;
    private String regex;

    TechnologyInfoEnum(String regex) {
        this.regex = regex;
    }

    @Override
    public String toString() {
        return this.regex;
    }

}
