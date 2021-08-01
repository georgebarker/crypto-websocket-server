package dev.georgebarker.publisher;

import dev.georgebarker.manager.SessionManager;
import dev.georgebarker.model.Cryptocurrency;

// TODO Possibly make the publisher more generic Publisher<Cryptocurrency> ?
public class CryptocurrencyPublisher {

    public static final CryptocurrencyPublisher INSTANCE = new CryptocurrencyPublisher();
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
