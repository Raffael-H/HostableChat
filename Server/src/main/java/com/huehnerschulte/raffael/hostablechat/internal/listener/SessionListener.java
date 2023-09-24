package com.huehnerschulte.raffael.hostablechat.internal.listener;


import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener {

    private static int activeSessions = 0;

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        activeSessions++;
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        activeSessions--;
    }

    public static int getActiveSessions() {
        return activeSessions;
    }
}
