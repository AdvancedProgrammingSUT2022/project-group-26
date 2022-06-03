package com.example.project.views;

import com.example.project.App;
import com.example.project.controllers.Output;
import com.example.project.models.DataBase;
import com.example.project.models.GlobalChat.Message;
import com.example.project.models.GlobalChat.PublicChat;
import com.example.project.models.User;
import com.example.project.models.WorldClock;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
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

public class GlobalChatMenu {

    private static GlobalChatMenu instance;

    public static GlobalChatMenu getInstance() {
        if (instance == null) instance = new GlobalChatMenu();
        return instance;
    }


    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox allMessages;
    @FXML
    private TextField textToSend;

    public void initialize() throws MalformedURLException {
        scrollPane.vvalueProperty().bind(allMessages.heightProperty());
        allMessages.setSpacing(5);
        for (int i = 0; i < PublicChat.getInstance().getAllMessages().size(); i++) {
            sendNewMessage(PublicChat.getInstance().getAllMessages().get(i));
            if (PublicChat.getInstance().getAllMessages().get(i).getUser() != DataBase.getInstance().getLoggedInUser())
                PublicChat.getInstance().getAllMessages().get(i).setSeen(true);
        }
    }

    public void enter(KeyEvent keyEvent) throws MalformedURLException {
        if (keyEvent.getCode().toString().equals("ENTER")) {
            keyEvent.consume();
            send(null);
        }
    }

    public void back(MouseEvent mouseEvent) {
        MenuChanger.changeMenu("MainMenu");
    }

    public void send(MouseEvent mouseEvent) throws MalformedURLException {
        if (textToSend.getText().length() == 0) {
            new PopupMessage(Alert.AlertType.ERROR, Output.TEXT_FIELD_IS_EMPTY.toString());
        } else if (textToSend.getText().length() > 20)
            new PopupMessage(Alert.AlertType.ERROR, Output.LONG_MESSAGE.toString());
        else {
            Message message = new Message(DataBase.getInstance().getLoggedInUser(), textToSend.getText(), WorldClock.getTime());
            PublicChat.getInstance().addMessage(message);
            sendNewMessage(message);
            textToSend.clear();
        }
    }

    private void sendNewMessage(Message message) throws MalformedURLException {
        Pane pane = getMessageBox(message);
        allMessages.getChildren().add(pane);
    }

    private Pane getMessageBox(Message message) throws MalformedURLException {
        Pane pane = new Pane();
        setMessagePaneSize(pane);
        addUserAvatar(pane, message.getUser());
        addText(pane, message.getMessage());
        addUserUsername(pane, message.getUser());
        addClock(pane, message.getClock());
        if (message.getUser() == DataBase.getInstance().getLoggedInUser())
            addSeenUnSeen(pane, message.isSeen());
        addButtonToDelete(pane);
        return pane;
    }

    private void addButtonToDelete(Pane pane) throws MalformedURLException {
        ImageView imageView = new ImageView(new Image(String.valueOf(
                new URL(App.class.getResource("/Image/Menu/Icon/redCross.png").toString()))));
        imageView.setFitHeight(20);
        imageView.setFitWidth(20);
        imageView.setLayoutX(300);
        imageView.setLayoutY(20);
        pane.getChildren().add(imageView);
    }

    private void addSeenUnSeen(Pane pane, boolean isSeen) throws MalformedURLException {
        ImageView imageView;
        if (isSeen) {
            imageView = new ImageView(new Image(String.valueOf(
                    new URL(App.class.getResource("/Image/Menu/Icon/seen.png").toString()))));
            imageView.setFitHeight(20);
            imageView.setFitWidth(20);
            imageView.setLayoutX(300);
            imageView.setLayoutY(45);
        } else {
            imageView = new ImageView(new Image(String.valueOf(
                    new URL(App.class.getResource("/Image/Menu/Icon/unseen.png").toString()))));
            imageView.setFitHeight(10);
            imageView.setFitWidth(10);
            imageView.setLayoutX(300);
            imageView.setLayoutY(50);
        }
        pane.getChildren().add(imageView);
    }

    private void addClock(Pane pane, String stringClock) {
        Label clock = new Label(stringClock);
        clock.setPrefHeight(60);
        clock.setPrefWidth(60);
        clock.setLayoutX(260);
        clock.setLayoutY(25);
        pane.getChildren().add(clock);
    }

    private void addText(Pane pane, String message) {
        Label text = new Label(message);
        text.setPrefHeight(20);
        text.setPrefHeight(60);
        text.setLayoutX(40);
        text.setLayoutY(15);
        text.setFont(Font.font(18));
        pane.getChildren().add(text);
    }

    private void addUserUsername(Pane pane, User user) {
        Label label = new Label("player: " + user.getUsername());
        label.setPrefHeight(20);
        label.setLayoutX(30);
        label.setLayoutY(10);
        label.setFont(Font.font(12));
        label.setStyle("-fx-font-family: \"High Tower Text\"");
        pane.getChildren().add(label);
    }

    private void addUserAvatar(Pane pane, User user) throws MalformedURLException {
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
                "-fx-background-color: #927819;");
    }
}
