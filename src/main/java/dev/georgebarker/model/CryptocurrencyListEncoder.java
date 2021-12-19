package dev.georgebarker.model;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import jakarta.websocket.Encoder;

/**
 * It's not yet clear whether the service should always publish an array of Cryptocurrencies rather than push them individually.
 *
 * This encoder returns the data in the style of:
 * [{"name":"Bitcoin","last":39000.0,"bid":16800.0,"ask":39000.0,"fiatTickSize":6}]
 */
public class CryptocurrencyListEncoder implements Encoder.Text<CryptocurrencyList> {

    private final Gson gson = new Gson();

    @Override
    public String encode(CryptocurrencyList cryptocurrencyList) {
        return gson.toJson(cryptocurrencyList.getCryptocurrencies());
    }
}