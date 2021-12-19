package dev.georgebarker.model;


import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Collection;

@Getter
@EqualsAndHashCode
@ToString
public class CryptocurrencyList {

    private final Collection<Cryptocurrency> cryptocurrencies;

    public CryptocurrencyList(Collection<Cryptocurrency> cryptocurrencies) {
        this.cryptocurrencies = cryptocurrencies;
    }
}
