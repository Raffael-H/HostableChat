package com.huehnerschulte.raffael.hostablechat.internal.data.documents;

import com.huehnerschulte.raffael.hostablechat.internal.data.documents.field.ChatHistory;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Document
@Data
public class Channel extends BaseDocument {

    private String name;
    private List<Account> participants = new ArrayList<>(2);
    private ChatHistory history;

}
