package com.example.project.models;

import java.util.ArrayList;

public class DataBase {
    private static ArrayList<Network> onlineNetworks = new ArrayList<>();
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

    public static ArrayList<Network> getOnlineNetworks() {
        return onlineNetworks;
    }

    public static void setOnlineNetworks(ArrayList<Network> onlineNetworks) {
        DataBase.onlineNetworks = onlineNetworks;
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
