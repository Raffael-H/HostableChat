package com.huehnerschulte.raffael.hostablechat.controller;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddChannelController {

    public TextField addChannelInput;
    private ChatViewController chatViewController;
    private Stage addChannelStage;

    public void handleAddChannel(ActionEvent actionEvent) {
        ListView<String> channelList = chatViewController.channelList;
        ObservableList<String> channelListItems = channelList.getItems();
        // Hier code ergänzen für Server Kommunikation. (Channel speichern etc.)
        channelListItems.add(addChannelInput.getText());
        channelList.setItems(channelListItems);
        addChannelStage.close();
    }
    // Kann zukünftig durch das Mediator-Pattern womöglich abgelöst werden
    public void setChatViewController(ChatViewController chatViewController) {
        this.chatViewController = chatViewController;
    }

    public void setStage(Stage addChannelStage) {
        this.addChannelStage = addChannelStage;
    }
}
