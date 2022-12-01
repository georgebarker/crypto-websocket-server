package dev.georgebarker.external.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

/**
 * Reflects object returned from Binance API, @see <a href="https://binance-docs.github.io/apidocs/spot/en/#symbol-order-book-ticker">Symbol Order Book Ticker</a>
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class BinanceCryptocurrency {
    private String symbol;
    private String bidPrice;
    private String askPrice;
}
