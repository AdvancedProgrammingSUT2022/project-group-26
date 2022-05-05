package controllers.GameControllers;

import controllers.Output;

import java.util.ArrayList;

import models.City;
import models.GameMap;
import models.Player;
import models.Tile.Tile;
import models.Units.Unit;
import models.Units.Combat.CombatUnits;
import models.Units.Nonecombat.NoneCombatUnits;

public class CombatController {
    MovementController movementController = new MovementController((GameMap) null);


    public void pillage(CombatUnits unit){
        unit.getPosition().getImprovement().setIsBroken(true);
    }

    public boolean isAttackPossible(Tile attacker, Tile defender) {
        Unit attackerUnit = attacker.getCombatUnits();
        if (attackerUnit == null) {
            return false;
        } else {
            return !attackerUnit.isASiege() || ((CombatUnits) attackerUnit).isStillForATurn();
        }
    }

    public Output attack(Tile attacker, Tile defender, Player player) {
        if (attacker.getCombatUnits() == null) {
            return Output.noCombatUnitHere;
        } else if (attacker.getCombatUnits().getPlayer() != player) {
            return Output.youDontOwnThisUnit;
        } else if (defender.getNoneCombatUnits() == null && defender.getCombatUnits() == null) {
            return Output.noUnitThere;
        }

        else if (attacker.getCombatUnits().isARangedCombatUnit() && defender.getCombatUnits() == null) {
            return Output.CantCaptureWithRangedUnits;
        } else if (defender.getCombatUnits() == null) {
            this.movementController.changePlaces(attacker, defender, attacker.getCombatUnits());
            this.captureDefender(defender.getNoneCombatUnits(), player);
            return Output.attackSuccessFull;
        } else if (attacker.getCombatUnits().isARangedCombatUnit()) {
            this.rangedAttack(attacker.getCombatUnits(), defender.getCombatUnits());
            return Output.attackSuccessFull;
        } else {
            this.meleeAttack(attacker.getCombatUnits(), defender.getCombatUnits());
            if (defender.getCombatUnits() == null) {
                this.movementController.changePlaces(attacker, defender, attacker.getCombatUnits());
            }
            return Output.attackSuccessFull;
        }
    }

    private void captureDefender(NoneCombatUnits captured, Player player) {
        captured.getPlayer().getUnits().remove(captured);
        captured.setPlayer(player);
        player.getUnits().add(captured);
        captured.setSavedRoute((ArrayList) null);
        // unit should reset whatever he is doing
    }

    public void meleeAttack(CombatUnits attacker, CombatUnits defender) {
        float attackerDamage = attacker.calculateAttack("melee");
        float defenderDamage = defender.calculateAttack("melee");
        defender.giveXp();
        attacker.giveXp();
        attacker.takeDamage(defenderDamage);
        defender.takeDamage(attackerDamage);
        if (attacker.getHealth() <= 0) attacker.died();
        if (defender.getHealth() <= 0) defender.died();
    }

    public void rangedAttack(CombatUnits attacker, CombatUnits defender) {
        float attackerDamage = attacker.calculateAttack("ranged");
        defender.giveXp();
        attacker.giveXp();
        defender.takeDamage(attackerDamage);
        if (defender.getHealth() <= 0) defender.died();
    }

    public void meleeAttackToCity(Unit attacker, City defender) {
    }

    public void rangedAttackToCity(Unit attacker, City defender) {
    }
}

