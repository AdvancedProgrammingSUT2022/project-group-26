package com.example.project.models;

import com.example.project.models.Building.Building;
import com.example.project.models.Building.BuildingEnum;
import com.example.project.models.Improvement.TileImprovement;
import com.example.project.models.Improvement.TileImprovementEnum;
import com.example.project.models.Resource.TileResource;
import com.example.project.models.Resource.TileResourceEnum;
import com.example.project.models.Technology.Tech;
import com.example.project.models.Technology.TechEnum;
import com.example.project.models.Tile.Tile;
import com.example.project.models.Tile.TileModeEnum;
import com.example.project.models.Units.Combat.CombatUnit;
import com.example.project.models.Units.Nonecombat.BuilderUnit;
import com.example.project.models.Units.Nonecombat.NoneCombatUnit;
import com.example.project.models.Units.Unit;
import com.example.project.models.Units.UnitNameEnum;

import java.util.ArrayList;

public class Player {
    private User user;// ok
    private int science; // ok
    private GameMap gameMap; // ok
    private ArrayList<Tech> fullyResearchedTechs; // ok
    private ArrayList<TileResource> availableResources; // ok
    private ArrayList<Unit> units; // ok
    private ArrayList<City> cities; // ok
    private ArrayList<String> notifications; // ok
    private ArrayList<String> unseenNotifications; // ok
    private ArrayList<Tech> researchedTechs; // ok
    private Tech techInResearch; // ok
    private City mainCapital; // ok
    private int boughtTilesNumber;// ok
    private int roadAmount = 0;// ok
    private ArrayList<Player> metPlayers = new ArrayList<>();// ok
    private ArrayList<Player> playersInWar = new ArrayList<>();// ok
    private ArrayList<Player> playersInPeace = new ArrayList<>();// ok

    private final ArrayList<Tile> ruinTileSeen = new ArrayList<>();// ok

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

    public static int findCombatUnitOwner(ArrayList<Player> players, CombatUnit unit) {
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).units.contains(unit))
                return i;
        }
        return -1;
    }

    public static int findNoncombatUnits(ArrayList<Player> players, NoneCombatUnit unit) {
        for (int i = 0; i < players.size(); i++) {
            if (players.get(i).units.contains(unit))
                return i;
        }
        return -1;
    }

    public void updateMap(GameMap mainGameMap) {
        boolean found = false;
        for (Unit unit : this.units) {
            found = true;
            ArrayList<Tile> inSightTiles = mainGameMap.getUnitInSightTiles(unit.getPosition());
            for (Tile inSightTile : inSightTiles) {
                this.getGameMap().getMap()[mainGameMap.getIndexI(inSightTile)][mainGameMap.getIndexJ(inSightTile)]
                        = inSightTile.clone();
            }
        }
        for (City city : this.cities) {
            found = true;
            ArrayList<Tile> allInSightTiles = new ArrayList<>();
            for (int k = 0; k < city.getTiles().size(); k++) {
                ArrayList<Tile> inSightTiles = mainGameMap.getCityInSightTiles(city.getTiles().get(k));
                for (Tile tile : inSightTiles)
                    if (!allInSightTiles.contains(tile))
                        allInSightTiles.add(tile);
            }
            for (Tile inSightTile : allInSightTiles) {
                this.getGameMap().getMap()[mainGameMap.getIndexI(inSightTile)][mainGameMap.getIndexJ(inSightTile)]
                        = inSightTile.clone();
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
        for (Unit unit : this.units) {
            ArrayList<Tile> inSightTiles = this.gameMap.getUnitInSightTiles((
                    GameMap.getCorrespondingTile(unit.getPosition(), mainGameMap, this.gameMap)));
            if (inSightTiles.contains(tile))
                return true;
        }

        for (City city : this.cities) {
            for (int k = 0; k < city.getTiles().size(); k++) {
                ArrayList<Tile> inSightTiles = this.gameMap.getCityInSightTiles(
                        GameMap.getCorrespondingTile(city.getTiles().get(k), mainGameMap, this.gameMap));
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
        buildingScience();
        buildingHappiness();
        updateTechs();
    }

    private void buildingHappiness() {
        for (City city : getCities()) {
            if (city.containsBuilding(BuildingEnum.BURIAL_TOMB)) Happiness.addPlayerHappiness(this, 2);
            if (city.containsBuilding(BuildingEnum.CIRCUS)) Happiness.addPlayerHappiness(this, 3);
            if (city.containsBuilding(BuildingEnum.COLOSSEUM)) Happiness.addPlayerHappiness(this, 4);
            if (city.containsBuilding(BuildingEnum.COURTHOUSE) && Happiness.getPlayerHappiness(this) < 0)
                Happiness.setHappiness(this, 0);
            if (city.containsBuilding(BuildingEnum.SATRAP_COURT)) Happiness.addPlayerHappiness(this, 2);
            if (city.containsBuilding(BuildingEnum.THEATER)) Happiness.addPlayerHappiness(this, 4);

        }
    }

    private void buildingScience() {
        for (City city : getCities()) {
            if (city.containsBuilding(BuildingEnum.LIBRARY))
                setScience(getScience() + city.getMaxPopulation() / 2);
            if (city.containsBuilding(BuildingEnum.UNIVERSITY)) {
                int count = 0;
                for (Tile underWorkTile : city.getUnderWorkTiles()) {
                    if (underWorkTile.getMode().getTileName() == TileModeEnum.GRASSLAND)
                        count++;
                }
                setScience(getScience() + 2 * count);
            }
        }
    }

    private void workerBuildForPlayer() {
        for (Unit unit : getUnits()) {
            if (!(unit instanceof BuilderUnit)) continue;
            String save = ((BuilderUnit) unit).build();
            if (save == null) continue;
            if (save.equals("remove feature")) unit.getPosition().setFeature(null);
            else if (save.equals("create road")) unit.getPosition().setHasRoad(true);
            else if (save.equals("repair improvement")) unit.getPosition().getImprovement().setIsBroken(false);
            else {
                String[] split = save.split(" ");
                if (split[0].equals("improve")) {
                    TileImprovementEnum tempEnum = TileImprovementEnum.valueOfLabel(split[1]);
                    if (tempEnum != null) unit.getPosition().setImprovement(new TileImprovement(tempEnum));
                    if (unit.getPosition().getResource() != null && unit.getPosition().getResource().getImprovement() == tempEnum)
                        if (unit.getPosition().getResource().isALuxuryResource()) {
                            Happiness.addPlayerHappiness(this, 4);
                        }
                    if (unit.getPosition().getResource() != null)
                        this.getAvailableResources().add(unit.getPosition().getResource().clone());
                }
                if (split[0].equals("repair")) {
                    for (City city : unit.getPlayer().getCities()) {
                        if (city.getCenter() == unit.getPosition()) {
                            for (Building building : city.getBuildings()) {
                                if (building.getName().equals(split[1])) {
                                    building.setBroken(false);
                                }
                            }
                        }
                    }
                }
            }
        }

    }

    public void cityBuildForPlayer() {
        Object save;
        for (City city : getCities()) {
            if ((save = city.build()) != null) {
                if (save instanceof Unit) {
                    if (((Unit) save).isACombatUnit()) {
                        CombatUnit combatUnit = new CombatUnit(city.getCenter(), ((Unit) save).getUnitNameEnum(), this);
                        this.getUnits().add(combatUnit);
                        combatUnit.setPlayer(this);
                        combatUnit.setPosition(city.getCenter());
                        city.getCenter().setCombatUnits(combatUnit);
                        getUnseenNotifications().add(combatUnit.getUnitNameEnum().getName() + " unit built");
                    } else {
                        NoneCombatUnit noneCombatUnit = new NoneCombatUnit(city.getCenter(), ((Unit) save).getUnitNameEnum(), this);
                        this.getUnits().add(noneCombatUnit);
                        noneCombatUnit.setPlayer(this);
                        noneCombatUnit.setPosition(city.getCenter());
                        city.getCenter().setNoneCombatUnits(noneCombatUnit);
                        getUnseenNotifications().add(noneCombatUnit.getUnitNameEnum().getName() + " unit built");
                    }
                }
                if (save instanceof Building) {
                    city.addBuilding((Building) save);
                    getUnseenNotifications().add(((Building) save).getName() + " building built");
                }
            }
        }
    }

    private void unitsSetup() {
        for (Unit unit : getUnits()) {
            unit.resetMovement();
            if (unit instanceof CombatUnit) ((CombatUnit) unit).setCanAttack(true);
            if (unit instanceof CombatUnit && ((CombatUnit) unit).isFortified())
                ((CombatUnit) unit).heal();
            if (unit instanceof CombatUnit && unit.isAlert() && inZoneOfControl(gameMap, unit.getPosition())) {
                unit.setAlert(false);
                unit.setSleeping(false);
            }
        }
    }

    private boolean inZoneOfControl(GameMap gameMap, Tile tile) {
        Tile temp;
        int indexI = gameMap.getIndexI(tile);
        int indexJ = gameMap.getIndexJ(tile);
        for (int i = indexI - 2; i < indexI + 3; i++) {
            for (int j = indexJ - 2; j < indexJ + 3; j++) {
                if ((i == -2 && j == 2)
                        || (i == -2 && j == 1)
                        || (i == -2 && j == -1)
                        || (i == -2 && j == -2)
                        || (i == -1 && j == 2)
                        || (i == -1 && j == -2)
                        || (i == 0 && j == 0))
                    continue;
                if ((temp = gameMap.getTile(i, j)) == null) continue; //test if it's a valid tile
                if (temp.getCombatUnits() != null && tile.getCombatUnits() != null
                        && temp.getCombatUnits().getPlayer() != tile.getCombatUnits().getPlayer())
                    return true;
            }
        }
        return false;
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
                        city.setGarrison((CombatUnit) unit);
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
            if (unit instanceof CombatUnit)
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

    public ArrayList<UnitNameEnum> getProducibleUnits() {
        ArrayList<UnitNameEnum> res = new ArrayList<>();
        for (UnitNameEnum unit : UnitNameEnum.values()) {
            if (unit.getTechnologyRequired() == null) res.add(unit);
            else if (getFullyResearchedTechByEnum(unit.getTechnologyRequired()) != null) res.add(unit);
        }
        return res;
    }

    public ArrayList<BuildingEnum> getProducibleBuildings(City city) {
        ArrayList<BuildingEnum> res = new ArrayList<>();
        for (BuildingEnum building : BuildingEnum.values()) {
            if (!city.getBuildingEnums().contains(building))
                if (building.getTechEnum() == null) res.add(building);
                else if (getResearchedTechByEnum(building.getTechEnum()) != null) res.add(building);
        }
        return res;
    }

    public City getCityByTile(Tile tile) {
        for (int i = 0; i < cities.size(); i++) {
            if (cities.get(i).getTiles().contains(tile))
                return cities.get(i);
        }
        return null;
    }

    public ArrayList<Player> getMetPlayers() {
        return metPlayers;
    }

    public void setMetPlayers(ArrayList<Player> metPlayers) {
        this.metPlayers = metPlayers;
    }

    public void addAPlayerMet(Player player) {
        if (player != this && !metPlayers.contains(player))
            this.metPlayers.add(player);
    }

    public ArrayList<Player> getPlayersInWar() {
        return playersInWar;
    }

    public void setPlayersInWar(ArrayList<Player> playersInWar) {
        this.playersInWar = playersInWar;
    }

    public void addPLayerInWar(Player player) {
        if (!playersInWar.contains(player)) {
            this.playersInWar.add(player);
            this.playersInPeace.remove(player);
            player.playersInPeace.remove(this);
            if (!player.playersInWar.contains(this)) {
                player.playersInWar.add(this);
            }
        }
    }

    public ArrayList<Player> getPlayersInPeace() {
        return playersInPeace;
    }

    public void setPlayersInPeace(ArrayList<Player> playersInPeace) {
        this.playersInPeace = playersInPeace;
    }

    public void addPlayerInPeace(Player player) {
        if (!playersInPeace.contains(player)) {
            this.playersInWar.remove(player);
            this.playersInPeace.add(player);
            player.playersInWar.remove(this);
            if (!player.playersInPeace.contains(this))
                player.playersInPeace.add(this);
        }
    }

    public ArrayList<Tile> getRuinTileSeen() {
        return ruinTileSeen;
    }

    public void addRuinTileSeen(Tile tile) {
        this.ruinTileSeen.add(tile);
    }

    public void notificationSeen(String notification) {
        if (!notifications.contains(notification))
            notifications.add(notification);
        unseenNotifications.remove(notification);
    }

    public void seenAllNotifications() {
        for (int i = unseenNotifications.size() - 1; i >= 0; i--)
            notificationSeen(unseenNotifications.get(i));
    }

    public void removeCity(City city) {
        for (Tile tile : city.getTiles())
            tile.setBuilding(null);
        cities.remove(city);
    }

    public int calculateScore() {
        int sum = 0;
        for (City city : cities) sum += city.getTiles().size();
        sum += 2 * cities.size();
        sum += 2 * units.size();
        sum += 3 * fullyResearchedTechs.size();
        sum += 2 * researchedTechs.size();
        return sum;
    }

    public void updateScore() {
        int score = this.calculateScore();
        if (score > this.getUser().getHighScore())
            this.getUser().setHighScore(score);
    }
}