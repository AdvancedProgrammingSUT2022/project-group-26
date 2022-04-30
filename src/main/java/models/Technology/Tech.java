package models.Technology;

import java.util.ArrayList;

public class Tech {
    private TechEnum techName;

    private int goldCost;
    private ArrayList<Tech> prerequisiteTechs;
    private int productionPointsNeeded;

    public Tech(TechEnum techName) {
        setTechName(techName);
        setGoldCost(techName.getCost());
    }

    public TechEnum getTechName() {
        return this.techName;
    }

    public void setTechName(TechEnum techName) {
        this.techName = techName;
    }

    public ArrayList<Tech> getPrerequisiteTechs() {
        return this.prerequisiteTechs;
    }

    public void setPrerequisiteTechs(ArrayList<Tech> prerequisiteTechs) {
        this.prerequisiteTechs = prerequisiteTechs;
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
