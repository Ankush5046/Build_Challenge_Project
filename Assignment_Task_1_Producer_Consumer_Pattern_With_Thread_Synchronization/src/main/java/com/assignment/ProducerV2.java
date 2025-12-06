package com.assignment;

import java.util.List;

/**
 * Producer using custom BoundedBuffer (wait/notify).
 */
public class ProducerV2 implements Runnable {

    private final BoundedBuffer<Item> buffer;
    private final List<Item> source;

    public ProducerV2(BoundedBuffer<Item> buffer, List<Item> source){
        this.buffer = buffer;
        this.source = source;
    }

    @Override
    public void run(){
        try {
            for(Item it : source){
                buffer.put(it);
            }
        } catch(Exception e){}
        finally {
            buffer.setFinished();
        }
    }
}