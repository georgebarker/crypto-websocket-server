package dev.georgebarker.endpoint;

import dev.georgebarker.model.CryptocurrencyEncoder;

import javax.websocket.*;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.Set;

@ServerEndpoint(
        value = "/cryptocurrencies",
        encoders = CryptocurrencyEncoder.class
)
public class CryptocurrenciesEndpoint {

    Set<Session> sessions;

    @OnOpen
    public void onOpen(Session session) throws IOException {
        sessions.add(session);
        System.out.println("Opened session: " + session);
        // Get session and WebSocket connection
    }

/*    @OnMessage
    public void onMessage(Session session, Message message) throws IOException {
        // Handle new messages
    }*/

    @OnClose
    public void onClose(Session session) throws IOException {
        // WebSocket connection closes
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        // Do error handling here
    }
}
