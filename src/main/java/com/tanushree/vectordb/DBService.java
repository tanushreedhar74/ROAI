package com.tanushree.vectordb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DBService {

    @Autowired
    JdbcTemplate jdbc;

    public void save(String id, String text, String embedding) {
        String sql = "INSERT INTO vectors (id, text, embedding) VALUES (?, ?, ?)";
        jdbc.update(sql, id, text, embedding);
    }
//loading previous data
    public List<Vector> loadAll() {
        String sql = "SELECT * FROM vectors";

        return jdbc.query(sql, (rs, rowNum) -> {

            String id = rs.getString("id");
            String text = rs.getString("text");
            String embeddingStr = rs.getString("embedding");

            float[] embedding = parseEmbedding(embeddingStr);

            return new Vector(id, embedding, text);
        });
    }
    private float[] parseEmbedding(String str) {

        str = str.replace("[", "").replace("]", "");

        String[] parts = str.split(",");

        float[] result = new float[parts.length];

        for (int i = 0; i < parts.length; i++) {
            result[i] = Float.parseFloat(parts[i].trim());
        }

        return result;
    }

}