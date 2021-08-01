package dev.georgebarker.model;

import com.google.gson.Gson;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

public class CryptocurrencyEncoder implements Encoder.Text<Cryptocurrency> {
    private final Gson gson = new Gson();

    @Override
    public String encode(Cryptocurrency cryptocurrency) throws EncodeException {
        return gson.toJson(cryptocurrency);
    }

    @Override
    public void init(EndpointConfig endpointConfig) {

    }

    @Override
    public void destroy() {

    }
}
