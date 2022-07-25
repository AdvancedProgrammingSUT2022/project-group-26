package com.example.project.controllers.Network;

import com.example.project.controllers.GlobalChatController;
import com.example.project.models.GlobalChat.PublicChat;
import com.example.project.models.Network;
import com.example.project.models.Request;
import com.example.project.models.RequestEnum;
import com.example.project.models.Response;
import com.thoughtworks.xstream.XStream;

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
            if (request.getAction() == RequestEnum.SEND_MESSAGE)
                globalChatController.addMessage(request, network.getLoggedInUser());
            else if (request.getAction() == RequestEnum.UPDATE_PUBLIC_CHAT) {
                globalChatController.updatePublicSeenMessages(network.getLoggedInUser());
                network.sendResponse(new Response(new XStream().toXML(PublicChat.getInstance())));
            } else if (request.getAction() == RequestEnum.EDIT_MESSAGE)
                globalChatController.editMessage(request);
            else if (request.getAction() == RequestEnum.DELETE_FOR_ME)
                globalChatController.deleteForMe(request);
            else if (request.getAction() == RequestEnum.DELETE_FOR_EVERYONE)
                globalChatController.deleteForEveryone(request);
            else if (request.getAction() == RequestEnum.UPDATE_LOGGED_IN_USER_CHATS)
                network.sendResponse(new Response(new XStream().toXML(network.getLoggedInUser())));
            else if (request.getAction() == RequestEnum.GET_SUGGESTION_PRIVATE_CHATS)
                network.sendResponse(new Response(new XStream().toXML(globalChatController.
                        showUsernamesStartsWithString((String) request.getParams().get("string"), network))));
            else if (request.getAction() == RequestEnum.CREATE_PRIVATE_CHAT)
                globalChatController.createPrivateChat(network, request);
            else if (request.getAction() == RequestEnum.GO_TO_A_PRIVATE_CHAT)
                globalChatController.goToAPrivateChat(network, request);
            else if (request.getAction() == RequestEnum.BACK)
                return;
        }
    }
}