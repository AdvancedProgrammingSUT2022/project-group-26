package models.Units.Combat;

import models.Player;
import models.Tile.Tile;
import models.Units.Unit;
import models.Units.UnitNameEnum;

public class CombatUnits extends Unit {
    public CombatUnits(Tile position, UnitNameEnum unitNameEnum, Player player) {
        super(player,position, unitNameEnum);
    }

    public CombatUnits (CombatUnits combatUnits){
        super(combatUnits);
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