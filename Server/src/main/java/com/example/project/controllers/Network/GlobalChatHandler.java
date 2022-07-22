package com.example.project.controllers.Network;

import com.example.project.controllers.GlobalChatController;
import com.example.project.models.Network;
import com.example.project.models.Request;

import java.io.IOException;

public class GlobalChatHandler {
    private Network network;
    private GlobalChatController globalChatController;

    public GlobalChatHandler(Network network) {
        this.network = network;
        globalChatController = new GlobalChatController();
    }

    public void run() throws IOException {
        Request request;
        while (true) {
            request = network.readRequest();
        }
    }
}
