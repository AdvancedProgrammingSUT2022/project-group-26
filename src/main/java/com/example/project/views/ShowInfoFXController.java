package com.example.project.views;

import com.example.project.controllers.GameControllers.PlayGameMenuController;
import com.example.project.models.Building.BuildingEnum;
import com.example.project.models.City;
import com.example.project.models.Game;
import com.example.project.models.Improvement.TileImprovementEnum;
import com.example.project.models.Player;
import com.example.project.models.Resource.TileResource;
import com.example.project.models.Resource.TileResourceEnum;
import com.example.project.models.Technology.Tech;
import com.example.project.models.Units.Nonecombat.BuilderUnit;
import com.example.project.models.Units.Unit;
import com.example.project.models.Units.UnitNameEnum;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.ArrayList;

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
                // todo : open tree !!
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
                sentMessage(Game.getInstance().getThisTurnPlayer(), player);
                MenuChanger.resetGameRequestFocus();
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
        ArrayList<TileResourceEnum> playerResources = new ArrayList<>();
        ArrayList<TileResourceEnum> enemyResources = new ArrayList<>();

        for (TileResource availableResource : player.getAvailableResources()) {
            playerResources.add(availableResource.getResourceName());
        }
        for (TileResource availableResource : enemy.getAvailableResources()) {
            enemyResources.add(availableResource.getResourceName());
        }
        enemyResources.removeAll(playerResources);

        clearBox();
        scrollPane.setVisible(true);

        HBox hBox;
        CheckBox checkBox;
        Label label;

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
        Slider playerSlider = new Slider(0, player.getGold(), 0);
        playerSlider.setBackground(new Background(new BackgroundFill(Color.AQUAMARINE.darker().darker(), new CornerRadii(20), null)));
        hBox.getChildren().addAll(label, playerSlider);
        infoBox.getChildren().add(hBox);

        //  : add resources
        VBox playerVbox = new VBox();
        for (TileResourceEnum playerResource : playerResources) {
            checkBox = new CheckBox(playerResource.getName());
            checkBox.setCursor(Cursor.HAND);
        }
        infoBox.getChildren().add(playerVbox);

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
        Slider enemySlider = new Slider(0, 1000, 0);
        enemySlider.setBackground(new Background(new BackgroundFill(Color.AQUAMARINE.darker().darker(), new CornerRadii(20), null)));
        hBox.getChildren().addAll(label, enemySlider);
        infoBox.getChildren().add(hBox);

        //  : add resources

        VBox enemyVbox = new VBox();
        for (TileResourceEnum enemyResource : enemyResources) {
            checkBox = new CheckBox(enemyResource.getName());
            checkBox.setCursor(Cursor.HAND);
        }
        infoBox.getChildren().add(enemyVbox);

        label = new Label();
        label.setFont(Font.font(15));
        label.setTextFill(Color.AQUAMARINE.darker());
        label.setText("sent trade request!");
        label.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent mouseEvent) {
                // todo : do the trading
                ArrayList<TileResourceEnum> playerTradeResource = new ArrayList<>();
                ArrayList<TileResourceEnum> enemyTradeResource = new ArrayList<>();
                for (Node child : playerVbox.getChildren()) {
                    if (((CheckBox) child).isSelected())
                        playerTradeResource.add(TileResourceEnum.getEnumByString(((CheckBox) child).getText()));
                }
                for (Node child : enemyVbox.getChildren()) {
                    if (((CheckBox) child).isSelected())
                        enemyTradeResource.add(TileResourceEnum.getEnumByString(((CheckBox) child).getText()));
                }
                // send request
                // if accepted !
                // if it is possible
                if (false)
                    sendTradeRequest(player, playerSlider.getValue(), playerTradeResource, enemy, enemySlider.getValue(), enemyTradeResource);
            }
        });
        label.setCursor(Cursor.HAND);
        infoBox.getChildren().add(label);


    }

    private void sendTradeRequest(Player player, double playerGold, ArrayList<TileResourceEnum> playerTradeResource, Player enemy, double enemyGold, ArrayList<TileResourceEnum> enemyTradeResource) {
//        // todo : need to do shit
//        // removing res
//        player.getResearchedTechs().removeIf(x -> (playerTradeResource.contains(x.getTechName())));
//        enemy.getResearchedTechs().removeIf(x -> (enemyTradeResource.contains(x.getTechName())));
//        // adding res
//        player.getResearchedTechs().addAll()
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
//                    Game.getInstance().getThisTurnPlayer(); // from
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

        Label label = new Label();
        label.setFont(Font.font(15));
        label.setTextFill(Color.DARKBLUE);
        label.setText("available improvement : ");
        infoBox.getChildren().add(label);

        ArrayList<TileImprovementEnum> improvementList = new ArrayList<>(); // todo : fill or input !?!

        for (TileImprovementEnum improvement : improvementList) {
            label = new Label();
            label.setFont(Font.font(15));
            label.setTextFill(Color.DARKBLUE);
            label.setText(improvement.getName());
            label.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    // todo : call the right function
                    MenuChanger.resetGameRequestFocus();
                }
            });
            label.setCursor(Cursor.HAND);
            infoBox.getChildren().add(label);
        }
    }
}
// todo : add a pic and hbox ?!