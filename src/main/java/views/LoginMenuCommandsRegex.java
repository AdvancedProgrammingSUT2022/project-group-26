package views;

public enum LoginMenuCommandsRegex {
    userLogin("user login (--username|--u) (?<username>\\S+) (--password|--p) (?<password>\\S+)"),
    register("register (--username|--u) (?<username>\\S+) (--password|--p) (?<password>\\S+) (--nickname|--n) (?<nickname>\\S+)"),
    listOfUsers("list of users"),
    exit("menu exit"),
    showMenu("menu show-current");
    


    private String regex;

    LoginMenuCommandsRegex(String regex) {
        this.regex = regex;
    }

    @Override
    public String toString() {
        return this.regex;
    }
}
