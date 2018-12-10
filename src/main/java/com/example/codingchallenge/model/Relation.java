package com.example.codingchallenge.model;

public enum Relation {
    EQ((short)0), // =
    GT((short)1), // >
    GTE((short)2), // >=
    LT((short)3), // <
    LTE((short)4); // <=

    private short enumVar;
    Relation(short value) {
        enumVar = value;
    }

    public short Value(){
        return this.enumVar;
    }
}
