package com.example.project.controllers;

import com.example.project.App;
import com.example.project.models.Network;

import java.io.IOException;
import java.net.Socket;

public class NetworkController {
    private static NetworkController instance;

    private NetworkController() {
    }

    public static NetworkController getInstance() {
        if (instance == null) instance = new NetworkController();
        return instance;
    }

    public static boolean connect() {
        try {
            Socket socket = new Socket("localhost", App.SERVER_PORT);
            Network.getInstance().setUp(socket);
        } catch (IOException ignored) {
            return false;
        }
        return true;
    }

    public String getResponse() throws IOException {
        return Network.getInstance().getInputStream().readUTF();
    }
}