package models;

import models.Building.Building;
import models.Units.Unit;

public class BeingBuild {
    //TODO : maybe add something to know if its a building or unit ?!
    private Object gettingBuild;
    private Integer productionCost;

    public BeingBuild(Unit unit) {
        setGettingBuild(unit);
        setProductionCost(unit.getUnitNameEnum().getCost());
    }
    public BeingBuild(Building building){
        setGettingBuild(building);
        setProductionCost(building.getGoldCost());
    }


    public Object getGettingBuild() {
        return gettingBuild;
    }

    public void setGettingBuild(Object gettingBuild) {
        this.gettingBuild = gettingBuild;
    }

    public Integer getProductionCost() {
        return productionCost;
    }

    public void setProductionCost(Integer productionCost) {
        this.productionCost = productionCost;
    }

    public boolean isBuilt() {
        return getProductionCost() <= 0;
    }

    public void removeFromProductionCost(int value) {
        setProductionCost(getProductionCost() - value);
    }
}