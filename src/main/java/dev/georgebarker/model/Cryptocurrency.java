package dev.georgebarker.model;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class Cryptocurrency {
    private String name;
    private BigDecimal last;
    private BigDecimal bid;
    private BigDecimal ask;
}
