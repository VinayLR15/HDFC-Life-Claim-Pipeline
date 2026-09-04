package com.hdfclife.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public final class ProducerConsumer {

    private ProducerConsumer() {
    }

    public static List<Integer> run() throws InterruptedException {

        final BlockingQueue<Integer> queue = new ArrayBlockingQueue<Integer>(2);

        final List<Integer> consumed = new ArrayList<Integer>();

        Thread producer = new Thread(new Runnable() {

                            @Override
                            public void run() {

                                try {

                                    queue.put(25000);
                                    queue.put(18000);
                                    queue.put(42000);

                                } catch (InterruptedException e) {

                                    Thread.currentThread()
                                            .interrupt();
                                }
                            }
                        },
                        "claim-producer"
                );

        Thread consumer = new Thread(new Runnable() {

                            @Override
                            public void run() {

                                try {

                                    for (int i = 0; i < 3; i++) {

                                        consumed.add(queue.take());
                                    }

                                } catch (InterruptedException e) {

                                    Thread.currentThread()
                                            .interrupt();
                                }
                            }
                        },
                        "claim-consumer"
                );

        producer.start();
        consumer.start();

        producer.join();
        consumer.join();

        return consumed;
    }
}