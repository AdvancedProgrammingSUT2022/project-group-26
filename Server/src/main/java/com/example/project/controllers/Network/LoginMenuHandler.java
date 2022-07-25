package com.example.project.controllers.Network;

import com.example.project.controllers.LoginMenuController;
import com.example.project.models.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

public class LoginMenuHandler {
    private Network network;
    private LoginMenuController loginMenuController = new LoginMenuController();

    public LoginMenuHandler(Network network) {
        this.network = network;
    }

    public void run()  {
        Request request;
        while (true) {
            try {
                request = network.readRequest();
                if (request.getAction() == RequestEnum.REGISTER_USER)
                    network.sendResponseWithOutput(loginMenuController.register(request));
                else if (request.getAction() == RequestEnum.LOGIN_USER)
                    login(request);
                else if (request.getAction() == RequestEnum.BACK)
                    return;
            } catch (IOException e) {
                return;
            }
        }
    }

    private void login(Request request) throws IOException {
        Output output = loginMenuController.login(request, network);
        if (output != Output.LOGGED_IN)
            network.sendResponseWithOutput(output);
        else {
            Response response = new Response(output);
            response.setData(new GsonBuilder().create().toJson(network.getLoggedInUser()));
            network.sendResponse(response);
            new MainMenuHandler(network).run();
        }
    }
}