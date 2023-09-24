package com.huehnerschulte.raffael.hostablechat.internal.service.auth;

import com.huehnerschulte.raffael.hostablechat.internal.data.documents.Account;
import com.huehnerschulte.raffael.hostablechat.internal.data.repository.AccountRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class HostableChatUserDetailsService implements UserDetailsService {

    private final AccountRepository accountRepository;
    public HostableChatUserDetailsService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username was not found."));

        return User.withUsername(account.getCredentials().getUsername())
                .password(account.getCredentials().getPassword())
                .roles(account.getRole())
                .build();
    }
}
