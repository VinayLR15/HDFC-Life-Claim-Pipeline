package com.hdfclife.list;

public final class ListReverser {

    private ListReverser() {
    }

    public static void reverseIterative(ClaimLinkedList list) {

        ClaimNode previous = null;
        ClaimNode current = list.getHead();

        while (current != null) {

            ClaimNode next = current.next;

            current.next = previous;

            previous = current;
            current = next;
        }

        list.setHead(previous);
    }

    public static void reverseRecursive(ClaimLinkedList list) {

        ClaimNode newHead =
                reverseRecursive(
                        list.getHead(),
                        null
                );

        list.setHead(newHead);
    }

    private static ClaimNode reverseRecursive(
            ClaimNode current,
            ClaimNode previous) {

        if (current == null) {
            return previous;
        }

        ClaimNode next = current.next;

        current.next = previous;

        return reverseRecursive(
                next,
                current
        );
    }
}