package views;

public enum LoginMenuCommandsRegex {
    USER_LOGIN("user login(?: (--username|-u) (?<username>\\S+)()| (--password|-p) (?<password>\\S+)()){2}\\3\\6"),
    REGISTER("register(?: (--username|-u) (?<username>\\S+)()| (--password|-p) (?<password>\\S+)()" +
            "| (--nickname|-n) (?<nickname>\\S+)()){3}\\3\\6\\9"),
    LIST_OF_USERS("list of users"),
    EXIT("menu exit"),
    SHOW_MENU("menu show-current");



    private String regex;

    LoginMenuCommandsRegex(String regex) {
        this.regex = regex;
    }

    @Override
    public String toString() {
        return this.regex;
    }
}