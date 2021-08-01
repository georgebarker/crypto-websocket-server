package dev.georgebarker.publisher;

import dev.georgebarker.manager.SessionManager;
import dev.georgebarker.model.Cryptocurrency;

public class CryptocurrencyMessagePublisher {

    private final SessionManager sessionManager = SessionManager.INSTANCE;

    public void publish(Cryptocurrency cryptocurrency) {
        sessionManager.getSessions().forEach(session -> session
                .getAsyncRemote()
                .sendObject(cryptocurrency,
                        result -> System.out.println(
                                "Got result: " + result
                                + "When sending Cryptocurrency: " + cryptocurrency
                                + " to Session: " + session)));
    }
}
