package controllers.GameControllers;

import models.City;
import models.Tile.Tile;
import models.Units.Units;

public class CombatController {
    
    public boolean isAttackPossible(Tile attacker, Tile defender) {
        return true;
    }

    public void attack(Tile attacker, Tile defender) {
    }

    public void meleeAttack(Units attacker, Units defender) {
    }

    public void rangedAttack(Units attacker, Units defender) {
    }
    public void meleeAttackToCity(Units attacker, City defender) {
    }

    public void rangedAttackToCity(Units attacker, City defender) {
    }

}