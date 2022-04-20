package controllers.GameControllers;

import controllers.Output;

import java.util.regex.Matcher;

public class GameMenuCommandController {
    public Output showMap(Matcher matcher) {
        int iCoordinate = Integer.parseInt(matcher.group("iCoordinate"));
        int jCoordinate = Integer.parseInt(matcher.group("jCoordinate"));
        if (!isValidCoordinate(iCoordinate, jCoordinate))
            return Output.invalidCoordinate;
        return null;
    }

    private boolean isValidCoordinate(int iCoordinate, int jCoordinate) {
        if (iCoordinate < 0 || jCoordinate < 0) return false;
        if (iCoordinate > 27 || jCoordinate > 24) return false;
        return true;
    }
}
