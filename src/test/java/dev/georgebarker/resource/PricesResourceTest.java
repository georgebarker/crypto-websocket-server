package dev.georgebarker.resource;

import dev.georgebarker.external.mapper.CryptocurrencyMapper;
import dev.georgebarker.external.resource.PricesResource;
import dev.georgebarker.model.Cryptocurrency;
import org.junit.jupiter.api.Test;

import java.util.Collection;
import java.util.concurrent.CompletableFuture;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PricesResourceTest {

    PricesResource pricesResource = new PricesResource(new CryptocurrencyMapper());

    @Test
    void testFetchPrices() {
        final CompletableFuture<Collection<Cryptocurrency>> response = pricesResource.fetchPrices("BTCUSDT", "ETHUSDT");
        response.thenAccept(cryptocurrencies -> assertEquals(2, cryptocurrencies.size()));
    }
}