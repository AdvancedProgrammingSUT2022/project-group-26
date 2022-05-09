package models.Units.Combat;

import models.Player;
import models.Tile.Tile;
import models.Units.UnitNameEnum;

public class RangedUnit extends CombatUnits {
    protected Integer range; // for melee units --> null
    protected Integer rangedCombatStrength; // for melee units --> null

    public RangedUnit(Tile position, UnitNameEnum unitNameEnum, Player player) {
        super(position, unitNameEnum, player);
        setRange(unitNameEnum.getRange());
        setRangedCombatStrength(unitNameEnum.getRangedCombatStrength());
    }

    public RangedUnit(CombatUnits combatUnits) {
        super(combatUnits);
    }

    public Integer getRange() {
        return range;
    }

    public void setRange(Integer range) {
        this.range = range;
    }

    public Integer getRangedCombatStrength() {
        return rangedCombatStrength;
    }

    public void setRangedCombatStrength(Integer rangedCombatStrength) {
        this.rangedCombatStrength = rangedCombatStrength;
    }

}
