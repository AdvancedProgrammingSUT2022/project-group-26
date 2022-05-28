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
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.net.MalformedURLException;
import java.net.URL;

public class GlobalChat {

    private static GlobalChat instance;

    public static GlobalChat getInstance() {
        if (instance == null) instance = new GlobalChat();
        return instance;
    }

    private User user = new User("ilya", "ilya", "ilya");

    @FXML
    private VBox allMessages;
    @FXML
    private TextField textToSend;

    public void initialize() {
        allMessages.setSpacing(5);
    }

    public void enter(KeyEvent keyEvent) throws MalformedURLException {
        if (keyEvent.getCode().toString().equals("ENTER")) {
            keyEvent.consume();
            send(null);
        }
    }

    public void back(MouseEvent mouseEvent) {
    }

    public void send(MouseEvent mouseEvent) throws MalformedURLException {
        if (textToSend.getText().length() == 0) {
            new PopupMessage(Alert.AlertType.ERROR, Output.TEXT_FIELD_IS_EMPTY.toString());
        } else {
            Pane pane = getMessageHBox();
            allMessages.getChildren().add(pane);
            textToSend.clear();
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
        text.setLayoutX(40);
        text.setLayoutY(15);
        text.setFont(Font.font(18));
        addUserUsername(pane);
        pane.getChildren().add(text);
    }

    private void addUserUsername(Pane pane) {
        Label label = new Label("username: " + GlobalChat.getInstance().getUser().getUsername());
        label.setPrefHeight(20);
        label.setLayoutX(30);
        label.setLayoutY(10);
        label.setFont(Font.font(12));
        label.setStyle("-fx-font-family: \"High Tower Text\"");
        pane.getChildren().add(label);
    }

    private void addUserAvatar(Pane pane) throws MalformedURLException {
        //TODO: add url of user
        ImageView imageView = new ImageView(new Image(String.valueOf(
                new URL(App.class.getResource("/Image/Menu/Icon/back.png").toString()))));
        imageView.setFitHeight(30);
        imageView.setFitWidth(30);
        imageView.setX(5);
        imageView.setY(25);
        pane.getChildren().add(imageView);
    }

    private void setMessagePaneSize(Pane pane) {
        pane.setPrefWidth(100);
        pane.setPrefHeight(80);
        pane.setStyle("-fx-border-radius: 30 30 30 30;" +
                "-fx-background-radius: 30 30 30 30;" +
                "-fx-background-color: #ff9900;");
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
