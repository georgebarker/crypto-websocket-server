package dev.georgebarker.endpoint;

import dev.georgebarker.config.SpringWebSocketConfigurator;
import dev.georgebarker.daemon.CryptocurrencyPricesDaemon;
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

    //TODO: Daemon is only present in here for the sake of triggering the dependency injection. Remove when Scheduling is introduced.
    private final CryptocurrencyPricesDaemon cryptocurrencyPricesDaemon;
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
