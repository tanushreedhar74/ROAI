package com.tanushree.vectordb;

public class SearchRequest {
    public float[]values;
    public int k;
    public String metric;
    public SearchRequest() {};
    public SearchRequest(float[]values,int k,String metric){
        this.values=values;
        this.k=k;
        this.metric=metric;
    }
}
