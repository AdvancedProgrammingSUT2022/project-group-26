package com.example.project.controllers.GameControllers;

import com.example.project.controllers.Output;

import java.util.ArrayList;

import com.example.project.models.City;
import com.example.project.models.GameMap;
import com.example.project.models.Gold;
import com.example.project.models.Player;
import com.example.project.models.Tile.Tile;
import com.example.project.models.Units.Combat.MeleeUnit;
import com.example.project.models.Units.Combat.RangedUnit;
import com.example.project.models.Units.Combat.SiegeUnit;
import com.example.project.models.Units.Unit;
import com.example.project.models.Units.Combat.CombatUnits;
import com.example.project.models.Units.Nonecombat.NoneCombatUnits;
import com.example.project.models.Units.UnitNameEnum;
import com.example.project.models.Units.UnitTypeEnum;

public class CombatController {
    MovementController movementController;

    public CombatController(GameMap gameMap) {
        this.movementController = new MovementController(gameMap);
    }

    public Output pillage(CombatUnits unit) {
        if (!unit.CanAttack()) return Output.ONE_ATTACK_PER_TURN;
        unit.setCanAttack(false);
        unit.getPosition().getImprovement().setIsBroken(true);
        return Output.COMMAND_SUCCESSFUL;
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
        } else if (!attacker.getCombatUnits().CanAttack()) {
            return Output.ONE_ATTACK_PER_TURN;
        } else if (!isAttackPossible(attacker, defender)) {
            return Output.OUT_OF_RANGE;
        } else if (attacker.getCombatUnits().isARangedCombatUnit() && defender.getCombatUnits() == null) {
            return Output.CantCaptureWithRangedUnits;
        } else if (defender.getCombatUnits() == null) {
            this.movementController.changePlaces(attacker, defender, attacker.getCombatUnits());
            this.captureDefender(defender.getNoneCombatUnits(), player);
            attacker.getCombatUnits().setCanAttack(false);
            return Output.attackSuccessFull;
        } else if (attacker.getCombatUnits().isARangedCombatUnit()) {
            this.rangedAttack(attacker.getCombatUnits(), defender.getCombatUnits());
            attacker.getCombatUnits().setCanAttack(false);
            return Output.attackSuccessFull;
        } else {
            this.meleeAttack(attacker.getCombatUnits(), defender.getCombatUnits());
            if (defender.getCombatUnits() == null) {
                this.movementController.changePlaces(attacker, defender, attacker.getCombatUnits());
            }
            attacker.getCombatUnits().setCanAttack(false);
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
        } else if (!attacker.getCombatUnits().CanAttack()) {
            return Output.ONE_ATTACK_PER_TURN;
        } else if (!isAttackPossible(attacker, defender.getCenter())) {
            return Output.OUT_OF_RANGE;
        } else if (attacker.getCombatUnits().isARangedCombatUnit()) {
            rangedAttackToCity(attacker.getCombatUnits(), defender);
        } else if (attacker.getCombatUnits().isAMeleeCombatUnit()) {
            meleeAttackToCity(attacker.getCombatUnits(), defender, players);
        }
        attacker.getCombatUnits().setCanAttack(false);
        return Output.attackSuccessFull;
    }

    public Output attackFromCity(City attacker, Tile defender, Player player) {
        if (!player.getCities().contains(attacker)) {
            return Output.CITY_NOT_YOURS;
        } else if (defender.getCombatUnits() == null) {
            return Output.NO_UNIT_TO_ATTACK;
        } else if (defender.getCombatUnits().getPlayer() == player) {
            return Output.CANT_ATTACK_YOURSELF;
        } else if (!attacker.getCenter().getCombatUnits().CanAttack()) {
            return Output.ONE_ATTACK_PER_TURN;
        } else if (!movementController.checkRange(attacker.getCenter(), defender, 3)) {
            return Output.OUT_OF_RANGE;
        }
        attacker.getGarrison().setCanAttack(false);
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
        float attackerDamage = attacker.calculateAttack() * specialUnitBonuses(attacker, defender);
        float defenderDamage = defender.calculateDefence() * specialUnitBonuses(defender, attacker);
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
        float attackerDamage = attacker.calculateAttack() * specialUnitBonuses(attacker, defender);
        float defenderDamage = defender.calculateDefence();
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
        float attackerDamage = attacker.calculateAttack() * specialUnitBonuses(attacker, defender);
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

    public float specialUnitBonuses(CombatUnits attacker, CombatUnits defender) {
        float bonus = 1;
        if ((attacker.getUnitNameEnum() == UnitNameEnum.SPEARMAN
                || attacker.getUnitNameEnum() == UnitNameEnum.PIKE_MAN)
                && defender.getUnitTypeEnum() == UnitTypeEnum.MOUNTED) bonus += 1;
        if (attacker.getUnitNameEnum() == UnitNameEnum.ANTITANK_GUN
                && defender.getUnitNameEnum() == UnitNameEnum.TANK) bonus += 0.1;
        return bonus;
    }

    public float specialUnitBonuses(CombatUnits attacker, City defender) {
        float bonus = 1;
        if (attacker.getUnitNameEnum() == UnitNameEnum.CATAPULT
                || attacker.getUnitNameEnum() == UnitNameEnum.TREBUCHET
                || attacker.getUnitNameEnum() == UnitNameEnum.CANON
                || attacker.getUnitNameEnum() == UnitNameEnum.ARTILLERY
        ) bonus += 0.1;
        if (attacker.getUnitNameEnum() == UnitNameEnum.TANK) bonus -= 0.1;
        return bonus;
    }
}

