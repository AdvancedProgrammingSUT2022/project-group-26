package views;

public enum PlayGameCommandsRegex {
    ShowMap("show map --Coordinate (?<iCoordinate>\\d+) --Coordinate (?<jCoordinate>\\d+)"),
    endGame("end game"),
    showMenu("menu show-current"),
    ;
    private String regex;

    PlayGameCommandsRegex(String regex) {
        this.regex = regex;
    }

    @Override
    public String toString() {
        return this.regex;
    }
}