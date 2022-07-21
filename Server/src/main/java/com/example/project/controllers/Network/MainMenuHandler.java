package com.example.project.controllers.Network;

import com.example.project.controllers.MainMenuController;
import com.example.project.models.DataBase;
import com.example.project.models.Network;
import com.example.project.models.Request;

import java.io.IOException;

public class MainMenuHandler {
    private Network network;
    private MainMenuController mainMenuController;

    public MainMenuHandler(Network network) {
        this.network = network;
        mainMenuController = new MainMenuController(network.getLoggedInUser(), DataBase.getInstance().getUsersDatabase());
    }

    public void run() throws IOException {
        Request request;
        while (true) {
            request = network.readRequest();
        }
    }
}
