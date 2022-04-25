package models.Building;

import models.Technology.TechEnum;

public enum BuildingEnum {
    BARRACKS("Barracks", 80, 1, TechEnum.BRONZE_WORKING),
    GRANARY("Granary", 100, 1, TechEnum.POTTERY),
    LIBRARY("Library", 80, 1, TechEnum.WRITING),
    MONUMENT("Monument", 60, 1, null),
    WALLS("Walls", 100, 1, TechEnum.MASONRY),
    WATER_MILL("Water Mill", 120, 2, TechEnum.THE_WHEEL),
    ARMORY("Armory", 130, 3, TechEnum.IRON_WORKING),
    BURIAL_TOMB("Burial Tomb", 120, 0, TechEnum.PHILOSOPHY),
    CIRCUS("Circus", 150, 3, TechEnum.HORSEBACK_RIDING),
    COLOSSEUM("Colosseum", 150, 3, TechEnum.CONSTRUCTION),
    COURTHOUSE("Courthouse", 200, 5, TechEnum.MATHEMATICS),
    STABLE("Stable", 100, 1, TechEnum.HORSEBACK_RIDING),
    TEMPLE("Temple", 120, 2, TechEnum.PHILOSOPHY),
    CASTLE("Castle", 200, 3, TechEnum.CHIVALRY),
    FORGE("Forge", 150, 2, TechEnum.METAL_CASTING),
    GARDEN("Garden", 120, 2, TechEnum.THEOLOGY),
    MARKET("Market", 120, 0, TechEnum.CURRENCY),
    MINT("Mint", 120, 0, TechEnum.CURRENCY),
    MONASTERY("Monastery", 120, 2, TechEnum.THEOLOGY),
    UNIVERSITY("University", 200, 3, TechEnum.EDUCATION),
    WORKSHOP("Workshop", 100, 2, TechEnum.METAL_CASTING),
    BANK("Bank", 220, 0, TechEnum.BANKING),
    MILITARY_ACADEMY("Military Academy", 350, 3, TechEnum.MILITARY_SCIENCE),
    MUSEUM("Museum", 350, 3, TechEnum.ARCHAEOLOGY),
    OPERA_HOUSE("Opera House", 220, 3, TechEnum.ACOUSTICS),
    PUBLIC_SCHOOL("Public School", 350, 3, TechEnum.SCIENTIFIC_THEORY),
    SATRAP_COURT("Satrap Court", 220, 0, TechEnum.BANKING),
    THEATER("Theater", 300, 5, TechEnum.PRINTING_PRESS),
    WINDMILL("Windmill", 180, 2, TechEnum.ECONOMICS),
    ARSENAL("Arsenal", 350, 3, TechEnum.RAILROAD),
    BROADCAST_TOWER("Broadcast Tower", 600, 3, TechEnum.RADIO),
    FACTORY("Factory", 300, 3, TechEnum.STEAM_POWER),
    HOSPITAL("Hospital", 400, 2, TechEnum.BIOLOGY),
    MILITARY_BASE("Military Base", 450, 4, TechEnum.TELEGRAPH),
    STOCK_EXCHANGE("Stock Exchange", 650, 0, TechEnum.ELECTRICITY);

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
}
