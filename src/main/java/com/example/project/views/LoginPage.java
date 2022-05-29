package com.example.project.views;

import com.example.project.controllers.LoginMenuController;
import com.example.project.controllers.Output;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.util.Objects;

public class LoginPage {
    LoginMenuController loginMenuController = new LoginMenuController();

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


    @FXML
    public void initialize() {
        usernameFieldSignUp.setOnKeyPressed(k -> {
            if (k.getCode().equals(KeyCode.ENTER)) registerUser();
        });
        nicknameFieldSignUp.setOnKeyPressed(k -> {
            if (k.getCode().equals(KeyCode.ENTER)) registerUser();
        });
        passwordFieldSignUp.setOnKeyPressed(k -> {
            if (k.getCode().equals(KeyCode.ENTER)) registerUser();
        });
        secondPasswordField.setOnKeyPressed(k -> {
            if (k.getCode().equals(KeyCode.ENTER)) registerUser();
        });
        usernameFieldLogin.setOnKeyPressed(k -> {
            if (k.getCode().equals(KeyCode.ENTER)) {
                try {
                    loginUser();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        passwordFieldLogin.setOnKeyPressed(k -> {
            if (k.getCode().equals(KeyCode.ENTER)) {
                try {
                    loginUser();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void registerUser() {
        Output massage = loginMenuController.register(usernameFieldSignUp.getText(), nicknameFieldSignUp.getText(), passwordFieldSignUp.getText(), secondPasswordField.getText());
        // pop up
        // do the thing
        usernameFieldSignUp.clear();
        nicknameFieldSignUp.clear();
        passwordFieldSignUp.clear();
        secondPasswordField.clear();
    }

    public void loginUser() {
        Output message = loginMenuController.login(usernameFieldLogin.getText(), passwordFieldLogin.getText());
        // pop up
        // do the thing
        usernameFieldLogin.clear();
        passwordFieldLogin.clear();
    }

    public void exit(MouseEvent mouseEvent) {
        // pop up -- confirmation
        Platform.exit();
    }

    public void playPauseMusic(MouseEvent mouseEvent) {

    }

    public void muteUnmuteMusic(MouseEvent mouseEvent) {
    }

    public void nextTrack(MouseEvent mouseEvent) {
    }
}
