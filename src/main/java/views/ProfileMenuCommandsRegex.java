package views;

public enum ProfileMenuCommandsRegex {
    changeNickname("profile change (--nickname|--n) (?<nickname>\\S+)"),
    changePassword("profile change (--password|--p) --current (?<currentPassword>\\S+) --new (?<newPassword>\\S+)"),
    exit("menu exit"),
    showMenu("menu show-current"),
    showInformation("show user information"),
    removeUser("user remove (--password|--p) (?<password>\\S+)");

    private String regex;

    ProfileMenuCommandsRegex(String regex) {
        this.regex = regex;
    }

    @Override
    public String toString() {
        return this.regex;
    }
}
