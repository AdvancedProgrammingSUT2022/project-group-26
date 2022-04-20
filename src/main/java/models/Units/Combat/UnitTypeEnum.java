package models.Units.Combat;

public enum UnitTypeEnum {
    archery("archery"),
    mounted("mounted"),
    recon("recon"),
    civilian("civilian"),
    melee("melee"),
    siege("siege"),
    gunpowder("gunpowder"),
    armored("armored");

   private final String name;

    UnitTypeEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}