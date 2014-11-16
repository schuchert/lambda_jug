package com.shoe.calculator;

import com.shoe.calculator.operators.Registrar;
import org.reflections.Reflections;

import java.beans.Introspector;
import java.util.LinkedHashMap;
import java.util.Map;

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

    RpnOperator operatorNamed(String operatorName) {
        return operatorsByName.get(operatorName);
    }
}
