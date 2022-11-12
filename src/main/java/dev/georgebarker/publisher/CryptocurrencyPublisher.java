package dev.georgebarker.publisher;

import dev.georgebarker.helper.LogHelper;
import dev.georgebarker.manager.SessionManager;
import dev.georgebarker.model.Cryptocurrency;
import dev.georgebarker.model.CryptocurrencyList;
import dev.georgebarker.store.PricesStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Slf4j
@Component
@RequiredArgsConstructor
public class CryptocurrencyPublisher implements InitializingBean {

    private final PricesStore pricesStore;
    private final SessionManager sessionManager;

    @Override
    public void afterPropertiesSet() {
        pricesStore.subscribe(this::publish);
    }
    public void publish(Collection<Cryptocurrency> cryptocurrencies) {
        final CryptocurrencyList cryptocurrencyList = new CryptocurrencyList(cryptocurrencies);
        sessionManager.getSessions().forEach(session ->
                session.getAsyncRemote()
                .sendObject(cryptocurrencyList,
                        result -> log.info("Got result {} when sending Cryptocurrencies {} to Session {}",
                                LogHelper.getLoggableSendResult(result),
                                cryptocurrencyList,
                                LogHelper.getLoggableSession(session))));
    }
}
