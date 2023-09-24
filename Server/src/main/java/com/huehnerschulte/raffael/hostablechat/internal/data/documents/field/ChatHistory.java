package com.huehnerschulte.raffael.hostablechat.internal.data.documents.field;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ChatHistory {

    private List<ChatMessage> messages = new ArrayList<>(0);
}
