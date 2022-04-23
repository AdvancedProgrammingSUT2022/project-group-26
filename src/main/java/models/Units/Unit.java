package models.Units;

import models.*;
import models.Tile.Tile;

public class Unit {
    protected Tile position;
    protected Double movementPoints;
    protected Player player;
    protected UnitTypeEnum unitTypeEnum;
    protected UnitNameEnum unitNameEnum;
    protected Integer combatStrength;
    protected Double movement;
    protected Integer range;
    protected Integer rangedCombatStrength;

    ///////////////////////////////////////////////
//    protected int gold;
//    protected TileResource resourcesRequired;
//    protected Tech techRequired;
//    protected int productionPointsNeeded;
    ///////////////////////////////////////////
    protected boolean stillForATurn;
    protected boolean needsCommand;
    protected boolean isAwake;
    protected boolean isAlert;

    // TODO : add pre ordered routes

    public Unit(Tile position, UnitNameEnum unitNameEnum, Player player) {
        setPosition(position);
        setUnitNameEnum(unitNameEnum);
        setPlayer(player);
        setUnitTypeEnum(unitNameEnum.getCombatType());
        setCombatStrength(unitNameEnum.getCombatStrength());
        setMovement(unitNameEnum.getMovement());
        setRange(unitNameEnum.getRange());
        setRangedCombatStrength(unitNameEnum.getRangedCombatStrength());
        setMovementPoints(unitNameEnum.getMovement());
    }


    public Tile getPosition() {
        return this.position;
    }

    public void setPosition(Tile position) {
        this.position = position;
    }


    public Player getPlayer() {
        return this.player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public boolean isNeedsCommand() {
        return this.needsCommand;
    }

    public boolean getNeedsCommand() {
        return this.needsCommand;
    }

    public void setNeedsCommand(boolean needsCommand) {
        this.needsCommand = needsCommand;
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


    public Double getMovementPoints() {
        return movementPoints;
    }

    public void setMovementPoints(Double movementPoints) {
        this.movementPoints = movementPoints;
    }

    public boolean isAwake() {
        return isAwake;
    }

    public void setAwake(boolean awake) {
        isAwake = awake;
    }

    public UnitNameEnum getUnitNameEnum() {
        return unitNameEnum;
    }

    public void setUnitNameEnum(UnitNameEnum unitNameEnum) {
        this.unitNameEnum = unitNameEnum;
    }

    public UnitTypeEnum getUnitTypeEnum() {
        return unitTypeEnum;
    }

    public void setUnitTypeEnum(UnitTypeEnum unitTypeEnum) {
        this.unitTypeEnum = unitTypeEnum;
    }

    public Integer getCombatStrength() {
        return combatStrength;
    }

    public void setCombatStrength(Integer combatStrength) {
        this.combatStrength = combatStrength;
    }

    public Double getMovement() {
        return movement;
    }

    public void setMovement(Double movement) {
        this.movement = movement;
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

    public boolean isStillForATurn() {
        return stillForATurn;
    }

    public void setStillForATurn(boolean stillForATurn) {
        this.stillForATurn = stillForATurn;
    }

    public boolean isACombatUnit() {
        return !(getUnitTypeEnum() == UnitTypeEnum.civilian);
    }

    public boolean isACivilian() {
        return getUnitTypeEnum() == UnitTypeEnum.civilian;
    }

    public boolean isARangedCombatUnit() {
        return (getUnitTypeEnum() == UnitTypeEnum.siege || getUnitTypeEnum() == UnitTypeEnum.archery);
    }

    public boolean isAMeleeCombatUnit() {
        return !(getUnitTypeEnum() == UnitTypeEnum.siege || getUnitTypeEnum() == UnitTypeEnum.archery) && !(getUnitTypeEnum() == UnitTypeEnum.civilian);
    }
}