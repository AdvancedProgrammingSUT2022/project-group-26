package com.example.project.controllers.GameControllers;

import com.example.project.models.DataBase;
import com.example.project.models.Game;
import com.example.project.views.ShowPanelFXController;

public class PanelUpdater implements Runnable {
    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            // todo fill
//            ShowPanelFXController.getInstance().getGoldAmount().setText();
//            ShowPanelFXController.getInstance().getGoldAmount().setText();
//            ShowPanelFXController.getInstance().getGoldAmount().setText();
        }
    }
}
