package com.assignment;

import java.util.*;
import java.util.concurrent.*;

/**
 *  Task 1 Producer-Consumer using BlockingQueue.
 */

public class Main {
    public static void main(String[] args) throws Exception {
        List<Item> source = new ArrayList<>();
        for(int i = 0; i < 50; i++)
            source.add(new Item(i, "payload-" + i));

        List<Item> dest = Collections.synchronizedList(new ArrayList<>());
        Item poison = new Item(-1, "POISON");

        BlockingQueue<Item> queue = new ArrayBlockingQueue<>(10);

        Thread producer = new Thread(new Producer(queue, source, poison));
        Thread consumer = new Thread(new Consumer(queue, poison, dest));

        producer.start();
        consumer.start();

        producer.join();
        consumer.join();

        System.out.println("Transferred items: " + dest.size());
    }
}