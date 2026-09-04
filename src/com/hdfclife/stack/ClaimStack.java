package com.hdfclife.stack;

public interface ClaimStack {

    void push(int value);

    int pop();

    int peek();

    boolean isEmpty();
}