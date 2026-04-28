package com.tanushree.vectordb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

@Service
public class DBService {

    @Autowired
    JdbcTemplate jdbc;

    public void save(String id, String text, String embedding) {
        String sql = "INSERT INTO vectors (id, text, embedding) VALUES (?, ?, ?)";
        jdbc.update(sql, id, text, embedding);
    }
}