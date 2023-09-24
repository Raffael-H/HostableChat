package com.huehnerschulte.raffael.hostablechat.internal.service.data;

import com.huehnerschulte.raffael.hostablechat.internal.data.converter.AccountConverter;
import com.huehnerschulte.raffael.hostablechat.internal.data.documents.Account;
import com.huehnerschulte.raffael.hostablechat.internal.data.documents.field.Credentials;
import com.huehnerschulte.raffael.hostablechat.internal.data.repository.AccountRepository;
import com.huehnerschulte.raffael.hostablechat.internal.data.representation.AccountDto;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Component
public class AccountService {

    private final AccountRepository accountRepository;
    private final AccountConverter accountConverter;

    public AccountService(AccountRepository accountRepository, AccountConverter accountConverter) {
        this.accountRepository = accountRepository;
        this.accountConverter = accountConverter;
    }

    public boolean verifyAdminAccount(String adminName) {
        if (accountRepository.count() > 0){
            return accountRepository.findByUsername(adminName).isPresent();
        }
        return false;
    }

    public AccountDto createAccount(String username, String password, String role, String displayName, String userTag) throws NullPointerException, IllegalArgumentException {
        Objects.requireNonNull(username);
        Objects.requireNonNull(password);
        Objects.requireNonNull(role);
        Objects.requireNonNull(displayName);
        Objects.requireNonNull(userTag);
        if (validateStrings(username, password, role, displayName, userTag)){
            Account account = new Account();
            account.setCredentials(new Credentials(username, password));
            account.setRole(role);
            account.setDisplayName(displayName);
            account.setUserTag(userTag);
            account.setCreatedDate(LocalDateTime.now());
            account.setUpdatedDate(LocalDateTime.now());
            return accountConverter.convert(accountRepository.save(account));
        } else {
            throw new IllegalArgumentException("Parameters must not be null.");
        }
    }

    private boolean validateStrings(String... strings) {
        List<String> list = Arrays.stream(strings).dropWhile(String::isBlank).toList();
        return list.size() == strings.length;
    }

    public AccountDto fetchAccount(String username) {
        return accountConverter.convert(accountRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User with Username" + username + " not found.")));
    }

    public long fetchAccountCount() {
        return accountRepository.count();
    }

    public List<AccountDto> fetchAllAccounts() {
        return accountConverter.convertAll(accountRepository.findAll());
    }
}
