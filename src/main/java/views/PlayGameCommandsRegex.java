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
    BUILD_IN_CITY("build in city --name (?<cityName>\\S+) --build (?<build>\\S+)"),
    INSTANT_BUILD_IN_CITY("instant build in city --name (?<cityName>\\S+) --build (?<build>\\S+)"),
    ASSIGN_ALL_PLAYER_CITIZENS_AUTOMATICALLY("assign all player citizens --type (?<type>(food|production|gold|economy))"),
    ASSIGN_ALL_CITY_CITIZENS_AUTOMATICALLY("assign all city citizens --cityName (?<cityName>\\S+) --type (?<type>(food|production|gold|economy))"),
    ASSIGN_A_CITIZEN_IN_CITY("add citizen of city to tile --cityName (?<cityName>\\S+) --iCoordinate (?<iCoordinate>\\d+) --jCoordinate (?<jCoordinate>\\d+)"),
    REMOVE_A_CITIZEN_IN_CITY("remove citizen of city from tile --cityName (?<cityName>\\S+) --iCoordinate (?<iCoordinate>\\d+) --jCoordinate (?<jCoordinate>\\d+)"),
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
    BUY_TILE_CITY("buy tile(?: --iCoordinate (?<iCoordinate>\\d+)()| " +
            "--jCoordinate (?<jCoordinate>\\d+)()| --name (?<cityName>\\S+)()){3}\\2\\4\\6"),
    REMOVE_CITY("remove city --name (?<cityName>\\S+)"),
    SHOW_CITY_BANNER("show city banner (--name|-n) (?<cityName>\\S+)")
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