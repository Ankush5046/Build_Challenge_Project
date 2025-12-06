package com.assignment;

import java.util.List;

/**
 * Consumer using custom BoundedBuffer with wait/notify.
 */
public class ConsumerV2 implements Runnable {

    private final BoundedBuffer<Item> buffer;
    private final List<Item> dest;

    public ConsumerV2(BoundedBuffer<Item> buffer, List<Item> dest){
        this.buffer = buffer;
        this.dest = dest;
    }

    @Override
    public void run(){
        try {
            while(true){
                Item it = buffer.get();
                if(it == null) break; // finished
                dest.add(it);
            }
        } catch(Exception e){}
    }
}