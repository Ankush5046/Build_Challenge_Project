package com.assignment;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Consumer that reads items from the BlockingQueue and stores them in a destination list.
 */
public class Consumer implements Runnable {
    private static final Logger log = LoggerFactory.getLogger(Consumer.class);

    private final BlockingQueue<Item> queue;
    private final Item poison;
    private final List<Item> dest;

    public Consumer(BlockingQueue<Item> queue, Item poison, List<Item> dest){
        this.queue = queue;
        this.poison = poison;
        this.dest = dest;
    }

    @Override
    public void run(){
        try {
            while(true){
                Item it = queue.take(); // blocks if empty
                if(it == poison){
                    log.info("Poison pill received. Stopping consumer...");
                    break;
                }
                log.info("Consumed: {}", it);
                dest.add(it);
            }
        } catch(Exception e){
            Thread.currentThread().interrupt();
        }
    }
}