package models;

public class BeingBuild {
    private int productionPointsNeeded;
    private Object thingBeingBuild;


    public int getProductionPointsNeeded() {
        return productionPointsNeeded;
    }

    public void setProductionPointsNeeded(int productionPointsNeeded) {
        this.productionPointsNeeded = productionPointsNeeded;
    }

    public Object getThingBeingBuild() {
        return thingBeingBuild;
    }

    public void setThingBeingBuild(Object thingBeingBuild) {
        this.thingBeingBuild = thingBeingBuild;
    }
}