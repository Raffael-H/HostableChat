package com.huehnerschulte.raffael.hostablechat.internal.data.documents;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document
@Data
public class BaseDocument {

    @Id
    private String id;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;
}
