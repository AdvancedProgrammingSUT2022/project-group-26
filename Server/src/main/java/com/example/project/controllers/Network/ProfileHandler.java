package com.example.project.controllers.Network;

import com.example.project.controllers.ProfileMenuController;
import com.example.project.models.Network;
import com.example.project.models.Output;
import com.example.project.models.Request;
import com.example.project.models.RequestEnum;

import java.io.IOException;
import java.net.URL;

public class ProfileHandler {
    private Network network;
    private ProfileMenuController profileMenuController;

    public ProfileHandler(Network network) {
        this.network = network;
        profileMenuController = new ProfileMenuController(network.getLoggedInUser());
    }

    public void run() throws IOException {
        Request request;
        while (true){
            request = network.readRequest();
            if (request.getAction() == RequestEnum.CHANGE_NICKNAME)
                network.sendResponseWithOutput(profileMenuController.changeNickname(request));
            else if (request.getAction() == RequestEnum.CHANGE_PASSWORD)
                network.sendResponseWithOutput(profileMenuController.changePassword(request, network.getLoggedInUser().getPassword()));
            else if (request.getAction() == RequestEnum.CHANGE_PROFILE_PICTURE)
                network.getLoggedInUser().setAvatarURL((URL) request.getParams().get("url"));
        }
    }

}
