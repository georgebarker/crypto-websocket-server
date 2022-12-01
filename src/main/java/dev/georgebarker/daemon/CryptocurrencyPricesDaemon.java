package dev.georgebarker.daemon;

import dev.georgebarker.external.resource.PricesResource;
import dev.georgebarker.store.PricesStore;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class CryptocurrencyPricesDaemon {
    private static final int THREE_SECONDS_MS = 3000;
    private final PricesStore pricesStore;
    private final PricesResource pricesResource;


    @Scheduled(fixedDelay = THREE_SECONDS_MS)
    public void run() {
        pricesResource.fetchPrices("BTCUSDT", "ETHUSDT")
                .thenAccept(pricesStore::update)
                .exceptionally(throwable -> {
                    log.error("Failed to fetch prices.", throwable);
                    return null;
                });
    }
}
