package models.Units.Nonecombat;

import models.Player;
import models.Tile.Tile;
import models.Units.Unit;
import models.Units.UnitNameEnum;

public class NoneCombatUnits extends Unit {

    public NoneCombatUnits(Tile position, UnitNameEnum unitNameEnum, Player player) {
        super(player,position, unitNameEnum);
    }
}