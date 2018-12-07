package com.example.codingchallenge.model;

public enum Entity {
    ADMIN ((short)0),
    ROLE ((short)1),
    ORGANIZATION ((short)2),
    PRODUCT ((short)3),
    SHARING_STATEMENT ((short)4);

    private short enumVar;
    Entity(short value) {
        enumVar = value;
    }

    public short Value(){
        return this.enumVar;
    }
}
