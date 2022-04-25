package models.Resource;

public enum TileResourceEnum {

    BANANA("banana", 1, 0, 0),
    COW("cow", 1, 0, 0),
    GAZELLE("gazelle", 1, 0, 0),
    SHEEP("sheep", 1, 0, 0),
    WHEAT("wheat", 1, 0, 0),
    COAL("coal", 0, 1, 0),
    HORSE("horse", 0, 1, 0),
    IRON("iron", 0, 1, 0),
    COTTON("cotton", 0, 0, 2),
    FUR("fur", 0, 0, 2),
    GEM_STONES("gemStones", 0, 0, 2),
    PAINT("paint",0, 0, 2),
    GOLD("gold", 0, 0, 3),
    INCENSE("incense", 0, 0, 2),
    TUSK("tusk", 0, 0, 2),
    MARBLE("marble", 0, 0, 2),
    SILK("silk", 0, 0, 2),
    SILVER("silver", 0, 0, 2),
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
