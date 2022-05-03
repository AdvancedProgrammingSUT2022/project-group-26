package models.Units.Combat;

import models.Player;
import models.Tile.Tile;
import models.Units.Unit;
import models.Units.UnitNameEnum;

public class CombatUnits extends Unit {
    // do we need a settler class ??!
    // combat unit <-- (inherit) siege ranged melee ?!?!


    protected float health = 20; // max health = 20  /  needed for combat types
    protected Integer combatStrength;
    protected Integer range; // for melee units --> null
    protected Integer rangedCombatStrength; // for melee units --> null

    protected boolean stillForATurn; // for siege
    protected boolean isSleeping;
    protected boolean isAlert;

    public CombatUnits(Tile position, UnitNameEnum unitNameEnum, Player player) {
        super(player, position, unitNameEnum);
        setCombatStrength(unitNameEnum.getCombatStrength());
        setRange(unitNameEnum.getRange());
        setRangedCombatStrength(unitNameEnum.getRangedCombatStrength());
    }

    public CombatUnits(CombatUnits combatUnits) {
        super(combatUnits);
    }

    public boolean isSleeping() {
        return isSleeping;
    }

    public void setSleeping(boolean sleeping) {
        isSleeping = sleeping;
    }

    public boolean isStillForATurn() {
        return stillForATurn;
    }

    public void setStillForATurn(boolean stillForATurn) {
        this.stillForATurn = stillForATurn;
    }


    public boolean isIsAlert() {
        return this.isAlert;
    }

    public boolean getIsAlert() {
        return this.isAlert;
    }

    public void setIsAlert(boolean isAlert) {
        this.isAlert = isAlert;
    }

    public Integer getCombatStrength() {
        return combatStrength;
    }

    public void setCombatStrength(Integer combatStrength) {
        this.combatStrength = combatStrength;
    }

    public Integer getRange() {
        return range;
    }

    public void setRange(Integer range) {
        this.range = range;
    }

    public Integer getRangedCombatStrength() {
        return rangedCombatStrength;
    }

    public void setRangedCombatStrength(Integer rangedCombatStrength) {
        this.rangedCombatStrength = rangedCombatStrength;
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

    public float calculateAttack() {
        return 0;
    }


}