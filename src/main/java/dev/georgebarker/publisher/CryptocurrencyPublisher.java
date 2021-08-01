package dev.georgebarker.publisher;

import dev.georgebarker.manager.SessionManager;
import dev.georgebarker.model.Cryptocurrency;

// Possibly make the publisher more generic Publisher<Cryptocurrency> ?
public class CryptocurrencyPublisher {

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
