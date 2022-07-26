package com.example.project.controllers.Network;

import com.example.project.controllers.MainMenuController;
import com.example.project.models.*;

import java.io.IOException;

public class GameHandler {
    private Network network;
    private MainMenuController mainMenuController;

    public GameHandler(Network network) {
        this.network = network;
        mainMenuController = new MainMenuController(network.getLoggedInUser(), DataBase.getInstance().getUsersDatabase());
    }

    public void run() throws IOException {
        Request request;
        while (true) {
            request = network.readRequest();
            if (request.getAction() == RequestEnum.GET_DATA)
                GameNetworkData.sendGame(network);
            else if (request.getAction() == RequestEnum.SEND_DATA)
                GameNetworkData.getGame(request, network);
        }
    }
}
