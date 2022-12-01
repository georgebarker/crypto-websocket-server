package dev.georgebarker.external.mapper;

import dev.georgebarker.external.model.BinanceCryptocurrency;
import dev.georgebarker.model.Cryptocurrency;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CryptocurrencyMapperTest {

    private final CryptocurrencyMapper cryptocurrencyMapper = new CryptocurrencyMapper();

    @Test
    public void testMap() {
        final BinanceCryptocurrency binanceCryptocurrency = new BinanceCryptocurrency();
        binanceCryptocurrency.setBidPrice("1273.98000000");
        binanceCryptocurrency.setAskPrice("1273.97700000");
        binanceCryptocurrency.setSymbol("ETHUSDT");

        final Collection<Cryptocurrency> mapped = cryptocurrencyMapper.map(new BinanceCryptocurrency[]{binanceCryptocurrency});
        assertEquals(1, mapped.size());

        final List<Cryptocurrency> cryptocurrencies = new ArrayList<>(mapped);
        final Cryptocurrency cryptocurrency = cryptocurrencies.get(0);

        assertEquals(3, cryptocurrency.getFiatTickSize());
        assertEquals(new BigDecimal("1273.980"), cryptocurrency.getBid());
        assertEquals(new BigDecimal("1273.977"), cryptocurrency.getAsk());
    }

}