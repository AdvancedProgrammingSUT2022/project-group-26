package views;

public enum PlayGameCommandsRegex {
    SHOW_MAP("show map(?: --iCoordinate (?<iCoordinate>\\d+)()| --jCoordinate (?<jCoordinate>\\d+)()){2}\\2\\4"),
    END_GAME("end game"),
    SHOW_MENU("menu show-current"),
    END_TURN("end turn"),
    endTurn("end turn"),
    MOVE_COMBAT_UNIT("move combat unit(?: -i1 (?<indexStartI>\\d+)()| -j1 (?<indexStartJ>\\d+)()" +
            "| -i2 (?<indexEndI>\\d+)()| -j2 (?<indexEndJ>\\d+)()){4}\\2\\4\\6\\8"),
    MOVE_CIVILIAN("move civilian(?: -i1 (?<indexStartI>\\d+)()| -j1 (?<indexStartJ>\\d+)()" +
            "| -i2 (?<indexEndI>\\d+)()| -j2 (?<indexEndJ>\\d+)()){4}\\2\\4\\6\\8"),
    END("end"),
    LEFT("left (?<moveCount>\\d+)"),
    UP("up (?<moveCount>\\d+)"),
    RIGHT("right (?<moveCount>\\d+)"),
    DOWN("down (?<moveCount>\\d+)"),
    SELECT_SETTLER("select settler(?: --iCoordinate (?<iCoordinate>\\d+)()| --jCoordinate (?<jCoordinate>\\d+)()){2}\\2\\4"),
    BUILD_IN_CITY("in city --name (?<cityName>\\S+) --build (?<build>\\S+)"),
    CREATE_CITY("create city --name (?<cityName>\\S+)"),
    ENTER_TECHNOLOGY_MENU("technology info"),
    SHOW_MAP_BY_CITY("show map --city (?<cityName>\\S+)"),
    INCREASE_TURN("increase -turn (?<amount>\\d+)"),
    INCREASE_GOLD("increase -gold (?<amount>\\d+)"),
    INCREASE_FOOD("increase -food (?<amount>\\d+) -city (?<cityName>\\S+)"),
    INCREASE_HAPPINESS("increase -happiness (?<amount>\\d+)"),
    INCREASE_MOVEMENT("increase -movement (?<amount>\\d+)" +
            "(?: --iCoordinate (?<iCoordinate>\\d+)()| --jCoordinate (?<jCoordinate>\\d+)()){2}\\2\\4"),
    INCREASE_SCIENCE("increase -science (?<amount>\\d+)"),
    WIN("win"),
    SHOW_GOLD("show gold"),
    SHOW_HAPPINESS("show happiness"),
    SHOW_CITY_FOOD("show food --city (?<cityName>\\S+)"),
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