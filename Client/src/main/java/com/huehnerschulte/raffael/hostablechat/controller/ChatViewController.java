package com.huehnerschulte.raffael.hostablechat.controller;

import com.huehnerschulte.raffael.hostablechat.HostableChatApp;
import com.huehnerschulte.raffael.hostablechat.data.Account;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextFlow;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Timer;
import java.util.TimerTask;

public class ChatViewController {

    public ListView<String> channelList;
    public Label status;
    public Label chatTitle;
    public TextFlow chatFlowField;
    public TextField msgInputField;
    public Button sendButton;
    public Label connection;
    public Label accountLabel;
    public MenuItem menuAddChannel;
    public ScrollPane scrollPaneChat;

    public void initialize(){
        // void for now
    }
    public void handleSendButtonAction(ActionEvent actionEvent) {
        String messageWritten = msgInputField.getText();
        if (!messageWritten.isBlank()) {
            Label newMessage = new Label(messageWritten);
            newMessage.setFont(new Font(16));
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");
            String time = now.format(dateTimeFormatter);
            Label timestamp = new Label(time);
            VBox messageContainer = new VBox(newMessage, timestamp);
            messageContainer.setAlignment(Pos.CENTER_RIGHT);
            messageContainer.setPadding(new Insets(12,50,12,12));
            messageContainer.setFillWidth(true);
            messageContainer.setMinWidth(880);
            chatFlowField.getChildren().add(messageContainer);
            // add code for adding message to messaging queue
            msgInputField.clear();

            sendDummyResponse("Test Response");
        }
    }

    private void sendDummyResponse(String message) {
        Timer timer = new Timer();

        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                Platform.runLater(() -> {
                    Label newMessage = new Label(message);
                    newMessage.setFont(new Font(16));
                    LocalDateTime now = LocalDateTime.now();
                    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("HH:mm");
                    String time = now.format(dateTimeFormatter);
                    Label timestamp = new Label(time);
                    VBox messageContainer = new VBox(newMessage, timestamp);
                    messageContainer.setAlignment(Pos.CENTER_LEFT);
                    messageContainer.setPadding(new Insets(12));
                    messageContainer.setFillWidth(true);
                    messageContainer.setMinWidth(880);
                    chatFlowField.getChildren().add(messageContainer);
                });
            }
        }, 5000);
    }

    public void handleAddChannel(ActionEvent actionEvent) throws Exception {
        Stage addChannelStage = new Stage();
        addChannelStage.setResizable(false);
        addChannelStage.initModality(Modality.APPLICATION_MODAL);
        addChannelStage.setTitle("Add Channel");
        FXMLLoader fxmlLoader = new FXMLLoader(HostableChatApp.class.getResource("add-channel.fxml"));
        Scene scene = new Scene(fxmlLoader.load(),895,300);
        AddChannelController addChannelController = fxmlLoader.getController();
        addChannelController.setChatViewController(this);
        addChannelController.setStage(addChannelStage);
        addChannelStage.setScene(scene);
        addChannelStage.showAndWait();
    }

    public void updateStatusData(Account account) {
        Label status = this.status;
        Label chatTitle = this.chatTitle;
        Label connection = this.connection;
        Label accountLabel = this.accountLabel;
        ListView<String> channelList = this.channelList;
        TextFlow chatFlowField = this.chatFlowField;
        ScrollPane scrollPane = this.scrollPaneChat;
        // ScrollPane konfigurieren um automatisches scrollen zu haben
        scrollPane.setContent(chatFlowField);
        scrollPane.setFitToHeight(true);
        scrollPane.setFitToWidth(true);
        chatFlowField.heightProperty().addListener((observable, oldValue, newValue) -> {
            scrollPane.setVvalue(scrollPane.getVmax());
        });
        // Default Werte zum anzeigen
        status.setText("Active");
        chatTitle.setText("Test Chat");
        accountLabel.setText(account.getUserTag());
        connection.setText("Connected");
        ObservableList<String> channels = FXCollections.observableArrayList();
        channels.add("Test Chat");
        channels.add("Joshua Harder");
        channels.add("Fabio Schulze");
        channelList.setItems(channels);
        channelList.setOnMouseClicked(mouseEvent -> {
            String selectedChannel = channelList.getSelectionModel().getSelectedItem();
            handleChannelSelect(selectedChannel, chatTitle, chatFlowField);
        });
        Label userMessage = new Label("Das ist mein Text");
        userMessage.setFont(new Font(16));
        Label timestamp1 = new Label("19:35");
        Label partnerMessage = new Label("Das ist sein Text");
        partnerMessage.setFont(new Font(16));
        Label timestamp2 = new Label("19:36");
        VBox userMessageContainer = new VBox(userMessage, timestamp1);
        VBox partnerMessageContainer = new VBox(partnerMessage, timestamp2);
        userMessageContainer.setAlignment(Pos.CENTER_RIGHT);
        userMessageContainer.setPadding(new Insets(12,50,12,12));
        userMessageContainer.setFillWidth(true);
        userMessageContainer.setMinWidth(880);
        partnerMessageContainer.setPadding(new Insets(12));
        partnerMessageContainer.setAlignment(Pos.CENTER_LEFT);
        partnerMessageContainer.setFillWidth(true);
        partnerMessageContainer.setMinWidth(880);
        chatFlowField.getChildren().addAll(userMessageContainer, partnerMessageContainer);
    }

    private void handleChannelSelect(String selectedChannel, Label chatTitle, TextFlow chatFlowField) {
        chatTitle.setText(selectedChannel);
        chatFlowField.getChildren().clear();
    }
}
