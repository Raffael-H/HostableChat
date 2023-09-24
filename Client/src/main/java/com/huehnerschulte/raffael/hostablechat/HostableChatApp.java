package com.huehnerschulte.raffael.hostablechat;

import com.huehnerschulte.raffael.hostablechat.controller.LoginController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HostableChatApp extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HostableChatApp.class.getResource("login-screen.fxml"));
        Parent root = fxmlLoader.load();
        LoginController loginController = fxmlLoader.getController();
        Scene scene = new Scene(root, 1280, 800);
        stage.setTitle("HostableChat");
        stage.setScene(scene);
        stage.setResizable(false);
        loginController.setStage(stage);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}