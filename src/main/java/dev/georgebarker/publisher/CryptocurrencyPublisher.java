package dev.georgebarker.publisher;

import dev.georgebarker.helper.LogHelper;
import dev.georgebarker.manager.SessionManager;
import dev.georgebarker.model.Cryptocurrency;
import dev.georgebarker.model.CryptocurrencyList;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Slf4j
@Component
@RequiredArgsConstructor
public class CryptocurrencyPublisher {
    private final SessionManager sessionManager;
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
