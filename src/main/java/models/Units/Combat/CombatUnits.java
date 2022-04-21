package models.Units.Combat;

import models.Player;
import models.Tile.Tile;
import models.Units.UnitNameEnum;
import models.Units.Units;

public class CombatUnits extends Units {
    public CombatUnits(Tile position, UnitNameEnum unitNameEnum, Player player) {
        super(position, unitNameEnum, player);
    }
    // TODO : check if we need subclasses
    // archery and siege --> ranged others --> not ranged


    // TODO : give values using unitName !
//    public CombatUnits(UnitNameEnum unitName){
//        setUnitName(unitName);
//
//
//    }

    public float calculateAttack() {
        return 0;
    }


}