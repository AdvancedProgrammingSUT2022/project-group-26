package models.Technology;

public enum TechEnum {
    agriculture("agriculture", 20),
    animalHusbandry("animal husbandry", 35),
    archery("archery", 35),
    bronzeWorking("bronze working", 55),
    calendar("calendar", 70),
    masonry("masonry", 55),
    mining("mining", 35),
    pottery("pottery", 35),
    theWheel("the wheel", 55),
    trapping("trapping", 55),
    writing("writing", 55),
    construction("construction",100),
    horsebackRiding("horseback riding",100),
    ironWorking("iron working",150),
    mathematics("mathematics",100),
    philosophy("philosophy",100),
    chivalry("chivalry",440),
    civilService("civil service",400),
    currency("currency",250),
    education("education",440),
    engineering("engineering",250),
    machinery("machinery",440),
    metalCasting("metal casting",240),
    physics("physics",440),
    steel("steel",440),
    theology("theology",250),
    acoustics("acoustics",650),
    archaeology("archaeology",1300),
    banking("banking",650),
    chemistry("chemistry",900),
    economics("economics",900),
    fertilizer("fertilizer",1300),
    gunpowder("gunpowder",680),
    metallurgy("metallurgy",900),
    militaryScience("military science",1300),
    printingPress("printing press",650),
    rifling("rifling",1425),
    scientificTheory("scientific theory",1300),
    biology("biology",1680),
    combustion("combustion",2200),
    dynamite("dynamite",1900),
    electricity("electricity",1900),
    radio("radio",2200),
    railroad("railroad",1900),
    replaceableParts("replaceable parts",1900),
    steamPower("steam power",1680),
    telegraph("telegraph",2200);


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
