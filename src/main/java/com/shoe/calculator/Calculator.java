package com.shoe.calculator;

import java.util.stream.IntStream;

public class Calculator {
    RpnStack values = new RpnStack();

    public void enter(int value) {
        values.push(value);
    }

    public void execute(String operatorName) {
        switch(operatorName){
            case "plus": {
                int rhs = values.pop();
                int lhs = values.pop();
                int result = lhs + rhs;
                values.push(result);
                break;
            }
            case "minus": {
                int rhs = values.pop();
                int lhs = values.pop();
                int result = lhs - rhs;
                values.push(result);
                break;
            }
            case "times": {
                int rhs = values.pop();
                int lhs = values.pop();
                int result = lhs * rhs;
                values.push(result);
                break;
            }
            case "divide": {
                int rhs = values.pop();
                int lhs = values.pop();
                int result = lhs / rhs;
                values.push(result);
                break;
            }
            case "factorial": {
                int value = values.pop();
                int result = IntStream.rangeClosed(1, value).reduce(1, (a, b) -> a * b);
                values.push(result);
                break;
            }
            case "swap": {
                int oldTop = values.pop();
                int newTop = values.pop();
                values.push(oldTop);
                values.push(newTop);
                break;
            }
            case "drop": {
                values.pop();
                break;
            }
            default: {
                throw new RuntimeException(String.format("Unknown Operator: '%s'", operatorName));
            }
        }
    }

    public int value() {
        return values.peek();
    }
}
