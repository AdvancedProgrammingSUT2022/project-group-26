package models.Building;

import models.Technology.Tech;

public class Building {
    private BuildingEnum name;
    private int goldCost;
    private int maintenance;
    private Tech requisiteTech;
    private int productionPointsNeeded;

    public Building(BuildingEnum name) {
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

    public Tech getRequisiteTech() {
        return requisiteTech;
    }

    public void setRequisiteTech(Tech requisiteTech) {
        this.requisiteTech = requisiteTech;
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
}