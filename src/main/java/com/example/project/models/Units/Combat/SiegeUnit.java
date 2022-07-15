package com.example.project.models.Units.Combat;

import com.example.project.models.Player;
import com.example.project.models.Tile.Tile;
import com.example.project.models.Units.UnitNameEnum;

public class SiegeUnit extends RangedUnit {
    protected boolean setUp = false;

    public SiegeUnit(Tile position, UnitNameEnum unitNameEnum, Player player) {
        super(position, unitNameEnum, player);
    }

    public boolean isSetUp() {
        return setUp;
    }

    public void setSetUp(boolean setUp) {
        this.setUp = setUp;
    }
}