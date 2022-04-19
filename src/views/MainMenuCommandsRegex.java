package views;

public enum MainMenuCommandsRegex {
    enterMenu("menu enter (?<menuName>\\S+)"),
    exit("menu exit"),
    showMenu("menu show-current"),
    logout("user logout"),
    showScoreBoard("show scoreboard");
    private String regex;

    MainMenuCommandsRegex(String regex) {
        this.regex = regex;
    }

    @Override
    public String toString() {
        return this.regex;
    }
}