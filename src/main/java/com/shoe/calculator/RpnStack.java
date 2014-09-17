package com.shoe.calculator;

import java.util.Stack;

public class RpnStack extends Stack<Integer> {
    @Override
    public Integer peek() {
        if(size() > 0) {
            return super.peek();
        }

        return 0;
    }

    @Override
    public Integer pop() {
        if(size() > 0) {
            return super.pop();
        }

        return 0;
    }
}
