package com.example.project.views;

import com.example.project.controllers.GameControllers.GameMenuCommandController;
import com.example.project.controllers.GameControllers.PlayGameMenuController;
import com.example.project.models.Building.BuildingEnum;
import com.example.project.models.City;
import com.example.project.models.Player;
import com.example.project.models.Technology.Tech;
import com.example.project.models.Units.Unit;
import com.example.project.models.Units.UnitNameEnum;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
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
            }
        });

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
        }


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
        infoBox.getChildren().add(label);
    }

    private void trade(Player thisTurnPlayer, Player player) {
        clearBox();
        scrollPane.setVisible(true);


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
        infoBox.getChildren().add(label);
    }
}


// todo : add a pic and hbox ?!