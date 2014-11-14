package com.shoe.calculator.operators;

import com.shoe.calculator.RpnOperator;

public class Squared implements Registrar {
    @Override
    public RpnOperator get() {
        return values -> {
            int value = values.pop();
            values.push(value * value);
        };
    }
}
