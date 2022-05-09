package models;

import models.Tile.Tile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RiverTest {
    private River river;
    private Tile firstTile;
    private Tile secondTile;
    private Tile thirdTile;

    @BeforeEach
    public void setUp(){
        firstTile = new Tile(null, null, null);
        secondTile = new Tile(null, null, null);
        thirdTile = new Tile(null, null, null);
        river = new River(firstTile, secondTile);
    }

    @Test
    public void hasRiverTest(){
        Assertions.assertTrue(River.hasRiver(firstTile, secondTile));
    }

    @Test
    public void reverseHasRiverTest(){
        Assertions.assertTrue(River.hasRiver(secondTile, firstTile));
    }

    @Test
    public void falseHasRiverTest(){
        Assertions.assertFalse(River.hasRiver(thirdTile, firstTile));
    }

    @Test
    public void secondHasRiverTest(){
        Assertions.assertTrue(River.hasRiver(firstTile));
    }

    @Test
    public void secondFalseHasRiverTest(){
        Assertions.assertFalse(River.hasRiver(thirdTile));
    }
}
