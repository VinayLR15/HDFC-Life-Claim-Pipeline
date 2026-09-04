package com.hdfclife.list;

public final class DigitListAdder {

    private DigitListAdder() {
    }

    public static ClaimLinkedList add(
            ClaimLinkedList first,
            ClaimLinkedList second) {

        ClaimNode a = first.getHead();
        ClaimNode b = second.getHead();

        ClaimLinkedList result =
                new ClaimLinkedList();

        int carry = 0;

        while (a != null ||
                b != null ||
                carry != 0) {

            int sum = carry;

            if (a != null) {
                sum += a.amount;
                a = a.next;
            }

            if (b != null) {
                sum += b.amount;
                b = b.next;
            }

            result.addLast(sum % 10);

            carry = sum / 10;
        }

        return result;
    }
}