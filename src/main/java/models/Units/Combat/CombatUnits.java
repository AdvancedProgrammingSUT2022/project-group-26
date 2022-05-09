package models.Units.Combat;

import models.Player;
import models.Tile.Tile;
import models.Units.Unit;
import models.Units.UnitNameEnum;

public class CombatUnits extends Unit {
    protected float health = 20; // max health = 20  /  needed for combat types
    protected Integer combatStrength;
    protected boolean canAttack = false;

    protected int XP = 0;

    protected boolean isFortified = false;
    protected boolean isGarrison = false; // can be removed ?!

    public CombatUnits(Tile position, UnitNameEnum unitNameEnum, Player player) {
        super(player, position, unitNameEnum);
        setCombatStrength(unitNameEnum.getCombatStrength());
    }

    // todo in model??!
    public CombatUnits(CombatUnits combatUnits) {
        super(combatUnits);
    }


    public Integer getCombatStrength() {
        return combatStrength;
    }

    public void setCombatStrength(Integer combatStrength) {
        this.combatStrength = combatStrength;
    }

    public boolean isFullyHealed() {
        return getHealth() == 20;
    }

    public float getHealth() {
        return health;
    }

    public void setHealth(float health) {
        this.health = health;
    }

    public void heal() {
        setHealth(Math.min(20, getHealth() + 5));
    }

//    public float calculateAttack(String mode) {
//        switch (mode) {
//            case "ranged":
//                return (float) ((getRangedCombatStrength() * getPosition().getCombatBonus()) * (20F / (20 - getHealth())));
//            case "melee":
//                return (float) ((getCombatStrength() * getPosition().getCombatBonus()) * (20F / (20 - getHealth())));
//            default:
//                //error
//        }
//        return 0F;
//    }

    public void giveXp() {
        setXP(getXP() + 5);
    }

    public void upgrade() {
    }

    public int getXP() {
        return XP;
    }

    public void setXP(int XP) {
        this.XP = XP;
    }

    public boolean isFortified() {
        return isFortified;
    }

    public void setFortified(boolean fortified) {
        isFortified = fortified;
    }

    public boolean isGarrison() {
        return isGarrison;
    }

    public void setGarrison(boolean garrison) {
        isGarrison = garrison;
    }

    public void takeDamage(float defenderDamage) {
        setHealth(getHealth() - defenderDamage);
    }

    public void died() {
        // شبیهش  رو زده بودیم ؟؟×!
        if (isGarrison()) {
        }//remove from city.garrison
        getPlayer().getUnits().remove(this);
        getPosition().setCombatUnits(null);
    }

    // fix
    public float calculateAttack() {
        return 0;
    }

    public float calculateDefence() {
        return 0;
    }

    public boolean CanAttack() {
        return canAttack;
    }

    public void setCanAttack(boolean canAttack) {
        this.canAttack = canAttack;
    }
}