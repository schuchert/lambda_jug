package com.shoe.calculator.operators;

import com.shoe.calculator.RpnOperator;

import java.util.stream.IntStream;

public class Factorial implements Registrar {
    @Override
    public RpnOperator get() {
        return values -> {
            int value = values.pop();
            int result = IntStream.rangeClosed(1, value).reduce(1, (a, b) -> a * b);
            values.push(result);
        };
    }
}
