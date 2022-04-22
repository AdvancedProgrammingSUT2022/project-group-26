package models.Resource;

import java.util.ArrayList;
import java.util.Arrays;

import models.Feature.TileFeature;
import models.Feature.TileFeatureEnum;
import models.Technology.TechEnum;
import models.Improvement.TileImprovementEnum;
import models.Tile.Tile;
import models.Tile.TileModeEnum;

// ali ino kolesho vase khodamo gozashtam begard bebin chizi hast ke bekhayo nabashe ...

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

    public static ArrayList<Enum> findWhereCanBeFind(TileResourceEnum resourceName) {
        ArrayList<Enum> whereCanBeFind = new ArrayList<>();
        if (resourceName == TileResourceEnum.banana)
            whereCanBeFind = new ArrayList<>(Arrays.asList(TileFeatureEnum.forest));
        else if (resourceName == TileResourceEnum.cow)
            whereCanBeFind = new ArrayList<>(Arrays.asList(TileModeEnum.grassland));
        else if (resourceName == TileResourceEnum.gazelle)
            whereCanBeFind = new ArrayList<>(Arrays.asList(TileFeatureEnum.forest, TileModeEnum.tundra, TileModeEnum.hill));
        else if (resourceName == TileResourceEnum.sheep)
            whereCanBeFind = new ArrayList<>(Arrays.asList(TileModeEnum.grassland, TileModeEnum.desert, TileModeEnum.PLAIN, TileModeEnum.hill));
        else if (resourceName == TileResourceEnum.wheat)
            whereCanBeFind = new ArrayList<>(Arrays.asList(TileModeEnum.PLAIN, TileFeatureEnum.plain));
        else if (resourceName == TileResourceEnum.coal)
            whereCanBeFind = new ArrayList<>(Arrays.asList(TileModeEnum.grassland, TileModeEnum.PLAIN, TileModeEnum.hill));
        else if (resourceName == TileResourceEnum.horse)
            whereCanBeFind = new ArrayList<>(Arrays.asList(TileModeEnum.grassland, TileModeEnum.tundra, TileModeEnum.PLAIN));
        else if (resourceName == TileResourceEnum.iron)
            whereCanBeFind = new ArrayList<>(Arrays.asList(TileModeEnum.grassland,
                    TileModeEnum.desert, TileModeEnum.PLAIN, TileModeEnum.hill, TileModeEnum.tundra, TileModeEnum.snow));
        else if (resourceName == TileResourceEnum.cotton)
            whereCanBeFind = new ArrayList<>(Arrays.asList(TileModeEnum.grassland, TileModeEnum.desert, TileModeEnum.PLAIN));
        else if (resourceName == TileResourceEnum.PAINT)
            whereCanBeFind = new ArrayList<>(Arrays.asList(TileFeatureEnum.denceForest, TileFeatureEnum.forest));
        else if (resourceName == TileResourceEnum.fur)
            whereCanBeFind = new ArrayList<>(Arrays.asList(TileModeEnum.tundra, TileFeatureEnum.forest));
        else if (resourceName == TileResourceEnum.sugar)
            whereCanBeFind = new ArrayList<>(Arrays.asList(TileFeatureEnum.plain, TileFeatureEnum.swamp));
        else if (resourceName == TileResourceEnum.silver)
            whereCanBeFind = new ArrayList<>(Arrays.asList(TileModeEnum.tundra, TileModeEnum.desert, TileModeEnum.hill));
        else if (resourceName == TileResourceEnum.silk)
            whereCanBeFind = new ArrayList<>(Arrays.asList(TileFeatureEnum.forest));
        else if (resourceName == TileResourceEnum.marble)
            whereCanBeFind = new ArrayList<>(Arrays.asList(TileModeEnum.tundra, TileModeEnum.PLAIN,
                    TileModeEnum.desert, TileModeEnum.grassland, TileModeEnum.hill));
        else if (resourceName == TileResourceEnum.tusk) whereCanBeFind =
                new ArrayList<>(Arrays.asList(TileFeatureEnum.plain));
        else if (resourceName == TileResourceEnum.INCENSE)
            whereCanBeFind = new ArrayList<>(Arrays.asList(TileFeatureEnum.plain, TileModeEnum.desert));
        else if (resourceName == TileResourceEnum.GOLD)
            whereCanBeFind = new ArrayList<>(Arrays.asList(TileFeatureEnum.plain, TileModeEnum.desert
                    , TileModeEnum.grassland, TileModeEnum.hill));
        else if (resourceName == TileResourceEnum.gemStones)
            whereCanBeFind = new ArrayList<>(Arrays.asList(TileFeatureEnum.denceForest, TileModeEnum.tundra,
                    TileFeatureEnum.plain, TileModeEnum.desert, TileModeEnum.grassland, TileModeEnum.hill));
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