package com.huehnerschulte.raffael.hostablechat.internal.data.documents;

import com.huehnerschulte.raffael.hostablechat.internal.data.documents.field.Credentials;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.mongodb.core.mapping.Document;

@EqualsAndHashCode(callSuper = true)
@Document
@Data
public class Account extends BaseDocument{

    private Credentials credentials;

    private String role;

    private String displayName;

    private String userTag;

    private List<>


}
