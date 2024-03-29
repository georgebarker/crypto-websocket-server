package dev.georgebarker.external.handler;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.net.http.HttpResponse;
import java.util.function.Supplier;

/**
 * @see <a href="https://github.com/mjg123/java-http-clients/blob/master/src/main/java/com/twilio/JsonBodyHandler.java">JsonBodyHandler.java</a>
 * @param <T> type of object to handle
 */
public class JsonBodyHandler<T> implements HttpResponse.BodyHandler<Supplier<T>> {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private final Class<T> targetClass;

    public JsonBodyHandler(Class<T> targetClass) {
        this.targetClass = targetClass;
    }

    @Override
    public HttpResponse.BodySubscriber<Supplier<T>> apply(HttpResponse.ResponseInfo responseInfo) {
        return asJSON(this.targetClass);
    }


    public static <W> HttpResponse.BodySubscriber<Supplier<W>> asJSON(Class<W> targetType) {
        HttpResponse.BodySubscriber<InputStream> upstream = HttpResponse.BodySubscribers.ofInputStream();

        return HttpResponse.BodySubscribers.mapping(
                upstream,
                inputStream -> toSupplierOfType(inputStream, targetType));
    }

    public static <W> Supplier<W> toSupplierOfType(InputStream inputStream, Class<W> targetType) {
        return () -> {
            try (InputStream stream = inputStream) {
                return objectMapper.readValue(stream, targetType);
            } catch (IOException e) {
                throw new UncheckedIOException(e);
            }
        };
    }
}