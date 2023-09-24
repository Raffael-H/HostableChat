package com.huehnerschulte.raffael.hostablechat.internal.data.documents.field;

import lombok.Data;

@Data
public class ChatMessage {

    private String sender;
    private String text;
    private String time;
}
