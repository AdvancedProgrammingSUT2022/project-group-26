package com.example.project.views;

import com.example.project.App;
import com.example.project.controllers.Output;
import com.example.project.models.User;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

import java.net.MalformedURLException;
import java.net.URL;

public class GlobalChat {

    private static User user = new User("ilya", "ilya", "ilya");

    @FXML
    private VBox allMessages;
    @FXML
    private TextField textToSend;

    public void initialize() {
        allMessages.setSpacing(5);
    }


    public void back(MouseEvent mouseEvent) {
    }

    public void send(MouseEvent mouseEvent) throws MalformedURLException {
        if (textToSend.getText().length() == 0) {
            new PopupMessage(Alert.AlertType.ERROR, Output.TEXT_FIELD_IS_EMPTY.toString());
        } else {
            Pane pane = getMessageHBox();
            allMessages.getChildren().add(pane);
        }
    }

    private Pane getMessageHBox() throws MalformedURLException {
        Pane pane = new Pane();
        setMessagePaneSize(pane);
        addUserAvatar(pane);
        addText(pane);
        return pane;
    }

    private void addText(Pane pane) {
        Label text = new Label(textToSend.getText());
        text.setPrefHeight(20);
        text.setPrefHeight(60);
        text.setLayoutX(100);
        text.setLayoutY(10);
        pane.getChildren().add(text);
    }

    private void addUserAvatar(Pane pane) throws MalformedURLException {
        //TODO: add url of user
        ImageView imageView = new ImageView(new Image(String.valueOf(
                new URL(App.class.getResource("/Image/Menu/Icon/back.png").toString()))));
        imageView.setFitHeight(30);
        imageView.setFitWidth(30);
        imageView.setX(5);
        imageView.setY(5);
        pane.getChildren().add(imageView);
    }

    private void setMessagePaneSize(Pane pane) {
        pane.setPrefWidth(100);
        pane.setPrefHeight(50);
        pane.setStyle("-fx-background-color: blue");
    }


}
