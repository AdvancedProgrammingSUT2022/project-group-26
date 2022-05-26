package models;

import com.example.project.models.Feature.TileFeature;
import com.example.project.models.Feature.TileFeatureEnum;
import com.example.project.models.Resource.TileResourceEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class TileFeatureTest {
    private TileFeature tileFeature;

    @BeforeEach
    private void setUp(){
        tileFeature = new TileFeature(TileFeatureEnum.FOREST);
    }

    @Test
    public void cloneTest(){
        TileFeature clonedTileFeature = tileFeature.clone();
        Assertions.assertEquals(tileFeature.getFeatureName(), clonedTileFeature.getFeatureName());
    }

    @Test
    public void resourceTest(){
        ArrayList<TileResourceEnum> resources = new ArrayList<>();
        tileFeature.setResources(resources);
        ArrayList<TileResourceEnum> result = tileFeature.getResources();
        Assertions.assertEquals(resources, result);
    }
}
