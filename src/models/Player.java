package models;
import java.util.ArrayList;

import models.Technology.Tech;
import models.Units.Combat.CombatUnits;
import models.Units.Nonecombat.NoneCombatUnits;
public class Player {
    private Gold gold;
    private Happiness happiness;
    private User user;
    private Tile[][] GameMap;
    private ArrayList<City> cities = new ArrayList<>();
    private ArrayList<Tech> tech = new ArrayList<>();
    private ArrayList<CombatUnits> combatUnits = new ArrayList<>();
    private ArrayList<NoneCombatUnits> noneCombatUnits = new ArrayList<>();
    private City mainCapital;
}
