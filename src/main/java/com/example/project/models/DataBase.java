package com.example.project.models;

import com.example.project.models.GlobalChat.Room;

import java.util.ArrayList;
import java.util.Arrays;

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
        if (instance == null) {
            instance = new DataBase();
            {
                instance.setLoggedInUser(new User("ilya", "ilya", "ilya"));
                instance.getUsersDatabase().addUser(instance.getLoggedInUser());
                instance.getUsersDatabase().addUser(new User("paria", "paria", "paria"));
                instance.getUsersDatabase().addUser(new User("paria", "paria", "paria"));
                instance.getUsersDatabase().addUser(new User("paria", "paria", "paria"));
                instance.getUsersDatabase().addUser(new User("paria", "paria", "paria"));
                instance.getUsersDatabase().addUser(new User("paria", "paria", "paria"));
                instance.getUsersDatabase().addUser(new User("paria", "paria", "paria"));
                instance.getUsersDatabase().addUser(new User("paria", "paria", "paria"));
                instance.getUsersDatabase().addUser(new User("paria", "paria", "paria"));
                instance.getUsersDatabase().addUser(new User("paria", "paria", "paria"));
                instance.getUsersDatabase().addUser(new User("paria", "paria", "paria"));

            }
        }
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
