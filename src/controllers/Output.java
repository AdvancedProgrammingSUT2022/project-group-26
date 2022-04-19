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
    invalidCoordinate("invalid Coordinate!");


    private String output;

    Output(String output) {
        this.output = output;
    }

    @Override
    public String toString() {
        return this.output;
    }
}
