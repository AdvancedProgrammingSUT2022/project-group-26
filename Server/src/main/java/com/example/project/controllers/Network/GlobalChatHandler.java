package com.example.project.controllers.Network;

import com.example.project.controllers.GlobalChatController;
import com.example.project.models.GlobalChat.PublicChat;
import com.example.project.models.Network;
import com.example.project.models.Request;
import com.example.project.models.RequestEnum;
import com.example.project.models.Response;
import com.google.gson.GsonBuilder;

import java.io.IOException;

public class GlobalChatHandler {
    private Network network;
    private GlobalChatController globalChatController;

    public GlobalChatHandler(Network network) {
        this.network = network;
        globalChatController = new GlobalChatController();
    }

    public void run() throws IOException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        System.out.println("up");
                        Response response = new Response(new GsonBuilder().create().toJson(PublicChat.getInstance()));
                        network.sendResponse(response);
                        System.out.println("down");
                        Thread.sleep(500);
                    } catch (IOException | InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
        Request request;
        while (true) {
            request = network.readRequest();
            if (request.getAction() == RequestEnum.SEND_PUBLIC_MESSAGE)
                globalChatController.addPublicMessage(request, network.getLoggedInUser());
        }
    }
}
