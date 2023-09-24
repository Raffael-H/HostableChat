package com.huehnerschulte.raffael.hostablechat.internal.data.documents.field;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;

@Data
@AllArgsConstructor
public class Credentials {

    @Indexed
    private String username;

    private String password;
}
