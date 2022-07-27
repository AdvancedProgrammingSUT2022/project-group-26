package com.example.project.models.Building;

import com.example.project.App;
import com.example.project.models.Technology.TechEnum;
import javafx.scene.image.Image;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public enum BuildingEnum {
    BARRACKS("Barracks", 80, 1, TechEnum.BRONZE_WORKING), // +15 xp land unit
    GRANARY("Granary", 100, 1, TechEnum.POTTERY), // done
    LIBRARY("Library", 80, 1, TechEnum.WRITING), // done
    MONUMENT("Monument", 60, 1, null), // no ability - done
    WALLS("Walls", 100, 1, TechEnum.MASONRY), // done
    WATER_MILL("Water Mill", 120, 2, TechEnum.THE_WHEEL), // done
    ARMORY("Armory", 130, 3, TechEnum.IRON_WORKING), // +15 XP for all Land Units, Requires Barracks
    BURIAL_TOMB("Burial Tomb", 120, 0, TechEnum.PHILOSOPHY), // done
    CIRCUS("Circus", 150, 3, TechEnum.HORSEBACK_RIDING),// done
    COLOSSEUM("Colosseum", 150, 3, TechEnum.CONSTRUCTION),// done

    COURTHOUSE("Courthouse", 200, 5, TechEnum.MATHEMATICS), // done

    STABLE("Stable", 100, 1, TechEnum.HORSEBACK_RIDING), //+25% Production of mounted units

    TEMPLE("Temple", 120, 2, TechEnum.PHILOSOPHY),// done

    CASTLE("Castle", 200, 3, TechEnum.CHIVALRY), // done

    FORGE("Forge", 150, 2, TechEnum.METAL_CASTING), //+15% Production of land units

    GARDEN("Garden", 120, 2, TechEnum.THEOLOGY), // done - removed ability

    MARKET("Market", 120, 0, TechEnum.CURRENCY), // done

    MINT("Mint", 120, 0, TechEnum.CURRENCY), // done

    MONASTERY("Monastery", 120, 2, TechEnum.THEOLOGY), // done

    UNIVERSITY("University", 200, 3, TechEnum.EDUCATION),//done

    WORKSHOP("Workshop", 100, 2, TechEnum.METAL_CASTING),//+20% production of Buildings

    BANK("Bank", 220, 0, TechEnum.BANKING), // done  +50 (per) science

    MILITARY_ACADEMY("Military Academy", 350, 3, TechEnum.MILITARY_SCIENCE), //+15 XP for all Land Units

    MUSEUM("Museum", 350, 3, TechEnum.ARCHAEOLOGY), // done

    OPERA_HOUSE("Opera House", 220, 3, TechEnum.ACOUSTICS), // done

    PUBLIC_SCHOOL("Public School", 350, 3, TechEnum.SCIENTIFIC_THEORY), // +50 science

    SATRAP_COURT("Satrap Court", 220, 0, TechEnum.BANKING),// done

    THEATER("Theater", 300, 5, TechEnum.PRINTING_PRESS),// done

    WINDMILL("Windmill", 180, 2, TechEnum.ECONOMICS), // +15% Production, City cannot be on Hills

    ARSENAL("Arsenal", 350, 3, TechEnum.RAILROAD), //+20% production of Land Units

    BROADCAST_TOWER("Broadcast Tower", 600, 3, TechEnum.RADIO), // done

    FACTORY("Factory", 300, 3, TechEnum.STEAM_POWER), // +50% Production

    HOSPITAL("Hospital", 400, 2, TechEnum.BIOLOGY),//-50% Food needed for City Growth

    MILITARY_BASE("Military Base", 450, 4, TechEnum.TELEGRAPH), //done

    STOCK_EXCHANGE("Stock Exchange", 650, 0, TechEnum.ELECTRICITY); // done

    private final String name;
    private final int cost;
    private final int maintenance;
    private final TechEnum techEnum;

    BuildingEnum(String name, int cost, int maintenance, TechEnum techEnum) {
        this.name = name;
        this.cost = cost;
        this.maintenance = maintenance;
        this.techEnum = techEnum;
    }

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    public int getMaintenance() {
        return maintenance;
    }

    public TechEnum getTechEnum() {
        return techEnum;
    }

    public static BuildingEnum valueOfLabel(String label) {
        for (BuildingEnum e : values()) {
            if (e.name.equals(label)) {
                return e;
            }
        }
        return null;
    }

    private static HashMap<BuildingEnum, Image> images = new HashMap<>();

    static {
        for (BuildingEnum buildingEnum : BuildingEnum.values()) {
            try {
                images.put(buildingEnum,
                        new Image(String.valueOf(new URL(App.class.getResource("/Image/Game/Building/" + buildingEnum.getName() + ".png").toString()))));
            } catch (MalformedURLException e) {
                throw new RuntimeException(e);
            }
        }
        ;

    }

    public static HashMap<BuildingEnum, Image> getImages() {
        return images;
    }
}
