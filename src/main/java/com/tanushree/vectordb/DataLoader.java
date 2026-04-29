package com.tanushree.vectordb;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class DataLoader {

    @Autowired
    DBService dbService;

    @Autowired
    VectorStore vectorStore;

    @PostConstruct
    public void loadData() {

        List<Vector> vectors = dbService.loadAll();

        for (Vector v : vectors) {
            vectorStore.add(v);
        }

        System.out.println("Loaded " + vectors.size() + " vectors from DB");
    }
}