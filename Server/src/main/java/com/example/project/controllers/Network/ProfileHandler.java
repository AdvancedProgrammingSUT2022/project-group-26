package com.example.project.controllers.Network;

import com.example.project.controllers.ProfileMenuController;
import com.example.project.models.Network;
import com.example.project.models.Request;

import java.io.IOException;

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
        }
    }

}
