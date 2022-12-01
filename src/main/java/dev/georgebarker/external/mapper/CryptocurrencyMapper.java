package dev.georgebarker.external.mapper;

import dev.georgebarker.external.model.BinanceCryptocurrency;
import dev.georgebarker.model.Cryptocurrency;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Collectors;

@Component
public class CryptocurrencyMapper {
    public Collection<Cryptocurrency> map(BinanceCryptocurrency[] binanceCryptocurrencies) {
        return Arrays.stream(binanceCryptocurrencies).map(this::map).collect(Collectors.toUnmodifiableSet());
    }

    private Cryptocurrency map(BinanceCryptocurrency binanceCryptocurrency) {
        final BigDecimal bid = new BigDecimal(binanceCryptocurrency.getBidPrice()).stripTrailingZeros();
        final BigDecimal ask = new BigDecimal(binanceCryptocurrency.getAskPrice()).stripTrailingZeros();
        int tickSize = Math.max(bid.scale(), ask.scale());
        return Cryptocurrency.builder()
                .name(binanceCryptocurrency.getSymbol())
                .bid(bid.setScale(tickSize, RoundingMode.UP))
                .ask(ask.setScale(tickSize, RoundingMode.UP))
                .fiatTickSize(tickSize)
                .build();
    }
}
