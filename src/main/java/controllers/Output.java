package controllers;

public enum Output {
    REPEATED_USERNAME("user with this username already exists!"),
    INVALID_USERNAME("invalid username!"),
    INVALID_PASSWORD("invalid password!"),
    INVALID_NICKNAME("invalid nickname!"),
    WEAK_PASSWORD("password is weak!"),
    REGISTERED("user registered successfully!"),
    NO_EXISTING_USER("no user exists with this username!"),
    INCORRECT_PASSWORD_OR_USERNAME("Username and password didn't match!"),

    LOGGED_IN("user logged in successfully!"),

    INVALID_MENU("invalid menu!"),
    REPEATED_NICKNAME("user with this nickname already exists!"),
    NICKNAME_CHANGED("nickname changed successfully!"),
    WRONG_PASSWORD("current password is invalid!"),
    SAME_PASSWORD("please enter a new password!"),
    PASSWORD_CHANGED("password changed successfully!"),
    INCORRECT_PASSWORD("incorrect password!"),

    VALID_PLAYERS("game created!"),
    NOT_ENOUGH_INPUT("at least two valid users needed!"),
    INCORRECT_USERNAME("some usernames are invalid!"),
    INCORRECT_PLAYER_NUMBER("only 1 - 6 numbers are valid!"),
    PLAYERS_MISSING("some players are missing!"),
    INVALID_DIFFICULTY("invalid difficulty!"),

    startTileAndUnitDontMatchUp("unit doesn't exist on the starting tile!"),
    youDontOwnThisUnit("this unit belongs to another player!"),
    enemyCombatUnitOnThatTile("there is a enemy combat unit there so you cant move there!"),
    enemyNonCombatUnitOnThatTile("there is a enemy civilian there so you cant move there!"),
    youAlreadyHaveATroopThere("there is a friendly unit there!"),
    movedSuccessfully("moved unit successfully!"),
    NOT_ENOUGH_MOVEMENT_POINTS("not enough movement points!"),

    noCombatUnitHere("there is no combat unit in the starting position!"),
    noUnitThere("there is no unit to attack in the ending position!"),
    CantCaptureWithRangedUnits("cant capture civilians with ranged units!"),
    attackSuccessFull("attacked successfully!"),
    CANT_ATTACK_YOURSELF("cant attack a friendly"),
    CITY_NOT_YOURS("city doesn't belong to you!"),
    NO_UNIT_TO_ATTACK("there is no unit to attack"),

    // merge conflict !

    userRemove("user deleted!"),
    invalidCoordinate("invalid Coordinate!"),
    INVALID_COMMAND("invalid command!"),
    EXTRA_PLAYER_NUMBERS("a maximum of 6 players are acceptable!"),
    FOG_OF_WAR("destination tile is out of sight!"),

    NO_EXISTING_COMBAT_UNITS("there is no combat unit in this tile!"),
    NO_EXISTING_NONE_COMBAT_UNITS("there is no none combat unit in this tile!"),
    NO_EXISTING_SETTLER("there is no settler in this tile!"),
    NO_EXISTING_BUILDER("there is no builder in this tile!"),
    SETTLER_NOT_YOURS("the selected settler is not yours!"),
    BUILDER_NOT_YOURS("the builder settler is not yours!"),
    COMBAT_UNIT_NOT_YOURS("the combat unit settler is not yours!"),

    UNABLE_CREATE_CITY("you can't create city in this tile!"),
    CITY_CREATED("city created successfully!"),
    INVALID_TECHNOLOGY_NAME("invalid technology name!"),
    RESEARCHED_TECHNOLOGY("technology has been already researched!"),
    RESEARCHED("researched successfully!"),
    IS_RESEARCHING("this technology is already in research!"),
    NO_CITY("you should create city first!"),


    CITY_IS_BUSY("city is building something!"),
    YOUR_TECH_IS_BEHIND("you need to improve your tech!"),
    DONT_HAVE_THE_NEEDED_RESOURCES("you need to improve your resources!"),
    INVALID_BUILD_NAME("invalid unit or building name!"),
    NOT_ENOUGH_GOLD("not enough gold!"),
    UNIT_CREATED("unit created!"),
    BUILDING_CREATED("building created!"),
    UNIT_GETTING_CREATED("unit is getting build!"),
    BUILDING_GETTING_CREATED("building is getting build!"),


    LONG_ROUTE("cant go that far!"),
    ADDED_ROUTE("added route to unit !"),
    INVALID_CITY("invalid city!"),
    INVALID_CITY_NAME("invalid city name!"),

    UNIT_NOT_YOURS("this unit doesn't belong to you!"),
    NOT_A_WORKER("you should select a worker!"),
    WORKER_IS_BUSY("worker is currently working!"),
    NO_FEATURE_TO_REMOVE("this tile has no features!"),
    REMOVING_FEATURE("worker is removing feature"),
    TILE_HAS_ROAD("this tile has a road built on it"),
    BUILDING_ROAD("worker is building the road"),
    CANT_PUT_AN_IMPROVEMENT_HERE("cant work here?!"),
    IMPROVING_TILE("tile is getting improved"),
    IMPROVEMENT_IS_NOT_BROKEN("cant repair a healthy improvement"),
    IMPROVEMENT_GETTING_REPAIRED("getting repaired !"),

    NOT_YOUR_TERRITORY("this tile is not on your city territory"),
    TILE_IS_FREE("this tile was free from the start"),
    TILE_FREED_SUCCESSFULLY("tile freed successfully"),
    TWO_CITIZEN_ON_A_TILE("cant assign 2 citizens on a tile"),
    NO_FREE_CITIZEN("no free citizen - free a citizen"),
    CITIZEN_ASSIGNED_SUCCESSFULLY("citizen assigned successfully"),
    ALL_CITIZENS_ASSIGNED_SUCCESSFULLY("all citizens assigned as the type requested"),

    BUY_TILE_SUCCESSFULLY("tile bought successfully!"),
    INVALID_TILE("invalid tile!"),
    REMOVE_CITY("city removed successfully!"),


    BUG("error --- possible bug"),

    ;


    private String output;

    Output(String output) {
        this.output = output;
    }

    @Override
    public String toString() {
        return this.output;
    }
}
