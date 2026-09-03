package com.hdfclife.list;

public final class ListReverser {
    private ListReverser() {}

    // Time: O(n), extra space: O(1).
    public static void reverseIterative(ClaimLinkedList list) {
        ClaimNode previous = null;
        ClaimNode current = list.getHead();
        while (current != null) {
            ClaimNode next = current.next;
            current.next = previous;
            previous = current;
            current = next;
        }
        // The public list intentionally keeps its head private.
        list.setHead(previous);
    }

    // Time: O(n), extra space: O(n) due to the call stack.
    public static void reverseRecursive(ClaimLinkedList list) {
        ClaimNode newHead = reverseRecursive(list.getHead(), null);
        list.setHead(newHead);
    }

    private static ClaimNode reverseRecursive(ClaimNode current, ClaimNode previous) {
        if (current == null) return previous;
        ClaimNode next = current.next;
        current.next = previous;
        return reverseRecursive(next, current);
    }

    private static void setHead(ClaimLinkedList list, ClaimNode newHead) {
        try {
            java.lang.reflect.Field field = ClaimLinkedList.class.getDeclaredField("head");
            field.setAccessible(true);
            field.set(list, newHead);
        } catch (ReflectiveOperationException e) {
            throw new IllegalStateException("Unable to update list head", e);
        }
    }
}
