package dev.georgebarker.manager;


import dev.georgebarker.helper.LogHelper;
import jakarta.websocket.Session;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Component
public class SessionManager {

    /**
     * TODO List:
     * 1. Manage onError message properly. Perhaps introduce a maxAttempts before removing.
     * 2. Use a Map to store a SessionDetails alongside the session with things like:
     * a) sessionOpenedTimestamp
     * b) sessionErrorCount
     * c) sessionErrors (List<Throwable>s)
     */

    private final Set<Session> sessions = new HashSet<>();

    public Set<Session> getSessions() {
        return sessions;
    }

    public void add(Session session) {
        sessions.add(session);
        log.info("Added Session {}", LogHelper.getLoggableSession(session));
    }

    public void remove(Session session, Throwable throwable) {
        log.error("Removing erroneous Session: {}", LogHelper.getLoggableSession(session), throwable);
        remove(session);
    }

    public void remove(Session session) {
        sessions.remove(session);
        log.info("Removed Session: {}", LogHelper.getLoggableSession(session));
    }
}
