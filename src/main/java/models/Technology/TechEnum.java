package models.Technology;

public enum TechEnum {
    AGRICULTURE("agriculture", 20),
    ANIMAL_HUSBANDRY("animal husbandry", 35),
    ARCHERY("archery", 35),
    BRONZE_WORKING("bronze working", 55),
    CALENDAR("calendar", 70),
    MASONRY("masonry", 55),
    MINING("mining", 35),
    POTTERY("pottery", 35),
    THE_WHEEL("the wheel", 55),
    TRAPPING("trapping", 55),
    WRITING("writing", 55),
    CONSTRUCTION("construction",100),
    HORSEBACK_RIDING("horseback riding",100),
    IRON_WORKING("iron working",150),
    MATHEMATICS("mathematics",100),
    PHILOSOPHY("philosophy",100),
    CHIVALRY("chivalry",440),
    CIVIL_SERVICE("civil service",400),
    CURRENCY("currency",250),
    EDUCATION("education",440),
    ENGINEERING("engineering",250),
    MACHINERY("machinery",440),
    METAL_CASTING("metal casting",240),
    PHYSICS("physics",440),
    STEEL("steel",440),
    THEOLOGY("theology",250),
    ACOUSTICS("acoustics",650),
    ARCHAEOLOGY("archaeology",1300),
    BANKING("banking",650),
    CHEMISTRY("chemistry",900),
    ECONOMICS("economics",900),
    FERTILIZER("fertilizer",1300),
    GUN_POWDER("gunpowder",680),
    METALLURGY("metallurgy",900),
    MILITARY_SCIENCE("military science",1300),
    PRINTING_PRESS("printing press",650),
    RIFLING("rifling",1425),
    SCIENTIFIC_THEORY("scientific theory",1300),
    BIOLOGY("biology",1680),
    COMBUSTION("combustion",2200),
    DYNAMITE("dynamite",1900),
    ELECTRICITY("electricity",1900),
    RADIO("radio",2200),
    RAILROAD("railroad",1900),
    REPLACEABLE_PARTS("replaceable parts",1900),
    STEAM_POWER("steam power",1680),
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