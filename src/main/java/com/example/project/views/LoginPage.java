package com.example.project.views;

import com.example.project.controllers.LoginMenuController;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.Objects;

public class LoginPage {
    @FXML
    private TextField usernameFieldSignUp;
    @FXML
    private PasswordField passwordFieldSignUp;
    @FXML
    private TextField usernameFieldLogin;
    @FXML
    private PasswordField passwordFieldLogin;
    @FXML
    private PasswordField secondPasswordField;
    @FXML
    private TextField nicknameFieldSignUp;
    @FXML
    private ImageView playPauseMusicButton;
    @FXML
    private ImageView muteUnmuteButton;
    @FXML
    private BorderPane mainPane;
    @FXML
    private Pane secondPane;
    @FXML
    private ImageView exitButton;


    public void registerUser(MouseEvent mouseEvent) {
    }

    public void loginUser(MouseEvent mouseEvent) {
    }

    public void exit(MouseEvent mouseEvent) {
    }

    public void playPauseMusic(MouseEvent mouseEvent) {
    }

    public void muteUnmuteMusic(MouseEvent mouseEvent) {
    }

    public void nextTrack(MouseEvent mouseEvent) {
    }
}
