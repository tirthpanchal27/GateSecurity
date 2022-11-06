package com.example.gatesecurutiyapp;

public class fetchResident
{
     String name;
     String Block;


    public fetchResident(){}

    public fetchResident(String name, String block) {
        this.name = name;
        Block = block;

    }

    public String getName() {
        return name;
    }

    public String getBlock() {
        return Block;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBlock(String block) {
        Block = block;
    }
}
