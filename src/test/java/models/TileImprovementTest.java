package models;

import models.Improvement.TileImprovement;
import models.Improvement.TileImprovementEnum;
import models.Tile.Tile;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class TileImprovementTest {
    private TileImprovement tileImprovement;

    @BeforeEach
    private void setUp(){
        tileImprovement = new TileImprovement(TileImprovementEnum.FARM);
    }
     @Test
    public void cloneTest(){
         TileImprovement clonedTileImprovement = tileImprovement.clone();
         tileImprovement.setIsBroken(false);
         ArrayList<Enum> whereCanBeFind = new ArrayList<>();
         whereCanBeFind = null;
         tileImprovement.setWhereCanBeFind(whereCanBeFind);
         Assertions.assertEquals(tileImprovement.getImprovementName(), clonedTileImprovement.getImprovementName());
     }
}
