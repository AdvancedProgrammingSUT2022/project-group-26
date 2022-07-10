package controllers;

import com.example.project.controllers.GameControllers.PlayGameMenuController;
import com.example.project.views.ShowMapFXController;
import com.example.project.models.GameMap;
import com.example.project.models.Improvement.TileImprovement;
import com.example.project.models.Improvement.TileImprovementEnum;
import com.example.project.models.Player;
import com.example.project.models.Tile.Tile;
import com.example.project.models.Tile.TileMode;
import com.example.project.models.Tile.TileModeEnum;
import com.example.project.models.Units.Nonecombat.NoneCombatUnits;
import com.example.project.models.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class ShowMapControllerTest {
    private ArrayList<Player> players = new ArrayList<>();
    private Player player1;
    private Player player2;
    private Player player3;
    private Player player4;
    private Player player5;
    private Player player6;
    private GameMap gameMap;
    private ShowMapFXController showMapController;
    private PlayGameMenuController playGameMenuController;
}