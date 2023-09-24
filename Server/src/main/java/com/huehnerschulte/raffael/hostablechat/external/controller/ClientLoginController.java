package com.huehnerschulte.raffael.hostablechat.external.controller;

import com.huehnerschulte.raffael.hostablechat.external.data.representation.LoginRequest;
import com.huehnerschulte.raffael.hostablechat.external.data.representation.LoginResponse;
import com.huehnerschulte.raffael.hostablechat.external.service.AuthenticationService;
import com.huehnerschulte.raffael.hostablechat.internal.data.representation.AccountDto;
import com.huehnerschulte.raffael.hostablechat.internal.service.data.AccountService;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
public class ClientLoginController {

    private final AuthenticationService authenticationService;
    private final AccountService accountService;

    public ClientLoginController(AuthenticationService authenticationService, AccountService accountService) {
        this.authenticationService = authenticationService;
        this.accountService = accountService;
    }

    @PostMapping(value = "/api/authenticate", produces = MediaType.APPLICATION_JSON_VALUE)
    public LoginResponse handleLoginRequest(@RequestBody LoginRequest loginRequest){
        String jwtToken = authenticationService.authenticate(loginRequest);
        if (jwtToken == null){
            throw new UsernameNotFoundException("User not found.");
        }
        AccountDto accountDto = accountService.fetchAccount(loginRequest.getUsername());
        return new LoginResponse(jwtToken,accountDto);
    }
}
