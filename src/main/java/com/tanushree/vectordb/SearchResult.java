package com.tanushree.vectordb;

public class SearchResult {
    public Vector vector;
    public float distance;

    public SearchResult(Vector vector, float distance) {
        this.vector = vector;
        this.distance = distance;
    }
}