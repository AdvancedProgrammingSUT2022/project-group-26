package models;

import java.util.ArrayList;

import models.Technology.Tech;
import models.Tile.Tile;
import models.Units.Combat.CombatUnits;
import models.Units.Nonecombat.NoneCombatUnits;
import models.Units.Unit;

public class Player {
    private User user;
    private Gold gold;
    private Happiness happiness;
    private Tile[][] GameMap;
    private ArrayList<Tech> fullyResearchedTechs = new ArrayList<>();
    private ArrayList<Unit> units = new ArrayList<>();
    private ArrayList<City> cities = new ArrayList<>();
    private ArrayList<Tech> researchedTechs = new ArrayList<>();
    private Tech techInResearch;
    private City mainCapital;

    public Player(User user) {
        setUser(user);
    }

    public int getGoldSaved() {
        return Gold.getGoldSaved(this);
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

    public Tile[][] getGameMap() {
        return GameMap;
    }

    public void setGameMap(Tile[][] gameMap) {
        GameMap = gameMap;
    }

    public User getUser() {
        return user;
    }

    public Gold getGold() {
        return gold;
    }

    public void setGold(Gold gold) {
        this.gold = gold;
    }

    public Happiness getHappiness() {
        return happiness;
    }

    public void setHappiness(Happiness happiness) {
        this.happiness = happiness;
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
        // TODO: update this function after make cities
        for (int i = 0; i < this.units.size(); i++) {
            ArrayList<Tile> inSightTiles = mainGameMap.getInSightTiles(this.units.get(i).getPosition());
            for (int j = 0; j < inSightTiles.size(); j++) {
                if (this.getGameMap()[mainGameMap.getIndexI(inSightTiles.get(j))][mainGameMap.getIndexJ(inSightTiles.get(j))] == null)
                    this.getGameMap()[mainGameMap.getIndexI(inSightTiles.get(j))][mainGameMap.getIndexJ(inSightTiles.get(j))]
                            = inSightTiles.get(j);
            }
        }
    }
}