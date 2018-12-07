package com.example.codingchallenge.model;

public enum Operation  {
    CREATE((short)0),
    READ((short)1),
    UPDATE((short)2),
    DELETE((short)3);

    private short enumVar;
    Operation(short value) {
        enumVar = value;
    }

    public short Value(){
        return this.enumVar;
    }
}
