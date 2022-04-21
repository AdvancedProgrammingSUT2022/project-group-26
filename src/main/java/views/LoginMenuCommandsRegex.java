package views;

public enum LoginMenuCommandsRegex {
    USER_LOGIN("user login (--username|--u) (?<username>\\S+) (--password|--p) (?<password>\\S+)"),
    REGISTER("register (--username|--u) (?<username>\\S+) (--password|--p) (?<password>\\S+) (--nickname|--n) (?<nickname>\\S+)"),
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
