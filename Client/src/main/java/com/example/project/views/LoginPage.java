package com.example.project.views;

import com.example.project.models.*;
import com.google.gson.Gson;
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

import java.io.IOException;


public class LoginPage {
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
            if (k.getCode().equals(KeyCode.ENTER)) nicknameFieldSignUp.requestFocus();
        });
        nicknameFieldSignUp.setOnKeyPressed(k -> {
            if (k.getCode().equals(KeyCode.ENTER)) passwordFieldSignUp.requestFocus();
        });
        passwordFieldSignUp.setOnKeyPressed(k -> {
            if (k.getCode().equals(KeyCode.ENTER)) secondPasswordField.requestFocus();
        });
        secondPasswordField.setOnKeyPressed(k -> {
            if (k.getCode().equals(KeyCode.ENTER)) {
                try {
                    registerUser();
                } catch (IOException e) {
                    e.printStackTrace();
                }
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

    public void registerUser() throws IOException {
        Request request = new Request(RequestEnum.REGISTER_USER);
        request.addToParams("username", usernameFieldSignUp.getText());
        request.addToParams("nickname", nicknameFieldSignUp.getText());
        request.addToParams("password", passwordFieldSignUp.getText());
        request.addToParams("confirm password", secondPasswordField.getText());
        Output output = Network.getInstance().sendRequestAndGetResponseOutput(request);
        new PopupMessage(Alert.AlertType.INFORMATION, output.toString());
        usernameFieldSignUp.clear();
        nicknameFieldSignUp.clear();
        passwordFieldSignUp.clear();
        secondPasswordField.clear();
    }

    public void loginUser() throws IOException {
        Request request = new Request(RequestEnum.LOGIN_USER);
        request.addToParams("username", usernameFieldLogin.getText());
        request.addToParams("password", passwordFieldLogin.getText());
        Response response = Network.getInstance().sendRequestAndGetResponse(request);

        new PopupMessage(Alert.AlertType.INFORMATION, response.getOutput().toString());

        if (response.getOutput() == Output.LOGGED_IN) {
            Gson gson = new Gson();
            DataBase.getInstance().setLoggedInUser(gson.fromJson(response.getData(), User.class));
            MenuChanger.changeMenu("MainMenu");
        }
        usernameFieldLogin.clear();
        passwordFieldLogin.clear();
    }

    public void exit(MouseEvent mouseEvent) throws IOException {
        Network.getInstance().sendRequestAndGetResponseOutput(new Request(RequestEnum.BACK));
        Platform.exit();
    }

    public void playPauseMusic(MouseEvent mouseEvent) {
    }

    public void muteUnmuteMusic(MouseEvent mouseEvent) {
    }

    public void nextTrack(MouseEvent mouseEvent) {
    }
}
