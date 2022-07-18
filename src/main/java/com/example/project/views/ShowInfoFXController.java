package com.example.project.views;

import com.example.project.App;
import com.example.project.controllers.GameControllers.CitizenController;
import com.example.project.controllers.GameControllers.GameMenuCommandController;
import com.example.project.controllers.GameControllers.PlayGameMenuController;
import com.example.project.models.Building.BuildingEnum;
import com.example.project.models.City;
import com.example.project.models.Player;
import com.example.project.models.Technology.Tech;
import com.example.project.models.Tile.Tile;
import com.example.project.models.Units.Unit;
import com.example.project.models.Units.UnitNameEnum;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.robot.Robot;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Iterator;

public class ShowInfoFXController {
    private PlayGameMenuController playGameMenuController;

    @FXML
    private VBox infoBox;
    @FXML
    private ScrollPane scrollPane;


    private static ShowInfoFXController instance;

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

        Player player = PlayGamePage.getInstance().getThisTurnPlayer();
        ArrayList<Tech> techs = PlayGamePage.getInstance().getThisTurnPlayer().getResearchedTechs();
        ArrayList<Tech> nextTechs = PlayGamePage.getInstance().getThisTurnPlayer().getPossibleTechnology();
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

        String extraText = "";
        if (player.getTechInResearch() != null) extraText = "-cant add a research-";
        else extraText = "-press the needed research- ";
        label = new Label();
        label.setFont(Font.font(10));
        label.setTextFill(Color.DARKBLUE);
        label.setText("can research these techs " + extraText);
        infoBox.getChildren().add(label);
        for (Tech nextTech : nextTechs) {
            label = new Label();
            label.setFont(Font.font(15));
            label.setTextFill(Color.DARKBLUE);
            label.setText(nextTech.getTechName().getName());
            if (player.getTechInResearch() == null) {
                label.setOnMouseClicked(new EventHandler<MouseEvent>() {
                    @Override
                    public void handle(MouseEvent mouseEvent) {
//                        addResearch(nextTech); todo : need to call the right func
                        clearBox();
                    }
                });
            }
            label.setCursor(Cursor.HAND);
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
                // todo :::::::::::::: TECH TREE
            }
        });
        label.setCursor(Cursor.HAND);
        infoBox.getChildren().add(label);
    }

    public void city() {
        clearBox();

        scrollPane.setVisible(true);

        ArrayList<City> cities = PlayGamePage.getInstance().getThisTurnPlayer().getCities();
        for (City city : cities) {
            Label label = new Label();
            label.setFont(Font.font(15));
            label.setTextFill(Color.DARKBLUE);
            label.setText("city --> " + city.getName());
            label.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    selectCity(city);
                }
            });
            label.setCursor(Cursor.HAND);
            infoBox.getChildren().add(label);
        }
    }

    private void selectCity(City city) {
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
        label.setText("build unit with gold");
        infoBox.getChildren().add(label);
        label.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                showUnitsToBuild(city, "fast");
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
        label.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                showBuildingsToBuild(city, "fast");
            }
        });
        label.setCursor(Cursor.HAND);
        infoBox.getChildren().add(label);

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
                // todo : destroy city
            }
        });
        label.setCursor(Cursor.HAND);
        infoBox.getChildren().add(label);


        label = new Label();
        label.setFont(Font.font(15));
        label.setTextFill(Color.DARKBLUE);
        label.setText("back bottom");
        label.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                city();
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
            }
        });
        label.setCursor(Cursor.HAND);
        infoBox.getChildren().add(label);


        label = new Label();
        label.setFont(Font.font(15));
        label.setTextFill(Color.DARKBLUE);
        label.setText("back bottom");
        label.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                selectCity(city);
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
                }
            });
            label.setCursor(Cursor.HAND);
            infoBox.getChildren().add(label);
        }

        label = new Label();
        label.setFont(Font.font(15));
        label.setTextFill(Color.DARKBLUE);
        label.setText("back bottom");
        label.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                citizenPanel(city);
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
            label.setText("citizen " + count + " : ");
            label.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    city.getUnderWorkTiles().remove(tile);
                    citizenPanel(city);
                }
            });
            label.setCursor(Cursor.HAND);
            infoBox.getChildren().add(label);

            label = new Label();
            label.setFont(Font.font(15));
            label.setTextFill(Color.DARKBLUE);
            label.setText("production : " + tile.getProduction() + " food : " + tile.getFood() + " gold : " + tile.getGold());
            label.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    city.getUnderWorkTiles().remove(tile);
                    citizenPanel(city);
                }
            });
            label.setCursor(Cursor.HAND);
            infoBox.getChildren().add(label);
            count++;

            label = new Label();
            label.setFont(Font.font(15));
            label.setTextFill(Color.AQUAMARINE.darker());
            label.setText(">>>----------<<<");
            infoBox.getChildren().add(label);
        }


        label = new Label();
        label.setFont(Font.font(15));
        label.setTextFill(Color.DARKBLUE);
        label.setText("back bottom");
        label.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                citizenPanel(city);
            }
        });
        label.setCursor(Cursor.HAND);
        infoBox.getChildren().add(label);
    }

    private void showUnitsToBuild(City city, String mode) {
        clearBox();

        scrollPane.setVisible(true);

        Label label;
        Player player = PlayGamePage.getInstance().getThisTurnPlayer();
        for (UnitNameEnum unit : player.getProduceAbleUnits()) {
            label = new Label();
            label.setFont(Font.font(15));
            label.setTextFill(Color.DARKBLUE);
            label.setText("unit name : " + unit.getName() + " cost : " + unit.getCost());
            label.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (mode.equals("fast") && playGameMenuController.haveEnoughGoldForUnit(player, unit))
                        System.out.println("dont have enough money");
                        // dont have enough money ::todo::
                    else if (unit.getName().equals("settler"))
                        playGameMenuController.createCivilian(player, city, unit, mode);
                    else if (unit.getName().equals("worker"))
                        playGameMenuController.createBuilder(player, city, unit, mode);
                    else playGameMenuController.createCombatUnit(player, city, unit, mode);
                }
            });
            label.setCursor(Cursor.HAND);
            infoBox.getChildren().add(label);
        }
        label = new Label();
        label.setFont(Font.font(15));
        label.setTextFill(Color.DARKBLUE);
        label.setText("back bottom");
        label.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                selectCity(city);
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
        Player player = PlayGamePage.getInstance().getThisTurnPlayer();
        ArrayList<BuildingEnum> buildings = player.getProduceAbleBuildings();
        for (BuildingEnum building : buildings) {
            label = new Label();
            label.setFont(Font.font(15));
            label.setTextFill(Color.DARKBLUE);
            label.setText("building name : " + building.getName() + " cost : " + building.getCost());
            label.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    if (mode.equals("fast") && playGameMenuController.haveEnoughGoldForBuilding(player, building))
                        System.out.println("dont have enough money");
                        // dont have enough money ::todo::
                    else playGameMenuController.createBuilding(player, city, building, mode);
                }
            });
            label.setCursor(Cursor.HAND);
            infoBox.getChildren().add(label);
        }
        label = new Label();
        label.setFont(Font.font(15));
        label.setTextFill(Color.DARKBLUE);
        label.setText("back bottom");
        label.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                selectCity(city);
            }
        });
        label.setCursor(Cursor.HAND);
        infoBox.getChildren().add(label);
        // todo --> add a info thingie !

    }

    public void troop() {
        clearBox();

        scrollPane.setVisible(true);

        int count = 0;
        ArrayList<Unit> units = PlayGamePage.getInstance().getThisTurnPlayer().getUnits();
        for (Unit unit : units) {
            Label label = new Label();
            label.setFont(Font.font(15));
            label.setTextFill(Color.DARKBLUE);
            label.setText(count + " : " + unit.getUnitNameEnum().getName());
            label.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    UnitCommandFxController.getInstance().setSelectedUnit(unit);
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

        Player player = PlayGamePage.getInstance().getThisTurnPlayer();
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
                sentMessage(PlayGamePage.getInstance().getThisTurnPlayer(), player);
            }
        });
        label.setCursor(Cursor.HAND);
        infoBox.getChildren().add(label);

        label = new Label();
        label.setFont(Font.font(15));
        label.setTextFill(Color.DARKBLUE);
        label.setText("trade with this player");
        label.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                trade(PlayGamePage.getInstance().getThisTurnPlayer(), player);
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
                // todo : peace
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
                // todo : war
            }
        });
        label.setCursor(Cursor.HAND);
        infoBox.getChildren().add(label);
    }

    private void trade(Player player, Player enemy) {
        clearBox();
        scrollPane.setVisible(true);

        HBox hBox;
        Label label;
        Slider slider;

        label = new Label();
        label.setFont(Font.font(20));
        label.setTextFill(Color.AQUAMARINE.darker());
        label.setText("----trade----");

        infoBox.getChildren().add(label);


        hBox = new HBox();
        label = new Label();
        label.setFont(Font.font(15));
        label.setTextFill(Color.DARKBLUE);
        label.setText("Gold");
        slider = new Slider(0, player.getGold(), 0);
        slider.setBackground(new Background(new BackgroundFill(Color.AQUAMARINE.darker().darker(), new CornerRadii(20), null)));
        hBox.getChildren().addAll(label, slider);
        infoBox.getChildren().add(hBox);

        // todo : add resources

        label = new Label();
        label.setFont(Font.font(20));
        label.setTextFill(Color.AQUAMARINE.darker());
        label.setText("----for----");
        infoBox.getChildren().add(label);

        hBox = new HBox();
        label = new Label();
        label.setFont(Font.font(15));
        label.setTextFill(Color.DARKBLUE);
        label.setText("Gold");
        slider = new Slider(0, 1000, 0);
        slider.setBackground(new Background(new BackgroundFill(Color.AQUAMARINE.darker().darker(), new CornerRadii(20), null)));
        hBox.getChildren().addAll(label, slider);
        infoBox.getChildren().add(hBox);

        // todo : add resources


        label = new Label();
        label.setFont(Font.font(15));
        label.setTextFill(Color.AQUAMARINE.darker());
        label.setText("sent trade request!");
        label.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                // todo : do the trading
            }
        });
        label.setCursor(Cursor.HAND);

        infoBox.getChildren().add(label);


    }

    private void sentMessage(Player me, Player player) {
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

        Player player = PlayGamePage.getInstance().getThisTurnPlayer();
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
            }
        });
        label.setCursor(Cursor.HAND);
        infoBox.getChildren().add(label);
    }
}
