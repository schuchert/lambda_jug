package com.shoe.calculator;

import com.shoe.calculator.operators.Registrar;
import org.reflections.Reflections;

import java.beans.Introspector;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.stream.IntStream;

public class OperatorFactory {
    Map<String, RpnOperator> operatorsByName;

    public OperatorFactory() {
        operatorsByName = new LinkedHashMap<String, RpnOperator>() {
            @Override
            public RpnOperator get(Object operatorName) {
                RpnOperator op = super.get(operatorName);
                if (op == null) {
                    return values -> {
                        throw new RuntimeException(String.format("Unknown Operator: '%s'", operatorName));
                    };
                }
                return op;
            }
        };

        operatorsByName.put("plus", binaryFor((a, b) -> a + b));
        operatorsByName.put("minus", binaryFor((a, b) -> a - b));
        operatorsByName.put("times", binaryFor((a, b) -> a * b));
        operatorsByName.put("divide", binaryFor((a, b) -> a / b));
        operatorsByName.put("factorial", values -> {
            int value = values.pop();
            int result = IntStream.rangeClosed(1, value).reduce(1, (a, b) -> a * b);
            values.push(result);
        });

        autoRegister();
    }

    private void autoRegister() {
        Reflections reflections = new Reflections("com.shoe.calculator.operators");
        reflections.getSubTypesOf(Registrar.class).stream().forEach(clazz -> {
            try {
                RpnOperator op = clazz.newInstance().get();
                operatorsByName.put(nameFor(clazz), op);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });
    }

    private String nameFor(Class<? extends Registrar> clazz) {
        return Introspector.decapitalize(clazz.getSimpleName());
    }


    RpnOperator binaryFor(BiFunction<Integer, Integer, Integer> op) {
        return values -> {
            int rhs = values.pop();
            int lhs = values.pop();
            int result = op.apply(lhs, rhs);
            values.push(result);
        };
    }

    RpnOperator operatorNamed(String operatorName) {
        return operatorsByName.get(operatorName);
    }
}
