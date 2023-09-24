package com.huehnerschulte.raffael.hostablechat.controller;

import com.huehnerschulte.raffael.hostablechat.HostableChatApp;
import com.huehnerschulte.raffael.hostablechat.data.Account;
import com.huehnerschulte.raffael.hostablechat.manager.AccountManager;
import com.huehnerschulte.raffael.hostablechat.manager.TokenManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import okhttp3.*;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.IOException;
import java.io.StringReader;

public class LoginController {
    public Button loginButton;
    public Label statusMessage;
    public MenuItem about;
    public MenuItem preferences;
    public TextField usernameInput;
    public PasswordField passwordInput;
    private Stage loginStage;

    public void handleLogin(ActionEvent actionEvent) throws Exception {
        String username = usernameInput.getText();
        String password = passwordInput.getText();
        sendAuthenticationRequest(username,password);
        if (TokenManager.getInstance().getToken() != null){
            switchToChatScreen();
        } else {
            statusMessage.setText("Wrong credentials!");
            statusMessage.setVisible(true);
        }
    }

    private void sendAuthenticationRequest(String username, String password) throws IOException {
         OkHttpClient client = new OkHttpClient();

        // Erstelle eine JSON-Request-Body mit den erforderlichen Login-Daten
        MediaType mediaType = MediaType.parse("application/json");

        JsonObject loginRequest = Json.createObjectBuilder()
                .add("username", username)
                .add("password", password)
                .build();
        RequestBody body = RequestBody.create(mediaType, loginRequest.toString());
        // Erstelle den Request
        Request request = new Request.Builder()
                .url("http://localhost:8080/api/authenticate") // Ersetze mit der tatsächlichen URL
                .post(body)
                .addHeader("Content-Type", "application/json")
                .build();

        // Sende den Request und erhalte die Response
        Response response = client.newCall(request).execute();
        // Überprüfe die Response
        if (response.isSuccessful()) {
            // Wenn die Anfrage erfolgreich war, hier die Verarbeitung der Antwort
            String responseBody = response.body().string();
            JsonReader jsonReader = Json.createReader(new StringReader(responseBody));
            JsonObject jsonObject = jsonReader.readObject();
            String token = jsonObject.getString("token");
            JsonObject accountJson = jsonObject.getJsonObject("account");
            Account account = Account.fromJsonObject(accountJson);
            System.out.println("Erfolgreich eingeloggt! Antwort vom Server: " + token);
            TokenManager.getInstance().saveToken(token);
            AccountManager.getInstance().setAccount(account);
        } else {
            // Wenn die Anfrage nicht erfolgreich war
            System.out.println("Fehler beim Einloggen. HTTP-Code: " + response.code());
        }

        // Schließe die Response
        response.close();
    }

    private void switchToChatScreen() throws Exception {
        FXMLLoader loader = new FXMLLoader(HostableChatApp.class.getResource("chat-screen.fxml"));
        Parent root = loader.load();
        ChatViewController chatViewController = loader.getController();
        Scene chatViewScene = new Scene(root);
        Stage chatViewStage = new Stage();
        chatViewStage.setResizable(false);
        chatViewStage.setTitle("HostableChat");
        chatViewStage.setScene(chatViewScene);
        chatViewController.updateStatusData(AccountManager.getInstance().getAccount());
        chatViewStage.show();
        loginStage.close();
    }
    public void handleOpenPreferences(ActionEvent actionEvent) {
        Stage preferences = new Stage();
        preferences.setResizable(false);
        preferences.initModality(Modality.APPLICATION_MODAL);
        preferences.setTitle("Preferences");
        FXMLLoader fxmlLoader = new FXMLLoader(HostableChatApp.class.getResource("preferences.fxml"));
        Scene scene;
        try {
            scene = new Scene(fxmlLoader.load(),600,400);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        ConfigController configController = fxmlLoader.getController();
        configController.updateData();
        preferences.setScene(scene);
        preferences.showAndWait();
    }

    public void setStage(Stage stage) {
        this.loginStage = stage;
    }
}
