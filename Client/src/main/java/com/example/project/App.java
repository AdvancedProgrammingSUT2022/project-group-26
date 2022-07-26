package com.example.project;

import com.example.project.controllers.NetworkController;
import com.example.project.views.MenuChanger;

import java.io.IOException;
import java.net.Socket;

public class App {
    public static int SERVER_PORT = 8080;

    public static void main(String[] args) {
        if (NetworkController.connect()) {
            MenuChanger.main(args);
        } else System.out.println("try later please");
    }

}