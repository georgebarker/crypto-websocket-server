package dev.georgebarker.helper;

import jakarta.websocket.SendResult;
import jakarta.websocket.Session;

public class LogHelper {

    public static String getLoggableSendResult(SendResult result) {
        if (result.isOK()) {
            return "OK";
        } else {
            return result.getException().toString();
        }
    }

    public static String getLoggableSession(Session session) {
        return "Session(id=" + session.getId() + ")";
    }
}
