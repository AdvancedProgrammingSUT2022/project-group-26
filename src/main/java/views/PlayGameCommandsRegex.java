package views;

public enum PlayGameCommandsRegex {
    SHOW_MAP("show map --Coordinate (?<iCoordinate>\\d+) --Coordinate (?<jCoordinate>\\d+)"),
    END_GAME("end game"),
    SHOW_MENU("menu show-current"),
    END_TURN("end turn")
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