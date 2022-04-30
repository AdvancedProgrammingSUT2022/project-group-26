package controllers.GameControllers;

import controllers.Output;
import models.City;
import models.Player;
import models.Tile.Tile;
import models.Units.UnitTypeEnum;
import models.Units.Unit;

public class CombatController {

    public boolean isAttackPossible(Tile attacker, Tile defender) {
        Unit attackerUnit = attacker.getCombatUnits();
        if (attackerUnit == null) return false;
        if (attackerUnit.isASiege() && attackerUnit.isStillForATurn() == false) return false;

        // TODO : check the range ?!
        // TODO : check unit players !
        return true;
    }

    public Output attack(Tile attacker, Tile defender, Player player) {
        // TODO : in this function attacker should be able to attack without moving !
        // TODO : as;dlvl;sdk;lsdk;dsa vaaaaaaay
        if (attacker.getCombatUnits() == null) return Output.noCombatUnitHere;
        if (attacker.getCombatUnits().getPlayer() != player) return Output.youDontOwnThisUnit;
        if (defender.getNoneCombatUnits() == null && defender.getCombatUnits() == null) return Output.noUnitThere;
        if (attacker.getCombatUnits().isARangedCombatUnit() && defender.getCombatUnits() == null)
            return Output.CantCaptureWithRangedUnits;
        if (defender.getCombatUnits() == null) {
            // TODO : go to that tile and get it (movement for melee attacks?)
            return Output.attackSuccessFull;
        }
        if (attacker.getCombatUnits().isARangedCombatUnit()) {
            // TODO : Attack !
            return Output.attackSuccessFull;
        } else {
            // TODO : attack and if enemy is defeated --> get the tile !? (movement?)
            return Output.attackSuccessFull;
        }
    }

    public void meleeAttack(Tile attacker, Tile defender) {
    }

    public void rangedAttack(Tile attacker, Tile defender) {
    }

    public void meleeAttackToCity(Unit attacker, City defender) {
    }

    public void rangedAttackToCity(Unit attacker, City defender) {
    }

}