package com.huehnerschulte.raffael.hostablechat.internal.data.converter;

import com.huehnerschulte.raffael.hostablechat.internal.data.documents.Account;
import com.huehnerschulte.raffael.hostablechat.internal.data.representation.AccountDto;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class AccountConverter implements Converter<Account, AccountDto> {

    @Override
    public AccountDto convert(Account source) {
        Objects.requireNonNull(source);
        AccountDto accountDto = new AccountDto();
        accountDto.setUsername(source.getCredentials().getUsername());
        accountDto.setPassword(source.getCredentials().getPassword());
        accountDto.setDisplayName(source.getDisplayName());
        accountDto.setUserTag(source.getUserTag());
        accountDto.setIsAdmin(source.getRole().equals("ADMIN"));
        accountDto.setCreatedDate(source.getCreatedDate());
        accountDto.setUpdatedDate(source.getUpdatedDate());
        accountDto.setId(source.getId());
        return accountDto;
    }

    public List<AccountDto> convertAll(List<Account> accounts) {
        return accounts.stream().map(this::convert).toList();
    }
}
