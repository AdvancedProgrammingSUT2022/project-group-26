package models.Units.Nonecombat;

import models.Player;
import models.Tile.Tile;
import models.Units.UnitNameEnum;
import models.Units.Units;

public class NoneCombatUnits extends Units{

    public NoneCombatUnits(Tile position, UnitNameEnum unitNameEnum, Player player) {
        super(position, unitNameEnum, player);
    }
}