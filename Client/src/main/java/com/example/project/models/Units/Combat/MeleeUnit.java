package com.example.project.models.Units.Combat;

import com.example.project.models.Player;
import com.example.project.models.Tile.Tile;
import com.example.project.models.Units.UnitNameEnum;

public class MeleeUnit extends CombatUnit {
    public MeleeUnit(Tile position, UnitNameEnum unitNameEnum, Player player) {
        super(position, unitNameEnum, player);
    }
}
