package com.example.project.controllers.Network;

import com.example.project.controllers.LoginMenuController;
import com.example.project.models.Network;
import com.example.project.models.Request;
import com.example.project.models.RequestEnum;

import java.io.IOException;

public class LoginMenuHandler {
    private Network network;
    private LoginMenuController loginMenuController = new LoginMenuController();

    public LoginMenuHandler(Network network) {
        this.network = network;
    }

    public void run() throws IOException {
        Request request;
        while (true) {
            request = network.readRequest();
            if (request.getAction() == RequestEnum.REQUEST_USER)
                network.sendResponse(loginMenuController.register(request));

        }
    }
}
