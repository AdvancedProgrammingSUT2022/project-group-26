package com.example.project.controllers.Network;

import com.example.project.controllers.LoginMenuController;
import com.example.project.models.*;
import com.google.gson.Gson;

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
                network.sendResponseWithOutput(loginMenuController.register(request));
            else if (request.getAction() == RequestEnum.LOGIN_USER)
                login(request);
            else if (request.getAction() == RequestEnum.BACK)
                return;
        }
    }

    private void login(Request request) throws IOException {
        Output output = loginMenuController.login(request, network);
        if (output != Output.LOGGED_IN)
            network.sendResponseWithOutput(output);
        else {
            Gson gson = new Gson();
            Response response = new Response(output);
            response.setData(gson.toJson(network.getLoggedInUser()));
            network.sendResponse(response);
            new MainMenuHandler(network).run();
        }
    }
}