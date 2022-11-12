package dev.georgebarker.store;

import dev.georgebarker.model.Cryptocurrency;
import org.springframework.context.annotation.Configuration;

import java.util.*;
import java.util.function.Consumer;

@Configuration
public class PricesStore {
    private final Set<Cryptocurrency> prices = new HashSet<>();
    private final Set<Consumer<Collection<Cryptocurrency>>> subscriptions = new HashSet<>();

    public void subscribe(Consumer<Collection<Cryptocurrency>> subscription) {
        subscriptions.add(subscription);
    }

    public void update(Cryptocurrency cryptocurrency) {
        if (!prices.contains(cryptocurrency)) {
            prices.add(cryptocurrency);
            subscriptions.forEach(subscription -> subscription.accept(prices));
        }
    }
}
