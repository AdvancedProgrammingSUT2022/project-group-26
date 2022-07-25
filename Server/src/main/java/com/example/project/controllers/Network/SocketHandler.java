package com.example.project.controllers.Network;

import com.example.project.models.Network;
import com.example.project.models.Request;

import java.io.IOException;
import java.net.Socket;

public class SocketHandler extends Thread {
    private Network network;

    public SocketHandler(Socket socket) throws IOException {
        network = new Network(socket);
    }

    @Override
    public void run() {
        new LoginMenuHandler(network).run();
    }
}