package models.Technology;

import com.sun.jdi.ArrayReference;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Tech {
    private TechEnum techName;

    private int goldCost;
    private ArrayList<TechEnum> prerequisiteTechs;
    private int productionPointsNeeded;

    public Tech(TechEnum techName) {
        setTechName(techName);
        setGoldCost(techName.getCost());
        prerequisiteTechs = findPrerequisiteTechs(techName);
    }

    public static ArrayList<TechEnum> findPrerequisiteTechs(TechEnum techName) {
        if (techName == TechEnum.AGRICULTURE) return new ArrayList<>(Arrays.asList());
        if (techName == TechEnum.ANIMAL_HUSBANDRY) return new ArrayList<>(Arrays.asList(TechEnum.AGRICULTURE));
        if (techName == TechEnum.ARCHERY) return new ArrayList<>(Arrays.asList(TechEnum.AGRICULTURE));
        if (techName == TechEnum.BRONZE_WORKING) return new ArrayList<>(Arrays.asList(TechEnum.MINING));
        if (techName == TechEnum.CALENDAR) return new ArrayList<>(Arrays.asList(TechEnum.POTTERY));
        if (techName == TechEnum.MASONRY) return new ArrayList<>(Arrays.asList(TechEnum.MINING));
        if (techName == TechEnum.MINING) return new ArrayList<>(Arrays.asList(TechEnum.AGRICULTURE));
        if (techName == TechEnum.POTTERY) return new ArrayList<>(Arrays.asList(TechEnum.AGRICULTURE));
        if (techName == TechEnum.THE_WHEEL) return new ArrayList<>(Arrays.asList(TechEnum.ANIMAL_HUSBANDRY));
        if (techName == TechEnum.TRAPPING) return new ArrayList<>(Arrays.asList(TechEnum.ANIMAL_HUSBANDRY));
        if (techName == TechEnum.WRITING) return new ArrayList<>(Arrays.asList(TechEnum.POTTERY));
        if (techName == TechEnum.CONSTRUCTION) return new ArrayList<>(Arrays.asList(TechEnum.MASONRY));
        if (techName == TechEnum.HORSEBACK_RIDING) return new ArrayList<>(Arrays.asList(TechEnum.THE_WHEEL));
        if (techName == TechEnum.IRON_WORKING) return new ArrayList<>(Arrays.asList(TechEnum.BRONZE_WORKING));
        if (techName == TechEnum.MATHEMATICS)
            return new ArrayList<>(Arrays.asList(TechEnum.THE_WHEEL, TechEnum.ARCHERY));
        if (techName == TechEnum.PHILOSOPHY) return new ArrayList<>(Arrays.asList(TechEnum.WRITING));
        if (techName == TechEnum.CHIVALRY)
            return new ArrayList<>(Arrays.asList(TechEnum.CIVIL_SERVICE, TechEnum.HORSEBACK_RIDING, TechEnum.CURRENCY));
        if (techName == TechEnum.CIVIL_SERVICE)
            return new ArrayList<>(Arrays.asList(TechEnum.PHILOSOPHY, TechEnum.TRAPPING));
        if (techName == TechEnum.CURRENCY) return new ArrayList<>(Arrays.asList(TechEnum.MATHEMATICS));
        if (techName == TechEnum.EDUCATION) return new ArrayList<>(Arrays.asList(TechEnum.THEOLOGY));
        if (techName == TechEnum.ENGINEERING)
            return new ArrayList<>(Arrays.asList(TechEnum.MATHEMATICS, TechEnum.CONSTRUCTION));
        if (techName == TechEnum.MACHINERY) return new ArrayList<>(Arrays.asList(TechEnum.ENGINEERING));
        if (techName == TechEnum.METAL_CASTING) return new ArrayList<>(Arrays.asList(TechEnum.IRON_WORKING));
        if (techName == TechEnum.PHYSICS)
            return new ArrayList<>(Arrays.asList(TechEnum.ENGINEERING, TechEnum.METAL_CASTING));
        if (techName == TechEnum.STEEL) return new ArrayList<>(Arrays.asList(TechEnum.METAL_CASTING));
        if (techName == TechEnum.THEOLOGY)
            return new ArrayList<>(Arrays.asList(TechEnum.CALENDAR, TechEnum.PHILOSOPHY));
        if (techName == TechEnum.TELEGRAPH) return new ArrayList<>(Arrays.asList(TechEnum.ELECTRICITY));
        if (techName == TechEnum.STEAM_POWER)
            return new ArrayList<>(Arrays.asList(TechEnum.SCIENTIFIC_THEORY, TechEnum.MILITARY_SCIENCE));
        if (techName == TechEnum.REPLACEABLE_PARTS) return new ArrayList<>(Arrays.asList(TechEnum.STEAM_POWER));
        if (techName == TechEnum.RAILROAD) return new ArrayList<>(Arrays.asList(TechEnum.STEAM_POWER));
        if (techName == TechEnum.RADIO) return new ArrayList<>(Arrays.asList(TechEnum.ELECTRICITY));
        if (techName == TechEnum.ELECTRICITY)
            return new ArrayList<>(Arrays.asList(TechEnum.BIOLOGY, TechEnum.STEAM_POWER));
        if (techName == TechEnum.DYNAMITE) return new ArrayList<>(Arrays.asList(TechEnum.FERTILIZER, TechEnum.RIFLING));
        if (techName == TechEnum.COMBUSTION)
            return new ArrayList<>(Arrays.asList(TechEnum.REPLACEABLE_PARTS, TechEnum.RAILROAD, TechEnum.DYNAMITE));
        if (techName == TechEnum.BIOLOGY)
            return new ArrayList<>(Arrays.asList(TechEnum.ARCHAEOLOGY, TechEnum.SCIENTIFIC_THEORY));
        if (techName == TechEnum.SCIENTIFIC_THEORY) return new ArrayList<>(Arrays.asList(TechEnum.ACOUSTICS));
        if (techName == TechEnum.RIFLING) return new ArrayList<>(Arrays.asList(TechEnum.METALLURGY));
        if (techName == TechEnum.PRINTING_PRESS)
            return new ArrayList<>(Arrays.asList(TechEnum.MACHINERY, TechEnum.PHYSICS));
        if (techName == TechEnum.MILITARY_SCIENCE)
            return new ArrayList<>(Arrays.asList(TechEnum.ECONOMICS, TechEnum.CHEMISTRY));
        if (techName == TechEnum.METALLURGY) return new ArrayList<>(Arrays.asList(TechEnum.GUN_POWDER));
        if (techName == TechEnum.GUN_POWDER) return new ArrayList<>(Arrays.asList(TechEnum.PHYSICS, TechEnum.STEEL));
        if (techName == TechEnum.FERTILIZER) return new ArrayList<>(Arrays.asList(TechEnum.CHEMISTRY));
        if (techName == TechEnum.ECONOMICS)
            return new ArrayList<>(Arrays.asList(TechEnum.BANKING, TechEnum.PRINTING_PRESS));
        if (techName == TechEnum.CHEMISTRY) return new ArrayList<>(Arrays.asList(TechEnum.GUN_POWDER));
        if (techName == TechEnum.BANKING) return new ArrayList<>(Arrays.asList(TechEnum.EDUCATION, TechEnum.CHIVALRY));
        if (techName == TechEnum.ARCHAEOLOGY) return new ArrayList<>(Arrays.asList(TechEnum.ACOUSTICS));
        if (techName == TechEnum.ACOUSTICS) return new ArrayList<>(Arrays.asList(TechEnum.EDUCATION));
        return null;
    }


    public TechEnum getTechName() {
        return this.techName;
    }

    public void setTechName(TechEnum techName) {
        this.techName = techName;
    }

    public ArrayList<TechEnum> getPrerequisiteTechs() {
        return this.prerequisiteTechs;
    }

    public void setPrerequisiteTechs(ArrayList<TechEnum> prerequisiteTechs) {
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
