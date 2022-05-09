package views.info;

public enum NotificationInfoEnum {
    SHOW_INFORMATION("show information"),
    EXIT("exit"),
    ;

    private String regex;

    NotificationInfoEnum(String regex) {
        this.regex = regex;
    }

    public String toString() {
        return this.regex;
    }
}