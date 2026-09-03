package com.hdfclife.list;

public final class CycleDetector {
    private CycleDetector() {}

    // Time: O(n), extra space: O(1).
    public static boolean hasCycle(ClaimLinkedList list) {
        ClaimNode slow = list.getHead();
        ClaimNode fast = list.getHead();
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) return true;
        }
        return false;
    }

    // Time: O(n), extra space: O(1).
    public static ClaimNode findCycleStart(ClaimLinkedList list) {
        ClaimNode slow = list.getHead();
        ClaimNode fast = list.getHead();

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                ClaimNode pointer = list.getHead();
                while (pointer != slow) {
                    pointer = pointer.next;
                    slow = slow.next;
                }
                return pointer;
            }
        }
        return null;
    }

    public static ClaimNode middle(ClaimLinkedList list) {
        ClaimNode slow = list.getHead();
        ClaimNode fast = list.getHead();
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}
