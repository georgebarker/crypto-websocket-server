package dev.georgebarker.daemon;

import dev.georgebarker.external.resource.PricesResource;
import dev.georgebarker.store.PricesStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Component
@RequiredArgsConstructor
public class CryptocurrencyPricesDaemon implements InitializingBean {
    private static final int THREE_SECONDS_MS = 3000;
    private final PricesStore pricesStore;
    private final PricesResource pricesResource;

    private LocalDateTime daemonStartedTimestamp;

    @Override
    public void afterPropertiesSet() {
        startDaemon();
    }

    private void startDaemon() {
        if (daemonStartedTimestamp == null) {
            final Runnable runnable = () -> {
                while (true) {
                    pricesResource.fetchPrices("BTCUSDT", "ETHUSDT")
                            .thenAccept(pricesStore::update)
                            .exceptionally(throwable -> {
                                log.error("Failed to fetch prices.", throwable);
                                return null;
                            });
                    try {
                        Thread.sleep(THREE_SECONDS_MS);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            new Thread(runnable).start();
            daemonStartedTimestamp = LocalDateTime.now();
            log.info("CryptocurrencyPricesDaemon started at {}.", daemonStartedTimestamp);
        } else {
            log.warn("startDaemon called when the daemon is already running since {}.", daemonStartedTimestamp);
        }
    }
}
