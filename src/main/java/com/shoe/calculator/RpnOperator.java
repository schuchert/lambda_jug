package com.shoe.calculator;

@FunctionalInterface
public interface RpnOperator {
    void execute(RpnStack values);
}
