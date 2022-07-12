package com.example.project.models.Units;

import com.example.project.models.Player;
import com.example.project.models.Tile.Tile;

import java.util.ArrayList;

public class Unit {
    protected Tile position;
    protected Player player;
    protected UnitTypeEnum unitTypeEnum;
    protected UnitNameEnum unitNameEnum;
    protected Double movement;
    protected ArrayList<Tile> savedRoute;
    protected boolean isSleeping = false;
    protected boolean isAlert = false;


    public Unit(Player player, Tile position, UnitNameEnum unitNameEnum) {
        setPosition(position);
        setUnitNameEnum(unitNameEnum);
        setPlayer(player);
        setUnitTypeEnum(unitNameEnum.getCombatType());
        setMovement(unitNameEnum.getMovement());
    }

    public Unit(Unit unit) {
        setPosition(unit.getPosition().clone());
        setUnitNameEnum(unit.getUnitNameEnum());
        setPlayer(unit.getPlayer());
        setUnitTypeEnum(unit.getUnitTypeEnum());
        setMovement(unit.getMovement());
    }

    public Unit clone() {
        return new Unit(this);
    }


    public boolean isSleeping() {
        return isSleeping;
    }

    public void setSleeping(boolean sleeping) {
        isSleeping = sleeping;
    }

    public boolean isAlert() {
        return this.isAlert;
    }


    public void setAlert(boolean isAlert) {
        this.isAlert = isAlert;
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


    public Double getMovement() {
        return movement;
    }

    public void setMovement(Double movement) {
        this.movement = movement;
    }


    public boolean isACombatUnit() {
        return !(getUnitTypeEnum() == UnitTypeEnum.CIVILIAN);
    }

    public boolean isACivilian() {
        return getUnitTypeEnum() == UnitTypeEnum.CIVILIAN;
    }

    public boolean isARangedCombatUnit() {
        return (getUnitTypeEnum() == UnitTypeEnum.SIEGE || getUnitTypeEnum() == UnitTypeEnum.ARCHERY);
    }

    public boolean isAMeleeCombatUnit() {
        return !(getUnitTypeEnum() == UnitTypeEnum.SIEGE
                || getUnitTypeEnum() == UnitTypeEnum.ARCHERY) && !(getUnitTypeEnum() == UnitTypeEnum.CIVILIAN);
    }

    public ArrayList<Tile> getSavedRoute() {
        return savedRoute;
    }

    public void setSavedRoute(ArrayList<Tile> savedRoute) {
        this.savedRoute = savedRoute;
    }

    public boolean isASiege() {
        return getUnitTypeEnum() == UnitTypeEnum.SIEGE;
    }

    public void resetMovement() {
        setMovement(getUnitNameEnum().getMovement());
    }

    public Double getMaxMovement() {
        return getUnitNameEnum().getMovement();
    }

    public boolean isAWorker() {
        return getUnitNameEnum() == UnitNameEnum.WORKER;
    }
}