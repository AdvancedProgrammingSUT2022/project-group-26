package com.example.project.models;

public class DataBase {
    private static DataBase instance;

    private UsersDatabase usersDatabase;
    private User loggedInUser;
    // other data
    // add in constructor

    private DataBase() {
        usersDatabase = UsersDatabase.getInstance();
    }

    public static DataBase getInstance() {
        if (instance == null)
            instance = new DataBase();
        return instance;
    }

    public User getLoggedInUser() {
        return loggedInUser;
    }

    public void setLoggedInUser(User loggedInUser) {
        this.loggedInUser = loggedInUser;
    }

    public UsersDatabase getUsersDatabase() {
        return usersDatabase;
    }

    public void setUsersDatabase(UsersDatabase usersDatabase) {
        this.usersDatabase = usersDatabase;
    }
}
