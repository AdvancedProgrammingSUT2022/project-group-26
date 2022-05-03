package models.Units.Nonecombat;

import models.Player;
import models.Tile.Tile;
import models.Units.UnitNameEnum;

public class Builder extends NoneCombatUnits{

    public Builder(Tile position, UnitNameEnum unitNameEnum, Player player) {
        super(position, unitNameEnum, player);
    }
}
