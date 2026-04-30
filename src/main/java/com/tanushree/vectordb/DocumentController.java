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
    public ResponseEntity<String> ask(@RequestBody AskRequest request) {

        String selectedModel = (request.model != null && !request.model.isEmpty())
                ? request.model
                : "llama3.2";

        // Handle short queries directly via AI
        if (request.question.trim().length() < 5) {
            return ResponseEntity.ok(
                    ollamaClient.generate(request.question, selectedModel)
            );
        }

        float[] questionVector = ollamaClient.embed(request.question);
        List<SearchResult> results = vectorStore.search(questionVector, 1, "cosine");

        float bestDistance = results.isEmpty() ? Float.MAX_VALUE : results.get(0).distance;

        // Strong match → return exact answer from DB
        System.out.println("Question: " + request.question);
        System.out.println("Results count: " + results.size());
        System.out.println("Best distance: " + bestDistance);
        if (!results.isEmpty()) {
            System.out.println("Best match: " + results.get(0).vector.text);
        }
        if (!results.isEmpty() && bestDistance < 0.47f) {
            return ResponseEntity.ok(results.get(0).vector.text);
        }

        // Nothing relevant in DB → answer from AI general knowledge only
        System.out.println("Question: " + request.question);
        System.out.println("Results count: " + results.size());
        System.out.println("Best distance: " + bestDistance);
        return ResponseEntity.ok(
                ollamaClient.generate(request.question, selectedModel)
        );
    }
}