package dev.georgebarker.endpoint;

import dev.georgebarker.generator.RandomDataGenerator;
import dev.georgebarker.manager.SessionManager;
import dev.georgebarker.model.CryptocurrencyListEncoder;
import jakarta.websocket.OnClose;
import jakarta.websocket.OnError;
import jakarta.websocket.OnOpen;
import jakarta.websocket.Session;
import jakarta.websocket.server.ServerEndpoint;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@ServerEndpoint(value = "/cryptocurrencies",
        encoders = CryptocurrencyListEncoder.class,
        configurator = SpringWebSocketConfigurator.class
)
@Component
@RequiredArgsConstructor
@Slf4j
public class CryptocurrenciesEndpoint {

    private final SessionManager sessionManager;
    private final RandomDataGenerator randomDataGenerator;
    @OnOpen
    public void onOpen(Session session) {
        // Lazy start the generation for until we actually get a connection.
        // This will be replaced with a dependency injected generator and eventually a real data feed.
        if (!randomDataGenerator.isStarted()) {
            randomDataGenerator.startRandomDataGeneration();
        }

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
