package dev.georgebarker.endpoint;

import dev.georgebarker.manager.SessionManager;
import dev.georgebarker.model.CryptocurrencyEncoder;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Set;

@ServerEndpoint(
        value = "/cryptocurrencies",
        encoders = CryptocurrencyEncoder.class
)
public class CryptocurrenciesEndpoint {

    private final SessionManager sessionManager = new SessionManager();

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
