package models;

import com.example.project.models.Improvement.TileImprovement;
import com.example.project.models.Improvement.TileImprovementEnum;
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
