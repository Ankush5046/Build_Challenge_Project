package com.assignment;

import org.junit.jupiter.api.Test;
import java.util.*;
import java.util.concurrent.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Tests for Task 1 and Task 2 implementations.
 */
public class ProducerConsumerTest {

    @Test
    public void testTask1BlockingQueue() throws Exception {
        List<Item> src = new ArrayList<>();
        for(int i=0;i<20;i++) src.add(new Item(i,"payload-"+i));

        BlockingQueue<Item> q = new ArrayBlockingQueue<>(5);
        List<Item> dest = Collections.synchronizedList(new ArrayList<>());
        Item poison = new Item(-1,"POISON");

        Thread p = new Thread(new Producer(q, src, poison));
        Thread c = new Thread(new Consumer(q, poison, dest));

        p.start(); c.start();
        p.join(); c.join();

        assertEquals(src.size(), dest.size());
        assertTrue(dest.containsAll(src));
    }

    @Test
    public void testTask2BoundedBuffer() throws Exception {
        List<Item> src = new ArrayList<>();
        for(int i=0;i<15;i++) src.add(new Item(i,"payload-"+i));

        BoundedBuffer<Item> buffer = new BoundedBuffer<>(5);
        List<Item> dest = Collections.synchronizedList(new ArrayList<>());

        Thread p = new Thread(new ProducerV2(buffer, src));
        Thread c = new Thread(new ConsumerV2(buffer, dest));

        p.start(); c.start();
        p.join(); c.join();

        assertEquals(src.size(), dest.size());
    }
}
