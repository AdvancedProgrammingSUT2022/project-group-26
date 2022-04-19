package models.Building;

import models.Technology.TechEnum;

public enum BuildingEnum {
    Barracks("Barracks", 80, 1, TechEnum.bronzeWorking),
    Granary("Granary", 100, 1, TechEnum.pottery),
    Library("Library", 80, 1, TechEnum.writing),
    Monument("Monument", 60, 1, null),
    Walls("Walls", 100, 1, TechEnum.masonry),
    WaterMill("Water Mill", 120, 2, TechEnum.theWheel),
    Armory("Armory", 130, 3, TechEnum.ironWorking),
    BurialTomb("Burial Tomb", 120, 0, TechEnum.philosophy),
    Circus("Circus", 150, 3, TechEnum.horsebackRiding),
    Colosseum("Colosseum", 150, 3, TechEnum.construction),
    Courthouse("Courthouse", 200, 5, TechEnum.mathematics),
    Stable("Stable", 100, 1, TechEnum.horsebackRiding),
    Temple("Temple", 120, 2, TechEnum.philosophy),
    Castle("Castle", 200, 3, TechEnum.chivalry),
    Forge("Forge", 150, 2, TechEnum.metalCasting),
    Garden("Garden", 120, 2, TechEnum.theology),
    Market("Market", 120, 0, TechEnum.currency),
    Mint("Mint", 120, 0, TechEnum.currency),
    Monastery("Monastery", 120, 2, TechEnum.theology),
    University("University", 200, 3, TechEnum.education),
    Workshop("Workshop", 100, 2, TechEnum.metalCasting),
    Bank("Bank", 220, 0, TechEnum.banking),
    MilitaryAcademy("Military Academy", 350, 3, TechEnum.militaryScience),
    Museum("Museum", 350, 3, TechEnum.archaeology),
    OperaHouse("Opera House", 220, 3, TechEnum.acoustics),
    PublicSchool("Public School", 350, 3, TechEnum.scientificTheory),
    SatrapCourt("Satrap Court", 220, 0, TechEnum.banking),
    Theater("Theater", 300, 5, TechEnum.printingPress),
    Windmill("Windmill", 180, 2, TechEnum.economics),
    Arsenal("Arsenal", 350, 3, TechEnum.railroad),
    BroadcastTower("Broadcast Tower", 600, 3, TechEnum.radio),
    Factory("Factory", 300, 3, TechEnum.steamPower),
    Hospital("Hospital", 400, 2, TechEnum.biology),
    MilitaryBase("Military Base", 450, 4, TechEnum.telegraph),
    StockExchange("Stock Exchange", 650, 0, TechEnum.electricity);

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
