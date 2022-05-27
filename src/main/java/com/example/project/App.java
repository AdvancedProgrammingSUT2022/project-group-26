package com.example.project;

import com.example.project.models.UsersDatabase;
import com.example.project.views.LoginMenu;
import com.example.project.views.MenuChanger;

public class App {
    public static void main(String[] args) {
//        UsersDatabase usersDatabase = SaveData.loadAndReturnUserDataBase();
        UsersDatabase usersDatabase = new UsersDatabase();
        MenuChanger.main(args);
//        loginMenu.run();
//        SaveData.saveUserDataBase(usersDatabase);
    }
}