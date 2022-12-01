package dev.georgebarker.store;

import dev.georgebarker.model.Cryptocurrency;
import org.springframework.context.annotation.Configuration;

import java.util.*;
import java.util.function.Consumer;

@Configuration
// TODO: This is being treated more as a middle-man between subscriptions and updates right now, rather than a store.
//  It should be transformed back into a store of historic data that publishes deltas upon receipt.
public class PricesStore {
    private final Set<Consumer<Collection<Cryptocurrency>>> subscriptions = new HashSet<>();

    public void subscribe(Consumer<Collection<Cryptocurrency>> subscription) {
        subscriptions.add(subscription);
    }

    public void update(Collection<Cryptocurrency> cryptocurrencies) {
        subscriptions.forEach(subscription -> subscription.accept(cryptocurrencies));
    }
}
