package com.example.project.views;

import com.example.project.models.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Paths;

public class ProfilePage {
    private DataBase dataBase = DataBase.getInstance();

    @FXML
    private Label userNameLabel;
    @FXML
    private TextField nickNameTextField;
    @FXML
    private TextField passwordTextField;
    @FXML
    private ImageView profileImageView = new ImageView();
    @FXML
    private HBox avatarsHBox;

    @FXML
    private ImageView ImageView1;
    @FXML
    private ImageView ImageView2;
    @FXML
    private ImageView ImageView3;
    @FXML
    private ImageView ImageView4;

    private Image image1 = new Image(String.valueOf(AvatarEnums.AVATAR_1.getUrl()));
    private Image image2 = new Image(String.valueOf(AvatarEnums.AVATAR_2.getUrl()));
    private Image image3 = new Image(String.valueOf(AvatarEnums.AVATAR_3.getUrl()));
    private Image image4 = new Image(String.valueOf(AvatarEnums.AVATAR_4.getUrl()));

    private ListView listView = new ListView();


    public void initialize() {
        setProfileData();
        nickNameTextField.setOnKeyPressed(k -> {
            if (k.getCode().equals(KeyCode.ENTER)) {
                try {
                    changeNickName();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        passwordTextField.setOnKeyPressed(k -> {
            if (k.getCode().equals(KeyCode.ENTER)) {
                try {
                    changePassword();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        if (dataBase.getLoggedInUser().getAvatarURL() != null)
            profileImageView.setImage(new Image(String.valueOf(dataBase.getLoggedInUser().getAvatarURL())));

        ImageView1.setCursor(Cursor.HAND);
        ImageView2.setCursor(Cursor.HAND);
        ImageView3.setCursor(Cursor.HAND);
        ImageView4.setCursor(Cursor.HAND);

        ImageView1.setOnMouseClicked(event -> {
            profileImageView.setImage(image1);
            DataBase.getInstance().getLoggedInUser().setAvatarURL(AvatarEnums.AVATAR_1.getUrl());
            avatarsHBox.setVisible(false);
        });

        ImageView2.setOnMouseClicked(event -> {
            profileImageView.setImage(image2);
            DataBase.getInstance().getLoggedInUser().setAvatarURL(AvatarEnums.AVATAR_2.getUrl());
            avatarsHBox.setVisible(false);
        });
        ImageView3.setOnMouseClicked(event -> {
            profileImageView.setImage(image3);
            DataBase.getInstance().getLoggedInUser().setAvatarURL(AvatarEnums.AVATAR_3.getUrl());
            avatarsHBox.setVisible(false);
        });

        ImageView4.setOnMouseClicked(event -> {
            profileImageView.setImage(image4);
            DataBase.getInstance().getLoggedInUser().setAvatarURL(AvatarEnums.AVATAR_4.getUrl());
            avatarsHBox.setVisible(false);
        });
        avatarsHBox.setVisible(false);
    }

    private void setProfileData() {
        // set image
        userNameLabel.setText(dataBase.getLoggedInUser().getUsername());
        nickNameTextField.setPromptText(dataBase.getLoggedInUser().getNickname());
        passwordTextField.setPromptText("*".repeat(dataBase.getLoggedInUser().getPassword().length()));
    }

    public void back(MouseEvent mouseEvent) {
        Network.getInstance().sendRequestWithoutResponse(new Request(RequestEnum.BACK));
        MenuChanger.changeMenu("MainMenu");
    }

    public void changeNickName() throws IOException {
        avatarsHBox.setVisible(false);
        Request request = new Request(RequestEnum.CHANGE_NICKNAME);
        request.addToParams("nickname", nickNameTextField.getText());
        Output message = Network.getInstance().sendRequestAndGetResponseOutput(request);
        if (message == Output.NICKNAME_CHANGED) {
            dataBase.getLoggedInUser().setNickname(nickNameTextField.getText());
        }
        nickNameTextField.clear();
        nickNameTextField.setPromptText(dataBase.getLoggedInUser().getNickname());
        new PopupMessage(Alert.AlertType.ERROR, message.toString());
    }

    public void changePassword() throws IOException {
        avatarsHBox.setVisible(false);
        Request request = new Request(RequestEnum.CHANGE_PASSWORD);
        request.addToParams("password", passwordTextField.getText());
        Output message = Network.getInstance().sendRequestAndGetResponseOutput(request);
        if (message == Output.PASSWORD_CHANGED) {
            dataBase.getLoggedInUser().setPassword(passwordTextField.getText());
        }
        passwordTextField.clear();
        passwordTextField.setPromptText("*".repeat(dataBase.getLoggedInUser().getPassword().length()));
        new PopupMessage(Alert.AlertType.ERROR, message.toString());
    }

    public void changeProfilePicture(MouseEvent mouseEvent) {
        avatarsHBox.setVisible(true);
    }


    public void chooseProfilePicture(ActionEvent actionEvent) {
        avatarsHBox.setVisible(false);

        FileChooser fc = new FileChooser();
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("JPG, PNG, JPEG Files", "*.jpg", "*.png", "*.jpeg"));
        File selectedFile = fc.showOpenDialog(MenuChanger.getStage());
        if (selectedFile != null) {
            listView.getItems().add(selectedFile.getAbsoluteFile());
            String fileName = String.valueOf(listView.getItems());
            fileName = fileName.substring(1, fileName.length() - 1);
            try {
                dataBase.getLoggedInUser().setAvatarURL(Paths.get(fileName).toUri().toURL());
                Request request = new Request(RequestEnum.CHANGE_PROFILE_PICTURE);
                request.addToParams("url", Paths.get(fileName).toUri().toURL());
                Network.getInstance().sendRequestWithoutResponse(request);
                profileImageView.setImage(new Image(String.valueOf(Paths.get(fileName).toUri().toURL())));
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
            listView.getItems().clear();
        } else {
            System.out.println("file is not valid");
        }
    }

    public void nextTrack(MouseEvent mouseEvent) {
    }

    public void playPauseMusic(MouseEvent mouseEvent) {
    }

    public void muteUnmuteMusic(MouseEvent mouseEvent) {
    }
}
