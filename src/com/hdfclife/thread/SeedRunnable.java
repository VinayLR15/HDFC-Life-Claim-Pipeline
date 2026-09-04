package com.hdfclife.thread;

public class SeedRunnable implements Runnable {

    private final int amount;

    public SeedRunnable(int amount) {
        this.amount = amount;
    }

    @Override
    public void run() {

        int processed = amount;

        if (processed < 0) {
            throw new IllegalArgumentException(
                    "Amount cannot be negative"
            );
        }
    }
}