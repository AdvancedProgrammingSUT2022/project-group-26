package views.info;

public enum InformationInfoEnum {
    SHOW_INFORMATION("show information"),
    EXIT("exit"),
    ;

    private String regex;

    InformationInfoEnum(String regex) {
        this.regex = regex;
    }

    public String toString() {
        return this.regex;
    }
}
