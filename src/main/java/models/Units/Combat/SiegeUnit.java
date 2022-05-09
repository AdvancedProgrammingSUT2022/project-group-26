package models.Units.Combat;

import models.Player;
import models.Tile.Tile;
import models.Units.UnitNameEnum;

public class SiegeUnit extends RangedUnit {
    protected boolean setUp = false;

    public SiegeUnit(Tile position, UnitNameEnum unitNameEnum, Player player) {
        super(position, unitNameEnum, player);
    }

    public SiegeUnit(CombatUnits combatUnits) {
        super(combatUnits);
    }

    public boolean isSetUp() {
        return setUp;
    }

    public void setSetUp(boolean setUp) {
        this.setUp = setUp;
    }
}
