package models.Units;

import models.Resource.*;
import models.Technology.*;
import models.*;
import models.Tile.Tile;

public class Units {
    protected Tile position;
    protected int movementPoints;
    protected Player player;
    protected Gold gold;
    protected TileResource resourcesRequired;
    protected Tech techRequired;
    protected int productionPointsNeeded;
    protected boolean needsCommand;
    protected boolean isaWake;
    protected boolean isAlert;


    public Tile getPosition() {
        return this.position;
    }

    public void setPosition(Tile position) {
        this.position = position;
    }

    public int getMovementPoints() {
        return this.movementPoints;
    }

    public void setMovementPoints(int movementPoints) {
        this.movementPoints = movementPoints;
    }

    public Player getPlayer() {
        return this.player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Gold getGold() {
        return this.gold;
    }

    public void setGold(Gold gold) {
        this.gold = gold;
    }

    public TileResource getResourcesRequired() {
        return this.resourcesRequired;
    }

    public void setResourcesRequired(TileResource resourcesRequired) {
        this.resourcesRequired = resourcesRequired;
    }

    public Tech getTechRequired() {
        return this.techRequired;
    }

    public void setTechRequired(Tech techRequired) {
        this.techRequired = techRequired;
    }

    public int getProductionPointsNeeded() {
        return this.productionPointsNeeded;
    }

    public void setProductionPointsNeeded(int productionPointsNeeded) {
        this.productionPointsNeeded = productionPointsNeeded;
    }

    public boolean isNeedsCommand() {
        return this.needsCommand;
    }

    public boolean getNeedsCommand() {
        return this.needsCommand;
    }

    public void setNeedsCommand(boolean needsCommand) {
        this.needsCommand = needsCommand;
    }

    public boolean isIsaWake() {
        return this.isaWake;
    }

    public boolean getIsaWake() {
        return this.isaWake;
    }

    public void setIsaWake(boolean isaWake) {
        this.isaWake = isaWake;
    }

    public boolean isIsAlert() {
        return this.isAlert;
    }

    public boolean getIsAlert() {
        return this.isAlert;
    }

    public void setIsAlert(boolean isAlert) {
        this.isAlert = isAlert;
    }
    


}