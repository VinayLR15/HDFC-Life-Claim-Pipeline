package com.hdfclife.queue;

import com.hdfclife.exception.QueueEmptyException;
import com.hdfclife.exception.QueueFullException;

public class CircularClaimQueue {

    private final int[] values;

    private int front;
    private int rear;
    private int size;

    public CircularClaimQueue(int capacity) {

        if (capacity <= 0) {
            throw new IllegalArgumentException("Capacity must be positive");
        }

        values = new int[capacity];
    }

    public void enqueue(int value) {

        if (isFull()) {
            throw new QueueFullException("Queue is full");
        }

        values[rear] = value;

        rear = (rear + 1) % values.length;

        size++;
    }

    public int dequeue() {

        if (isEmpty()) {
            throw new QueueEmptyException("Cannot dequeue: queue is empty");
        }

        int value = values[front];

        front = (front + 1) % values.length;

        size--;

        return value;
    }

    public boolean isFull() {
        return size == values.length;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public String contents() {

        StringBuilder result = new StringBuilder();

        for (int i = 0; i < size; i++) {

            if (i > 0) {
                result.append(", ");
            }

            result.append(values[(front + i) % values.length]);
        }

        return result.toString();
    }
}