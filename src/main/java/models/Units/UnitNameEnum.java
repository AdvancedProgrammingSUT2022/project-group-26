package models.Units;

import models.Resource.TileResourceEnum;
import models.Technology.TechEnum;

public enum UnitNameEnum {
    archer("archer", 70, UnitTypeEnum.archery, 4, 6, 2, 2D, null, TechEnum.archery),
    chariotArcher("chariot archer", 60, UnitTypeEnum.mounted, 3, 6, 2, 4D, TileResourceEnum.horse, TechEnum.theWheel),
    scout("scout", 25, UnitTypeEnum.recon, 4, null, null, 5D, null, null),
    settler("settler", 89, UnitTypeEnum.civilian, null, null, null, 2D, null, null),
    spearman("spearman", 50, UnitTypeEnum.melee, 7, null, null, 2D, null, TechEnum.bronzeWorking),
    warrior("warrior", 40, UnitTypeEnum.melee, 6, null, null, 2D, null, null),
    worker("worker", 70, UnitTypeEnum.civilian, null, null, null, 2D, null, null),
    catapult("catapult", 100, UnitTypeEnum.siege, 4, 14, 2, 2D, TileResourceEnum.iron, TechEnum.mathematics),
    horseman("horseman", 80, UnitTypeEnum.mounted, 12, null, null, 4D, TileResourceEnum.horse, TechEnum.horsebackRiding),
    swordsman("swordsman", 80, UnitTypeEnum.melee, 11, null, null, 2D, TileResourceEnum.iron, TechEnum.ironWorking),
    crossbowman("crossbowman", 120, UnitTypeEnum.archery, 6, 12, 2, 2D, null, TechEnum.machinery),
    knight("knight", 150, UnitTypeEnum.mounted, 18, null, null, 3D, TileResourceEnum.horse, TechEnum.chivalry),
    longswordsman("longswordsman", 150, UnitTypeEnum.melee, 18, null, null, 3D, TileResourceEnum.iron, TechEnum.steel),
    pikeman("pikeman", 100, UnitTypeEnum.melee, 10, null, null, 2D, null, TechEnum.civilService),
    trebuchet("trebuchet", 170, UnitTypeEnum.siege, 6, 20, 2, 2D, TileResourceEnum.iron, TechEnum.physics),
    canon("canon", 250, UnitTypeEnum.siege, 10, 26, 2, 2D, null, TechEnum.chemistry),
    cavalry("cavalry", 260, UnitTypeEnum.mounted, 25, null, null, 3D, TileResourceEnum.horse, TechEnum.militaryScience),
    lancer("lancer", 220, UnitTypeEnum.mounted, 22, null, null, 4D, TileResourceEnum.horse, TechEnum.metallurgy),
    musketman("musketman", 120, UnitTypeEnum.gunpowder, 16, null, null, 2D, null, TechEnum.gunpowder),
    rifleman("rifleman", 200, UnitTypeEnum.gunpowder, 25, null, null, 2D, null, TechEnum.rifling),
    antiTankGun("anti-tank gun", 300, UnitTypeEnum.gunpowder, 32, null, null, 2D, null, TechEnum.replaceableParts),
    artillery("artillery", 420, UnitTypeEnum.siege, 16, 32, 3, 2D, null, TechEnum.dynamite),
    infantry("infantry", 300, UnitTypeEnum.gunpowder, 36, null, null, 2D, null, TechEnum.replaceableParts),
    panzer("panzer", 450, UnitTypeEnum.armored, 60, null, null, 5D, null, TechEnum.combustion),
    tank("tank", 450, UnitTypeEnum.armored, 50, null, null, 4D, null, TechEnum.combustion);

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
}