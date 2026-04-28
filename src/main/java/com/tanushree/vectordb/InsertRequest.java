package com.tanushree.vectordb;

public class InsertRequest {
  public  String id;
  public  float[]values;
  public String text;
    public InsertRequest() {};
  public InsertRequest(String id,float[]values,String text){
      this.id=id;
      this.values=values;
      this.text=text;
  }
}
