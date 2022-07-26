package com.example.project.controllers.Network;

import com.example.project.controllers.MainMenuController;
import com.example.project.models.*;

import java.io.IOException;

public class GameHandler {
    private Network network;

    public GameHandler(Network network) {
        this.network = network;
    }

    public void run() throws IOException {
        Request request;
        while (true) {
            request = network.readRequest();
            if (request.getAction() == RequestEnum.GET_DATA)
                GameNetworkData.sendGame(network);
            else if (request.getAction() == RequestEnum.SEND_DATA) {
                GameNetworkData.getGame(request, network);
                GameNetworkData.sendGameToOtherPlayers(network);
            } else {
                System.out.println("else in game");
                return;
            }
        }
    }
}