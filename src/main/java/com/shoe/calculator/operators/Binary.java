package com.shoe.calculator.operators;

import com.shoe.calculator.RpnOperator;

import java.util.function.BiFunction;

public class Binary {
    public static RpnOperator binaryFor(BiFunction<Integer, Integer, Integer> op) {
        return values -> {
            int rhs = values.pop();
            int lhs = values.pop();
            int result = op.apply(lhs, rhs);
            values.push(result);
        };
    }
}
