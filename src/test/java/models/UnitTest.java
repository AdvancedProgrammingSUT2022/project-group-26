package models;

import models.Tile.Tile;
import models.Tile.TileMode;
import models.Tile.TileModeEnum;
import models.Units.Combat.CombatUnits;
import models.Units.Nonecombat.NoneCombatUnits;
import models.Units.Unit;
import models.Units.UnitNameEnum;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UnitTest {
    private Player player;
    private CombatUnits combatUnits;
    private NoneCombatUnits noneCombatUnits;
    private Unit unit;

    @BeforeEach
    public void setUp() {
        Tile tile = new Tile(new TileMode(TileModeEnum.GRASSLAND), null, null);
        player = new Player(new User("", "", ""));
        combatUnits = new CombatUnits(tile, UnitNameEnum.SCOUT, player);
        noneCombatUnits = new NoneCombatUnits(tile, UnitNameEnum.SETTLER, player);
        unit = new Unit(player, tile, UnitNameEnum.SETTLER);
    }

    @Test
    public void cloneTest() {
        Unit clonedUnit = unit.clone();
        Assertions.assertEquals(clonedUnit.getUnitNameEnum(), unit.getUnitNameEnum());
    }

    @Test
    public void isACivilianTest() {
        noneCombatUnits.getUnitTypeEnum().getName();
        boolean result = noneCombatUnits.isACivilian();
        Assertions.assertTrue(result);
    }

    @Test
    public void isACombatUnitTest() {
        boolean result = combatUnits.isACombatUnit();
        Assertions.assertTrue(result);
    }

    @Test
    public void isRangedTest() {
        boolean result = new CombatUnits(null, UnitNameEnum.ARCHER, null).isARangedCombatUnit();
        Assertions.assertTrue(result);
    }

    @Test
    public void isMeleeTest() {
        boolean result = new CombatUnits(null, UnitNameEnum.ARCHER, null).isAMeleeCombatUnit();
        Assertions.assertFalse(result);
    }

    @Test
    public void isSiegeTest() {
        boolean result = new CombatUnits(null, UnitNameEnum.CATAPULT, null).isASiege();
        Assertions.assertTrue(result);
    }

    @Test
    public void isWorkerTest() {
        boolean result = new CombatUnits(null, UnitNameEnum.WORKER, null).isAWorker();
        Assertions.assertTrue(result);
    }

    @Test
    public void resetMovementTest() {
        combatUnits.setMovement(20D);
        combatUnits.resetMovement();
        Assertions.assertEquals(UnitNameEnum.SCOUT.getMovement(), combatUnits.getMaxMovement());
    }

    @Test
    public void savedRouteTest() {
        unit.setSavedRoute(null);
        Assertions.assertEquals(null, unit.getSavedRoute());
    }

    @Test
    public void checkEnums() {
        boolean result = UnitNameEnum.SETTLER.getName().equals("settler")
                && UnitNameEnum.SETTLER.getCost() == 89
                && UnitNameEnum.SETTLER.getResourcesRequired() == null
                && UnitNameEnum.SETTLER.getTechnologyRequired() == null;

        Assertions.assertTrue(result);
    }

    @Test
    public void valueOfLabelTest() {
        UnitNameEnum result = UnitNameEnum.valueOfLabel("settler");
        Assertions.assertEquals(UnitNameEnum.SETTLER, result);
    }

//    @Test
//    public void actionToStringTest() {
//        if (combatUnits.isStillForATurn()) ;
//        combatUnits.setStillForATurn(false);
//        if (combatUnits.isGarrison()) ;
//        combatUnits.setGarrison(false);
//        if (combatUnits.isSleeping()) ;
//        combatUnits.setSleeping(false);
//        if (combatUnits.isIsAlert() || combatUnits.IsAlert()) ;
//        combatUnits.setAlert(false);
//        if (combatUnits.isFortified()) ;
//        combatUnits.setFortified(false);
//        String result = combatUnits.getActionToString();
//        Assertions.assertEquals("no action!", result);
//    }

    @Test
    public void diedTest() {
        combatUnits.died();
        Assertions.assertFalse(player.getUnits().contains(combatUnits));
    }

    @Test
    public void damageTest() {
        combatUnits.setHealth(10);
        combatUnits.takeDamage(10);
        Assertions.assertEquals(0, combatUnits.getHealth());
    }

    @Test
    public void xpTest() {
        combatUnits.giveXp();
        Assertions.assertEquals(5, combatUnits.getXP());
    }

    @Test
    public void attackCalculateTest() {
        combatUnits.setHealth(19);
        float attack = combatUnits.calculateAttack();
        Assertions.assertTrue(0.38D > attack && attack > 0.37D);
    }

    @Test
    public void healTest() {
        combatUnits.isFortified();
        if (!combatUnits.isFullyHealed()) ;
        combatUnits.heal();
        Assertions.assertEquals(20, combatUnits.getHealth());
    }

    @Test
    public void constructorTest() {
        CombatUnits secondCombatUnit = new CombatUnits(combatUnits);
        Assertions.assertEquals(combatUnits.getUnitNameEnum(), secondCombatUnit.getUnitNameEnum());
    }
}
