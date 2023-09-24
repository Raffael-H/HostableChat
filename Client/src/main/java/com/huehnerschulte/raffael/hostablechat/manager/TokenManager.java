package com.huehnerschulte.raffael.hostablechat.manager;

import java.util.prefs.Preferences;
public class TokenManager {

    private static final String TOKEN_KEY = "jwt_token";
    private static TokenManager instance;
    private final Preferences prefs;

    private TokenManager() {
        prefs = Preferences.userNodeForPackage(TokenManager.class);
    }

    public static synchronized TokenManager getInstance() {
        if (instance == null) {
            instance = new TokenManager();
        }
        return instance;
    }

    public void saveToken(String token) {
        prefs.put(TOKEN_KEY, token);
    }

    public String getToken() {
        return prefs.get(TOKEN_KEY, null);
    }
}
