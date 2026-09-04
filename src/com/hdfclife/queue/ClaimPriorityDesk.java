package com.hdfclife.queue;

import com.hdfclife.model.Claim;
import com.hdfclife.model.Urgency;

import java.util.Comparator;
import java.util.PriorityQueue;

public final class ClaimPriorityDesk {

    private ClaimPriorityDesk() {
    }

    public static String pollIds(Claim[] claims) {

        PriorityQueue<Claim> queue = new PriorityQueue<Claim>(new Comparator<Claim>() {

                            @Override
                            public int compare(Claim first, Claim second) {

                                int urgencyCompare = Integer.compare(
                                                rank(first.getUrgency()),
                                                rank(second.getUrgency())
                                        );

                                if (urgencyCompare != 0) {
                                    return urgencyCompare;
                                }

                                return Integer.compare(
                                        second.getAmount(),
                                        first.getAmount()
                                );
                            }
                        }
                );

        for (int i = 0; i < claims.length; i++) {

            queue.offer(claims[i]);
        }

        StringBuilder result = new StringBuilder();

        while (!queue.isEmpty()) {

            if (result.length() > 0) {
                result.append(", ");
            }

            result.append(queue.poll().getClaimId());
        }

        return result.toString();
    }

    private static int rank(Urgency urgency) {

        switch (urgency) {

            case HIGH:
                return 0;

            case MEDIUM:
                return 1;

            case LOW:
                return 2;

            default:
                throw new IllegalArgumentException("Unknown urgency");
        }
    }
}