package com.example.project.models.Units.Nonecombat;

import com.example.project.models.Player;
import com.example.project.models.Tile.Tile;
import com.example.project.models.Units.Unit;
import com.example.project.models.Units.UnitNameEnum;

public class NoneCombatUnits extends Unit {

    public NoneCombatUnits(Tile position, UnitNameEnum unitNameEnum, Player player) {
        super(player,position, unitNameEnum);
    }
}