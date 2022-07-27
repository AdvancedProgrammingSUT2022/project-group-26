package com.example.project.models.Units;

import com.example.project.App;
import com.example.project.models.Resource.TileResourceEnum;
import com.example.project.models.Technology.TechEnum;
import com.google.gson.annotations.SerializedName;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

public enum UnitNameEnum {
    ARCHER("archer", 70, UnitTypeEnum.ARCHERY, 4, 6, 2, 2D, null, TechEnum.ARCHERY),
    CHARIOT_ARCHER("chariot archer", 60, UnitTypeEnum.MOUNTED, 3, 6, 2, 4D, TileResourceEnum.HORSE, TechEnum.THE_WHEEL),
    SCOUT("scout", 25, UnitTypeEnum.RECON, 4, null, null, 2D, null, null),
    SETTLER("settler", 89, UnitTypeEnum.CIVILIAN, null, null, null, 2D, null, null),
    SPEARMAN("spearman", 50, UnitTypeEnum.MELEE, 7, null, null, 2D, null, TechEnum.BRONZE_WORKING),
    WARRIOR("warrior", 40, UnitTypeEnum.MELEE, 6, null, null, 2D, null, null),
    WORKER("worker", 70, UnitTypeEnum.CIVILIAN, null, null, null, 2D, null, null),
    CATAPULT("catapult", 100, UnitTypeEnum.SIEGE, 4, 14, 2, 2D, TileResourceEnum.IRON, TechEnum.MATHEMATICS),
    HORSEMAN("horseman", 80, UnitTypeEnum.MOUNTED, 12, null, null, 4D, TileResourceEnum.HORSE, TechEnum.HORSEBACK_RIDING),
    SWORDSMAN("swordsman", 80, UnitTypeEnum.MELEE, 11, null, null, 2D, TileResourceEnum.IRON, TechEnum.IRON_WORKING),

    CROSSBOWMAN("crossbowman", 120, UnitTypeEnum.ARCHERY, 6, 12, 2, 2D, null, TechEnum.MACHINERY),

    KNIGHT("knight", 150, UnitTypeEnum.MOUNTED, 18, null, null, 3D, TileResourceEnum.HORSE, TechEnum.CHIVALRY),

    LONG_SWORDS_MAN("long swords man", 150, UnitTypeEnum.MELEE, 18, null, null, 3D, TileResourceEnum.IRON, TechEnum.STEEL),

    PIKE_MAN("pike man", 100, UnitTypeEnum.MELEE, 10, null, null, 2D, null, TechEnum.CIVIL_SERVICE),

    TREBUCHET("trebuchet", 170, UnitTypeEnum.SIEGE, 6, 20, 2, 2D, TileResourceEnum.IRON, TechEnum.PHYSICS),

    CANON("canon", 250, UnitTypeEnum.SIEGE, 10, 26, 2, 2D, null, TechEnum.CHEMISTRY),

    CAVALRY("cavalry", 260, UnitTypeEnum.MOUNTED, 25, null, null, 3D, TileResourceEnum.HORSE, TechEnum.MILITARY_SCIENCE),

    LANCER("lancer", 220, UnitTypeEnum.MOUNTED, 22, null, null, 4D, TileResourceEnum.HORSE, TechEnum.METALLURGY),

    MUSKET_MAN("musket man", 120, UnitTypeEnum.GUNPOWDER, 16, null, null, 2D, null, TechEnum.GUN_POWDER),

    RIFLEMAN("rifleman", 200, UnitTypeEnum.GUNPOWDER, 25, null, null, 2D, null, TechEnum.RIFLING),

    ANTITANK_GUN("anti-tank gun", 300, UnitTypeEnum.GUNPOWDER, 32, null, null, 2D, null, TechEnum.REPLACEABLE_PARTS),

    ARTILLERY("artillery", 420, UnitTypeEnum.SIEGE, 16, 32, 3, 2D, null, TechEnum.DYNAMITE),

    INFANTRY("infantry", 300, UnitTypeEnum.GUNPOWDER, 36, null, null, 2D, null, TechEnum.REPLACEABLE_PARTS),

    PANZER("panzer", 450, UnitTypeEnum.ARMORED, 60, null, null, 5D, null, TechEnum.COMBUSTION),

    TANK("tank", 450, UnitTypeEnum.ARMORED, 50, null, null, 4D, null, TechEnum.COMBUSTION);

    private final String name;
    private final int cost;
    private final UnitTypeEnum combatType;
    private final Integer combatStrength;
    private final Integer rangedCombatStrength;
    private final Integer range;
    private final Double movement;
    private final TileResourceEnum resourcesRequired;
    private final TechEnum technologyRequired;

    UnitNameEnum(String name, int cost, UnitTypeEnum combatType, Integer combatStrength, Integer rangedCombatStrength, Integer range, Double movement, TileResourceEnum resourcesRequired, TechEnum technologyRequired) {
        this.name = name;
        this.cost = cost;
        this.combatType = combatType;
        this.combatStrength = combatStrength;
        this.rangedCombatStrength = rangedCombatStrength;
        this.range = range;
        this.movement = movement;
        this.resourcesRequired = resourcesRequired;
        this.technologyRequired = technologyRequired;
    }

    public String getName() {
        return name;
    }

    public int getCost() {
        return cost;
    }

    public Integer getCombatStrength() {
        return combatStrength;
    }

    public Integer getRangedCombatStrength() {
        return rangedCombatStrength;
    }

    public Integer getRange() {
        return range;
    }

    public Double getMovement() {
        return movement;
    }

    public TileResourceEnum getResourcesRequired() {
        return resourcesRequired;
    }

    public TechEnum getTechnologyRequired() {
        return technologyRequired;
    }

    public UnitTypeEnum getCombatType() {
        return combatType;
    }

    public static UnitNameEnum valueOfLabel(String label) {
        for (UnitNameEnum e : values()) {
            if (e.name.equals(label)) {
                return e;
            }
        }
        return null;
    }

}