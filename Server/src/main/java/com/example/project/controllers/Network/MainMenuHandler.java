package com.example.project.controllers.Network;

import com.example.project.controllers.MainMenuController;
import com.example.project.models.*;

import java.io.IOException;

public class MainMenuHandler {
    private Network network;
    private MainMenuController mainMenuController;

    public MainMenuHandler(Network network) {
        this.network = network;
        mainMenuController = new MainMenuController(network.getLoggedInUser(), DataBase.getInstance().getUsersDatabase());
    }

    public void run() throws IOException {
        Request request;
        while (true) {
            request = network.readRequest();
            if (request.getAction() == RequestEnum.GO_TO_PROFILE_MENU) {
                network.sendResponse(new Response(Output.STOP_THREAD));
                new ProfileHandler(network).run();
            } else if (request.getAction() == RequestEnum.GO_TO_PLAY_GAME_SETTINGS) {
                network.sendResponse(new Response(Output.STOP_THREAD));
                new GameSettingsHandler(network).run();
            } else if (request.getAction() == RequestEnum.INVITATION_ACCEPTED) {
                if (!Game.getNetworksInGame().contains(network))
                    Game.getNetworksInGame().add(network);
            } else if (request.getAction() == RequestEnum.GO_TO_GLOBAL_CHAT) {
                network.sendResponse(new Response(Output.STOP_THREAD));
                new GlobalChatHandler(network).run();
            } else if (request.getAction() == RequestEnum.GO_TO_SCORE_BOARD) {
                network.sendResponse(new Response(Output.STOP_THREAD));
                new ScoreBoardHandler(network).run();
            } else if (request.getAction() == RequestEnum.BACK) {
                network.sendResponse(new Response(Output.STOP_THREAD));
                network.setOnMainMenu(false);
                network.getLoggedInUser().setOnline(false);
                return;
            }
        }
    }
}
