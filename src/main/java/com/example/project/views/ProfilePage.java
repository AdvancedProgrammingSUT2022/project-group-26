package com.example.project.views;

import com.example.project.controllers.Output;
import com.example.project.controllers.ProfileMenuController;
import com.example.project.models.DataBase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;

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

    public void initialize() {
        setProfileData();
        nickNameTextField.setOnKeyPressed(k -> {
            if (k.getCode().equals(KeyCode.ENTER)) changeNickName();
        });
        passwordTextField.setOnKeyPressed(k -> {
            if (k.getCode().equals(KeyCode.ENTER)) changePassword();
        });

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
        Output message = profileMenuController.changeNickname(nickNameTextField.getText());
        if (message == Output.NICKNAME_CHANGED) {
            dataBase.getLoggedInUser().setNickname(nickNameTextField.getText());
        }
        nickNameTextField.clear();
        nickNameTextField.setPromptText(dataBase.getLoggedInUser().getNickname());
        new PopupMessage(Alert.AlertType.ERROR, message.toString());
    }

    public void changePassword() {
        Output message = profileMenuController.changePassword(passwordTextField.getText());
        if (message == Output.PASSWORD_CHANGED) {
            dataBase.getLoggedInUser().setPassword(passwordTextField.getText());
        }
        passwordTextField.clear();
        passwordTextField.setPromptText("*".repeat(dataBase.getLoggedInUser().getPassword().length()));
        new PopupMessage(Alert.AlertType.ERROR, message.toString());
    }

    public void changeProfilePicture(MouseEvent mouseEvent) {

    }

    public void chooseProfilePicture(ActionEvent actionEvent) {

    }

    public void nextTrack(MouseEvent mouseEvent) {
    }

    public void playPauseMusic(MouseEvent mouseEvent) {
    }

    public void muteUnmuteMusic(MouseEvent mouseEvent) {
    }
}
