package views;

public enum ProfileMenuCommandsRegex {
    CHANGE_NICKNAME("profile change (--nickname|--n) (?<nickname>\\S+)"),
    CHANGE_PASSWORD("profile change (--password|--p) --current (?<currentPassword>\\S+) --new (?<newPassword>\\S+)"),
    EXIT("menu exit"),
    SHOW_MENU("menu show-current"),
    SHOW_INFORMATION("show user information"),
    REMOVE_USER("user remove (--password|--p) (?<password>\\S+)");

    private String regex;

    ProfileMenuCommandsRegex(String regex) {
        this.regex = regex;
    }

    @Override
    public String toString() {
        return this.regex;
    }
}