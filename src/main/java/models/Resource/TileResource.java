package models.Resource;

import java.util.ArrayList;
import java.util.Arrays;

import models.Feature.TileFeature;
import models.Feature.TileFeatureEnum;
import models.Improvement.TileImprovement;
import models.Technology.TechEnum;
import models.Improvement.TileImprovementEnum;
import models.Tile.Tile;
import models.Tile.TileModeEnum;


public class TileResource {
    private TileResourceEnum resourceName;
    private int food;
    private int production;
    private int gold;
    private TileImprovementEnum improvement;
    private TechEnum requisiteTech;
    private ArrayList<Enum> whereCanBeFind;


    public TileResource(TileResourceEnum resourceName) {
        setResourceName(resourceName);
        setFood(resourceName.getFood());
        setProduction(resourceName.getProduction());
        setGold(resourceName.getGold());
        whereCanBeFind = findWhereCanBeFind(resourceName);
    }

    public TileResource(TileResource tileResource){
        setResourceName(tileResource.getResourceName());
        setFood(tileResource.getFood());
        setProduction(tileResource.getProduction());
        setGold(tileResource.getGold());
        setWhereCanBeFind(new ArrayList<>(tileResource.getWhereCanBeFind()));
    }

    public TileResource clone(){
        return new TileResource(this);
    }

    public static ArrayList<Enum> findWhereCanBeFind(TileResourceEnum resourceName) {
        ArrayList<Enum> whereCanBeFind = new ArrayList<>();
        if (resourceName == TileResourceEnum.BANANA)
            whereCanBeFind = new ArrayList<>(Arrays.asList(TileFeatureEnum.FOREST));
        else if (resourceName == TileResourceEnum.COW)
            whereCanBeFind = new ArrayList<>(Arrays.asList(TileModeEnum.GRASSLAND));
        else if (resourceName == TileResourceEnum.GAZELLE)
            whereCanBeFind = new ArrayList<>(Arrays.asList(TileFeatureEnum.FOREST, TileModeEnum.TUNDRA, TileModeEnum.HILL));
        else if (resourceName == TileResourceEnum.SHEEP)
            whereCanBeFind = new ArrayList<>(Arrays.asList(TileModeEnum.GRASSLAND, TileModeEnum.DESERT, TileModeEnum.PLAIN, TileModeEnum.HILL));
        else if (resourceName == TileResourceEnum.WHEAT)
            whereCanBeFind = new ArrayList<>(Arrays.asList(TileModeEnum.PLAIN, TileFeatureEnum.PLAIN));
        else if (resourceName == TileResourceEnum.COAL)
            whereCanBeFind = new ArrayList<>(Arrays.asList(TileModeEnum.GRASSLAND, TileModeEnum.PLAIN, TileModeEnum.HILL));
        else if (resourceName == TileResourceEnum.HORSE)
            whereCanBeFind = new ArrayList<>(Arrays.asList(TileModeEnum.GRASSLAND, TileModeEnum.TUNDRA, TileModeEnum.PLAIN));
        else if (resourceName == TileResourceEnum.IRON)
            whereCanBeFind = new ArrayList<>(Arrays.asList(TileModeEnum.GRASSLAND,
                    TileModeEnum.DESERT, TileModeEnum.PLAIN, TileModeEnum.HILL, TileModeEnum.TUNDRA, TileModeEnum.SNOW));
        else if (resourceName == TileResourceEnum.COTTON)
            whereCanBeFind = new ArrayList<>(Arrays.asList(TileModeEnum.GRASSLAND, TileModeEnum.DESERT, TileModeEnum.PLAIN));
        else if (resourceName == TileResourceEnum.PAINT)
            whereCanBeFind = new ArrayList<>(Arrays.asList(TileFeatureEnum.DENSE_FOREST, TileFeatureEnum.FOREST));
        else if (resourceName == TileResourceEnum.FUR)
            whereCanBeFind = new ArrayList<>(Arrays.asList(TileModeEnum.TUNDRA, TileFeatureEnum.FOREST));
        else if (resourceName == TileResourceEnum.SUGAR)
            whereCanBeFind = new ArrayList<>(Arrays.asList(TileFeatureEnum.PLAIN, TileFeatureEnum.SWAMP));
        else if (resourceName == TileResourceEnum.SILVER)
            whereCanBeFind = new ArrayList<>(Arrays.asList(TileModeEnum.TUNDRA, TileModeEnum.DESERT, TileModeEnum.HILL));
        else if (resourceName == TileResourceEnum.SILK)
            whereCanBeFind = new ArrayList<>(Arrays.asList(TileFeatureEnum.FOREST));
        else if (resourceName == TileResourceEnum.MARBLE)
            whereCanBeFind = new ArrayList<>(Arrays.asList(TileModeEnum.TUNDRA, TileModeEnum.PLAIN,
                    TileModeEnum.DESERT, TileModeEnum.GRASSLAND, TileModeEnum.HILL));
        else if (resourceName == TileResourceEnum.TUSK)
            whereCanBeFind = new ArrayList<>(Arrays.asList(TileFeatureEnum.PLAIN));
        else if (resourceName == TileResourceEnum.INCENSE)
            whereCanBeFind = new ArrayList<>(Arrays.asList(TileFeatureEnum.PLAIN, TileModeEnum.DESERT));
        else if (resourceName == TileResourceEnum.GOLD)
            whereCanBeFind = new ArrayList<>(Arrays.asList(TileFeatureEnum.PLAIN, TileModeEnum.DESERT
                    , TileModeEnum.GRASSLAND, TileModeEnum.HILL));
        else if (resourceName == TileResourceEnum.GEM_STONES)
            whereCanBeFind = new ArrayList<>(Arrays.asList(TileFeatureEnum.DENSE_FOREST, TileModeEnum.TUNDRA,
                    TileFeatureEnum.PLAIN, TileModeEnum.DESERT, TileModeEnum.GRASSLAND, TileModeEnum.HILL));
        return whereCanBeFind;
    }

    public TileResourceEnum getResourceName() {
        return this.resourceName;
    }

    public void setResourceName(TileResourceEnum resourceName) {
        this.resourceName = resourceName;
    }

    public int getFood() {
        return this.food;
    }

    public void setFood(int food) {
        this.food = food;
    }

    public int getProduction() {
        return this.production;
    }

    public void setProduction(int production) {
        this.production = production;
    }

    public int getGold() {
        return this.gold;
    }

    public void setGold(int gold) {
        this.gold = gold;
    }

    public TileImprovementEnum getImprovement() {
        return this.improvement;
    }

    public void setImprovement(TileImprovementEnum improvement) {
        this.improvement = improvement;
    }

    public TechEnum getRequisiteTech() {
        return this.requisiteTech;
    }

    public void setRequisiteTech(TechEnum requisiteTech) {
        this.requisiteTech = requisiteTech;
    }

    public ArrayList<Enum> getWhereCanBeFind() {
        return this.whereCanBeFind;
    }

    public void setWhereCanBeFind(ArrayList<Enum> whereCanBeFind) {
        this.whereCanBeFind = whereCanBeFind;
    }

    public static ArrayList<TileResourceEnum> findPossibleResources(Tile tile) {
        ArrayList<TileResourceEnum> possibleResources = new ArrayList<>();
        TileResourceEnum[] allResources = TileResourceEnum.values();
        for (int i = 0; i < allResources.length; i++) {
            if (findWhereCanBeFind(allResources[i]).contains(tile.getMode().getTileName()))
                possibleResources.add(allResources[i]);
            for (int j = 0; j < tile.getFeatures().size(); j++) {
                if (findWhereCanBeFind(allResources[i]).contains(tile.getFeatures().get(j).getFeatureName()))
                    possibleResources.add(allResources[i]);
            }
        }
        return possibleResources;
    }
}