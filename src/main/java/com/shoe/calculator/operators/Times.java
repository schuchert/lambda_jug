package com.shoe.calculator.operators;

import com.shoe.calculator.RpnOperator;

import static com.shoe.calculator.operators.Binary.binaryFor;

public class Times implements Registrar {
    @Override
    public RpnOperator get() {
        return binaryFor((a, b) -> a * b);
    }
}
