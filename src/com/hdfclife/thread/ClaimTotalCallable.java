package com.hdfclife.thread;

import java.util.concurrent.Callable;

public class ClaimTotalCallable implements Callable<Integer> {

    private final int[] amounts;

    public ClaimTotalCallable(int[] amounts) {

        this.amounts = amounts;
    }

    @Override
    public Integer call() {

        int sum = 0;

        for (int i = 0; i < amounts.length; i++) {

            sum += amounts[i];
        }

        return sum;
    }
}