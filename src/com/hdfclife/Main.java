package com.hdfclife;


import com.hdfclife.exception.QueueEmptyException;
import com.hdfclife.exception.StackEmptyException;
import com.hdfclife.list.ClaimLinkedList;
import com.hdfclife.list.ClaimNode;
import com.hdfclife.list.CycleDetector;
import com.hdfclife.list.DigitListAdder;
import com.hdfclife.list.ListReverser;
import com.hdfclife.model.Claim;
import com.hdfclife.model.Urgency;
import com.hdfclife.queue.BranchBfs;
import com.hdfclife.queue.CircularClaimQueue;
import com.hdfclife.queue.ClaimPriorityDesk;
import com.hdfclife.stack.ArrayClaimStack;
import com.hdfclife.stack.ParenthesesChecker;
import com.hdfclife.stack.PostfixEvaluator;
import com.hdfclife.thread.ClaimTotalCallable;
import com.hdfclife.thread.ProducerConsumer;
import com.hdfclife.thread.SeedRunnable;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Main {
    private static final int[] AMOUNTS = {25000, 18000, 42000, 15000, 31000, 9000};

    public static void main(String[] args) throws Exception {
        ClaimLinkedList seed = createSeedList();

        System.out.print("1. Seed list -> ");
        seed.print();

        ClaimLinkedList editDemo = seed.copy();
        editDemo.insertAt(2, 22000);
        System.out.print("2. After insertAt(2, 22000) -> ");
        editDemo.print();
        editDemo.deleteAt(2);
        System.out.print("3. After deleteAt(2) -> ");
        editDemo.print();

        ClaimLinkedList iterative = seed.copy();
        ListReverser.reverseIterative(iterative);
        System.out.print("4. Reverse iterative -> ");
        iterative.print();

        ClaimLinkedList recursive = seed.copy();
        ListReverser.reverseRecursive(recursive);
        System.out.print("5. Reverse recursive -> ");
        recursive.print();

        System.out.println("6. Middle of seed -> " + CycleDetector.middle(seed).amount);
        System.out.println("7. hasCycle on seed -> " + CycleDetector.hasCycle(seed));

        ClaimLinkedList cyclic = seed.copy();
        ClaimNode tail = cyclic.getTail();
        tail.next = cyclic.nodeAt(2);
        System.out.println("8. hasCycle after linking tail to index 2 -> " + CycleDetector.hasCycle(cyclic));
        System.out.println("9. Cycle start amount -> " + CycleDetector.findCycleStart(cyclic).amount);
        tail.next = null;

        ClaimLinkedList digitsA = digits(25000);
        ClaimLinkedList digitsB = digits(18000);
        ClaimLinkedList sum = DigitListAdder.add(digitsA, digitsB);
        System.out.print("10. Add-two-numbers -> ");
        sum.print();

        System.out.println("11. Balanced ((TERM)(ULIP)) -> " +
                ParenthesesChecker.isBalanced("((TERM)(ULIP))"));
        System.out.println("12. Balanced ((TERM)(ULIP) -> " +
                ParenthesesChecker.isBalanced("((TERM)(ULIP)"));
        System.out.println("13. Balanced ([)] -> " +
                ParenthesesChecker.isBalanced("([)]"));

        System.out.println("14. Postfix 25000 18000 + 1000 - -> " +
                PostfixEvaluator.evaluate("25000 18000 + 1000 -"));

        CircularClaimQueue queue = new CircularClaimQueue(4);
        queue.enqueue(25000);
        queue.enqueue(18000);
        queue.enqueue(42000);
        System.out.println("15. Circular dequeue() -> " + queue.dequeue());
        queue.enqueue(15000);
        queue.enqueue(31000);
        System.out.println("16. Circular queue after wrap -> " + queue.contents());

        System.out.println("17. BFS from MUMBAI -> " + BranchBfs.traverse());

        Claim[] claims = createClaims();
        System.out.println("18. PriorityQueue poll ids -> " + ClaimPriorityDesk.pollIds(claims));

        Thread stateThread = new Thread(new SeedRunnable(AMOUNTS[0]), "seed-runnable");
        System.out.println("19. Thread state before start -> " + stateThread.getState());
        stateThread.start();
        stateThread.join();
        System.out.println("20. Thread state after join -> " + stateThread.getState());

        ExecutorService executor = Executors.newFixedThreadPool(2);
        try {
            Future<Integer> future = executor.submit(new ClaimTotalCallable(AMOUNTS));
            System.out.println("21. Callable Future.get() sum -> " + future.get());
            System.out.println("22. isDone after get -> " + future.isDone());

            CompletableFuture<Integer> asyncSum = CompletableFuture.supplyAsync(() -> sumAmounts(AMOUNTS), executor);
            System.out.println("23. CompletableFuture.supplyAsync sum -> " + asyncSum.get());

            Future<Integer> cancellable = executor.submit(new java.util.concurrent.Callable<Integer>() {
                @Override
                public Integer call() throws Exception {
                    Thread.sleep(30000);
                    return 0;
                }
            });
            Thread.sleep(100);
            cancellable.cancel(true);
            System.out.println("24. Cancelled future -> " + cancellable.isCancelled());

            Thread daemon = new Thread(new SeedRunnable(0), "daemon-demo");
            daemon.setDaemon(true);
            System.out.println("25. Daemon flag -> " + daemon.isDaemon());

            List<Integer> consumed = ProducerConsumer.run();
            System.out.println("26. Producer-consumer takes -> " + joinIntegers(consumed));
        } finally {
            executor.shutdownNow();
        }

        try {
            ClaimLinkedList invalid = seed.copy();
            invalid.deleteAt(99);
        } catch (com.hdfclife.exception.PipelineException e) {
            System.out.println("27. Caught message for invalid list index 99 -> " + e.getMessage());
        }

        try {
            new ArrayClaimStack().pop();
        } catch (StackEmptyException e) {
            System.out.println("28. Caught message for empty stack pop -> " + e.getMessage());
        }

        try {
            new CircularClaimQueue(4).dequeue();
        } catch (QueueEmptyException e) {
            System.out.println("29. Caught message for empty queue dequeue -> " + e.getMessage());
        }
    }

    private static ClaimLinkedList createSeedList() {
        ClaimLinkedList list = new ClaimLinkedList();
        for (int i = 0; i < AMOUNTS.length; i++) list.addLast(AMOUNTS[i]);
        return list;
    }

    private static ClaimLinkedList digits(int number) {
        ClaimLinkedList list = new ClaimLinkedList();
        while (number > 0) {
            list.addLast(number % 10);
            number /= 10;
        }
        return list;
    }

    private static Claim[] createClaims() {
        return new Claim[] {
                new Claim("CLM-01", 25000, "HDFC-LIFE-1001", "Anita Sharma", Urgency.HIGH),
                new Claim("CLM-02", 18000, "HDFC-LIFE-1002", "Rahul Mehta", Urgency.MEDIUM),
                new Claim("CLM-03", 42000, "HDFC-LIFE-1005", "Sneha Patel", Urgency.HIGH),
                new Claim("CLM-04", 15000, "HDFC-LIFE-1004", "Vikram Singh", Urgency.LOW),
                new Claim("CLM-05", 31000, "HDFC-LIFE-1001", "Anita Sharma", Urgency.MEDIUM),
                new Claim("CLM-06", 9000, "HDFC-LIFE-1003", "Priya Nair", Urgency.LOW)
        };
    }

    private static int sumAmounts(int[] values) {
        int sum = 0;
        for (int i = 0; i < values.length; i++) sum += values[i];
        return sum;
    }

    private static String joinIntegers(List<Integer> values) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < values.size(); i++) {
            if (i > 0) result.append(", ");
            result.append(values.get(i));
        }
        return result.toString();
    }
}
