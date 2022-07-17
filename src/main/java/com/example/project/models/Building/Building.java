package com.example.project.models.Building;

import com.example.project.models.Player;
import com.example.project.models.Technology.Tech;
import com.example.project.models.Technology.TechEnum;
import com.example.project.models.Happiness;

public class Building {
    private BuildingEnum name;
    private int goldCost;
    private int maintenance;
    private TechEnum requisiteTechEnum;
    private Tech requisiteTech;
    private int productionPointsNeeded;
    private boolean broken = false;

    public Building(BuildingEnum name) {
        setName(name);
        setMaintenance(name.getMaintenance());
        setGoldCost(name.getCost());
        setRequisiteTechEnum(name.getTechEnum());
    }

    public BuildingEnum getName() {
        return name;
    }

    public void setName(BuildingEnum name) {
        this.name = name;
    }

    public int getMaintenance() {
        return maintenance;
    }

    public void setMaintenance(int maintenance) {
        this.maintenance = maintenance;
    }

    public int getGoldCost() {
        return goldCost;
    }

    public void setGoldCost(int goldCost) {
        this.goldCost = goldCost;
    }

    public int getProductionPointsNeeded() {
        return productionPointsNeeded;
    }

    public void setProductionPointsNeeded(int productionPointsNeeded) {
        this.productionPointsNeeded = productionPointsNeeded;
    }


    public TechEnum getRequisiteTechEnum() {
        return requisiteTechEnum;
    }

    public void setRequisiteTechEnum(TechEnum requisiteTechEnum) {
        this.requisiteTechEnum = requisiteTechEnum;
    }

    public Tech getRequisiteTech() {
        return requisiteTech;
    }

    public void setRequisiteTech(Tech requisiteTech) {
        this.requisiteTech = requisiteTech;
    }

    public boolean isBroken() {
        return broken;
    }

    public void setBroken(boolean broken) {
        this.broken = broken;
    }

    public void handlePlayerHappiness(Player player) {
        if (name == BuildingEnum.CIRCUS) Happiness.setHappiness(player, Happiness.getPlayerHappiness(player) + 3);
        if (name == BuildingEnum.COLOSSEUM) Happiness.setHappiness(player, Happiness.getPlayerHappiness(player) + 4);
        if (name == BuildingEnum.THEATER) Happiness.setHappiness(player, Happiness.getPlayerHappiness(player) + 4);
        //TODO: HANDLE COURTHOUSE
    }
}

