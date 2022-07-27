package com.example.project.views;

import com.example.project.controllers.GameControllers.BuilderController;
import com.example.project.controllers.GameControllers.CitizenController;
import com.example.project.controllers.GameControllers.PlayGameMenuController;
import com.example.project.models.Building.Building;
import com.example.project.models.Building.BuildingEnum;
import com.example.project.models.City;
import com.example.project.models.Game;
import com.example.project.models.GameMap;
import com.example.project.models.Improvement.TileImprovementEnum;
import com.example.project.models.Player;
import com.example.project.models.Technology.Tech;
import com.example.project.models.Tile.Tile;
import com.example.project.models.Units.Nonecombat.BuilderUnit;
import com.example.project.models.Units.Unit;
import com.example.project.models.Units.UnitNameEnum;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.ArrayList;
import java.util.Iterator;

public class ShowInfoFXController {
    private PlayGameMenuController playGameMenuController;

    @FXML
    private VBox infoBox;
    @FXML
    private ScrollPane scrollPane;


    private static ShowInfoFXController instance;

    public static void setNull() {
        instance = null;
    }

    private ShowInfoFXController() {
    }

    public static ShowInfoFXController getInstance() {
        if (instance == null) instance = new ShowInfoFXController();
        return instance;
    }

    public void setInfoBox(VBox infoBox) {
        this.infoBox = infoBox;
    }

    public void setScrollPane(ScrollPane scrollPane) {
        this.scrollPane = scrollPane;
    }

    public void research() {
        clearBox();
        scrollPane.setVisible(true);

        Player player = Game.getInstance().getThisTurnPlayer();
        ArrayList<Tech> techs = Game.getInstance().getThisTurnPlayer().getFullyResearchedTechs();
        ArrayList<Tech> nextTechs = Game.getInstance().getThisTurnPlayer().getPossibleTechnology();
        Label label = new Label();
        label.setFont(Font.font(12));
        label.setTextFill(Color.DARKBLUE);
        label.setText("researched techs : ");
        infoBox.getChildren().add(label);
        for (Tech tech : techs) {
            label = new Label();
            label.setFont(Font.font(15));
            label.setTextFill(Color.DARKBLUE);
            label.setText(tech.getTechName().getName());
            infoBox.getChildren().add(label);
        }
        label = new Label();
        label.setFont(Font.font(12));
        label.setTextFill(Color.AQUAMARINE.darker().darker());
        label.setText(">>>----------<<<");
        infoBox.getChildren().add(label);

        label = new Label();
        label.setFont(Font.font(10));
        label.setTextFill(Color.DARKBLUE);
        label.setText("can research these techs ");
        infoBox.getChildren().add(label);
        for (Tech nextTech : nextTechs) {
            label = new Label();
            label.setFont(Font.font(15));
            label.setTextFill(Color.DARKBLUE);
            label.setText(nextTech.getTechName().getName() + "- G=" + nextTech.getTechName().getCost());
            if (player.getTechInResearch() == null) {
                label.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
                        playGameMenuController.research(nextTech.getTechName(), Game.getInstance().getThisTurnPlayer());
                        clearBox();
                        MenuChanger.resetGameRequestFocus();
                    }
                });
                label.setCursor(Cursor.HAND);
            }
            infoBox.getChildren().add(label);
        }
        label = new Label();
        label.setFont(Font.font(12));
        label.setTextFill(Color.AQUAMARINE.darker().darker());
        label.setText(">>>----------<<<");
        infoBox.getChildren().add(label);

        label = new Label();
        label.setFont(Font.font(15));
        label.setTextFill(Color.DARKBLUE);
        label.setText("tech tree");
        label.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                PlayGamePage.getInstance().setOnMap(false);
                MenuChanger.goToTechTree();
            }
        });
        label.setCursor(Cursor.HAND);
        infoBox.getChildren().add(label);
    }

    public void city() {
        clearBox();

        scrollPane.setVisible(true);

        ArrayList<City> cities = Game.getInstance().getThisTurnPlayer().getCities();
        for (City city : cities) {
            Label label = new Label();
            label.setFont(Font.font(15));
            label.setTextFill(Color.DARKBLUE);
            label.setText("city --> " + city.getName());
            label.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    selectCity(city);
                    MenuChanger.resetGameRequestFocus();
                }
            });
            label.setCursor(Cursor.HAND);
            infoBox.getChildren().add(label);
        }
    }

    public void selectCity(City city) {
        clearBox();

        scrollPane.setVisible(true);

        Label label = new Label();
        label.setFont(Font.font(15));
        label.setTextFill(Color.DARKBLUE);
        label.setText("city name : " + city.getName());
        infoBox.getChildren().add(label);

        label = new Label();
        label.setFont(Font.font(15));
        label.setTextFill(Color.DARKBLUE);
        label.setText("city health : " + city.getHealth());
        infoBox.getChildren().add(label);

        label = new Label();
        label.setFont(Font.font(15));
        label.setTextFill(Color.DARKBLUE);
        label.setText("city population : " + city.getMaxPopulation());
        infoBox.getChildren().add(label);

        label = new Label();
        label.setFont(Font.font(15));
        label.setTextFill(Color.DARKBLUE);
        label.setText("city food production : " + city.getFoodProduction());
        infoBox.getChildren().add(label);

        label = new Label();
        label.setFont(Font.font(15));
        label.setTextFill(Color.DARKBLUE);
        label.setText("city gold production : " + city.getGoldProduction());
        infoBox.getChildren().add(label);

        label = new Label();
        label.setFont(Font.font(15));
        label.setTextFill(Color.DARKBLUE);
        label.setText("city production created : " + city.getProduction());
        infoBox.getChildren().add(label);

        label = new Label();
        label.setFont(Font.font(15));
        label.setTextFill(Color.DARKBLUE);
        if (city.getBeingBuild() == null) label.setText("city isn't working");
        else {
            Object obj = city.getBeingBuild().getGettingBuild();
            if (obj instanceof Building) label.setText("creating building : " + ((Building) obj).getName().getName());
            else if (obj instanceof Unit) label.setText("creating unit : " + ((Unit) obj).getUnitNameEnum().getName());
        }
        infoBox.getChildren().add(label);

        label = new Label();
        label.setFont(Font.font(15));
        label.setTextFill(Color.DARKBLUE);
        label.setText("buy tiles");
        label.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                buyTile(city);
                MenuChanger.resetGameRequestFocus();
            }
        });
        label.setCursor(Cursor.HAND);
        infoBox.getChildren().add(label);

        label = new Label();
        label.setFont(Font.font(15));
        label.setTextFill(Color.DARKBLUE);
        label.setText("build unit with gold");
        infoBox.getChildren().add(label);
        label.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                showUnitsToBuild(city, "fast");
                MenuChanger.resetGameRequestFocus();
            }
        });
        label.setCursor(Cursor.HAND);


        if (city.getBeingBuild() == null) {
            label = new Label();
            label.setFont(Font.font(15));
            label.setTextFill(Color.DARKBLUE);
            label.setText("build unit with production");
            infoBox.getChildren().add(label);
            label.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    showUnitsToBuild(city, "slow");
                }
            });
            label.setCursor(Cursor.HAND);
        }

        label = new Label();
        label.setFont(Font.font(15));
        label.setTextFill(Color.DARKBLUE);
        label.setText("build building with gold");
        infoBox.getChildren().add(label);
        label.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                showBuildingsToBuild(city, "fast");
                MenuChanger.resetGameRequestFocus();
            }
        });
        label.setCursor(Cursor.HAND);

        if (city.getBeingBuild() == null) {
            label = new Label();
            label.setFont(Font.font(15));
            label.setTextFill(Color.DARKBLUE);
            label.setText("build building with production");
            infoBox.getChildren().add(label);
            label.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    showBuildingsToBuild(city, "slow");
                    MenuChanger.resetGameRequestFocus();
                }
            });
            label.setCursor(Cursor.HAND);
        }

        label = new Label();
        label.setFont(Font.font(15));
        label.setTextFill(Color.DARKBLUE);
        label.setText("citizens panel");
        label.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                citizenPanel(city);
                MenuChanger.resetGameRequestFocus();
            }
        });
        label.setCursor(Cursor.HAND);
        infoBox.getChildren().add(label);


        label = new Label();
        label.setFont(Font.font(15));
        label.setTextFill(Color.DARKBLUE);
        label.setText("destroy city");
        label.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                playGameMenuController.destroyCity(city);
                MenuChanger.resetGameRequestFocus();
            }
        });
        label.setCursor(Cursor.HAND);
        infoBox.getChildren().add(label);


        label = new Label();
        label.setFont(Font.font(15));
        label.setTextFill(Color.DARKBLUE);
        label.setText("back");
        label.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                city();
                MenuChanger.resetGameRequestFocus();
            }
        });
        label.setCursor(Cursor.HAND);
        infoBox.getChildren().add(label);
    }

    private void buyTile(City city) {
        clearBox();
        scrollPane.setVisible(true);

        Label label = new Label();
        label.setFont(Font.font(15));
        label.setTextFill(Color.DARKBLUE);
        label.setText("enter a valid tile to buy");
        infoBox.getChildren().add(label);

        TextField xCord = new TextField();
        TextField yCord = new TextField();
        xCord.setPromptText("enter x coordination");
        yCord.setPromptText("enter y coordination");

        infoBox.getChildren().addAll(xCord, yCord);
        // should i add focus handling for the text fields

        label = new Label();
        label.setFont(Font.font(15));
        label.setTextFill(Color.DARKBLUE);
        label.setText("buy!");
        label.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                String res = "";
                if (xCord.getText().matches("\\d+") && yCord.getText().matches("\\d+")) {
                    res = playGameMenuController.buyCityTile(Game.getInstance().getThisTurnPlayer(), city, xCord.getText(), yCord.getText());
                    if (!res.equals("ok")) new PopupMessage(Alert.AlertType.ERROR, res);
                    else selectCity(city);
                } else new PopupMessage(Alert.AlertType.ERROR, "invalid input");
                MenuChanger.resetGameRequestFocus();
            }
        });
        label.setCursor(Cursor.HAND);
        infoBox.getChildren().add(label);


        label = new Label();
        label.setFont(Font.font(15));
        label.setTextFill(Color.DARKBLUE);
        label.setText("back");
        label.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                selectCity(city);
                MenuChanger.resetGameRequestFocus();
            }
        });
        label.setCursor(Cursor.HAND);
        infoBox.getChildren().add(label);
    }

    private void citizenPanel(City city) {
        clearBox();
        scrollPane.setVisible(true);

        Label label = new Label();
        label.setFont(Font.font(15));
        label.setTextFill(Color.DARKBLUE);
        label.setText("assign for max gold");
        label.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                CitizenController.assignCitizensOfCity(city, "gold");
                selectCity(city);
                MenuChanger.resetGameRequestFocus();
            }
        });
        label.setCursor(Cursor.HAND);
        infoBox.getChildren().add(label);

        label = new Label();
        label.setFont(Font.font(15));
        label.setTextFill(Color.DARKBLUE);
        label.setText("assign for max food");
        label.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                CitizenController.assignCitizensOfCity(city, "food");
                selectCity(city);
                MenuChanger.resetGameRequestFocus();
            }
        });
        label.setCursor(Cursor.HAND);
        infoBox.getChildren().add(label);


        label = new Label();
        label.setFont(Font.font(15));
        label.setTextFill(Color.DARKBLUE);
        label.setText("assign for max production");
        label.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                CitizenController.assignCitizensOfCity(city, "production");
                selectCity(city);
                MenuChanger.resetGameRequestFocus();
            }
        });
        label.setCursor(Cursor.HAND);
        infoBox.getChildren().add(label);

        label = new Label();
        label.setFont(Font.font(15));
        label.setTextFill(Color.DARKBLUE);
        label.setText("assign for max economy");
        label.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                CitizenController.assignCitizensOfCity(city, "economy");
                selectCity(city);
                MenuChanger.resetGameRequestFocus();
            }
        });
        label.setCursor(Cursor.HAND);
        infoBox.getChildren().add(label);

        label = new Label();
        label.setFont(Font.font(15));
        label.setTextFill(Color.DARKBLUE);
        label.setText("remove citizen");
        label.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                removeCitizen(city);
                MenuChanger.resetGameRequestFocus();
            }
        });
        label.setCursor(Cursor.HAND);
        infoBox.getChildren().add(label);

        label = new Label();
        label.setFont(Font.font(15));
        label.setTextFill(Color.DARKBLUE);
        label.setText("assign citizen");
        label.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                assignCitizen(city);
                MenuChanger.resetGameRequestFocus();
            }
        });
        label.setCursor(Cursor.HAND);
        infoBox.getChildren().add(label);


        label = new Label();
        label.setFont(Font.font(15));
        label.setTextFill(Color.DARKBLUE);
        label.setText("back");
        label.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                selectCity(city);
                MenuChanger.resetGameRequestFocus();
            }
        });
        label.setCursor(Cursor.HAND);
        infoBox.getChildren().add(label);

    }

    private void assignCitizen(City city) {
        clearBox();
        scrollPane.setVisible(true);
        int available = city.getMaxPopulation() - city.getUnderWorkTiles().size();

        Label label = new Label();
        label.setFont(Font.font(15));
        label.setTextFill(Color.DARKBLUE);
        label.setText("available citizens : " + available);
        infoBox.getChildren().add(label);

        String temp;
        if (available == 0) temp = "free some citizens first!";
        else temp = "select a tile to assign a citizen to it";
        label = new Label();
        label.setFont(Font.font(15));
        label.setTextFill(Color.DARKBLUE);
        label.setText(temp);
        infoBox.getChildren().add(label);

        ArrayList<Tile> tiles = (ArrayList<Tile>) city.getTiles().clone();
        tiles.removeAll(city.getUnderWorkTiles());
        for (Tile tile : tiles) {
            label = new Label();
            label.setFont(Font.font(15));
            label.setTextFill(Color.DARKBLUE);
            label.setText("tile with P:" + tile.getProduction() + " G:" + tile.getGold() + " F:" + tile.getFood());
            label.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    city.getUnderWorkTiles().add(tile);
                    citizenPanel(city);
                    MenuChanger.resetGameRequestFocus();
                }
            });
            label.setCursor(Cursor.HAND);
            infoBox.getChildren().add(label);

        }

        label = new Label();
        label.setFont(Font.font(15));
        label.setTextFill(Color.DARKBLUE);
        label.setText("back");
        label.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                citizenPanel(city);
                MenuChanger.resetGameRequestFocus();
            }
        });
        label.setCursor(Cursor.HAND);
        infoBox.getChildren().add(label);

    }

    private void removeCitizen(City city) {
        clearBox();
        scrollPane.setVisible(true);


        Label label = new Label();
        label.setFont(Font.font(15));
        label.setTextFill(Color.DARKBLUE);
        label.setText("working citizens : ");
        infoBox.getChildren().add(label);


        Iterator iterator = city.getUnderWorkTiles().iterator();

        int count = 1;
        while (iterator.hasNext()) {
            Tile tile = (Tile) iterator.next();


            label = new Label();
            label.setFont(Font.font(15));
            label.setTextFill(Color.DARKBLUE);
            label.setText("citizen with P:" + tile.getProduction() + " F:" + tile.getFood() + " G:" + tile.getGold());
            label.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    city.getUnderWorkTiles().remove(tile);
                    citizenPanel(city);
                    MenuChanger.resetGameRequestFocus();
                }
            });
            label.setCursor(Cursor.HAND);
            infoBox.getChildren().add(label);
            count++;
        }
        label = new Label();
        label.setFont(Font.font(15));
        label.setTextFill(Color.DARKBLUE);
        label.setText("back");
        label.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                citizenPanel(city);
                MenuChanger.resetGameRequestFocus();
            }
        });
        label.setCursor(Cursor.HAND);
        infoBox.getChildren().add(label);

    }

    private void showUnitsToBuild(City city, String mode) {
        clearBox();
        scrollPane.setVisible(true);
        Label label;
        Player player = Game.getInstance().getThisTurnPlayer();
        for (UnitNameEnum unit : player.getProducibleUnits()) {
            label = new Label();
            label.setFont(Font.font(15));
            label.setTextFill(Color.DARKBLUE);
            label.setText("unit name : " + unit.getName() + " cost : " + unit.getCost());
            label.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (mode.equals("fast") && !playGameMenuController.haveEnoughGoldForUnit(player, unit))
                        new PopupMessage(Alert.AlertType.ERROR, "dont have enough money");
                    else if (unit == UnitNameEnum.SETTLER)
                        playGameMenuController.createCivilian(player, city, unit, mode);
                    else if (unit == UnitNameEnum.WORKER)
                        playGameMenuController.createBuilder(player, city, unit, mode);
                    else playGameMenuController.createCombatUnit(player, city, unit, mode);
                    MenuChanger.resetGameRequestFocus();
                }
            });
            label.setCursor(Cursor.HAND);
            infoBox.getChildren().add(label);
        }
        label = new Label();
        label.setFont(Font.font(15));
        label.setTextFill(Color.DARKBLUE);
        label.setText("back");
        label.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                selectCity(city);
                MenuChanger.resetGameRequestFocus();
            }
        });
        label.setCursor(Cursor.HAND);
        infoBox.getChildren().add(label);
        // todo --> add a info thingie !
    }

    private void showBuildingsToBuild(City city, String mode) {
        clearBox();

        scrollPane.setVisible(true);

        Label label;
        Player player = Game.getInstance().getThisTurnPlayer();
        ArrayList<BuildingEnum> buildings = player.getProducibleBuildings(city);
        for (BuildingEnum building : buildings) {
            label = new Label();
            label.setFont(Font.font(15));
            label.setTextFill(Color.DARKBLUE);
            label.setText("building name : " + building.getName() + " cost : " + building.getCost());
            label.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (mode.equals("fast") && !playGameMenuController.haveEnoughGoldForBuilding(player, building))
                        new PopupMessage(Alert.AlertType.ERROR, "dont have enough money");
                    else {
                        playGameMenuController.createBuilding(player, city, building, mode);
                        showBuildingsToBuild(city, mode);
                    }
                    MenuChanger.resetGameRequestFocus();
                }
            });
            label.setCursor(Cursor.HAND);
            infoBox.getChildren().add(label);
        }
        label = new Label();
        label.setFont(Font.font(15));
        label.setTextFill(Color.DARKBLUE);
        label.setText("back");
        label.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                selectCity(city);
                MenuChanger.resetGameRequestFocus();
            }
        });
        label.setCursor(Cursor.HAND);
        infoBox.getChildren().add(label);
        // todo --> add a info thingie !

    }

    public void troop() {
        clearBox();

        scrollPane.setVisible(true);

        int count = 1;
        ArrayList<Unit> units = Game.getInstance().getThisTurnPlayer().getUnits();
        for (Unit unit : units) {
            Label label = new Label();
            label.setFont(Font.font(15));
            label.setTextFill(Color.DARKBLUE);
            label.setText(count + " : " + unit.getUnitNameEnum().getName());
            label.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    UnitCommandFxController.getInstance().setSelectedUnit(unit);
                    MenuChanger.resetGameRequestFocus();
                }
            });
            label.setCursor(Cursor.HAND);
            infoBox.getChildren().add(label);
            count++;
        }

    }

    public void clearBox() {
        scrollPane.setVisible(false);
        infoBox.getChildren().clear();
    }

    public void diplomacy() {
        clearBox();
        scrollPane.setVisible(true);

        Player player = Game.getInstance().getThisTurnPlayer();
        Label label = new Label();
        label.setFont(Font.font(15));
        label.setTextFill(Color.DARKBLUE);
        label.setText("players met : ");
        infoBox.getChildren().add(label);

        for (Player metPlayer : player.getMetPlayers()) {
            label = new Label();
            label.setFont(Font.font(15));
            label.setTextFill(Color.DARKBLUE);
            label.setText("player name : " + metPlayer.getUser().getNickname());
            label.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    playerInfo(metPlayer);
                    MenuChanger.resetGameRequestFocus();
                }
            });
            label.setCursor(Cursor.HAND);
            infoBox.getChildren().add(label);
        }
    }

    private void playerInfo(Player player) {
        clearBox();
        scrollPane.setVisible(true);

        Label label = new Label();
        label.setFont(Font.font(15));
        label.setTextFill(Color.DARKBLUE);
        label.setText("player name " + player.getUser().getNickname());
        infoBox.getChildren().add(label);

        label = new Label();
        label.setFont(Font.font(15));
        label.setTextFill(Color.DARKBLUE);
        label.setText("send message");
        label.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                sendMessage(Game.getInstance().getThisTurnPlayer(), player);
                MenuChanger.resetGameRequestFocus();
            }
        });
        infoBox.getChildren().add(label);
        label.setCursor(Cursor.HAND);

        label = new Label();
        label.setFont(Font.font(15));
        label.setTextFill(Color.DARKBLUE);
        label.setText("trade with this player");
        label.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                trade(Game.getInstance().getThisTurnPlayer(), player);
                MenuChanger.resetGameRequestFocus();
            }
        });
        label.setCursor(Cursor.HAND);
        infoBox.getChildren().add(label);

        label = new Label();
        label.setFont(Font.font(15));
        label.setTextFill(Color.DARKBLUE);
        label.setText("declare peace");
        label.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Game.getInstance().getThisTurnPlayer().addPlayerInPeace(player);
                MenuChanger.resetGameRequestFocus();
            }
        });
        label.setCursor(Cursor.HAND);
        infoBox.getChildren().add(label);

        label = new Label();
        label.setFont(Font.font(15));
        label.setTextFill(Color.DARKBLUE);
        label.setText("declare war");
        label.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                Game.getInstance().getThisTurnPlayer().addPLayerInWar(player);
                MenuChanger.resetGameRequestFocus();
            }
        });
        label.setCursor(Cursor.HAND);
        infoBox.getChildren().add(label);

        label = new Label();
        label.setFont(Font.font(15));
        label.setTextFill(Color.DARKBLUE);
        label.setText("demand");
        label.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                if (Game.getInstance().getThisTurnPlayer().getPlayersInWar().contains(player))
                    new PopupMessage(Alert.AlertType.INFORMATION, "you are in war with this player");
                else {
                    demand(player);
                    MenuChanger.resetGameRequestFocus();
                }
                MenuChanger.resetGameRequestFocus();
            }
        });
        label.setCursor(Cursor.HAND);
        infoBox.getChildren().add(label);
    }

    private void demand(Player player) {
        clearBox();
        scrollPane.setVisible(true);
        addSpacingHBox();

        addSlider(1000, "Gold");
        addSlider(1000, "Happiness");
        addSlider(1000, "Science");

        Label label = new Label();
        label.setFont(Font.font(15));
        label.setTextFill(Color.AQUAMARINE.darker().darker());
        label.setText("send demand request!");
        label.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                MenuChanger.resetGameRequestFocus();            }
        });
        label.setCursor(Cursor.HAND);
        infoBox.getChildren().add(label);
    }

    private void trade(Player player, Player enemy) {
        clearBox();
        scrollPane.setVisible(true);
        addSpacingHBox();

        HBox hBox;
        Label label;
        Slider slider;

        label = new Label();
        label.setFont(Font.font(20));
        label.setTextFill(Color.AQUAMARINE.darker());
        label.setText("----trade----");

        infoBox.getChildren().add(label);

        addSlider(player.getGold(), "Gold");
        addSlider(player.getHappiness(), "Happiness");
        addSlider(player.getScience(), "Science");

        label = new Label();
        label.setFont(Font.font(20));
        label.setTextFill(Color.AQUAMARINE.darker());
        label.setText("----for----");
        infoBox.getChildren().add(label);

        addSlider(1000, "Gold");
        addSlider(1000, "Happiness");
        addSlider(1000, "Science");

        label = new Label();
        label.setFont(Font.font(15));
        label.setTextFill(Color.AQUAMARINE.darker().darker());
        label.setText("send trade request!");
        label.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                MenuChanger.resetGameRequestFocus();            }
        });
        label.setCursor(Cursor.HAND);
        infoBox.getChildren().add(label);
    }

    public void addSlider(int size, String name) {
        HBox hBox = new HBox();
        Label label = new Label();
        label.setFont(Font.font(15));
        label.setTextFill(Color.DARKBLUE);
        label.setText(name);
        label.setPrefWidth(100);
        Slider slider = new Slider(0, size, 0);
        slider.setBackground(new Background(new BackgroundFill(Color.AQUAMARINE.darker().darker(), new CornerRadii(20), null)));
        hBox.getChildren().addAll(label, slider);
        infoBox.getChildren().add(hBox);
    }

    private void sendMessage(Player me, Player player) {
        clearBox();
        scrollPane.setVisible(true);

        ScrollPane chatScroll = new ScrollPane();
        VBox root = new VBox();
        VBox chat = new VBox();

        fillChat(chat);

        TextField textField = new TextField();
        textField.setPromptText("send message");
        textField.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.ENTER) {
                    // send message
//                    textField.getText(); //text
//                    PlayGamePage.getInstance().getThisTurnPlayer(); // from
//                    player; // to
                }
                MenuChanger.resetGameRequestFocus();
            }
        });
        root.getChildren().addAll(chat, textField);
        chatScroll.setContent(root);
    }

    private void fillChat(VBox chat) {
        // todo : ask ilya
    }

    public void setPlayGameMenuController(PlayGameMenuController playGameMenuController) {
        this.playGameMenuController = playGameMenuController;
    }

    public void notification() {
        clearBox();
        scrollPane.setVisible(true);

        Label label = new Label();
        label.setFont(Font.font(15));
        label.setTextFill(Color.DARKBLUE);
        label.setText("notifications : ");
        infoBox.getChildren().add(label);

        Player player = Game.getInstance().getThisTurnPlayer();
        for (String notification : player.getNotifications()) {
            label = new Label();
            label.setFont(Font.font(15));
            label.setTextFill(Color.DARKBLUE);
            label.setText(notification);
            infoBox.getChildren().add(label);
        }

        label = new Label();
        label.setFont(Font.font(15));
        label.setTextFill(Color.AQUAMARINE.darker());
        label.setText(">>>----------<<<");
        infoBox.getChildren().add(label);


        label = new Label();
        label.setFont(Font.font(15));
        label.setTextFill(Color.DARKBLUE);
        label.setText("back");
        label.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                clearBox();
                MenuChanger.resetGameRequestFocus();
            }
        });
        label.setCursor(Cursor.HAND);
        infoBox.getChildren().add(label);
    }


    // todo : use this  -- <ilya's thingie>
    public void setImprovements(BuilderUnit builderUnit) {
        clearBox();
        scrollPane.setVisible(true);
        addSpacingHBox();

        Label label = new Label();
        label.setFont(Font.font(15));
        label.setTextFill(Color.DARKBLUE);
        label.setText("available improvement : ");
        infoBox.getChildren().add(label);

        ArrayList<TileImprovementEnum> improvementList = builderUnit.getPosition().getPossibleImprovements(Game.getInstance().getThisTurnPlayer());
        for (TileImprovementEnum improvement : improvementList) {
            label = new Label();
            label.setFont(Font.font(15));
            label.setTextFill(Color.DARKBLUE);
            label.setText(improvement.getName());
            label.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    new PopupMessage(Alert.AlertType.INFORMATION,
                            new BuilderController().improveTile(Game.getInstance().getThisTurnPlayer(), builderUnit, improvement).toString());
                    MenuChanger.resetGameRequestFocus();
                }
            });
            label.setCursor(Cursor.HAND);
            infoBox.getChildren().add(label);
        }
    }

    private void addSpacingHBox() {
        HBox spacingHBox = new HBox();
        spacingHBox.setPrefHeight(30);
        infoBox.getChildren().add(spacingHBox);
    }
}