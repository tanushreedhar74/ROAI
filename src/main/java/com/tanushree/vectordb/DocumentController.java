package com.tanushree.vectordb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/doc")
public class DocumentController {
    @Autowired
    VectorStore vectorStore;
    @Autowired
    OllamaClient ollamaClient;
    @Autowired
    DBService dbService;
    @PostMapping("/insert")
    public ResponseEntity<String> insert(@RequestBody DocInsertRequest request){
        String id = UUID.randomUUID().toString();
        float[]vector=ollamaClient.embed(request.text);
        Vector v=new Vector(id, vector, request.text);
        vectorStore.add(v);
        dbService.save(id, request.text, Arrays.toString(vector));
        return ResponseEntity.ok("Document inserted: "+id);
    }
    @PostMapping("/ask")
    public ResponseEntity<String>ask(@RequestBody AskRequest request){
        //Convert question to vector
        float[]questionVector=ollamaClient.embed(request.question);
        //find similar
        List<SearchResult> results = vectorStore.search(questionVector, request.k, "cosine");
        //select model
        String selectedModel = (request.model != null && !request.model.isEmpty())
                ? request.model
                : "llama3.2";
        float bestDistance = results.isEmpty() ? Float.MAX_VALUE : results.get(0).distance;
        String answer;

        // handle short queries
        if (request.question.length() < 5) {
            return ResponseEntity.ok(
                    ollamaClient.generate(request.question, selectedModel)
            );
        }
        if (!results.isEmpty() && bestDistance < 0.4f) {
            // return exact stored answer
            String dbAnswer = results.get(0).vector.text;
            return ResponseEntity.ok(dbAnswer);
        } // fallback → use AI
        return ResponseEntity.ok(
                ollamaClient.generate(request.question, selectedModel)
        );

    }
}