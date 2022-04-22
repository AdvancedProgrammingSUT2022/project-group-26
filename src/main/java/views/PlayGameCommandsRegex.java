package views;

public enum PlayGameCommandsRegex {
    SHOW_MAP("show map --Coordinate (?<iCoordinate>\\d+) --Coordinate (?<jCoordinate>\\d+)"),
    END_GAME("end game"),
    SHOW_MENU("menu show-current"),
    END_TURN("end turn"),
    endTurn("end turn"),
    MOVE_COMBAT_UNIT("move combat unit -i1 (?<indexStartI>\\d+) -j1 (?<indexStartJ>\\d+) -i2 (?<indexEndI>\\d+) -j2 (?<indexEndJ>\\d+)"),
    MOVE_CIVILIAN("move civilian -i1 (?<indexStartI>\\d+) -j1 (?<indexStartJ>\\d+) -i2 (?<indexEndI>\\d+) -j2 (?<indexEndJ>\\d+)"),
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