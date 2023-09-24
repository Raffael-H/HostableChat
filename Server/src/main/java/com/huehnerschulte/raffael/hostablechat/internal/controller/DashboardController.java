package com.huehnerschulte.raffael.hostablechat.internal.controller;

import com.huehnerschulte.raffael.hostablechat.internal.data.representation.AccountDto;
import com.huehnerschulte.raffael.hostablechat.internal.listener.SessionListener;
import com.huehnerschulte.raffael.hostablechat.internal.service.data.AccountService;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/dashboard")
public class DashboardController {

    private final AccountService accountService;

    public DashboardController(AccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping()
    public String dashboardView(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AccountDto account = accountService.fetchAccount(authentication.getName());
        model.addAttribute("accountForm", new AccountDto());
        model.addAttribute("activeSessions",SessionListener.getActiveSessions());
        model.addAttribute("currentUser", account);
        model.addAttribute("registeredAccounts", accountService.fetchAccountCount());
        model.addAttribute("accountList", accountService.fetchAllAccounts());
        return "dashboard";
    }

    @PostMapping(value = "/create-account", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String createAccount(AccountDto accountDto){
        Boolean isAdmin = accountDto.getIsAdmin();
        String role = isAdmin ? "ADMIN" : "USER";
        accountService.createAccount(accountDto.getUsername(),accountDto.getPassword(),role,accountDto.getDisplayName(),accountDto.getUserTag());
        return "redirect:/dashboard";
    }
}
