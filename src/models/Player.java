package models;

import java.util.ArrayList;

import models.Technology.Tech;
import models.Tile.Tile;
import models.Units.Units;

public class Player {
    private User user;
    private Gold gold;
    private Happiness happiness;
    private Tile[][] GameMap;
    private ArrayList<Tech> fullyResearchedTechs = new ArrayList<>();
    private ArrayList<Units> units = new ArrayList<>();
    private ArrayList<City> cities = new ArrayList<>();
    private ArrayList<Tech> researchedTechs = new ArrayList<>();
    private Tech techInResearch;
    private City mainCapital;

    public Player(User user) {
        setUser(user);
    }

    public void updateGold() {
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

    public void updateGameMap(Tile[][] MainGameMap) {
    }

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

    public ArrayList<Units> getUnits() {
        return units;
    }

    public void setUnits(ArrayList<Units> units) {
        this.units = units;
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

    public void setUser(User user) {
        this.user = user;
    }

    public void removeUser(City city) {
        this.cities.remove(city);
    }
}