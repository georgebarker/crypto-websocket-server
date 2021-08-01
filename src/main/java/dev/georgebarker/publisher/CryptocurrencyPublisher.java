package dev.georgebarker.publisher;

import dev.georgebarker.helper.LogHelper;
import dev.georgebarker.manager.SessionManager;
import dev.georgebarker.model.Cryptocurrency;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CryptocurrencyPublisher {

    public static final CryptocurrencyPublisher INSTANCE = new CryptocurrencyPublisher();
    private final SessionManager sessionManager = SessionManager.INSTANCE;

    public void publish(Cryptocurrency cryptocurrency) {
        sessionManager.getSessions().forEach(session ->
                session.getAsyncRemote()
                .sendObject(cryptocurrency,
                        result -> log.info("Got result {} when sending Cryptocurrency {} to Session {}",
                                LogHelper.getLoggableSendResult(result),
                                cryptocurrency,
                                LogHelper.getLoggableSession(session))));
    }
}
