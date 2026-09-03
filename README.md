# HDFC Life Claim Pipeline

Plain Java console application implementing the HDFC Life Claim Pipeline assignment.

## Requirements

- JDK 21+ recommended
- Plain Java only
- No Spring
- No Java Streams
- Custom singly linked list and custom stack
- `PriorityQueue` used for claim prioritization
- `java.util.Queue` is used only for BFS
- `java.util.concurrent` is used for worker-thread demonstrations

## Project structure

```text
hdfc-life-claim-pipeline/
├── src/
│   └── com/hdfclife/
│       ├── Main.java
│       ├── model/
│       │   ├── Claim.java
│       │   └── Urgency.java
│       ├── list/
│       │   ├── ClaimNode.java
│       │   ├── ClaimLinkedList.java
│       │   ├── ListReverser.java
│       │   ├── CycleDetector.java
│       │   └── DigitListAdder.java
│       ├── stack/
│       │   ├── ClaimStack.java
│       │   ├── ArrayClaimStack.java
│       │   ├── LinkedClaimStack.java
│       │   ├── ParenthesesChecker.java
│       │   └── PostfixEvaluator.java
│       ├── queue/
│       │   ├── CircularClaimQueue.java
│       │   ├── BranchBfs.java
│       │   └── ClaimPriorityDesk.java
│       ├── thread/
│       │   ├── SeedRunnable.java
│       │   ├── ClaimTotalCallable.java
│       │   └── ProducerConsumer.java
│       └── exception/
│           ├── PipelineException.java
│           ├── InvalidIndexException.java
│           ├── EmptyListException.java
│           ├── StackEmptyException.java
│           ├── StackFullException.java
│           ├── QueueEmptyException.java
│           └── QueueFullException.java
├── README.md
└── .gitignore
```

## Run in IntelliJ IDEA

1. Open the project folder.
2. Mark `src` as **Sources Root** if IntelliJ has not detected it.
3. Open `src/com/hdfclife/Main.java`.
4. Run `Main.main()`.

## Run from terminal

From the project root:

```bash
javac -d out $(find src -name "*.java")
java -cp out com.hdfclife.Main
```

Windows PowerShell alternative:

```powershell
javac -d out (Get-ChildItem -Recurse src -Filter *.java | ForEach-Object { $_.FullName })
java -cp out com.hdfclife.Main
```

## Expected main results

The program prints the required demonstrations in the assignment order:

```text
1. Seed list -> 25000, 18000, 42000, 15000, 31000, 9000
2. After insertAt(2, 22000) -> 25000, 18000, 22000, 42000, 15000, 31000, 9000
3. After deleteAt(2) -> 25000, 18000, 42000, 15000, 31000, 9000
4. Reverse iterative -> 9000, 31000, 15000, 42000, 18000, 25000
5. Reverse recursive -> 9000, 31000, 15000, 42000, 18000, 25000
6. Middle of seed -> 15000
7. hasCycle on seed -> false
8. hasCycle after linking tail to index 2 -> true
9. Cycle start amount -> 42000
10. Add-two-numbers -> 0, 0, 0, 3, 4
11. Balanced ((TERM)(ULIP)) -> true
12. Balanced ((TERM)(ULIP) -> false
13. Balanced ([)] -> false
14. Postfix 25000 18000 + 1000 - -> 42000
15. Circular dequeue() -> 25000
16. Circular queue after wrap -> 18000, 42000, 15000, 31000
17. BFS from MUMBAI -> MUMBAI, PUNE, DELHI, HYDERABAD, KOLKATA, CHENNAI
18. PriorityQueue poll ids -> CLM-03, CLM-01, CLM-05, CLM-02, CLM-04, CLM-06
19. Thread state before start -> NEW
20. Thread state after join -> TERMINATED
21. Callable Future.get() sum -> 140000
22. isDone after get -> true
23. CompletableFuture.supplyAsync sum -> 140000
24. Cancelled future -> true
25. Daemon flag -> true
26. Producer-consumer takes -> 25000, 18000, 42000
27. Caught message for invalid list index 99 -> Invalid list index: 99
28. Caught message for empty stack pop -> Cannot pop: stack is empty
29. Caught message for empty queue dequeue -> Cannot dequeue: queue is empty
```

## Complexity

| Operation | Time | Extra Space |
|---|---:|---:|
| `insertAt` / `deleteAt` | O(n) | O(1) |
| Reverse iterative | O(n) | O(1) |
| Reverse recursive | O(n) | O(n) call stack |
| Cycle detect | O(n) | O(1) |
| Middle (slow/fast) | O(n) | O(1) |
| Add-two-numbers | O(max(m,n)) | O(max(m,n)) result |
| Stack push/pop | O(1) | O(1) per operation |
| Circular enqueue/dequeue | O(1) | O(1) besides array |
| BFS | O(V + E) | O(V) |

If a claim queue can grow to millions of entries, a linked list can be useful when the workload needs dynamic growth without reserving a large contiguous array. It also avoids a fixed capacity limit. An array is preferable when memory locality and predictable capacity matter. A circular array queue is especially efficient for bounded workloads because enqueue and dequeue are O(1) without shifting elements. For very large queues, the right choice depends on throughput, memory overhead, and whether a practical capacity bound exists.

## Assignment constraints covered

- Custom linked-list nodes; no `java.util.LinkedList` for the claim list.
- Custom array and linked stacks; no `java.util.Stack` or `ArrayDeque`.
- Floyd slow/fast pointers for middle and cycle operations.
- Cycle is explicitly broken after the demo.
- Digit addition is performed digit-by-digit with carry.
- Circular queue wraps using `% capacity`.
- BFS uses a `Queue<String>` and no recursion.
- `PriorityQueue.poll()` drives claim ordering.
- Runnable, Callable, Future, CompletableFuture, cancellation, daemon flag, and producer-consumer are demonstrated with real threads.
- All required custom exceptions extend `RuntimeException` through `PipelineException`.
