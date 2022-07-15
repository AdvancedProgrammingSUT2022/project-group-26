package com.example.project.models.Building;

import com.google.gson.annotations.SerializedName;
import com.example.project.models.Technology.TechEnum;

public enum BuildingEnum {
    @SerializedName("0")
    BARRACKS("Barracks", 80, 1, TechEnum.BRONZE_WORKING, "src/main/resources/Image/Game/Building/Barracks.png"),
    @SerializedName("1")
    GRANARY("Granary", 100, 1, TechEnum.POTTERY, "src/main/resources/Image/Game/Building/Granary.png"),
    @SerializedName("2")
    LIBRARY("Library", 80, 1, TechEnum.WRITING, "src/main/resources/Image/Game/Building/Library.png"),
    @SerializedName("3")
    MONUMENT("Monument", 60, 1, null, "src/main/resources/Image/Game/Building/Monument.png"),
    @SerializedName("4")
    WALLS("Walls", 100, 1, TechEnum.MASONRY, "src/main/resources/Image/Game/Building/Walls.png"),
    @SerializedName("5")
    WATER_MILL("Water Mill", 120, 2, TechEnum.THE_WHEEL, "src/main/resources/Image/Game/Building/Water Mill.png"),
    @SerializedName("6")
    ARMORY("Armory", 130, 3, TechEnum.IRON_WORKING, "src/main/resources/Image/Game/Building/Armory.png"),
    @SerializedName("7")
    BURIAL_TOMB("Burial Tomb", 120, 0, TechEnum.PHILOSOPHY, "src/main/resources/Image/Game/Building/Burial Tomb.png"),
    @SerializedName("8")
    CIRCUS("Circus", 150, 3, TechEnum.HORSEBACK_RIDING, "src/main/resources/Image/Game/Building/Circus.png"),
    @SerializedName("9")
    COLOSSEUM("Colosseum", 150, 3, TechEnum.CONSTRUCTION, "src/main/resources/Image/Game/Building/Colosseum.png"),
    @SerializedName("10")
    COURTHOUSE("Courthouse", 200, 5, TechEnum.MATHEMATICS, "src/main/resources/Image/Game/Building/Courthouse.png"),
    @SerializedName("11")
    STABLE("Stable", 100, 1, TechEnum.HORSEBACK_RIDING, "src/main/resources/Image/Game/Building/Stable.png"),
    @SerializedName("12")
    TEMPLE("Temple", 120, 2, TechEnum.PHILOSOPHY, "src/main/resources/Image/Game/Building/Temple.png"),
    @SerializedName("13")
    CASTLE("Castle", 200, 3, TechEnum.CHIVALRY, "src/main/resources/Image/Game/Building/Castle.png"),
    @SerializedName("14")
    FORGE("Forge", 150, 2, TechEnum.METAL_CASTING, "src/main/resources/Image/Game/Building/Forge.png"),
    @SerializedName("15")
    GARDEN("Garden", 120, 2, TechEnum.THEOLOGY, "src/main/resources/Image/Game/Building/Garden.png"),
    @SerializedName("16")
    MARKET("Market", 120, 0, TechEnum.CURRENCY, "src/main/resources/Image/Game/Building/Market.png"),
    @SerializedName("17")
    MINT("Mint", 120, 0, TechEnum.CURRENCY, "src/main/resources/Image/Game/Building/Mint.png"),
    @SerializedName("18")
    MONASTERY("Monastery", 120, 2, TechEnum.THEOLOGY, "src/main/resources/Image/Game/Building/Monastery.png"),
    @SerializedName("19")
    UNIVERSITY("University", 200, 3, TechEnum.EDUCATION, "src/main/resources/Image/Game/Building/University.png"),
    @SerializedName("20")
    WORKSHOP("Workshop", 100, 2, TechEnum.METAL_CASTING, "src/main/resources/Image/Game/Building/Workshop.png"),
    @SerializedName("21")
    BANK("Bank", 220, 0, TechEnum.BANKING, "src/main/resources/Image/Game/Building/Bank.png"),
    @SerializedName("22")
    MILITARY_ACADEMY("Military Academy", 350, 3, TechEnum.MILITARY_SCIENCE, "src/main/resources/Image/Game/Building/Military Academy.png"),
    @SerializedName("23")
    MUSEUM("Museum", 350, 3, TechEnum.ARCHAEOLOGY, "src/main/resources/Image/Game/Building/Museum.png"),
    @SerializedName("24")
    OPERA_HOUSE("Opera House", 220, 3, TechEnum.ACOUSTICS, "src/main/resources/Image/Game/Building/Opera House.png"),
    @SerializedName("25")
    PUBLIC_SCHOOL("Public School", 350, 3, TechEnum.SCIENTIFIC_THEORY, "src/main/resources/Image/Game/Building/Public School.png"),
    @SerializedName("26")
    SATRAP_COURT("Satrap Court", 220, 0, TechEnum.BANKING, "src/main/resources/Image/Game/Building/Satrap Court.png"),
    @SerializedName("27")
    THEATER("Theater", 300, 5, TechEnum.PRINTING_PRESS, "src/main/resources/Image/Game/Building/Theater.png"),
    @SerializedName("28")
    WINDMILL("Windmill", 180, 2, TechEnum.ECONOMICS, "src/main/resources/Image/Game/Building/Windmill.png"),
    @SerializedName("29")
    ARSENAL("Arsenal", 350, 3, TechEnum.RAILROAD, "src/main/resources/Image/Game/Building/Arsenal.png"),
    @SerializedName("30")
    BROADCAST_TOWER("Broadcast Tower", 600, 3, TechEnum.RADIO, "src/main/resources/Image/Game/Building/Broadcast Tower.png"),
    @SerializedName("31")
    FACTORY("Factory", 300, 3, TechEnum.STEAM_POWER, "src/main/resources/Image/Game/Building/Factory.png"),
    @SerializedName("32")
    HOSPITAL("Hospital", 400, 2, TechEnum.BIOLOGY, "src/main/resources/Image/Game/Building/Hospital.png"),
    @SerializedName("33")
    MILITARY_BASE("Military Base", 450, 4, TechEnum.TELEGRAPH, "src/main/resources/Image/Game/Building/Military Base.png"),
    @SerializedName("34")
    STOCK_EXCHANGE("Stock Exchange", 650, 0, TechEnum.ELECTRICITY, "src/main/resources/Image/Game/Building/Stock Exchange.png");

    private final String name;
    private final int cost;
    private final int maintenance;
    private final TechEnum techEnum;
    private final String src;

    BuildingEnum(String name, int cost, int maintenance, TechEnum techEnum, String src) {
        this.name = name;
        this.cost = cost;
        this.maintenance = maintenance;
        this.techEnum = techEnum;
        this.src = src;
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

    public String getSrc() {
        return src;
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
