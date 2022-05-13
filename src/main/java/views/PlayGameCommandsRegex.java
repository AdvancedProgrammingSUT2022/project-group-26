package views;

public enum PlayGameCommandsRegex {
    SHOW_MAP("show map(?: --iCoordinate (?<iCoordinate>\\d+)()| --jCoordinate (?<jCoordinate>\\d+)()){2}\\2\\4"),
    END_GAME("end game"),
    SHOW_MENU("menu show-current"),
    END_TURN("end turn"),
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
    SELECT_COMBAT_UNIT("select combat unit(?: --iCoordinate (?<iCoordinate>\\d+)()| --jCoordinate (?<jCoordinate>\\d+)()){2}\\2\\4"),
    SELECT_BUILDER("select builder(?: --iCoordinate (?<iCoordinate>\\d+)()| --jCoordinate (?<jCoordinate>\\d+)()){2}\\2\\4"),
    BUILD_IN_CITY("build in city --name (?<cityName>\\S+) --build (?<build>\\S+)"),
    INSTANT_BUILD_IN_CITY("instant build in city --name (?<cityName>\\S+) --build (?<build>\\S+)"),
    ASSIGN_ALL_PLAYER_CITIZENS_AUTOMATICALLY("assign all player citizens --type (?<type>(food|production|gold|economy))"),
    ASSIGN_ALL_CITY_CITIZENS_AUTOMATICALLY("assign all city citizens (--city|-c) (?<cityName>\\S+) --type (?<type>(food|production|gold|economy))"),
    ASSIGN_A_CITIZEN_IN_CITY("add citizen of city to tile (--city|-c) (?<cityName>\\S+) --iCoordinate (?<iCoordinate>\\d+) --jCoordinate (?<jCoordinate>\\d+)"),
    REMOVE_A_CITIZEN_IN_CITY("remove citizen of city from tile (--city|-c) (?<cityName>\\S+) --iCoordinate (?<iCoordinate>\\d+) --jCoordinate (?<jCoordinate>\\d+)"),
    CREATE_CITY("create city --name (?<cityName>\\S+)"),
    CITY_ATTACK("attack tile --iCoordinate (?<iCoordinate>\\d+) --jCoordinate (?<jCoordinate>\\d+) with  city --cityName (?<cityName>\\S+)"),
    ENTER_TECHNOLOGY_MENU("technology info"),
    SHOW_MAP_BY_CITY("show map (--city|-c) (?<cityName>\\S+)"),
    INCREASE_TURN("increase -turn (?<amount>\\d+)"),
    INCREASE_GOLD("increase -gold (?<amount>\\d+)"),
    INCREASE_FOOD("increase -food (?<amount>\\d+) -city (?<cityName>\\S+)"),
    INCREASE_HAPPINESS("increase -happiness (?<amount>\\d+)"),
    INCREASE_MOVEMENT("increase movement --amount (?<amount>\\d+) --name (?<name>\\S+)" +
            "(?: --iCoordinate (?<iCoordinate>\\d+)()| --jCoordinate (?<jCoordinate>\\d+)()){2}\\4\\6"),
    INCREASE_SCIENCE("increase -science (?<amount>\\d+)"),
    WIN("win game"),
    LOSE("win"),//this is a cheat that using this will knock the player out
    BUY_TECHNOLOGY("buy -technology (?<technology>\\S+)"),
    ATTACH_CITY("attach city --city (?<cityName>\\S+)"),
    SHOW_GOLD("show gold"),
    SHOW_HAPPINESS("show happiness"),
    SHOW_CITY_FOOD("show food (--city|-c) (?<cityName>\\S+)"),
    BUY_TILE_CITY("buy tile(?: --iCoordinate (?<iCoordinate>\\d+)()| " +
            "--jCoordinate (?<jCoordinate>\\d+)()| --name (?<cityName>\\S+)()){3}\\2\\4\\6"),
    REMOVE_CITY("remove city (--city|-c) (?<cityName>\\S+)"),
    CITY_INFO("city info"),
    SIEGE_SETUP("setup siege unit"),
    PILLAGE("pillage tile with unit"),
    ATTACK_UNIT("attack unit on tile(?: --iCoordinate (?<iCoordinate>\\d+)()| --jCoordinate (?<jCoordinate>\\d+)()){2}\\2\\4"),
    ATTACK_CITY("attack city --cityName (?<cityName>\\S+)"),
    SLEEP("sleep unit"),
    WAKE("wake unit"),
    ALERT("alert unit"),
    GARRISON("garrison unit"),
    FORTIFY("fortify unit"),
    DELETE("delete unit"),
    ADD_ROUTE("add unit route to tile --iCoordinate (?<iCoordinate>\\d+) --jCoordinate (?<jCoordinate>\\d+)"),
    RESET_ROUTE("reset unit route"),
    MOVE_FROM_ROUTE("move from saved route"),
    BUILD_ROAD("build a road"),
    CLEAR_LAND("clear land"),
    IMPLEMENT_IMPROVEMENT("implement improvement (?<improvementName>\\S+)"),
    REPAIR_IMPROVEMENT("repair improvement"),
    REPAIR_BUILDING("repair building (?<buildingName>\\S+)"),
    UNIT_INFO("unit info"),
    MILITARY_INFO("military info"),
    ECONOMIC_INFO("economic info"),
    DEMOGRAPHIC_INFO("demographic info"),
    NOTIFICATION_INFO("notification info"),
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