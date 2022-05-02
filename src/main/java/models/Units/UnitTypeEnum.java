package models.Units;

import com.google.gson.annotations.SerializedName;

public enum UnitTypeEnum {
    @SerializedName("0")
    ARCHERY("archery"),
    @SerializedName("1")
    MOUNTED("mounted"),
    @SerializedName("2")
    RECON("recon"),
    @SerializedName("3")
    CIVILIAN("civilian"),
    @SerializedName("4")
    MELEE("melee"),
    @SerializedName("5")
    SIEGE("siege"),
    @SerializedName("6")
    GUNPOWDER("gunpowder"),
    @SerializedName("7")
    ARMORED("armored");

   private final String name;

    UnitTypeEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}