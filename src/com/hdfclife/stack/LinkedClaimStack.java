package com.hdfclife.stack;

import com.hdfclife.exception.StackEmptyException;

public class LinkedClaimStack
        implements ClaimStack {

    private static class Node {

        int value;
        Node next;

        Node(int value, Node next) {
            this.value = value;
            this.next = next;
        }
    }

    private Node top;
    
    @Override
    public void push(int value) {
        top = new Node(value, top);
    }

    @Override
    public int pop() {

        if (isEmpty()) {
            throw new StackEmptyException(
                    "Cannot pop: stack is empty"
            );
        }

        int value = top.value;

        top = top.next;

        return value;
    }

    @Override
    public int peek() {

        if (isEmpty()) {
            throw new StackEmptyException(
                    "Cannot peek: stack is empty"
            );
        }

        return top.value;
    }

    @Override
    public boolean isEmpty() {
        return top == null;
    }
}