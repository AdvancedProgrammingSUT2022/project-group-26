package com.example.project.controllers.Network;

import com.example.project.controllers.ScoreBoardController;
import com.example.project.models.Network;
import com.example.project.models.Request;
import com.example.project.models.RequestEnum;

import java.io.IOException;

public class ScoreBoardHandler {
    private Network network;

    public ScoreBoardHandler(Network network) {
        this.network = network;
    }

    public void run() throws IOException {
        Request request;
        while (true) {
            request = network.readRequest();
            if (request.getAction() == RequestEnum.UPDATE_SCOREBOARD_DATA)
                network.sendResponse(ScoreBoardController.sendData());
            else if (request.getAction() == RequestEnum.BACK)
                System.out.println("back");
        }
    }
}