package controllers.GameControllers;

import controllers.Output;
import models.City;
import models.GameMap;
import models.Player;
import models.Tile.Tile;
import models.Units.Combat.CombatUnits;
import models.Units.Nonecombat.NoneCombatUnits;
import models.Units.UnitTypeEnum;
import models.Units.Unit;

public class CombatController {
    // TODO : fix
    MovementController movementController = new MovementController(null);

    public boolean isAttackPossible(Tile attacker, Tile defender) {
        Unit attackerUnit = attacker.getCombatUnits();
        if (attackerUnit == null) return false;
        if (attackerUnit.isASiege() && attackerUnit.isStillForATurn() == false) return false;

        // TODO : check the range ?!
        // TODO : check unit players !
        return true;
    }

    public Output attack(Tile attacker, Tile defender, Player player) {
        // TODO : function to check the range
        if (attacker.getCombatUnits() == null) return Output.noCombatUnitHere;
        if (attacker.getCombatUnits().getPlayer() != player) return Output.youDontOwnThisUnit;
        if (defender.getNoneCombatUnits() == null && defender.getCombatUnits() == null) return Output.noUnitThere;
        if (attacker.getCombatUnits().isARangedCombatUnit() && defender.getCombatUnits() == null)
            return Output.CantCaptureWithRangedUnits;
        if (defender.getCombatUnits() == null) {
            movementController.changePlaces(attacker, defender, attacker.getCombatUnits());
            captureDefender(defender.getNoneCombatUnits(), player);
            return Output.attackSuccessFull;
        }
        if (attacker.getCombatUnits().isARangedCombatUnit()) {
            rangedAttack(attacker.getCombatUnits(),defender.getCombatUnits());
            return Output.attackSuccessFull;
        } else {
            meleeAttack(attacker.getCombatUnits(),defender.getCombatUnits());
            // if defender is dead
            if (defender.getCombatUnits() == null) movementController.changePlaces(attacker,defender,attacker.getCombatUnits());
            return Output.attackSuccessFull;
        }
    }

    private void captureDefender(NoneCombatUnits captured, Player player) {
        captured.getPlayer().getUnits().remove(captured);
        captured.setPlayer(player);
        player.getUnits().add(captured);
        captured.setSavedRoute(null);
    }

    public void meleeAttack(CombatUnits attacker, CombatUnits defender) {
        float attackerDamage = attacker.getRangedCombatStrength();  // * (1/2 + (1/2)*(getHealth / max health)) kolesh *(attacker.getPosition().getCombatBonus() + attacker.player.getCombatBoost) ;
        float defenderDamage = defender.getCombatStrength(); // like above
        // kam kon az ham
    }

    public void rangedAttack(CombatUnits attacker, CombatUnits defender) {
        float attackerDamage = attacker.getRangedCombatStrength();  // * (1/2 + (1/2)*(getHealth / max health)) kolesh *(attacker.getPosition().getCombatBonus() + attacker.player.getCombatBoost) ;
        float defenderDamage = defender.getCombatStrength(); // like above
        // kam kon az ham
    }

    public void meleeAttackToCity(Unit attacker, City defender) {
    }

    public void rangedAttackToCity(Unit attacker, City defender) {
    }

}