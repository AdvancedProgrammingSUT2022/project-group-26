package com.example.project.models.Technology;

import com.google.gson.annotations.SerializedName;

public enum TechEnum {
    @SerializedName("0")
    AGRICULTURE("agriculture", 20, "src/main/resources/Image/Game/Tech/agriculture.png"),
    @SerializedName("1")
    ANIMAL_HUSBANDRY("animal husbandry", 35, "src/main/resources/Image/Game/Tech/animal_husbandry.png"),
    @SerializedName("2")
    ARCHERY("archery", 35, "src/main/resources/Image/Game/Tech/archery.png"),
    @SerializedName("3")
    BRONZE_WORKING("bronze working", 55, "src/main/resources/Image/Game/Tech/bronze_working.png"),
    @SerializedName("4")
    CALENDAR("calendar", 70, "src/main/resources/Image/Game/Tech/calendar.png"),
    @SerializedName("5")
    MASONRY("masonry", 55, "src/main/resources/Image/Game/Tech/masonry.png"),
    @SerializedName("6")
    MINING("mining", 35, "src/main/resources/Image/Game/Tech/mining.png"),
    @SerializedName("7")
    POTTERY("pottery", 35, "src/main/resources/Image/Game/Tech/pottery.png"),
    @SerializedName("8")
    THE_WHEEL("the wheel", 55, "src/main/resources/Image/Game/Tech/the_wheel.png"),
    @SerializedName("9")
    TRAPPING("trapping", 55, "src/main/resources/Image/Game/Tech/trapping.png"),
    @SerializedName("10")
    WRITING("writing", 55, "src/main/resources/Image/Game/Tech/writing.png"),
    @SerializedName("11")
    CONSTRUCTION("construction", 100, "src/main/resources/Image/Game/Tech/construction.png"),
    @SerializedName("12")
    HORSEBACK_RIDING("horseback riding", 100, "src/main/resources/Image/Game/Tech/horseback_riding.png"),
    @SerializedName("13")
    IRON_WORKING("iron working", 150, "src/main/resources/Image/Game/Tech/iron_working.png"),
    @SerializedName("14")
    MATHEMATICS("mathematics", 100, "src/main/resources/Image/Game/Tech/mathematics.png"),
    @SerializedName("15")
    PHILOSOPHY("philosophy", 100, "src/main/resources/Image/Game/Tech/philosophy.png"),
    @SerializedName("16")
    CHIVALRY("chivalry", 440, "src/main/resources/Image/Game/Tech/chivalry.png"),
    @SerializedName("17")
    CIVIL_SERVICE("civil service", 400, "src/main/resources/Image/Game/Tech/civil_service.png"),
    @SerializedName("18")
    CURRENCY("currency", 250, "src/main/resources/Image/Game/Tech/currency.png"),
    @SerializedName("19")
    EDUCATION("education", 440, "src/main/resources/Image/Game/Tech/education.png"),
    @SerializedName("20")
    ENGINEERING("engineering", 250, "src/main/resources/Image/Game/Tech/engineering.png"),
    @SerializedName("21")
    MACHINERY("machinery", 440, "src/main/resources/Image/Game/Tech/machinery.png"),
    @SerializedName("22")
    METAL_CASTING("metal casting", 240, "src/main/resources/Image/Game/Tech/metal_casting.png"),
    @SerializedName("23")
    PHYSICS("physics", 440, "src/main/resources/Image/Game/Tech/physics.png"),
    @SerializedName("24")
    STEEL("steel", 440, "src/main/resources/Image/Game/Tech/steel.png"),
    @SerializedName("25")
    THEOLOGY("theology", 250, "src/main/resources/Image/Game/Tech/theology.png"),
    @SerializedName("26")
    ACOUSTICS("acoustics", 650, "src/main/resources/Image/Game/Tech/acoustics.png"),
    @SerializedName("27")
    ARCHAEOLOGY("archaeology", 1300, "src/main/resources/Image/Game/Tech/archaeology.png"),
    @SerializedName("28")
    BANKING("banking", 650, "src/main/resources/Image/Game/Tech/banking.png"),
    @SerializedName("29")
    CHEMISTRY("chemistry", 900, "src/main/resources/Image/Game/Tech/chemistry.png"),
    @SerializedName("30")
    ECONOMICS("economics", 900, "src/main/resources/Image/Game/Tech/economics.png"),
    @SerializedName("31")
    FERTILIZER("fertilizer", 1300, "src/main/resources/Image/Game/Tech/fertilizer.png"),
    @SerializedName("32")
    GUN_POWDER("gunpowder", 680, "src/main/resources/Image/Game/Tech/gunpowder.png"),
    @SerializedName("33")
    METALLURGY("metallurgy", 900, "src/main/resources/Image/Game/Tech/metallurgy.png"),
    @SerializedName("34")
    MILITARY_SCIENCE("military science", 1300, "src/main/resources/Image/Game/Tech/military_science.png"),
    @SerializedName("35")
    PRINTING_PRESS("printing press", 650, "src/main/resources/Image/Game/Tech/printing_press.png"),
    @SerializedName("36")
    RIFLING("rifling", 1425, "src/main/resources/Image/Game/Tech/rifling.png"),
    @SerializedName("37")
    SCIENTIFIC_THEORY("scientific theory", 1300, "src/main/resources/Image/Game/Tech/scientific_theory.png"),
    @SerializedName("38")
    BIOLOGY("biology", 1680, "src/main/resources/Image/Game/Tech/biology.png"),
    @SerializedName("39")
    COMBUSTION("combustion", 2200, "src/main/resources/Image/Game/Tech/combustion.png"),
    @SerializedName("40")
    DYNAMITE("dynamite", 1900, "src/main/resources/Image/Game/Tech/dynamite.png"),
    @SerializedName("41")
    ELECTRICITY("electricity", 1900, "src/main/resources/Image/Game/Tech/electricity.png"),
    @SerializedName("42")
    RADIO("radio", 2200, "src/main/resources/Image/Game/Tech/radio.png"),
    @SerializedName("43")
    RAILROAD("railroad", 1900, "src/main/resources/Image/Game/Tech/railroad.png"),
    @SerializedName("44")
    REPLACEABLE_PARTS("replaceable parts", 1900, "src/main/resources/Image/Game/Tech/replaceable_parts.png"),
    @SerializedName("45")
    STEAM_POWER("steam power", 1680, "src/main/resources/Image/Game/Tech/steam_power.png"),
    @SerializedName("46")
    TELEGRAPH("telegraph", 2200, "src/main/resources/Image/Game/Tech/telegraph.png");


    private final String name;
    private final int cost;
    private final String src;

    TechEnum(String name, int cost, String src) {
        this.name = name;
        this.cost = cost;
        this.src = src;
    }

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    public String getSrc() {
        return src;
    }
}