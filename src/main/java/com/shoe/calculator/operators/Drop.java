package com.shoe.calculator.operators;

import com.shoe.calculator.RpnOperator;
import com.shoe.calculator.RpnStack;

public class Drop implements Registrar {
    @Override
    public RpnOperator get() {
        return RpnStack::pop;
    }
}
