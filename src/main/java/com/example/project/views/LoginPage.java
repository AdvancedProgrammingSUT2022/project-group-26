package com.example.project.views;

import com.example.project.controllers.LoginMenuController;
import com.example.project.controllers.Output;
import com.example.project.models.DataBase;
import com.example.project.models.User;
import com.example.project.models.UsersDatabase;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class LoginPage {

    private LoginMenuController loginMenuController = new LoginMenuController();
    private UsersDatabase usersDatabase = UsersDatabase.getInstance();
    private DataBase dataBase = DataBase.getInstance();

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
    public void initialize() {
        usernameFieldSignUp.setOnKeyPressed(k -> {
            if (k.getCode().equals(KeyCode.ENTER)) nicknameFieldSignUp.requestFocus();
        });
        nicknameFieldSignUp.setOnKeyPressed(k -> {
            if (k.getCode().equals(KeyCode.ENTER)) passwordFieldSignUp.requestFocus();
        });
        passwordFieldSignUp.setOnKeyPressed(k -> {
            if (k.getCode().equals(KeyCode.ENTER)) secondPasswordField.requestFocus();
        });
        secondPasswordField.setOnKeyPressed(k -> {
            if (k.getCode().equals(KeyCode.ENTER)){
                registerUser();
                usernameFieldSignUp.requestFocus();
            }
        });
        usernameFieldLogin.setOnKeyPressed(k -> {
            if (k.getCode().equals(KeyCode.ENTER)) {
                passwordFieldLogin.requestFocus();
            }
        });
        passwordFieldLogin.setOnKeyPressed(k -> {
            if (k.getCode().equals(KeyCode.ENTER)) {
                try {
                    loginUser();
                    usernameFieldLogin.requestFocus();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void registerUser() {
        Output message = loginMenuController.register(usernameFieldSignUp.getText(), nicknameFieldSignUp.getText(), passwordFieldSignUp.getText(), secondPasswordField.getText());
        // todo : should handel error and info!
        new PopupMessage(Alert.AlertType.ERROR, message.toString());
        if (message == Output.REGISTERED)
            usersDatabase.getUsers().add(new User(usernameFieldSignUp.getText(), passwordFieldSignUp.getText(), nicknameFieldSignUp.getText()));
        usernameFieldSignUp.clear();
        nicknameFieldSignUp.clear();
        passwordFieldSignUp.clear();
        secondPasswordField.clear();
    }

    public void loginUser() {
        Output message = loginMenuController.login(usernameFieldLogin.getText(), passwordFieldLogin.getText());
        // todo : should handel error and info!
        new PopupMessage(Alert.AlertType.ERROR, message.toString());
        if (message == Output.LOGGED_IN) {
            DataBase.getInstance().setLoggedInUser(UsersDatabase.getInstance().getUserByUsername(usernameFieldLogin.getText()));
            MenuChanger.changeMenu("MainMenu");
        }
        usernameFieldLogin.clear();
        passwordFieldLogin.clear();
    }

    public void exit(MouseEvent mouseEvent) {
        Platform.exit();
    }

    public void playPauseMusic(MouseEvent mouseEvent) {

    }

    public void muteUnmuteMusic(MouseEvent mouseEvent) {
    }

    public void nextTrack(MouseEvent mouseEvent) {
    }
}
