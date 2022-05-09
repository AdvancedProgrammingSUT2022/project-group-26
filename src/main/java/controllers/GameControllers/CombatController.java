package controllers.GameControllers;

import controllers.Output;

import java.util.ArrayList;

import models.City;
import models.GameMap;
import models.Gold;
import models.Player;
import models.Tile.Tile;
import models.Units.Combat.MeleeUnit;
import models.Units.Combat.RangedUnit;
import models.Units.Combat.SiegeUnit;
import models.Units.Unit;
import models.Units.Combat.CombatUnits;
import models.Units.Nonecombat.NoneCombatUnits;

public class CombatController {
    MovementController movementController;

    public CombatController(GameMap gameMap) {
        this.movementController = new MovementController(gameMap);
    }

    public void pillage(CombatUnits unit) {
        unit.getPosition().getImprovement().setIsBroken(true);
    }

    public boolean isAttackPossible(Tile attacker, Tile defender) {
        Unit attackerUnit = attacker.getCombatUnits();
        if (attackerUnit == null) {
            return false;
        } else {
            if (attackerUnit instanceof MeleeUnit
                    && !movementController.checkRange(attacker, defender, 1))
                return false;
            if (attackerUnit instanceof RangedUnit
                    && !movementController.checkRange(attacker, defender, ((RangedUnit) attackerUnit).getRange()))
                return false;
            return !(attackerUnit instanceof SiegeUnit) || ((SiegeUnit) attackerUnit).isSetUp();
        }
    }

    public Output attackUnits(Tile attacker, Tile defender, Player player) {
        if (attacker.getCombatUnits() == null) {
            return Output.noCombatUnitHere;
        } else if (attacker.getCombatUnits().getPlayer() != player) {
            return Output.youDontOwnThisUnit;
        } else if (defender.getNoneCombatUnits() == null && defender.getCombatUnits() == null) {
            return Output.noUnitThere;
        } else if ((defender.getCombatUnits() != null
                && defender.getCombatUnits().getPlayer() == player)
                || (defender.getNoneCombatUnits() != null
                && defender.getNoneCombatUnits().getPlayer() == player)) {
            return Output.CANT_ATTACK_YOURSELF;
        } else if (attacker.getCombatUnits().isARangedCombatUnit() && defender.getCombatUnits() == null) {
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

    public Output attackToCity(Tile attacker, City defender, Player player, ArrayList<Player> players) {
        if (attacker.getCombatUnits() == null) {
            return Output.noCombatUnitHere;
        } else if (attacker.getCombatUnits().getPlayer() != player) {
            return Output.youDontOwnThisUnit;
        } else if (player.getCities().contains(defender)) {
            return Output.CANT_ATTACK_YOURSELF;
        } else if (attacker.getCombatUnits().isARangedCombatUnit()) {
            rangedAttackToCity(attacker.getCombatUnits(), defender);
        } else if (attacker.getCombatUnits().isAMeleeCombatUnit()) {
            meleeAttackToCity(attacker.getCombatUnits(), defender, players);
        }
        return Output.attackSuccessFull;
    }


    public Output attackFromCity(City attacker, Tile defender, Player player) {
        if (!player.getCities().contains(attacker)) {
            return Output.CITY_NOT_YOURS;
        } else if (defender.getCombatUnits() == null) {
            return Output.NO_UNIT_TO_ATTACK;
        } else if (defender.getCombatUnits().getPlayer() == player) {
            return Output.CANT_ATTACK_YOURSELF;
        }
        cityAttack(attacker, defender.getCombatUnits());
        return Output.attackSuccessFull;
    }

    private void captureDefender(NoneCombatUnits captured, Player player) {
        captured.getPlayer().getUnits().remove(captured);
        captured.setPlayer(player);
        player.getUnits().add(captured);
        captured.setSavedRoute((ArrayList) null);
        // unit should reset whatever he is doing
    }

    public void meleeAttack(CombatUnits attacker, CombatUnits defender) {
        float attackerDamage = attacker.calculateAttack();
        float defenderDamage = defender.calculateDefence();
        defender.giveXp();
        attacker.giveXp();
        attacker.takeDamage(defenderDamage);
        defender.takeDamage(attackerDamage);
        if (attacker.getHealth() <= 0) attacker.died();
        if (defender.getHealth() <= 0) defender.died();
    }

    public void rangedAttack(CombatUnits attacker, CombatUnits defender) {
        float attackerDamage = attacker.calculateAttack();
        defender.giveXp();
        attacker.giveXp();
        defender.takeDamage(attackerDamage);
        if (defender.getHealth() <= 0) defender.died();
    }

    public void meleeAttackToCity(CombatUnits attacker, City defender, ArrayList<Player> players) {
        float attackerDamage = attacker.calculateAttack();
        float defenderDamage = defender.calculateAttack();
        attacker.giveXp();
        attacker.takeDamage(defenderDamage);
        defender.takeDamage(attackerDamage);
        if (attacker.getHealth() <= 0) attacker.died();
        else if (defender.getHealth() <= 0) {
            Player loser = SearchController.findPlayerOfCity(players, defender);
            int goldLost = Gold.getPlayerGold(loser) / 5;
            Gold.removeGold(loser, goldLost);
            Gold.addGold(attacker.getPlayer(), goldLost);
            attacker.getPlayer().attachCity(defender, loser);
        }
    }

    public void rangedAttackToCity(CombatUnits attacker, City defender) {
        float attackerDamage = attacker.calculateAttack();
        attacker.giveXp();
        defender.takeDamage(attackerDamage);
        if (defender.getHealth() <= 0) defender.setHealth(1f);
    }

    public void cityAttack(City attacker, CombatUnits defender) {
        float attackerDamage = attacker.calculateAttack();
        defender.giveXp();
        defender.takeDamage(attackerDamage);
        if (defender.getHealth() <= 0) defender.died();
    }
}

