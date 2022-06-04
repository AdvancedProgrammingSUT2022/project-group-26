package com.example.project.views;

import com.example.project.App;
import com.example.project.controllers.GlobalChatController;
import com.example.project.controllers.Output;
import com.example.project.models.DataBase;
import com.example.project.models.GlobalChat.Message;
import com.example.project.models.GlobalChat.PrivateChat;
import com.example.project.models.GlobalChat.PublicChat;
import com.example.project.models.User;
import com.example.project.models.WorldClock;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public class GlobalChatMenu {

    private static GlobalChatMenu instance;

    public static GlobalChatMenu getInstance() {
        if (instance == null) instance = new GlobalChatMenu();
        return instance;
    }


    private int chatMode = 0;//0: public chat, 1: private chat, 2: room
    private PrivateChat privateChat;

    @FXML
    private ScrollPane suggestionScrollPane;
    @FXML
    private TextField searchTextField;
    @FXML
    private VBox suggestionVBox;
    @FXML
    private ImageView photoOfChat;
    @FXML
    public Label labelOfDataChat;
    @FXML
    private HBox dataOfChatHBox;
    @FXML
    private ImageView searchButton;
    @FXML
    private ImageView sendMessageIcon;
    @FXML
    private HBox searchPanel;
    @FXML
    private VBox mainVBox;
    @FXML
    private Button changeToRoomChatButton;
    @FXML
    private Button changeToPrivateChatButton;
    @FXML
    private Button changeToPublicChatButton;
    @FXML
    private VBox rightBarVBox;
    private boolean isRightBarVBoxOpen = false;
    @FXML
    private Button changeMessageButton;
    @FXML
    private TextField editTextField;
    @FXML
    private VBox editMessage;
    @FXML
    private Button deleteForMe;
    @FXML
    private Button deleteForEveryone;
    @FXML
    private VBox deleteMessage;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox allMessages;
    @FXML
    private TextField textToSend;

    public void initialize() throws MalformedURLException {
        mainVBox.getChildren().remove(searchPanel);
        rightBarVBox.setVisible(false);
        setSelectedRightBoxButtonStyle(changeToPublicChatButton);
        scrollPane.vvalueProperty().bind(allMessages.heightProperty());
        allMessages.setSpacing(5);
        showPublicMessages();
    }

    private void setSelectedRightBoxButtonStyle(Button button) {
        button.setStyle("-fx-background-color: #aca9a9;" +
                "-fx-border-radius: 30 30 30 30;" +
                "-fx-background-radius: 30 30 30 30;");
    }

    private void setNotSelectedRightBoxButtonStyle(Button button) {
        button.setStyle("-fx-background-color: #efeaea;" +
                "-fx-border-radius: 30 30 30 30;" +
                "-fx-background-radius: 30 30 30 30;");
    }

    public void showPublicMessages() throws MalformedURLException {
        mainVBox.getChildren().clear();
        mainVBox.getChildren().add(scrollPane);
        allMessages.getChildren().clear();
        textToSend.setVisible(true);
        sendMessageIcon.setVisible(true);
        for (int i = 0; i < PublicChat.getInstance().getAllMessages().size(); i++) {
            Message message = PublicChat.getInstance().getAllMessages().get(i);
            if (!(message.getUser() == DataBase.getInstance().getLoggedInUser() && message.isDeletedForUser()))
                sendNewMessage(message);
            if (message.getUser() != DataBase.getInstance().getLoggedInUser())
                message.setSeen(true);
        }
    }

    public void showPrivateChats() throws MalformedURLException {
        mainVBox.getChildren().clear();
        mainVBox.getChildren().add(searchPanel);
        searchTextField.setVisible(false);
        mainVBox.getChildren().add(scrollPane);
        allMessages.getChildren().clear();
        textToSend.setVisible(false);
        sendMessageIcon.setVisible(false);
        showUserAllPrivateChats();
        searchButton.setOnMouseClicked(mouseEvent1 -> {
            searchTextField.setVisible(true);
            searchTextField.requestFocus();
            startForSearchingPrivateChat();
        });
    }

    private void goToAPrivateChat() throws MalformedURLException {
        mainVBox.getChildren().clear();
        mainVBox.getChildren().add(dataOfChatHBox);
        dataOfChatHBox.setPrefHeight(50);
        mainVBox.getChildren().add(scrollPane);
        scrollPane.setPrefHeight(570);
        allMessages.getChildren().clear();
        setDataOfChatHBoxPrivateChat();
        textToSend.setVisible(true);
        sendMessageIcon.setVisible(true);
        showMessagesOfPrivateChat();
    }

    private void setDataOfChatHBoxPrivateChat() throws MalformedURLException {
        labelOfDataChat.setText(privateChat.getOtherUser(DataBase.getInstance().getLoggedInUser()).getUsername());
        //TODO: avatar
        photoOfChat.setImage(new Image(String.valueOf(
                new URL(App.class.getResource("/Image/Menu/Icon/back.png").toString()))));
    }

    private void showMessagesOfPrivateChat() throws MalformedURLException {
        for (int i = 0; i < privateChat.getMessages().size(); i++) {
            Message message = privateChat.getMessages().get(i);
            if (!(message.getUser() == DataBase.getInstance().getLoggedInUser() && message.isDeletedForUser()))
                sendNewMessage(message);
            if (message.getUser() != DataBase.getInstance().getLoggedInUser())
                message.setSeen(true);
        }
    }

    private void showUserAllPrivateChats() throws MalformedURLException {
        for (int i = 0; i < DataBase.getInstance().getLoggedInUser().getPrivateChats().size(); i++)
            addAPrivateChat(DataBase.getInstance().getLoggedInUser().getPrivateChats().get(i));
    }

    private void setOnActionForPrivateChatsSearch() {

    }

    public void showRoomChats() throws MalformedURLException {
        allMessages.getChildren().clear();
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
        if (!GlobalChatController.getInstance().isValidShortMessage(textToSend.getText())) {
            new PopupMessage(Alert.AlertType.ERROR, Output.TEXT_FIELD_IS_EMPTY.toString());
        } else if (!GlobalChatController.getInstance().isValidLongMessage(textToSend.getText()))
            new PopupMessage(Alert.AlertType.ERROR, Output.LONG_MESSAGE.toString());
        else {
            Message message = new Message(DataBase.getInstance().getLoggedInUser(), textToSend.getText(), WorldClock.getTime());
            if (chatMode == 0)
                PublicChat.getInstance().addMessage(message);
            else if (chatMode == 1)
                this.privateChat.getMessages().add(message);
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
        if (message.getUser() == DataBase.getInstance().getLoggedInUser())
            addButtonToDelete(pane, message);
        if (message.getUser() == DataBase.getInstance().getLoggedInUser())
            addButtonToEdit(pane, message);
        return pane;
    }

    private void addButtonToEdit(Pane pane, Message message) throws MalformedURLException {
        ImageView imageView = new ImageView(new Image(String.valueOf(
                new URL(App.class.getResource("/Image/Menu/Icon/edit.png").toString()))));
        imageView.setFitHeight(16);
        imageView.setFitWidth(16);
        imageView.setLayoutX(288);
        imageView.setLayoutY(14);
        imageView.setCursor(Cursor.HAND);
        AtomicBoolean isSelectedForEdit = new AtomicBoolean(true);
        imageView.setOnMouseClicked(mouseEvent -> {
            if (isSelectedForEdit.get()) {
                isSelectedForEdit.set(false);
                pane.getChildren().add(editMessage);
                editMessage.setLayoutX(330);
                editMessage.setLayoutY(0);
                editTextField.setText(message.getMessage());
                editMessageClicked(pane, isSelectedForEdit, message);
                editMessage.setVisible(true);
                editTextField.requestFocus();
            } else {
                isSelectedForEdit.set(true);
                pane.getChildren().remove(editMessage);
            }
        });
        pane.getChildren().add(imageView);
    }

    private void editMessageClicked(Pane pane, AtomicBoolean isSelectedForEdit, Message message) {
        editTextField.setOnKeyPressed(k -> {
            if (k.getCode().equals(KeyCode.ENTER)) {
                if (editTextField.getText().length() > 20)
                    new PopupMessage(Alert.AlertType.ERROR, Output.LONG_MESSAGE.toString());
                else {
                    isSelectedForEdit.set(true);
                    if (pane.getChildren().get(1) instanceof Label) {
                        ((Label) pane.getChildren().get(1)).setText(editTextField.getText());
                    }
                    message.setMessage(editTextField.getText());
                    pane.getChildren().remove(editMessage);
                }
            }
        });
        changeMessageButton.setOnMouseClicked(mouseEvent -> {
            if (editTextField.getText().length() > 20)
                new PopupMessage(Alert.AlertType.ERROR, Output.LONG_MESSAGE.toString());
            else {
                isSelectedForEdit.set(true);
                if (pane.getChildren().get(1) instanceof Label) {
                    ((Label) pane.getChildren().get(1)).setText(editTextField.getText());
                }
                message.setMessage(editTextField.getText());
                pane.getChildren().remove(editMessage);
            }
        });
    }

    private void addButtonToDelete(Pane pane, Message message) throws MalformedURLException {
        ImageView imageView = new ImageView(new Image(String.valueOf(
                new URL(App.class.getResource("/Image/Menu/Icon/blackCross.png").toString()))));
        imageView.setFitHeight(15);
        imageView.setFitWidth(15);
        imageView.setLayoutX(310);
        imageView.setLayoutY(15);
        imageView.setCursor(Cursor.HAND);
        AtomicBoolean isSelectedForDelete = new AtomicBoolean(true);
        imageView.setOnMouseClicked(mouseEvent -> {
            if (isSelectedForDelete.get()) {
                isSelectedForDelete.set(false);
                pane.getChildren().add(deleteMessage);
                deleteMessage.setLayoutX(330);
                deleteMessage.setLayoutY(0);
                deleteMessageClicked(pane, isSelectedForDelete, message);
                deleteMessage.setVisible(true);
            } else {
                isSelectedForDelete.set(true);
                pane.getChildren().remove(deleteMessage);
            }
        });
        pane.getChildren().add(imageView);
    }

    private void deleteMessageClicked(Pane pane, AtomicBoolean isSelectedForDelete, Message message) {
        deleteForEveryone.setOnMouseClicked(mouseEvent -> {
            isSelectedForDelete.set(true);
            pane.getChildren().remove(deleteMessage);
            allMessages.getChildren().remove(pane);
            if (chatMode == 0)
                PublicChat.getInstance().getAllMessages().remove(message);
            if (chatMode == 1)
                privateChat.getMessages().remove(message);
        });
        deleteForMe.setOnMouseClicked(mouseEvent -> {
            isSelectedForDelete.set(true);
            pane.getChildren().remove(deleteMessage);
            allMessages.getChildren().remove(pane);
            message.setDeletedForUser(true);
        });
    }

    private void addSeenUnSeen(Pane pane, boolean isSeen) throws MalformedURLException {
        ImageView imageView;
        if (isSeen) {
            imageView = new ImageView(new Image(String.valueOf(
                    new URL(App.class.getResource("/Image/Menu/Icon/seen.png").toString()))));
            imageView.setFitHeight(20);
            imageView.setFitWidth(20);
            imageView.setLayoutX(305);
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
        label.setLayoutX(40);
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

    // chatMode
    public void changeToPublic(MouseEvent mouseEvent) throws MalformedURLException {
        if (chatMode != 0) {
            chatMode = 0;
            setSelectedRightBoxButtonStyle(changeToPublicChatButton);
            setNotSelectedRightBoxButtonStyle(changeToPrivateChatButton);
            setNotSelectedRightBoxButtonStyle(changeToRoomChatButton);
            showPublicMessages();
        }
    }

    public void openRightVBox(MouseEvent mouseEvent) {
        if (isRightBarVBoxOpen) {
            isRightBarVBoxOpen = false;
            rightBarVBox.setVisible(false);
        } else {
            isRightBarVBoxOpen = true;
            rightBarVBox.setVisible(true);
        }
    }


    public void changeToPrivate(MouseEvent mouseEvent) throws MalformedURLException {
        if (chatMode != 1) {
            chatMode = 1;
            setNotSelectedRightBoxButtonStyle(changeToPublicChatButton);
            setSelectedRightBoxButtonStyle(changeToPrivateChatButton);
            setNotSelectedRightBoxButtonStyle(changeToRoomChatButton);
            showPrivateChats();
        }
    }

    public void startForSearchingPrivateChat() {
        mainVBox.getChildren().clear();
        mainVBox.getChildren().add(searchPanel);
        mainVBox.getChildren().add(suggestionScrollPane);
        searchTextField.setOnKeyReleased(keyEvent -> {
            try {
                showSuggestionPrivateChats();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        });
        searchButton.setOnMouseClicked(mouseEvent -> {
            try {
                showPrivateChats();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        });
    }

    public void changeToRoom(MouseEvent mouseEvent) throws MalformedURLException {
        if (chatMode != 2) {
            chatMode = 2;
            setNotSelectedRightBoxButtonStyle(changeToPublicChatButton);
            setNotSelectedRightBoxButtonStyle(changeToPrivateChatButton);
            setSelectedRightBoxButtonStyle(changeToRoomChatButton);
            showRoomChats();
        }
    }

    //private chat
    private void addAPrivateChat(PrivateChat privateChat) throws MalformedURLException {
        Pane pane = new Pane();
        setMessagePaneSize(pane);
        setPrivateChatPaneStyle(pane);
        addUserAvatar(pane, privateChat.getOtherUser(DataBase.getInstance().getLoggedInUser()));
        addOtherUserLabelPrivateChat(pane, privateChat.getOtherUser(DataBase.getInstance().getLoggedInUser()));
        if (privateChat.getMessages().size() > 0)
            showLastMessage(pane, privateChat.getMessages().get(privateChat.getMessages().size() - 1).getMessage());
        pane.setCursor(Cursor.HAND);
        pane.setOnMouseClicked(mouseEvent -> {
            try {
                this.privateChat = privateChat;
                goToAPrivateChat();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        });
        allMessages.getChildren().add(pane);
    }

    private void setPrivateChatPaneStyle(Pane pane) {
        pane.setStyle("-fx-border-radius: 30 30 30 30;" +
                "-fx-background-radius: 30 30 30 30;" +
                "-fx-background-color: #48d816;");
    }

    private void showLastMessage(Pane pane, String message) {
        Label label = new Label(message);
        label.setPrefHeight(20);
        label.setLayoutX(50);
        label.setLayoutY(40);
        label.setStyle("-fx-font-family: \"High Tower Text\";" +
                "       -fx-font-size: 18");
        pane.getChildren().add(label);
    }

    private void addOtherUserLabelPrivateChat(Pane pane, User user) {
        Label label = new Label(user.getUsername());
        label.setPrefHeight(20);
        label.setLayoutX(50);
        label.setLayoutY(0);
        label.setStyle("-fx-font-family: \"High Tower Text\";" +
                "       -fx-font-size: 24");
        pane.getChildren().add(label);
    }

    public void backFromChat(MouseEvent mouseEvent) throws MalformedURLException {
        if (chatMode == 1)
            showPrivateChats();
        else if (chatMode == 2) {

        }
    }

    private void showSuggestionPrivateChats() throws MalformedURLException {
        suggestionVBox.getChildren().clear();
        if (searchTextField.getText().equals("")) {
            return;
        }
        ArrayList<User> suggestionUsers = GlobalChatController.getInstance().showUsernamesStartsWithString(searchTextField.getText());
        for (User user : suggestionUsers)
            addSuggestionPane(user);
    }

    private void addSuggestionPane(User user) throws MalformedURLException {
        Pane pane = new Pane();
        pane.setPrefHeight(50);
        pane.setStyle("-fx-background-color: #dfc10c;" +
                "-fx-border-radius: 30 30 30 30;" +
                "-fx-background-radius: 30 30 30 30;");
        addLabelToSuggestionPane(pane, user.getUsername());
        addAvatarToSuggestionPane(pane, user);
        pane.setCursor(Cursor.HAND);
        suggestionVBox.getChildren().add(pane);
        pane.setOnMouseClicked(mouseEvent -> {
            try {
                this.privateChat = GlobalChatController.getInstance().getUserPrivateChat(user);
                goToAPrivateChat();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        });
    }

    private void addLabelToSuggestionPane(Pane pane, String username) {
        Label label = new Label(username);
        label.setPrefHeight(20);
        label.setLayoutX(70);
        label.setLayoutY(10);
        label.setStyle("-fx-font-family: \"High Tower Text\";" +
                "       -fx-font-size: 18");
        pane.getChildren().add(label);
    }

    private void addAvatarToSuggestionPane(Pane pane, User user) throws MalformedURLException {
        ImageView imageView = new ImageView(new Image(String.valueOf(
                new URL(App.class.getResource("/Image/Menu/Icon/back.png").toString()))));
        imageView.setFitHeight(30);
        imageView.setFitWidth(30);
        imageView.setLayoutX(20);
        imageView.setLayoutY(8);
        pane.getChildren().add(imageView);
    }
}