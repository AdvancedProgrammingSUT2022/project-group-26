package com.example.project.models.Building;

import com.example.project.App;
import com.google.gson.annotations.SerializedName;
import com.example.project.models.Technology.TechEnum;
import javafx.scene.image.Image;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public enum BuildingEnum {
    @SerializedName("0")
    BARRACKS("Barracks", 80, 1, TechEnum.BRONZE_WORKING),
    @SerializedName("1")
    GRANARY("Granary", 100, 1, TechEnum.POTTERY),
    @SerializedName("2")
    LIBRARY("Library", 80, 1, TechEnum.WRITING ),
    @SerializedName("3")
    MONUMENT("Monument", 60, 1, null ),
    @SerializedName("4")
    WALLS("Walls", 100, 1, TechEnum.MASONRY),
    @SerializedName("5")
    WATER_MILL("Water Mill", 120, 2, TechEnum.THE_WHEEL ),
    @SerializedName("6")
    ARMORY("Armory", 130, 3, TechEnum.IRON_WORKING ),
    @SerializedName("7")
    BURIAL_TOMB("Burial Tomb", 120, 0, TechEnum.PHILOSOPHY ),
    @SerializedName("8")
    CIRCUS("Circus", 150, 3, TechEnum.HORSEBACK_RIDING ),
    @SerializedName("9")
    COLOSSEUM("Colosseum", 150, 3, TechEnum.CONSTRUCTION ),
    @SerializedName("10")
    COURTHOUSE("Courthouse", 200, 5, TechEnum.MATHEMATICS ),
    @SerializedName("11")
    STABLE("Stable", 100, 1, TechEnum.HORSEBACK_RIDING ),
    @SerializedName("12")
    TEMPLE("Temple", 120, 2, TechEnum.PHILOSOPHY ),
    @SerializedName("13")
    CASTLE("Castle", 200, 3, TechEnum.CHIVALRY ),
    @SerializedName("14")
    FORGE("Forge", 150, 2, TechEnum.METAL_CASTING),
    @SerializedName("15")
    GARDEN("Garden", 120, 2, TechEnum.THEOLOGY ),
    @SerializedName("16")
    MARKET("Market", 120, 0, TechEnum.CURRENCY ),
    @SerializedName("17")
    MINT("Mint", 120, 0, TechEnum.CURRENCY),
    @SerializedName("18")
    MONASTERY("Monastery", 120, 2, TechEnum.THEOLOGY ),
    @SerializedName("19")
    UNIVERSITY("University", 200, 3, TechEnum.EDUCATION ),
    @SerializedName("20")
    WORKSHOP("Workshop", 100, 2, TechEnum.METAL_CASTING ),
    @SerializedName("21")
    BANK("Bank", 220, 0, TechEnum.BANKING),
    @SerializedName("22")
    MILITARY_ACADEMY("Military Academy", 350, 3, TechEnum.MILITARY_SCIENCE ),
    @SerializedName("23")
    MUSEUM("Museum", 350, 3, TechEnum.ARCHAEOLOGY ),
    @SerializedName("24")
    OPERA_HOUSE("Opera House", 220, 3, TechEnum.ACOUSTICS ),
    @SerializedName("25")
    PUBLIC_SCHOOL("Public School", 350, 3, TechEnum.SCIENTIFIC_THEORY  ),
    @SerializedName("26")
    SATRAP_COURT("Satrap Court", 220, 0, TechEnum.BANKING ),
    @SerializedName("27")
    THEATER("Theater", 300, 5, TechEnum.PRINTING_PRESS ),
    @SerializedName("28")
    WINDMILL("Windmill", 180, 2, TechEnum.ECONOMICS ),
    @SerializedName("29")
    ARSENAL("Arsenal", 350, 3, TechEnum.RAILROAD ),
    @SerializedName("30")
    BROADCAST_TOWER("Broadcast Tower", 600, 3, TechEnum.RADIO ),
    @SerializedName("31")
    FACTORY("Factory", 300, 3, TechEnum.STEAM_POWER ),
    @SerializedName("32")
    HOSPITAL("Hospital", 400, 2, TechEnum.BIOLOGY ),
    @SerializedName("33")
    MILITARY_BASE("Military Base", 450, 4, TechEnum.TELEGRAPH ),
    @SerializedName("34")
    STOCK_EXCHANGE("Stock Exchange", 650, 0, TechEnum.ELECTRICITY );

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
        for(BuildingEnum buildingEnum : BuildingEnum.values()) {
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
