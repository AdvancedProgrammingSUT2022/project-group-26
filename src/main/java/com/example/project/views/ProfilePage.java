package com.example.project.views;

import com.example.project.controllers.Output;
import com.example.project.controllers.ProfileMenuController;
import com.example.project.models.DataBase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.File;
import java.net.MalformedURLException;
import java.nio.file.Paths;

public class ProfilePage {
    DataBase dataBase = DataBase.getInstance();
    ProfileMenuController profileMenuController = new ProfileMenuController();

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
            if (k.getCode().equals(KeyCode.ENTER)) changeNickName();
        });
        passwordTextField.setOnKeyPressed(k -> {
            if (k.getCode().equals(KeyCode.ENTER)) changePassword();
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
        MenuChanger.changeMenu("MainMenu");
    }

    public void changeNickName() {
        avatarsHBox.setVisible(false);

        Output message = profileMenuController.changeNickname(nickNameTextField.getText());
        if (message == Output.NICKNAME_CHANGED) {
            dataBase.getLoggedInUser().setNickname(nickNameTextField.getText());
        }
        nickNameTextField.clear();
        nickNameTextField.setPromptText(dataBase.getLoggedInUser().getNickname());
        new PopupMessage(Alert.AlertType.ERROR, message.toString());
    }

    public void changePassword() {
        avatarsHBox.setVisible(false);

        Output message = profileMenuController.changePassword(passwordTextField.getText());
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
        fc.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("JPG, PNG, JEPG Files", "*.jpg", "*.png", "*.jepg"));
        File selectedFile = fc.showOpenDialog(MenuChanger.getStage());
        if (selectedFile != null) {
            listView.getItems().add(selectedFile.getAbsoluteFile());
            String fileName = String.valueOf(listView.getItems());
            fileName = fileName.substring(1, fileName.length() - 1);
            System.out.println(fileName);
            try {
                dataBase.getLoggedInUser().setAvatarURL(Paths.get(fileName).toUri().toURL());
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
