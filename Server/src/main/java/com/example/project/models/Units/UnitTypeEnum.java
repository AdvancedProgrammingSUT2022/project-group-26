package com.example.project.models.Units;


public enum UnitTypeEnum {
    ARCHERY("archery"),
    MOUNTED("mounted"),
    RECON("recon"),
    CIVILIAN("civilian"),
    MELEE("melee"),
    SIEGE("siege"),
    GUNPOWDER("gunpowder"),
    ARMORED("armored");

    private final String name;

    UnitTypeEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}