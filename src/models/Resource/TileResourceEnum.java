package models.Resource;

public enum TileResourceEnum {

    banana("banana", 1, 0, 0),
    cow("cow", 1, 0, 0),
    gazelle("gazelle", 1, 0, 0),
    sheep("sheep", 1, 0, 0),
    wheat("wheat", 1, 0, 0),
    coal("coal", 0, 1, 0),
    horse("horse", 0, 1, 0),
    iron("iron", 0, 1, 0),
    cotton("cotton", 0, 0, 2),
    fur("fur", 0, 0, 2),
    gemStones("gemStones", 0, 0, 2),
    GOLD("gold", 0, 0, 3),
    INCENSE("incense", 0, 0, 2),
    tusk("tusk", 0, 0, 2),
    marble("marble", 0, 0, 2),
    silk("silk", 0, 0, 2),
    silver("silver", 0, 0, 2),
    sugar("sugar", 0, 0, 2);

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
