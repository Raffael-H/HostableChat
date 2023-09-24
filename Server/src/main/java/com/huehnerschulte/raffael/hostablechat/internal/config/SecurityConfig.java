package com.huehnerschulte.raffael.hostablechat.internal.config;


import com.huehnerschulte.raffael.hostablechat.internal.service.auth.HostableChatAuthManager;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final HostableChatAuthManager hostableChatAuthManager;

    public SecurityConfig(HostableChatAuthManager hostableChatAuthManager) {
        this.hostableChatAuthManager = hostableChatAuthManager;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // CORS und CSRF deaktivieren, damit es etwas zügiger Vorwärts geht.
        http.cors().and().csrf().disable();

        // AuthenticationManager hinzufügen um benutzerdefinierte Authentication zu ermöglichen
        http.authenticationManager(hostableChatAuthManager);

        // öffentliche Ressourcen verfügbar machen
        http
                .authorizeRequests()
                .antMatchers("/public/**", "/css/**", "/scss/**", "/js/**", "/images/**", "/fonts/**").permitAll()
                // External Endpunkte einstellen
                .antMatchers("/api/authenticate").permitAll()
                .anyRequest().authenticated()
                .and()

                // Login-Flow definieren
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/dashboard")
                .permitAll()
                .and()

                // Logout-Flow definieren
                .logout()
                .logoutUrl("/logout")
                .logoutSuccessUrl("/login?logout")
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
                .permitAll()
                .and()
                .httpBasic();

    }

}
