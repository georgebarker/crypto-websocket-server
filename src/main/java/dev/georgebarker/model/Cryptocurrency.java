package dev.georgebarker.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

/**
 * Represents a cryptocurrency with live price information.
 * Prices are related to no specific fiat currency for the moment.
 */
@Data
@Builder
public class Cryptocurrency {
    /**
     * The name of the cryptocurrency
     */
    private String name;

    /**
     * The last price that was paid on the market for this cryptocurrency
     */
    private BigDecimal last;

    /**
     * The highest price someone on the market is willing to buy this cryptocurrency for
     */
    private BigDecimal bid;

    /**
     * The lowest price someone on the market is willing to sell this cryptocurrency for
     */
    private BigDecimal ask;

    /**
     * The tick size (decimal place) of the Fiat currency for the prices this cryptocurrency is denominated in
     */
    private int fiatTickSize;
}
