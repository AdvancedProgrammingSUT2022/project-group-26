package com.example.project.models.Technology;

import com.google.gson.annotations.SerializedName;

public enum TechEnum {
    @SerializedName("0")
    AGRICULTURE("agriculture", 20),
    @SerializedName("1")
    ANIMAL_HUSBANDRY("animal husbandry", 35),
    @SerializedName("2")
    ARCHERY("archery", 35),
    @SerializedName("3")
    BRONZE_WORKING("bronze working", 55),
    @SerializedName("4")
    CALENDAR("calendar", 70),
    @SerializedName("5")
    MASONRY("masonry", 55),
    @SerializedName("6")
    MINING("mining", 35),
    @SerializedName("7")
    POTTERY("pottery", 35),
    @SerializedName("8")
    THE_WHEEL("the wheel", 55),
    @SerializedName("9")
    TRAPPING("trapping", 55),
    @SerializedName("10")
    WRITING("writing", 55),
    @SerializedName("11")
    CONSTRUCTION("construction",100),
    @SerializedName("12")
    HORSEBACK_RIDING("horseback riding",100),
    @SerializedName("13")
    IRON_WORKING("iron working",150),
    @SerializedName("14")
    MATHEMATICS("mathematics",100),
    @SerializedName("15")
    PHILOSOPHY("philosophy",100),
    @SerializedName("16")
    CHIVALRY("chivalry",440),
    @SerializedName("17")
    CIVIL_SERVICE("civil service",400),
    @SerializedName("18")
    CURRENCY("currency",250),
    @SerializedName("19")
    EDUCATION("education",440),
    @SerializedName("20")
    ENGINEERING("engineering",250),
    @SerializedName("21")
    MACHINERY("machinery",440),
    @SerializedName("22")
    METAL_CASTING("metal casting",240),
    @SerializedName("23")
    PHYSICS("physics",440),
    @SerializedName("24")
    STEEL("steel",440),
    @SerializedName("25")
    THEOLOGY("theology",250),
    @SerializedName("26")
    ACOUSTICS("acoustics",650),
    @SerializedName("27")
    ARCHAEOLOGY("archaeology",1300),
    @SerializedName("28")
    BANKING("banking",650),
    @SerializedName("29")
    CHEMISTRY("chemistry",900),
    @SerializedName("30")
    ECONOMICS("economics",900),
    @SerializedName("31")
    FERTILIZER("fertilizer",1300),
    @SerializedName("32")
    GUN_POWDER("gunpowder",680),
    @SerializedName("33")
    METALLURGY("metallurgy",900),
    @SerializedName("34")
    MILITARY_SCIENCE("military science",1300),
    @SerializedName("35")
    PRINTING_PRESS("printing press",650),
    @SerializedName("36")
    RIFLING("rifling",1425),
    @SerializedName("37")
    SCIENTIFIC_THEORY("scientific theory",1300),
    @SerializedName("38")
    BIOLOGY("biology",1680),
    @SerializedName("39")
    COMBUSTION("combustion",2200),
    @SerializedName("40")
    DYNAMITE("dynamite",1900),
    @SerializedName("41")
    ELECTRICITY("electricity",1900),
    @SerializedName("42")
    RADIO("radio",2200),
    @SerializedName("43")
    RAILROAD("railroad",1900),
    @SerializedName("44")
    REPLACEABLE_PARTS("replaceable parts",1900),
    @SerializedName("45")
    STEAM_POWER("steam power",1680),
    @SerializedName("46")
    TELEGRAPH("telegraph",2200);


    private final String name;
    private final int cost;

    TechEnum(String name, int cost) {
        this.name = name;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }
}