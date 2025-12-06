package com.assignment;

/**
 * Represents a unit of data transferred between Producer and Consumer.
 */
public class Item {
    private final int id;
    private final String payload;

    public Item(int id, String payload){
        this.id = id;
        this.payload = payload;
    }

    public int getId(){ return id; }
    public String getPayload(){ return payload; }

    @Override
    public String toString(){
        return "Item[" + id + ":" + payload + "]";
    }
}