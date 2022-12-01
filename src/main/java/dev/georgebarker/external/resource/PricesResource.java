package dev.georgebarker.external.resource;

import dev.georgebarker.external.exception.ResourceException;
import dev.georgebarker.external.handler.JsonBodyHandler;
import dev.georgebarker.external.mapper.CryptocurrencyMapper;
import dev.georgebarker.external.model.BinanceCryptocurrency;
import dev.georgebarker.model.Cryptocurrency;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.net.URI;
import java.net.URISyntaxException;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collection;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@Slf4j
@Component
@RequiredArgsConstructor
public class PricesResource {
    private final HttpClient httpClient = HttpClient.newHttpClient();
    private final CryptocurrencyMapper cryptocurrencyMapper;

    public CompletableFuture<Collection<Cryptocurrency>> fetchPrices(String... symbols) {

        if (symbols.length == 0) {
            return CompletableFuture.failedFuture(new ResourceException("No symbols given."));
        }

        final URI uri;
        try {
            uri = buildURI(symbols);
        } catch (URISyntaxException e) {
            return CompletableFuture.failedFuture(new ResourceException("Failed to build URI", e));
        }
        final HttpRequest request = HttpRequest.newBuilder(uri)
                .header("Content-Type", "application/json")
                .GET().build();

        return httpClient.sendAsync(request, new JsonBodyHandler<>(BinanceCryptocurrency[].class))
                .thenApply(HttpResponse::body)
                .thenApply(Supplier::get)
                .thenApply(cryptocurrencyMapper::map);
    }
    private URI buildURI(String... symbols) throws URISyntaxException {
        final String params = URLEncoder.encode(convertSymbolsToStringParam(symbols), StandardCharsets.UTF_8);
        final String uri = "https://api.binance.com/api/v3/ticker/bookTicker?symbols=" + params;
        return new URI(uri);
    }


    // Binance requires symbols in the format of ["BTCUSDT","ETHUSDT"].
    private String convertSymbolsToStringParam(String... symbols) {
        final Set<String> symbolsWrappedInQuotes = Arrays.stream(symbols).map(symbol -> "\"" + symbol + "\"").collect(Collectors.toSet());
        final String commaDelimitedSymbolsWrappedInQuotes = String.join(",", symbolsWrappedInQuotes);
        return "[" + commaDelimitedSymbolsWrappedInQuotes + "]";
    }
}
