package views;

public enum PlayGameCommandsRegex {
    ShowMap("show map --Coordinate (?<iCoordinate>\\d+) --Coordinate (?<jCoordinate>\\d+)"),
    endGame("end game"),
    showMenu("menu show-current"),
    endTurn("end turn"),
    MOVE_UNIT("move unit -i1 (?<indexStartI>\\d+) -j1 (?<indexStartJ>\\d+) -i2 (?<indexEndI>\\d+) -j2 (?<indexEndJ>\\d+)"),
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