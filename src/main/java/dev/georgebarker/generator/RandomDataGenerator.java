package dev.georgebarker.generator;

import dev.georgebarker.model.Cryptocurrency;
import dev.georgebarker.publisher.CryptocurrencyPublisher;

import java.math.BigDecimal;

public class RandomDataGenerator {

    public static final RandomDataGenerator INSTANCE = new RandomDataGenerator();

    private static final int THREE_SECONDS_MS = 3000;

    private BigDecimal bid = BigDecimal.valueOf(100);
    private BigDecimal ask = BigDecimal.valueOf(100);
    private BigDecimal last = BigDecimal.valueOf(100);

    private boolean isStarted = false;

    public void startRandomDataGeneration() {
        isStarted = true;
        // TODO: Replace this horrible stuff with a Scheduler.
        //  Eventually to be replaced with a real data source, of course.
        final Runnable runnable = () -> {
            while (true) {
                bid = bid.multiply(BigDecimal.valueOf(1.2));
                ask = ask.multiply(BigDecimal.valueOf(1.3));
                last = last.multiply(BigDecimal.valueOf(1.4));
                final Cryptocurrency cryptocurrency = Cryptocurrency.builder()
                        .name("Bitcoin")
                        .bid(bid)
                        .ask(ask)
                        .last(last)
                        .build();

                CryptocurrencyPublisher.INSTANCE.publish(cryptocurrency);
                try {
                    Thread.sleep(THREE_SECONDS_MS);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        new Thread(runnable).start();
    }

    public boolean isStarted() {
        return isStarted;
    }
}
