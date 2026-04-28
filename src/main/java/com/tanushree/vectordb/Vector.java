package com.tanushree.vectordb;

public class Vector {
    public String id;
    public float[]values;
    public String text;
    public Vector(String id, float[]values, String text){
    this.id=id;
    this.values=values;
    this.text=text;
    }
}
