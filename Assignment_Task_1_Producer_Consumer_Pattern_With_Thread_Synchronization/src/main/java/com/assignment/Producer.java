package com.assignment;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Producer that reads items from a source list and places them into a shared BlockingQueue.
 */
public class Producer implements Runnable {
    private static final Logger log = LoggerFactory.getLogger(Producer.class);

    private final BlockingQueue<Item> queue;
    private final List<Item> source;
    private final Item poison;

    public Producer(BlockingQueue<Item> queue, List<Item> source, Item poison){
        this.queue = queue;
        this.source = source;
        this.poison = poison;
    }

    @Override
    public void run() {
        try {
            for(Item it : source){
                log.info("Producing: {}", it);
                queue.put(it); // blocks if queue is full
            }
        } catch (InterruptedException e){
            Thread.currentThread().interrupt();
        } finally {
            try {
                log.info("Sending poison pill...");
                queue.put(poison);
            } catch(Exception e){}
        }
    }
}