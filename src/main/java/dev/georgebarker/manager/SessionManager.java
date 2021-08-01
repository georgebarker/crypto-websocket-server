package dev.georgebarker.manager;

import javax.websocket.Session;
import java.util.HashSet;
import java.util.Set;

public class SessionManager {

    /**
     * TODO List:
     * 1. Manage onError message properly. Perhaps introduce a maxAttempts before removing.
     * 2. Use a Map to store a SessionDetails alongside the session with things like:
     *     a) sessionOpenedTimestamp
     *     b) sessionErrorCount
     *     c) sessionErrors (List<Throwable>s)
     */

    private final Set<Session> sessions = new HashSet<>();

    public void add(Session session) {
        System.out.println("INFO: Added Session: " + session);
        sessions.add(session);
    }

    public void remove(Session session, Throwable throwable) {
        System.out.println("ERROR: Removing Session: " + session + ", Message: " + throwable.getMessage());
        remove(session);
    }

    public void remove(Session session) {
        if (sessions.contains(session)) {
            sessions.remove(session);
            System.out.println("INFO: Removed Session: " + session);
        } else {
            System.out.println("WARN: Attempted to remove Session that was not present: " + session);
        }
    }
}
