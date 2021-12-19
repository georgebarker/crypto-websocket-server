package dev.georgebarker.publisher;

import dev.georgebarker.helper.LogHelper;
import dev.georgebarker.manager.SessionManager;
import dev.georgebarker.model.Cryptocurrency;
import dev.georgebarker.model.CryptocurrencyList;
import lombok.extern.slf4j.Slf4j;

import java.util.Collections;
import java.util.List;

@Slf4j
public class CryptocurrencyPublisher {

    public static final CryptocurrencyPublisher INSTANCE = new CryptocurrencyPublisher();
    private final SessionManager sessionManager = SessionManager.INSTANCE;

    public void publish(Cryptocurrency cryptocurrency) {
        final CryptocurrencyList cryptocurrencyList = new CryptocurrencyList(Collections.singletonList(cryptocurrency));
        sessionManager.getSessions().forEach(session ->
                session.getAsyncRemote()
                .sendObject(cryptocurrencyList,
                        result -> log.info("Got result {} when sending Cryptocurrencies {} to Session {}",
                                LogHelper.getLoggableSendResult(result),
                                cryptocurrencyList,
                                LogHelper.getLoggableSession(session))));
    }
}
