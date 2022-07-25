package com.example.project.controllers.Network;

import com.example.project.controllers.MainMenuController;
import com.example.project.models.DataBase;
import com.example.project.models.Network;
import com.example.project.models.Request;
import com.example.project.models.RequestEnum;

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
            if (request.getAction() == RequestEnum.GO_TO_PROFILE_MENU)
                new ProfileHandler(network).run();
            else if(request.getAction() == RequestEnum.GO_TO_PLAY_GAME_SETTINGS)
                new GameSettingsHandler(network, false, null).run();
            else if (request.getAction() == RequestEnum.INVITATION_ACCEPTED)
                new GameSettingsHandler(network, true, (String) request.getParams().get("username")).run();
            else if (request.getAction() == RequestEnum.GO_TO_GLOBAL_CHAT)
                new GlobalChatHandler(network).run();
            else if (request.getAction() == RequestEnum.GO_TO_SCORE_BOARD)
                new ScoreBoardHandler(network).run();
            else if (request.getAction() == RequestEnum.BACK)
                return;
        }
    }
}
