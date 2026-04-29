package com.tanushree.vectordb;


import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Map;

@Component
public class OllamaClient {
    //connect ollama
    private final WebClient client=WebClient.create("http://localhost:11434");
    //takes text return numbers
    public  float[]embed(String text){
        Map response=client.post().uri("/api/embeddings")
                .bodyValue(Map.of(
                        "model","nomic-embed-text",
                        "prompt",text
                )).retrieve().bodyToMono(Map.class).block();
        //extract embedding
        List<Double>embedding=(List<Double>)response.get("embedding");
        float[]result=new float[embedding.size()];
        //converting val
        for(int i=0;i<embedding.size();i++){
            result[i]=embedding.get(i).floatValue();
        }
        return result;
    }
    //ai generated texts
    public String generate(String prompt,String model){
        Map response=client.post().uri("/api/generate")
                .bodyValue(Map.of(
                        "model", model,
                        "prompt", prompt,
                        "stream", false
                )).retrieve().bodyToMono(Map.class).block();
        return (String)response.get("response");
    }

}