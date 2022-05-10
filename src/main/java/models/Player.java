package models;

import java.util.ArrayList;

import models.Building.Building;
import models.Improvement.TileImprovement;
import models.Improvement.TileImprovementEnum;
import models.Resource.TileResource;
import models.Resource.TileResourceEnum;
import models.Technology.Tech;
import models.Technology.TechEnum;
import models.Tile.Tile;
import models.Units.Combat.CombatUnits;
import models.Units.Nonecombat.BuilderUnit;
import models.Units.Nonecombat.NoneCombatUnits;
import models.Units.Unit;

public class Player {
    private User user;
    private int science;
    private GameMap gameMap;
    private ArrayList<Tech> fullyResearchedTechs;
    private ArrayList<TileResource> availableResources;
    private ArrayList<Unit> units;
    private ArrayList<City> cities;
    private ArrayList<Tech> researchedTechs;
    private Tech techInResearch;
    private City mainCapital;
    private int boughtTilesNumber;

    public Player(User user) {
        setUser(user);
        setUnits(new ArrayList<>());
        setCities(new ArrayList<>());
        setResearchedTechs(new ArrayList<>());
        setFullyResearchedTechs(new ArrayList<>());
        setAvailableResources(new ArrayList<>());
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
        for (int i = 0; i < this.units.size(); i++) {
            ArrayList<Tile> inSightTiles = mainGameMap.getUnitInSightTiles(this.units.get(i).getPosition());
            for (int j = 0; j < inSightTiles.size(); j++) {
                this.getGameMap().getMap()[mainGameMap.getIndexI(inSightTiles.get(j))][mainGameMap.getIndexJ(inSightTiles.get(j))]
                        = inSightTiles.get(j).clone();
            }
        }
        for (int i = 0; i < this.cities.size(); i++) {
            for (int k = 0; k < cities.get(i).getTiles().size(); k++) {
                ArrayList<Tile> inSightTiles = mainGameMap.getCityInSightTiles(cities.get(i).getTiles().get(k));
                for (int j = 0; j < inSightTiles.size(); j++) {
                    this.getGameMap().getMap()[mainGameMap.getIndexI(inSightTiles.get(j))][mainGameMap.getIndexJ(inSightTiles.get(j))]
                            = inSightTiles.get(j).clone();
                }
            }
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
                this.fullyResearchedTechs.add(techInResearch);
                this.researchedTechs.remove(techInResearch);
                science -= (techInResearch.getCost() - techInResearch.getEarnedCost());
                techInResearch.setEarnedCost(techInResearch.getCost());
                techInResearch = null;
            }
        }
    }

    public void endTurn(GameMap mainGameMap) {
//        for (Unit unit : getUnits()) {
//            if (unit.getNeedsCommand()) return Output.UNIT_NEEDS_COMMAND();
//        }
//        for (City city : getCities()) {
//            if (city.getBeingBuild() == null) return Output.CITY_IS_DOING_NOTHING;
//        }
//        if (techInResearch==null) return Output.RESEARCH_SOMETHING;

        // start of the turn
        setGarrisons();
        workerBuildForPlayer();
        cityBuildForPlayer();
        handleHappiness();
        handleGold(); // needs handling for gold (-)
        outOfGold();
        handleFood();
        unitsSetup();
        updateMap(mainGameMap);
        setScience(getTurnScience() + science);
        updateTechs();
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
                    } else {
                        NoneCombatUnits noneCombatUnit = new NoneCombatUnits(city.getCenter(), ((Unit) save).getUnitNameEnum(), this);
                        this.getUnits().add(noneCombatUnit);
                        noneCombatUnit.setPlayer(this);
                        noneCombatUnit.setPosition(city.getCenter());
                        city.getCenter().setNoneCombatUnits(noneCombatUnit);
                    }
                }
                if (save instanceof Building) {
                    city.getBuildings().add((Building) save);
                }
            }
        }
    }

    private void unitsSetup() {
        for (Unit unit : getUnits()) {
            unit.resetMovement();
            if (unit instanceof CombatUnits && (((CombatUnits) unit).isSleeping() || ((CombatUnits) unit).isIsAlert()))
                ((CombatUnits) unit).heal();
            // TODO :movement for a turned command !?
            // باید برای بولین ها یونیت یجوری کنیم گه چنتا چیزو بفمیم
            // میمتونه اتک بده یا نه
            // میتونه را بره یا نه

            // چیزی نداشتیم برای خود به خود بیدار شدن بعد مکس شدن هلف ؟
//            if (unit.getIsAlert() && unit.isFullyHealed()) unit.setIsAlert(false);
        }
    }

    private void handleGold() {
        Gold.addGold(this, getGoldProduction());
        maintainBuilding();
        maintainUnits();
        // cost of road (kinda a building)
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
        //TODO: ali havaset bashe vaghti ye shahri ro as dast middi gold az dast midi
        //TODO: va agar ham ye shahro begiri gold migiri (agar inaro handle kardi in 2ta ro pak kon)
    }

    public void setGarrisons() {
        for (Unit unit : this.units) {
            if (unit.isACombatUnit())
                for (City city : cities) {
                    if (unit.getPosition() == city.getCenter()) {
                        city.setGarrison(unit);
                        city.setHealth(city.getHealth() + 20);
                    }
                }
        }
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
}