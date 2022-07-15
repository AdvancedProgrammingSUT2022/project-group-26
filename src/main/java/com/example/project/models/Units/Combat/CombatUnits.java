package com.example.project.models.Units.Combat;

import com.example.project.models.Player;
import com.example.project.models.Tile.Tile;
import com.example.project.models.Units.Unit;
import com.example.project.models.Units.UnitNameEnum;

public class CombatUnits extends Unit {
    protected float health = 100; // max health = 20  /  needed for combat types
    protected Integer combatStrength;
    protected boolean canAttack = false;

    protected int XP = 0;

    protected boolean isFortified = false;
    protected boolean isGarrison = false; // can be removed ?!

    public CombatUnits(Tile position, UnitNameEnum unitNameEnum, Player player) {
        super(player, position, unitNameEnum);
        setCombatStrength(unitNameEnum.getCombatStrength());
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

//    public void upgrade() {
//    }

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
        if (isGarrison()) {
            //remove from city.garrison
        }
        getPlayer().getUnits().remove(this);
        getPosition().setCombatUnits(null);
    }

    // fix -- should i make it int?!
    public float calculateAttack() {
        float bonus = 1;
        bonus += getPosition().getCombatBonus();

        if (this instanceof RangedUnit || this instanceof SiegeUnit)
            return getUnitNameEnum().getRangedCombatStrength() * ((getHealth() / 200) + 1 / 2) * bonus; // 200 == 2 * max health
        return getUnitNameEnum().getCombatStrength() * ((getHealth() / 200) + 1 / 2) * bonus;
    }

    public float calculateDefence() {
        float bonus = 1;
        bonus += getPosition().getCombatBonus();
        if ((isAlert || isSleeping || isFortified)
                && (!(getUnitNameEnum() == UnitNameEnum.CHARIOT_ARCHER
                || getUnitNameEnum() == UnitNameEnum.CATAPULT
                || getUnitNameEnum() == UnitNameEnum.HORSEMAN
                || getUnitNameEnum() == UnitNameEnum.KNIGHT
                || getUnitNameEnum() == UnitNameEnum.TREBUCHET
                || getUnitNameEnum() == UnitNameEnum.CANON
                || getUnitNameEnum() == UnitNameEnum.CAVALRY
                || getUnitNameEnum() == UnitNameEnum.LANCER
                || getUnitNameEnum() == UnitNameEnum.ARTILLERY
                || getUnitNameEnum() == UnitNameEnum.PANZER
                || getUnitNameEnum() == UnitNameEnum.TANK))) bonus += 0.4;
        //  ctrl c v
        if (this instanceof RangedUnit || this instanceof SiegeUnit)
            return getUnitNameEnum().getRangedCombatStrength() * ((getHealth() / 200) + 1 / 2) * bonus; // 200 == 2 * max health
        return getUnitNameEnum().getCombatStrength() * ((getHealth() / 200) + 1 / 2) * bonus;
    }

    public boolean CanAttack() { return canAttack; }

    public void setCanAttack(boolean canAttack) {
        this.canAttack = canAttack;
    }

    public String getActionToString() {
        if (isGarrison())
            return "garrisoned!";
        if (isFortified())
            return "fortified!";
        if (isAlert())
            return "alerted!";
        if (isSleeping())
            return "sleeping!";
        return "no action!";
    }
}