package models.Units.Combat;

import models.Player;
import models.Tile.Tile;
import models.Units.UnitNameEnum;

public class MeleeUnit extends CombatUnits{
    public MeleeUnit(Tile position, UnitNameEnum unitNameEnum, Player player) {
        super(position, unitNameEnum, player);
    }

    public MeleeUnit(CombatUnits combatUnits) {
        super(combatUnits);
    }
}
