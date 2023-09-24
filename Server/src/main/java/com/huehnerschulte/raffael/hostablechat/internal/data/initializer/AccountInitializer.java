package com.huehnerschulte.raffael.hostablechat.internal.data.initializer;

import com.huehnerschulte.raffael.hostablechat.internal.data.representation.AccountDto;
import com.huehnerschulte.raffael.hostablechat.internal.service.data.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.passay.CharacterData;
import org.passay.CharacterRule;
import org.passay.EnglishCharacterData;
import org.passay.PasswordGenerator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class AccountInitializer implements ApplicationListener<ApplicationReadyEvent> {

    private final AccountService accountService;

    @Value(value = "${preferred.admin.name:admin@hostablechat.com}")
    private String adminName;
    public AccountInitializer(AccountService accountService) {
        this.accountService = accountService;
    }

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        createDefaultAdminAccount();
    }

    private void createDefaultAdminAccount() {
        if (!accountService.verifyAdminAccount(adminName)){
            try {
                log.info("------------ Admin Account Creation ------------");
                String password = generatePassword();
                accountService.createAccount(adminName, password, "ADMIN", "Admin", "@Admin_HC");
                log.info("USERNAME: {}", adminName);
                log.info("PASSWORD: {}", password);
                log.info("------------------------------------------------");
            } catch (NullPointerException npe){
                log.error("Configured Admin Name is invalid.");
            } catch (IllegalArgumentException iae){
                log.error("Parameters are not allowed to be blank strings");
            }
        } else {
            try {
                log.info("------------ Current Admin Account ------------");
                AccountDto account = accountService.fetchAccount(adminName);
                log.info("USERNAME: {}", account.getUsername());
                log.info("PASSWORD: {}", account.getPassword());
                log.info("-----------------------------------------------");
            } catch (UsernameNotFoundException notFoundException){
                log.error("Something went wrong. Username could not be found.");
            }
        }
    }

    private String generatePassword() {
        PasswordGenerator generator = new PasswordGenerator();
        CharacterData lowerCaseChars = EnglishCharacterData.LowerCase;
        CharacterRule lowerCaseRule = new CharacterRule(lowerCaseChars);
        lowerCaseRule.setNumberOfCharacters(2);

        CharacterData upperCaseChars = EnglishCharacterData.UpperCase;
        CharacterRule upperCaseRule = new CharacterRule(upperCaseChars);
        upperCaseRule.setNumberOfCharacters(2);

        CharacterData digitChars = EnglishCharacterData.Digit;
        CharacterRule digitRule = new CharacterRule(digitChars);
        digitRule.setNumberOfCharacters(2);

        return generator.generatePassword(10, lowerCaseRule,
                upperCaseRule, digitRule);
    }
}
