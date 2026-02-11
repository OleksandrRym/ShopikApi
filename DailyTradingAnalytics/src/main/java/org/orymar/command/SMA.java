package org.orymar.command;

import java.math.BigDecimal;

/// Simple Moving Average
public class SMA implements Command {

    @Override
    public BigDecimal execute(BigDecimal[] inputValue) {
        int k_candle = inputValue.length;
        BigDecimal sum = BigDecimal.ZERO;
        for (int i = 0; i < k_candle; i++) {
            sum.add(inputValue[i]);
        }
        return sum.divide(new BigDecimal(k_candle));
    }

}
