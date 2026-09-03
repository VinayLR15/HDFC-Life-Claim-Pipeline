package com.hdfclife.thread;

import java.util.concurrent.Callable;

public class ClaimTotalCallable implements Callable<Integer> {
    private final int[] amounts;

    public ClaimTotalCallable(int[] amounts) {
        this.amounts = amounts;
    }

    // Future.get() blocks until this worker completes; extra space is the worker's
    // call stack rather than an additional O(n) array.
    @Override
    public Integer call() {
        int sum = 0;
        for (int i = 0; i < amounts.length; i++) sum += amounts[i];
        return sum;
    }
}
