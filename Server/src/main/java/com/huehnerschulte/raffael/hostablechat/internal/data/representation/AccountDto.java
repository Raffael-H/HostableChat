package com.huehnerschulte.raffael.hostablechat.internal.data.representation;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AccountDto {

    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    private String displayName;

    private String userTag;

    private Boolean isAdmin;

    private LocalDateTime createdDate;

    private LocalDateTime updatedDate;

    private String id;

}
