package models;

import java.util.ArrayList;
import java.util.Objects;

import models.Resource.TileResource;
import models.Technology.Tech;
import models.Technology.TechEnum;
import models.Tile.Tile;
import models.Units.Combat.CombatUnits;
import models.Units.Nonecombat.NoneCombatUnits;
import models.Units.Unit;

public class Player {
    private User user;
    private int science;
    private GameMap gameMap;
    private ArrayList<Tech> fullyResearchedTechs = new ArrayList<>();
    private ArrayList<TileResource> availableResources = new ArrayList<>();
    private ArrayList<Unit> units = new ArrayList<>();
    private ArrayList<City> cities = new ArrayList<>();
    private ArrayList<Tech> researchedTechs = new ArrayList<>();
    private Tech techInResearch;
    private City mainCapital;

    public int getScience() {
        return science;
    }

    public void setScience(int science) {
        this.science = science;
    }

    public Player(User user) {
        setUser(user);
    }

    public int getGold() {
        return Gold.getPlayerGold(this);
    }

    public int getGoldProduction() {
        return Gold.getGoldProduction(this);
    }
    //TODO : add happiness methods

    public City getCurrentCapital() {
        return null;
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

    public void removeUser(City city) {
        this.cities.remove(city);
    }

    public Tech getResearchedTechByEnum(TechEnum name) {
        for (int i = 0; i < this.researchedTechs.size(); i++)
            if (researchedTechs.get(i).getTechName() == name)
                return researchedTechs.get(i);
        return null;
    }

    public Tech getFullyResearchedTechByEnum(TechEnum name) {
        for (int i = 0; i < this.fullyResearchedTechs.size(); i++)
            if (fullyResearchedTechs.get(i).getTechName() == name)
                return fullyResearchedTechs.get(i);
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
        updateMap(mainGameMap);
        setScience(getTurnScience() + science);
        updateTechs();
    }

    public ArrayList<TileResource> getAvailableResources() {
        return availableResources;
    }

    public void setAvailableResources(ArrayList<TileResource> availableResources) {
        this.availableResources = availableResources;
    }

    public City getCityBYName(String name) {
        for (int i = 0; i < cities.size(); i++) {
            if (cities.get(i).getName().equals(name))
                return cities.get(i);
        }
        return null;
    }

    public void startGame() {
        new Gold(this);
        new Happiness(this);
    }
}