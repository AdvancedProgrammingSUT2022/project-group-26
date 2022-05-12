package models;

import models.Tile.Tile;
import models.Tile.TileMode;
import models.Tile.TileModeEnum;
import models.Units.Combat.CombatUnits;
import models.Units.Combat.MeleeUnit;
import models.Units.Combat.RangedUnit;
import models.Units.Combat.SiegeUnit;
import models.Units.Nonecombat.NoneCombatUnits;
import models.Units.Unit;
import models.Units.UnitNameEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class UnitTest {

    private Player player;
    private CombatUnits combatUnits;
    private NoneCombatUnits noneCombatUnits;
    private Unit unit;
    private Tile tile;

    @BeforeEach
    public void setUp() {
        tile = new Tile(new TileMode(TileModeEnum.GRASSLAND), null, null);
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

    @Test
    public void actionToStringTest() {
        if (combatUnits.isGarrison()) ;
        combatUnits.setGarrison(false);
        if (combatUnits.isSleeping()) ;
        combatUnits.setSleeping(false);
        if (combatUnits.isAlert() || combatUnits.isAlert()) ;
        combatUnits.setAlert(false);
        if (combatUnits.isFortified()) ;
        combatUnits.setFortified(false);
        String result = combatUnits.getActionToString();
        Assertions.assertEquals("no action!", result);
    }

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

    @Test
    public void siegeAndMeleeTest() {
        MeleeUnit meleeUnit = new MeleeUnit(tile, UnitNameEnum.SCOUT, player);
        meleeUnit = new MeleeUnit(combatUnits);
        SiegeUnit siegeUnit = new SiegeUnit(combatUnits);
        siegeUnit = new SiegeUnit(tile, UnitNameEnum.CATAPULT, player);
        float attack = siegeUnit.calculateAttack();
        Assertions.assertEquals(7, attack);
    }

    @Test
    public void siegeCloneTest() {
        SiegeUnit siegeUnit = new SiegeUnit(tile, UnitNameEnum.CATAPULT, player);
        siegeUnit.setSetUp(true);
        boolean result = siegeUnit.isSetUp() && siegeUnit.isASiege();
        Assertions.assertTrue(result);
    }

    @Test
    public void defenceTest() {
        combatUnits.setSleeping(true);
        combatUnits.setUnitNameEnum(UnitNameEnum.TANK);
        float ans = combatUnits.calculateDefence();
        Assertions.assertEquals(25, ans);
    }

    @Test
    public void rangedUnitTest(){
        RangedUnit rangedUnit = new RangedUnit(tile, UnitNameEnum.ARCHER, player);
        int result = rangedUnit.getRange();
        Assertions.assertEquals(UnitNameEnum.ARCHER.getRange(), result);
    }

    @Test
    public void combatUnitAlertTest(){
        combatUnits.setAlert(true);
        String result = combatUnits.getActionToString();
        Assertions.assertEquals("alerted!", result);
    }

    @Test
    public void combatUnitForfeitedTest(){
        combatUnits.setFortified(true);
        String result = combatUnits.getActionToString();
        Assertions.assertEquals("fortified!", result);
    }

    @Test
    public void combatUnitSleepTest(){
        combatUnits.setSleeping(true);
        String result = combatUnits.getActionToString();
        Assertions.assertEquals("sleeping!", result);
    }

    @Test
    public void combatUnitCanAttackTest(){
        combatUnits.setCanAttack(true);
        boolean result = combatUnits.CanAttack();
        Assertions.assertTrue(result);
    }

    @Test
    public void healthTest(){
        combatUnits.setHealth(10f);
        combatUnits.heal();
        float health = combatUnits.getHealth();
        Assertions.assertEquals(15f, health);
    }
}