package com.example.project.models.Units.Nonecombat;

import com.example.project.models.Player;
import com.example.project.models.Tile.Tile;
import com.example.project.models.Units.UnitNameEnum;

public class BuilderUnit extends NoneCombatUnit {
    private boolean isWorking = false;
    private String work = null;
    private int turn = 0;

    public BuilderUnit(Tile position, UnitNameEnum unitNameEnum, Player player) {
        super(position, unitNameEnum, player);
    }

    public boolean getIsWorking() {
        return isWorking;
    }

    public void setIsWorking(boolean working) {
        isWorking = working;
    }

    public int getTurn() {
        return turn;
    }

    public void setTurn(int turn) {
        this.turn = turn;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public String build() {
        if (!getIsWorking()) return null;
        setTurn(getTurn() + 1);
        // ** can be changed **
        if (getTurn() == 2) {
            setIsWorking(false);
            setTurn(0);
            return getWork();
        }
        return null;
    }
}
