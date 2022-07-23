package com.example.project.controllers.Network;

import com.example.project.controllers.GlobalChatController;
import com.example.project.models.GlobalChat.PrivateChat;
import com.example.project.models.GlobalChat.PublicChat;
import com.example.project.models.GlobalChat.Room;
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
        Request request;
        while (true) {
            request = network.readRequest();
            if (request.getAction() == RequestEnum.SEND_PUBLIC_MESSAGE)
                globalChatController.addPublicMessage(request, network.getLoggedInUser());
            else if (request.getAction() == RequestEnum.UPDATE_PUBLIC_CHAT) {
                globalChatController.updatePublicSeenMessages(network.getLoggedInUser());
                network.sendResponse(new Response(new GsonBuilder().create().toJson(PublicChat.getInstance())));
            }else if(request.getAction() == RequestEnum.EDIT_MESSAGE)
                globalChatController.editMessage(request);
        }
    }
}
