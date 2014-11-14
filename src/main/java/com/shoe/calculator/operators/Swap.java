package com.shoe.calculator.operators;

import com.shoe.calculator.RpnOperator;

public class Swap implements Registrar {
    @Override
    public RpnOperator get() {
        return values -> {
            int oldTop = values.pop();
            int newTop = values.pop();
            values.push(oldTop);
            values.push(newTop);
        };
    }
}
