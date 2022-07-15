package com.example.project.models;

import com.example.project.controllers.GameControllers.MovementController;
import com.example.project.models.Building.Building;
import com.example.project.models.Building.BuildingEnum;
import com.example.project.models.Improvement.TileImprovement;
import com.example.project.models.Improvement.TileImprovementEnum;
import com.example.project.models.Resource.TileResource;
import com.example.project.models.Resource.TileResourceEnum;
import com.example.project.models.Technology.Tech;
import com.example.project.models.Technology.TechEnum;
import com.example.project.models.Tile.Tile;
import com.example.project.models.Units.Combat.CombatUnits;
import com.example.project.models.Units.Nonecombat.BuilderUnit;
import com.example.project.models.Units.Nonecombat.NoneCombatUnits;
import com.example.project.models.Units.Unit;
import com.example.project.models.Units.UnitNameEnum;

import java.util.ArrayList;

public class Player {
    private User user;
    private int science;
    private GameMap gameMap;
    private ArrayList<Tech> fullyResearchedTechs;
    private ArrayList<TileResource> availableResources;
    private ArrayList<Unit> units;
    private ArrayList<City> cities;
    private ArrayList<String> notifications;
    private ArrayList<String> unseenNotifications;
    private ArrayList<Tech> researchedTechs;
    private Tech techInResearch;
    private City mainCapital;
    private int boughtTilesNumber;
    private int roadAmount = 0;

    public Player(User user) {
        setUser(user);
        setUnits(new ArrayList<>());
        setCities(new ArrayList<>());
        setResearchedTechs(new ArrayList<>());
        setFullyResearchedTechs(new ArrayList<>());
        setAvailableResources(new ArrayList<>());
        setNotifications(new ArrayList<>());
        setUnseenNotifications(new ArrayList<>());
        setGold(0);
    }

    public void setNotifications(ArrayList<String> notifications) {
        this.notifications = notifications;
    }

    public void setUnseenNotifications(ArrayList<String> unseenNotifications) {
        this.unseenNotifications = unseenNotifications;
    }

    public ArrayList<String> getNotifications() {
        return notifications;
    }

    public ArrayList<String> getUnseenNotifications() {
        return unseenNotifications;
    }

    public void addNotification(String notification) {
        this.notifications.add(notification);
    }

    public int getScience() {
        return science;
    }

    public void setScience(int science) {
        this.science = science;
    }

    public int getGold() {
        return Gold.getPlayerGold(this);
    }

    public void setGold(int amount) {
        Gold.setPlayerGold(this, amount);
    }

    public int getGoldProduction() {
        return Gold.getGoldProduction(this);
    }
    //TODO : add happiness methods

    public City getCurrentCapital() {
        return this.cities.get(0);
    }

    public City getMainCapital() {
        return mainCapital;
    }

    public void setMainCapital(City mainCapital) {
        this.mainCapital = mainCapital;
    }

    public Tech getTechInResearch() {
        return techInResearch;
    }

    public void setTechInResearch(Tech techInResearch) {
        this.techInResearch = techInResearch;
    }

    public ArrayList<Tech> getFullyResearchedTechs() {
        return fullyResearchedTechs;
    }

    public void setFullyResearchedTechs(ArrayList<Tech> fullyResearchedTechs) {
        this.fullyResearchedTechs = fullyResearchedTechs;
    }

    public ArrayList<City> getCities() {
        return cities;
    }

    public void setCities(ArrayList<City> cities) {
        this.cities = cities;
    }

    public ArrayList<Tech> getResearchedTechs() {
        return researchedTechs;
    }

    public void setResearchedTechs(ArrayList<Tech> researchedTechs) {
        this.researchedTechs = researchedTechs;
    }

    public GameMap getGameMap() {
        return gameMap;
    }

    public void setGameMap(GameMap gameMap) {
        this.gameMap = gameMap;
    }

    public User getUser() {
        return user;
    }

    public int getHappiness() {
        return Happiness.getPlayerHappiness(this);
    }

    public ArrayList<Unit> getUnits() {
        return units;
    }

    public void setUnits(ArrayList<Unit> units) {
        this.units = units;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Tech getResearchedTechByEnum(TechEnum name) {
        for (int i = 0; i < this.researchedTechs.size(); i++)
            if (researchedTechs.get(i).getTechName() == name) return researchedTechs.get(i);
        return null;
    }

    public Tech getFullyResearchedTechByEnum(TechEnum name) {
        for (int i = 0; i < this.fullyResearchedTechs.size(); i++)
            if (fullyResearchedTechs.get(i).getTechName() == name) return fullyResearchedTechs.get(i);
        return null;
    }

    public TileResource getAvailableResourcesByEnum(TileResourceEnum name) {
        for (int i = 0; i < getAvailableResources().size(); i++) {
            if (getAvailableResources().get(i).getResourceName() == name) return getAvailableResources().get(i);
        }
        return null;
    }

    public static int findCombatUnitOwner(ArrayList<Player> players, CombatUnits unit) {
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).units.contains(unit))
                return i;
        }
        return -1;
    }

    public static int findNoncombatUnits(ArrayList<Player> players, NoneCombatUnits unit) {
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).units.contains(unit))
                return i;
        }
        return -1;
    }

    public void updateMap(GameMap mainGameMap) {
        boolean found = false;
        for (int i = 0; i < this.units.size(); i++) {
            found = true;
            ArrayList<Tile> inSightTiles = mainGameMap.getUnitInSightTiles(this.units.get(i).getPosition());
            for (int j = 0; j < inSightTiles.size(); j++) {
                this.getGameMap().getMap()[mainGameMap.getIndexI(inSightTiles.get(j))][mainGameMap.getIndexJ(inSightTiles.get(j))]
                        = inSightTiles.get(j).clone();
            }
        }
        for (int i = 0; i < this.cities.size(); i++) {
            found = true;
            for (int k = 0; k < cities.get(i).getTiles().size(); k++) {
                ArrayList<Tile> inSightTiles = mainGameMap.getCityInSightTiles(cities.get(i).getTiles().get(k));
                for (int j = 0; j < inSightTiles.size(); j++) {
                    this.getGameMap().getMap()[mainGameMap.getIndexI(inSightTiles.get(j))][mainGameMap.getIndexJ(inSightTiles.get(j))]
                            = inSightTiles.get(j).clone();
                }
            }
        }
        if (!found) {
            for (int i = 0; i < gameMap.getMap().length; i++)
                for (int j = 0; j < gameMap.getMap()[i].length; j++)
                    if (gameMap.getTile(i, j) != null)
                        gameMap.getMap()[i][j] = mainGameMap.getTile(i, j).clone();
        }
    }

    public boolean isVisible(Tile tile, GameMap mainGameMap) {
        for (int i = 0; i < this.units.size(); i++) {
            ArrayList<Tile> inSightTiles = this.gameMap.getUnitInSightTiles((
                    GameMap.getCorrespondingTile(this.units.get(i).getPosition(), mainGameMap, this.gameMap)));
            if (inSightTiles.contains(tile))
                return true;
        }
        for (int i = 0; i < this.cities.size(); i++) {
            for (int k = 0; k < cities.get(i).getTiles().size(); k++) {
                ArrayList<Tile> inSightTiles = this.gameMap.getCityInSightTiles(
                        GameMap.getCorrespondingTile(cities.get(i).getTiles().get(k), mainGameMap, this.gameMap));
                if (inSightTiles.contains(tile))
                    return true;
            }
        }
        return false;
    }

    public boolean hasTile(Tile tile) {
        for (int i = 0; i < this.cities.size(); i++) {
            if (this.cities.get(i).getTiles().contains(tile))
                return true;
        }
        return false;
    }

    public static Player findTileOwner(Tile tile, ArrayList<Player> players) {
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).hasTile(tile))
                return players.get(i);
        }
        return null;
    }

    public ArrayList<Tech> getPossibleTechnology() {
        ArrayList<Tech> possibleTechs = new ArrayList<>();
        TechEnum[] allTechs = TechEnum.values();
        for (TechEnum tech : allTechs) {
            ArrayList<TechEnum> prerequisiteTechs = Tech.findPrerequisiteTechs(tech);
            boolean toAdd = true;
            for (TechEnum prerequisiteTech : prerequisiteTechs) {
                boolean hasTech = false;
                for (int i = 0; i < this.fullyResearchedTechs.size(); i++) {
                    if (fullyResearchedTechs.get(i).getTechName() == prerequisiteTech) {
                        hasTech = true;
                        break;
                    }
                }
                if (!hasTech) {
                    toAdd = false;
                    break;
                }
            }
            if (toAdd) {
                if (getFullyResearchedTechByEnum(tech) == null) {
                    if (getResearchedTechByEnum(tech) != null) possibleTechs.add(getResearchedTechByEnum(tech));
                    else possibleTechs.add(new Tech(tech));
                }
            }
        }
        return possibleTechs;
    }

    public int getTurnScience() {
        int science = 0;
        for (int i = 0; i < cities.size(); i++)
            science += cities.get(i).getMaxPopulation();
        if (cities.size() > 0)
            science += 3;
        return science;
    }

    public void updateTechs() {
        if (techInResearch != null) {
            if (techInResearch.getEarnedCost() + science < techInResearch.getCost()) {
                techInResearch.setEarnedCost(techInResearch.getEarnedCost() + science);
                science = 0;
            } else {
                unseenNotifications.add(techInResearch.getTechName().getName() + " researched completely");
                this.fullyResearchedTechs.add(techInResearch);
                this.researchedTechs.remove(techInResearch);
                science -= (techInResearch.getCost() - techInResearch.getEarnedCost());
                techInResearch.setEarnedCost(techInResearch.getCost());
                techInResearch = null;
            }
        }
    }

    public void endTurn(GameMap mainGameMap, boolean isCheatCode) {
        setGarrisons();
        workerBuildForPlayer();
        cityBuildForPlayer();
        handleHappiness();
        handleGold();
        outOfGold();
        handleFood();
        unitsSetup();
        updateMap(mainGameMap);
        setScience(getTurnScience() + science);
        updateTechs();
        if (!isCheatCode)
            addEndTurnNotifications();
    }

    private void addEndTurnNotifications() {
        if (techInResearch == null)
            this.unseenNotifications.add("why don't you start researching " + this.getUser().getUsername() + "?");

        for (int i = 0; i < this.getCities().size(); i++) {
            City city = this.getCities().get(i);
            if (city.getBeingBuild() == null)
                this.unseenNotifications.add("why don't you start building for " + city.getName()
                        + " " + this.getUser().getUsername() + "?");
        }
    }

    private void workerBuildForPlayer() {
        for (Unit unit : getUnits()) {
            if (!(unit instanceof BuilderUnit)) continue;
            String save = ((BuilderUnit) unit).build();
            if (save == null) continue;
            switch (save) {
                case "remove feature":
                    unit.getPosition().setFeature(null);
                    break;
                case "create road":
                    unit.getPosition().setHasRoad(true);
                    break;
                case "repair improvement":
                    unit.getPosition().getImprovement().setIsBroken(false);
                    break;
                default:
                    TileImprovementEnum tempEnum = TileImprovementEnum.valueOfLabel(save);
                    if (tempEnum != null) unit.getPosition().setImprovement(new TileImprovement(tempEnum));
                    if (unit.getPosition().getResource() != null && unit.getPosition().getResource().getImprovement() == tempEnum)
                        if (unit.getPosition().getResource().isALuxuryResource()) {
                            Happiness.addPlayerHappiness(this, 4);
                        }
                    if (unit.getPosition().getResource() != null)
                        this.getAvailableResources().add(unit.getPosition().getResource().clone());
            }
        }
    }

    public void cityBuildForPlayer() {
        Object save;
        for (City city : getCities()) {
            if ((save = city.build()) != null) {
                if (save instanceof Unit) {
                    if (((Unit) save).isACombatUnit()) {
                        CombatUnits combatUnit = new CombatUnits(city.getCenter(), ((Unit) save).getUnitNameEnum(), this);
                        this.getUnits().add(combatUnit);
                        combatUnit.setPlayer(this);
                        combatUnit.setPosition(city.getCenter());
                        city.getCenter().setCombatUnits(combatUnit);
                        getUnseenNotifications().add(combatUnit.getUnitNameEnum().getName() + " unit built");
                    } else {
                        NoneCombatUnits noneCombatUnit = new NoneCombatUnits(city.getCenter(), ((Unit) save).getUnitNameEnum(), this);
                        this.getUnits().add(noneCombatUnit);
                        noneCombatUnit.setPlayer(this);
                        noneCombatUnit.setPosition(city.getCenter());
                        city.getCenter().setNoneCombatUnits(noneCombatUnit);
                        getUnseenNotifications().add(noneCombatUnit.getUnitNameEnum().getName() + " unit built");
                    }
                }
                if (save instanceof Building) {
                    city.getBuildings().add((Building) save);
                    getUnseenNotifications().add(((Building) save).getName() + " building built");
                }
            }
        }
    }

    private void unitsSetup() {
        for (Unit unit : getUnits()) {
            unit.resetMovement();
            if (unit instanceof CombatUnits) ((CombatUnits) unit).setCanAttack(true);
            if (unit instanceof CombatUnits && ((CombatUnits) unit).isFortified())
                ((CombatUnits) unit).heal();
            if (unit instanceof CombatUnits && unit.isAlert() && MovementController.inZoneOfControl(gameMap, unit.getPosition())) {
                unit.setAlert(false);
                unit.setSleeping(false);
            }
        }
    }

    private void handleGold() {
        Gold.addGold(this, getGoldProduction());
        maintainBuilding();
        maintainUnits();
        maintainRoads();
    }

    private void maintainRoads() {
        Gold.removeGold(this, getRoadAmount() * 1);// TODO : اینجا یه مشت ثابت میخوام که نگفته !؟!
    }

    private void outOfGold() {
        if (Gold.getPlayerGold(this) < 0) {
            if (science >= Gold.getPlayerGold(this))
                Gold.addGold(this, science - Gold.getPlayerGold(this));
            else Gold.addGold(this, science);
        }
    }

    private void maintainUnits() {
        Gold.removeGold(this, getUnits().size() * 1);// TODO : اینجا یه مشت ثابت میخوام که نگفته !؟!
    }

    private void maintainBuilding() {
        for (City city : getCities()) {
            for (Building building : city.getBuildings()) {
                Gold.removeGold(this, building.getGoldCost());
            }
        }
    }

    public ArrayList<TileResource> getAvailableResources() {
        return availableResources;
    }

    public void setAvailableResources(ArrayList<TileResource> availableResources) {
        this.availableResources = availableResources;
    }

    public City getCityByName(String name) {
        for (int i = 0; i < cities.size(); i++) {
            if (cities.get(i).getName().equals(name))
                return cities.get(i);
        }
        return null;
    }

    public void startGame(int difficulty) {
        new Gold(this);
        new Happiness(this);
        Happiness.setHappiness(this, (3 - difficulty) * 20);
    }

    private void handleFood() {
        for (City city : getCities()) {
            Food.handleFoodOFCity(city);
        }
    }

    private void handleHappiness() {
        ArrayList<TileResourceEnum> luxuryRecourses = new ArrayList<>();
        for (City city : cities) {
            if (city.isAttached()) Happiness.setHappiness(this, Happiness.getPlayerHappiness(this) - 1);
            if (city.getMaxPopulation() > 10) Happiness.setHappiness(this, Happiness.getPlayerHappiness(this) - 1);
            for (Building building : city.getBuildings()) {
                building.handlePlayerHappiness(this);
            }
            for (Tile tile : city.getTiles()) {
                if (tile.getResource() != null && tile.getResource().getResourceName().getLuxury()
                        && !luxuryRecourses.contains(tile.getResource().getResourceName())) {
                    Happiness.setHappiness(this, Happiness.getPlayerHappiness(this) + 1);
                    luxuryRecourses.add(tile.getResource().getResourceName());
                }
            }
        }
        if (cities.size() > 5) Happiness.setHappiness(this, Happiness.getPlayerHappiness(this) - 2);
    }

    public boolean canBuyTile(Tile tile, GameMap mainGameMap, City city) {
        return city.getNeighborTiles(mainGameMap).contains(tile);
    }

    public int getBoughtTilesNumber() {
        return boughtTilesNumber;
    }

    public void setBoughtTilesNumber(int boughtTilesNumber) {
        this.boughtTilesNumber = boughtTilesNumber;
    }

    public void attachCity(City city, Player previousOwner) {
        previousOwner.getCities().remove(city);
        this.cities.add(city);
        city.setAttached(true);
    }

    public void setGarrisons() {
        for (Unit unit : this.units) {
            if (unit.isACombatUnit())
                for (City city : cities) {
                    if (unit.getPosition() == city.getCenter()) {
                        city.setGarrison((CombatUnits) unit);
                        city.setHealth(city.getHealth() + 20);
                    }
                }
        }
    }

    public int getRoadAmount() {
        return roadAmount;
    }

    public void setRoadAmount(int roadAmount) {
        this.roadAmount = roadAmount;
    }

    public ArrayList<TileResource> getStrategicResources() {
        ArrayList<TileResource> strategicResources = new ArrayList<>();
        for (City city : cities) {
            for (Tile tile : city.getTiles()) {
                if (tile.getResource() != null && tile.getResource().isStrategicResource()
                        && !strategicResources.contains(tile.getResource()))
                    strategicResources.add(tile.getResource());
            }
        }
        return strategicResources;
    }

    public ArrayList<Unit> getCombatUnits() {
        ArrayList<Unit> combatUnits = new ArrayList<>();
        for (Unit unit : units)
            if (unit instanceof CombatUnits)
                combatUnits.add(unit);
        return combatUnits;
    }

    public ArrayList<Tile> getTiles() {
        ArrayList<Tile> tiles = new ArrayList<>();
        for (City city : cities)
            for (Tile tile : city.getTiles())
                tiles.add(tile);
        return tiles;
    }

    public int getTotalPopulation() {
        int sum = 0;
        for (City city : cities)
            sum += city.getMaxPopulation();
        return sum;
    }

    public ArrayList<UnitNameEnum> getProduceAbleUnits() {
        ArrayList<UnitNameEnum> res = new ArrayList<>();
        for (UnitNameEnum unit : UnitNameEnum.values()) {
            if (getResearchedTechByEnum(unit.getTechnologyRequired()) != null) res.add(unit);
        }
        return res;
    }

    public ArrayList<BuildingEnum> getProduceAbleBuildings() {
        ArrayList<BuildingEnum> res = new ArrayList<>();
        for (BuildingEnum building : BuildingEnum.values()) {
            if (getResearchedTechByEnum(building.getTechEnum()) != null) res.add(building);
        }
        return res;
    }
}