package com.siddu6003.expensetracker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionInformation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class SessionDebugController {

    @Autowired
    private SessionRegistry sessionRegistry;

    @GetMapping("/active-sessions")
    public List<Map<String, Object>> getAllActiveSessions() {
        List<Object> allPrincipals = sessionRegistry.getAllPrincipals();
        List<Map<String, Object>> sessions = new ArrayList<>();

        for (Object principal : allPrincipals) {
            List<SessionInformation> sessionInfoList = sessionRegistry.getAllSessions(principal, false);
            for (SessionInformation sessionInfo : sessionInfoList) {
                Map<String, Object> sessionData = new HashMap<>();
                sessionData.put("username", principal.toString());
                sessionData.put("sessionId", sessionInfo.getSessionId());
                sessionData.put("lastRequest", sessionInfo.getLastRequest());
                sessionData.put("isExpired", sessionInfo.isExpired());
                sessions.add(sessionData);
            }
        }

        return sessions;
    }
}

