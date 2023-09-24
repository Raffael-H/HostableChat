package com.huehnerschulte.raffael.hostablechat.external.data.representation;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.huehnerschulte.raffael.hostablechat.internal.data.representation.AccountDto;
import lombok.Data;

@Data
public class LoginResponse {

    private final String token;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private final AccountDto account;

    public LoginResponse(String token) {
        this.token = token;
        this.account = null;
    }

    public LoginResponse(String token, AccountDto accountDto) {
        this.token = token;
        this.account = accountDto;
    }
}
