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

    VALID_PLAYERS("game created"),
    NOT_ENOUGH_INPUT("at least two valid users needed !"),
    INCORRECT_USERNAME("some usernames are invalid"),

    userRemove("user deleted!"),
    invalidCoordinate("invalid Coordinate!"),
    INVALID_COMMAND("invalid command!"),
    EXTRA_PLAYER_NUMBERS("a maximum of 6 players are acceptable!"),
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
