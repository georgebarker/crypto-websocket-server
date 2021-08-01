package dev.georgebarker.endpoint;

import dev.georgebarker.manager.SessionManager;
import dev.georgebarker.model.CryptocurrencyEncoder;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnError;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;


@ServerEndpoint(value = "/cryptocurrencies", encoders = CryptocurrencyEncoder.class)
public class CryptocurrenciesEndpoint {

    public CryptocurrenciesEndpoint() {
        System.out.println("instantiated CryptocurrenciesEndpoint");
    }

    private final SessionManager sessionManager = SessionManager.INSTANCE;

    @OnOpen
    public void onOpen(Session session) {
        sessionManager.add(session);
    }

    @OnClose
    public void onClose(Session session) {
        sessionManager.remove(session);
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        sessionManager.remove(session, throwable);
    }
}
