package com.huehnerschulte.raffael.hostablechat.external.data.representation;

import lombok.Data;

@Data
public class LoginRequest {

    private String username;

    private String password;
}
