package dev.georgebarker.model;

import com.google.gson.Gson;
import jakarta.websocket.EncodeException;
import jakarta.websocket.Encoder;
import jakarta.websocket.EndpointConfig;


public class CryptocurrencyEncoder implements Encoder.Text<Cryptocurrency> {
    private final Gson gson = new Gson();

    @Override
    public String encode(Cryptocurrency cryptocurrency) {
        return gson.toJson(cryptocurrency);
    }

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }
}
