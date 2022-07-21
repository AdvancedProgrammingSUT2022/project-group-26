package com.example.project;

import com.example.project.controllers.Network.SocketHandler;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class App {
    public static int SERVER_PORT = 8080;

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(SERVER_PORT);
        while (true) {
            Socket socket = serverSocket.accept();
            SocketHandler handler = new SocketHandler(socket);
            handler.start();
        }
    }
}