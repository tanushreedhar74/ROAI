package com.tanushree.vectordb;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
@Component
public class VectorStore {
    //create a list to keep all the records
    private  final List<Vector> store= new ArrayList<>();
    //add a new vector
    public  void add(Vector vector){
        store.add(vector);
    }
    //how many vectors are stored
    public int size(){
        return store.size();
    }
    //get all vectords
    public List<Vector>getAll(){
        return  store;
    }
    //delete by id
    public boolean deleteById(String id){
        for(int i=0;i<store.size();i++){
            Vector v=store.get(i);
            if(v.id.equals(id)){
                store.remove(i);
                return  true;
            }
        }
        return  false;
    }
    //search
    public List<SearchResult>search(float[]query,int k,String metric){
        List<SearchResult>result=new ArrayList<>();
        for(Vector v:store){
            float distance;
            if(metric.equals("cosine")){
                distance=DistanceUtils.cosine(query,v.values);
            }else if(metric.equals("euclidean")){
                distance=DistanceUtils.euclidean(query,v.values);
            }else{
                distance = DistanceUtils.manhattan(query, v.values);
            }
            result.add(new SearchResult(v,distance));
        }
        result.sort(Comparator.comparingDouble((SearchResult r)->r.distance));
        return result.subList(0, Math.min(k, result.size()));
    }

}
