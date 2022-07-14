package com.example.project.views;

import com.example.project.models.City;
import com.example.project.models.GameMap;
import com.example.project.models.Technology.Tech;
import com.example.project.models.Units.Unit;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.util.ArrayList;

public class ShowInfoFXController {
    @FXML
    private VBox infoBox;


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

    public void research() {
        clearBox();
        ArrayList<Tech> techs = PlayGamePage.getInstance().getThisTurnPlayer().getResearchedTechs();
        ArrayList<Tech> nextTechs = PlayGamePage.getInstance().getThisTurnPlayer().getPossibleTechnology();
        Label label = new Label();
        label.setFont(Font.font(18));
        label.setTextFill(Color.DARKBLUE);
        label.setText("researched techs : ");
        infoBox.getChildren().add(label);
        for (Tech tech : techs) {
            label = new Label();
            label.setFont(Font.font(18));
            label.setTextFill(Color.DARKBLUE);
            label.setText(tech.getTechName().getName());
            infoBox.getChildren().add(label);
        }
        label = new Label();
        label.setFont(Font.font(18));
        label.setTextFill(Color.DARKBLUE);
        label.setText("can research these techs");
        infoBox.getChildren().add(label);
        // TODO (optional) میشه انکلیک زد رو این تک ها برای اینکه اد بشن برای ریسرچ
        for (Tech nextTech : nextTechs) {
            label = new Label();
            label.setFont(Font.font(18));
            label.setTextFill(Color.DARKBLUE);
            label.setText(nextTech.getTechName().getName());
            infoBox.getChildren().add(label);
        }
        label = new Label();
        label.setFont(Font.font(18));
        label.setTextFill(Color.DARKBLUE);
        label.setText("click here to open tech tree");
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
        ArrayList<City> cities = PlayGamePage.getInstance().getThisTurnPlayer().getCities();
        for (City city : cities) {
            Label label = new Label();
            label.setFont(Font.font(18));
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
        Label label = new Label();
        label.setFont(Font.font(18));
        label.setTextFill(Color.DARKBLUE);
        label.setText("city name : " + city.getName());
        infoBox.getChildren().add(label);

        label = new Label();
        label.setFont(Font.font(18));
        label.setTextFill(Color.DARKBLUE);
        label.setText("city health : " + city.getHealth());
        infoBox.getChildren().add(label);

        label = new Label();
        label.setFont(Font.font(18));
        label.setTextFill(Color.DARKBLUE);
        label.setText("city population : " + city.getMaxPopulation());
        infoBox.getChildren().add(label);

        label = new Label();
        label.setFont(Font.font(18));
        label.setTextFill(Color.DARKBLUE);
        label.setText("city food production : " + city.getFoodProduction());
        infoBox.getChildren().add(label);

        label = new Label();
        label.setFont(Font.font(18));
        label.setTextFill(Color.DARKBLUE);
        label.setText("city gold production : " + city.getGoldProduction());
        infoBox.getChildren().add(label);

        label = new Label();
        label.setFont(Font.font(18));
        label.setTextFill(Color.DARKBLUE);
        label.setText("city production created : " + city.getProduction());
        infoBox.getChildren().add(label);

        label = new Label();
        label.setFont(Font.font(18));
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

    public void troop() {
        clearBox();
        ArrayList<Unit> units = PlayGamePage.getInstance().getThisTurnPlayer().getUnits();
        for (Unit unit : units) {
            Label label = new Label();
            label.setFont(Font.font(12));
            label.setTextFill(Color.DARKBLUE);
            label.setText("troop on coordination x-" + PlayGamePage.getInstance().getThisTurnPlayer().getGameMap().getIndexI(unit.getPosition())
                    + " y-" + PlayGamePage.getInstance().getThisTurnPlayer().getGameMap().getIndexJ(unit.getPosition()));
            label.setOnMouseClicked(new EventHandler<MouseEvent>() {
                @Override
                public void handle(MouseEvent mouseEvent) {
                    // change selected unit :: todo ::
                }
            });
            infoBox.getChildren().add(label);
        }

    }

    public void clearBox() {
        infoBox.getChildren().clear();
    }

    public void diplomacy() {
        clearBox();
    }
}
