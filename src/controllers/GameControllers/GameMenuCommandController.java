package controllers.GameControllers;

import controllers.Output;
import org.jetbrains.annotations.NotNull;

import java.util.regex.Matcher;

public class GameMenuCommandController {
    public Output showMap(@NotNull Matcher matcher){
        int iCoordinate = Integer.parseInt(matcher.group("iCoordinate"));
        int jCoordinate = Integer.parseInt(matcher.group("jCoordinate"));
        if(!isValidCoordinate(iCoordinate, jCoordinate))
            return Output.invalidCoordinate;
        return null;
    }

    private boolean isValidCoordinate(int iCoordinate, int jCoordinate){
        if(iCoordinate < 0 || jCoordinate < 0)
            return false;
        return true;
        // TODO: add upper boundary for Coordinates
    }

}