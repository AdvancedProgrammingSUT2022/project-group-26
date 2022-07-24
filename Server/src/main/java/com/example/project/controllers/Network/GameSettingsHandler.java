package com.example.project.controllers.Network;

import com.example.project.controllers.GameControllers.GameSettingController;
import com.example.project.models.*;
import com.google.gson.Gson;

import java.io.IOException;

public class GameSettingsHandler {
    private Network network;
    private GameSettingController gameSettingController;

    public GameSettingsHandler(Network network) {
        this.network = network;
        this.gameSettingController = new GameSettingController(network);
    }

    public void run() throws IOException {
        Request request;
        while (true) {
            request = network.readRequest();
            if (request.getAction() == RequestEnum.CLEAR_PLAYERS_IN_GAME)
                gameSettingController.clearPlayers(network);
            else if (request.getAction() == RequestEnum.BACK)
                return;
            else if (request.getAction() == RequestEnum.UPDATE_SEARCHED_PLAYERS) {
                Response response = new Response(new Gson().toJson(gameSettingController.showUsernamesStartsWithString((String) request.getParams().get("searched"), network)));
                network.sendResponse(response);
            } else if (request.getAction() == RequestEnum.UPDATE_IN_GAME_PLAYERS) {
                Response response = new Response(new Gson().toJson(gameSettingController.getUsers()));
                network.sendResponse(response);
            } else if (request.getAction() == RequestEnum.ADD_PLAYER)
                network.sendResponseWithOutput(gameSettingController.addPlayerStatues((String) request.getParams().get("user")));
            else if (request.getAction() == RequestEnum.SET_NUMBER_OF_PLAYERS)
                gameSettingController.setNumberOfPlayers((int) (double) request.getParams().get("number"));
            else if (request.getAction() == RequestEnum.SEND_INVITATION_REQUEST) {
                gameSettingController.sendInvitationRequest((String) request.getParams().get("username"), network);
            }
        }
    }
}
