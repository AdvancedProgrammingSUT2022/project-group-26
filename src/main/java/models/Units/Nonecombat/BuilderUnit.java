package models.Units.Nonecombat;

import models.Player;
import models.Tile.Tile;
import models.Units.UnitNameEnum;

public class BuilderUnit extends NoneCombatUnits {
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
        if (getTurn() == 2) /// ** can be changed **
        {
            setIsWorking(false);
            setTurn(0);
            return getWork();
        }
        return null;
    }
}
