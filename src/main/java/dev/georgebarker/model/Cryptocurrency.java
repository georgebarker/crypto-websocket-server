package dev.georgebarker.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class Cryptocurrency {
    private String name;
    private BigDecimal last;
    private BigDecimal bid;
    private BigDecimal ask;
}
