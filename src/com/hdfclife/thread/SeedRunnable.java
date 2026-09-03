package com.hdfclife.thread;

public class SeedRunnable implements Runnable {
    private final int amount;

    public SeedRunnable(int amount) {
        this.amount = amount;
    }

    @Override
    public void run() {
        // Real Runnable worker. The state is inspected by Main before/after join.
        // No output is required here so Main's required output order stays exact.
        int processed = amount;
        if (processed < 0) {
            throw new IllegalArgumentException("Amount cannot be negative");
        }
    }
}
