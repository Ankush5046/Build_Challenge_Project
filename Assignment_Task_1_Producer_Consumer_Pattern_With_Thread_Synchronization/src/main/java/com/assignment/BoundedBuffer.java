package com.assignment;

import java.util.LinkedList;

/**
 * Custom bounded buffer implementing wait/notify for Task 2.
 */
public class BoundedBuffer<T> {

    private final LinkedList<T> buffer = new LinkedList<>();
    private final int capacity;
    private boolean finished = false;

    public BoundedBuffer(int capacity){
        this.capacity = capacity;
    }

    public synchronized void put(T item) throws InterruptedException {
        while(buffer.size() == capacity){
            wait(); // wait until space becomes available
        }
        buffer.addLast(item);
        notifyAll(); // notify a waiting consumer
    }

    public synchronized T get() throws InterruptedException {
        while(buffer.isEmpty()){
            if(finished) return null; // producer done
            wait(); // wait until items appear
        }
        T item = buffer.removeFirst();
        notifyAll(); // notify producer
        return item;
    }

    public synchronized void setFinished(){
        finished = true;
        notifyAll();
    }
}