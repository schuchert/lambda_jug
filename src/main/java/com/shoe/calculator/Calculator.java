package com.shoe.calculator;

public class Calculator {
    RpnStack values = new RpnStack();
    OperatorFactory factory = new OperatorFactory();

    public void enter(int value) {
        values.push(value);
    }

    public void execute(String operatorName) {
        factory.operatorNamed(operatorName).execute(values);
    }

    public int value() {
        return values.peek();
    }
}
