package com.example.project.controllers.Network;

import com.example.project.controllers.LoginMenuController;
import com.example.project.models.Network;
import com.example.project.models.Output;
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
            if (request.getAction() == RequestEnum.REGISTER_USER)
                network.sendResponse(loginMenuController.register(request));
            else if (request.getAction() == RequestEnum.LOGIN_USER)
                login(request);

        }
    }

    private void login(Request request) throws IOException {
        Output output = loginMenuController.login(request);
        network.sendResponse(output);
        if (output == Output.LOGGED_IN)
            new MainMenuHandler(network);
    }
}
