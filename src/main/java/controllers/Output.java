package controllers;

public enum Output {
    repeatedUsername("user with this username already exists!"),
    invalidUsername("invalid username!"),
    invalidPassword("invalid password!"),
    invalidNickname("invalid nickname!"),
    weakPassword("password is weak!"),
    registered("user registered successfully!"),
    noExistingUser("no user exists with this username!"),
    incorrectPasswordOrUsername("Username and password didn’t match!"),

    loggedIn("user logged in successfully!"),

    invalidMenu("invalid menu!"),
    repeatedNickname("user with this nickname already exists!"),
    nicknameChanged("nickname changed successfully!"),
    wrongPass("current password is invalid!"),
    samePass("please enter a new password!"),
    passwordChanged("password changed successfully!"),
    incorrectPassword("incorrect password!"),
    userRemove("user deleted!"),
    invalidCoordinate("invalid Coordinate!"),

    VALID_PLAYERS("game created"),
    NOT_ENOUGH_INPUT("at least two valid users needed !"),
    INCORRECT_USERNAME("some usernames are invalid"),
    INCORRECT_PLAYER_NUMBER("only 1 - 6 numbers are valid !"),
    PLAYERS_MISSING("some players are missing"),

    startTileAndUnitDontMatchUp("unit doesn't exist on the starting tile"),
    youDontOwnThisUnit("this unit belongs to another player"),
    enemyCombatUnitOnThatTile("there is a enemy combat unit there so you cant move there"),
    enemyNonCombatUnitOnThatTile("there is a enemy civilian there so you cant move there"),
    youAlreadyHaveATroopThere("there is a friendly unit there !"),
    movedSuccessfully("moved unit successfully !"),

    noCombatUnitHere("there is no combat unit in the starting position"),
    noUnitThere("there is no unit to attack in the ending position"),
    CantCaptureWithRangedUnits("cant capture civilians with ranged units"),
    attackSuccessFull("attacked successfully !"),

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
