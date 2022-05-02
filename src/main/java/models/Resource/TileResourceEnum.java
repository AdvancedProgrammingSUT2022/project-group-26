package models.Resource;

import com.google.gson.annotations.SerializedName;

public enum TileResourceEnum {

    @SerializedName("0")
    BANANA("banana", 1, 0, 0),
    @SerializedName("1")
    COW("cow", 1, 0, 0),
    @SerializedName("2")
    GAZELLE("gazelle", 1, 0, 0),
    @SerializedName("3")
    SHEEP("sheep", 1, 0, 0),
    @SerializedName("4")
    WHEAT("wheat", 1, 0, 0),
    @SerializedName("5")
    COAL("coal", 0, 1, 0),
    @SerializedName("6")
    HORSE("horse", 0, 1, 0),
    @SerializedName("7")
    IRON("iron", 0, 1, 0),
    @SerializedName("8")
    COTTON("cotton", 0, 0, 2),
    @SerializedName("9")
    FUR("fur", 0, 0, 2),
    @SerializedName("10")
    GEM_STONES("gemStones", 0, 0, 2),
    @SerializedName("11")
    PAINT("paint",0, 0, 2),
    @SerializedName("12")
    GOLD("gold", 0, 0, 3),
    @SerializedName("13")
    INCENSE("incense", 0, 0, 2),
    @SerializedName("14")
    TUSK("tusk", 0, 0, 2),
    @SerializedName("15")
    MARBLE("marble", 0, 0, 2),
    @SerializedName("16")
    SILK("silk", 0, 0, 2),
    @SerializedName("17")
    SILVER("silver", 0, 0, 2),
    @SerializedName("18")
    SUGAR("sugar", 0, 0, 2);

    private final String name;
    private final int food;
    private final int production;
    private final int gold;

    TileResourceEnum(String name, int food, int production, int gold) {
        this.name = name;
        this.food = food;
        this.production = production;
        this.gold = gold;
    }

    public String getName() {
        return name;
    }

    public int getFood() {
        return food;
    }

    public int getProduction() {
        return production;
    }

    public int getGold() {
        return gold;
    }
}
