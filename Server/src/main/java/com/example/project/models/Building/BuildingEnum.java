package com.example.project.models.Building;

import com.example.project.App;
import com.google.gson.annotations.SerializedName;
import com.example.project.models.Technology.TechEnum;

public enum BuildingEnum {
    @SerializedName("0")
    BARRACKS("Barracks", 80, 1, TechEnum.BRONZE_WORKING), // +15 xp land unit
    @SerializedName("1")
    GRANARY("Granary", 100, 1, TechEnum.POTTERY), // done
    @SerializedName("2")
    LIBRARY("Library", 80, 1, TechEnum.WRITING), // done
    @SerializedName("3")
    MONUMENT("Monument", 60, 1, null), // no ability - done
    @SerializedName("4")
    WALLS("Walls", 100, 1, TechEnum.MASONRY), // done
    @SerializedName("5")
    WATER_MILL("Water Mill", 120, 2, TechEnum.THE_WHEEL), // done
    @SerializedName("6")
    ARMORY("Armory", 130, 3, TechEnum.IRON_WORKING), // +15 XP for all Land Units, Requires Barracks
    @SerializedName("7")
    BURIAL_TOMB("Burial Tomb", 120, 0, TechEnum.PHILOSOPHY), // done
    @SerializedName("8")
    CIRCUS("Circus", 150, 3, TechEnum.HORSEBACK_RIDING),// done
    @SerializedName("9")
    COLOSSEUM("Colosseum", 150, 3, TechEnum.CONSTRUCTION),// done
    @SerializedName("10")
    COURTHOUSE("Courthouse", 200, 5, TechEnum.MATHEMATICS), // done
    @SerializedName("11")
    STABLE("Stable", 100, 1, TechEnum.HORSEBACK_RIDING), //+25% Production of mounted units
    @SerializedName("12")
    TEMPLE("Temple", 120, 2, TechEnum.PHILOSOPHY),// done
    @SerializedName("13")
    CASTLE("Castle", 200, 3, TechEnum.CHIVALRY), // done
    @SerializedName("14")
    FORGE("Forge", 150, 2, TechEnum.METAL_CASTING), //+15% Production of land units
    @SerializedName("15")
    GARDEN("Garden", 120, 2, TechEnum.THEOLOGY), // done - removed ability
    @SerializedName("16")
    MARKET("Market", 120, 0, TechEnum.CURRENCY), // done
    @SerializedName("17")
    MINT("Mint", 120, 0, TechEnum.CURRENCY), // done
    @SerializedName("18")
    MONASTERY("Monastery", 120, 2, TechEnum.THEOLOGY), // done
    @SerializedName("19")
    UNIVERSITY("University", 200, 3, TechEnum.EDUCATION),//done
    @SerializedName("20")
    WORKSHOP("Workshop", 100, 2, TechEnum.METAL_CASTING),//+20% production of Buildings
    @SerializedName("21")
    BANK("Bank", 220, 0, TechEnum.BANKING), // done  +50 (per) science
    @SerializedName("22")
    MILITARY_ACADEMY("Military Academy", 350, 3, TechEnum.MILITARY_SCIENCE), //+15 XP for all Land Units
    @SerializedName("23")
    MUSEUM("Museum", 350, 3, TechEnum.ARCHAEOLOGY), // done
    @SerializedName("24")
    OPERA_HOUSE("Opera House", 220, 3, TechEnum.ACOUSTICS), // done
    @SerializedName("25")
    PUBLIC_SCHOOL("Public School", 350, 3, TechEnum.SCIENTIFIC_THEORY), // +50 science
    @SerializedName("26")
    SATRAP_COURT("Satrap Court", 220, 0, TechEnum.BANKING),// done
    @SerializedName("27")
    THEATER("Theater", 300, 5, TechEnum.PRINTING_PRESS),// done
    @SerializedName("28")
    WINDMILL("Windmill", 180, 2, TechEnum.ECONOMICS), // +15% Production, City cannot be on Hills
    @SerializedName("29")
    ARSENAL("Arsenal", 350, 3, TechEnum.RAILROAD), //+20% production of Land Units
    @SerializedName("30")
    BROADCAST_TOWER("Broadcast Tower", 600, 3, TechEnum.RADIO), // done
    @SerializedName("31")
    FACTORY("Factory", 300, 3, TechEnum.STEAM_POWER), // +50% Production
    @SerializedName("32")
    HOSPITAL("Hospital", 400, 2, TechEnum.BIOLOGY),//-50% Food needed for City Growth
    @SerializedName("33")
    MILITARY_BASE("Military Base", 450, 4, TechEnum.TELEGRAPH), //done
    @SerializedName("34")
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
}
