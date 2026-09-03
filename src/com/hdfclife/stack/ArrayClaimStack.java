package com.hdfclife.stack;

import com.hdfclife.exception.StackEmptyException;
import com.hdfclife.exception.StackFullException;

public class ArrayClaimStack implements ClaimStack {
    private final int[] values;
    private int top = -1;

    public ArrayClaimStack() {
        this(32);
    }

    public ArrayClaimStack(int capacity) {
        if (capacity < 1) throw new IllegalArgumentException("Capacity must be positive");
        values = new int[capacity];
    }

    // Time: O(1), extra space: O(1) per operation.
    @Override
    public void push(int value) {
        if (top == values.length - 1) throw new StackFullException("Stack is full");
        values[++top] = value;
    }

    // Time: O(1), extra space: O(1).
    @Override
    public int pop() {
        if (isEmpty()) throw new StackEmptyException("Cannot pop: stack is empty");
        return values[top--];
    }

    @Override
    public int peek() {
        if (isEmpty()) throw new StackEmptyException("Cannot peek: stack is empty");
        return values[top];
    }

    @Override
    public boolean isEmpty() {
        return top == -1;
    }
}
