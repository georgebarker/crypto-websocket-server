package dev.georgebarker.generator;

import dev.georgebarker.model.Cryptocurrency;
import dev.georgebarker.publisher.CryptocurrencyPublisher;
import lombok.extern.slf4j.Slf4j;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;
import java.util.concurrent.ThreadLocalRandom;

@Slf4j
public class RandomDataGenerator {
    public static final RandomDataGenerator INSTANCE = new RandomDataGenerator();

    private static final int THREE_SECONDS_MS = 3000;

    private static final int DEFAULT_TICK_SIZE = 2;
    private static final MathContext MATH_CONTEXT_FOR_TICK_SIZE =
            new MathContext(DEFAULT_TICK_SIZE, RoundingMode.HALF_UP);

    private static final int ROUNDING_PRECISION_FOR_RANDOM_GENERATION = 2;
    private static final MathContext MATH_CONTEXT_FOR_RANDOM_GENERATION =
            new MathContext(ROUNDING_PRECISION_FOR_RANDOM_GENERATION, RoundingMode.HALF_UP);
    private static final double RANDOM_DOUBLE_LOWER_BOUND = 0.5D;
    private static final double RANDOM_DOUBLE_UPPER_BOUND = 1.5D;

    private static final int STARTING_VALUE = 30_000;

    private BigDecimal bid = BigDecimal.valueOf(STARTING_VALUE);
    private BigDecimal ask = BigDecimal.valueOf(STARTING_VALUE);
    private BigDecimal last = BigDecimal.valueOf(STARTING_VALUE);

    private boolean isStarted = false;

    public void startRandomDataGeneration() {
        isStarted = true;
        // TODO: Replace this horrible stuff with a Scheduler.
        //  Eventually to be replaced with a real data source, of course.
        final Runnable runnable = () -> {
            while (true) {
                bid = bid.multiply(getRandomMultiplier(), MATH_CONTEXT_FOR_TICK_SIZE);
                ask = ask.multiply(getRandomMultiplier(), MATH_CONTEXT_FOR_TICK_SIZE);
                last = last.multiply(getRandomMultiplier(), MATH_CONTEXT_FOR_TICK_SIZE);
                final Cryptocurrency cryptocurrency = Cryptocurrency.builder()
                        .name("Bitcoin")
                        .bid(bid)
                        .ask(ask)
                        .last(last)
                        .fiatTickSize(DEFAULT_TICK_SIZE)
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

    private BigDecimal getRandomMultiplier() {
        double randomDouble = ThreadLocalRandom.current()
                .nextDouble(RANDOM_DOUBLE_LOWER_BOUND, RANDOM_DOUBLE_UPPER_BOUND);
        return new BigDecimal(randomDouble, MATH_CONTEXT_FOR_RANDOM_GENERATION);
    }
}
