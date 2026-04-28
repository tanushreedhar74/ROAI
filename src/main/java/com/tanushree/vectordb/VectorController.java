package com.tanushree.vectordb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@RestController
@RequestMapping("/api")
public class VectorController {
    @Autowired
    public VectorStore vectorStore;
@PostMapping("/insert")
    public ResponseEntity<String>insert(@RequestBody InsertRequest request){
    Vector vector=new Vector(request.id,request.values,request.text);
    vectorStore.add(vector);
    return ResponseEntity.ok("Vector inserted successfully");
    }
@GetMapping("/search")
    public ResponseEntity<List<SearchResult>>search(@RequestBody SearchRequest request){
    List<SearchResult>result=vectorStore.search(request.values,request.k,request.metric);
    return  ResponseEntity.ok(result);
}
@GetMapping("/stats")
    public ResponseEntity<String> stats(){
    return  ResponseEntity.ok("Total vector stored:"+vectorStore.size());
}
@DeleteMapping("/delete/{id}")
    public  ResponseEntity<String>delete(@PathVariable String id){
    boolean deleted=vectorStore.deleteById(id);
    if(deleted){
       return ResponseEntity.ok("Deleted  successfully");
    }else{
        return ResponseEntity.ok("Vector not found");
    }
    }
}
