package com.hdfclife.list;

import com.hdfclife.exception.EmptyListException;
import com.hdfclife.exception.InvalidIndexException;

public class ClaimLinkedList {

    private ClaimNode head;
    private int size;

    public void addLast(int amount) {
        ClaimNode newNode = new ClaimNode(amount);

        if (head == null) {
            head = newNode;
        } else {
            ClaimNode current = head;

            while (current.next != null) {
                current = current.next;
            }

            current.next = newNode;
        }

        size++;
    }

    public void addFirst(int amount) {
        ClaimNode newNode = new ClaimNode(amount);

        newNode.next = head;
        head = newNode;

        size++;
    }

    public void insertAt(int index, int amount) {

        if (index < 0 || index > size) {
            throw new InvalidIndexException(
                    "Invalid list index: " + index
            );
        }

        if (index == 0) {
            addFirst(amount);
            return;
        }

        if (index == size) {
            addLast(amount);
            return;
        }

        ClaimNode previous = nodeAt(index - 1);

        ClaimNode newNode = new ClaimNode(amount);

        newNode.next = previous.next;
        previous.next = newNode;

        size++;
    }

    public void deleteAt(int index) {

        if (size == 0) {
            throw new EmptyListException(
                    "Cannot delete index "
                            + index
                            + " from an empty list"
            );
        }

        if (index < 0 || index >= size) {
            throw new InvalidIndexException(
                    "Invalid list index: " + index
            );
        }

        if (index == 0) {
            head = head.next;
            size--;
            return;
        }

        ClaimNode previous = nodeAt(index - 1);

        previous.next = previous.next.next;

        size--;
    }

    public ClaimNode nodeAt(int index) {

        if (index < 0 || index >= size) {
            throw new InvalidIndexException(
                    "Invalid list index: " + index
            );
        }

        ClaimNode current = head;

        for (int i = 0; i < index; i++) {
            current = current.next;
        }

        return current;
    }

    void setHead(ClaimNode head) {
        this.head = head;
    }

    public ClaimNode getHead() {
        return head;
    }

    public ClaimNode getTail() {

        if (head == null) {
            return null;
        }

        ClaimNode current = head;

        while (current.next != null) {
            current = current.next;
        }

        return current;
    }

    public int size() {
        return size;
    }

    public int[] toArray() {

        int[] result = new int[size];

        ClaimNode current = head;

        for (int i = 0; i < size; i++) {
            result[i] = current.amount;
            current = current.next;
        }

        return result;
    }

    public void print() {

        ClaimNode current = head;

        StringBuilder output = new StringBuilder();

        while (current != null) {

            if (output.length() > 0) {
                output.append(", ");
            }

            output.append(current.amount);

            current = current.next;
        }

        System.out.println(output);
    }

    public ClaimLinkedList copy() {

        ClaimLinkedList copy = new ClaimLinkedList();

        ClaimNode current = head;

        for (int i = 0; i < size; i++) {

            copy.addLast(current.amount);

            current = current.next;
        }

        return copy;
    }
}